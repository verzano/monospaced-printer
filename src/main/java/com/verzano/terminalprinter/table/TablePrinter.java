package com.verzano.terminalprinter.table;

import com.verzano.terminalprinter.table.metrics.Size;
import com.verzano.terminalprinter.table.model.TablePrinterModel;
import com.verzano.terminalprinter.table.view.TablePrinterView;
import com.verzano.terminalprinter.table.view.ui.GridUI;

import java.io.PrintStream;
import java.util.Arrays;

// TODO add colors
// TODO add styling
// TODO add setters/getters and organize them
// TODO combine some of the similar drawing logic
public class TablePrinter {
  private PrintStream out;

  private TablePrinterModel model;

  private TablePrinterView view;

  private Size renderedTitleSize;

  private Size[] renderedHeaderSizes;
  private int renderedHeaderHeight;

  private Size[][] renderedDataSizes;
  private int[] renderedRowHeights;
  private int[] renderedColWidths;

  private String[] chunkedTitle;
  private String[][] chunkedHeaders;
  private String[][][] chunkedData;

  public TablePrinter(
      TablePrinterModel model,
      TablePrinterView view,
      PrintStream out) {
    this.model = model == null ? new TablePrinterModel() : model;
    this.view = view == null ? new TablePrinterView(this.model) : view;
    this.out = out == null ? System.out : out;

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
    
    int nHeaders = model.headerCount();
    renderedHeaderSizes = new Size[nHeaders];
    
    int nRows = model.rowCount();
    int nCols = model.columnCount();
    renderedDataSizes = new Size[nRows][nCols];
    renderedRowHeights = new int[nRows];
    renderedColWidths = new int[nCols];

    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        int dataWidth = model.dataWidthAt(row, col);

        int width = Math.max(
            view.minWidthAt(row, col),
            Math.min(view.maxWidthAt(row, col), dataWidth));

        int height = Math.max(
            view.minHeightAt(row, col),
            Math.min(view.maxHeightAt(row, col), (int)Math.ceil(dataWidth/(double)width)));

        renderedDataSizes[row][col] = new Size(width, height);
        renderedColWidths[col] = Math.max(renderedColWidths[col], width);
        renderedRowHeights[row] = Math.max(renderedRowHeights[row], height);
      }
    }

    if (view.isShowHeaders()) {
      for (int header = 0; header < nHeaders; header++) {
        int headerWidth = model.headerWidthAt(header);

        int width = Math.max(
            view.minHeaderWidthAt(header),
            Math.min(view.maxHeaderWidthAt(header), headerWidth));
        
        int height = Math.max(
            view.minHeaderHeightAt(header),
            Math.min(view.maxHeaderHeightAt(header), (int)Math.ceil(headerWidth/(double) width)));
        
        renderedHeaderSizes[header] = new Size(width, height);
        renderedColWidths[header] = Math.max(renderedColWidths[header], width);
        renderedHeaderHeight = Math.max(renderedHeaderHeight, height);
      }
    }

    if (view.isShowTitle()) {
      int titleWidth = model.titleWidth();
      renderedTitleSize.width = Math.max(
          titleWidth,
          Arrays.stream(renderedColWidths).sum()
              + nCols - 1
              + view.getPads().left * (nCols - 1)
              + view.getPads().right * (nCols - 1));

      renderedTitleSize.height = (int)Math.ceil(titleWidth/(double)renderedTitleSize.width);

      chunkedTitle = new String[renderedTitleSize.height];
      for (int chunk = 0; chunk < renderedTitleSize.height; chunk++) {
        int beginIndex = renderedTitleSize.width * chunk;
        chunkedTitle[chunk] = model.getTitle().toString().substring(
            beginIndex,
            Math.min(titleWidth, renderedTitleSize.width * (chunk + 1)));
      }
    }

    chunkedData = new String[nRows][nCols][];
    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        chunkedData[row][col] = new String[renderedRowHeights[row]];

        for (int chunk = 0; chunk < renderedRowHeights[row]; chunk++) {
          String data = model.dataAt(row, col).toString();
          int dataLength = data.length();
          int beginIndex = renderedColWidths[col] * chunk;

          if (beginIndex < dataLength) {
            chunkedData[row][col][chunk] = data.substring(
                beginIndex,
                Math.min(dataLength, renderedColWidths[col] * (chunk + 1)));
          } else {
            chunkedData[row][col][chunk] = view.cellUI().getSpace() + "";
          }
        }
      }
    }

    chunkedHeaders = new String[nHeaders][];
    for (int head = 0; head < nHeaders; head++) {
      chunkedHeaders[head] = new String[renderedHeaderHeight];

      for (int chunk = 0; chunk < renderedHeaderHeight; chunk++) {
        String header = model.headerAt(head).toString();
        int headerLength = header.length();
        int beginIndex = renderedColWidths[head] * chunk;

        if (beginIndex < headerLength) {
          chunkedHeaders[head][chunk] = header.substring(
              beginIndex,
              Math.min(headerLength, renderedColWidths[head] * (chunk + 1)));
        } else {
          chunkedHeaders[head][chunk] = view.headerUI().getSpace() + "";
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
    if (model.columnCount() > 0) {
      int rightCol = model.columnCount() - 1;
      pr(left);

      for (int col = 0; col < rightCol; col++) {
        pr(middle, view.getPads().left + renderedColWidths[col] + view.getPads().right);
        pr(cross);
      }

      pr(middle, view.getPads().left + renderedColWidths[rightCol] + view.getPads().right);
      pr(right);
      ln();
    }
  }
  
  private void printTitle() {
    GridUI titleUI = view.titleUI();

    printTopDividerLine(titleUI);
    printEmptyRows(titleUI, view.getPads().bottom);

    for (int chunk = 0; chunk < renderedTitleSize.height; chunk++) {
      pr(titleUI.getLeftWall());
      pr(titleUI.getSpace(), view.getPads().left);

      prf(chunkedTitle[chunk], renderedTitleSize.width);

      pr(titleUI.getSpace(), view.getPads().right);
      pr(titleUI.getRightWall());

      ln();
    }

    printEmptyRows(titleUI, view.getPads().bottom);
    printBottomDividerLine(titleUI);
  }

  private void printHeaders() {
    GridUI headerUI = view.headerUI();

    if (!view.isShowTitle()) {
      printTopDividerLine(headerUI);
    }
    printEmptyRows(headerUI, view.getPads().bottom);
    int rightCol = model.columnCount() - 1;
    for (int chunk = 0; chunk < renderedHeaderHeight; chunk++) {
      for (int col = 0; col < model.columnCount(); col++) {
        if (col == 0) {
          pr(headerUI.getLeftWall());
        }
        pr(headerUI.getSpace(), view.getPads().left);

        prf(chunkedHeaders[col][chunk], renderedColWidths[col]);

        pr(headerUI.getSpace(), view.getPads().right);
        if (col == rightCol) {
          pr(headerUI.getRightWall());
        } else {
          pr(headerUI.getInnerWall());
        }
      }
      ln();
    }
    printEmptyRows(headerUI, view.getPads().bottom);
    printBottomDividerLine(headerUI);
  }

  private void printRow(GridUI gridUI, int row) {
    int rightCol = model.columnCount() - 1;

    for (int chunk = 0; chunk < renderedRowHeights[row]; chunk++) {
      for (int col = 0; col < model.columnCount(); col++) {
        if (col == 0) {
          pr(gridUI.getLeftWall());
        }
        pr(gridUI.getSpace(), view.getPads().left);

        if (chunk >= renderedDataSizes[row][col].height) {
          prf(gridUI.getSpace(), renderedDataSizes[row][col].width);
        } else {
          prf(chunkedData[row][col][chunk], renderedColWidths[col]);
        }

        pr(gridUI.getSpace(), view.getPads().right);
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
    int rowCount = model.rowCount();
    if (view.isShowTitle()) {
      printTitle();
      if (view.isShowHeaders()) {
        printHeaders();
      }
    } else if (view.isShowHeaders()) {
      printHeaders();
    }

    if (rowCount > 0) {
      GridUI cellUI = view.cellUI();
      if (!view.isShowTitle() && !view.isShowHeaders()) {
        printTopDividerLine(cellUI);
      }

      int bottomRow = rowCount - 1;
      for (int row = 0; row < rowCount; row++) {
        printEmptyRows(cellUI, view.getPads().top);
        printRow(cellUI, row);
        printEmptyRows(cellUI, view.getPads().bottom);
        if (row == bottomRow) {
          printBottomDividerLine(cellUI);
        } else {
          printInnerDividerLine(cellUI);
        }
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
