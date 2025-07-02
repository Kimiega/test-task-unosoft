package com.kimiega.unosoft.testtask.core;

import com.kimiega.unosoft.testtask.model.Group;
import com.kimiega.unosoft.testtask.model.Result;

import java.util.*;

public class GroupMatcher {
    private static final String EMPTY_VALUE = "\"\"";
    private static final String QUOTE_MARK = "\"";
    private static final String SEMI_COLON = ";";


    public GroupMatcher() {
    }

    public List<Group> getGroups(List<String> rows) {
        Set<String> setRows = new HashSet<>(rows);
        List<Map<String, Group>> groupMappings = new ArrayList<>();
        List<Group> groups = new ArrayList<>();

        for (String row : setRows) {
            String[] values = row.trim().split(SEMI_COLON);
            Group foundGroup = null;

            boolean badRow = false;

            for (int i = 0; i < values.length; i++) {

                var preparingResult = prepareValue(values[i]);

                if (!preparingResult.isSuccess()) {
                    badRow = true;
                    break;
                }

                if (groupMappings.size() <= i) {
                    break;
                }
                if (preparingResult.getValue() == null) {
                    continue;
                }
                if (groupMappings.get(i).containsKey(preparingResult.getValue())) {
                    foundGroup = groupMappings.get(i).get(preparingResult.getValue());
                    break;
                }
            }

            if (badRow) {
                continue;
            }

            if (foundGroup == null) {
                foundGroup = new Group();
                groups.add(foundGroup);
            }

            foundGroup.addRow(row);

            for (int i = 0; i < values.length; i++) {
                if (groupMappings.size() <= i) {
                    groupMappings.add(new HashMap<>());
                }
                var preparingResult = prepareValue(values[i]);
                if (!preparingResult.isSuccess()) {
                    throw new IllegalArgumentException(preparingResult.getValue());
                }
                if (preparingResult.getValue() == null) {
                    continue;
                }
                groupMappings.get(i).put(preparingResult.getValue(), foundGroup);
            }
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
}
