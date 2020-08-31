# net.optimalsolutions
To run the application you should run the main method in Main class.
I used Maven to create the project and inserted SQLite dependencies. 
No DLLs needed, just Maven needs to pull the dependencies. 
I had to parse comma separated values, but column E had commas inside its double quoted value, so I had to do some workaround to parse these values correctly.
Regarding the performance issue, I have chosen to read the CSV file line by line using BufferedReader,
checking the record for errors and inserting it into the corresponding file: the errors in error-CSV file and valid records into the database.
Implementing the method for checking each record for errors (the right number of fields),
I have discovered that I can not only have missing data, but some records had extra columns (I appreciated the joke :) 
If you found me Keep up the good work!). So, I extended the error checking method for extra data too.
Regarding the database, I have created a method that dynamically creates a table based on results parsed from the first line of given CSV,
which contains the column header names. So, we can reuse the code for another CSV file with different column count.
The column count and their names are used to create the table, with TEXT data type for all of them. Since this is a In-memory database,
the program creates the table each time. Trying to connect to a database that doesn't exist actually creates that database starting from JDBC 4.0 and above,
so I didn’t use the old fashioned style for creating the connection to the database.
If I would have to work on a physical database I would include a check for an existing  database and table.
The same approach I took when I have created PreparedStatement clause for inserting the data into table.
We have to use a question mark per column for each record, separated with comma (INSERT INTO results VALUES(?,?,?,….?)) .
I have created a method that creates dynamically, based on first line from CSV (which holds the information about the number of columns in the database),
a string whit the right number of question marks and assigned it to a String variable which I used inside the statement.
This approach also is about code flexibility and reusability.
I used In-memory database. To check the results in a real database, just replace the url "jdbc:sqlite::memory:" with  "jdbc:sqlite:parseCSV.db" in CSVtoSQL class.
I haven’t implemented a check for an existing table, so for the second run to succeed, delete the database parseCSV.db in the root folder of the project.
