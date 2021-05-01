import java.util.Scanner;

public class WeirdAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        StringBuilder result = new StringBuilder();
        result.append(n);
        while (n != 1) {
            if ((n & 1) == 0) {
                n = n >> 1;
            } else {
                n = (n * 3) + 1;
            }
            result.append(" ").append(n);
        }
        System.out.println(result);
    }

}
