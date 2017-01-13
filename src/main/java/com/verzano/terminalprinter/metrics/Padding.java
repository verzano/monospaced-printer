package com.verzano.terminalprinter.metrics;

public class Padding {
  public int left;
  public int right;
  public int top;
  public int bottom;

  public Padding(int left, int right, int top, int bottom) {
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
  }

  public Padding(int leftRight, int topBottom) {
    this(leftRight, leftRight, topBottom, topBottom);
  }

  public Padding() {
    this(0, 0);
  }
}
