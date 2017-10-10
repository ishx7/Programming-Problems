import com.sun.deploy.util.StringUtils;

import java.io.*;

/**
 * Created by ishani2 on 4/8/17.
 */
public class TidyNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("test.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));

        int numTests = Integer.parseInt(br.readLine());

        for (int k = 0; k < numTests; k++) {
            long num = Long.parseLong(br.readLine());
            String numString = Integer.toString((int) num);
            int numLength = Integer.toString((int) num).length();
            int spot = 0;

            if (numLength == 1) {
                pw.println(num);
                pw.close();
            }

            while (spot < numLength && Integer.parseInt(numString.substring(spot, spot + 1)) <
                    Integer.parseInt(numString.substring(spot + 1, spot + 2))) {
                    spot++;
            }

            long firstPart = Long.parseLong(numString.substring(0, spot + 1));
            firstPart--;
            StringBuilder output = new StringBuilder(Integer.toString((int) firstPart));
            for (int z = 0; z < numLength - spot; z++) {
                output.append("9");
            }

            pw.println(output);
            pw.close();

        }
    }
}
