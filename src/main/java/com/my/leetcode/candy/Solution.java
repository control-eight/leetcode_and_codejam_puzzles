package com.my.leetcode.candy;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().candy(new int[] {1, 5, 2, 1, 0}));
        //11
        System.out.println(new Solution().candy(new int[] {1, 0, 2}));
        //5
        System.out.println(new Solution().candy(new int[] {1, 2, 2}));
        //4
        System.out.println(new Solution().candy(new int[] {1, 3, 2, 2, 1}));
        //7
        System.out.println(new Solution().candy(new int[] {2, 0, 1}));
        //5
        System.out.println(new Solution().candy(new int[] {0, 1, 2, 5, 3, 2, 7}));
        //15
    }

    public int candy(int[] ratings) {
        int i = 0;
        int j = ratings.length - 1;
        int left = 0;
        int right = 0;
        int result = 0;
        while (i < j) {
            if (ratings[i] < ratings[j]) {
                result += left + 1;
                if (ratings[i] > ratings[i + 1]) {
                    result += left == 0? 1: 0;
                    left = 0;
                } else if (ratings[i] == ratings[i + 1])  {
                    left = 0;
                } else {
                    left++;
                }
                i++;
            } else {
                result += right + 1;
                if (ratings[j] > ratings[j - 1]) {
                    result += right == 0? 1: 0;
                    right = 0;
                } else if (ratings[j] == ratings[j - 1]) {
                    right = 0;
                } else {
                    right++;
                }
                j--;
            }
        }
        int add = 0;
        if (i > 0 && ratings[i] > ratings[i - 1]) {
            add = Math.max(left, add);
        }
        if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
            add = Math.max(right, add);
        }
        result += add + 1;
        return result;
    }
}
