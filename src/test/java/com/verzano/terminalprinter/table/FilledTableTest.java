package com.verzano.terminalprinter.table;

import org.junit.Test;

import static com.verzano.terminalprinter.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.terminalprinter.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

public class FilledTableTest {
  @Test
  public void createFilled1x1Table() throws Exception {
    String empty1x1Table = getTestResourceAsString("table/filled/Filled1x1Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {1}
    }));

    assertEquals(empty1x1Table, printedTable);
  }

  @Test
  public void createFilled1x2Table() throws Exception {
    String filled1x1Table = getTestResourceAsString("table/filled/Filled1x2Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {1, 2}
    }));

    assertEquals(filled1x1Table, printedTable);
  }

  @Test
  public void createFilled2x1Table() throws Exception {
    String filled1x1Table = getTestResourceAsString("table/filled/Filled2x1Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {1},
        {2}
    }));

    assertEquals(filled1x1Table, printedTable);
  }

  @Test
  public void createFilled2x2Table() throws Exception {
    String filled1x1Table = getTestResourceAsString("table/filled/Filled2x2Table.txt");
    String printedTable = printTableAsString(new TablePrinter(new Object[][]{
        {1, 2},
        {3, 4}
    }));

    assertEquals(filled1x1Table, printedTable);
  }
}
