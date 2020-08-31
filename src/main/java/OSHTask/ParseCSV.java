package OSHTask;

import java.io.*;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Arrays;

public class ParseCSV {
    private final String splitRegex;
    private final Path csvPath;  //path to & filename of .csv file
    private final Path pathErr; // complete path to error .csv file
    private static int tableSize = 0;

    ParseCSV(Path csvPath, Path pathErr, String splitRegex) {
        this.splitRegex = splitRegex;
        this.csvPath = csvPath;
        this.pathErr = pathErr;
    }

    int check = 0;

    private void check(String[] fields) {
        int k = 0;
        while (!"".equals(fields[k]) && k < fields.length - 1)
            k++;
        if (k < fields.length - 1)
            check = 0;
        else check = 1;
    }

    public void parseCSV(ParseErrors parseErrors, CSVtoSQLInterface csVtoSQL) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(String.valueOf(csvPath)));
            String[] s1 = br.readLine().split(splitRegex, -1);
            tableSize = s1.length;
            csVtoSQL.createTable(s1);
            csVtoSQL.setFields(s1);
            String line;
            while ((line = br.readLine()) != null) {  //while ((line = br.readLine()) != null)
                String[] fields = line.split(splitRegex, -1);
                if (fields.length == tableSize)
                    check(fields);
                if (check == 0) {     //parseErrors;
                    parseErrors.parseErrors(fields);
                } else {
                    csVtoSQL.insert(fields);
                }
                check = 0;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
