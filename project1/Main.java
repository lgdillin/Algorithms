import java.util.Random;
import java.util.TreeSet;

class Main {
  static final int TABLE_SIZE = 1009;
  static final int EMPTY = Integer.MIN_VALUE;
  static final int c1 = 1;
  static final int c2 = 3;

  static Random random;
  static int[] linearTable, quadraticTable, doubleTable;

  Main() {
    random = new Random(1234);
    //table = new ArrayList<Item>(1009);
    linearTable = new int[1009];
    quadraticTable = new int[1009];
    doubleTable = new int[1009];
  }

  // Auxialary hash function for linear and quadratic probing
  static int auxiliaryHash(int k) {
    // In this assignemtn h'(k) = k
    return k;
  }

  // linear probing
  static int linearProbing(int k, int i) {
    return ((auxiliaryHash(k) + i) % TABLE_SIZE);
  }

  // quadratic probing
  static int quadraticProbing(int k, int i) {
    // As per the assignment, our constants c1, c2 are defined
    return ((auxiliaryHash(k) + (c1 * i) + (c2 * i *i)) % TABLE_SIZE);
  }

  /// Functions for double hashing
  static int h1(int k) {
    return k;
  }

  static int h2(int k) {
    return (1 + (k % (TABLE_SIZE - 1)));
  }

  static int doubleHashing(int k, int i) {
    return ((h1(k) + i * h2(k)) % TABLE_SIZE);
  }


  public static void main(String[] args) {
    Main main = new Main();


    // TreeSet<Integer> t;
    // int[] populationData, analysisData;

    // Produce unique samples
    TreeSet<Integer> t = new TreeSet<Integer>();
    while(t.size() < 950) {
      t.add(random.nextInt(100000));
    }

    // parse the populationData
    int[] populationData = new int[900];
    for(int i = 0; i < 900; ++i) {
      populationData[i] = t.pollFirst();
    }

    // Separate 50 values
    int[] analysisData = new int[50];
    for(int i = 0; i < analysisData.length; ++i) {
      analysisData[i] = t.pollFirst();
    }

    /// LINEAR PROBING
    // Initialize table
    for(int i = 0; i < linearTable.length; ++i) {
      linearTable[i] = EMPTY;
    }

    // hash 900 random numbers into the linear table
    int collisions = 0;
    int j = 0;
    while(j < 900) {
      int hashedIndex = linearProbing(populationData[j], collisions);

      if(linearTable[hashedIndex] == EMPTY) {
        linearTable[hashedIndex] = populationData[j];
        ++j;
      } else {
        ++collisions;
      }
    }

    int aCollisions = 0;
    int probes = 0;
    j = 0;
    while(j < 50) {
      int hashedIndex = linearProbing(analysisData[j], collisions);

      if(linearTable[hashedIndex] == EMPTY) {
        linearTable[hashedIndex] = analysisData[j];
        ++j;
      } else {
        ++collisions;
        ++aCollisions;
      }
      ++probes;
    }

    System.out.println("# Probes for LINEAR hashing (analysis data): " + probes);
    System.out.println("# Collisions for LINEAR hashing (analysis data): " + aCollisions);
    System.out.println();

    /// QUADRATIC PROBING
    // Initialize table
    for(int i = 0; i < quadraticTable.length; ++i) {
      quadraticTable[i] = EMPTY;
    }

    // hash 900 random numbers into the quadratic table
    collisions = 0;
    j = 0;
    while(j < 900) {
      int hashedIndex = quadraticProbing(populationData[j], collisions);

      if(quadraticTable[hashedIndex] == EMPTY) {
        quadraticTable[hashedIndex] = populationData[j];
        ++j;
      } else {
        ++collisions;
      }
    }

    aCollisions = 0;
    probes = 0;
    j = 0;
    while(j < 50) {
      int hashedIndex = quadraticProbing(analysisData[j], collisions);

      if(quadraticTable[hashedIndex] == EMPTY) {
        quadraticTable[hashedIndex] = analysisData[j];
        ++j;
      } else {
        ++collisions;
        ++aCollisions;
      }
      ++probes;
    }

    System.out.println("# Probes for QUADRATIC hashing (analysis data): " + probes);
    System.out.println("# Collisions for QUADRATIC hashing (analysis data): " + aCollisions);
    System.out.println();


    /// DOUBLE PROBING
    // Initialize table
    for(int i = 0; i < doubleTable.length; ++i) {
      doubleTable[i] = EMPTY;
    }

    // hash 900 random numbers into the double table
    collisions = 0;
    j = 0;
    while(j < 900) {
      int hashedIndex = doubleHashing(populationData[j], collisions);

      if(doubleTable[hashedIndex] == EMPTY) {
        doubleTable[hashedIndex] = populationData[j];
        ++j;
      } else {
        ++collisions;
      }
    }

    aCollisions = 0;
    probes = 0;
    j = 0;
    while(j < 50) {
      int hashedIndex = doubleHashing(analysisData[j], collisions);

      if(doubleTable[hashedIndex] == EMPTY) {
        doubleTable[hashedIndex] = analysisData[j];
        ++j;
      } else {
        ++collisions;
        ++aCollisions;
      }
      ++probes;
    }

    System.out.println("# Probes for DOUBLE hashing (analysis data): " + probes);
    System.out.println("# Collisions for DOUBLE hashing (analysis data): " + aCollisions);
    System.out.println();
  }
}
