package com.verzano.terminalprinter.table.model;

public class DefaultTablePrinterModel implements TablePrinterModel {
  private Object[][] rows;
  private Object[] headers;
  private Object title;

  public DefaultTablePrinterModel() {
    this(null, null, null);
  }

  public DefaultTablePrinterModel(Object[][] rows, Object[] headers, Object title) {
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

  @Override
  public Object getTitle() {
    return title;
  }

  public void setTitle(Object title) {
    this.title = title;
  }

  @Override
  public Object dataAt(int row, int col) {
    return rows == null ? null : rows[row][col];
  }

  @Override
  public int dataWidthAt(int row, int col) {
    return dataAt(row, col) == null ? 0 : rows[row][col].toString().length();
  }

  @Override
  public Object headerAt(int col) {
    return headers == null ? null : headers[col];
  }

  @Override
  public int headerWidthAt(int col) {
    return headerAt(col) == null ? 0 : headers[col].toString().length();
  }

  @Override
  public int titleWidth() {
    return title == null ? 0 : title.toString().length();
  }

  @Override
  public int rowCount() {
    return rows == null ? 0 : rows.length;
  }

  @Override
  public int columnCount() {
    if (rowCount() == 0) {
      return headerCount();
    }
    return rows[0].length;
  }

  @Override
  public int headerCount() {
    return headers == null ? 0 : headers.length;
  }
}
