# Worksheet Interpreter
This program parses text in a dump from *Powerlink* and refines it to be easily read programmatically.

## Features

  - Scans .acu files dumped from *Powerlink*
  - Removes unnecessary characters
  - Restructures data for future usage
  - Stores data in SQL database so it can be read by the Inventory Manager app.

### Installation

Clone the repository:
```sh
$ git clone https://github.com/Jaymus3/Worksheet-Interpreter
```
You'll also need [MySQL Connector][Mysq] in order for the SQL database interaction to work.  Open the project with your favorite Java editor, such as [Eclipse][Ecl]

### Development

See something that could use improvement?  Great!  Just fork the repo, make your changes, and make a pull request detailing what you fixed up.


   [Rob]: <https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html>
   [Mysq]: <https://www.mysql.com/products/connector/>
   [Ecl]: <https://eclipse.org/>
