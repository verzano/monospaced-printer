package com.verzano.monospaced.printer;

import java.io.PrintStream;

public abstract class Printer {
  private PrintStream printer;

  public PrintStream getPrinter() {
    return printer;
  }

  public void setPrinter(PrintStream printer) {
    this.printer = printer;
  }

  public abstract void print();

  public Printer(PrintStream printer) {
    this.printer = printer == null ? System.out : printer;
  }
  
  public void pr(Object c) {
    printer.print(c);
  }

  public void pr(Object c, int reps) {
    for (int i = 0; i < reps; i++) {
      pr(c);
    }
  }

  public void prf(Object c, int width) {
    printer.printf("%" + width + "s", c);
  }

  public void ln() {
    printer.println();
  }
}
