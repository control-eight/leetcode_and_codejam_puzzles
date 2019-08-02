package com.my.leetcode.guess_num_hi_lo_II;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().getMoneyAmount(4));
        System.out.println(new Solution().getMoneyAmount(5));
        System.out.println(new Solution().getMoneyAmount(6));
        System.out.println(new Solution().getMoneyAmount(7));
        //10
        System.out.println(new Solution().getMoneyAmount(10));
    }

    public int getMoneyAmount(int n) {
        if (n == 1) return 0;
        int[][] dp = new int[n+1][n+2];
        for (int k = 2; k <= n; k++) {
            int gMin = Integer.MAX_VALUE;
            for (int i = 1; i <= n - (k - 1); i++) {
                int min = Integer.MAX_VALUE;
                for (int j = i; j < i + k; j++) {
                    min = Math.min(min, Math.max(dp[j - i][i] + j, j + dp[i + k - (j + 1)][j + 1]));
                }
                dp[k][i] = min;
                gMin = Math.min(gMin, min);
            }
            dp[k][n + 1] = gMin;
        }
        return dp[n][n + 1];
    }
}
