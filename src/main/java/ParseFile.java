import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class ParseFile {
    public static final String RESOURCES = "src/main/resources/";

    public ParseFile() throws ParseException {
    }

    public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
        final NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }
        return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]",""));
    }

    public static void main(final String... args) throws IOException, ParseException {
        //pentru command-line parametru folosesc aceasta secventa
        //final Path path = Paths.get(RESOURCES + args[0]);
        final Path path = Paths.get(RESOURCES + "Interview-task-data-osh.csv");

        BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)));

        final List<String> lines = Files.readAllLines(path);
        lines.remove(0);
        String str1 = lines.get(14);
                for(String s: str1.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)){
            System.out.println(s);
        }

        String[] ar1 = str1.split(",");
        System.out.println(ar1[7]);
        System.out.println(parse(ar1[7], Locale.US));

        //BigDecimal total = new BigDecimal(0);
        float total2 = 0;
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        for (final String line : lines) {
            final String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            //final BigDecimal amount = parse(columns[6], Locale.US);
            Number value = format.parse(columns[6]);

            total2 +=  value.floatValue();
            //total = total.add(amount);
        }
        System.out.println("The total for all transactions is " + total2);
    }

   /* NumberFormat format = NumberFormat.getCurrencyInstance();
    Object value = format.parse("$5.45");*/
}

