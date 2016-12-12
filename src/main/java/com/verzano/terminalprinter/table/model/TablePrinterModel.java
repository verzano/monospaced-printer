package com.verzano.terminalprinter.table.model;

public class TablePrinterModel {
  private Object[][] rows;
  private Object[] headers;
  private Object title;

  public TablePrinterModel() {
    this(null, null, null);
  }

  public TablePrinterModel(Object[][] rows, Object[] headers, Object title) {
    this.rows = rows;
    int rowCount = rows == null ? 0 : rows.length;
    int columnCount = rowCount == 0 ? 0 : rows[0].length;

    this.headers = headers;
    int headerCount = headers == null ? 0 : headers.length;

    if (rowCount != 0 && headerCount != 0 && headerCount != columnCount) {
      throw new IllegalArgumentException("Mismatched number of headers and columns:"
          + " " + headerCount + " headers " + columnCount + " columns ");
    }

    this.title = title;
  }

  public Object[][] getRows() {
    return rows;
  }

  public void setRows(Object[][] rows) {
    this.rows = rows;
  }

  public Object[] getHeaders() {
    return headers;
  }

  public void setHeaders(Object[] headers) {
    this.headers = headers;
  }

  public Object getTitle() {
    return title;
  }

  public void setTitle(Object title) {
    this.title = title;
  }

  public Object dataAt(int row, int col) {
    return rows == null ? null : rows[row][col];
  }

  public int dataWidthAt(int row, int col) {
    return dataAt(row, col) == null ? 0 : rows[row][col].toString().length();
  }

  public Object headerAt(int col) {
    return headers == null ? null : headers[col];
  }

  public int headerWidthAt(int col) {
    return headerAt(col) == null ? 0 : headers[col].toString().length();
  }

  public int titleWidth() {
    return title == null ? 0 : title.toString().length();
  }

  public int rowCount() {
    return rows == null ? 0 : rows.length;
  }

  public int columnCount() {
    return rowCount() == 0 ? 0 : rows[0].length;
  }

  public int headerCount() {
    return headers == null ? 0 : headers.length;
  }
}
