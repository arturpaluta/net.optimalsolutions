package OSHTask;

import java.sql.*;

public class CSVtoSQLite implements CSVtoSQL {
    static int result;
    public CSVtoSQLite() throws SQLException {
    }
    // Connection conn = DriverManager.getConnection("jdbc:sqlite:csvtosqlite.db"); // "jdbc:sqlite:D:\\Temp\\testjava.db"
    //Trying to connect to a database that doesn't exist actually creates that database
    // starting from JDBC 4.0 and above
    Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
    Statement st = conn.createStatement();
    String sqlValues = ""; //designed to hold n times "?" (number of columns) for sql statement insert into
    String nameOfFields = ""; // designed to hold the names of columns, extracted from first header line

    //The method to get the number of columns for sqlValues ("?, ?, ?, ... n times") used in insert into table...
    public void setFields(String[] string) {
        StringBuilder sb1 = new StringBuilder(sqlValues);
        for (String s : string) {
            sb1.append("?,");
            sqlValues = sb1.toString();
        }
        sqlValues = sqlValues.substring(0, sqlValues.length() - 1);
    }
    //Creating the table, extracting column names from first line
    public void createTable(String[] lineOne) throws SQLException {
        StringBuilder sb2 = new StringBuilder(nameOfFields);
        for (String s : lineOne) {
            sb2.append(s + " TEXT, ");
            nameOfFields = sb2.toString();
        }
        nameOfFields = nameOfFields.substring(0, nameOfFields.length() - 2);
        st.execute("CREATE TABLE results (" + nameOfFields + ")");
    }
    //Insert values into table
    public void insert(String[] fields) throws SQLException {

        PreparedStatement pSt = conn.prepareStatement("insert into results values(" + sqlValues + ")");
        int i = 1;
        for (String s : fields) {
            pSt.setString(i, s);
            i++;
        }
        int success = pSt.executeUpdate();
        result += success;
        System.out.println(result);

    }
    //conn.close();

}
