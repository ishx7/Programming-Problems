import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by ishani2 on 4/10/17.
 */
public class HanSolo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTroopers = scanner.nextInt();
        Point start = new Point(scanner.nextInt(), scanner.nextInt());
        HashSet<Fraction> directions = new HashSet<>();


        for (int n = 0; n < numTroopers; n++) {
            int tempX = scanner.nextInt() - start.x;
            int tempY = scanner.nextInt() - start.y;
            int tempGCD = GCD(tempX, tempY);
            Fraction tempF = new Fraction(tempX / tempGCD, tempY / tempGCD);
            Fraction tempRatio = tempX == 0 ? new Fraction(Integer.MAX_VALUE, 1) : tempF;
            directions.add(tempRatio);
        }

        System.out.println(directions.size());
    }

    public static int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b,a % b);
    }
}

class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Fraction other = (Fraction) o;
            if (this.numerator * other.denominator == this.denominator * other.numerator)
                return true;
            else if (this.numerator == other.numerator && this.denominator == other.denominator) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Integer.hashCode(numerator) + Integer.hashCode(denominator);
    }
}