package com.kimiega.unosoft.testtask.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileRowsReader implements RowsReader {
    private final File file;
    public FileRowsReader(String path) throws Exception {
        this.file = getFileFromPath(path);
    }

    @Override
    public List<String> readRows() throws IOException {
        List<String> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line);
            }
        }
        return rows;
    }

    private File getFileFromPath(String path) throws Exception {

        if (path == null || path.isEmpty()) {
            throw new NullPointerException("Provided path is null or empty");
        }

        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("File does not exist: " + path);
        }

        if (!file.isFile()) {
            throw new IOException("There was provided not file: " + path);
        }

        return file;
    }
}
