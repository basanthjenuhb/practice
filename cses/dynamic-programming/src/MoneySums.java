import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MoneySums {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        Reader scanner = new Reader();
        int numCoins = scanner.nextInt();

        int[] coins = new int[numCoins + 1];
        int sum = 0;
        for (int i = 0; i < numCoins; i++) {
            coins[i + 1] = scanner.nextInt();
            sum += coins[i + 1];
        }

        boolean[][] dp = new boolean[numCoins + 1][sum + 1];
        dp[0][0] = true;
        for (int coinIndex = 1; coinIndex < dp.length; coinIndex++) {
            for (int target = 0; target < dp[coinIndex].length; target++) {
                dp[coinIndex][target] = dp[coinIndex - 1][target];
                if (target - coins[coinIndex] >= 0) {
                    dp[coinIndex][target] = dp[coinIndex][target] || dp[coinIndex - 1][target - coins[coinIndex]];
                }
            }
        }
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (int i = 1; i < dp[0].length; i++) {
            if (dp[numCoins][i]) {
                count++;
                result.append(i).append(" ");
            }
        }
        System.out.println(count);
        System.out.println(result);
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
