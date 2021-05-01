import java.util.Scanner;

public class Permutations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 2 || n == 3) {
            System.out.println("NO SOLUTION");
            return;
        }
        if (n == 4) {
            System.out.println("2 4 1 3");
            return;
        }
        int[] nums = new int[n];
        int counter = 1;
        for (int i = 0; i < n; i = i + 2) {
            nums[i] = counter++;
        }
        for (int i = 1; i < n; i = i + 2) {
            nums[i] = counter++;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(nums[i]).append(" ");
        }
        System.out.println(result);
    }
}
