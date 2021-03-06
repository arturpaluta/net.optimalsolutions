package OSHTask;

import java.sql.*;

public class CSVtoSQL implements CSVtoSQLInterface {
    private static int result;
    public static final String TABLE_PARSE_CSV = ":memory:";
    //"parseCSV.db"; // use this filename (or any name) to check the records in a real database.
    //the database will be created in the root directory of the project // "jdbc:sqlite:parseCSV.db";
    private String url = "jdbc:sqlite:" + TABLE_PARSE_CSV;
    public int getResult(){
        return result;
    }
    public CSVtoSQL() throws SQLException {
            }
    //Trying to connect to a database that doesn't exist actually creates that database
    // starting from JDBC 4.0 and above
    private static String sqlValues = ""; //designed to hold n times "?" (number of columns) for sql statement insert into
    private String nameOfFields = ""; // designed to hold the names of columns, extracted from first header line
    //The method to get the number of columns for sqlValues ("?, ?, ?, ... n times") used in insert into table...


    Connection conn = DriverManager.getConnection(url);  //"jdbc:sqlite:D:\\Temp\\testjava.db"
    PreparedStatement pSt;

    public void setFields(String[] lineOne) throws SQLException {
        StringBuilder sb1 = new StringBuilder(sqlValues);
        for (String s : lineOne) {
            sb1.append("?,");
        }
        sb1.delete(sb1.length()-1, sb1.length());
        sqlValues = sb1.toString();
        pSt = conn.prepareStatement("insert into results values(" + sqlValues + ")");
         }
    //Creating the table, extracting column names from first line

    public void createTable(String[] lineOne) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("DROP TABLE IF EXISTS results");
        StringBuilder sb2 = new StringBuilder(nameOfFields);
        for (String s : lineOne) {
            sb2.append(s + " TEXT, ");
        }
        sb2.delete(sb2.length()-2, sb2.length());
        nameOfFields = sb2.toString();
        st.execute("CREATE TABLE IF NOT EXISTS results (" + nameOfFields + ")");
        st.close();
    }
    //Insert values into table
    public void insert(String[] fields) throws SQLException {
        int i = 1;
        for (String s : fields) {
            pSt.setString(i, s);
            i++;
        }
        int success = pSt.executeUpdate();
        result += success;
        System.out.println(result);

    }

}
