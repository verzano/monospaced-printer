package com.verzano.terminalprinter.table.view.ui;

// TODO add colors
// TODO make the getters return colors as well???
public class GridUI {
  private static final char DEF_TOP_LEFT = '┌';
  private static final char DEF_TOP_FLOOR = '─';
  private static final char DEF_TOP_CROSS = '┬';
  private static final char DEF_TOP_RIGHT = '┐';
  private static final char DEF_RIGHT_WALL = '│';
  private static final char DEF_RIGHT_CROSS = '┤';
  private static final char DEF_BOT_RIGHT = '┘';
  private static final char DEF_BOT_FLOOR = '─';
  private static final char DEF_BOT_CROSS = '┴';
  private static final char DEF_BOT_LEFT = '└';
  private static final char DEF_LEFT_WALL = '│';
  private static final char DEF_LEFT_CROSS = '├';
  private static final char DEF_INNER_WALL = '│';
  private static final char DEF_INNER_FLOOR = '─';
  private static final char DEF_INNER_CROSS = '┼';
  private static final char DEF_SPACE = ' ';

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
  private char space = DEF_SPACE;

  public GridUI(
      char topLeft,
      char topFloor,
      char topCross,
      char topRight,
      char rightWall,
      char rightCross,
      char botRight,
      char botFloor,
      char botCross,
      char botLeft,
      char leftWall,
      char leftCross,
      char innerWall,
      char innerFloor,
      char innerCross,
      char space) {
    this.topLeft = topLeft;
    this.topFloor = topFloor;
    this.topCross = topCross;
    this.topRight = topRight;
    this.rightWall = rightWall;
    this.rightCross = rightCross;
    this.botRight = botRight;
    this.botFloor = botFloor;
    this.botCross = botCross;
    this.botLeft = botLeft;
    this.leftWall = leftWall;
    this.leftCross = leftCross;
    this.innerWall = innerWall;
    this.innerFloor = innerFloor;
    this.innerCross = innerCross;
    this.space = space;
  }

  public char getTopLeft() {
    return topLeft;
  }

  public void setTopLeft(char topLeft) {
    this.topLeft = topLeft;
  }

  public char getTopFloor() {
    return topFloor;
  }

  public void setTopFloor(char topFloor) {
    this.topFloor = topFloor;
  }

  public char getTopCross() {
    return topCross;
  }

  public void setTopCross(char topCross) {
    this.topCross = topCross;
  }

  public char getTopRight() {
    return topRight;
  }

  public void setTopRight(char topRight) {
    this.topRight = topRight;
  }

  public char getRightWall() {
    return rightWall;
  }

  public void setRightWall(char rightWall) {
    this.rightWall = rightWall;
  }

  public char getRightCross() {
    return rightCross;
  }

  public void setRightCross(char rightCross) {
    this.rightCross = rightCross;
  }

  public char getBotRight() {
    return botRight;
  }

  public void setBotRight(char botRight) {
    this.botRight = botRight;
  }

  public char getBotFloor() {
    return botFloor;
  }

  public void setBotFloor(char botFloor) {
    this.botFloor = botFloor;
  }

  public char getBotCross() {
    return botCross;
  }

  public void setBotCross(char botCross) {
    this.botCross = botCross;
  }

  public char getBotLeft() {
    return botLeft;
  }

  public void setBotLeft(char botLeft) {
    this.botLeft = botLeft;
  }

  public char getLeftWall() {
    return leftWall;
  }

  public void setLeftWall(char leftWall) {
    this.leftWall = leftWall;
  }

  public char getLeftCross() {
    return leftCross;
  }

  public void setLeftCross(char leftCross) {
    this.leftCross = leftCross;
  }

  public char getInnerWall() {
    return innerWall;
  }

  public void setInnerWall(char innerWall) {
    this.innerWall = innerWall;
  }

  public char getInnerFloor() {
    return innerFloor;
  }

  public void setInnerFloor(char innerFloor) {
    this.innerFloor = innerFloor;
  }

  public char getInnerCross() {
    return innerCross;
  }

  public void setInnerCross(char innerCross) {
    this.innerCross = innerCross;
  }

  public char getSpace() {
    return space;
  }

  public void setSpace(char space) {
    this.space = space;
  }
}
