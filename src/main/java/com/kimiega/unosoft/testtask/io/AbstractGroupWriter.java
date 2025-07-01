package com.kimiega.unosoft.testtask.io;

import com.kimiega.unosoft.testtask.model.Group;

import java.util.List;

public abstract class AbstractGroupWriter implements GroupsWriter {

    protected String groupsToString(List<Group> groups) {
        StringBuilder sb = new StringBuilder();

        sb.append(groups.stream().filter(g -> g.getRows().size() > 1).count()).append("\n");

        int groupNumber = 1;
        for (Group group : groups) {
            sb.append("Группа ").append(groupNumber++).append("\n");
            group.getRows().forEach(r -> sb.append(r).append("\n"));
            sb.append("\n");
        }

        return sb.toString();
    }
}
