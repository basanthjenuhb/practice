import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Monsters {

    static class Position {
        Position parent;
        int x, y;
        char direction;

        public Position(int x, int y, char direction, Position parent) {
            this.parent = parent;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Position{" +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = scanner.nextInt();
        int width = scanner.nextInt();

        char[][] grid = new char[height][width];
        int[][] timer = new int[height][width];

        Queue<Position> personQueue = new ArrayDeque<>();
        Queue<Position> monsterQueue = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            String line = scanner.next();
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'A') {
                    personQueue.add(new Position(i, j, ' ', null));
                } else if (grid[i][j] == 'M') {
                    monsterQueue.add(new Position(i, j, ' ', null));
                    timer[i][j] = 1;
                }
            }
        }

        int[][] dirs = new int[][]{
            {-1, 0, 'U'},
            {0, 1, 'R'},
            {0, -1, 'L'},
            {1, 0, 'D'}
        };

        int counter = 1;
        while (!monsterQueue.isEmpty()) {
            counter++;
            int size = monsterQueue.size();
            for (int i = 0; i < size && !monsterQueue.isEmpty(); i++) {
                Position pos = monsterQueue.poll();
                for (int[] dir: dirs) {
                    int newx = pos.x + dir[0];
                    int newy = pos.y + dir[1];
                    if (newx < 0 || newx >= timer.length || newy < 0 || newy >= timer[newx].length) {
                        continue;
                    }
                    if (grid[newx][newy] == '#') {
                        continue;
                    }
                    if (timer[newx][newy] != 0) {
                        continue;
                    }
                    timer[newx][newy] = counter;
                    monsterQueue.add(new Position(newx, newy, ' ', null));
                }
            }
        }

        counter = 1;
        Position destination = null;
        boolean stop = false;
        while (!personQueue.isEmpty() && !stop) {
            counter++;
            int size = personQueue.size();
            for (int i = 0; i < size && !personQueue.isEmpty(); i++) {
                Position pos = personQueue.poll();
                if (pos.x == 0 || pos.y == 0 || pos.x == timer.length - 1 || pos.y == timer[0].length - 1) {
                    destination = pos;
                    stop = true;
                    break;
                }
                for (int[] dir: dirs) {
                    int newx = pos.x + dir[0];
                    int newy = pos.y + dir[1];
                    if (newx < 0 || newx >= timer.length || newy < 0 || newy >= timer[newx].length) {
                        continue;
                    }
                    if (grid[newx][newy] == '#') {
                        continue;
                    }
                    if (counter >= timer[newx][newy] && timer[newx][newy] != 0) {
                        continue;
                    }
                    personQueue.add(new Position(newx, newy, (char) dir[2], pos));
                }
            }
        }

        if (destination == null) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        StringBuilder result = new StringBuilder();
        while (destination != null && destination.direction != ' ') {
            result.append(destination.direction);
            destination = destination.parent;
        }
        result.reverse();
        System.out.println(result.length());
        System.out.println(result);
    }
}
