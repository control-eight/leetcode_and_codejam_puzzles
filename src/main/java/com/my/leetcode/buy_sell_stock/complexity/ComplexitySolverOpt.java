package com.my.leetcode.buy_sell_stock.complexity;

import java.math.BigDecimal;

public class ComplexitySolverOpt {

    public static void main(String[] args) {
        //System.out.println(new ComplexitySolverOpt().c(100000));

        for (int i = 1; i <= 18; i++) {
            System.out.println("i = " + i + ", complexity = " + new ComplexitySolverOpt().c(i));
        }
    }

    //sum(i = (1 .. N))(E(i))
    //E(0) = 1
    //E(N) = N * E(0) + (N - 1) * E(1) ... 1 * E(N - 1)
    public BigDecimal c(int N) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal[] E = new BigDecimal[N];
        E[0] = BigDecimal.ONE;
        for (int i = 1; i < N; i++) {
            E[i] = BigDecimal.ZERO;
            for (int j = 0; j < i; j++) {
                E[i] = E[i].add(BigDecimal.valueOf((i - j)).multiply(E[j]));
            }
            result = result.add(E[i]);
        }
        return result;
    }
}