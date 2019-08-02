package com.my.leetcode.threesum_closest;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int threeSumClosest(int[] nums, int target) {

        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }


        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (numsSet.contains(target - (nums[i] + nums[j]))) {
                    return 1;
                }
            }
        }
        return 1;
    }
}
