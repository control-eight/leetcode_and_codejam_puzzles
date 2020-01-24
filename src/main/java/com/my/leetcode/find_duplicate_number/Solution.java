package com.my.leetcode.find_duplicate_number;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicate(new int [] {2,3,4,5,2,1}));
        System.out.println(new Solution().findDuplicate(new int [] {5,4,3,2,1,2}));
        System.out.println(new Solution().findDuplicate(new int [] {3,1,3,4,2}));
        System.out.println(new Solution().findDuplicate(new int [] {1,2,2,2,2,2,2,2,4,5,6,7,8,9,10}));
        System.out.println(new Solution().findDuplicate(new int [] {1,3,4,4,4}));
        System.out.println(new Solution().findDuplicate(new int [] {1,1,1,3,4}));
        System.out.println(new Solution().findDuplicate(new int [] {13,17,11,18,9,19,15,7,14,3,16,15,15,15,15,10,2,6,12,15}));
        System.out.println(new Solution().findDuplicate(new int [] {2,2,2,2,2}));
        System.out.println(new Solution().findDuplicate(new int [] {2,5,9,6,9,3,8,9,7,1}));
    }

    public int findDuplicate(int[] nums) {
        int lo = 1;
        int hi = nums.length - 1;
        while (lo < hi - 1) {
            int half = (hi - lo) / 2;
            int countLo = count(lo, lo + half, nums);
            if (countLo > half + 1) {
                hi = lo + half;
            } else {
                lo = lo + half + 1;
            }
        }
        return count(lo, lo, nums) > 1? lo: hi;
    }

    private int count(int lo, int hi, int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num >= lo && num <= hi) {
                count++;
            }
        }
        return count;
    }
}
