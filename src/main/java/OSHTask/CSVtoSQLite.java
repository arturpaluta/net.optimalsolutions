package OSHTask;

import java.sql.*;

public class CSVtoSQLite implements CSVtoSQL {
    static int result;
    // Connection conn = DriverManager.getConnection("jdbc:sqlite:csvtosqlite.db"); // "jdbc:sqlite:D:\\Temp\\testjava.db"
    Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
    Statement st = conn.createStatement();

    public CSVtoSQLite() throws SQLException {
    }

    String sqlValues = "";
    String nameOfFields = "";

    public void setFields(String[] string) {
        StringBuilder sb1 = new StringBuilder(sqlValues);
        for (String s : string) {
            sb1.append("?,");
            sqlValues = sb1.toString();
        }
        sqlValues = sqlValues.substring(0, sqlValues.length() - 1);
    }

    public void createTable(String[] lineOne) throws SQLException {
        StringBuilder sb2 = new StringBuilder(nameOfFields);
        for (String s : lineOne) {
            sb2.append(s + " TEXT, ");
            nameOfFields = sb2.toString();
        }
        nameOfFields = nameOfFields.substring(0, nameOfFields.length() - 2);
        st.execute("CREATE TABLE results (" + nameOfFields + ")");
    }

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
