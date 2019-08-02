package com.my.leetcode.new_21_game;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().new21Game(21, 17, 10));
        //0.73278
        System.out.println(new Solution().new21Game(6, 1, 10));
        //0.60000
        System.out.println(new Solution().new21Game(0, 0, 2));
        //1.0
        System.out.println(new Solution().new21Game(10, 1, 10));
        //1.00000
        System.out.println(new Solution().new21Game(2, 2, 2));
        //0.75
        System.out.println(new Solution().new21Game(1,0,3));
        //1.0
        System.out.println(new Solution().new21Game(2,2,3));
        //0.44444
    }

    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) return 1;
        double dp[] = new double[N + 1];
        double res = 0;
        dp[0] = 1;
        double window = 1.0;
        for (int i = 1; i <= N; ++i) {
            dp[i] = window / W;
            if (i >= W) {
                window -= dp[i - W];
            }
            if (i >= K) {
                res += dp[i];
            } else {
                window += dp[i];
            }
        }
        return res;
    }
}
