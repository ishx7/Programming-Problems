import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by ishani2 on 4/21/17.
 */
public class ThisQuestion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int strLength = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();
        HashSet<String> palindromes = new HashSet<>();

        for (int s = 0; s < strLength; s++) {
            for (int e = s + 1; e < strLength; e++) {
                if (s != e) {
                    StringBuilder sub = new StringBuilder(str.substring(s, e));
                    if (!isPalindrome(sub.toString())) {
                        String holder = sub.toString();
                        StringBuilder temp = sub;
                        temp.setCharAt(0, holder.charAt(holder.length() - 1));
                        if (isPalindrome(temp.toString())) {
                            palindromes.add(temp.toString());
                        }
                        temp = new StringBuilder(holder);
                        temp.setCharAt(holder.length() - 1, holder.charAt(0));
                        if (isPalindrome(temp.toString())) {
                            palindromes.add(temp.toString());
                        }
                    }
                }
            }
        }

        System.out.println(palindromes.size());
    }

    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }
}
