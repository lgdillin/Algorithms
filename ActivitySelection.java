import java.io.*;
import java.util.ArrayList;

class Activity {
  int id, start, finish, value;

  Activity(int i, int s, int f, int v) {
    id = i;
    start = s;
    finish = f;
    value = v;
  }
}


class ActivitySelection {
  int numActivities, maxRange, activityID, value;
  ArrayList<Activity> activities;

  ActivitySelection() {

  }

  // s: start times
  // f: finish times
  // k: index k that defines the subproblem S_k it is to solve
  // n: the size of the original problem
  void recursiveActivitySelector(int[] s, int[] f, int k, int n) {
    int m = k + 1;

    // looks for the first activity in S_k to finish
    while(m <= n && s[m] < f[k]) {
      ++m;
    }

    if(m <= n)
      return; // {a_m} UNION activitySelector(s, f, m, n)
    else return; // return empty set
  }

  void greedyActivitySelector(int[] s, int[] f) {

  }


  public static void main(String[] args) {
    ActivitySelection as = new ActivitySelection();

    // read the input file
    try {
      File file = new File("input.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      StringBuffer stringBuffer = new StringBuffer();
      String line;

      line = bufferedReader.readLine();

      // Get number of activities
      as.numActivities = Character.getNumericValue(line.charAt(0));
      if(as.numActivities == -1) throw new RuntimeException("Bad file!");

      as.activities = new ArrayList<Activity>();

      // Get max range of interval i, ranging over [0, i]
      as.maxRange = Character.getNumericValue(line.charAt(2));
      if(as.maxRange == -1) throw new RuntimeException("Bad file!");

      while ((line = bufferedReader.readLine()) != null) {
        int id = Character.getNumericValue(line.charAt(0));
        int start = Character.getNumericValue(line.charAt(2));
        int finish = Character.getNumericValue(line.charAt(4));
        int values = Character.getNumericValue(line.charAt(6));

        if(id == -1) throw new RuntimeException("Bad file!");
        if(start == -1) throw new RuntimeException("Bad file!");
        if(finish == -1) throw new RuntimeException("Bad file!");
        if(values == -1) throw new RuntimeException("Bad file!");

        as.activities.add(new Activity(id, start, finish, values));
      }
      fileReader.close();
    } catch(IOException e) {
      e.printStackTrace();
    }

    // map start and finish times into arrays
    int[] startTimes = new int[as.activities.size()];
    int[] finishTimes = new int[as.activities.size()];


  }
}
