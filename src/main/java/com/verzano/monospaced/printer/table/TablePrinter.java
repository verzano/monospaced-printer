package com.verzano.monospaced.printer.table;

import com.verzano.monospaced.printer.Printer;
import com.verzano.monospaced.printer.metrics.Size;
import com.verzano.monospaced.printer.table.model.DefaultTablePrinterModel;
import com.verzano.monospaced.printer.table.model.TablePrinterModel;
import com.verzano.monospaced.printer.table.view.TablePrinterView;
import com.verzano.monospaced.printer.table.view.ui.GridUI;
import java.io.PrintStream;
import java.util.stream.IntStream;

// TODO add colors
// TODO add styling
// TODO add setters/getters and organize them
// TODO combine some of the similar drawing logic
// TODO add a configurable default for null
public class TablePrinter extends Printer {
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

  public TablePrinter(TablePrinterModel model, TablePrinterView view, PrintStream printer) {
    super(printer);
    this.model = model == null ? new DefaultTablePrinterModel() : model;
    this.view = view == null ? new TablePrinterView(this.model) : view;

    calculateRenderedSizes();
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
      int renderedTableWidth = IntStream.of(renderedColWidths).sum()
          + nCols - 1
          + view.getPads().left * (nCols - 1)
          + view.getPads().right * (nCols - 1);
      renderedTitleSize.width = Math.max(titleWidth, renderedTableWidth);

      // TODO for now enforce title locked to one line...
      renderedTitleSize.height = 1;
      chunkedTitle = new String[renderedTitleSize.height];
      chunkedTitle[0] = model.getTitle().toString();
//      renderedTitleSize.height = (int)Math.ceil(titleWidth/(double)renderedTitleSize.width);
//
//      chunkedTitle = new String[renderedTitleSize.height];
//      for (int chunk = 0; chunk < renderedTitleSize.height; chunk++) {
//        int beginIndex = renderedTitleSize.width * chunk;
//        chunkedTitle[chunk] = model.getTitle().toString().substring(
//            beginIndex,
//            Math.min(titleWidth, renderedTitleSize.width * (chunk + 1)));
//      }

      if (renderedTableWidth < renderedTitleSize.width) {
        int diff = renderedTitleSize.width - renderedTableWidth;

        switch (view.getExpansion()) {
          case FIRST:
            if (model.columnCount() > 0) {
              renderedColWidths[0] += diff;
            }
            if (model.headerCount() > 0) {
              renderedHeaderSizes[0].width += diff;
            }
            break;
          case LAST:
            if (model.columnCount() > 0) {
              renderedColWidths[model.columnCount() - 1] += diff;
            }
            if (model.headerCount() > 0) {
              renderedHeaderSizes[model.headerCount() - 1].width += diff;
            }
            break;
          case ALL:
            if (model.columnCount() > 0) {
              for (int col = 0; col < model.columnCount(); col++) {
                renderedColWidths[col] += diff/model.columnCount();
              }

              renderedColWidths[0] += diff % model.columnCount();
            }
            if (model.headerCount() > 0) {
              for (int head = 0; head < model.headerCount(); head++) {
                renderedHeaderSizes[head].width += diff/model.headerCount();
              }

              renderedColWidths[0] += diff % model.headerCount();
            }
            break;
        }
      }
    }

    chunkedData = new String[nRows][nCols][];
    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        chunkedData[row][col] = new String[renderedRowHeights[row]];

        for (int chunk = 0; chunk < renderedRowHeights[row]; chunk++) {
          Object data = model.dataAt(row, col);
          String dataString = data == null ? "" : data.toString();
          chunkText(dataString, chunk, chunkedData[row][col], renderedColWidths[col], view.cellUI());
        }
      }
    }

    chunkedHeaders = new String[nHeaders][];
    for (int head = 0; head < nHeaders; head++) {
      chunkedHeaders[head] = new String[renderedHeaderHeight];

      for (int chunk = 0; chunk < renderedHeaderHeight; chunk++) {
        Object header = model.headerAt(head);
        String headerString = header == null ? "" : header.toString();
        chunkText(headerString, chunk, chunkedHeaders[head], renderedColWidths[head], view.headerUI());
      }
    }
  }

  private void chunkText(String text, int chunk, String[] chunkedArray, int renderedColWidth, GridUI ui) {
      int textLength = text.length();
      int beginIndex = renderedColWidth * chunk;

      if (beginIndex < textLength) {
          chunkedArray[chunk] = text.substring(
                  beginIndex,
                  Math.min(textLength, renderedColWidth * (chunk + 1)));
      } else {
          chunkedArray[chunk] = ui.getSpace() + "";
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

  @Override
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
}
