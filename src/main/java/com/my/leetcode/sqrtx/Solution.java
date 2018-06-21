package com.my.leetcode.sqrtx;

/**
 * @author abykovsky
 * @since 4/1/18
 */
public class Solution {

    public int mySqrt(int x) {
        for (int i = 2; i <= x; i++) {
            if ((long)i*i > x) {
                return i - 1;
            }
        }
        return x;
    }
}
