package com.my.leetcode.coin_change;

public class SolutionDP {

    public int coinChange(int[] coins, int amount) {
        int[][] solutions = new int[amount + 1][coins.length + 1];
        for (int j = 0; j < coins.length + 1; j++) {
            solutions[0][j] = 0;
        }
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] < 0) {
                    solutions[i][j] = 100000;
                } else {
                    solutions[i][j] = solutions[i - coins[j]][coins.length] + 1;
                }
                min = Math.min(min, solutions[i][j]);
            }
            solutions[i][coins.length] = min;
        }
        int res = solutions[amount][coins.length];
        return res >= 100000? -1: res;
    }
}
