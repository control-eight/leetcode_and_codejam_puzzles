package com.my.leetcode.word_patternII;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().wordPatternMatch("abab", "redblueredblue"));
        System.out.println(new Solution().wordPatternMatch("itwasthebestoftimes", "ittwaastthhebesttoofttimesss"));
        System.out.println(new Solution().wordPatternMatch("", "s"));
        System.out.println(new Solution().wordPatternMatch("aaaa", "asdasdasdasd"));
        System.out.println(new Solution().wordPatternMatch("aabb", "xyzabcxzyabc"));
    }

    public boolean wordPatternMatch(String pattern, String str) {
        return wordPatternMatch(pattern, str, 0, new HashMap<>(), new HashSet<>(), 0, 0, str.length());
    }

    private boolean wordPatternMatch(String pattern,
                                     String str,
                                     int start,
                                     Map<String, Character> map,
                                     Set<Character> used,
                                     int index,
                                     int codedLength,
                                     int slength) {

        if (index == pattern.length()) {
            return codedLength == slength;
        }
        if (start >= str.length()) {
            return false;
        }

        for (int length = 1; length <= (str.length() - start) - (pattern.length() - index - 1); length++) {
            String word = str.substring(start, start + length);

            if (!map.containsKey(word)) {
                if (used.contains(pattern.charAt(index))) {
                    continue;
                }
            } else if (map.get(word) != pattern.charAt(index)) {
                continue;
            }

            boolean toRemove1 = !map.containsKey(word);
            map.put(word, pattern.charAt(index));
            boolean toRemove2 = !used.contains(pattern.charAt(index));
            used.add(pattern.charAt(index));
            boolean result = wordPatternMatch(pattern, str, start + length, map, used, index + 1,
                    codedLength + word.length(), slength);
            if (result) {
                return true;
            }
            if (toRemove1) {
                map.remove(word);
            }
            if (toRemove2) {
                used.remove(pattern.charAt(index));
            }
        }
        return false;
    }
}
