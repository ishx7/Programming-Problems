import java.awt.Point;
import java.io.*;
import java.util.*;

/**
 * Hmm, I can't figure this out. My code seems to run in time and I get the
 * correct output on the sample, but I'm getting a Wrong Answer (WA) when
 * uploading to the online judge!
 *
 * Problem statement can be found here:
 * https://icpcarchive.ecs.baylor.edu/external/45/4509.pdf
 */
public class FindTheBug4 {
    private static class HauntedHole {
        int x1, y1, x2, y2, t;
        public HauntedHole(int x1, int y1, int x2, int y2, int t) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.t = t;
        }
    }

    static int w, h, g, e;
    static Set<Point> gravestones;
    static List<HauntedHole> portals;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    // return a unique ID for every point in the grid
    private static int pointToInt(int x, int y) {
        return w * x + y;
    }

    // Returns true if input is done
    private static boolean readInput(Scanner scan) {
        gravestones = new HashSet<>();
        portals = new ArrayList<>();

        w = scan.nextInt();
        h = scan.nextInt();
        // Check for end of input
        if (w == 0 || h == 0) {
            return true;
        }

        g = scan.nextInt();
        for (int i = 0; i < g; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();

            gravestones.add(new Point(x, y));
        }

        e = scan.nextInt();
        for (int i = 0; i < e; i++) {
            int y1 = scan.nextInt();
            int x1 = scan.nextInt();
            int y2 = scan.nextInt();
            int x2 = scan.nextInt();
            int t = scan.nextInt();
            portals.add(new HauntedHole(x1, y1, x2, y2, t));
        }

        return false;
    }

    // returns true if a relaxation happened
    private static boolean relax(long[] distance) {
        boolean relaxed = false;

        // There are two types of edges.
        // 1. Edges between adjacent cells in a grid
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // No outgoing edges from a gravestone.
                // John will leave the exit cell immediately, so don't
                // handle outgoing edges from there.
                if (gravestones.contains(new Point(i, j)) || i == h - 1 && j == w - 1) {
                    continue;
                }

                // for every cell, find all adjacent cells
                for (int d = 0; d < 4; d++) {
                    int newI = i + dx[d];
                    int newJ = j + dy[d];

                    // bounds check
                    if (newI < 0 || newI >= h || newJ < 0 || newJ >= w
                            || gravestones.contains(new Point(newI, newJ))) {
                        continue;
                    }

                    // there is an edge of length 1 between (i, j) and (newI, newJ).
                    int vertex1 = pointToInt(i, j);
                    int vertex2 = pointToInt(newI, newJ);

                    if (distance[vertex1] + 1 < distance[vertex2]) {
                        relaxed = true;
                        distance[vertex2] = distance[vertex1] + 1;
                    }
                }
            }
        }

        // 2. Haunted hole edges
        for (HauntedHole portal : portals) {
            int vertex1 = pointToInt(portal.x1, portal.y1);
            int vertex2 = pointToInt(portal.x2, portal.y2);

            if (distance[vertex1] + portal.t < distance[vertex2]) {
                relaxed = true;
                distance[vertex2] = distance[vertex1] + portal.t;
            }
        }

        return relaxed;
    }

    // Return distances to every vertex
    // returns null if there's a negative cycle
    private static long[] bellmanFord() {
        // initialize distances to infinity
        long[] distance = new long[w * h];
        Arrays.fill(distance, 100000L * w * h);

        // 0 distance to the first cell
        distance[0] = 0;

        // iterate |V| - 1 times
        for (int iter = 0; iter < (w * h) - 1; iter++) {
            relax(distance);
        }

        if (relax(distance)) {
            return null;
        } else {
            return distance;
        }
    }

    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);
        while (true) {
            // Read in input
            if (readInput(scan)) {
                break;
            }

            // Run Bellman-Ford
            long[] distance = bellmanFord();
            int target = pointToInt(h - 1, w - 1);

            if (distance == null) {
                System.out.println("Never");
            } else if (distance[target] == 100000L * w * h) {
                System.out.println("Impossible");
            } else {
                System.out.println(distance[target]);
            }
        }
    }
}
