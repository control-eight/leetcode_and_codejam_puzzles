package com.my.leetcode.l_subs_at_most_two_dist_chars;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(new Solution().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int typeOne = -1;
        int typeTwo = -1;
        int sum = 0;
        int lastType = -1;
        int lastTypeSum = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (typeOne == -1) {
                typeOne = s.charAt(i);
            } else if (typeTwo == -1) {
                if (s.charAt(i) != typeOne) {
                    typeTwo = s.charAt(i);
                }
            } else if (s.charAt(i) != typeOne && s.charAt(i) != typeTwo) {
                if (typeTwo == lastType) {
                    typeOne = typeTwo;
                }
                typeTwo = s.charAt(i);
                max = Math.max(max, sum);
                sum = lastTypeSum;
            }

            if (lastType == -1) {
                lastType = s.charAt(i);
                lastTypeSum = 1;
            } else {
                if (lastType == s.charAt(i)) {
                    lastTypeSum++;
                } else {
                    lastType = s.charAt(i);
                    lastTypeSum = 1;
                }
            }
            sum++;
        }
        max = Math.max(max, sum);
        return max;
    }
}
