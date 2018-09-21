package com.my.leetcode.palindrome_permutation;

public class Solution {

    public boolean canPermutePalindrome(String s) {
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        int oddCount = 0;
        for (int i : count) {
            oddCount += i % 2 == 1? 1: 0;
            if (oddCount > 1) {
                return false;
            }
        }
        return true;
    }
}
