import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by ishani2 on 2/13/17.
 */
public class PolynomialInversion {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();
        for (int k = 0; k < numTests; k++) {

            int degree = in.nextInt();
            long desiredValue = in.nextLong();
            ArrayList<Integer> coefficients = new ArrayList<>();
            for (int j = 0; j <= degree; j++) {
                coefficients.add(in.nextInt());
            }

            //binary search
            long low = -1;
            long high = desiredValue + 1;
            while (low < high - 1) {
                long mid = (low + high) / 2;
                if (works(coefficients, desiredValue, mid)) {
                    low = mid;
                }
                else {
                    high = mid;
                }
            }
            //if low is still -1, no positive answer was found
            if (low < 0) {
                System.out.println("N/A");
            }
            else {
                System.out.println(low);
            }
        }
    }

    public static boolean works (ArrayList<Integer> coefficients, long desiredValue, long currentVal) {
        long totalSum = 0;
        for (int d = 0; d < coefficients.size(); d++) {
            totalSum = totalSum + coefficients.get(d) * (long) (Math.pow(currentVal, d));
        }
        return totalSum <= desiredValue;
    }


}
