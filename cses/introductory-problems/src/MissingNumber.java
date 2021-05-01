import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long totalNums = scanner.nextInt();
        long actualSum = 0;
        for (int i = 0; i < totalNums - 1; i++) {
            actualSum += scanner.nextInt();
        }
        long totalSum = (totalNums * (totalNums + 1)) / 2;
        System.out.println(totalSum - actualSum);
    }
}
