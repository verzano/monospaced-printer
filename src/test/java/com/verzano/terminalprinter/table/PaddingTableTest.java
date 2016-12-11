package com.verzano.terminalprinter.table;

import com.verzano.terminalprinter.table.metrics.Padding;
import org.junit.Test;

import static com.verzano.terminalprinter.test.helper.TestHelper.getTestResourceAsString;
import static com.verzano.terminalprinter.test.helper.TestHelper.printTableAsString;
import static org.junit.Assert.assertEquals;

public class PaddingTableTest {
  @Test
  public void createTable1x1Pad0x0x0x0() throws Exception {
    String table1x1Pad0x0x0x0 = getTestResourceAsString("table/padding/Table1x1Pad0x0x0x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 0, 0, 0)));

    assertEquals(table1x1Pad0x0x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x0x0x1() throws Exception {
    String table1x1Pad0x0x0x1 = getTestResourceAsString("table/padding/Table1x1Pad0x0x0x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 0, 0, 1)));

    assertEquals(table1x1Pad0x0x0x1, printedTable);
  }

  @Test
  public void createTable1x1Pad0x0x1x0() throws Exception {
    String table1x1Pad0x0x1x0 = getTestResourceAsString("table/padding/Table1x1Pad0x0x1x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 0, 1, 0)));

    assertEquals(table1x1Pad0x0x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x0x1x1() throws Exception {
    String table1x1Pad0x0x1x1 = getTestResourceAsString("table/padding/Table1x1Pad0x0x1x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 0, 1, 1)));

    assertEquals(table1x1Pad0x0x1x1, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x0x0() throws Exception {
    String table1x1Pad0x1x0x0 = getTestResourceAsString("table/padding/Table1x1Pad0x1x0x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 1, 0, 0)));

    assertEquals(table1x1Pad0x1x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x0x1() throws Exception {
    String table1x1Pad0x1x0x1 = getTestResourceAsString("table/padding/Table1x1Pad0x1x0x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 1, 0, 1)));

    assertEquals(table1x1Pad0x1x0x1, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x1x0() throws Exception {
    String table1x1Pad0x1x1x0 = getTestResourceAsString("table/padding/Table1x1Pad0x1x1x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 1, 1, 0)));

    assertEquals(table1x1Pad0x1x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad0x1x1x1() throws Exception {
    String table1x1Pad0x1x1x1 = getTestResourceAsString("table/padding/Table1x1Pad0x1x1x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(0, 1, 1, 1)));

    assertEquals(table1x1Pad0x1x1x1, printedTable);
  }

  @Test
  public void createTable1x1Pad1x0x0x0() throws Exception {
    String table1x1Pad1x0x0x0 = getTestResourceAsString("table/padding/Table1x1Pad1x0x0x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 0, 0, 0)));

    assertEquals(table1x1Pad1x0x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x0x0x1() throws Exception {
    String table1x1Pad1x0x0x1 = getTestResourceAsString("table/padding/Table1x1Pad1x0x0x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 0, 0, 1)));

    assertEquals(table1x1Pad1x0x0x1, printedTable);
  }
  @Test
  public void createTable1x1Pad1x0x1x0() throws Exception {
    String table1x1Pad1x0x1x0 = getTestResourceAsString("table/padding/Table1x1Pad1x0x1x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 0, 1, 0)));

    assertEquals(table1x1Pad1x0x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x0x1x1() throws Exception {
    String table1x1Pad1x0x1x1 = getTestResourceAsString("table/padding/Table1x1Pad1x0x1x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 0, 1, 1)));

    assertEquals(table1x1Pad1x0x1x1, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x0x0() throws Exception {
    String table1x1Pad1x1x0x0 = getTestResourceAsString("table/padding/Table1x1Pad1x1x0x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 1, 0, 0)));

    assertEquals(table1x1Pad1x1x0x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x0x1() throws Exception {
    String table1x1Pad1x1x0x1 = getTestResourceAsString("table/padding/Table1x1Pad1x1x0x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 1, 0, 1)));

    assertEquals(table1x1Pad1x1x0x1, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x1x0() throws Exception {
    String table1x1Pad1x1x1x0 = getTestResourceAsString("table/padding/Table1x1Pad1x1x1x0.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 1, 1, 0)));

    assertEquals(table1x1Pad1x1x1x0, printedTable);
  }

  @Test
  public void createTable1x1Pad1x1x1x1() throws Exception {
    String table1x1Pad1x1x1x1 = getTestResourceAsString("table/padding/Table1x1Pad1x1x1x1.txt");
    String printedTable = printTableAsString(new TablePrinter(
        new Object[][]{{1}},
        null,
        null,
        null,
        null,
        new Padding(1, 1, 1, 1)));

    assertEquals(table1x1Pad1x1x1x1, printedTable);
  }
}
