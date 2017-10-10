import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by ishani2 on 2/16/17.
 */
public class PingPong {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Interval> intervalsList = new ArrayList<>();
        int numQueries = scanner.nextInt();
        HashSet<Interval> visited = new HashSet<>();
        Queue<Interval> queue = new ArrayDeque<>();

        for (int k = 0; k < numQueries; k++) {

            queue.clear();
            visited.clear();

            //type 1: add interval to list of intervals
            if (scanner.nextInt() == 1) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                intervalsList.add(new Interval(start, end));
                intervalsList = adjustCompanions(intervalsList);
            }
            //type 2: check if intervals are connected
            else {
                Interval comp1 = intervalsList.get(scanner.nextInt() - 1);
                Interval comp2 = intervalsList.get(scanner.nextInt() - 1);
                boolean found = false;
                queue.add(comp1);
                visited.add(comp1);

                while (queue.peek() != null && !found) {
                    Interval temp = queue.poll();
                    if (temp.equals(comp2)) {
                        found = true;
                        break;
                    }
                    for (int j = 0; j < temp.getCompanions().size(); j++) {
                        if (!visited.contains(temp.getCompanions().get(j))) {
                            visited.add(temp.getCompanions().get(j));
                            queue.add(temp.getCompanions().get(j));
                        }
                    }
                }

                if (found) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    public static ArrayList<Interval> adjustCompanions (ArrayList<Interval> intervals) {

        Interval recent = intervals.get(intervals.size() - 1);

        for (int k = 0; k < intervals.size() - 1; k++) {
            Interval temp = intervals.get(k);
            if ((temp.getStart() < recent.getStart() && recent.getStart() < temp.getEnd()) ||
                    (temp.getStart() < recent.getEnd() && recent.getEnd() < temp.getEnd())) {
                recent.getCompanions().add(temp);
            }
            if ((recent.getStart() < temp.getStart() && temp.getStart() < recent.getEnd()) ||
                    (recent.getStart() < temp.getEnd() && temp.getEnd() < recent.getEnd())) {
                temp.getCompanions().add(recent);
            }
        }
        intervals.set(intervals.size() - 1, recent);

        return intervals;
    }
}

class Interval {

    private int start;
    private int end;
    private ArrayList<Interval> companions;


    public Interval (int start, int end) {
        this.start = start;
        this.end = end;
        companions = new ArrayList<>();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public ArrayList<Interval> getCompanions() {
        return companions;
    }
}
