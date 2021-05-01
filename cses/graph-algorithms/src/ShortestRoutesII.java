import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ShortestRoutesII {

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

    public static void main(String[] args) throws Exception {
        Reader scanner = new Reader();
        int numNodes = scanner.nextInt();
        int numEdges = scanner.nextInt();
        int numQueries = scanner.nextInt();

        long[][] graph = new long[numNodes + 1][numNodes + 1];
        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[i].length; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Long.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int cost = scanner.nextInt();
            graph[source][destination] = Math.min(cost, graph[source][destination]);
            graph[destination][source] = Math.min(cost, graph[destination][source]);
        }

        for (int k = 1; k < graph.length; k++) {
            for (int i = 1; i < graph.length; i++) {
                for (int j = 1; j < graph.length; j++) {
                    if (graph[i][k] != Long.MAX_VALUE
                        && graph[k][j] != Long.MAX_VALUE
                        && graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numQueries; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            long distance = graph[source][destination];
            if (distance == Long.MAX_VALUE) {
                result.append("-1\n");
            } else {
                result.append(distance).append("\n");
            }
        }
        System.out.println(result);
    }
}
