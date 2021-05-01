import java.util.Scanner;

public class DiceCombinations {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] dp = new int[Math.max(num + 1, 2)];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            for (int j = 1; j <= 6 && i - j >= 0; j++) {
                dp[i] = (dp[i] + dp[i - j]) % MOD;
            }
        }
        System.out.println(dp[dp.length - 1]);
     }
}
