package com.my.leetcode.buy_sell_stock.complexity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class ComplexitySolverOptChEFixXx {

    public static void main(String[] args) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        //for (int i = 1; i <= 18; i++) {
        long[] time = new long[3];
        for (int i = 2; i <= 20000; i++) {

            long start = System.nanoTime();
            BigDecimal c1 = new ComplexitySolverOptChEFixXx().c2(i);
            time[0] += System.nanoTime() - start;

            start = System.nanoTime();
            //BigDecimal c2 = new ComplexitySolverOptChEFixXx().c3(i);
            BigDecimal c2 = BigDecimal.ONE;
            time[1] += System.nanoTime() - start;

            start = System.nanoTime();
            BigDecimal cp = new ComplexitySolverOptChEFixXx().cp(i);
            time[2] += System.nanoTime() - start;
            /*System.out.println(
                    "i = " + i
                    + ", complexity = "
                    + c1
                    + " vs " + c2
                    + " vs " + cp
                    + " = [" + c1.divide(c2, mc).setScale(2, RoundingMode.HALF_UP).doubleValue()
                    + ", "
                    + c1.divide(cp, mc).setScale(2, RoundingMode.HALF_UP).doubleValue()
                    + ", "
                    + c2.divide(cp, mc).setScale(2, RoundingMode.HALF_UP).doubleValue()
                    + "]"
            );*/
            System.out.println(
                    "i = " + i
                    + ", complexity = "
                    + c1.divide(cp, mc).setScale(2, RoundingMode.HALF_UP).doubleValue());
            //System.out.println(c1.divide(c2, mc).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        System.out.println("time: " + Arrays.toString(time));
    }

    private BigDecimal c(int N) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal allPrev = BigDecimal.ZERO;
        BigDecimal last = BigDecimal.ZERO;
        for (int i = 1; i <= N; i++) {
            last = BigDecimal.ONE.add(last).add(allPrev);
            result = result.add(last);
            allPrev = allPrev.add(last);
        }
        return result;
    }

    private BigDecimal c2(int N) {
        BigDecimal result = BigDecimal.valueOf(4);
        BigDecimal prev = BigDecimal.valueOf(3);
        BigDecimal prevPrev = BigDecimal.ONE;
        for (int i = 2; i < N; i++) {
            BigDecimal tmp = prev;
            prev = prev.multiply(BigDecimal.valueOf(3)).subtract(prevPrev);
            result = result.add(prev);
            prevPrev = tmp;
        }
        return result;
    }

    private BigDecimal c3(int N) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        BigDecimal sqrt5 = BigDecimal.valueOf(5).sqrt(mc);
        //(3 + sqrt(5)) / (2 * sqrt(5))
        BigDecimal c0 = BigDecimal.valueOf(3).add(sqrt5).divide(BigDecimal.valueOf(2).multiply(sqrt5), mc);
        //(3 + sqrt(5)) / 2
        BigDecimal divide = BigDecimal.valueOf(3).add(sqrt5).divide(BigDecimal.valueOf(2), mc);

        BigDecimal result = BigDecimal.ZERO;
        for (int i = 1; i < N; i++) {
            //((3 + sqrt(5)) / 2)^N
            result = result.add(c0.multiply(divide.pow(i)));
        }
        return result;
    }

    private BigDecimal c4(int N) {
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 1; i < N; i++) {
            result = result.add(BigDecimal.valueOf(1.17).multiply(BigDecimal.valueOf(2.618).pow(i))
                    .setScale(0, RoundingMode.HALF_UP));
        }
        return result;
    }

    private BigDecimal cp(int N) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
        BigDecimal sqrt5 = BigDecimal.valueOf(5).sqrt(mc);
        //5.5/sqrt(5) + 2.5
        BigDecimal c0 = BigDecimal.valueOf(5.5).divide(sqrt5, mc).add(BigDecimal.valueOf(2.5));
        //(3 + sqrt(5)) / 2
        BigDecimal divide = BigDecimal.valueOf(3).add(sqrt5).divide(BigDecimal.valueOf(2), mc);
        //(22 + 10 * sqrt(5)) / (10 + 6 * sqrt(5)) * 2.618^(N - 1)
        return BigDecimal.valueOf(1.89445178581).multiply(divide.pow(N-1));
    }

    private long c1(int N) {
        long result = 0;
        long allPrev = 0;
        long last = 0;
        for (int i = 1; i < N; i++) {
            last = 1 + last + allPrev;
            result += last;
            allPrev += last;
        }
        return result;
    }
}