import java.util.Scanner;

public class BitStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        long value = 1;
        for (int i = 0; i < num; i++) {
            value = (value * 2) % (1_000_000_000 + 7);
        }
        System.out.println(value);
    }
}
