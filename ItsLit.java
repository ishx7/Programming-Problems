import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by ishani2 on 4/7/17.
 */
public class ItsLit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> likes = new HashSet<>();
        HashSet<String> dislikes = new HashSet<>();
        int numPeople = scanner.nextInt();
        scanner.nextLine();

        for (int k = 0; k <numPeople; k++) {
            String likedDrinks = scanner.nextLine();
            String dislikedDrinks = scanner.nextLine();

            if(!likedDrinks.equals("")) {
                String[] tempLikes = likedDrinks.split(", ");
                boolean cont = true;
                for (int t = 0; t < tempLikes.length && cont; t++) {
                    if (!dislikes.contains(tempLikes[t])) {
                        cont = false;
                    }
                }

                if (cont) {
                    System.out.println("not lit");
                    return;
                }
            }

            if (!dislikedDrinks.equals("")) {
                String[] tempDislikes = dislikedDrinks.split(", ");
                for (int t = 0; t < tempDislikes.length; t++) {
                    dislikes.add(tempDislikes[t]);
                }
            }
        }
        System.out.println("its lit");
    }
}
