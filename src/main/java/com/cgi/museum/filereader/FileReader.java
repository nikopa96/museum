package com.cgi.museum.filereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    private final String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getLines() throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));
        return bufferedReader.lines().collect(Collectors.toList());
    }
}
