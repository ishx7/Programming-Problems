import java.util.*;

/**
 * Created by ishani2 on 2/27/17.
 */
public class RoadRepairs {

    private static ArrayList<HashSet<Integer>> graph;
    private static ArrayList<Integer> circuitPath;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numTests = scanner.nextInt();
        graph = new ArrayList<>();
        circuitPath = new ArrayList<>();

        for (int t = 0; t < numTests; t++) {
            int numNodes = scanner.nextInt();
            int numEdges = scanner.nextInt();

            //instantiate arraylist of nodes
            for (int i = 0; i < numNodes; i++) {
                graph.add(new HashSet<>());
            }

            //add edges to arraylist of nodes and hashset of edges
            for (int e = 0; e < numEdges; e++) {
                int start = scanner.nextInt() - 1;
                int end = scanner.nextInt() - 1;
                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            //see if any edges are odd-degree
            int countOdds = 0;
            int ifOddStartHere = -1;
            for (int n = numNodes - 1; n >= 0; n--) {
                if (graph.get(n).size() % 2 == 1) {
                    countOdds++;
                    ifOddStartHere = n;
                }
            }

            int currentVertex = 0;

            //if there are odd-degree vertices and not 2 of them then no Euler path exists
            if (countOdds != 2 && countOdds != 0) {
                System.out.println("-1");
                continue;
            } else if (countOdds == 2) {
                currentVertex = ifOddStartHere;
            }

            //call hierholzer, modifies global variables
            hierholzer(currentVertex);

            StringBuilder path = new StringBuilder();

            //if not all edges are seen
            if (numEdges != circuitPath.size() - 1) {
                System.out.println("-1");
                continue;
            } else {
                //build string from back
                for (int p = circuitPath.size() - 1; p >= 0; p--) {
                    path.append(circuitPath.get(p)).append(" ");
                }
            }
            System.out.println(path.toString());
        }
    }

    public static void hierholzer(int currentVertex) {
        while (!graph.get(currentVertex).isEmpty()) {
            int newCurrent = graph.get(currentVertex).iterator().next();
            graph.get(currentVertex).remove(newCurrent);
            graph.get(newCurrent).remove(currentVertex);
            hierholzer(newCurrent);
        }
        circuitPath.add(currentVertex + 1);
    }

    public static class Edge {
        private int start;
        private int end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
