package ParseCSVtoSQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:"); // "jdbc:sqlite:D:\\Temp\\testjava.db"
            Statement st = conn.createStatement();
        }catch (SQLException e){
            System.out.println("Something went wrong "+ e.getMessage());
        }
    }
}