package com.my.leetcode.word_pattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) return false;

        Map<String, Character> map = new HashMap<>();
        Set<Character> used = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                if (used.contains(pattern.charAt(i))) {
                    return false;
                } else {
                    map.put(words[i], pattern.charAt(i));
                    used.add(pattern.charAt(i));
                }
            } else if (map.get(words[i]) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
