package com.verzano.terminalprinter.table.view.ui;

public class TableUI {
  private static final char DEF_TITLE_TOP_LEFT = '\u250C';
  private static final char DEF_TITLE_TOP_FLOOR = '\u2500';
  private static final char DEF_TITLE_TOP_RIGHT = '\u2510';
  private static final char DEF_TITLE_RIGHT_WALL = '\u2502';
  private static final char DEF_TITLE_BOT_RIGHT = '\u2561';
  private static final char DEF_TITLE_BOT_FLOOR = '\u2550';
  private static final char DEF_TITLE_BOT_CROSS = '\u2564';
  private static final char DEF_TITLE_BOT_LEFT = '\u255E';
  private static final char DEF_TITLE_LEFT_WALL = '\u2502';
  private static final char DEF_TITLE_SPACE = ' ';

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
  private static final char DEF_HEADER_SPACE = ' ';

  private static final char DEF_CELL_TOP_LEFT = '\u250C';
  private static final char DEF_CELL_TOP_FLOOR = '\u2500';
  private static final char DEF_CELL_TOP_CROSS = '\u252C';
  private static final char DEF_CELL_TOP_RIGHT = '\u2510';
  private static final char DEF_CELL_RIGHT_WALL = '\u2502';
  private static final char DEF_CELL_RIGHT_CROSS = '\u2524';
  private static final char DEF_CELL_BOT_RIGHT = '\u2518';
  private static final char DEF_CELL_BOT_FLOOR = '\u2500';
  private static final char DEF_CELL_BOT_CROSS = '\u2534';
  private static final char DEF_CELL_BOT_LEFT = '\u2514';
  private static final char DEF_CELL_LEFT_WALL = '\u2502';
  private static final char DEF_CELL_LEFT_CROSS = '\u251C';
  private static final char DEF_CELL_INNER_WALL = '\u2502';
  private static final char DEF_CELL_INNER_FLOOR = '\u2500';
  private static final char DEF_CELL_INNER_CROSS = '\u253C';
  private static final char DEF_CELL_SPACE = ' ';
  
  private GridUI titleUI;
  private GridUI headerUI;
  private GridUI cellUI;
  
  public TableUI() {
    titleUI = new GridUI(
        DEF_TITLE_TOP_LEFT,
        DEF_TITLE_TOP_FLOOR,
        ' ',
        DEF_TITLE_TOP_RIGHT,
        DEF_TITLE_RIGHT_WALL,
        ' ',
        DEF_TITLE_BOT_RIGHT,
        DEF_TITLE_BOT_FLOOR,
        DEF_TITLE_BOT_CROSS,
        DEF_TITLE_BOT_LEFT,
        DEF_TITLE_LEFT_WALL,
        ' ',
        ' ',
        ' ',
        ' ',
        DEF_TITLE_SPACE);

    headerUI = new GridUI(
        DEF_HEADER_TOP_LEFT,
        DEF_HEADER_TOP_FLOOR,
        DEF_HEADER_TOP_CROSS,
        DEF_HEADER_TOP_RIGHT,
        DEF_HEADER_RIGHT_WALL,
        ' ',
        DEF_HEADER_BOT_RIGHT,
        DEF_HEADER_BOT_FLOOR,
        DEF_HEADER_BOT_CROSS,
        DEF_HEADER_BOT_LEFT,
        DEF_HEADER_LEFT_WALL,
        ' ',
        DEF_HEADER_INNER_WALL,
        ' ',
        ' ',
        DEF_HEADER_SPACE);

    cellUI = new GridUI(
        DEF_CELL_TOP_LEFT,
        DEF_CELL_TOP_FLOOR,
        DEF_CELL_TOP_CROSS,
        DEF_CELL_TOP_RIGHT,
        DEF_CELL_RIGHT_WALL,
        DEF_CELL_RIGHT_CROSS,
        DEF_CELL_BOT_RIGHT,
        DEF_CELL_BOT_FLOOR,
        DEF_CELL_BOT_CROSS,
        DEF_CELL_BOT_LEFT,
        DEF_CELL_LEFT_WALL,
        DEF_CELL_LEFT_CROSS,
        DEF_CELL_INNER_WALL,
        DEF_CELL_INNER_FLOOR,
        DEF_CELL_INNER_CROSS,
        DEF_CELL_SPACE);
  }

  public GridUI getTitleUI() {
    return titleUI;
  }

  public void setTitleUI(GridUI titleUI) {
    this.titleUI = titleUI;
  }

  public GridUI getHeaderUI() {
    return headerUI;
  }

  public void setHeaderUI(GridUI headerUI) {
    this.headerUI = headerUI;
  }

  public GridUI getCellUI() {
    return cellUI;
  }

  public void setCellUI(GridUI cellUI) {
    this.cellUI = cellUI;
  }
}
