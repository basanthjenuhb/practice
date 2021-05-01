import java.util.Scanner;

public class TwoSets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        long sum = getTotalSum(num);
        if (sum % 2 == 1) {
            System.out.println("NO");
            return;
        }
        int i = 0;
        if (num % 2 == 0) {
            i = 1;
        }
        int low = i, high = num;
        int[] set1 = new int[num/2 + 1]; int p1 = 0;
        int[] set2 = new int[num/2 + 1]; int p2 = 0;
        while (low < high) {
            if (low != 0) {
                set1[p1++] = low;
            }
            low++;
            set1[p1++] = high--;
            set2[p2++] = low++;
            set2[p2++] = high--;
        }
        StringBuilder res = new StringBuilder();
        res.append("YES\n").append(p1).append("\n");
        for (int val: set1) {
            if (val != 0) {
                res.append(val).append(" ");
            }
        }
        res.append("\n").append(p2).append("\n");
        for (int val: set2) {
            if (val != 0) {
                res.append(val).append(" ");
            }
        }
        System.out.println(res);
    }

    private static long getTotalSum(long n) {
        return (n * (n + 1)) / 2;
    }
}
