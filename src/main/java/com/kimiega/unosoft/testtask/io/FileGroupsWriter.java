package com.kimiega.unosoft.testtask.io;

import com.kimiega.unosoft.testtask.model.Group;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGroupsWriter extends AbstractGroupWriter {
    private final boolean APPEND_FILE = false;

    private final File outputFile;

    public FileGroupsWriter(String outputFilename) {
        this.outputFile = new File(outputFilename);
    }

    @Override
    public void writeGroups(List<Group> groups) throws IOException {
        String text = groupsToString(groups);

        outputFile.createNewFile();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, APPEND_FILE))) {
            bw.write(text);
        }
    }
}
