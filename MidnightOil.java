import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ishani2 on 2/13/17.
 */
public class MidnightOil {

    private int closestNum = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        long numLines = in.nextInt();
        long kVal = in.nextInt();

        //binary search part
        long low = 0;
        long high = numLines + 1;
        while (low < high - 1) {
            long mid = (low + high) / 2;
            if (works(numLines, kVal, mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        System.out.println(high);
    }

    public static boolean works(long numLines, long kVal, long currentVal) {
        int totalSum = 0;
        int degree = 0;
        while (Math.floor(currentVal / Math.pow(kVal, degree)) > 0) {
            totalSum = totalSum + (int) Math.floor(currentVal / Math.pow(kVal, degree));
            degree++;
        }
        return totalSum < numLines;
    }

}
