package com.verzano.terminalprinter.table;

import com.verzano.terminalprinter.table.metrics.Size;
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
            new Size[][]{
                {new Size(0, 0), new Size(0, 0)},
                {new Size(0, 0), new Size(0, 0)}
            },
            new Size[][]{
                {new Size(1, 1), new Size(1, 1)},
                {new Size(1, 1), new Size(1, 1)}
            },
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
            new Size[][]{
                {new Size(2, 2), new Size(2, 2)},
                {new Size(2, 2), new Size(2, 2)}
            },
            new Size[][]{
                {new Size(2, 2), new Size(2, 2)},
                {new Size(2, 2), new Size(2, 2)}
            },
            null));

    assertEquals(table2x2Min2x2Max2x2, printedTable);
  }

  @Test
  public void createTable2x2Min2x1Max2x1Wrapped() throws Exception {
    String table2x2Min2x1Max2x1Wrapped = getTestResourceAsString("table/minmax/Table2x2Min2x1Max2x1Wrapped.txt");
    String printedTable = printTableAsString(
        new TablePrinter(
            new Object[][]{
                {111, 2},
                {3, 4}
            },
            null,
            null,
            new Size[][]{
                {new Size(2, 1), new Size(1, 1)},
                {new Size(2, 1), new Size(1, 1)}
            },
            new Size[][]{
                {new Size(2, 2), new Size(1, 1)},
                {new Size(2, 2), new Size(1, 1)}
            },
            null));

    assertEquals(table2x2Min2x1Max2x1Wrapped, printedTable);
  }
}
