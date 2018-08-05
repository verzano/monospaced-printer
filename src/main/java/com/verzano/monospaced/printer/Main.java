package com.verzano.monospaced.printer;

import com.verzano.monospaced.printer.table.TablePrinter;
import com.verzano.monospaced.printer.table.model.DefaultTablePrinterModel;
import com.verzano.monospaced.printer.table.model.TablePrinterModel;
import com.verzano.monospaced.printer.table.view.TablePrinterView;

public class Main {

  public static void main(String[] args) {
    Object[][] data = new Object[10][5];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 5; j++) {
        data[i][j] = 0;
      }
    }

    TablePrinterModel model = new DefaultTablePrinterModel(data, null, "1234567890123456789");
    new TablePrinter(model, new TablePrinterView(model), null).print();
  }
}
