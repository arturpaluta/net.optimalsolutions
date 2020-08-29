package OSHTask;

import java.sql.SQLException;

public interface CSVtoSQL {
    void createTable(String[] firstLine) throws SQLException;
    void insert (String[] fields) throws SQLException;
    void setFields(String[] fields);
}
