import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CountingRooms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int width = scanner.nextInt();
        char[][] map = new char[length][width];
        for (int i = 0; i < map.length; i++) {
            String line = scanner.next();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '.') {
                    count++;
                    traverse(map, i, j);
                }
            }
        }
        System.out.println(count);
    }

    private static final int[][] dirs = new int[][]{
        {-1, 0},
        {0, 1},
        {0, -1},
        {1, 0}
    };

    private static void traverse(char[][] map, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        map[i][j] = '-';
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int[] dir: dirs) {
                int newx = dir[0] + pos[0];
                int newy = dir[1] + pos[1];
                if (newx >= 0
                    && newx < map.length
                    && newy >= 0
                    && newy < map[newx].length
                    && map[newx][newy] == '.') {
                    queue.add(new int[]{newx, newy});
                    map[newx][newy] = '-';
                }
            }
        }
    }
}
