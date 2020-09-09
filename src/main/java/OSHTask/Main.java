package OSHTask;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static final String RESOURCES = "src/main/resources/";
    private static String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    private static Path csvPath;
    private static String badDataFileName = new SimpleDateFormat("'bad-data-'yyyy-MM-dd-HH-mm'.csv'").format(new Date());
    private static Path pathErr = Paths.get(RESOURCES + badDataFileName);
    private static Path pathLog = Paths.get(RESOURCES + "logFile.log");

    public Main() throws SQLException {
    }
    private static void setFilename(String[] args){
        String filename;
        if (args.length == 0){
            filename = "Interview-task-data-osh.csv";
        }
        else{
            filename = args[0].toString();
        }
        csvPath = Paths.get(RESOURCES + filename);
    }
    public static void main(String[] args) throws IOException, SQLException {
        setFilename(args);
        ParseCSV parseCSV = new ParseCSV(csvPath, pathErr, splitRegex);
            ParseErrorsToCsv parseErr = new ParseErrorsToCsv(pathErr);
            CSVtoSQLInterface csVtoSQLite = new CSVtoSQL();
            parseCSV.parseCSV(parseErr, csVtoSQLite);
            String log = "Successfully added "+ csVtoSQLite.getResult() + " records, \nFailed records: "
                    +parseErr.getFailedRecords() + "\nTotal number of records: " + (csVtoSQLite.getResult() +parseErr.getFailedRecords()) ;
            System.out.println(log);
            LogFile.log(pathLog, log);

    }
}
