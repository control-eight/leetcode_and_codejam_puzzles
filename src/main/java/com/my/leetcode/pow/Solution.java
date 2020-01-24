package com.my.leetcode.pow;

public class Solution {

    public static void main(String[] args) {
        long[] masks = new long[64];
        masks[0] = 1;
        for (int i = 1; i < 64; i++) {
            masks[i] = masks[i - 1] << 1;
        }
        System.out.println(pow2(2,8, masks));
        System.out.println(pow2(2,3, masks));
        System.out.println(pow2(2,4, masks));
        System.out.println(pow2(2,7, masks));
        System.out.println(pow2(2,11, masks));
        System.out.println(pow2(3,8, masks));
        System.out.println(pow2(3,11, masks));
        System.out.println(pow2(3,11, masks));
    }

    public static long pow(long x, long y) {
        long res = 1;
        long mask = 1;
        while (mask <= y) {
            if ((mask & y) >= 1) {
                res *= x;
            }
            x *= x;
            mask <<= 1;
        }
        return res;
    }

    public static long pow2(long x, long y, long[] masks) {
        long res = 1;
        int i = 0;
        while (masks[i] <= y) {
            if ((masks[i] & y) >= 1) {
                res *= x;
            }
            x *= x;
            i++;
        }
        return res;
    }
}
