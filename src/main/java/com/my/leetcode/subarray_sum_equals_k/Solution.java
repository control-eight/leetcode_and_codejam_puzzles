package com.my.leetcode.subarray_sum_equals_k;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sums = new HashMap<>();
        int sum = 0;
        int res = 0;
        sums.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sums.containsKey(sum - k)) {
                res += sums.get(sum - k);
            }
            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
