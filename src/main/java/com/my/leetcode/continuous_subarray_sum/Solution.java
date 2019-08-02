package com.my.leetcode.continuous_subarray_sum;

public class Solution {

    public static void main(String[] args) {
        //System.out.println(new Solution().checkSubarraySum(new int[] {23,2,6,4,7}, 6));
        System.out.println(new Solution().checkSubarraySum(new int[] {0,0}, 0));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        for (int kk = 2; kk <= nums.length; kk++) {
            int prevSum = 0;
            for (int i = 0; i < kk; i++) {
                prevSum += nums[i];
            }
            if (k == 0 && prevSum == 0 || k != 0 && prevSum % k == 0) return true;

            for (int i = kk; i < nums.length; i++) {
                prevSum -= nums[i - kk];
                prevSum += nums[i];
                if (k == 0 && prevSum == 0 || k != 0 && prevSum % k == 0) return true;
            }
        }

        return false;
    }
}