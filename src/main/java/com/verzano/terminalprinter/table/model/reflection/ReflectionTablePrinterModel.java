package com.verzano.terminalprinter.table.model.reflection;

import com.verzano.terminalprinter.table.model.DefaultTablePrinterModel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

// TODO allow selection of only certain fields (exclusion & inclusion)
// TODO don't extends DefaultTablePrinterModel, maybe use a default in the TablePrinterModel...
public class ReflectionTablePrinterModel extends DefaultTablePrinterModel {
  public ReflectionTablePrinterModel(Collection<?> rows) {
    super(null, null, null);

    String[] headers = null;
    Object[][] reflectionRows = null;

    if (rows.size() > 0) {
      Class clazz = rows.iterator().next().getClass();
      setTitle(clazz.getSimpleName());

      Field[] fields = clazz.getDeclaredFields();
      headers = Arrays.stream(fields)
          .map(Field::getName)
          .toArray(String[]::new);

      reflectionRows = new Object[rows.size()][headers.length];
      int index = 0;
      for (Object row : rows) {
        reflectionRows[index] = Arrays.stream(fields)
            .map(field -> {
              try {
                return field.get(row);
              } catch (IllegalAccessException e) {
                return null;
              }
            }).toArray(Object[]::new);

        index++;
      }
    }

    setRows(reflectionRows);
    setHeaders(headers);
  }
}
