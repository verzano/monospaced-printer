package com.verzano.monospaced.printer.table;

import static org.junit.Assert.assertEquals;

import com.verzano.monospaced.printer.metrics.Padding;
import com.verzano.monospaced.printer.table.model.DefaultTablePrinterModel;
import com.verzano.monospaced.printer.table.view.TablePrinterView;
import com.verzano.monospaced.printer.test.helper.TestHelper;
import org.junit.Test;

public class PaddingTableTest {
  @Test
  public void createTable1x1Pad0x0x0x0() throws Exception {
    String table1x1Pad0x0x0x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x0x0x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 0, 0, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x0x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x0x0x1() throws Exception {
    String table1x1Pad0x0x0x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x0x0x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 0, 0, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x0x0x1, printedTable);
  }

  @Test
  public void createTable1x1Pad0x0x1x0() throws Exception {
    String table1x1Pad0x0x1x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x0x1x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 0, 1, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x0x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x0x1x1() throws Exception {
    String table1x1Pad0x0x1x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x0x1x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 0, 1, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x0x1x1, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x0x0() throws Exception {
    String table1x1Pad0x1x0x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x1x0x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 1, 0, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x1x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x0x1() throws Exception {
    String table1x1Pad0x1x0x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x1x0x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 1, 0, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x1x0x1, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x1x0() throws Exception {
    String table1x1Pad0x1x1x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x1x1x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 1, 1, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x1x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x1x1() throws Exception {
    String table1x1Pad0x1x1x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad0x1x1x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(0, 1, 1, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad0x1x1x1, printedTable);
  }

  @Test
  public void createTable1x1Pad1x0x0x0() throws Exception {
    String table1x1Pad1x0x0x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x0x0x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 0, 0, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x0x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x0x0x1() throws Exception {
    String table1x1Pad1x0x0x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x0x0x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 0, 0, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x0x0x1, printedTable);
  }
  @Test
  public void createTable1x1Pad1x0x1x0() throws Exception {
    String table1x1Pad1x0x1x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x0x1x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 0, 1, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x0x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x0x1x1() throws Exception {
    String table1x1Pad1x0x1x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x0x1x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 0, 1, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x0x1x1, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x0x0() throws Exception {
    String table1x1Pad1x1x0x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x1x0x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 1, 0, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x1x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x0x1() throws Exception {
    String table1x1Pad1x1x0x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x1x0x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 1, 0, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x1x0x1, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x1x0() throws Exception {
    String table1x1Pad1x1x1x0 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x1x1x0.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 1, 1, 0));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x1x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x1x1() throws Exception {
    String table1x1Pad1x1x1x1 = TestHelper
        .getTestResourceAsString("table/padding/Table1x1Pad1x1x1x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{{1}},
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        null,
        null,
        null,
        null,
        false,
        false,
        new Padding(1, 1, 1, 1));
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table1x1Pad1x1x1x1, printedTable);
  }
}
