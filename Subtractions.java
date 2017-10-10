import java.util.Scanner;

/**
 * Created by ishani2 on 4/3/17.
 */
public class Subtractions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        for (int k = 0; k  < numTests; k++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int count = 0;
            int temp = 0;
            while (a > 0 && b > 0) {
                if (a > b) {
                    temp = a / b;
                    count += temp;
                    a = a - b * temp;

                } else {
                    temp = b / a;
                    count += temp;
                    b = b - a * temp;
                }
            }
            System.out.println(count);
        }
    }
}
