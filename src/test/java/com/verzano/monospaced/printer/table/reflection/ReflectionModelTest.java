package com.verzano.monospaced.printer.table.reflection;

import static org.junit.Assert.assertEquals;

import com.verzano.monospaced.printer.table.TablePrinter;
import com.verzano.monospaced.printer.table.model.reflection.ReflectionTablePrinterModel;
import com.verzano.monospaced.printer.test.helper.TestHelper;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.Test;

public class ReflectionModelTest {
  @Test
  public void ReflectionModel5Rows() throws Exception{
    String reflectionModel5Rows = TestHelper
        .getTestResourceAsString("table/reflection/ReflectionModel5Rows.txt");

    Collection<Person> people = new LinkedList<>();
    people.add(new Person("Person 1", 1, Gender.MALE, Country.GERMANY));
    people.add(new Person("Person 2", 2, Gender.FEMALE, Country.GERMANY));
    people.add(new Person("Person 3", 3, Gender.MALE, Country.USA));
    people.add(new Person("Person 4", 4, Gender.FEMALE, Country.TURKEY));
    people.add(new Person("Person 5", 5, Gender.MALE, Country.JAPAN));

    ReflectionTablePrinterModel model = new ReflectionTablePrinterModel(people);
    String printedTable = TestHelper.printTableAsString(new TablePrinter(model, null, null));

    assertEquals(reflectionModel5Rows, printedTable);
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
