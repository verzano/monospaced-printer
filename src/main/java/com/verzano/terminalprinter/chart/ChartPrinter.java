package com.verzano.terminalprinter.chart;

import com.verzano.terminalprinter.TerminalPrinter;
import com.verzano.terminalprinter.chart.model.ChartPrinterModel;
import com.verzano.terminalprinter.chart.view.ChartPrinterView;

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
