import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DownloadSpeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numComputers = scanner.nextInt();
        int numConnections = scanner.nextInt();

        List<int[]>[] graph = new List[numComputers + 1];

        for (int i = 0; i < numConnections; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int speed = scanner.nextInt();

            if (graph[source] == null) {
                graph[source] = new ArrayList<>();
            }
            graph[source].add(new int[]{destination, speed});
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, Integer.MAX_VALUE});

        while (!queue.isEmpty()) {
            int[] source = queue.poll();
            for (int[] children: graph[source[0]]) {

            }
        }

    }
}
