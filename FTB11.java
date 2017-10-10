import java.io.*;
import java.util.*;

// Help! I'm getting Wrong Answer.
//
// https://icpcarchive.ecs.baylor.edu/index.php?option=onlinejudge&page=show_problem&problem=4150
public class FTB11 {
    // This implementation of a segment tree uses an array of 2 * n + 1 nodes
    // A node's children are at 2 * i and 2 * i + 1
    // Tree nodes are 1 indexed, though the original array was 0 indexed
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

            // set the leaves of the tree
            for (int i = 0; i < n; i++) {
                tree[n + i] = arr[i];
            }

            // set the rest of the tree
            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[2 * i] * tree[2 * i + 1];
            }
        }

        // Find the product between a and b, inclusive, 0 indexed
        public int query(int a, int b) {
            // Convert the bounds to tree indexes
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

        // Update a single value in the tree
        public void update(int index, int value) {
            index += n;
            tree[index] = value;

            // while you have a parent to update
            while (index > 1) {
                index /= 2;
                tree[index] = tree[2 * index] * tree[2 * index + 1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line);

            final int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            final int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            final SegmentTree tree = new SegmentTree(arr);
            final StringBuilder stringBuilder = new StringBuilder();
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                // 1-indexed -> 0-indexed
                final char type = st.nextToken().charAt(0);
                final int a = Integer.parseInt(st.nextToken());
                final int b = Integer.parseInt(st.nextToken());

                if (type == 'C') {
                    tree.update(a - 1, b);
                } else {
                    final int result = tree.query(a - 1, b - 1);
                    if (result == 0) {
                        stringBuilder.append('0');
                    } else if (result < 0) {
                        stringBuilder.append('-');
                    } else {
                        stringBuilder.append('+');
                    }
                }
            }

            System.out.println(stringBuilder.toString());
        }
    }
}
