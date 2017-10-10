import java.util.Scanner;

/**
 * Created by ishani2 on 3/4/17.
 */
public class VladikFlights {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numAirports = scanner.nextInt();
        int start = scanner.nextInt() - 1;
        int end = scanner.nextInt() - 1;
        String airportSpots = scanner.next();
        int distance = 0;

        if (airportSpots.charAt(start) != airportSpots.charAt(end)) {
           distance = 1;
        }
        System.out.println(distance);
    }
}
