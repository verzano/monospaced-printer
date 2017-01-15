package com.verzano.terminalprinter.test.helper;

import com.verzano.terminalprinter.table.TablePrinter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestHelper {
  private TestHelper() { }

  public static String getTestResourceAsString(String file) throws URISyntaxException, IOException {
    return new String(Files.readAllBytes(
        Paths.get(TestHelper.class
            .getClassLoader()
            .getResource(file)
            .toURI())), Charset.forName("UTF-8"));
  }

  public static String printTableAsString(TablePrinter printer) throws IOException {
    try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos)) {
      printer.setPrinter(out);
      printer.print();
      return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }
  }
}
