

class Main {

  public static final int NEG_INF = -1000;

  Main() {

  }

  int[] findMaxSubarray(int[] arr, int low, int high) {
    int[] result = new int[3];
    int[] left = new int[3];
    int[] right = new int[3];
    int[] cross = new int[3];

    if(high == low) {
      result[0] = low;
      result[1] = high;
      result[2] = arr[low];

      return result;
    } else {
      int mid = ((low + high) / 2);
      left = findMaxSubarray(arr, low, mid);
      right = findMaxSubarray(arr, mid+1, high);
      cross = findMaxCrossingSubarray(arr, low, mid, high);


      for(int i = 0; i < cross.length; ++i) {
        System.out.print(right[i] + " ");
      }
      System.out.println();

      if(left[2] >= right[2] && left[2] >= cross[2]) {
        return left;
      } else if(right[2] >= left[2] && right[2] >= cross[2])
        return right;
      else {
        return cross;
      }
    }
  }

  int[] findMaxCrossingSubarray(int[] arr, int low, int mid, int high) {
    int[] result = new int[3];

    int leftSum = NEG_INF;
    int rightSum = NEG_INF;
    int sum = 0;

    int maxLeft = mid;
    int maxRight = mid + 1;

    // Left side
    for(int i = mid; i >= low; --i) {
      sum = sum + arr[i];

      if(sum > leftSum) {
        leftSum = sum;
        maxLeft = i;
      }
    }

    // Right side
    for(int i = mid+1; i <= high; ++i) {
      sum = sum + arr[i];

      if(sum > rightSum) {
        rightSum = sum;
        maxRight = i;
      }
    }

    result[0] = maxLeft;
    result[1] = maxRight;
    result[2] = leftSum + rightSum;
    return result;
  }


  public static void main(String[] args) {
    int[] price = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
    int[] difference = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
    //int[] difference = new int[price.length - 1];

    // Calculate the difference between days
    for(int i = 0; i < difference.length; ++i) {
      difference[i] = price[i+1] - price[i];
    }

    Main main = new Main();
    int[] result = main.findMaxSubarray(difference, 0, difference.length - 1);
    for(int i = 0; i < result.length; ++i) {
      System.out.println(result[i]);
    }
  }
}
