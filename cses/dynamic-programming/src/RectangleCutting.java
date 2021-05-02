import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Recurrence relation
 * f(i, j)
 * 	- if (i == j) then 0
 * 	- min(f(i, j), 1 + f(k, j) + f(i - k, j)) for k in (1, i)
 * 	- min(f(i, j), 1 + f(i, k) + f(i, j - k)) for k in (1, j)
 */
public class RectangleCutting {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        Reader scanner = new Reader();
        int height = scanner.nextInt();
        int width = scanner.nextInt();

        int[][] dp = new int[height + 1][width + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                } else if (i == 1) {
                    dp[i][j] = j - 1;
                } else if (j == 1) {
                    dp[i][j] = i - 1;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = 1; k < i; k++) {
                        min = Math.min(min, 1 + dp[k][j] + dp[i - k][j]);
                    }

                    for (int k = 1; k < j; k++) {
                        min = Math.min(min, 1 + dp[i][k] + dp[i][j - k]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        System.out.println(dp[dp.length - 1][dp[0].length - 1]);
    }


    /*************************************************/
    /**
     * Ignore Below code. all are utility functions
     */

    /**
     * Print array
     */
    private static void printArray(Object[] array) {
        System.out.println(
            Arrays
                .deepToString(array)
                .replace("], ", "]\n")
        );
    }

    /**
     * IGNORE
     * This is required for fast JAVA I/O
     */
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
