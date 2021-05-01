import java.util.Scanner;

public class StringMatching {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        String pattern = scanner.next();
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (check(text, pattern, i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean check(String text, String pattern, int i) {
        int k = i, j = 0;
        for (; k < text.length() && j < pattern.length(); k++, j++) {
            if (text.charAt(k) != pattern.charAt(j)) {
                break;
            }
        }
        return j == pattern.length();
    }
}
