package OSHTask;

import java.io.*;
import java.nio.file.Path;
import java.sql.SQLException;

public class ParseCSV {
    private String splitRegex;
    private Path csvPath;  //path to & filename of .csv file
    private Path pathErr; // complete path to error .csv file
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
        if (k < fields.length - 1 || fields.length>tableSize) check = 0;
        else check = 1;
    }

    public void parseCSV(ParseErrors parseErrors, CSVtoSQL csVtoSQL) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(csvPath)));
            String[] s1 = br.readLine().split(splitRegex, -1);
            this.tableSize = s1.length;
            csVtoSQL.createTable(s1);
            csVtoSQL.setFields(s1);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(splitRegex, -1);
            /*for (int i = 0; i < 25; i++) {
                String[] fields = br.readLine().split(splitRegex, -1);*/
                check(fields);
                if (check == 0) {     //parseErrors;
                    parseErrors.parseErrors(fields);
                } else {
                    csVtoSQL.insert(fields);
                }
            }

            br.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
