import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ishani2 on 4/17/17.
 */
public class GregArrays {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrLength = scanner.nextInt();
        int numOps = scanner.nextInt();
        int numQueries = scanner.nextInt();

        //initial array
        int[] arr = new int[arrLength];
        for (int k = 0; k < arrLength; k++) {
            arr[k] = scanner.nextInt();
        }

        //arraylist of arraylists
        ArrayList<ArrayList<Integer>> opsArr = new ArrayList<>();

        for (int q = 0; q < numOps; q++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(scanner.nextInt());
            temp.add(scanner.nextInt());
            temp.add(scanner.nextInt());
            opsArr.add(temp);
        }

        //array that stores how many times to hit each query
        long queryArr [] = new long[numOps + 1];
        for (int m = 0; m < numQueries; m++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            queryArr[start - 1] = queryArr[start - 1] + 1;
            if (end < numQueries) {
                queryArr[end] = queryArr[end] - 1;
            }
        }

        long [] incrArr = new long[arrLength + 1];

        long count = 0;

        for (int y = 0; y < numOps; y++) {
            count += queryArr[y];
            int start = opsArr.get(y).get(0);
            int end = opsArr.get(y).get(1);
            int diff = opsArr.get(y).get(2);
            incrArr[start - 1] = incrArr[start - 1] + (long) diff * count;
            if (end < incrArr.length) {
                incrArr[end] = incrArr[end] - (long) diff * count;
            }
        }

        StringBuilder sb = new StringBuilder();
        long summa = 0;
        for (int w = 0; w < arrLength; w++) {
            summa += incrArr[w];
            long tempOut = summa + (long) arr[w];
            sb.append(tempOut).append(" ");
        }

        System.out.println(sb.toString());
    }


}