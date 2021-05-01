import java.util.Scanner;

public class PalindromeReorder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        int[] count = new int[26];

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            count[ch - 'A']++;
        }

        int oddCount = 0;
        for (int num: count) {
            if (num % 2 == 1) {
                oddCount++;
            }
        }
        if (oddCount > 1) {
            System.out.println("NO SOLUTION");
            return;
        }
        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            char ch = (char)('A' + i);
            int total = count[i];
            if (total % 2 == 0) {
                for (int j = 0; j < total / 2; j++) {
                    even.append(ch);
                }
            } else {
                for (int j = 0; j < total; j++) {
                    odd.append(ch);
                }
            }
        }

        String result = even.toString() + odd.toString() + even.reverse().toString();
        System.out.println(result);
    }
}
