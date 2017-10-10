/**
 * Created by ishani on 2/23/17.
 */
import org.json.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.*;

//assume that no adjacent boxes are out of the bounds of the maze
public class Maze {

    public static void main(String[] args) {

        StringBuilder path = new StringBuilder();
        URL url;
        try {
            url = new URL("https://challenge.flipboard.com/start");
            HttpURLConnection connection = (HttpURLConnection) (url.openConnection());
            connection.setRequestMethod("GET");

            String jsonString = getResponse(connection);
            //s value
            String mazeID = connection.getURL().toString().split("&")[0].split("=")[1];

            // First JSON Object (Start)
            JSONObject startObject = new JSONObject(jsonString);
            System.out.println(startObject);
            System.out.println(mazeID);
            Node currentNode = new Node(startObject, 0, 0);

            //make stack and HashSet
            Stack<Node> nodeStack = new Stack<>();
            HashSet<Node> visited = new HashSet<>();
            HashSet<Point> addedToStack = new HashSet<>();

            //add start info to set and stack
            nodeStack.push(currentNode);

            //loop part
            while (!currentNode.getEnd()) {
                //add letter to path
                currentNode = nodeStack.peek();
                path.append(currentNode.getLetter());

                if (visited.contains(currentNode)) {
                    nodeStack.pop();
                    continue;
                }
                visited.add(currentNode);

                JSONArray adjacencies = currentNode.getJSONArray();

                //create and add adjacencies to stack
                int count = 0;
                for (Node o : currentNode.getAdjacencies(adjacencies, mazeID)) {
                    if (!addedToStack.contains(o.getPoint())) {
                        System.out.println("point: " + o.getPoint());
                        nodeStack.push(o);
                        addedToStack.add(currentNode.getPoint());
                        count++;
                    }
                }
                if (count == 0) {
                    nodeStack.pop();
                }

                System.out.println(currentNode.getCurrentObject());
                System.out.println();

                //currentNode = nodeStack.peek();
            }

            System.out.println(path);
            System.out.println(mazeID);
            System.out.println(currentNode.currentObject);
            System.out.println(checkConnection(mazeID, path.toString()));

        } catch(IOException e){
            System.out.println(e);
        }
    }

    public static JSONObject newConnection(String mazeID, int x, int y) {
        try {
            String params = String.format("s=%s&x=%s&y=%s",
                    URLEncoder.encode(mazeID, "UTF-8"),
                    URLEncoder.encode("" + x, "UTF-8"),
                    URLEncoder.encode("" + y, "UTF-8"));
            URL url = new URL("https://challenge.flipboard.com/step?" + params);
            HttpURLConnection connection = (HttpURLConnection) (url.openConnection());

            String jsonString = getResponse(connection);
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static JSONObject checkConnection(String mazeID, String guess) {
        try {
            String params = String.format("s=%s&guess=%s",
                    URLEncoder.encode(mazeID, "UTF-8"),
                    URLEncoder.encode(guess, "UTF-8"));
            URL url = new URL("https://challenge.flipboard.com/check?" + params);
            HttpURLConnection connection = (HttpURLConnection) (url.openConnection());

            String jsonString = getResponse(connection);
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static String getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        int index;
        StringBuilder returnString = new StringBuilder();
        while ((index = bufferedReader.read()) != -1) {
            returnString.append((char) index);
        }
        return returnString.toString();
    }

    public static class Node {

        private JSONObject currentObject;
        private Point point;

        public Node(JSONObject currentObject,  int x, int y) {
            this.currentObject = currentObject;
            this.point = new Point(x, y);
        }

        public JSONObject getCurrentObject() {
            return currentObject;
        }

        public String getLetter() {
            return (String) currentObject.get("letter");
        }

        public Point getPoint() {
            return point;
        }

        public JSONArray getJSONArray() {
            return (JSONArray) currentObject.get("adjacent");
        }

        public boolean getEnd() {
            return currentObject.getBoolean("end");
        }

        public ArrayList<Node> getAdjacencies(JSONArray jsonArrayAdjacencies, String mazeID) {
            ArrayList<Node> adjacencies = new ArrayList<>();
            if (jsonArrayAdjacencies != null) {
                for (int k = 0; k < jsonArrayAdjacencies.length(); k++) {
                    //System.out.println(jsonArrayAdjacencies.get(k));
                    JSONObject obj = jsonArrayAdjacencies.getJSONObject(k);
                    String temp = obj.toString();
                    int x = Integer.parseInt(temp.split(",")[0].split(":")[1]);
                    int y = Integer.parseInt(temp.split("y\":")[1].split("}")[0]);
                    adjacencies.add(new Node(newConnection(mazeID, x, y), x, y));
                }
            }
            return adjacencies;
        }

        @Override
        public int hashCode() {
            return Objects.hash(point);
        }

        @Override
        public boolean equals(Object node) {
            if (node instanceof Node) {
                return this.point.equals(((Node) node).point);
            }
            return false;
        }
    }
}



