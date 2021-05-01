import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Labyrinth {

    private static class Position {
        int x, y;
        char direction;
        Position parent;

        public Position(int x, int y, char direction, Position parent) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.parent = parent;
        }
    }

    private static final int[][] dirs = new int[][] {
        {-1, 0, 'U'},
        {0, 1, 'R'},
        {0, -1, 'L'},
        {1, 0, 'D'}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int width = scanner.nextInt();
        char[][] map = new char[length][width];
        int startx = 0, starty = 0, endx = 0, endy = 0;
        for (int i = 0; i < map.length; i++) {
            String line = scanner.next();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'A') {
                    startx = i;
                    starty = j;
                }
                if (map[i][j] == 'B') {
                    endx = i;
                    endy = j;
                }
            }
        }
        Queue<Position> queue = new ArrayDeque<>();
        queue.add(new Position(startx, starty, ' ', null));
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            if (position.x == endx && position.y == endy) {
                System.out.println("YES");
                StringBuilder result = new StringBuilder();
                while (position != null) {
                    if (position.direction != ' ') {
                        result.append(position.direction);
                    }
                    position = position.parent;
                }
                result = result.reverse();
                System.out.println(result.length());
                System.out.println(result);
                return;
            }
            for (int[] dir: dirs) {
                int newx = position.x + dir[0];
                int newy = position.y + dir[1];
                if (newx >= 0
                && newy >= 0
                && newx < map.length
                && newy < map[newx].length
                && (map[newx][newy] == '.' || map[newx][newy] == 'B')) {
                    queue.add(new Position(newx, newy, (char) dir[2], position));
                    map[newx][newy] = '-';
                }
            }
        }
        System.out.println("NO");
    }
}
