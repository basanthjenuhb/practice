import java.util.Scanner;

public class KnightTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        int[][] grid = new int[8][8];
        Boolean[][][] visited = new Boolean[8][8][64];
        grid[y][x] = 1;
        check(y, x, 2, grid, visited);
        StringBuilder result = new StringBuilder();
        for (int[] row: grid) {
            for (int num: row) {
                result.append(String.format("%2d", num)).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    private static final int[][] dirs = new int[][] {
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };

    private static boolean check(int x, int y, int counter, int[][] grid, Boolean[][][] visited) {
        if (grid[x][y] == 64) {
            return true;
        }
        for (int[] dir: dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0
            && newY >= 0
            && newX < grid.length
            && newY < grid[newX].length
            && grid[newX][newY] == 0) {
                grid[newX][newY] = counter;
                if (check(newX, newY, counter + 1, grid, visited)) {
                    return true;
                } else {
                    grid[newX][newY] = 0;
                }
            }
        }
        return false;
    }
}
