package com.my.leetcode.isomorphic_str;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isIsomorphic("ab", "aa"));
        System.out.println(new Solution().isIsomorphic("egg", "add"));
        System.out.println(new Solution().isIsomorphic("foo", "bar"));
        System.out.println(new Solution().isIsomorphic("paper", "title"));
    }

    public boolean isIsomorphic(String s, String t) {
        char[] chars1 = new char[257];
        char[] chars2 = new char[257];
        for (int i = 0; i < s.length(); i++) {
            if (chars1[s.charAt(i) + 1] == 0 && chars2[t.charAt(i) + 1] == 0) {
                chars1[s.charAt(i) + 1] = t.charAt(i);
                chars2[t.charAt(i) + 1] = s.charAt(i);
            } else if (chars1[s.charAt(i) + 1] != t.charAt(i) || chars2[t.charAt(i) + 1] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
