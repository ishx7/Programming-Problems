import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.util.Scanner;

public class SerialTime {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("test.in"));
        int depthK = in.nextInt();
        int lengthN = in.nextInt();
        int widthM = in.nextInt();
        in.nextLine();
        in.nextLine();

        ArrayList<String[][]> levels = new ArrayList<>();

        //put empty matrices in arraylist
        for (int empty = 0; empty < depthK; empty++) {
            String[][] mat = new String[lengthN][widthM];
            levels.add(mat);
        }

        //read input into arraylist of matrices
        for (int k = 0; k < depthK; k++) {
            for (int row = 0; row < lengthN; row++) {
                String[] rowEntry = in.nextLine().split("");
                for (int col = 0; col < widthM; col++) {
                    levels.get(k)[row][col] = rowEntry[col];
                }
            }
            in.nextLine();
        }
        int xCoord = in.nextInt() - 1;
        int yCoord = in.nextInt() - 1;
        int zCoord = 0;

        boolean[][][]visited = new boolean[depthK][lengthN][widthM];
        Queue<point3D> queue = new ArrayDeque<>();
        point3D startCoord = new point3D(xCoord, yCoord, zCoord);

        queue.add(startCoord);
        point3D addCoord;
        int x;
        int y;
        int z;

        while (!queue.isEmpty()) {

            point3D curPoint = queue.remove();

            x = curPoint.x;
            y = curPoint.y;
            z = curPoint.z;

            if(visited[z][x][y]) {
                continue;
            }

            visited[z][x][y] = true;

            if (x - 1 >= 0 && levels.get(z)[x - 1][y].equals(".")) {
                addCoord = new point3D(x - 1, y, z);
                if (!visited[z][x - 1][y]) {
                    queue.add(addCoord);
                }
            }
            if (x + 1 < lengthN && levels.get(z)[x + 1][y].equals(".")) {
                addCoord = new point3D(x + 1, y, z);
                if (!visited[z][x + 1][y]) {
                    queue.add(addCoord);
                }
            }
            if (y - 1 >= 0 && levels.get(z)[x][y - 1].equals(".")) {
                addCoord = new point3D(x, y - 1, z);
                if (!visited[z][x][y - 1]) {
                    queue.add(addCoord);
                }
            }

            if (y + 1 < widthM && levels.get(z)[x][y + 1].equals(".")) {
                addCoord = new point3D(x, y + 1, z);
                if (!visited[z][x][y + 1]) {
                    queue.add(addCoord);
                }
            }
            if (z - 1 >= 0 && levels.get(z - 1)[x][y].equals(".")) {
                addCoord = new point3D(x, y, z - 1);
                if (!visited[z - 1][x][y]) {
                    queue.add(addCoord);
                }
            }
            if (z + 1 < depthK && levels.get(z + 1)[x][y].equals(".")) {
                addCoord = new point3D(x, y, z + 1);
                if (!visited[z + 1][x][y]) {
                    queue.add(addCoord);
                }
            }

        }

        int count = 0;

        for (int i = 0; i < depthK; i++) {
            for (int j = 0; j < lengthN; j++) {
                for (int k = 0; k < widthM; k++) {
                    if (visited[i][j][k]) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    public static class point3D {
        int x;
        int y;
        int z;

        public point3D (int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
