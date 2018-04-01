package com.my.sqrtx;

/**
 * @author abykovsky
 * @since 4/1/18
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().mySqrt(2147483647));
        System.out.println(new Solution2().mySqrt(0));
        System.out.println(new Solution2().mySqrt(1));
        System.out.println(new Solution2().mySqrt(50));
        System.out.println(new Solution2().mySqrt(121));
        System.out.println(new Solution2().mySqrt(777));
    }

    public int mySqrt(int x) {
        return binarySearch(x, 0, (long) x + 1);
    }

    private int binarySearch(int x, long start, long end) {
        long mid = start + (end - start)/2;

        while (start < end - 1) {
            long square = mid * mid;
            if (square == x) {
                return (int) mid;
            } else if (square > x) {
                return binarySearch(x, start, mid);
            } else {
                return binarySearch(x, mid, end);
            }
        }

        return (int) mid;
    }
}
