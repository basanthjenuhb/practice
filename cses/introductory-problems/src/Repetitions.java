import java.util.Scanner;

public class Repetitions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        int count = 1, maxCount = 0;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == text.charAt(i - 1)) {
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                count = 1;
            }
        }
        maxCount = Math.max(count, maxCount);
        System.out.println(maxCount);
    }
}
