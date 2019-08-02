package com.my.leetcode.guess_the_word;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {

        Map<Character, Set<Integer>> trueCharPos = new HashMap<>();
        Map<Character, Set<Integer>> falseCharPos = new HashMap<>();

        String word = wordlist[0];
        while (true) {
            int res = master.guess(word);
            if (res == -1) {
                throw new RuntimeException("-1");
            } else if (res == 6) {
                return;
            } else if (res == 0) {
                putPos(falseCharPos, word);
            } else {
                putPos(trueCharPos, word);
            }


        }
    }

    private void putPos(Map<Character, Set<Integer>> charPos, String word) {
        for (int j = 0; j < word.length(); j++) {
            char c = word.charAt(j);
            charPos.putIfAbsent(c, new HashSet<>());
            charPos.get(c).add(j);
        }
    }
}
