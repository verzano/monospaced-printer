# Terminal Printer
The purpose of this project is to provide a simple means by which a Java project can print data to a PrintStream.

## Table
The table package facilitates the printing of tabular data in a grid format.  The base class in this package is the TablePrinter class, from which all other printers in the table package extend.  Tables can have titles, headers, and data.

### TablePrinter
The TablePrinter class is the most basic of printers in the table package

### ReflectionTablePrinter
The ReflectionTablePrinter allows for a Collection of Objects to be printed in tabular form.  Fields from those Objects are obtained reflectively. 

## Chart