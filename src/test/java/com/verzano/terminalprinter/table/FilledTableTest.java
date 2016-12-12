package com.verzano.terminalprinter.table;

import com.verzano.terminalprinter.table.model.TablePrinterModel;
import org.junit.Test;

import static com.verzano.terminalprinter.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.terminalprinter.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

public class FilledTableTest {
  @Test
  public void createTable1x1Filled() throws Exception {
    String table1x1Filled = getTestResourceAsString("table/filled/Table1x1Filled.txt");

    TablePrinterModel model = new TablePrinterModel(
        new Object[][]{
            {1}
        },
        null,
        null);
    String printedTable = printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table1x1Filled, printedTable);
  }

  @Test
  public void createTable1x2Filled() throws Exception {
    String table1x2Filled = getTestResourceAsString("table/filled/Table1x2Filled.txt");

    TablePrinterModel model = new TablePrinterModel(
        new Object[][]{
            {1, 2}
        },
        null,
        null);
    String printedTable = printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table1x2Filled, printedTable);
  }

  @Test
  public void createTable2x1Filled() throws Exception {
    String table2x1Filled = getTestResourceAsString("table/filled/Table2x1Filled.txt");

    TablePrinterModel model = new TablePrinterModel(
        new Object[][]{
            {1},
            {2}
        },
        null,
        null);
    String printedTable = printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table2x1Filled, printedTable);
  }

  @Test
  public void createTable2x2Filled() throws Exception {
    String table2x2Filled = getTestResourceAsString("table/filled/Table2x2Filled.txt");

    TablePrinterModel model = new TablePrinterModel(
        new Object[][]{
            {1, 2},
            {3, 4}
        },
        null,
        null);
    String printedTable = printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table2x2Filled, printedTable);
  }
}
