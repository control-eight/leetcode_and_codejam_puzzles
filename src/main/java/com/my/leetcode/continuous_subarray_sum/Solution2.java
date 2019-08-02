package com.my.leetcode.continuous_subarray_sum;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().checkSubarraySum(new int[] {23,2,6,4}, 6));
        System.out.println(new Solution2().checkSubarraySum(new int[] {0,0}, 0));
        System.out.println(new Solution2().checkSubarraySum(new int[] {23,2,4,6,7}, -6));
        System.out.println(new Solution2().checkSubarraySum(new int[] {5,0,0}, 0));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> mod = new HashMap<>();
        int sum = nums[0];
        mod.put(k == 0? sum: sum % k, 0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == 0 && nums[i] == 0) return true;
            sum += nums[i];
            if (k != 0 && (sum % k == 0 || mod.containsKey(sum % k) && mod.get(sum % k) < i - 1)) {
                return true;
            }
            mod.put(k == 0? sum: sum % k, i);
        }
        return false;
    }
}