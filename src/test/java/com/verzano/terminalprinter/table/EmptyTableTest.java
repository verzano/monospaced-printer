package com.verzano.terminalprinter.table;

import org.junit.Test;

import static com.verzano.terminalprinter.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.terminalprinter.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

public class EmptyTableTest {
  @Test
  public void createEmpty1x1Table() throws Exception {
    String empty1x1Table = getTestResourceAsString("table/empty/Empty1x1Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null}
    }));

    assertEquals(empty1x1Table, printedTable);
  }

  @Test
  public void createEmpty1x2Table() throws Exception {
    String empty1x1Table = getTestResourceAsString("table/empty/Empty1x2Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null, null}
    }));

    assertEquals(empty1x1Table, printedTable);
  }

  @Test
  public void createEmpty2x1Table() throws Exception {
    String empty1x1Table = getTestResourceAsString("table/empty/Empty2x1Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null},
        {null}
    }));

    assertEquals(empty1x1Table, printedTable);
  }

  @Test
  public void createEmpty2x2Table() throws Exception {
    String empty1x1Table = getTestResourceAsString("table/empty/Empty2x2Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {null, null},
        {null, null}
    }));

    assertEquals(empty1x1Table, printedTable);
  }
}
