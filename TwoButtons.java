import java.util.*;
import java.io.*;

public class TwoButtons {
    public static void main (String [] args) {

        Scanner sc = new Scanner(System.in);
        String stringEntry = sc.nextLine();
        String [] arrayNM = stringEntry.split(" ");

        //assign a temporary value of n and a the value of m
        int n = Integer.parseInt(arrayNM[0]);
        int tempM = Integer.parseInt(arrayNM[1]);

        //keep track of number of times a button is pressed
        int buttonPresses = 0;

        while (tempM != n) {

            if (tempM < n) {
                //press blue button
                tempM++;
                buttonPresses++;
            }
            else {
                if (tempM % 2 == 0 && tempM > n) {
                    tempM = tempM / 2;
                    buttonPresses++;
                }
                else {
                    tempM++;
                    buttonPresses++;
                }
            }
        }
        System.out.println(buttonPresses);
    }
}

 