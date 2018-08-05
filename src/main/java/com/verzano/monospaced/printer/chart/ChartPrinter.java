package com.verzano.monospaced.printer.chart;

import com.verzano.monospaced.printer.TerminalPrinter;
import com.verzano.monospaced.printer.chart.model.ChartPrinterModel;
import com.verzano.monospaced.printer.chart.view.ChartPrinterView;
import java.io.PrintStream;

public class ChartPrinter extends TerminalPrinter {
  private ChartPrinterModel model;

  private ChartPrinterView view;

  public ChartPrinter(PrintStream printer) {
    this(null, null, printer);
  }

  public ChartPrinter(ChartPrinterModel model, ChartPrinterView view) {
    this(model, view, null);
  }

  public ChartPrinter(ChartPrinterModel model, ChartPrinterView view, PrintStream printer) {
    super(printer);
    this.model = model;
    this.view = view;
  }

  @Override
  public void print() {

  }
}
