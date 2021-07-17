import java.util.HashSet;

public class NGrams {
  public static double sim(String str1, String str2) {
    String s1 = "__" + str1 + "__", 
           s2 = "__" + str2  + "__";;
    int num = 0;
    int l1 = 0, l2 = 0;

    HashSet<String> set = new HashSet<String>();
    for (; l1 < str1.length() + 2; l1++) {
      String gram = s1.substring(l1, l1 + 3);
      set.add(gram);
    }

    for (; l2 < str2.length() + 2; l2++) {
      String gram = s2.substring(l2, l2 + 3);
      if (set.contains(gram)) num++;
    }
    return 2.0 * num / (l1 + l2);
  }

  public static void main(String[] args) {
    System.out.println(sim("Qualität", "Quantität"));
  }
}
