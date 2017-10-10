import java.io.IOException;
import java.util.*;

/**
 * Created by ishani2 on 2/6/17.
 */
public class Commute {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();


        for (int k = 0; k  < numTests; k++) {

            int nodes = in.nextInt();
            int edges = in.nextInt();

            ArrayList<Node> graph = new ArrayList<>();

            //fill graph with empty nodes
            for (int fill = 0; fill < nodes; fill++) {
                graph.add(new Node(fill, Double.MAX_VALUE, new ArrayList<>()));
            }

            //set start value to 0
            graph.get(0).value = 0.0;

            //create graph
            int startNode;
            int endNode;
            for (int n = 0; n < edges; n++) {
                startNode = in.nextInt() - 1;
                endNode = in.nextInt() - 1;
                Double distance = (1.0 / in.nextInt()) * in.nextInt();
                Node t = new Node(endNode, distance, new ArrayList<>());
                graph.get(startNode).neighbors.add(t);
            }
            Double result = dijkstra(graph, graph.get(0), graph.get(nodes - 1));
            if (result - Math.floor(result) >= .5) {
                System.out.println((int) (double) (result) + 1);
            } else {
                System.out.println((int) (double) (result));
            }
        }
    }

    public static Double dijkstra(ArrayList<Node> graph, Node startNode, Node endNode) {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(startNode);

        while (queue.peek() != null) {
            Node temp = queue.poll();
            visited.add(temp.id);
            for (Node n : temp.neighbors) {
                if (temp.value + n.value < graph.get(n.id).value) {
                    graph.get(n.id).value = temp.value + n.value;
                }
                if (!visited.contains(n.id)) {
                    queue.add(graph.get(n.id));
                }
            }
        }
        return graph.get(endNode.id).value;
    }

    public static class Node implements Comparable<Node> {
        int id;
        Double value;
        ArrayList<Node> neighbors = new ArrayList<>();

        public Node(int id, Double value, ArrayList<Node> neighbors) {
            this.id = id;
            this.value = value;
            this.neighbors = neighbors;
        }

        @Override
        public int compareTo(Node o) {
            return value < o.value ? -1 : 1;
        }
    }
}
