package OSHTask;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseCSV {
    private String splitRegex;
    private Path csvPath;  //path to & filename of .csv file
    private Path pathErr; // complete path to error .csv file

    ParseCSV(Path csvPath, Path pathErr, String splitRegex) {
        this.splitRegex = splitRegex;
        this.csvPath = csvPath;
        this.pathErr = pathErr;
    }
    int check = 0;
    private int check(String[] fields) {
        int k = 0;
        while(!"".equals(fields[k]) && k < fields.length-1)
            k++;
            if (k < fields.length-1) check = 0;
            else check = 1;
        /*
        for (int j = 0; j < fields.length; j++)
            if ("".equals(fields[j])){
                 check = 0;
            break;
            }
            else check = 1;
*/
        return 1;
    }

    public void parseCSV(ParseErrors parseErrors) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(csvPath)));
            br.readLine();
            for (int i = 0; i < 25; i++) {
                String[] fields = br.readLine().split(splitRegex, -1);
                check(fields);
                if (check == 0) {
                        //parseErrors;
                        parseErrors.parseErrors(fields);
//
                }
                    else
                        {
                            for (int k = 0; k < fields.length; k++){
                                System.out.print(fields[k] + " ");

                            }
                            System.out.println("");
                        }



            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
