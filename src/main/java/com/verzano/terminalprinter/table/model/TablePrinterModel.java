package com.verzano.terminalprinter.table.model;

// TODO add things like adding/removing columns
public interface TablePrinterModel {
  Object dataAt(int row, int col);

  int rowCount();

  int columnCount();

  int dataWidthAt(int row, int col);

  Object headerAt(int head);

  int headerCount();

  Object getTitle();

  int headerWidthAt(int header);

  int titleWidth();
}
