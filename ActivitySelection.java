import java.io.*;
import java.util.ArrayList;


class ActivitySelection {
  int numActivities, maxRange, activityID, value;


  ActivitySelection() {

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

      // Get max range of interval i, ranging over [0, i]
      as.maxRange = Character.getNumericValue(line.charAt(2));

      while ((line = bufferedReader.readLine()) != null) {

        // String replacement = line.replaceAll("authentic", "poor");
        // stringBuffer.append(replacement);
        // stringBuffer.append("\n");
        //
        // stringBuffer.append(line);
        // stringBuffer.append("\n");
      }

      fileReader.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
