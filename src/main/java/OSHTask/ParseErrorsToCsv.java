package OSHTask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseErrorsToCsv implements ParseErrors  {
    private Path pathErr;
    BufferedWriter bw;
    static int failedRecords;

    public ParseErrorsToCsv(Path pathErr2) throws IOException {
        this.pathErr = pathErr2;
        this.bw = new BufferedWriter(new FileWriter(String.valueOf(pathErr)));
    }

    public void parseErrors(String[] fields) {

        StringBuilder sb = new StringBuilder();
        // Append strings from array
        for (String element : fields) {
            sb.append(element);
            sb.append(",");
        }
        try {
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
            failedRecords++;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    // bw.close();
}
