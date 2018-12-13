package com.my.leetcode.distinct_subsequences;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().numDistinct("rabbbit", "rabbit"));
        System.out.println(new Solution().numDistinct("babgbag", "bag"));
        System.out.println(new Solution().numDistinct("dbaaadcddccdddcadacbadbadbabbbcad", "dadcccbaab"));
    }

    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;

        return numDistinct("", s, t, new HashMap<>());
    }

    private int numDistinct(String acc, String s, String t, Map<String, Integer> cache) {
        if (acc.length() + s.length() < t.length()) return 0;
        if (acc.length() > 0 && t.charAt(acc.length() - 1) != acc.charAt(acc.length() - 1)) return 0;

        String key = acc + s.length();
        Integer result = cache.get(key);
        if (result != null) return result;

        if (acc.length() == t.length()) {
            return acc.equals(t)? 1: 0;
        }

        result = numDistinct(acc + s.substring(0, 1), s.substring(1), t, cache)
                + numDistinct(acc, s.substring(1), t, cache);
        cache.put(key, result);
        return result;
    }
}
