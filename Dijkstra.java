import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by ishani2 on 2/6/17.
 */
public class Dijkstra {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

            int nodes = in.nextInt();
            int edges = in.nextInt();

            ArrayList<Node> graph = new ArrayList<>();

            //fill graph with empty nodes
            for (int fill = 0; fill < nodes; fill++) {
                graph.add(new Node(fill, Integer.MAX_VALUE, new ArrayList<>(), 0));
            }

            //set start value to 0
            graph.get(0).value = 0;

            //create graph
            int startNode;
            int endNode;
            for (int n = 0; n < edges; n++) {
                startNode = in.nextInt() - 1;
                endNode = in.nextInt() - 1;
                int distance = in.nextInt();
                Node t1 = new Node(endNode, distance, new ArrayList<>(), 0);
                Node t2 = new Node(startNode, distance, new ArrayList<>(), 0);
                graph.get(startNode).neighbors.add(t1);
                graph.get(endNode).neighbors.add(t2);
            }
            Stack<Integer> output = dijkstra(graph, graph.get(0), graph.get(nodes - 1));
            StringBuilder path = new StringBuilder();
            while (!output.isEmpty()) {
                path.append(output.pop()).append(" ");
            }
            System.out.println(path.toString());
        }

    public static Stack<Integer> dijkstra(ArrayList<Node> graph, Node startNode, Node endNode) {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            temp.visited = true;
            for (Node n : temp.neighbors) {
                if (temp.value + n.value < graph.get(n.id).value) {
                    graph.get(n.id).value = temp.value + n.value;
                    graph.get(n.id).parent = temp.id;
                    queue.add(graph.get(n.id));
                }
            }
        }
        //ArrayList<Integer> shortestPath = new ArrayList<>();
        Stack<Integer> shortestPath = new Stack<>();

        if (endNode.visited) {

            int pr = endNode.id;

            while (pr != startNode.id) {
                shortestPath.push(pr + 1);
                pr = graph.get(pr).parent;
            }
            shortestPath.push(startNode.id + 1);

            return shortestPath;
        }
        else {
            shortestPath.add(-1);
            return shortestPath;
        }
    }

    public static class Node implements Comparable<Node> {
        int id;
        int value;
        int parent;
        boolean visited;
        ArrayList<Node> neighbors = new ArrayList<>();

        public Node(int id, int value, ArrayList<Node> neighbors, int parent) {
            this.id = id;
            this.value = value;
            this.neighbors = neighbors;
            this.parent = parent;
            visited = false;
        }

        @Override
        public int compareTo(Node o) {
            return value < o.value ? -1 : 1;
        }
    }
}
