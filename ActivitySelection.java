import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

class ActivityComparator implements Comparator<Activity> {
  public int compare(Activity a, Activity b) {
    if(a.id < b.id)
      return -1;
    else if(a.id > b.id)
      return 1;
    return 0;
  }
}

class Activity {
  int id, start, finish, value;

  Activity(int i, int s, int f, int v) {
    id = i;
    start = s;
    finish = f;
    value = v;
  }

  static void print(Activity a) {
    System.out.println(a.id + " " + a.start + " " + a.finish + " " + a.value);
  }
}


class ActivitySelection {
  int numActivities, maxRange, activityID, value;
  ActivityComparator comp;
  TreeSet<Activity> set;
  HashMap<Integer, Activity> map;

  ActivitySelection() {
    comp = new ActivityComparator();
  }

  // s: start times
  // f: finish times
  // k: index k that defines the subproblem S_k it is to solve
  // n: the size of the original problem
  TreeSet<Activity> recursiveActivitySelector(int[] s, int[] f, int k, int n) {
    int m = k + 1;
    TreeSet<Activity> ts = new TreeSet<Activity>(comp);

    // looks for the first activity in S_k to finish
    while(m <= n && s[m] < f[k]) {
      ++m;
    }

    if(m <= n) {
      ts.add(map.get(m));
      ts.addAll(recursiveActivitySelector(s, f, m, n));
      return ts;
    } else {
      return ts;
    }
  }

  TreeSet<Activity> greedyActivitySelector(int[] s, int[] f) {
    int n = s.length;
    int k = 0;
    TreeSet<Activity> ts = new TreeSet<Activity>(comp);
    ts.add(map.get(1));

    for(int m = 1; m < n; ++m) {

      //System.out.println("comp: " + s[m] + " " + f[k]);
      if(s[m] >= f[k]) {
        // A = A UNION {a_m}
        //System.out.println(m);
        ts.add(map.get(m+1));
        k = m;
      }
    }
    return ts; // return A;
  }

  TreeSet<Activity> greedyActivityValueSelector(int first) {
    int n = map.size();
    TreeSet<Activity> ts = new TreeSet<Activity>(comp);
    Activity fActivity = map.get(first);
    ts.add(fActivity);

    Activity a = fActivity;
    for(int i = first; i <= n; ++i) {
      Activity b = map.get(i);
      if(b.start >= a.finish) {
        ts.add(b);
        a = b;
      }
    }
    return ts; // return A;
  }


  public static void main(String[] args) {
    ActivitySelection as = new ActivitySelection();
    as.set = new TreeSet<Activity>(as.comp);
    as.map = new HashMap<Integer, Activity>();

    // read the input file
    try {
      File file = new File("input1.txt");
      //File file = new File(args[0]);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;

      line = bufferedReader.readLine();
      StringTokenizer st = new StringTokenizer(line);

      // Get number of activities
      //as.numActivities = Character.getNumericValue(line.charAt(0));
      as.numActivities = Integer.parseInt(st.nextToken());
      if(as.numActivities == -1) throw new RuntimeException("Bad file!");

      //as.activities = new ArrayList<Activity>()

      // Get max range of interval i, ranging over [0, i]
      as.maxRange = Integer.parseInt(st.nextToken());
      if(as.maxRange == -1) throw new RuntimeException("Bad file!");

      while ((line = bufferedReader.readLine()) != null) {
        st = new StringTokenizer(line);
        int id = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int finish = Integer.parseInt(st.nextToken());
        int values = Integer.parseInt(st.nextToken());

        if(id == -1) throw new RuntimeException("Bad file!");
        if(start == -1) throw new RuntimeException("Bad file!");
        if(finish == -1) throw new RuntimeException("Bad file!");
        if(values == -1) throw new RuntimeException("Bad file!");

        if(as.map.containsKey(id))
          throw new RuntimeException("duplicate ids in input file");
        as.map.put(id, new Activity(id, start, finish, values));
        //set.add(new Activity(id, start, finish, values));
      }
      fileReader.close();
    } catch(IOException e) {
      e.printStackTrace();
    }

    // Arrays to hold start and finish times
    int[] startTimes = new int[as.numActivities];
    int[] finishTimes = new int[as.numActivities];
    // Holds the collection of values (Activity) from the hashmap
    Collection<Activity> activities = as.map.values();

    // Map the activities' start and finish times
    // from the hashmap collection into the array
    int i = 0;
    Iterator<Activity> itr = activities.iterator();
    while(itr.hasNext()) {
      Activity a = itr.next();
      startTimes[i] = a.start;
      finishTimes[i] = a.finish;
      ++i;
    }

    TreeSet<Activity> maxSet = null;
    TreeSet<Activity> ts = null;
    int max = 0;
    for(int j = 1; j <= as.numActivities; ++j) {
      ts = as.greedyActivityValueSelector(j);

      int sum = 0;
      Iterator<Activity> itr1 = ts.iterator();
      while(itr1.hasNext()) {
        Activity a = itr1.next();
        sum = sum + a.value;
      }

      if(sum > max) {
        max = sum;
        maxSet = ts;
      }
    }

    Iterator<Activity> itr1 = maxSet.iterator();
    while(itr1.hasNext()) {
      Activity a = itr1.next();
      System.out.print(a.id + ", ");
    }
    System.out.println();

    // TreeSet<Activity> ts = as.greedyActivitySelector(startTimes, finishTimes);
    // TreeSet<Activity> ts = as.recursiveActivitySelector(startTimes, finishTimes, 0, as.numActivities);
  }
}
