package com.verzano.terminalprinter.table;

import com.verzano.terminalprinter.table.metrics.Padding;
import com.verzano.terminalprinter.table.ui.GridUI;
import com.verzano.terminalprinter.table.ui.TableUI;

import java.io.PrintStream;
import java.util.Arrays;

// TODO add colors
// TODO add styling
// TODO enforce that nHeaders and nCols are the same
// TODO do the resizing of the table more intelligently (allow title to influence and have sizes for cells)
// TODO break into model and view
// TODO add setters/getters and organize them
// TODO combine some of the similar drawing logic
public class TablePrinter {
  private PrintStream out;

  // TODO move this to the model
  private TableUI tableUI;

  private Object title;

  private Object[] headers;
  private int nHeaders;

  private Object[][] rows;
  private int nCols;
  private int nRows;

  private int[] minWidths;
  private int[] maxWidths;

  private Padding pads;

  private int renderedTitleWidth;
  private int renderedTitleHeight;
  private int renderedHeaderHeight;
  private int[] renderedColWidths;
  private String[] chunkedTitle;

  private int[] renderedRowHeights;
  private String[][] chunkedHeaders;
  private String[][][] chunkedData;

  public TablePrinter() {
    this(null, null, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows) {
    this(rows, null, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows, Object[] headers) {
    this(rows, headers, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows, Object[] headers, Object title) {
    this(rows, headers, title, null, null, null, null, null);
  }

  public TablePrinter(
      Object[][] rows,
      Object[] headers,
      Object title,
      int[] minWidths,
      int[] maxWidths,
      Padding pads) {
    this(rows, headers, title, minWidths, maxWidths, pads, null,  null);
  }

  public TablePrinter(
      Object[][] rows,
      Object[] headers,
      Object title,
      int[] minWidths,
      int[] maxWidths,
      Padding pads,
      TableUI tableUI,
      PrintStream out) {
    // TODO allow the actual setting of this
    this.tableUI = tableUI == null ? new TableUI() : tableUI;

    this.out = out == null ? System.out : out;

    this.rows = rows;
    nRows = rows == null ? 0 : rows.length;
    nCols = nRows == 0 ? 0 : rows[0].length;

    this.headers = headers;
    nHeaders = headers == null ? 0 : headers.length;

    if (nRows != 0 && nHeaders != 0 && nHeaders != nCols) {
      throw new IllegalArgumentException("Mismatched number of headers and columns:"
          + " " + nHeaders + " headers " + nCols + " columns ");
    }
    
    this.title = title;

    this.minWidths = minWidths == null ? new int[nCols] : minWidths;

    if (maxWidths == null) {
      maxWidths = new int[nCols];
      Arrays.fill(maxWidths, Integer.MAX_VALUE);
    }
    this.maxWidths = maxWidths;

    this.pads = pads == null ? new Padding(0, 0) : pads;

    calculateRenderedSizes();
  }

  public void setOut(PrintStream out) {
    this.out = out;
  }

  public void setHeaders(Object[] headers) {
    this.headers = headers;
    nHeaders = headers == null ? 0 : headers.length;
    calculateRenderedSizes();
  }

  public void setRows(Object[][] rows) {
    this.rows = rows;
    nRows = rows == null ? 0 : rows.length;
    nCols = rows == null ? 0 : rows[0].length;

    if (minWidths.length < nCols) {
      int[] newMinWidths = new int[nCols];
      System.arraycopy(minWidths, 0, newMinWidths, 0, minWidths.length);
      minWidths = newMinWidths;
    } else if (minWidths.length > nCols) {
      minWidths = Arrays.copyOfRange(minWidths, 0, nCols);
    }

    if (maxWidths.length < nCols) {
      int[] newMaxWidths = new int[nCols];
      Arrays.fill(newMaxWidths, Integer.MAX_VALUE);
      System.arraycopy(maxWidths, 0, newMaxWidths, 0, maxWidths.length);
      maxWidths = newMaxWidths;
    } else if (maxWidths.length > nCols) {
      maxWidths = Arrays.copyOfRange(maxWidths, 0, nCols);
    }

    calculateRenderedSizes();
  }

  public void setMinWidths(int[] minWidths) {
    this.minWidths = minWidths == null ? new int[nCols] : minWidths;
    calculateRenderedSizes();
  }

  public void setMaxWidths(int[] maxWidths) {
    this.maxWidths = maxWidths;

    if (maxWidths == null) {
      maxWidths = new int[nCols];
      Arrays.fill(maxWidths, Integer.MAX_VALUE);
    }
    this.maxWidths = maxWidths;
    calculateRenderedSizes();
  }

  public void setPads(Padding pads) {
    this.pads = pads == null ? new Padding(0, 0) : pads;
    calculateRenderedSizes();
  }
  
  public void setTitle(Object title) {
    this.title = title;
    calculateRenderedSizes();
  }

  private void calculateRenderedSizes() {
    renderedTitleWidth = 0;
    renderedTitleHeight = 0;
    renderedColWidths = new int[nCols];
    renderedRowHeights = new int[nRows];

    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        int dataWidth = rows[row][col] == null ? 0 : rows[row][col].toString().length();

        int width = Math.max(
            minWidths[col],
            Math.min(maxWidths[col], dataWidth));
        renderedColWidths[col] = Math.max(renderedColWidths[col], width);

        renderedRowHeights[row] = Math.max(
            renderedRowHeights[row],
            (int)Math.ceil(dataWidth/(double)width));
      }
    }

    renderedHeaderHeight = 0;
    for (int head = 0; head < nHeaders; head++) {
      int headerWidth = headers[head] == null ? 0 : headers[head].toString().length();

      int width = Math.max(
          minWidths[head],
          Math.min(maxWidths[head], headerWidth));
      renderedColWidths[head] = Math.max(renderedColWidths[head], width);

      renderedHeaderHeight = Math.max(
          renderedHeaderHeight,
          (int)Math.ceil(headerWidth/(double)width));
    }

    if (title != null) {
      renderedTitleWidth = Arrays.stream(renderedColWidths).sum()
          + nCols - 1
          + pads.left * (nCols - 1)
          + pads.right * (nCols - 1);

      String titleString = title.toString();
      int titleLength = titleString.length();

      renderedTitleHeight = (int)Math.ceil(titleLength/(double)renderedTitleWidth);

      chunkedTitle = new String[renderedTitleHeight];
      for (int chunk = 0; chunk < renderedTitleHeight; chunk++) {
        int beginIndex = renderedTitleWidth * chunk;
        chunkedTitle[chunk] = titleString.substring(
            beginIndex,
            Math.min(titleLength, renderedTitleWidth * (chunk + 1)));
      }
    }

    chunkedData = new String[nRows][nCols][];
    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        chunkedData[row][col] = new String[renderedRowHeights[row]];

        for (int chunk = 0; chunk < renderedRowHeights[row]; chunk++) {
          String data = rows[row][col].toString();
          int dataLength = data.length();
          int beginIndex = renderedColWidths[col] * chunk;

          if (beginIndex < dataLength) {
            chunkedData[row][col][chunk] = data.substring(
                beginIndex,
                Math.min(dataLength, renderedColWidths[col] * (chunk + 1)));
          } else {
            chunkedData[row][col][chunk] = tableUI.getCellUI().getSpace() + "";
          }
        }
      }
    }

    chunkedHeaders = new String[nCols][];
    for (int head = 0; head < nHeaders; head++) {
      chunkedHeaders[head] = new String[renderedHeaderHeight];

      for (int chunk = 0; chunk < renderedHeaderHeight; chunk++) {
        String header = headers[head].toString();
        int headerLength = header.length();
        int beginIndex = renderedColWidths[head] * chunk;

        if (beginIndex < headerLength) {
          chunkedHeaders[head][chunk] = header.substring(
              beginIndex,
              Math.min(headerLength, renderedColWidths[head] * (chunk + 1)));
        } else {
          chunkedHeaders[head][chunk] = tableUI.getHeaderUI().getSpace() + "";
        }
      }
    }
  }

  private void printTopDividerLine(GridUI gridUI) {
    printDividerLine(
        gridUI.getTopLeft(),
        gridUI.getTopFloor(),
        gridUI.getTopCross(),
        gridUI.getTopRight());
  }

  private void printInnerDividerLine(GridUI gridUI) {
    printDividerLine(
        gridUI.getLeftCross(),
        gridUI.getInnerFloor(),
        gridUI.getInnerCross(),
        gridUI.getRightCross());
  }

  private void printBottomDividerLine(GridUI gridUI) {
    printDividerLine(
        gridUI.getBotLeft(),
        gridUI.getBotFloor(),
        gridUI.getBotCross(),
        gridUI.getBotRight());
  }

  private void printEmptyRows(GridUI gridUI, int numRows) {
    for(int row = 0; row < numRows; row++) {
      printDividerLine(
          gridUI.getLeftWall(),
          gridUI.getSpace(),
          gridUI.getInnerWall(),
          gridUI.getRightWall());
    }
  }

  private void printDividerLine(char left, char middle, char cross, char right) {
    if (nCols > 0) {
      int rightCol = nCols - 1;
      pr(left);

      for (int col = 0; col < rightCol; col++) {
        pr(middle, pads.left + renderedColWidths[col] + pads.right);
        pr(cross);
      }

      pr(middle, pads.left + renderedColWidths[rightCol] + pads.right);
      pr(right);
      ln();
    }
  }
  
  private void printTitle() {
    GridUI titleUI = tableUI.getTitleUI();

    printTopDividerLine(titleUI);
    printEmptyRows(titleUI, pads.bottom);

    for (int chunk = 0; chunk < renderedTitleHeight; chunk++) {
      pr(titleUI.getLeftWall());
      pr(titleUI.getSpace(), pads.left);

      prf(chunkedTitle[chunk], renderedTitleWidth);

      pr(titleUI.getSpace(), pads.right);
      pr(titleUI.getRightWall());

      ln();
    }

    printEmptyRows(titleUI, pads.bottom);
    printBottomDividerLine(titleUI);
  }

  private void printHeaders(boolean printTop) {
    GridUI headerUI = tableUI.getHeaderUI();

    if (printTop) {
      printTopDividerLine(headerUI);
    }
    printEmptyRows(headerUI, pads.bottom);
    int rightCol = nCols - 1;
    for (int chunk = 0; chunk < renderedHeaderHeight; chunk++) {
      for (int col = 0; col < nCols; col++) {
        if (col == 0) {
          pr(headerUI.getLeftWall());
        }
        pr(headerUI.getSpace(), pads.left);

        prf(chunkedHeaders[col][chunk], renderedColWidths[col]);

        pr(headerUI.getSpace(), pads.right);
        if (col == rightCol) {
          pr(headerUI.getRightWall());
        } else {
          pr(headerUI.getInnerWall());
        }
      }
      ln();
    }
    printEmptyRows(headerUI, pads.bottom);
    printBottomDividerLine(headerUI);
  }

  private void printRow(GridUI gridUI, int row) {
    int rightCol = nCols - 1;

    for (int chunk = 0; chunk < renderedRowHeights[row]; chunk++) {
      for (int col = 0; col < nCols; col++) {
        if (col == 0) {
          pr(gridUI.getLeftWall());
        }
        pr(gridUI.getSpace(), pads.left);

        prf(chunkedData[row][col][chunk], renderedColWidths[col]);

        pr(gridUI.getSpace(), pads.right);
        if (col == rightCol) {
          pr(gridUI.getRightWall());
        } else {
          pr(gridUI.getInnerWall());
        }
      }
      ln();
    }
  }

  public void print() {
    GridUI cellUI = tableUI.getCellUI();
    int bottomRow = nRows - 1;
    for (int row = 0; row < nRows; row++) {
      if (row == 0) {
        if (title != null) {
          printTitle();
          if (headers != null) {
            printHeaders(false);
          }
        } else if (headers != null) {
          printHeaders(true);
        } else {
          printTopDividerLine(cellUI);
        }
      } else {
        printInnerDividerLine(cellUI);
      }
      printEmptyRows(cellUI, pads.top);
      printRow(cellUI, row);
      printEmptyRows(cellUI, pads.bottom);
      if(row == bottomRow) {
        printBottomDividerLine(cellUI);
      }
    }
  }

  private void pr(Object c) {
    out.print(c);
  }

  private void pr(Object c, int reps) {
    for (int i = 0; i < reps; i++) {
      pr(c);
    }
  }

  private void prf(Object c, int width) {
    out.printf("%" + width + "s", c);
  }

  private void ln() {
    out.println();
  }
}
