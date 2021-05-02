import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Just add values from porevious column
 * Add all values in last column
 */
public class ArrayDescription {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        Reader scanner = new Reader();
        int numValues = scanner.nextInt();
        int maxValue = scanner.nextInt();

        int[][] dp = new int[maxValue + 2][numValues];
        int[] nums = new int[numValues];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = scanner.nextInt();
        }

        if (nums[0] == 0) {
            for (int i = 1; i < dp.length - 1; i++) {
                dp[i][0] = 1;
            }
        } else {
            dp[nums[0]][0] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                for (int j = 1; j < dp.length - 1; j++) {
                    dp[j][i] = (dp[j][i] + dp[j - 1][i - 1]) % MOD;
                    dp[j][i] = (dp[j][i] + dp[j][i - 1]) % MOD;
                    dp[j][i] = (dp[j][i] + dp[j + 1][i - 1]) % MOD;
                }
            } else {
                int rowIndex = nums[i];
                dp[rowIndex][i] = ( dp[rowIndex][i] + dp[rowIndex - 1][i - 1]) % MOD;
                dp[rowIndex][i] = ( dp[rowIndex][i] + dp[rowIndex][i - 1]) % MOD;
                dp[rowIndex][i] = ( dp[rowIndex][i] + dp[rowIndex + 1][i - 1]) % MOD;
            }
        }

        int maxWays = 0;
        for (int[] ints : dp) {
            maxWays = (maxWays + ints[ints.length - 1]) % MOD;
        }
        System.out.println(maxWays);
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
