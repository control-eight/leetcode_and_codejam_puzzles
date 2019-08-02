package com.my.leetcode.prefix_suffix_search;

import java.util.LinkedList;
import java.util.Queue;

public class WordFilter {

    public static void main(String[] args) {
        WordFilter wordFilter;

        wordFilter = new WordFilter(new String[] {
                "apple"
        });
        System.out.println(wordFilter.f("a", "e"));
        //0
        System.out.println(wordFilter.f("b", ""));
        //-1

        wordFilter = new WordFilter(new String[] {
                "apple", "aaae", "bag", "bla", "aaaaaaaaaaaae", "aaaaafjsdfkdre"
        });
        System.out.println(wordFilter.f("a", "e"));
        //5
        System.out.println(wordFilter.f("b", ""));
        //3

        wordFilter = new WordFilter(new String[] {
                "pop"
        });
        System.out.println(wordFilter.f("pgp", "pgp"));
        //5
    }

    private Trie trie;

    public WordFilter(String[] words) {
        trie = new Trie(words);
    }

    public int f(String prefix, String suffix) {
        return trie.findMax(prefix, suffix);
    }

    private static class Trie {

        private static final char CHAR = 'a';
        private Trie[][] map = new Trie[26][26];
        private boolean isWord;
        private int weight;

        public Trie(boolean isWord, int weight) {
            this.isWord = isWord;
            this.weight = weight;
        }

        public Trie(String[] words) {
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                Trie[][] aMap = map;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int t1 = chars[i] - CHAR;
                    int t2 = chars[chars.length - i - 1] - CHAR;
                    boolean isWord = i == chars.length - 1;
                    if (aMap[t1][t2] != null) {
                        aMap[t1][t2].isWord |= isWord;
                        if (isWord) {
                            aMap[t1][t2].weight = j;
                        }
                    } else {
                        aMap[t1][t2] = new Trie(isWord, j);
                    }
                    aMap = aMap[t1][t2].map;
                }
            }
        }

        public int findMax(String prefix, String suffix) {
            int result = -1;

            Queue<Trie> traverse = new LinkedList<>();
            if (prefix.isEmpty()) {
                for (Trie[] tries : map) {
                    if (suffix.isEmpty()) {
                        for (Trie aTry : tries) {
                            if (aTry != null) {
                                traverse.offer(aTry);
                            }
                        }
                    } else {
                        if (tries[suffix.charAt(suffix.length() - 1) - CHAR] != null) {
                            traverse.offer(tries[suffix.charAt(suffix.length() - 1) - CHAR]);
                        }
                    }
                }
            } else {
                if (suffix.isEmpty()) {
                    for (Trie trie : map[prefix.charAt(0) - CHAR]) {
                        if (trie != null) {
                            traverse.offer(trie);
                        }
                    }
                } else {
                    if (map[prefix.charAt(0) - CHAR][suffix.charAt(suffix.length() - 1) - CHAR] != null) {
                        traverse.offer(map[prefix.charAt(0) - CHAR][suffix.charAt(suffix.length() - 1) - CHAR]);
                    }
                }
            }

            while (!traverse.isEmpty()) {
                Trie next = traverse.poll();
                if (next.isWord) {
                    result = Math.max(next.weight, result);
                }
                result = Math.max(next.findMax(prefix.isEmpty()? prefix: prefix.substring(1),
                        suffix.isEmpty()? suffix: suffix.substring(0, suffix.length() - 1)), result);
            }
            return result;
        }
    }
}
