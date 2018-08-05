package com.verzano.monospaced.printer.table;

import static org.junit.Assert.assertEquals;

import com.verzano.monospaced.printer.metrics.Size;
import com.verzano.monospaced.printer.table.model.DefaultTablePrinterModel;
import com.verzano.monospaced.printer.table.view.TablePrinterView;
import com.verzano.monospaced.printer.test.helper.TestHelper;
import org.junit.Test;

public class MinMaxTableTest {
  @Test
  public void createTable2x2Min0x0Max1x1() throws Exception {
    String table2x2Min0x0Max1x1 = TestHelper
        .getTestResourceAsString("table/minmax/Table2x2Min0x0Max1x1.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1, 2},
            {3, 4}
        },
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        new Size[][]{
            {new Size(0, 0), new Size(0, 0)},
            {new Size(0, 0), new Size(0, 0)}
        },
        new Size[][]{
            {new Size(1, 1), new Size(1, 1)},
            {new Size(1, 1), new Size(1, 1)}
        },
        null,
        null,
        false,
        false,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table2x2Min0x0Max1x1, printedTable);
  }

  @Test
  public void createTable2x2Min2x2Max2x2() throws Exception {
    String table2x2Min2x2Max2x2 = TestHelper
        .getTestResourceAsString("table/minmax/Table2x2Min2x2Max2x2.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {1, 2},
            {3, 4}
        },
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        new Size[][]{
            {new Size(2, 2), new Size(2, 2)},
            {new Size(2, 2), new Size(2, 2)}
        },
        new Size[][]{
            {new Size(2, 2), new Size(2, 2)},
            {new Size(2, 2), new Size(2, 2)}
        },
        null,
        null,
        false,
        false,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table2x2Min2x2Max2x2, printedTable);
  }

  @Test
  public void createTable2x2Min2x1Max2x1Wrapped() throws Exception {
    String table2x2Min2x1Max2x1Wrapped = TestHelper
        .getTestResourceAsString("table/minmax/Table2x2Min2x1Max2x1Wrapped.txt");

    DefaultTablePrinterModel model = new DefaultTablePrinterModel(
        new Object[][]{
            {111, 2},
            {3, 4}
        },
        null,
        null);
    TablePrinterView view = new TablePrinterView(
        model,
        null,
        new Size[][]{
            {new Size(2, 1), new Size(1, 1)},
            {new Size(2, 1), new Size(1, 1)}
        },
        new Size[][]{
            {new Size(2, 2), new Size(1, 1)},
            {new Size(2, 2), new Size(1, 1)}
        },
        null,
        null,
        false,
        false,
        null);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, view, null));

    assertEquals(table2x2Min2x1Max2x1Wrapped, printedTable);
  }
}
