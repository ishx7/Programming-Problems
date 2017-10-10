import java.util.*;

/**
 * Created by ishani2 on 3/6/17.
 */
public class RockFight {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        ArrayList<Integer> blemRocks = new ArrayList<>();
        ArrayList<Integer> ruthRocks = new ArrayList<>();

        for (int t = 0; t < numTests; t++) {
            int numRocks = scanner.nextInt();
            for (int r = 0; r < numRocks; r++) {
                ruthRocks.add(scanner.nextInt());
            }
            for (int b = 0; b < numRocks; b++) {
                blemRocks.add(scanner.nextInt());
            }

            Collections.sort(ruthRocks);
            Collections.sort(blemRocks);

            Collections.reverse(ruthRocks);
            Collections.reverse(blemRocks);

            int rIndex = 0;
            int bIndex = 0;
            int leastWeight = 0;

            while (ruthRocks.size() > 0) {
                if (blemRocks.get(bIndex) > ruthRocks.get(rIndex)) {
                    leastWeight += ruthRocks.get(ruthRocks.size() - 1);
                    ruthRocks.remove(ruthRocks.size() - 1);
                    bIndex++;
                } else {
                    int tempRIndex = rIndex;
                    int currentBSpot = blemRocks.get(bIndex);
                    while (tempRIndex < ruthRocks.size() && ruthRocks.get(tempRIndex) >= currentBSpot) {
                        tempRIndex++;
                    }
                    ruthRocks.remove(tempRIndex - 1);
                    bIndex++;
                }
            }
            System.out.println(leastWeight);

            blemRocks.clear();
        }
    }
}
