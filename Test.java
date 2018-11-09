import java.util.HashMap;

class Test {

  public static void main(String[] args) {
    HashMap<Integer, String> set = new HashMap<Integer, String>();

    set.put(1, "Hello");
    set.put(1, "Hello1");

    System.out.println(set.get(1));
  }
}
