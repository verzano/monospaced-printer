package com.verzano.monospaced.printer.table;

import static org.junit.Assert.assertEquals;

import com.verzano.monospaced.printer.table.model.DefaultTablePrinterModel;
import com.verzano.monospaced.printer.table.view.TablePrinterView;
import com.verzano.monospaced.printer.test.helper.TestHelper;
import org.junit.Test;

public class FilledTableTest {
  @Test
  public void createTable1x1Filled() throws Exception {
    String table1x1Filled = TestHelper.getTestResourceAsString("table/filled/Table1x1Filled.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1}
        },
        null,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table1x1Filled, printedTable);
  }

  @Test
  public void createTable1x1FilledWithHeaders() throws Exception {
    String table1x1FilledWithHeaders = TestHelper
        .getTestResourceAsString("table/filled/Table1x1FilledWithHeaders.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1}
        },
        new Object[]{"C1"},
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        true,
        false,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1FilledWithHeaders, printedTable);
  }

  @Test
  public void createTable1x1FilledWithHeadersAndTitle() throws Exception {
    String table1x1FilledWithHeadersAndTitle = TestHelper
        .getTestResourceAsString("table/filled/Table1x1FilledWithHeadersAndTitle.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1}
        },
        new Object[]{"C1"},
        "Title");
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        true,
        true,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1FilledWithHeadersAndTitle, printedTable);
  }

  @Test
  public void createTable1x1FilledWithTitle() throws Exception {
    String table1x1FilledWithTitle = TestHelper
        .getTestResourceAsString("table/filled/Table1x1FilledWithTitle.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1}
        },
        new Object[]{},
        "Title");
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        true,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1FilledWithTitle, printedTable);
  }

  @Test
  public void createTable1x2Filled() throws Exception {
    String table1x2Filled = TestHelper.getTestResourceAsString("table/filled/Table1x2Filled.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1, 2}
        },
        null,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table1x2Filled, printedTable);
  }

  @Test
  public void createTable1x2EmptyWithHeaders() throws Exception {
    String table1x2FilledWithHeaders = TestHelper
        .getTestResourceAsString("table/filled/Table1x2FilledWithHeaders.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1, 2}
        },
        new Object[]{"C1", "C2"},
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        true,
        false,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x2FilledWithHeaders, printedTable);
  }

  @Test
  public void createTable1x2EmptyWithHeadersAndTitle() throws Exception {
    String table1x2FilledWithHeadersAndTitle = TestHelper
        .getTestResourceAsString("table/filled/Table1x2FilledWithHeadersAndTitle.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1, 2}
        },
        new Object[]{"C1", "C2"},
        "Title");
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        true,
        true,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x2FilledWithHeadersAndTitle, printedTable);
  }

  @Test
  public void createTable1x2EmptyWithTitle() throws Exception {
    String table1x2FilledWithHeaders = TestHelper
        .getTestResourceAsString("table/filled/Table1x2FilledWithTitle.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1, 2}
        },
        new Object[]{},
        "Title");
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        true,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x2FilledWithHeaders, printedTable);
  }

  @Test
  public void createTable2x1Filled() throws Exception {
    String table2x1Filled = TestHelper.getTestResourceAsString("table/filled/Table2x1Filled.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1},
            {2}
        },
        null,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table2x1Filled, printedTable);
  }

  @Test
  public void createTable2x2Filled() throws Exception {
    String table2x2Filled = TestHelper.getTestResourceAsString("table/filled/Table2x2Filled.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1, 2},
            {3, 4}
        },
        null,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, null, null));

    assertEquals(table2x2Filled, printedTable);
  }
}
