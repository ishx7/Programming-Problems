import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by ishani2 on 2/22/17.
 */
public class VideoGame {

    public static class Edge {
        private int start;
        private int destination;
        private int weight;

        public Edge(int start, int destination, int weight) {
            this.start = start;
            this.destination = destination;
            this.weight = weight;
        }

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Integer> nodeMap;
        ArrayList<Edge> edgeList;

        int numTests = scanner.nextInt();

        for (int k = 0; k < numTests; k++) {
            int numNodes = scanner.nextInt();
            int numEdges = scanner.nextInt();
            int startingValue = scanner.nextInt();

            //initialize hashmap of nodes
            nodeMap = new HashMap<>();
            edgeList = new ArrayList<>();
            for (int t = 0; t < numNodes; t++) {
                nodeMap.put(t + 1, Integer.MIN_VALUE);
            }
            nodeMap.put(1, startingValue);


            for (int a = 0; a < numEdges; a++) {
                int start = scanner.nextInt();
                int destination = scanner.nextInt();
                int edgeWeight = scanner.nextInt();
                edgeList.add(new Edge(start, destination, edgeWeight));
            }


            for (int i = 1; i < numNodes; i++) {
                for (Edge edge : edgeList) {
                    int start = edge.start;
                    int destination = edge.destination;
                    int weight = edge.weight;
                    if (nodeMap.get(start) != Integer.MIN_VALUE && nodeMap.get(start) + weight > nodeMap.get(destination)) {
                        nodeMap.put(destination, nodeMap.get(start) + weight);
                    }
                }
            }

            boolean infinity = false;
            for (int j = 0; j < numEdges; j++) {
                int u = edgeList.get(j).start;
                int v = edgeList.get(j).destination;
                int weight = edgeList.get(j).weight;
                if (nodeMap.get(u) != Integer.MIN_VALUE && nodeMap.get(u) + weight > nodeMap.get(v)) {
                    infinity = true;
                    break;
                }
            }

            if (infinity) {
                System.out.println("infinity");
            } else if (nodeMap.get(numNodes) == Integer.MIN_VALUE) {
                System.out.println("-1");
            } else {
                System.out.println(nodeMap.get(numNodes));
            }
        }

    }
}
