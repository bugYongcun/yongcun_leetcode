import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

        if (line.size() == 7)
            return line.get(0) + ", " + line.get(6) + " years old, is from " + line.get(5) + " and is interested in " + line.get(3) + ".";
        else
            return "";
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);

        List<String> result = new ArrayList<>();

        while (sc.hasNext()) {
            String str = sc.nextLine();
            String res = parseCsv(str);
            result.add(res);
        }

        for (String s : result) {
            System.out.println(s);
        }

        sc.close();
    }
}


