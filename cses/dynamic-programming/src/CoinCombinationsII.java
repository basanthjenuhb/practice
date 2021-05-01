import java.util.Scanner;

public class CoinCombinationsII {
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

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int coin: coins) {
            for (int i = 1; i <= target; i++) {
                if (i - coin >= 0) {
                    dp[i] = (dp[i] + dp[i - coin]) % MOD;
                }
            }
        }
        System.out.println(dp[dp.length - 1]);
    }
}
