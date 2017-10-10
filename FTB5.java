import java.io.*;
import java.util.*;

/**
 * Problem statement:
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=2733
 *
 * The online judge has extremely weak test data, so this solution is accepted.
 * However, there's a bug! If the test data were not ridiculously weak, this
 * code would get wrong answer. Can you find the problem?
 */
public class FTB5 {

    /**
     * Adjacency list graph representation with topsort functionality.
     */
    private static class Graph {

        private int n;
        private ArrayList<ArrayList<Integer>> adjacencyList;
        private int[] inDegree;

        private Graph(int n) {
            this.n = n;
            this.adjacencyList = new ArrayList<ArrayList<Integer>>();
            this.inDegree = new int[n];
            for (int i = 0; i < n; i++) {
                this.adjacencyList.add(new ArrayList<Integer>());
                this.inDegree[i] = 0;
            }
        }

        private void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
            inDegree[v]++;
        }

        private ArrayList<Integer> getTopologicalOrdering() {
            int[] remainingInDegree = new int[n];

            ArrayList<Integer> zeros = new ArrayList<>();
            ArrayList<Integer> sort = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                remainingInDegree[i] = inDegree[i];
                if (remainingInDegree[i] == 0) {
                    zeros.add(i);
                }
            }

            while (!zeros.isEmpty()) {
                int u = zeros.get(zeros.size() - 1);
                zeros.remove(zeros.size() - 1);
                sort.add(u);
                for (int v : adjacencyList.get(u)) {
                    if (--remainingInDegree[v] == 0) {
                        zeros.add(v);
                    }
                }
            }

            return sort;
        }

    }

    public static void main(String[] args) throws IOException {
        FastIO io = new FastIO(System.in, System.out);
        while (true) {
            int n = io.nextInt();
            int m = io.nextInt();
            if (n == 0 && m == 0)
                break;

            // Build a directed graph representing dependencies
            Graph graph = new Graph(n);
            for (int i = 0; i < m; i++) {
                int u = io.nextInt() - 1;
                int v = io.nextInt() - 1;
                graph.addEdge(u, v);
            }

            ArrayList<Integer> answer = graph.getTopologicalOrdering();
            if (answer.isEmpty()) {
                io.println("IMPOSSIBLE");
            } else {
                for (int u : answer) {
                    io.println(Integer.toString(u + 1));
                }
            }
        }

        io.close();
    }

    /**
     * Fast input/output.
     *
     * You can ignore this class. The bug is somewhere above this line!
     */
    private static class FastIO {

        private BufferedReader reader;
        private PrintWriter writer;

        private StringTokenizer tokenizer;

        private FastIO(InputStream in, PrintStream out) {
            this.reader = new BufferedReader(new InputStreamReader(in));
            this.writer = new PrintWriter(new BufferedOutputStream(out));
        }

        private String nextToken() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreElements()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }

            return tokenizer.nextToken();
        }

        private int nextInt() throws IOException {
            return Integer.parseInt(nextToken());
        }

        private void println(String line) {
            writer.println(line);
        }

        private void close() throws IOException {
            reader.close();
            writer.close();
        }

    }

}
