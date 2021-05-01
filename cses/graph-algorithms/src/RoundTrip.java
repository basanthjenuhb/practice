import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoundTrip {

    public static void main(String[] args) throws Exception {
        Reader scanner = new Reader();
        int numNodes = scanner.nextInt();
        int numEdges = scanner.nextInt();

        List<Integer>[] graph = new List[numNodes + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph[source].add(destination);
            graph[destination].add(source);
        }
        int[] parents = new int[numNodes + 1];
        boolean[] visited = new boolean[numNodes + 1];

        for (int i = 1; i <= numNodes; i++) {
            if (!visited[i]) {
                dfs(graph, i, 0, parents, visited);
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    private static void dfs(List<Integer>[] graph, int node, int parent, int[] parents, boolean[] visited) {
        visited[node] = true;
        parents[node] = parent;
        for (int child: graph[node]) {
            if (child == parent) {
                continue;
            } else if (!visited[child]) {
                dfs(graph, child, node, parents, visited);
            } else {
                StringBuilder result = new StringBuilder();
                result.append(child).append(" ");
                int count = 1;
                while (node != child) {
                    count++;
                    result.append(node).append(" ");
                    node = parents[node];
                }
                count++;
                result.append(node).append(" ");
                System.out.println(count);
                System.out.println(result);
                System.exit(0);
            }
        }
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
