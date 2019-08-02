package com.my.leetcode.missing_ranges;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findMissingRanges(new int[] {0, 1, 3, 50, 75}, 0, 99));
        System.out.println(new Solution().findMissingRanges(new int[] {5, 50}, 0, 99));
        System.out.println(new Solution().findMissingRanges(new int[] {}, -3, -1));
        System.out.println(new Solution().findMissingRanges(new int[] {-1}, -1, -1));
        System.out.println(new Solution().findMissingRanges(new int[] {-1}, -1, -1));
        System.out.println(new Solution().findMissingRanges(new int[] {-2147483648,-2147483648,0,2147483647,2147483647},
                -2147483648, 2147483647));
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        Long prev = null;
        List<String> result = new ArrayList<>();
        for (long num : nums) {
            if (prev == null && num >= lower) {
                prev = num;
                if (num > lower) {
                    result.add(getInterval(lower, num - 1));
                }
            } else if (num > prev + 1) {
                result.add(getInterval(prev + 1, num - 1));
            }
            prev = num;
            if (num > upper) {
                break;
            }
        }
        if (prev == null) {
            result.add(getInterval(lower, upper));
        } else if (prev < upper) {
            result.add(getInterval(prev + 1, upper));
        }
        return result;
    }

    private String getInterval(long lo, long hi) {
        return hi == lo? (hi) + "": lo + "->" + hi;
    }
}
