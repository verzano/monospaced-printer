package com.verzano.util.printer.table;

import com.verzano.util.printer.table.metrics.Padding;

import java.io.PrintStream;
import java.util.Arrays;

// TODO add colors
// TODO add styling
// TODO enforce that nHeaders and nCols are the same
// TODO do the resizing of the table more intelligently (allow title to influence and have sizes for cells)
// TODO allow nulls
public class TablePrinter {
  // Title symbols
  private static final char DEF_TITLE_TOP_LEFT = '\u250C';
  private static final char DEF_TITLE_TOP_FLOOR = '\u2500';
  private static final char DEF_TITLE_TOP_RIGHT = '\u2510';
  private static final char DEF_TITLE_RIGHT_WALL = '\u2502';
  private static final char DEF_TITLE_BOT_RIGHT = '\u2561';
  private static final char DEF_TITLE_BOT_FLOOR = '\u2550';
  private static final char DEF_TITLE_BOT_CROSS = '\u2564';
  private static final char DEF_TITLE_BOT_LEFT = '\u255E';
  private static final char DEF_TITLE_LEFT_WALL = '\u2502';

  private char titleTopLeft = DEF_TITLE_TOP_LEFT;
  private char titleTopFloor = DEF_TITLE_TOP_FLOOR;
  private char titleTopRight = DEF_TITLE_TOP_RIGHT;
  private char titleRightWall = DEF_TITLE_RIGHT_WALL;
  private char titleBotRight = DEF_TITLE_BOT_RIGHT;
  private char titleBotCross = DEF_TITLE_BOT_CROSS;
  private char titleBotFloor = DEF_TITLE_BOT_FLOOR;
  private char titleBotLeft = DEF_TITLE_BOT_LEFT;
  private char titleLeftWall = DEF_TITLE_LEFT_WALL;
  
  // Header symbols
  private static final char DEF_HEADER_TOP_LEFT = '\u250C';
  private static final char DEF_HEADER_TOP_FLOOR = '\u2500';
  private static final char DEF_HEADER_TOP_CROSS = '\u252C';
  private static final char DEF_HEADER_TOP_RIGHT = '\u2510';
  private static final char DEF_HEADER_RIGHT_WALL = '\u2502';
  private static final char DEF_HEADER_BOT_RIGHT = '\u2561';
  private static final char DEF_HEADER_BOT_FLOOR = '\u2550';
  private static final char DEF_HEADER_BOT_CROSS = '\u256A';
  private static final char DEF_HEADER_BOT_LEFT = '\u255E';
  private static final char DEF_HEADER_LEFT_WALL = '\u2502';
  private static final char DEF_HEADER_INNER_WALL = '\u2502';

  private char headerTopLeft = DEF_HEADER_TOP_LEFT;
  private char headerTopFloor = DEF_HEADER_TOP_FLOOR;
  private char headerTopCross = DEF_HEADER_TOP_CROSS;
  private char headerTopRight = DEF_HEADER_TOP_RIGHT;
  private char headerRightWall = DEF_HEADER_RIGHT_WALL;
  private char headerBotRight = DEF_HEADER_BOT_RIGHT;
  private char headerBotFloor = DEF_HEADER_BOT_FLOOR;
  private char headerBotCross = DEF_HEADER_BOT_CROSS;
  private char headerBotLeft = DEF_HEADER_BOT_LEFT;
  private char headerLeftWall = DEF_HEADER_LEFT_WALL;
  private char headerInnerWall = DEF_HEADER_INNER_WALL;

  // Table symbols
  private static final char DEF_TOP_LEFT = '\u250C';
  private static final char DEF_TOP_FLOOR = '\u2500';
  private static final char DEF_TOP_CROSS = '\u252C';
  private static final char DEF_TOP_RIGHT = '\u2510';
  private static final char DEF_RIGHT_WALL = '\u2502';
  private static final char DEF_RIGHT_CROSS = '\u2524';
  private static final char DEF_BOT_RIGHT = '\u2518';
  private static final char DEF_BOT_FLOOR = '\u2500';
  private static final char DEF_BOT_CROSS = '\u2534';
  private static final char DEF_BOT_LEFT = '\u2514';
  private static final char DEF_LEFT_WALL = '\u2502';
  private static final char DEF_LEFT_CROSS = '\u251C';
  private static final char DEF_INNER_WALL = '\u2502';
  private static final char DEF_INNER_FLOOR = '\u2500';
  private static final char DEF_INNER_CROSS = '\u253C';

  private char topLeft = DEF_TOP_LEFT;
  private char topFloor = DEF_TOP_FLOOR;
  private char topCross = DEF_TOP_CROSS;
  private char topRight = DEF_TOP_RIGHT;
  private char rightWall = DEF_RIGHT_WALL;
  private char rightCross = DEF_RIGHT_CROSS;
  private char botRight = DEF_BOT_RIGHT;
  private char botFloor = DEF_BOT_FLOOR;
  private char botCross = DEF_BOT_CROSS;
  private char botLeft = DEF_BOT_LEFT;
  private char leftWall = DEF_LEFT_WALL;
  private char leftCross = DEF_LEFT_CROSS;
  private char innerWall = DEF_INNER_WALL;
  private char innerFloor = DEF_INNER_FLOOR;
  private char innerCross = DEF_INNER_CROSS;

  // Spacer
  private static final char DEF_SPACE = ' ';

  private char space = DEF_SPACE;

  private PrintStream out = System.out;

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
  private int[] renderedRowHeights;

  private String[] chunkedTitle;
  private String[][] chunkedHeaders;
  private String[][][] chunkedData;

  public TablePrinter() {
    this(null, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows) {
    this(rows, null, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows, Object[] headers) {
    this(rows, headers, null, null, null, null, null);
  }

  public TablePrinter(Object[][] rows, Object[] headers, Object title) {
    this(rows, headers, title, null, null, null, null);
  }

  public TablePrinter(
      Object[][] rows,
      Object[] headers,
      Object title,
      int[] minWidths,
      int[] maxWidths,
      Padding pads) {
    this(rows, headers, title, minWidths, maxWidths, pads, null);
  }

  public TablePrinter(
      Object[][] rows,
      Object[] headers,
      Object title,
      int[] minWidths,
      int[] maxWidths,
      Padding pads,
      PrintStream out) {
    this.out = out == null ? System.out : out;

    this.rows = rows;
    nRows = rows == null ? 0 : rows.length;
    nCols = rows == null ? 0 : rows[0].length;

    this.headers = headers;
    nHeaders = headers == null ? 0 : headers.length;
    
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

  public void setTitleTopLeft(char titleTopLeft) {
    this.titleTopLeft = titleTopLeft;
  }

  public void setTitleTopFloor(char titleTopFloor) {
    this.titleTopFloor = titleTopFloor;
  }

  public void setTitleTopRight(char titleTopRight) {
    this.titleTopRight = titleTopRight;
  }

  public void setTitleRightWall(char titleRightWall) {
    this.titleRightWall = titleRightWall;
  }

  public void setTitleBotRight(char titleBotRight) {
    this.titleBotRight = titleBotRight;
  }

  public void setTitleBotCross(char titleBotCross) {
    this.titleBotCross = titleBotCross;
  }

  public void setTitleBotFloor(char titleBotFloor) {
    this.titleBotFloor = titleBotFloor;
  }

  public void setTitleBotLeft(char titleBotLeft) {
    this.titleBotLeft = titleBotLeft;
  }

  public void setTitleLeftWall(char titleLeftWall) {
    this.titleLeftWall = titleLeftWall;
  }

  public void setHeaderTopLeft(char headerTopLeft) {
    this.headerTopLeft = headerTopLeft;
  }

  public void setHeaderTopFloor(char headerTopFloor) {
    this.headerTopFloor = headerTopFloor;
  }

  public void setHeaderTopCross(char headerTopCross) {
    this.headerTopCross = headerTopCross;
  }

  public void setHeaderTopRight(char headerTopRight) {
    this.headerTopRight = headerTopRight;
  }

  public void setHeaderRightWall(char headerRightWall) {
    this.headerRightWall = headerRightWall;
  }

  public void setHeaderBotRight(char headerBotRight) {
    this.headerBotRight = headerBotRight;
  }

  public void setHeaderBotFloor(char headerBotFloor) {
    this.headerBotFloor = headerBotFloor;
  }

  public void setHeaderBotCross(char headerBotCross) {
    this.headerBotCross = headerBotCross;
  }

  public void setHeaderBotLeft(char headerBotLeft) {
    this.headerBotLeft = headerBotLeft;
  }

  public void setHeaderLeftWall(char headerLeftWall) {
    this.headerLeftWall = headerLeftWall;
  }

  public void setHeaderInnerWall(char headerInnerWall) {
    this.headerInnerWall = headerInnerWall;
  }

  public void setTopLeft(char topLeft) {
    this.topLeft = topLeft;
  }

  public void setTopFloor(char topFloor) {
    this.topFloor = topFloor;
  }

  public void setTopCross(char topCross) {
    this.topCross = topCross;
  }

  public void setTopRight(char topRight) {
    this.topRight = topRight;
  }

  public void setRightWall(char rightWall) {
    this.rightWall = rightWall;
  }

  public void setRightCross(char rightCross) {
    this.rightCross = rightCross;
  }

  public void setBotRight(char botRight) {
    this.botRight = botRight;
  }

  public void setBotFloor(char botFloor) {
    this.botFloor = botFloor;
  }

  public void setBotCross(char botCross) {
    this.botCross = botCross;
  }

  public void setBotLeft(char botLeft) {
    this.botLeft = botLeft;
  }

  public void setLeftWall(char leftWall) {
    this.leftWall = leftWall;
  }

  public void setLeftCross(char leftCross) {
    this.leftCross = leftCross;
  }

  public void setInnerWall(char innerWall) {
    this.innerWall = innerWall;
  }

  public void setInnerFloor(char innerFloor) {
    this.innerFloor = innerFloor;
  }

  public void setInnerCross(char innerCross) {
    this.innerCross = innerCross;
  }

  public void setSpace(char space) {
    this.space = space;
  }

  private void calculateRenderedSizes() {
    renderedTitleWidth = 0;
    renderedTitleHeight = 0;
    renderedColWidths = new int[nCols];
    renderedRowHeights = new int[nRows];

    for (int row = 0; row < nRows; row++) {
      for (int col = 0; col < nCols; col++) {
        int dataWidth = rows[row][col].toString().length();

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
      int headerWidth = headers[head].toString().length();
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

        String data = rows[row][col].toString();
        int dataLength = data.length();
        for (int chunk = 0; chunk < renderedRowHeights[row]; chunk++) {
          int beginIndex = renderedColWidths[col] * chunk;

          if (beginIndex < dataLength) {
            chunkedData[row][col][chunk] = data.substring(
                beginIndex,
                Math.min(dataLength, renderedColWidths[col] * (chunk + 1)));
          } else {
            chunkedData[row][col][chunk] = space + "";
          }
        }
      }
    }

    chunkedHeaders = new String[nCols][];
    for (int head = 0; head < nHeaders; head++) {
      chunkedHeaders[head] = new String[renderedHeaderHeight];

      String header = headers[head].toString();
      int headerLength = header.length();
      for (int chunk = 0; chunk < renderedHeaderHeight; chunk++) {
        int beginIndex = renderedColWidths[head] * chunk;

        if (beginIndex < headerLength) {
          chunkedHeaders[head][chunk] = header.substring(
              beginIndex,
              Math.min(headerLength, renderedColWidths[head] * (chunk + 1)));
        } else {
          chunkedHeaders[head][chunk] = space + "";
        }
      }
    }
  }

  private void printLine(char left, char floor, char cross, char right) {
    if (nCols > 0) {
      pr(left);

      pr(floor, renderedColWidths[0] + pads.left + pads.right);
      for (int col = 1; col < nCols; col++) {
        pr(cross);
        pr(floor, renderedColWidths[col] + pads.left + pads.right);
      }
      pr(right);
      ln();
    }
  }
  
  private void printTitle() {
    pr(titleTopLeft);
    pr(titleTopFloor, renderedTitleWidth + pads.left + pads.right);
    pr(titleTopRight);
    ln();
    printNEmptyRows(pads.bottom, titleLeftWall, ' ', titleRightWall);

    for (int chunk = 0; chunk < renderedTitleHeight; chunk++) {
      pr(titleLeftWall);
      pr(space, pads.left);

      prf(chunkedTitle[chunk], renderedTitleWidth);

      pr(space, pads.right);
      pr(titleRightWall);

      ln();
    }

    printNEmptyRows(pads.bottom, titleLeftWall, ' ', titleRightWall);
    printLine(titleBotLeft, titleBotFloor, titleBotCross, titleBotRight);
  }

  private void printHeaders(boolean printTop) {
    if (printTop) {
      printLine(headerTopLeft, headerTopFloor, headerTopCross, headerTopRight);
    }
    printNEmptyRows(pads.bottom, headerLeftWall, headerInnerWall, headerRightWall);
    int rightCol = nCols - 1;
    for (int chunk = 0; chunk < renderedHeaderHeight; chunk++) {
      for (int col = 0; col < nCols; col++) {
        if (col == 0) {
          pr(headerLeftWall);
        }
        pr(space, pads.left);

        prf(chunkedHeaders[col][chunk], renderedColWidths[col]);

        pr(space, pads.right);
        if (col == rightCol) {
          pr(headerRightWall);
        } else {
          pr(headerInnerWall);
        }
      }
      ln();
    }
    printNEmptyRows(pads.bottom, headerLeftWall, headerInnerWall, headerRightWall);
    printLine(headerBotLeft, headerBotFloor, headerBotCross, headerBotRight);
  }

  private void printTop() {
    printLine(topLeft, topFloor, topCross, topRight);
  }

  private void printRowSeparator() {
    printLine(leftCross, innerFloor, innerCross, rightCross);
  }

  private void printBottom() {
    printLine(botLeft, botFloor, botCross, botRight);
  }

  private void printRow(int row, char left, char middle, char right) {
    int rightCol = nCols - 1;

    for (int chunk = 0; chunk < renderedRowHeights[row]; chunk++) {
      for (int col = 0; col < nCols; col++) {
        if (col == 0) {
          pr(left);
        }
        pr(space, pads.left);

        prf(chunkedData[row][col][chunk], renderedColWidths[col]);

        pr(space, pads.right);
        if (col == rightCol) {
          pr(right);
        } else {
          pr(middle);
        }
      }
      ln();
    }
  }

  private void printNEmptyRows(int nEmptyRows, char left, char middle, char right) {
    for(int row = 0; row < nEmptyRows; row++) {
      int rightCol = nCols - 1;
      for (int col = 0; col < nCols; col++) {
        if (col == 0) {
          pr(left);
        }
        pr(space, pads.left);

        prf(space, renderedColWidths[col]);

        pr(space, pads.right);
        if (col == rightCol) {
          pr(right);
        } else {
          pr(middle);
        }
      }
      ln();
    }
  }

  public void print() {
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
          printTop();
        }
      } else {
        printRowSeparator();
      }
      printNEmptyRows(pads.top, leftWall, innerWall, rightWall);
      printRow(row, leftWall, innerWall, rightWall);
      printNEmptyRows(pads.bottom, leftWall, innerWall, rightWall);
      if(row == bottomRow) {
        printBottom();
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
