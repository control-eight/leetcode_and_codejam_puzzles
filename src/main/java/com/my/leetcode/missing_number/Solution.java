package com.my.leetcode.missing_number;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().missingNumber(new int[] {1, 0}));
        System.out.println(new Solution().missingNumber(new int[] {1, 2}));
        System.out.println(new Solution().missingNumber(new int[] {0, 1, 3}));
    }

    public int missingNumber(int[] nums) {
        if (!checkForZero(nums)) return 0;

        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            while (v < nums.length && v != nums[v]) {
                int tmp = nums[v];
                nums[v] = v;
                v = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums[nums.length - 1] + 1;
    }

    private boolean checkForZero(int[] nums) {
        for (int num : nums) {
            if (num == 0) {
                return true;
            }
        }
        return false;
    }
}
