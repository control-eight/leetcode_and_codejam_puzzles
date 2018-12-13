package com.my.leetcode.rotate_array;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        new Solution().rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }

    public void rotate(int[] nums, int k) {
        int l = k % nums.length;
        int count = 0;
        for (int j = 0; j < nums.length; j++) {
            int i = j;
            int tmp = nums[i];
            while (count < nums.length) {
                int next;
                if (i >= nums.length - l) {
                    next = l - (nums.length - i);
                } else {
                    next = i + l;
                }
                int nextTmp = nums[next];
                nums[next] = tmp;
                i = next;
                tmp = nextTmp;
                count++;
                if (i == j) break;
            }
            if (count == nums.length) break;
        }
    }
}
