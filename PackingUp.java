import java.util.Scanner;

/**
 * Created by ishani2 on 4/7/17.
 */
public class PackingUp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxWeight = scanner.nextInt();
        int currentWeight = 0;
        int numItems = scanner.nextInt();
        scanner.nextLine();
        for (int k = 0; k < numItems; k++) {
            currentWeight += Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        }
        if (currentWeight <= maxWeight) {
            System.out.println("my man!");
        } else {
            System.out.println("uh, Rick?");
        }
    }
}
