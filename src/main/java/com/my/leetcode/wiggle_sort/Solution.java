package com.my.leetcode.wiggle_sort;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        run(new int[] {3, 5, 2, 1, 6, 4});
        //[3,5,1,6,2,4]
        run(new int[] {1, 2, 3, 4, 5, 6});
        //[1,3,2,5,4,6]
    }

    private static void run(int[] nums) {
        new Solution().wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }
}
