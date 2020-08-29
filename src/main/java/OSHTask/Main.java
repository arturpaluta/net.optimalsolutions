package OSHTask;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static final String RESOURCES = "src/main/resources/";
    static String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    static Path csvPath = Paths.get(RESOURCES + "Interview-task-data-osh.csv");
    static String badDataFileName = new SimpleDateFormat("'bad-data-'yyyy-MM-dd-HH-mm'.csv'").format(new Date());
    static Path pathErr = Paths.get(RESOURCES + badDataFileName);
    //static Path sqlPath = Paths.get(RESOURCES + "sqlite.db");


    public static void main(String[] args) throws IOException, SQLException {
            ParseCSV parseCSV = new ParseCSV(csvPath, pathErr, splitRegex);
            ParseErrorsToCsv parseErr = new ParseErrorsToCsv(pathErr);
            CSVtoSQL csVtoSQLite = new CSVtoSQLite();
            parseCSV.parseCSV(parseErr, csVtoSQLite);
        System.out.println("Successfully added "+ CSVtoSQLite.result + " records");
    }
}
