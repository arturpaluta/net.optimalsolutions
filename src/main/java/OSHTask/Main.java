package OSHTask;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static final String RESOURCES = "src/main/resources/";
    public static String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    final static Path csvPath = Paths.get(RESOURCES + "Interview-task-data-osh.csv");
    static String badDataFileName = new SimpleDateFormat("'bad-data-'yyyy-MM-dd-HH-mm'.csv'").format(new Date());
    static Path pathErr = Paths.get(RESOURCES + badDataFileName);


    public static void main(String[] args) throws IOException {
            ParseCSV parseCSV = new ParseCSV(csvPath, pathErr, splitRegex);
            ParseErrorsToCsv parseErr = new ParseErrorsToCsv(pathErr);
            parseCSV.parseCSV(parseErr);
    }
}
