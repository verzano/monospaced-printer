package com.verzano.terminalprinter;

import com.verzano.terminalprinter.table.TablePrinter;
import com.verzano.terminalprinter.table.model.reflection.ReflectionTablePrinterModel;

import java.util.Collection;
import java.util.LinkedList;

public class ManualTest {
  public static void main(String[] args) {
//    Random r = new Random();
//
//    Integer[][] rows = new Integer[5][5];
//
//    for (int i = 0; i < 5; i++) {
//      for (int j = 0; j < 5; j++) {
//        rows[i][j] = r.nextInt();
//      }
//    }
//
//    String[] headers = new String[] {
//        "First", "Second", "Third", "Fuck", "You"
//    };
//
//    TablePrinterModel model = new DefaultTablePrinterModel(rows, headers, "You're a cunt");
//    TablePrinter printer = new TablePrinter(model, null, null);
//    printer.print();

    Collection<Person> people = new LinkedList<>();
    people.add(new Person("Roman Preuss", 30, Gender.MALE, Country.GERMANY));
    people.add(new Person("Laurence Bortfeld", 29, Gender.MALE, Country.GERMANY));
    people.add(new Person("Mitchell Berg", 26, Gender.MALE, Country.USA));
    people.add(new Person("Alican Dedekargenoglu", 88, Gender.FEMALE, Country.TURKEY));
    people.add(new Person("Hironori Kawaida", 27, Gender.MALE, Country.JAPAN));

    ReflectionTablePrinterModel rModel = new ReflectionTablePrinterModel(people);
    TablePrinter rPrinter = new TablePrinter(rModel, null, null);
    rPrinter.print();
  }

  public static class Person {
    public String name;
    public Integer age;
    public Gender gender;
    public Country country;

    public Person(String name, Integer age, Gender gender, Country country) {
      this.name = name;
      this.age = age;
      this.gender = gender;
      this.country = country;
    }
  }

  public enum Gender {
    MALE, FEMALE
  }

  public enum Country {
    GERMANY, USA, TURKEY, JAPAN
  }
}
