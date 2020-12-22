package com.my.leetcode.buy_sell_stock.complexity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FibonacciComplexity {

    public static void main(String[] args) {
        MathContext mc = new MathContext(1000, RoundingMode.HALF_UP);
        //BigDecimal c = BigDecimal.valueOf(1.618);
        BigDecimal c = BigDecimal.ONE.add(BigDecimal.valueOf(5).sqrt(mc)).divide(BigDecimal.valueOf(2), mc);
        for (int i = 0; i <= 70; i++) {
            System.out.println("i = " + i + ", complexity = " + f(i) + " vs "
                    + c.pow(i, mc).divide(BigDecimal.valueOf(5).sqrt(mc), mc).longValue());
        }
    }

    private static long f(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;

        long prevPrev = 0;
        long prev = 1;
        long current = 0;
        for (int i = 2; i <= N; i++) {
            current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }
        return current;
    }
}
