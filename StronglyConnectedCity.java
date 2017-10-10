import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by ishani2 on 2/26/17.
 */
public class StronglyConnectedCity {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        scanner.nextLine();
        String horizontalDirections = scanner.nextLine();
        String verticalDirections = scanner.nextLine();

        Node [] graph = new Node[rows * columns];
        for (int k = 0; k < graph.length; k++) {
            graph[k] = new Node(k, new ArrayList<>());
        }


        for (int n = 0; n < graph.length; n++) {

            //rows
            if (horizontalDirections.charAt(n / columns) == '>') {
                if ((n + 1) % columns != 0) {
                    graph[n].getNeighbors().add(graph[n + 1]);
                }
            } else {
                if (n % columns != 0) {
                    graph[n].getNeighbors().add(graph[n - 1]);
                }
            }

            //columns
            if (verticalDirections.charAt(n % columns) == 'v') {
                if (n + columns < graph.length) {
                    graph[n].getNeighbors().add(graph[n + columns]);
                }
            } else {
                if (n - columns >= 0) {
                    graph[n].getNeighbors().add(graph[n - columns]);
                }
            }
        }

        int count = 0;
        for (int s = 0; s < graph.length; s++) {
            for (int d = 0; d < graph.length; d++) {
                if (s != d) {
                    if (!isReachable(graph[s], graph[d], rows, columns)) {
                        count++;
                    }
                }
            }
        }
        if (count == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean isReachable(Node start, Node dest, int rows, int columns) {
        LinkedList<Node> temp;

        boolean visited[] = new boolean[rows * columns];

        LinkedList<Node> queue = new LinkedList<>();

        visited[start.getID()] = true;
        queue.add(start);
        Iterator<Node> i;

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            start = queue.poll();

            Node n;
            i = start.getNeighbors().listIterator();

            while (i.hasNext()) {
                n = i.next();

                if (n == dest)
                    return true;

                if (!visited[n.getID()]) {
                    visited[n.getID()] = true;
                    queue.add(n);
                }
            }
        }
        return false;
    }


    public static class Node {
        int id;
        ArrayList<Node> neighbors = new ArrayList<>();

        public Node(int id, ArrayList<Node> neighbors) {
            this.id = id;
            this.neighbors = neighbors;
        }

        public int getID() {
            return id;
        }

        public ArrayList<Node> getNeighbors() {
            return neighbors;
        }

        @Override
        public boolean equals(Object node) {
            if (node instanceof Node) {
                return this.id == ((Node) node).id;
            }
            return false;
        }
    }
}
