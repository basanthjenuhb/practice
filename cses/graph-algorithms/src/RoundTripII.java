import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoundTripII {

    private static final int MOD = 1_000_000_007;

    private static void dfs(int src, List<Integer>[] graph, boolean[] visited, int[] parents) {
        visited[src] = true;
        for (int child: graph[src]) {
            if (!visited[child]) {
                parents[child] = src;
                dfs(child, graph, visited, parents);
            } else {
                StringBuilder result = new StringBuilder();
                result.append(src);
                int node = src;
                int counter = 2;
                do {
                    src = parents[src];
                    if (src == 0) {
                        return;
                    }
                    counter++;
                    result.insert(0, src + " ");
                } while (src != child);
                result.insert(0, node + " ");
                System.out.println(counter);
                System.out.println(result);
                System.exit(0);
            }
            visited[child] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scanner = new Reader();
        int numNodes = scanner.nextInt();
        int numEdges = scanner.nextInt();

        List<Integer>[] graph = new List[numNodes + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < numEdges; i++) {
            int src = scanner.nextInt();
            int dst = scanner.nextInt();
            graph[src].add(dst);
        }
        boolean[] visited = new boolean[numNodes + 1];
        int[] parents = new int[numNodes + 1];
        for (int i = 1; i <= numNodes; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, parents);
            }
            visited[i] = false;
        }
        System.out.println("IMPOSSIBLE");
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
