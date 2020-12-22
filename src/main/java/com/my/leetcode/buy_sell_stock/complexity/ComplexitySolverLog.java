package com.my.leetcode.buy_sell_stock.complexity;

public class ComplexitySolverLog {

    static long[][] multMatrix(long[][] a, long[][] b) {
        return new long[][]{
                {a[0][0] * b[0][0] + a[0][1] * b[1][0], a[0][0] * b[0][1] + a[0][1] * b[1][1]},
                {a[1][0] * b[0][0] + a[1][1] * b[1][0], a[1][0] * b[0][1] + a[1][1] * b[1][1]}
        };
    }

    static long[][] powMatrix(long[][] m, int pow) {
        if (pow == 1) return m;
        if (pow % 2  == 1) {
            return multMatrix(m, powMatrix(m, pow - 1));
        } else {
            long[][] subPow = powMatrix(m, pow / 2);
            return multMatrix(subPow, subPow);
        }
    }

    static long nRecurrent(int n) {
        if (n == 0) return 1;
        if (n == 1) return 3;

        long[][] recurrentMatrix = new long[][]{{3, -1}, {1, 0}};
        long[][] matrixPower = powMatrix(recurrentMatrix, n - 1);

        return matrixPower[0][0] * 3 + matrixPower[0][1];
    }

    public static void main(String[] params) {
        for (int i = 0; i < 45; ++i) {
            System.out.println(nRecurrent(i) + " vs "
                    + ((3+Math.sqrt(5))/(2*Math.sqrt(5))*Math.pow(((3+Math.sqrt(5))/2), i)));
        }
    }
}
