import java.util.Arrays;
import java.util.Scanner;

public class MinimizingCoins {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCoins = scanner.nextInt();
        int target = scanner.nextInt();

        int[] coins = new int[numCoins];
        coins[0] = 1;
        for (int i = 0; i < numCoins; i++) {
            coins[i] = scanner.nextInt();
        }

        long[] dp = new long[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin: coins) {
            for (int i = 1; i <= target; i++) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        long value = dp[dp.length - 1];
        if (value == Integer.MAX_VALUE) {
            value = -1;
        }
        System.out.println(value);
    }
}
