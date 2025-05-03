package com.evision.TextSimilarityEngine.Model;

public class FileMatcherResult {
    double matcherPercentage;
    String fileName;

    public FileMatcherResult(String fileName,Double matcherPercentage) {
        this.matcherPercentage = matcherPercentage;
        this.fileName = fileName;
    }

    public double getMatcherPercentage() {
        return matcherPercentage;
    }

    public String getFileName() {
        return fileName;
    }
}
