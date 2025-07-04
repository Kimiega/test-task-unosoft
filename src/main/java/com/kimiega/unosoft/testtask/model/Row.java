package com.kimiega.unosoft.testtask.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Row {

    private final String row;
    private final List<Row> friends;
    private boolean isRead;


    public Row(String row) {
        this.row = row;
        this.friends = new ArrayList<>();
        this.isRead = false;
    }

    public String getRow() {
        return row;
    }

    public List<Row> getFriends() {
        return friends;
    }

    public void addFriend(Row friend) {
        friends.add(friend);
    }

    public boolean isReadRow() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Row row1 = (Row) o;
        return Objects.equals(row, row1.row);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(row);
    }
}
