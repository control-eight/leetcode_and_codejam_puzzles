package com.my.leetcode.optimal_acc_balancing;

import java.util.Arrays;
import java.util.Random;

public class Generator {

    public static void main(String[] args) {
        //{10,11,6}, {12,13,7}, {14,15,2}, {14,16,2}, {14,17,2}, {14,18,2}

        int testCases = (int) 1e6;
        int peopleCount = 7;
        int transactionCount = 10;
        int transactionMaxAmount = 10;

        Random random = new Random(1234);

        for (int t = 1; t <= testCases; t++) {
            int ttt = (int) (random.nextDouble() * transactionCount);
            int[][] arr = new int[ttt][3];
            for (int i = 0; i < ttt; i++) {
                int p = (int) (random.nextDouble() * peopleCount);
                int p1 = (int) (random.nextDouble() * peopleCount);
                while (p1 == p) {
                    p1 = (int) (random.nextDouble() * peopleCount);
                }
                arr[i] = new int[] {p, p1, (int) (random.nextDouble() * transactionMaxAmount)};
            }
            int expected = new Solution2().minTransfers(arr);
            int actual = new Solution().minTransfers(arr);
            if (actual != expected) {
                System.out.println("expected = " + expected + " actual = " + actual + " arr = "
                    + Arrays.deepToString(arr));
                break;
            } else {
                System.out.println(t);
            }
        }
    }
}
