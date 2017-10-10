import java.util.*;
import java.io.*;

public class GravityFlip {
    public static void main (String [] args) {

        Scanner sc = new Scanner(System.in);
        String rowsEntry = sc.nextLine();
        String originalHeightsEntry = sc.nextLine();
        int rows = Integer.parseInt(rowsEntry);
        String [] heightsEntry = originalHeightsEntry.split(" ");
        ArrayList<Integer> heights = new ArrayList<Integer>();

        //convert string to integer of heights in unsorted array
        for (int k = 0; k < heightsEntry.length; k++) {
            heights.add(Integer.parseInt(heightsEntry[k]));
        }

        Collections.sort(heights);

        String sortedHeights = "";

        for (int i = 0; i < heights.size(); i++) {
            sortedHeights = sortedHeights + heights.get(i) + " ";
        }

        System.out.println(sortedHeights);
    }
}

