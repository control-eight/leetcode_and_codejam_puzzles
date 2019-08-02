package com.my.leetcode.sort_transformed_array;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        base();
        /*System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-98,-97,-93,-90,-89,-89,-86,-84,-82,-81,-78,-78,-73,-70,-68,-68,-67,-66,-63,-62,-61,-60,-59,-54,-54,-53,-50,-50,-48,-48,-47,-43,-43,-42,-42,-37,-33,-30,-28,-23,-21,-21,-20,-19,-19,-17,-17,-9,-7,-4,-3,-3,-2,0,0,7,8,11,11,20,21,25,27,30,33,33,36,40,40,41,49,50,50,52,54,61,64,65,65,67,69,72,73,74,74,76,78,79,81,83,84,84,85,85,86,88,89,89,93,97},
                4,-64,25
        )));*/
        //[-231,-227,-195,-195,25,25,169,253,253,345,345,445,669,925,925,1213,1705,2269,2269,2269,2269,2685,2685,2905,2905,3133,3133,3613,3865,3865,4125,4953,5545,6493,6493,6825,6825,7513,7869,8233,9769,9769,10173,10173,11005,11869,12313,12313,12313,12765,12765,13225,13225,13693,14653,14653,15145,15145,16153,16669,17193,17193,17725,18265,18265,18813,19369,19369,19933,19933,21085,21673,22269,22269,22873,22873,22873,22873,23485,23485,24105,24105,25369,26013,26013,26013,28669,29353,29353,31453,31453,32169,33625,35113,37405,37405,38185,40573,43869,44713]
    }

    private static void base() {
        /*System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},0,0,5
        )));
        //[5,5,5,5]
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},0,3,5
        )));
        //[-7,-1,11,17]
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},0,-3,5
        )));
        //[-7,-1,11,17]
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},1,0,5
        )));
        //[9,9,21,21]*/
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},-1,0,5
        )));
        //[-11,-11,1,1]
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},1,3,5
        )));
        //[3,9,15,33]
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},1,-3,5
        )));
        //[3,9,15,33]
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},-1,3,5
        )));
        //[-23,-5,1,7]
        System.out.println(Arrays.toString(new Solution().sortTransformedArray(
                new int[] {-4,-2,2,4},-1,3,5
        )));
        //[-23,-5,1,7]
    }

    //a*x^2 + b*x

    //b == 0
        //a == 0
        //output 0
        //a > 0
        //from middle
        //a < 0
        //from edges
    //a == 0
        //b > 0
        //from smallest
        //b < 0
        //from largest
    // a > 0 && b > 0
        //from middle
    // a > 0 && b < 0
        //from some point
    // a < 0 && b > 0
        //from edges
    // a < 0 && b < 0
        //from edges

    //apply c

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        if (a == 0 && b == 0) {
            Arrays.fill(result, c);
        } else {
            if (a == 0) {
                if (b > 0) {
                    return addC(fromPoints(nums, result, a, b, 0, 1), c);
                } else {
                    return addC(fromPoints(nums, result, a, b, nums.length - 1, nums.length), c);
                }
            }
            if (a > 0) {
                int min = findMin(nums, a, b);
                return addC(fromPoints(nums, result, a, b, min, min + 1), c);
            } else {
                return addC(fromPoints(nums, result, a, b, nums.length - 1, 0), c);
            }
        }
        return result;
    }

    private int[] fromPoints(int[] nums, int[] result, int a, int b, int left, int right) {
        int l = left;
        int r = right;
        int i = 0;
        boolean reverse = l > r;
        while ((l >= 0 || r < nums.length) && (reverse? l >= r: l <= r)) {
            Integer lValue = null;
            Integer rValue = null;

            if (l >= 0) {
                lValue = calc(l, nums, a, b);
            }
            if (r < nums.length) {
                rValue = calc(r, nums, a, b);
            }

            if (lValue == null || rValue != null && rValue.compareTo(lValue) < 0) {
                result[i++] = rValue;
                r++;
            } else {
                result[i++] = lValue;
                l--;
            }
        }
        return result;
    }

    private int findMin(int[] nums, int a, int b) {
        int min = Integer.MAX_VALUE;
        int minI = -1;
        for (int i = 0; i < nums.length; i++) {
            if (calc(i, nums, a, b) < min) {
                min = calc(i, nums, a, b);
                minI = i;
            }
        }
        return minI;
    }

    private int calc(int i, int[] nums, int a, int b) {
        return a * nums[i] * nums[i] + b * nums[i];
    }

    private int[] addC(int[] res, int c) {
        for (int i = 0; i < res.length; i++) {
            res[i] += c;
        }
        return res;
    }
}
