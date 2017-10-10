import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ishani2 on 3/14/17.
 */
public class CoinChange {

    private static ArrayList<Integer> coins;
    private static int [] values;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        coins = new ArrayList<>();
        coins.add(1);
        coins.add(5);
        coins.add(10);
        coins.add(25);
        for (int k = 0; k < numTests; k++) {
            int length = scanner.nextInt();
            values = new int[length + 1];
            System.out.println(numWays(length));
        }
    }

    public static int numWays(int length) {
        //base case
        values[0] = 1;

        for (int n = 0; n < values.length; n++) {
            for (int m = 0; m < coins.size(); m++) {
                    if (coins.get(m) <= n) {
                        int partialSum = values[n - coins.get(m)];
                        //values[n] = values[n] + partialSum;
                        values[n] = (partialSum + values[n]) % 1000000007;
                }
            }
        }
        return values[length];
    }
}
