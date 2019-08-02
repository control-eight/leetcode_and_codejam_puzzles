package com.my.leetcode.new_21_game;

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().new21Game(21, 17, 10));
        //0.73278
        System.out.println(new Solution2().new21Game(6, 1, 10));
        //0.60000
        System.out.println(new Solution2().new21Game(0, 0, 2));
        //1.0
        System.out.println(new Solution2().new21Game(10, 1, 10));
        //1.00000
        System.out.println(new Solution2().new21Game(2, 2, 2));
        //0.75
        System.out.println(new Solution2().new21Game(1,0,3));
        //1.0
        System.out.println(new Solution2().new21Game(2,2,3));
        //0.44444
    }

    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) return 1;

        double result = 0;
        double c = 1.0;
        double[] cache = new double[K];
        for (int i = N - W + 1; i < K; i++, c++) {
            result += probability(i, W, cache) * (c/W);
        }
        return 1 - result;
    }

    private double probability(int N, int W, double[] cache) {
        if (N < 0) return 0;
        if (cache[N] > 0) return cache[N];
        if (N == 0) {
            return 1.0;
        }
        double result = 0;
        for (int i = Math.max(N - W, 0); i < N; i++) {
            result += probability(i, W, cache) * 1.0/W;
        }
        cache[N] = result;
        return result;
    }
}
