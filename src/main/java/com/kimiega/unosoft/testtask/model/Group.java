package com.kimiega.unosoft.testtask.model;

import java.util.*;

public class Group {
    private final Set<String> rows;

    public Group() {
        rows = new HashSet<>();
    }

    public void addRow(String row) {
        rows.add(row);
    }

    public Set<String> getRows() {
        return rows;
    }
}
