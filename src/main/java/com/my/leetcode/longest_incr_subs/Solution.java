package com.my.leetcode.longest_incr_subs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;

        List<Container> solutions = new ArrayList<>();
        solutions.add(new Container(nums[0], 1));
        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            int maxLocalCount = 0;
            for (Container solution : solutions) {
                if (nums[i] > solution.value) {
                    maxLocalCount = Math.max(maxLocalCount, solution.count);
                }
            }
            solutions.add(new Container(nums[i], maxLocalCount + 1));
            max = Math.max(maxLocalCount + 1, max);
        }
        return max;
    }

    private static class Container {
        private int value;
        private int count;

        public Container(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
