package com.kimiega.unosoft.testtask.io;

import com.kimiega.unosoft.testtask.model.Group;

import java.io.IOException;
import java.util.List;

public interface GroupsWriter {

    void writeGroups(List<Group> groups) throws IOException;
}
