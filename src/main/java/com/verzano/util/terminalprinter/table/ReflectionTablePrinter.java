package com.verzano.util.terminalprinter.table;

import com.verzano.util.terminalprinter.table.metrics.Padding;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public class ReflectionTablePrinter extends TablePrinter {
  public ReflectionTablePrinter() {
    this(null, null, null, null, null, null);
  }

  public ReflectionTablePrinter(Collection<?> rows) {
    this(rows, null, null, null, null, null);
  }

  public ReflectionTablePrinter(Collection<?> rows, Object title) {
    this(rows, title, null, null, null, null);
  }

  public ReflectionTablePrinter(
      Collection<?> rows,
      Object title,
      int[] minWidths,
      int[] maxWidths,
      Padding pads,
      PrintStream out) {
    super(null, null, null, minWidths, maxWidths, pads, out);
    setRows(rows);
    setTitle(title);
  }

  public void setRows(Collection<?> rows) {
    String[] headers = null;
    Object[][] reflectionRows = null;

    if (rows.size() > 0) {
      Field[] fields = rows.iterator().next().getClass().getDeclaredFields();
      headers = Arrays.stream(fields)
          .map(Field::getName)
          .toArray(String[]::new);

      reflectionRows = new Object[rows.size()][headers.length];
      int index = 0;
      for (Object row : rows) {
        reflectionRows[index] = Arrays.stream(fields)
            .map(f -> {
              try {
                return f.get(row);
              } catch (IllegalAccessException e) {
                // TODO make this a null when nulls are allowed
                return "";
              }
            }).toArray(Object[]::new);

        index++;
      }
    }

    setRows(reflectionRows);
    setHeaders(headers);
  }
}
