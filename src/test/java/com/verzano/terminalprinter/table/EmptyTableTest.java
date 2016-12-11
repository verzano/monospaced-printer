package com.verzano.terminalprinter.table;

import org.junit.Test;

import static com.verzano.terminalprinter.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.terminalprinter.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

public class EmptyTableTest {
  @Test
  public void createTable0x0Empty() throws Exception {
    String table0x0Empty = getTestResourceAsString("table/empty/Table0x0Empty.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{}));

    assertEquals(table0x0Empty, printedTable);
  }

  @Test
  public void createTable1x1Empty() throws Exception {
    String table1x1Empty = getTestResourceAsString("table/empty/Table1x1Empty.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null}
    }));

    assertEquals(table1x1Empty, printedTable);
  }

  @Test
  public void createTable1x2Empty() throws Exception {
    String table1x2Empty = getTestResourceAsString("table/empty/Table1x2Empty.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null, null}
    }));

    assertEquals(table1x2Empty, printedTable);
  }

  @Test
  public void createTable2x1Empty() throws Exception {
    String table2x1Empty = getTestResourceAsString("table/empty/Table2x1Empty.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null},
        {null}
    }));

    assertEquals(table2x1Empty, printedTable);
  }

  @Test
  public void createTable2x2Empty() throws Exception {
    String table2x2Empty = getTestResourceAsString("table/empty/Table2x2Empty.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null, null},
        {null, null}
    }));

    assertEquals(table2x2Empty, printedTable);
  }
}
