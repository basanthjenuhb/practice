import java.util.Scanner;

public class IncreasingArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalNums = scanner.nextInt();
        int previousNum = -1;
        long sum = 0;
        for (int i = 0; i < totalNums; i++) {
            int num = scanner.nextInt();
            if (i == 0) {
                previousNum = num;
                continue;
            }
            int bigNumber = Math.max(num, previousNum);
            int diff = Math.abs(bigNumber - num);
            sum += diff;
            previousNum = bigNumber;
        }
        System.out.println(sum);
    }
}
