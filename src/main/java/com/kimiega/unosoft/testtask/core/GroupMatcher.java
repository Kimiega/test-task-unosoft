package com.kimiega.unosoft.testtask.core;

import com.kimiega.unosoft.testtask.model.Group;
import com.kimiega.unosoft.testtask.model.Result;
import com.kimiega.unosoft.testtask.model.Row;

import java.util.*;
import java.util.stream.Collectors;

public class GroupMatcher {
    private static final String EMPTY_VALUE = "\"\"";
    private static final String QUOTE_MARK = "\"";
    private static final char SEMI_COLON_CHAR = ';';


    public GroupMatcher() {}

    public List<Group> getGroups(List<String> rows) {
        List<Row> localRows = rows.stream().map(Row::new).collect(Collectors.toList());

        List<Map<String, Row>> rowMappings = new ArrayList<>();

        for (Row row : localRows) {
            List<String> values = new ArrayList<>();
            boolean isValid = true;

            var iterator = splitRow(row.getRow().trim()).stream().map(this::prepareValue).iterator();
            while (iterator.hasNext()) {
                var result = iterator.next();
                values.add(result.getValue());
                if (!result.isSuccess())
                    isValid = false;
            }

            if (!isValid)
                continue;

            for (int i = 0; i < values.size(); i++) {
                if (rowMappings.size() <= i) {
                    break;
                }
                if (values.get(i) == null) {
                    continue;
                }
                if (rowMappings.get(i).containsKey(values.get(i))) {
                    rowMappings.get(i).get(values.get(i)).addFriend(row);
                    row.addFriend(rowMappings.get(i).get(values.get(i)));
                }
            }

            for (int i = 0; i < values.size(); i++) {
                if (rowMappings.size() <= i) {
                    rowMappings.add(new HashMap<>());
                }

                if (values.get(i) == null) {
                    continue;
                }
                if (!rowMappings.get(i).containsKey(values.get(i)))
                    rowMappings.get(i).put(values.get(i), row);
            }
        }

        List<Group> groups = new ArrayList<>();

        for (Row row : localRows) {
            if (row.isReadRow())
                continue;
            Queue<Row> queue = new LinkedList<>();
            queue.add(row);
            Group group = new Group();

            while (!queue.isEmpty()) {
                Row nextRow = queue.poll();
                if (nextRow.isReadRow())
                    continue;
                nextRow.setIsRead(true);
                group.addRow(nextRow.getRow());
                queue.addAll(nextRow.getFriends());
            }

            groups.add(group);
        }

        return groups;
    }

    private Result<String> prepareValue(String value) {
        if (value == null || value.isEmpty() || value.equals(EMPTY_VALUE)) {
            return new Result<>(true, null);
        }
        var splitValue = value.split(QUOTE_MARK);
        if (splitValue.length != 2 || !splitValue[0].isEmpty()) {
            return new Result<>(false, "Invalid value: " + value);
        }
        return new Result<>(true, value);
    }

    public static List<String> splitRow(String row) {
        if (row == null || row.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> values = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == SEMI_COLON_CHAR) {
                values.add(builder.toString());
                builder = new StringBuilder();
            } else {
                builder.append(row.charAt(i));
            }
        }
        values.add(builder.toString());
        return values;
    }
}
