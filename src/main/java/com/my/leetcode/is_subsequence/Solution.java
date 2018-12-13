package com.my.leetcode.is_subsequence;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isSubsequence("ab", "adb"));
        System.out.println(new Solution().isSubsequence("abc", "ahbgdc"));
        System.out.println(new Solution().isSubsequence("bb", "bcb"));
        System.out.println(new Solution().isSubsequence("axc", "ahbgdc"));
    }

    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        int[] lettersCount = new int[26];
        for (char c : tArr) {
            lettersCount[c - 'a']++;
        }

        int count = 0;
        int posTrueBefore = -1;
        for (int sPos = 0; sPos < sArr.length; sPos++) {
            boolean found = false;
            if (lettersCount[sArr[sPos] - 'a'] > 0) {
                count++;
                found = true;
            }
            for (int tPos = posTrueBefore + 1; tPos < tArr.length; tPos++) {
                lettersCount[tArr[tPos] - 'a']--;
                if (sArr[sPos] == tArr[tPos]) {
                    posTrueBefore = tPos;
                    break;
                }
            }
            if (!found) break;
        }
        return count == s.length();
    }
}
