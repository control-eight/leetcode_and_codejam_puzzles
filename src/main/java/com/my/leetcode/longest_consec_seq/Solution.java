package com.my.leetcode.longest_consec_seq;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxCount = 0;
        for (int num : nums) {
            int count = 0;
            while(set.contains(num)) {
                num++;
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}
