import java.util.*;

import java.util.Scanner;

/**
 * Created by ishani2 on 1/30/17.
 */
public class GraphSummaries {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();
        int numNodes;
        int numEdges;
        int startNode;
        int distance;
        StringBuilder output = new StringBuilder();
        for (int k = 0 ; k < numTests; k++) {

            numNodes = in.nextInt();
            numEdges = in.nextInt();
            startNode = in.nextInt();
            distance = in.nextInt();

            int numComponents = 0;
            int reachableNodes = 0;

            ArrayList<HashSet<Integer>> graph = new ArrayList<>();
            for (int n = 0; n < numNodes + 1; n++) {
                graph.add(new HashSet<>());
            }

            for (int i = 0; i < numEdges; i++) {
                int node1 = in.nextInt();
                int node2 = in.nextInt();

                graph.get(node1).add(node2);
                graph.get(node2).add(node1);
            }

            HashSet<Integer> visited = new HashSet<>();
            Queue<Integer> nodeQueue = new ArrayDeque<>();

            for (int g = 1; g <= numNodes; g++) {
                if (!visited.contains(g)) {
                    nodeQueue.add(g);
                    visited.add(g);

                    while (nodeQueue.peek() != null) {
                        for (int iter : graph.get(nodeQueue.peek())) {
                            if (!visited.contains(iter)) {
                                nodeQueue.add(iter);
                                visited.add(iter);
                            }
                        }
                        nodeQueue.poll();
                    }
                    numComponents++;
                }
            }

            Queue<Integer> reachable = new ArrayDeque<>();
            reachable.add(startNode);

            HashSet<Integer> visitedReachables = new HashSet<>();
            visitedReachables.add(startNode);

            if (distance == 1) {
                reachableNodes = graph.get(startNode).size() + 1;
            }
            else {

                int start = 0;

                for (int s = 0; s < distance; s++) {
                    while (reachable.peek() != null) {
                        for (int iter : graph.get(reachable.peek())) {
                            if (!visitedReachables.contains(iter)) {
                                reachable.add(iter);
                                visitedReachables.add(iter);
                            }
                        }
                        reachable.poll();
                    }
                }
                reachableNodes = visitedReachables.size();
            }
            output.append(numComponents).append(" ").append(reachableNodes).append("\n");
        }
        System.out.println(output.toString());
    }
}
