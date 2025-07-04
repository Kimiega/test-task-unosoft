package com.kimiega.unosoft.testtask;

import com.kimiega.unosoft.testtask.core.GroupMatcher;
import com.kimiega.unosoft.testtask.io.FileGroupsWriter;
import com.kimiega.unosoft.testtask.io.FileRowsReader;
import com.kimiega.unosoft.testtask.io.GroupsWriter;
import com.kimiega.unosoft.testtask.io.RowsReader;
import com.kimiega.unosoft.testtask.model.Group;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String OUTPUT_FILENAME = "output.txt";

    public static void main(String[] args) {

        try {
            validateArgs(args);

            String path = getPathFromArgs(args);

            RowsReader rowsReader = new FileRowsReader(path);

            List<String> rows = rowsReader.readRows()
                    .stream().distinct().collect(Collectors.toList());

            GroupMatcher groupMatcher = new GroupMatcher();

            List<Group> groups = groupMatcher.getGroups(rows);

            groups.sort((a, b) -> b.getRows().size() - a.getRows().size());

            GroupsWriter groupsWriter = new FileGroupsWriter(OUTPUT_FILENAME);

            groupsWriter.writeGroups(groups);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar app.jar <full/path/to/file>");
            System.exit(1);
        }
    }

    private static String getPathFromArgs(String[] args) {
        return args[0];
    }
}
