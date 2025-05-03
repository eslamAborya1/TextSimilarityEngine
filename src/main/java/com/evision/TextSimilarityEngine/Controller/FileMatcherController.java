package com.evision.TextSimilarityEngine.Controller;

import com.evision.TextSimilarityEngine.Model.FileMatcherResult;
import com.evision.TextSimilarityEngine.Service.FileMatcherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileMatcherController {
   private final FileMatcherService matcherService;
    public FileMatcherController(FileMatcherService matcherService) {
        this.matcherService = matcherService;
    }
    @GetMapping("/compare")
    public List<FileMatcherResult> compareFiles() throws IOException {
        return  matcherService.compareFiles();
    }

}
