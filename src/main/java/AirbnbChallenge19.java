import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirbnbChallenge19 {
    public static String parseCsv(String str) {
        List<String> line = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean quote = false;
        int i = 0;

        while (i < str.length()) {
            char c = str.charAt(i);

            if (c == '"') {
                if (i + 1 < str.length() && str.charAt(i + 1) == '"') {
                    sb.append('"');
                    i++;
                } else {
                    quote = !quote;
                }

            } else if (c == ',') {
                if (quote) {
                    sb.append(',');
                } else {
                    line.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            } else {
                sb.append(c);
            }

            i++;

            if (i == str.length()) {
                if (!sb.toString().equals("")) {
                    line.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }

        return line.toString();

    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String str = "\"1,\"\"\"\"3\"\"\"\"\"";

        List<String> result = new ArrayList<>();

        String res = parseCsv(str);
        result.add(res);

        for (String s : result) {
            System.out.println(s);
        }
    }
}


