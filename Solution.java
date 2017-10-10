import apple.laf.JRSUIUtils;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Change this class however you want. Print your output to STDOUT
public class Solution {

    private HashMap<String, TreeMap<String, ArrayList<String>>> data;

    public Solution() {
        data = new HashMap<>();
    }

    public void update(String profileId, String fieldName, String fieldValue) {

        //if the specified profileid does not exist in data
        if (!data.containsKey(profileId)) {
            TreeMap<String, ArrayList<String>> treeMap = new TreeMap<>();
            ArrayList<String> temp = new ArrayList<>();
            temp.add(fieldValue);
            treeMap.put(fieldName, temp);
            data.put(profileId, treeMap);
        }
        //if specified profileId exists but does not have specified fieldname
        else if (!data.get(profileId).containsKey(fieldName)) {

            int maxSize = 0;
            ArrayList<String> arrayList = new ArrayList<>();
            Iterator<String> iter = data.get(profileId).keySet().iterator();
            while (iter.hasNext()) {
                if (data.get(profileId).get(iter).size() > maxSize) {
                    maxSize = data.get(profileId).get(iter).size();
                }
            }

            //update version and add new type of fieldName to treemap
            for (int k = 0; k < maxSize; k++) {
                arrayList.add(fieldName);
            }

            data.get(profileId).put(fieldName, arrayList);
        }
        //if profileId exists and has field name
        else {
            data.get(profileId).get(fieldName).add(fieldName);
            updateVersions(profileId);
        }
    }

    public void updateVersions(String profileId) {
        Iterator<String> iter = data.get(profileId).keySet().iterator();
        while (iter.hasNext()) {
            ArrayList<String> temp = data.get(profileId).get(iter);
            data.get(profileId).get(iter).add(temp.get(temp.size() - 1));
        }
    }

    public void get(String profileId, int version) {
        System.out.println("Profile for " + profileId + " at version " + version + ":\n");
        if (!data.containsKey(profileId)) {
            System.out.println("Invalid request!");
            return;
        } else if (version <= 0) {
            System.out.println("Invalid request!");
            return;
        } else {
            //count to check if version is valid
            int count = 0;
            //version indexed off by 1
            version--;
            Iterator<String> iter = data.get(profileId).keySet().iterator();
            StringBuilder output = new StringBuilder();
            while (iter.hasNext()) {
                ArrayList<String> temp = data.get(profileId).get(iter);
                if (version < temp.size()) {
                    if (temp.get(version) != temp.get(version - 1)) {
                        output.append(iter).append(" - ").append(temp.get(version)).append("\n");
                        count++;
                    }
                }
            }
            if (count == 0) {
                System.out.println("Invalid request!");
            } else {
                System.out.println(output.toString());
            }
        }
    }

    public void getField(String profileId, int version, String fieldName) {
        System.out.println("" + fieldName + " for " + profileId + " at version " + version + ":\n");
        data.get(profileId).get(fieldName).get(version - 1);
    }


    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        Solution sol = new Solution();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String queryType = in.next();
            String profileId = in.next();
            if ("update".equals(queryType)) {
                String fieldName = in.next();
                String fieldValue = in.next();
                sol.update(profileId, fieldName, fieldValue);
            } else if ("get".equals(queryType)) {
                int version = in.nextInt();
                sol.get(profileId, version);
            } else if ("getfield".equals(queryType)) {
                int version = in.nextInt();
                String fieldName = in.next();
                sol.getField(profileId, version, fieldName);
            }
        }
    }
}