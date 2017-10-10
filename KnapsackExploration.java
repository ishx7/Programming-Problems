import java.util.*;

/**
 * Created by ishani2 on 3/28/17.
 */
public class KnapsackExploration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();

        for(int k = 0; k < numTests; k++) {
            int numJewels = scanner.nextInt();
            int capacity = scanner.nextInt();

            int [] values = new int[numJewels];
            int [] weights = new int[numJewels];
            ArrayList<Jewel> jewels = new ArrayList<>();

            for (int j = 0; j < numJewels; j++) {
                values[j] = scanner.nextInt();
                weights[j] = scanner.nextInt();
                jewels.add(new Jewel(values[j], weights[j]));
            }

            StringBuilder output = new StringBuilder(regularKnapsack(capacity, values, weights) + " ");
            output.append(cloneKnapsack(capacity, jewels)).append(" ");
            output.append(laserCutterKnapsack(capacity, jewels)).append(" ");
            //output.append(cloneAndLaserKnapsack(capacity, jewels)).append(" ");
            System.out.print(output.toString());
            double d = cloneAndLaserKnapsack(capacity, jewels);
            System.out.printf("%.2f", d);
        }
    }

    public static int regularKnapsack(int capacity, int [] values, int [] weights) {
        int [][] matrix = new int[values.length + 1][capacity + 1];
        for (int n = 0; n <= values.length; n++) {
            for (int m = 0; m <= capacity; m++) {
                if (n == 0 || m == 0) {
                    matrix[n][m] = 0;
                } else if (m >= weights[n - 1]) {
                    matrix[n][m] = (Math.max(matrix[n - 1][m], values[n - 1] + matrix[n - 1][m - weights[n - 1]]));
                } else {
                    matrix[n][m] = matrix[n - 1][m];
                }
            }
        }
        return matrix[values.length][capacity];
    }

    public static int cloneKnapsack(int capacity, ArrayList<Jewel> jewels) {
        int[] best = new int[capacity + 1];
        best[0] = 0;
        for (int c = 1; c < capacity; c++) {
            for (Jewel j : jewels) {
                if (j.weight <= c) {
                    best[c] = Math.max(j.value + best[c - j.weight], best[c]);
                }
            }
        }
        return best[capacity];
    }

    public static double laserCutterKnapsack(int capacity, ArrayList<Jewel> jewels) {
        Collections.sort(jewels, Comparator.reverseOrder());
        double value = 0.0;
        for (Jewel j : jewels) {
            double take = Math.min(capacity, j.weight);
            capacity -= take;
            value += take * ((double) j.value / j.weight);
        }

        return value;
    }

    public static double cloneAndLaserKnapsack(int capacity, ArrayList<Jewel> jewels) {
        Collections.sort(jewels, Comparator.reverseOrder());
        return capacity * ((double) jewels.get(0).value / jewels.get(0).weight);
    }
}

class Jewel implements Comparable<Jewel> {
    public int value, weight;

    public Jewel(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int compareTo(Jewel jewel) {
        return this.value * jewel.weight - this.weight * jewel.value;
    }
}
