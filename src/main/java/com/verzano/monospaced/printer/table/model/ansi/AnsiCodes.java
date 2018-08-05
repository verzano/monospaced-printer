package com.verzano.monospaced.printer.table.model.ansi;

import java.util.StringJoiner;

public class AnsiCodes {
  // escape characters
  public static final String ESC = "\\033";
  public static final String BEGIN = ESC + "[";
  public static final String SEPARATOR = ";";
  public static final String END = "m";

  // text attributes
  public static final String ALL_OFF = "0";
  public static final String BOLD = "1";
  public static final String UNDERSCORE = "4";
  public static final String BLINK = "5";
  public static final String REVERSE = "7";
  public static final String CONCEALED = "8";

  // foreground colors
  public static final String BLACK_F = "30";
  public static final String RED_F = "31";
  public static final String GREEN_F = "32";
  public static final String YELLOW_F = "33";
  public static final String BLUE_F = "34";
  public static final String MAGENTA_F = "35";
  public static final String CYAN_F = "36";
  public static final String WHITE_F = "37";

  // background colors
  public static final String BLACK_B = "40";
  public static final String RED_B = "41";
  public static final String GREEN_B = "42";
  public static final String YELLOW_B = "43";
  public static final String BLUE_B = "44";
  public static final String MAGENTA_B = "45";
  public static final String CYAN_B = "46";
  public static final String WHITE_B = "47";

  // utility
  public static final String CLEAR = generateCode(ALL_OFF, null, null);

  private AnsiCodes() { }

  public static String generateCode(String attribute, String foreground, String background) {
    StringJoiner join = new StringJoiner(SEPARATOR, BEGIN, END);

    if (attribute != null) {
      join.add(attribute);
    }

    if (foreground != null) {
      join.add(foreground);
    }

    if (background != null) {
      join.add(background);
    }

    return join.toString();
  }
}
