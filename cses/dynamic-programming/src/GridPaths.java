import java.util.Scanner;

public class GridPaths {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] grid = new int[size + 1][size + 1];
        for (int i = 1; i < grid.length; i++) {
            String line = scanner.next();
            for (int j = 1; j < grid[i].length; j++) {
                char ch = line.charAt(j - 1);
                if (ch == '*') {
                    continue;
                }
                if (i == 1 && j == 1) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = (grid[i - 1][j] + grid[i][j - 1]) % MOD;
                }
            }
        }

        System.out.println(Math.max(grid[size][size], 0));
    }
}
