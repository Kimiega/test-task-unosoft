package com.kimiega.unosoft.testtask.io;

import java.io.IOException;
import java.util.List;

public interface RowsReader {
    List<String> readRows() throws IOException;
}
