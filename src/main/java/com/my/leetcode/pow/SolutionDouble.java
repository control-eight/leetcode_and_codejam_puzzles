package com.my.leetcode.pow;

public class SolutionDouble {

    public static void main(String[] args) {
        System.out.println(new SolutionDouble().myPow(2.0, 10));
        //1024
        System.out.println(new SolutionDouble().myPow(2.1, 3));
        //9.261
        System.out.println(new SolutionDouble().myPow(2.0, -2));
        //0.25
        System.out.println(new SolutionDouble().myPow(2.0, -2147483648));
        //0.0

        System.out.println(new SolutionDouble().myPow2(2.0, 10));
        //1024
        System.out.println(new SolutionDouble().myPow2(2.1, 3));
        //9.261
        System.out.println(new SolutionDouble().myPow2(2.0, -2));
        //0.25
        System.out.println(new SolutionDouble().myPow2(2.0, -2147483648));
        //0.0
    }

    private static long[] masks = new long[64];
    static {
        masks[0] = 1;
        for (int i = 1; i < 64; i++) {
            masks[i] = masks[i - 1] << 1;
        }
    }

    public double myPow(double x, int y) {
        if (y == 0) return 1;

        double res = 1;
        long mask = 1;
        double sign = Math.signum(y);
        long z = Math.abs((long)y);
        while (mask <= z) {
            if ((mask & z) >= 1) {
                res *= x;
            }
            x *= x;
            mask <<= 1;
        }
        return sign == 1.0? res: 1/res;
    }

    public double myPow2(double x, int y) {
        if (y == 0) return 1;

        double res = 1;
        double sign = Math.signum(y);
        long z = Math.abs((long)y);
        int i = 0;
        while (masks[i] <= z) {
            if ((masks[i] & z) >= 1) {
                res *= x;
            }
            x *= x;
            i++;
        }
        return sign == 1.0? res: 1/res;
    }
}
