package com.my.leetcode.burst_balloons;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxCoins(new int[]{3,1,5,8}));
        //167
    }

    public int maxCoins(int[] nums) {
        Integer[][] cache = new Integer[nums.length][nums.length];
        return maxCoins(nums, cache, 0, nums.length - 1);
    }

    private int maxCoins(int[] nums, Integer[][] cache, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (cache[i][j] != null) {
            return cache[i][j];
        }
        int result = 0;

        int left = i == 0 ? 1 : nums[i - 1];
        int right = j == nums.length - 1 ? 1 : nums[j + 1];

        for (int k = i; k <= j; k++) {
            result = Math.max(maxCoins(nums, cache, i, k - 1) + left * nums[k] * right
                    + maxCoins(nums, cache, k + 1, j), result);
        }
        cache[i][j] = result;
        return result;
    }
}
