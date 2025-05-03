package com.evision.TextSimilarityEngine.Service;

import com.evision.TextSimilarityEngine.Model.FileMatcherResult;

import java.io.IOException;
import java.util.List;


public interface FileMatcherService {
    List<FileMatcherResult> compareFiles() throws IOException;

}
