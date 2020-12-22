package com.my.leetcode.buy_sell_stock.complexity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ComplexitySolverLogBigDecimal {

    static BigDecimal[][] multMatrix(BigDecimal[][] a, BigDecimal[][] b) {
        return new BigDecimal[][]{
                {a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0])),
                        a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]))},
                {a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0])),
                        a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]))}
        };
    }

    static BigDecimal[][] powMatrix(BigDecimal[][] m, int pow) {
        if (pow == 1) return m;
        if (pow % 2  == 1) {
            return multMatrix(m, powMatrix(m, pow - 1));
        } else {
            BigDecimal[][] subPow = powMatrix(m, pow / 2);
            return multMatrix(subPow, subPow);
        }
    }

    static BigDecimal nRecurrent(int n) {
        if (n == 0) return BigDecimal.ONE;
        if (n == 1) return BigDecimal.valueOf(3);

        BigDecimal[][] recurrentMatrix = new BigDecimal[][]{{BigDecimal.valueOf(3),
                BigDecimal.valueOf(-1)}, {BigDecimal.ONE, BigDecimal.ZERO}};
        BigDecimal[][] matrixPower = powMatrix(recurrentMatrix, n - 1);

        return matrixPower[0][0].multiply(BigDecimal.valueOf(3)).add(matrixPower[0][1]);
    }

    public static void main(String[] params) {
        MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
        for (int i = 2; i < 20000; ++i) {
            BigDecimal c1 = nRecurrent(i);
            BigDecimal c2 = calc(i);
            BigDecimal bigDecimal = c1.divide(c2, mc).setScale(2, RoundingMode.HALF_UP);
            if (bigDecimal.compareTo(BigDecimal.ONE) < 0) {
                System.out.println(c1 + " vs " + c2 + " = " + bigDecimal);
                break;
            }
            System.out.println(i);
        }
    }

    private static BigDecimal calc(int N) {
        MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
        BigDecimal sqrt5 = BigDecimal.valueOf(5).sqrt(mc);
        //(3 + sqrt(5)) / (2 * sqrt(5))
        BigDecimal c0 = BigDecimal.valueOf(3).add(sqrt5).divide(BigDecimal.valueOf(2).multiply(sqrt5), mc);
        BigDecimal divide = BigDecimal.valueOf(3).add(sqrt5).divide(BigDecimal.valueOf(2), mc);
        //T(N) = (3 + sqrt(5)) / (2 * sqrt(5)) * ((3 + sqrt(5)) / 2) ^ (N - 1)
        return c0.multiply(divide.pow(N));
    }
}
