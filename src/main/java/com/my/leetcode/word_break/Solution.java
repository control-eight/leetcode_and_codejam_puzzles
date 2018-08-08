package com.my.leetcode.word_break;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(new Solution().wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(new Solution().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordDict);
        boolean[] solutions = new boolean[s.length() + 1];
        solutions[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (wordSet.contains(s.substring(i, j)) && solutions[i]) {
                    solutions[j] = true;
                }
            }
        }
        return solutions[solutions.length - 1];
    }
}
