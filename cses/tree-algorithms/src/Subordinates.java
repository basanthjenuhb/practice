import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subordinates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numEmployees = scanner.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[numEmployees + 1];
        int[] subordinates = new int[numEmployees + 1];

        for (int i = 2; i <= numEmployees; i++) {
            int parent = scanner.nextInt();
            if (graph[parent] == null) {
                graph[parent] = new ArrayList<>();
            }
            graph[parent].add(i);
        }
        count(graph, subordinates, 1);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < subordinates.length; i++) {
            result.append(subordinates[i]).append(" ");
        }
        System.out.println(result);
    }

    private static int count(List<Integer>[] graph, int[] subordinates, int parent) {
        int count = 0;
        if (graph[parent] != null) {
            for (int child: graph[parent]) {
                count += count(graph, subordinates, child);
            }
        }
        subordinates[parent] = count;
        return count + 1;
    }
}
