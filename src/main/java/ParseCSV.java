import java.io.*;
import java.lang.annotation.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class ParseCSV {
        public static final String RESOURCES = "src/main/resources/";



    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        final Path path = Paths.get(RESOURCES + "Interview-task-data-osh.csv");

        String line = "";
        String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        try{
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)));
            br.readLine();
            //for(line = br.readLine(); line != null; line = br.readLine()); // equivalent to while()
            while((line = br.readLine())!=null){
                String[] fields=line.split(splitRegex, -1);
                if(fields[0]!=null & fields[1]!=null & fields[2]!=null & fields[3]!=null & fields[4]!=null
                        & fields[5]!=null & fields[6]!=null & fields[7]!=null
                        & fields[8]!=null & fields[9]!=null) {
                    String A = fields[0];
                    String B = fields[1];
                    String C = fields[2];
                    String D = fields[3];
                    String E = fields[4];
                    String F = fields[5];
                    float G = format.parse(fields[6]).floatValue();
                    boolean H = Boolean.parseBoolean(fields[7]);
                    boolean I = Boolean.parseBoolean(fields[8]);
                    String J = fields[9];
                } else continue;



            }
            br.close();
        }
            catch (IOException e) {
                e.printStackTrace();}

    }
}
