import java.util.Scanner;

/**
 * Created by ishani2 on 3/24/17.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sequenceLength = scanner.nextInt();
        int [] sequence = new int[sequenceLength];
        for (int k = 0; k < sequenceLength; k++) {
            sequence[k] = scanner.nextInt();
        }
        int [] numWays = new int[sequenceLength];
        for (int i = 0; i < sequenceLength; i++) {
            numWays[i] = 1;
        }

        for (int n = 0; n < sequenceLength; n++) {
            for (int m = 0; m < n; m++) {
                if (sequence[m] < sequence[n] && numWays[m] + 1 > numWays[n]) {
                    numWays[n] = numWays[m] + 1;
                }
            }
        }

        int max = 1;
        for (int p = 0; p < numWays.length; p++) {
            if (numWays[p] > max) {
                max = numWays[p];
            }
        }

        System.out.println(max);
    }
}