import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CycleFinding {

    private static final int MOD = 1_000_000_007;

    private static class Edge {
        int source;
        int destination;
        int cost;

        public Edge(int source, int destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        Reader scanner = new Reader();
        int numNodes = scanner.nextInt();
        int numEdges = scanner.nextInt();

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int cost = scanner.nextInt();
            edges.add(new Edge(source, destination, cost));
        }

        long[] distances = new long[numNodes + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[1] = 0;
        int[] parents = new int[numNodes + 1];
        for (int i = 0; i < numNodes; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] == Long.MAX_VALUE) {
                    continue;
                }
                if (distances[edge.destination] > distances[edge.source] + edge.cost) {
                    distances[edge.destination] = distances[edge.source] + edge.cost;
                    parents[edge.destination] = edge.source;
                }
            }
        }

        int targetNode = 0;
        for (Edge edge : edges) {
            if (distances[edge.source] == Long.MAX_VALUE) {
                continue;
            }
            if (distances[edge.destination] > distances[edge.source] + edge.cost) {
                targetNode = edge.destination;
            }
        }
        if (targetNode == 0) {
            System.out.println("NO");
            return;
        }
        StringBuilder result = new StringBuilder();
        result.append("YES\n").append(targetNode);
        int tempNode = targetNode;
        do {
            tempNode = parents[tempNode];
            result.append(" ").append(tempNode);
        } while (tempNode != targetNode);
        System.out.println(result);
    }

    static class Reader {
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
