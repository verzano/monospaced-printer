package com.verzano.terminalprinter.table;

import org.junit.Test;

import static com.verzano.terminalprinter.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.terminalprinter.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

public class MinMaxTableTest {
  @Test
  public void createTable2x2Min0x0Max1x1() throws Exception {
    String table2x2Min0x0Max1x1 = getTestResourceAsString("table/minmax/Table2x2Min0x0Max1x1.txt");
    String printedTable = printTableAsString(
        new TablePrinter(
            new Object[][]{
                {1, 2},
                {3, 4}
            },
            null,
            null,
            new int[]{0, 0},
            new int[]{1, 1},
            null));

    assertEquals(table2x2Min0x0Max1x1, printedTable);
  }

  @Test
  public void createTable2x2Min2x2Max2x2() throws Exception {
    String table2x2Min2x2Max2x2 = getTestResourceAsString("table/minmax/Table2x2Min2x2Max2x2.txt");
    String printedTable = printTableAsString(
        new TablePrinter(
            new Object[][]{
                {1, 2},
                {3, 4}
            },
            null,
            null,
            new int[]{2, 2},
            new int[]{2, 2},
            null));

    assertEquals(table2x2Min2x2Max2x2, printedTable);
  }
}
