package com.my.leetcode.buy_sell_stock.complexity;

public class ComplexitySolver {

    public static void main(String[] args) {
        System.out.println(new ComplexitySolver().c(100));
    }

    //E(N) = N * E(0) + (N - 1) * E(1) ... 1 * E(N - 1)
    private int c(int N) {
        int result = 0;
        for (int i = 1; i < N; i++) {
            result += E(i);
        }
        return result;
    }

    private int E(int N) {
        if (N == 0) return 1;

        int result = 0;
        for (int j = 0; j < N; j++) {
            result += (N - j) * E(j);
        }
        return result;
    }
}