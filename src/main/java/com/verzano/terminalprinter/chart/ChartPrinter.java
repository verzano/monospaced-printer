package com.verzano.terminalprinter.chart;

import com.verzano.terminalprinter.TerminalPrinter;
import com.verzano.terminalprinter.chart.model.ChartPrinterModel;
import com.verzano.terminalprinter.chart.view.ChartPrinterView;

import java.io.PrintStream;

public class ChartPrinter extends TerminalPrinter {
  private ChartPrinterModel model;

  private ChartPrinterView view;

  public ChartPrinter(PrintStream printer) {
    super(printer);
  }

  @Override
  public void print() {

  }
}
