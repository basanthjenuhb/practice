import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FlightDiscount {

    static class Package {
        int id;
        long cost;
        boolean discountUsed;
        Package parent;

        public Package(int id, long cost, boolean discountUsed, Package parent) {
            this.id = id;
            this.cost = cost;
            this.discountUsed = discountUsed;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Package{" +
                "destination=" + id +
                ", cost=" + cost +
                ", discountUsed=" + discountUsed +
//                ", parent=" + parent +
                '}';
        }
    }

    public static void main(String[] args) throws Exception {
        Reader reader = new Reader();
        int numNodes = reader.nextInt();
        int numEdges = reader.nextInt();

        List<Package>[] graph = new List[numNodes + 1];

        for (int i = 0; i < numEdges; i++) {
            int source = reader.nextInt();
            int dest = reader.nextInt();
            int cost = reader.nextInt();

            if (graph[source] == null) {
                graph[source] = new ArrayList<>();
            }
            graph[source].add(new Package(dest, cost, false, null));
        }

        Queue<Package> queue = new PriorityQueue<>(Comparator.comparingLong(p -> p.cost));
        long[] distances = new long[numNodes + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        long[] discount = new long[numNodes + 1];
        Arrays.fill(discount, Long.MAX_VALUE);
        queue.add(new Package(1, 0, false, null));
        while (!queue.isEmpty()) {
            Package pack = queue.poll();
            if (pack.discountUsed && pack.cost > discount[pack.id]) {
                continue;
            }
            if (!pack.discountUsed && pack.cost > distances[pack.id]) {
                continue;
            }
            if (pack.discountUsed) {
                discount[pack.id] = Math.min(discount[pack.id], pack.cost);
            } else {
                distances[pack.id] = Math.min(distances[pack.id], pack.cost);
            }
            if (graph[pack.id] == null) {
                continue;
            }
            for (Package child: graph[pack.id]) {
                queue.add(new Package(
                    child.id,
                    child.cost + pack.cost,
                    pack.discountUsed,
                    pack
                ));
                if (!pack.discountUsed) {
                    queue.add(new Package(
                        child.id,
                        child.cost / 2 + pack.cost,
                        true,
                        pack
                    ));
                }
            }
        }
        System.out.println(discount[numNodes]);
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
