package com.verzano.monospaced.printer.table.model.ansi;

import com.verzano.monospaced.printer.table.model.TablePrinterModel;

// TODO fix the attributes
// TODO to make this work right may need to add a 'renderedDataAt' and 'renderedDataWidth'....
public class AnsiTablePrinterModel implements TablePrinterModel {

  private TablePrinterModel decoratedModel;
  private String[][] attributes;
  private String[][] foregrounds;
  private String[][] backgrounds;

  public AnsiTablePrinterModel(
      TablePrinterModel decoratedModel,
      String[][] attributes,
      String[][] foregrounds,
      String[][] backgrounds) {
    this.decoratedModel = decoratedModel;
    this.attributes = attributes;
    this.foregrounds = foregrounds;
    this.backgrounds = backgrounds;
  }

  @Override
  public Object dataAt(int row, int col) {
    return decoratedModel.dataAt(row, col);
  }

  @Override
  public int rowCount() {
    return decoratedModel.rowCount();
  }

  @Override
  public int columnCount() {
    return decoratedModel.columnCount();
  }

  @Override
  public int dataWidthAt(int row, int col) {
    return decoratedModel.dataWidthAt(row, col);
  }

  @Override
  public Object headerAt(int head) {
    return decoratedModel.headerAt(head);
  }

  @Override
  public int headerCount() {
    return decoratedModel.headerCount();
  }

  @Override
  public Object getTitle() {
    return decoratedModel.getTitle();
  }

  @Override
  public int headerWidthAt(int header) {
    return decoratedModel.headerWidthAt(header);
  }

  @Override
  public int titleWidth() {
    return decoratedModel.titleWidth();
  }
}
