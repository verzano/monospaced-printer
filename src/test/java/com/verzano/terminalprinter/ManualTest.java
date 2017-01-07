package com.verzano.terminalprinter;

import com.verzano.terminalprinter.table.TablePrinter;
import com.verzano.terminalprinter.table.model.DefaultTablePrinterModel;
import com.verzano.terminalprinter.table.model.TablePrinterModel;
import com.verzano.terminalprinter.table.model.ansi.AnsiTablePrinterModel;

public class ManualTest {
  public static void main(String[] args) {
    String[][] atbrs = new String[][] {
        {null, null},
        {null, null}
    };
    String[][] fores = new String[][]{
        {null, null},
        {null, null}
    };
    String[][] backs = new String[][] {
        {null, null},
        {null, null}
    };
    Object[][] rows = new String[][] {
        {"ABC", "123"},
        {"456", "DEF"}
    };
    TablePrinterModel model = new AnsiTablePrinterModel(
        new DefaultTablePrinterModel(rows, null, null),
        atbrs,
        fores,
        backs);
    TablePrinter printer = new TablePrinter(model, null, null);

    printer.print();
  }
}
