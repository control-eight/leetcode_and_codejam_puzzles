package com.my.leetcode.repeated_string_match;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().repeatedStringMatch("abababaaba", "aabaaba"));
        System.out.println(new Solution().repeatedStringMatch("abcabcabcabc", "abac"));
        System.out.println(new Solution().repeatedStringMatch("aabaa", "aaab"));
        System.out.println(new Solution().repeatedStringMatch("abab", "aba"));
        System.out.println(new Solution().repeatedStringMatch("a", "a"));
        System.out.println(new Solution().repeatedStringMatch("aca", "caa"));
        System.out.println(new Solution().repeatedStringMatch("aca", "aa"));
        System.out.println(new Solution().repeatedStringMatch("abcd", "cdabcdab"));
    }

    public int repeatedStringMatch(String A, String B) {
        int count = 1;
        StringBuilder sb = new StringBuilder(A);
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.indexOf(B) >= 0) return count;
        sb.append(A);
        count++;
        return sb.indexOf(B) >= 0? count: -1;
    }
}
