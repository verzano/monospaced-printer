package com.verzano.terminalprinter.table;

import com.verzano.terminalprinter.table.metrics.Padding;
import com.verzano.terminalprinter.table.metrics.Size;
import com.verzano.terminalprinter.table.ui.GridUI;
import com.verzano.terminalprinter.table.ui.TableUI;

import java.io.PrintStream;
import java.util.Arrays;

// TODO add colors
// TODO add styling
// TODO do the resizing of the table more intelligently (allow title to influence and have sizes for cells)
// TODO break into model and view
// TODO add setters/getters and organize them
// TODO combine some of the similar drawing logic
public class TablePrinter {
  private PrintStream out;

  private TableUI tableUI;

  private Object title;
  private boolean showTitle;

  private Object[] headers;
  private int nHeaders;
  private Size[] minHeaderSizes;
  private Size[] maxHeaderSizes;
  private boolean showHeaders;

  private Object[][] rows;
  private int nCols;
  private int nRows;
  private Size[][] minSizes;
  private Size[][] maxSizes;

  private Padding pads;

  private Size renderedTitleSize;

  private Size[] renderedHeaderSizes;
  private int renderedHeaderHeight;

  private Size[][] renderedDataSizes;
  private int[] renderedRowHeights;
  private int[] renderedColWidths;

  private String[] chunkedTitle;
  private String[][] chunkedHeaders;
  private String[][][] chunkedData;

  public TablePrinter() {
    this(null, null, null, null, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows) {
    this(rows, null, null, null, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows, Object[] headers) {
    this(rows, headers, null, null, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows, Object[] headers, Object title) {
    this(rows, headers, title, null, null, null, null, null, null, null);
  }

  public TablePrinter(
      Object[][] rows,
      Object[] headers,
      Object title,
      Size[][] minSizes,
      Size[][] maxSizes,
      Padding pads) {
    this(rows, headers, title, minSizes, maxSizes, null, null, pads, null,  null);
  }

  public TablePrinter(
      Object[][] rows,
      Object[] headers,
      Object title,
      Size[][] minSizes,
      Size[][] maxSizes,
      Size[] minHeaderSizes,
      Size[] maxHeaderSizes,
      Padding pads,
      TableUI tableUI,
      PrintStream out) {
    this.tableUI = tableUI == null ? new TableUI() : tableUI;

    this.out = out == null ? System.out : out;

    this.rows = rows;
    nRows = rows == null ? 0 : rows.length;
    nCols = nRows == 0 ? 0 : rows[0].length;

    if (minSizes == null) {
      minSizes = new Size[nRows][nCols];
      for (int row = 0; row < nRows; row++) {
        for (int col = 0; col < nCols; col++) {
          minSizes[row][col] = new Size(0, 0);
        }
      }
    }
    this.minSizes = minSizes;

    if (maxSizes == null) {
      maxSizes = new Size[nRows][nCols];
      for (int row = 0; row < nRows; row++) {
        for (int col = 0; col < nCols; col++) {
          maxSizes[row][col] = new Size(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
      }
    }
    this.maxSizes = maxSizes;

    this.headers = headers;
    nHeaders = headers == null ? 0 : headers.length;

    if (nRows != 0 && nHeaders != 0 && nHeaders != nCols) {
      throw new IllegalArgumentException("Mismatched number of headers and columns:"
          + " " + nHeaders + " headers " + nCols + " columns ");
    }

    showHeaders = headers != null;

    if (minHeaderSizes == null) {
      minHeaderSizes = new Size[nHeaders];
      for (int header = 0; header < nHeaders; header++) {
        minHeaderSizes[header] = new Size(0, 0);
      }
    }
    this.minHeaderSizes = minHeaderSizes;

    if (maxHeaderSizes == null) {
      maxHeaderSizes = new Size[nHeaders];
      for (int header = 0; header < nHeaders; header++) {
        maxHeaderSizes[header] = new Size(Integer.MAX_VALUE, Integer.MAX_VALUE);
      }
    }
    this.maxHeaderSizes = maxHeaderSizes;
    
    this.title = title;
    showTitle = title != null;

    this.pads = pads == null ? new Padding(0, 0, 0, 0) : pads;

    calculateRenderedSizes();
  }

  public PrintStream getOut() {
    return out;
  }

  public void setOut(PrintStream out) {
    this.out = out;
  }

  private void calculateRenderedSizes() {
    renderedTitleSize = new Size();
    renderedHeaderSizes = new Size[nHeaders];
    renderedDataSizes = new Size[nRows][nCols];
    renderedRowHeights = new int[nRows];
    renderedColWidths = new int[nCols];

    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        int dataWidth = rows[row][col] == null ? 0 : rows[row][col].toString().length();

        int width = Math.max(
            minSizes[row][col].width,
            Math.min(maxSizes[row][col].width, dataWidth));

        int height = Math.max(
            minSizes[row][col].height,
            Math.min(maxSizes[row][col].height, (int)Math.ceil(dataWidth/(double)width)));

        renderedDataSizes[row][col] = new Size(width, height);
        renderedColWidths[col] = Math.max(renderedColWidths[col], width);
        renderedRowHeights[row] = Math.max(renderedRowHeights[row], height);
      }
    }

    if (showHeaders) {
      for (int header = 0; header < nHeaders; header++) {
        int headerWidth = headers[header] == null ? 0 : headers[header].toString().length();

        int width = Math.max(
            minHeaderSizes[header].width,
            Math.min(maxHeaderSizes[header].width, headerWidth));
        
        int height = Math.max(
            minHeaderSizes[header].height,
            Math.min(maxHeaderSizes[header].width, (int)Math.ceil(headerWidth/(double) width)));
        
        renderedHeaderSizes[header] = new Size(width, height);
        renderedColWidths[header] = Math.max(renderedColWidths[header], width);
        renderedHeaderHeight = Math.max(renderedHeaderHeight, height);
      }
    }

    if (showTitle) {
      renderedTitleSize.width = Arrays.stream(renderedColWidths).sum()
          + nCols - 1
          + pads.left * (nCols - 1)
          + pads.right * (nCols - 1);

      String titleString = title.toString();
      int titleLength = titleString.length();

      renderedTitleSize.height = (int)Math.ceil(titleLength/(double)renderedTitleSize.width);

      chunkedTitle = new String[renderedTitleSize.height];
      for (int chunk = 0; chunk < renderedTitleSize.height; chunk++) {
        int beginIndex = renderedTitleSize.width * chunk;
        chunkedTitle[chunk] = titleString.substring(
            beginIndex,
            Math.min(titleLength, renderedTitleSize.width * (chunk + 1)));
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

    for (int chunk = 0; chunk < renderedTitleSize.height; chunk++) {
      pr(titleUI.getLeftWall());
      pr(titleUI.getSpace(), pads.left);

      prf(chunkedTitle[chunk], renderedTitleSize.width);

      pr(titleUI.getSpace(), pads.right);
      pr(titleUI.getRightWall());

      ln();
    }

    printEmptyRows(titleUI, pads.bottom);
    printBottomDividerLine(titleUI);
  }

  private void printHeaders() {
    GridUI headerUI = tableUI.getHeaderUI();

    if (!showTitle) {
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

        prf(chunkedHeaders[col][chunk], renderedHeaderSizes[col].width);

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

        if (chunk >= renderedDataSizes[row][col].height) {
          prf(gridUI.getSpace(), renderedDataSizes[row][col].width);
        } else {
          prf(chunkedData[row][col][chunk], renderedDataSizes[row][col].width);
        }

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
        if (showTitle) {
          printTitle();
          if (showHeaders) {
            printHeaders();
          }
        } else if (showHeaders) {
          printHeaders();
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
