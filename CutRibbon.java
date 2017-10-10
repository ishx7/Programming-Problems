import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by ishani2 on 3/14/17.
 */
public class CutRibbon {

    private static ArrayList<Integer> ribbonLengths;
    private static int [] values;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();
        values = new int[length + 1];

        ribbonLengths = new ArrayList<>();
        ribbonLengths.add(scanner.nextInt());
        ribbonLengths.add(scanner.nextInt());
        ribbonLengths.add(scanner.nextInt());
        Collections.sort(ribbonLengths);
        Collections.reverse(ribbonLengths);

        System.out.println(maxPieces(length));
    }

    public static int maxPieces(int length) {

        //base case
        values[0] = 0;

        for (int k = 1; k < length; k++) {
            values[k] = Integer.MIN_VALUE;
        }

        for (int n = 0; n < values.length; n++) {
            for (int m = 0; m < ribbonLengths.size(); m++) {
                if (ribbonLengths.get(m) <= n) {
                    int partialSum = values[n - ribbonLengths.get(m)];
                    if (partialSum != Integer.MIN_VALUE && partialSum + 1 > values[n]) {
                        values[n] = partialSum + 1;
                    }
                }
            }
        }
        return values[length];
    }
}
