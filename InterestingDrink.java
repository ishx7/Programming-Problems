import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Created by ishani2 on 2/9/17.
 */
public class InterestingDrink {

    private static ArrayList<Integer> storePrices = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        int numShops = in.nextInt();
        for (int k = 0; k < numShops; k++) {
            storePrices.add(in.nextInt());
        }

        Collections.sort(storePrices);

        int numDays = in.nextInt();
        for (int j = 0; j < numDays; j++) {
            System.out.println(lessEqualSearch(in.nextInt()));
        }
    }

    public static int lessEqualSearch(int priceLimit) {
        int low = 0;
        int high = storePrices.size() - 1;
        int mid = (low + high) / 2;

        if (priceLimit < storePrices.get(low)) {
            return 0;
        }
        else if (priceLimit >= storePrices.get(high)) {
            return storePrices.size();
        }
        else {

            while (low != high - 1) {

                if (priceLimit < storePrices.get(mid)) {
                    high = mid;
                    mid = (low + high) / 2;
                } else {
                    low = mid;
                    mid = (low + high) / 2;
                }
            }
            return low + 1;
        }
    }
}
