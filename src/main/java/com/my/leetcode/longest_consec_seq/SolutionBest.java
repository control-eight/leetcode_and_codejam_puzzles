package com.my.leetcode.longest_consec_seq;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SolutionBest {

    public static void main(String[] args) {
        System.out.println(new SolutionBest().longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxCount = 0;
        int index = 0;
        while (!set.isEmpty()) {
            int num = nums[index];
            int startNum = num;
            int count = 0;
            while (set.remove(num)) {
                num++;
                count++;
            }
            num = startNum - 1;
            while (set.remove(num)) {
                num--;
                count++;
            }
            maxCount = Math.max(maxCount, count);
            index++;
        }
        return maxCount;
    }
}
