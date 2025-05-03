package com.evision.TextSimilarityEngine.Service;

import com.evision.TextSimilarityEngine.Model.FileMatcherResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Service
public class ServiceImpl implements FileMatcherService {

    @Value("${base.file.path}")
    private String baseFilepath;
    @Value("${pool.directory}")
    private String poolDirectory;

    public Set<String> readBaseFileWords() throws IOException {
        return extractWords(Path.of(baseFilepath));
    }
    public Map<String,Set<String>> readPoolfiles() throws IOException {
        File directory = new File(poolDirectory);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null) return Collections.emptyMap();

        Map<String, Set<String>> fileWords = new HashMap<>();
        int count = 0;
        for (File file : files) {
            if (count >= 20){
                break;}
            Set<String> words = extractWords(file.toPath());
            fileWords.put(file.getName(), words);
            count++;
        }
        return fileWords;

    }
    @Override
    public List<FileMatcherResult> compareFiles() throws IOException {
        Set<String> baseWords = readBaseFileWords();
        Map<String,Set<String>> poolfiles = readPoolfiles();
        List<FileMatcherResult> results = new ArrayList<>();

        for (var entry : poolfiles.entrySet()) {
            Set<String> fileWords = entry.getValue();
            int commonCount = (int) fileWords.stream().filter(baseWords::contains).count();
            double score = baseWords.isEmpty() ? 0 : (100.0 * commonCount / baseWords.size());
            results.add(new FileMatcherResult(entry.getKey(), score));
        }
        return results;
    }
    private Set<String> extractWords(Path filePath) {

        Set<String> words = new HashSet<>();

        try (Stream<String> lines = Files.lines(filePath)) {
            lines.flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(word -> word.replaceAll("[^A-Za-z]", "").toLowerCase())
                    .filter(word -> !word.isEmpty())
                    .forEach(words::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }


}
