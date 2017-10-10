import java.util.Scanner;

/**
 * Created by ishani2 on 3/6/17.
 */
public class SantaClausCandies {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder candies = new StringBuilder();
        int count = 0;
        int candyAmount = 1;

        while (count < n) {
            candies.append(candyAmount). append(" ");
            candyAmount++;
            count = count + candyAmount;
        }

        System.out.println(candies.toString());
    }
}
