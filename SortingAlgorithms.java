import java.util.Random;

class InsertionSort {

  InsertionSort() {
  }

  static void sort(int[] arr) {
    for(int j = 1; j < arr.length; ++j) {
      int key = arr[j];
      // Insert arr[j] into the sorted sequence

      int i = j - 1;
      while(i >= 0 && arr[i] > key) {
        arr[i + 1] = arr[i];
        i = i - 1;
      }
      arr[i + 1] = key;
    }
  }
}

class MergeSort {
  MergeSort() {}

  static void sort(int[] arr) {
    mergeSort(arr, 0, arr.length - 1);
  }

  static void mergeSort(int[] arr, int p, int r) {
    if(p < r) {
      int q = (p + r) / 2;
      mergeSort(arr, p, q);
      mergeSort(arr, q + 1, r);
      merge(arr, p, q, r);
    }
  }

  static void merge(int[] arr, int p, int q, int r) {
    int n1 = q - p + 1;
    int n2 = r - q;

    int[] left = new int[n1 + 1];
    int[] right = new int[n2 + 1];

    // populate the left partition
    for(int i = 0; i < n1; ++i) {
      left[i] = arr[p + i];
    }

    // populate the right partition
    for(int i = 0; i < n2; ++i) {
      right[i] = arr[q + i + 1];
    }

    // Sentinel values
    left[n1] = 98;
    right[n2] = 98;
    int i = 0;
    int j = 0;

    // Compare and merge
    for(int k = p; k < r + 1; ++k) {
      if(left[i] <= right[j]) {
        arr[k] = left[i];
        ++i;
      } else {
        arr[k] = right[j];
        ++j;
      }
    }

  }
}

class HeapSort {
  static int heapSize;

  HeapSort() {
  }

  static void sort(int[] arr) {
    heapSort(arr);
  }

  static int parent(int i) {
    int index = i / 2;
    return index;
  }

  static int left(int i) {
    int index = 2 * i + 1;
    return index;
  }

  static int right(int i) {
    int index = (2 * i) + 2;
    return index;
  }

  static void heapSort(int[] arr) {
    buildMaxHeap(arr);
    for(int i = arr.length - 1; i >= 0; --i) {
      int swap = arr[0];
      arr[0] = arr[i];
      arr[i] = swap;
      --heapSize;
      maxHeapify(arr, 0);
    }
  }

  static void buildMaxHeap(int[] arr) {
    heapSize = arr.length;
    for(int i = arr.length/2 - 1; i >= 0; --i)
      maxHeapify(arr, i);
  }

  static void maxHeapify(int[] arr, int i) {
    int l = left(i);
    int r = right(i);
    int largest = i;
    if(l < heapSize && arr[l] > arr[largest])
      largest = l;

    if(r < heapSize && arr[r] > arr[largest])
      largest = r;
    if(largest != i) {
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;
      maxHeapify(arr, largest);
    }
  }
}

class QuickSort {
  QuickSort() {

  }

  static void sort(int[] arr) {
    quicksort(arr, 0, arr.length - 1);
  }

  static void quicksort(int[] arr, int p, int r) {
    if(p < r) {
      int q = partition(arr, p, r);
      quicksort(arr, p, q - 1);
      quicksort(arr, q + 1, r);
    }
  }

  static int partition(int[] arr, int p, int r) {
    int x = arr[r];
    int i = p - 1;
    for(int j = p; j <= r - 1; ++j) {
      if(arr[j] <= x) {
        ++i;
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
      }
    }

    int swap = arr[i + 1];
    arr[i + 1] = arr[r];
    arr[r] = swap;
    return i + 1;
  }
}

class CountingSort {
  CountingSort() {

  }

  static void sort(int[] arr) {
    int[] barr = new int[arr.length];

    int max = arr[0];
    for(int i = 0; i < arr.length; ++i) {
      if(arr[i] > max)
        max = arr[i];
    }

    countingSort(arr, barr, max);
    System.arraycopy(barr, 0, arr, 0, arr.length);
  }

  static void countingSort(int[] arr, int[] barr, int k) {
    int[] carr = new int[k + 1];

    for(int i = 0; i <= k; ++i) {
      carr[i] = 0;
    }

    for(int i = 0; i < arr.length; ++i) {
      carr[arr[i]] = carr[arr[i]] + 1;
    }

    for(int i = 1; i <= k; ++i) {
      carr[i] = carr[i] + carr[i - 1];
    }

    for(int j = arr.length - 1; j >= 0; --j) {
      int x = carr[arr[j]] - 1;
      barr[x] = arr[j];
      carr[arr[j]] = carr[arr[j]] - 1;
    }
  }
}

class RadixSort {
  RadixSort() {

  }

  static void sort(int[] arr) {
    int max = Integer.toString(arr[0]).length();
    for(int i = 0; i < arr.length; ++i) {
      if(Integer.toString(arr[i]).length() > max)
        max = Integer.toString(arr[i]).length();
    }

    radixSort(arr, max);
  }

  static void radixSort(int[] arr, int d) {

  }
}

class BucketSort {
  private class Node {
    int elem;
    Node next;

    Node(int k, Node prev) {
      prev = this;
    }
  }

  BucketSort() {

  }

  static void sort(int[] arr) {
    bucketSort(arr);
  }

  static void bucketSort(int[] arr) {
    Node[] buckets = new Node[arr.length - 1];

    for(int i = 0; i < arr.length - 1; ++i) {
      buckets[i] = null;
    }

    for(int i = 0; i < arr.length; ++i) {

    }
  }
}


class SortingAlgorithms {
  SortingAlgorithms() { }

  public static void main(String[] args) {
    Random r = new Random();
    int length = r.nextInt(100);
    int[] arr = new int[length];

    for(int i = 0; i < arr.length; ++i) {
      arr[i] = r.nextInt(100);
    }

    for(int i = 0; i < arr.length; ++i) {
      System.out.print(arr[i] + ", ");
    }
    System.out.println();

    //InsertionSort.sort(arr);
    MergeSort.sort(arr);
    //HeapSort.sort(arr);
    //QuickSort.sort(arr);
    //CountingSort.sort(arr);

    for(int i = 0; i < arr.length; ++i) {
      System.out.print(arr[i] + ", ");
    }
    System.out.println();

    for(int i = 0; i < arr.length - 1; ++i) {
      if(arr[i] > arr[i + 1]) {
        System.out.println("Sorting failed");
        break;
      }
    }
  }
}
