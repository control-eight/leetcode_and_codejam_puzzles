package com.my.leetcode.divide_two_integers;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().divide(10, 3));
        System.out.println(new Solution().divide(3, 3));
        System.out.println(new Solution().divide(12, 3));
        System.out.println(new Solution().divide(15, 4));
        System.out.println(new Solution().divide(51, 9));
        System.out.println(new Solution().divide(7, -3));
        System.out.println(new Solution().divide(2147483647, 1));
        System.out.println(new Solution().divide(2147483647, 2));
        System.out.println(new Solution().divide(-2147483648, 2));
        System.out.println(new Solution().divide(-2147483648, 3));
    }

public int divide(int dividend, int divisor) {
    if (divisor == Integer.MIN_VALUE) return dividend == Integer.MIN_VALUE? 1: 0;
    if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

    if (divisor == 1) return dividend;
    if (divisor == -1) return -dividend;
    if (divisor == 2) return dividend >> 1;
    if (divisor == -2) return -dividend >> 1;

    boolean negative = (dividend ^ divisor) < 0;

    int result = 0;
    if (dividend == Integer.MIN_VALUE) {
        dividend += Math.abs(divisor);
        result = 1;
    }
    result += dividePositive(Math.abs(dividend), Math.abs(divisor));
    return negative ? -result: result;
}

private int dividePositive(int dividend, int divisor) {
    int result = 0;
    while (dividend >= divisor) {
        int tmp = divisor;
        int count = 1;
        while (tmp + tmp < dividend && tmp + tmp > 0) {
            tmp += tmp;
            count += count;
        }
        dividend -= tmp;
        result += count;
    }
    return result;
}

}