//import java.util.Arrays;
//import java.util.Scanner;
//
//public class NumberSpiral {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int tests = scanner.nextInt();
//        int[][] inputs = new int[tests][2];
//        int maxSize = 0;
//        for (int i = 0; i < inputs.length; i++) {
//            inputs[i][0] = scanner.nextInt() - 1;
//            inputs[i][1] = scanner.nextInt() - 1;
//            maxSize = Math.max(maxSize, inputs[i][0] + 1);
//            maxSize = Math.max(maxSize, inputs[i][1] + 1);
//        }
//
//        int size = maxSize;
//        int[][] grid = new int[size][size];
//        int counter = size * size;
//        int i = 0, j = size - 1;
//        while (true) {
//            while (i < size) {
//                grid[i][j] = counter--;
//                i++;
//            }
//            i--;
//            while (j >= 0) {
//                grid[i][j] = counter--;
//                j--;
//            }
//            i--;
//            j++;
////            while ()
//        }
//
//        for (int[] line: grid) {
//            for (int num: line) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }
//        StringBuilder result = new StringBuilder();
//        for (int[] input : inputs) {
//            result.append(grid[input[0]][input[1]]).append("\n");
//        }
//        System.out.println(result);
//    }
//}
