package com.my.code_jam.y2019.round1.round1a.alien_rhyme;

import java.util.*;

/**
 * Created by alex.bykovsky on 5/4/18.
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int W = in.nextInt();
            String[] words = new String[W];
            for (int i = 0; i < W; i++) {
                words[i] = in.next();
            }
            solve(t, words);
        }
    }

    private static void solve(int t, String[] words) {
        Trie trie = new Trie(words);
        int result = trie.traverseAndCalculate();
        System.out.printf("Case #%d: %d\n", t, result);
    }

    private static class Trie {

        public static final char CHAR = 'A';
        private Trie[] map = new Trie[26];
        private boolean isWord;

        public Trie(boolean isWord) {
            this.isWord = isWord;
        }

        public Trie(String[] words) {
            for (String word : words) {
                Trie[] aMap = map;
                char[] chars = word.toCharArray();
                for (int i = chars.length - 1; i >= 0; i--) {
                    char c = chars[i];
                    boolean isWord = i == 0;
                    if (aMap[c - CHAR] != null) {
                        aMap[c - CHAR].isWord |= isWord;
                    } else {
                        aMap[c - CHAR] = new Trie(isWord);
                    }
                    aMap = aMap[c - CHAR].map;
                }
            }
        }

        public int traverseAndCalculate() {
            Counter counter = new Counter();
            for (Trie aTrie : map) {
                if (aTrie != null) {
                    aTrie.traverseAndCalculate(counter);
                }
            }
            return counter.value;
        }

        private int traverseAndCalculate(Counter counter) {
            int result = 0;
            if (isWord) {
                result++;
            }
            for (Trie aTrie : map) {
                if (aTrie != null) {
                    result += aTrie.traverseAndCalculate(counter);
                }
            }
            if (result > 1) {
                counter.value += 2;
                return result - 2;
            } else {
                return result;
            }
        }
    }

    private static class Counter {
        private int value;
    }
}
