package com.verzano.monospaced.printer.table;

import static com.verzano.monospaced.printer.table.view.Expansion.ALL;
import static com.verzano.monospaced.printer.table.view.Expansion.FIRST;
import static com.verzano.monospaced.printer.table.view.Expansion.LAST;
import static com.verzano.monospaced.printer.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.monospaced.printer.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

import com.verzano.monospaced.printer.table.model.DefaultTablePrinterModel;
import com.verzano.monospaced.printer.table.model.TablePrinterModel;
import com.verzano.monospaced.printer.table.view.TablePrinterView;
import org.junit.Test;

public class ColumnExpansionTest {
  @Test
  public void expandAll0x1WithHeader() throws Exception {
    String expandAll1x1OnlyHeader = getTestResourceAsString("table/expanded/ExpandAll0x1WithHeader.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        null,
        new Object[] {null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(ALL);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandAll1x1OnlyHeader, printedTable);
  }

  @Test
  public void expandAll1x1() throws Exception {
    String expandAll1x1 = getTestResourceAsString("table/expanded/ExpandAll1x1.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null}},
        null,
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(ALL);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandAll1x1, printedTable);
  }

  @Test
  public void expandAll1x1WithHeader() throws Exception {
    String expandAll1x1WithHeader = getTestResourceAsString("table/expanded/ExpandAll1x1WithHeader.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null}},
        new Object[] {null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(ALL);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandAll1x1WithHeader, printedTable);
  }

  @Test
  public void expandAll1x3() throws Exception {
    String expandAll1x3 = getTestResourceAsString("table/expanded/ExpandAll1x3.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null, null, null}},
        null,
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(ALL);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandAll1x3, printedTable);
  }

  @Test
  public void expandAll1x3WithHeaders() throws Exception {
    String expandAll1x3WithHeader = getTestResourceAsString("table/expanded/ExpandAll1x3WithHeaders.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null, null, null}},
        new Object[] {null, null, null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(ALL);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandAll1x3WithHeader, printedTable);
  }

  @Test
  public void expandFirst0x1WithHeader() throws Exception {
    String expandFirst1x1 = getTestResourceAsString("table/expanded/ExpandFirst0x1WithHeader.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        null,
        new Object[] {null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(FIRST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandFirst1x1, printedTable);
  }

  @Test
  public void expandFirst1x1() throws Exception {
    String expandFirst1x1 = getTestResourceAsString("table/expanded/ExpandFirst1x1.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null}},
        null,
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(FIRST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandFirst1x1, printedTable);
  }

  @Test
  public void expandFirst1x1WithHeader() throws Exception {
    String expandFirst1x1WithHeader = getTestResourceAsString("table/expanded/ExpandFirst1x1WithHeader.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null}},
        new Object[] {null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(FIRST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandFirst1x1WithHeader, printedTable);
  }

  @Test
  public void expandFirst1x3() throws Exception {
    String expandFirst1x3 = getTestResourceAsString("table/expanded/ExpandFirst1x3.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null, null, null}},
        null,
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(FIRST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandFirst1x3, printedTable);
  }

  @Test
  public void expandFirst1x3WithHeaders() throws Exception {
    String expandFirst1x3WithHeader = getTestResourceAsString("table/expanded/ExpandFirst1x3WithHeaders.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null, null, null}},
        new Object[] {null, null, null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(FIRST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandFirst1x3WithHeader, printedTable);
  }

  @Test
  public void expandLast0x1WithHeader() throws Exception {
    String expandLast1x1 = getTestResourceAsString("table/expanded/ExpandLast0x1WithHeader.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        null,
        new Object[] {null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(LAST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandLast1x1, printedTable);
  }

  @Test
  public void expandLast1x1() throws Exception {
    String expandLast1x1 = getTestResourceAsString("table/expanded/ExpandLast1x1.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null}},
        null,
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(LAST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandLast1x1, printedTable);
  }

  @Test
  public void expandLast1x1WithHeader() throws Exception {
    String expandLast1x1WithHeader = getTestResourceAsString("table/expanded/ExpandLast1x1WithHeader.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null}},
        new Object[] {null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(LAST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandLast1x1WithHeader, printedTable);
  }

  @Test
  public void expandLast1x3() throws Exception {
    String expandLast1x3 = getTestResourceAsString("table/expanded/ExpandLast1x3.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null, null, null}},
        null,
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(LAST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandLast1x3, printedTable);
  }
  
  @Test
  public void expandLast1x3WithHeaders() throws Exception {
    String expandLast1x3WithHeader = getTestResourceAsString("table/expanded/ExpandLast1x3WithHeaders.txt");

    TablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][] {{null, null, null}},
        new Object[] {null, null, null},
        "Title");
    TablePrinterView view = new TablePrinterView(model);
    view.setExpansion(LAST);
    String printedTable = printTableAsString(new TablePrinter(model, view, null));

    assertEquals(expandLast1x3WithHeader, printedTable);
  }
}
