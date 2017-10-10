import javafx.beans.binding.StringBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class hunkydunk {

    public static void main(String [] args) {
        String s = "indeed     ";
        s = replaceSpace(s);
        System.out.println(s);

        int [][] matrix = new int[][]{
                {1,2,3},
                {5,6,7},
                {9,0,1},
                {3,4,5}
        };
        System.out.println(matrix[0].length);
    }

    public static String replaceSpace(String str) {

        StringBuilder sb = new StringBuilder();
        String[] split = str.split(" ");
        for (int k = 0; k < split.length; k++) {
            sb.append(split[k]);
            if (k < split.length - 1) {
                sb.append("%20");
            }
        }

        return sb.toString();
    }
}
