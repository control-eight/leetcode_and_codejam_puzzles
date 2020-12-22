package com.my.leetcode.buy_sell_stock.complexity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ComplexitySolverOptChE {

    public static void main(String[] args) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        for (int i = 1; i <= 10000; i++) {
            BigDecimal c1 = new ComplexitySolverOptChE().c(i);
            BigDecimal c2 = new ComplexitySolverOpt().c(i);
            if (c2.intValue() != 0) {
                /*System.out.println("i = " + i + ", complexity = " + c1 + " vs " + c2 + " = "
                        + c1.divide(c2, mc).setScale(2, RoundingMode.HALF_UP).doubleValue());*/
                System.out.println(c1.divide(c2, mc).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
        }
    }

    //2.618
    private BigDecimal c(int N) {
        BigDecimal result = BigDecimal.ZERO;
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        for (int i = 1; i < N; i++) {
            result = result.add(BigDecimal.valueOf(2.618).pow(i, mc));
        }
        //.divide(BigDecimal.valueOf(2.24), mc)
        return result;
    }
}