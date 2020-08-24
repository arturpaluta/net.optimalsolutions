package ParseCSVtoSQLite;
import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class ParseCSV {
    public static final String RESOURCES = "src/main/resources/";
    public static String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    public static NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
    static String B;
    static String A;
    static String C;
    static String D;
    static String E;
    static String F;
    static float G;
    static boolean H;
    static boolean I;
    static String J;



    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        final Path path = Paths.get(RESOURCES + "Interview-task-data-osh.csv");
        String fileName = new SimpleDateFormat("'bad-data-'yyyy-MM-dd-HH-mm'.csv'").format(new Date());
        final Path pathErr = Paths.get(RESOURCES + fileName);
        String line = "";
        BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(pathErr)));

                try{
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)));
            br.readLine();


            for(int i=0;i<20;i++){
                String[] fields=br.readLine().split(splitRegex, -1);
                if("".equals(fields[0]) || "".equals(fields[1]) || "".equals(fields[2]) || "".equals(fields[3]) || "".equals(fields[4])
                || "".equals(fields[5]) || "".equals(fields[6]) || "".equals(fields[7])
                || "".equals(fields[8]) || "".equals(fields[9]))
                {   StringBuilder sb = new StringBuilder();
                    // Append strings from array
                    for (String element : fields) {
                        sb.append(element);
                        sb.append(",");
                    }
                    //sb.append("\n");
                    bw.write(sb.toString());
                    bw.newLine();
                    bw.flush();
                    continue;
                }else{
                    A = fields[0];
                    B = fields[1];
                    C = fields[2];
                    D = fields[3];
                    E = fields[4];
                    F = fields[5];
                    G = format.parse(fields[6]).floatValue();
                    H = Boolean.parseBoolean(fields[7]);
                    I = Boolean.parseBoolean(fields[8]);
                    J = fields[9];
                }

                System.out.println(A + " " + B + " " + C + " " + D + " " + F + " " + G + " " + H  + " " + I  + " " + J);


            }
            br.close();
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();}

    }
}
