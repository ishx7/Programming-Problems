import java.util.Scanner;

/**
 * Created by ishani2 on 3/24/17.
 */
public class DZYSubsequences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numLength = scanner.nextInt();
        int [] seq = new int[numLength + 2];
        seq[0] = 0;
        seq[seq.length - 1] = Integer.MAX_VALUE;
        for (int k = 1; k < numLength; k++) {
            seq[k] = scanner.nextInt();
        }

        int maxLength = 0;
        int count = 0;
        boolean usedChange = false;

        for (int n = 1; n < numLength; n++) {
            while (seq[n] > seq[n - 1]) {
                count++;
                n++;
            }
            if (!usedChange && seq[n - 1] + 1 < seq[n + 1]) {
                count++;
                usedChange = true;
            } else {
                maxLength = count > maxLength ? count : maxLength;
                count = 0;
                usedChange = false;
            }
        }

        System.out.println(maxLength);
    }
}