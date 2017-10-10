import java.io.*;
import java.util.*;

public class DynamicGCD {

    public static class SegmentTree {
        final static int DEFAULT = 1;
        int n;
        int[] tree;

        private int getSign(int x) {
            if (x == 0) return 0;
            else if (x < 0) return -1;
            else return 1;
        }

        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[2 * n + 1];

            for (int i = 0; i < n; i++) {
                tree[n + i] = arr[i];
            }

            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[2 * i] * tree[2 * i + 1];
            }
        }

        public int query(int a, int b) {
            a += n;
            b += n;

            int left = DEFAULT;
            int right = DEFAULT;

            while (a <= b) {
                if (a % 2 == 1) {
                    left *= tree[a];
                    a += 1;
                }

                if (b % 2 == 0) {
                    right *= tree[b];
                    b -= 1;
                }

                a /= 2;
                b /= 2;
            }

            return left * right;
        }

        public void update(int index, int value) {
            index += n;
            tree[index] = value;

            while (index > 1) {
                index /= 2;
                tree[index] = tree[2 * index] * tree[2 * index + 1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();

        for (int t = 0; t < numTests; t++) {
            int numElements = scanner.nextInt();
            int numQueries = scanner.nextInt();
            int [] arr = new int[numElements];

            for (int k = 0; k < arr.length; k++) {
                arr[k] = scanner.nextInt();
            }

            SegmentTree tree = new SegmentTree(arr);
            StringBuilder sb = new StringBuilder();

            ArrayList<ArrayList<Integer>> queriesArr = new ArrayList<>();

            for (int q = 0; q < numQueries; q++) {
                ArrayList<Integer> temp = new ArrayList<>();
                int queryType = scanner.nextInt();
                temp.add(queryType);
                temp.add(scanner.nextInt());
                temp.add(scanner.nextInt());
                queriesArr.add(temp);
            }

            if (numElements == 1) {
                System.out.println(arr[0]);
                return;
            }
        }

    }

    public static int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }
}
