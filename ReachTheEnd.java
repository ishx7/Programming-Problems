import java.awt.*;
import java.util.*;

/**
 * Created by ishani2 on 4/3/17.
 */
public class ReachTheEnd {

    static int w, h, g, e;
    static Set<Point> blocks;
    private static int[][] ways;
    static Set<Point> jumpSpots;
    static HashMap<Point, Point> jumpMap;

    public static void main(String[] args) {
        blocks = new HashSet<>();
        jumpMap = new HashMap<>();
        Scanner scan = new Scanner(System.in);

        w = scan.nextInt();
        h = scan.nextInt();
        scan.next();
        ways = new int[w][h];

        String blockedString = scan.next();
        Scanner scan2 = new Scanner(blockedString);
        while (scan2.hasNextInt()) {
            blocks.add(new Point(scan2.nextInt(), scan2.nextInt()));
        }

        String jumpString = scan.next();
        Scanner scan3 = new Scanner(jumpString);
        while (scan3.hasNextInt()){
            int x1 = scan3.nextInt();
            int y1 = scan3.nextInt();
            int x2 = scan3.nextInt();
            int y2 = scan3.nextInt();
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            jumpMap.put(start, end);
        }

        jumpSpots = jumpMap.keySet();

        for (int i = 0; i < w; i++) {
            ways[i][0] = 1;
        }

        for (int j = 0; j < h; j++) {
            ways[0][j] = 1;
        }

        Iterator iter = blocks.iterator();
         while (iter.hasNext()) {
             Point p = (Point) iter.next();
             ways[p.x][p.y] = 0;
         }

         fill(1, 1);
    }

    public static void fill(int x, int y) {
        int squareSum = 0;
        for (int r = x; r < ways.length; r++) {
            for (int c = y; c < ways[r].length; c++) {
                Point temp = new Point(r, c);
                if (!blocks.contains(temp)) {
                    if (r - 1 >= 0) {
                        squareSum += ways[r - 1][c];
                    }
                    if (c - 1 >= 0) {
                        squareSum += ways[r][c - 1];
                    }

                    if (jumpSpots.contains(temp)) {

                    } else {
                        ways[r][c] = ways[r][c] + squareSum;
                    }
                } else {

                }
            }
        }
    }
}
