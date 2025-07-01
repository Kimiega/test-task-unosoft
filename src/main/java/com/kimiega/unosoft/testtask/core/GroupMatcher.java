package com.kimiega.unosoft.testtask.core;

import com.kimiega.unosoft.testtask.model.Group;

import java.util.*;

public class GroupMatcher {
    public GroupMatcher() {}

    public List<Group> getGroups(List<String> rows) {
        Set<String> setRows = new HashSet<>(rows);
        List<Map<String, Group>> groupMappings = new ArrayList<>();
        List<Group> groups = new ArrayList<>();

        for (String row : setRows) {
            var numbers = row.split(";");
            Group foundGroup = null;

            for (int i = 0; i < numbers.length; i++) {
                var number = prepareNumber(numbers[i]);

                if (groupMappings.size() <= i) {
                    break;
                }
                if (number == null) {
                    continue;
                }
                if (groupMappings.get(i).containsKey(number)) {
                    foundGroup = groupMappings.get(i).get(number);
                    break;
                }
            }
            if (foundGroup == null) {
                foundGroup = new Group();
                groups.add(foundGroup);
            }

            foundGroup.addRow(row);

            for (int i = 0; i < numbers.length; i++) {
                if (groupMappings.size() <= i) {
                    groupMappings.add(new HashMap<>());
                }
                var number = prepareNumber(numbers[i]);
                if (number == null) {
                    continue;
                }
                groupMappings.get(i).put(number, foundGroup);
            }
        }

        return groups;
    }

    private String prepareNumber(String number) {
        if (number == null || number.isEmpty()) {
            return null;
        }

        if (number.charAt(0) == '"' && number.charAt(number.length() - 1) == '"' && number.length() > 2) {
            for (int i = 1; i < number.length() - 1; i++) {
                if (!Character.isDigit(number.charAt(i)))
                    return null;
            }
        } else return null;

        return number;
    }
}
