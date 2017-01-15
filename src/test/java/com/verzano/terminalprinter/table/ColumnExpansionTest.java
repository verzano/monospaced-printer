package com.verzano.terminalprinter.table;

import com.verzano.terminalprinter.table.model.DefaultTablePrinterModel;
import com.verzano.terminalprinter.table.model.TablePrinterModel;
import com.verzano.terminalprinter.table.view.TablePrinterView;
import org.junit.Test;

import static com.verzano.terminalprinter.table.view.Expansion.ALL;
import static com.verzano.terminalprinter.table.view.Expansion.FIRST;
import static com.verzano.terminalprinter.table.view.Expansion.LAST;
import static com.verzano.terminalprinter.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.terminalprinter.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

public class ColumnExpansionTest {
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
}
