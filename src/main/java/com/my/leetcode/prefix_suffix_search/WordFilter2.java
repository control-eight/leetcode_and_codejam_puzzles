package com.my.leetcode.prefix_suffix_search;

import java.util.LinkedList;
import java.util.Queue;

public class WordFilter2 {

    public static void main(String[] args) {
        WordFilter2 wordFilter;

        wordFilter = new WordFilter2(new String[] {
                "apple"
        });
        System.out.println(wordFilter.f("a", "e"));
        //0
        System.out.println(wordFilter.f("b", ""));
        //-1

        wordFilter = new WordFilter2(new String[] {
                "apple", "aaae", "bag", "bla", "aaaaaaaaaaaae", "aaaaafjsdfkdre"
        });
        System.out.println(wordFilter.f("a", "e"));
        //5
        System.out.println(wordFilter.f("b", ""));
        //3

        wordFilter = new WordFilter2(new String[] {
                "pop"
        });
        System.out.println(wordFilter.f("pgp", "pgp"));
        //-1
    }

    private Trie trie;

    public WordFilter2(String[] words) {
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
                this.weight = buildTries(words[j].toCharArray(), 0, j);
            }
        }

        private int buildTries(char[] chars, int i, int j) {
            Trie[][] aMap = map;
            if (i == chars.length) return 0;
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
            weight = Math.max(aMap[t1][t2].buildTries(chars, i + 1, j), weight);
            return weight;
        }

        public int findMax(String prefix, String suffix) {
            int result = -1;

            if (prefix.isEmpty() && suffix.isEmpty()) {
                return weight;
            }

            Queue<Trie> traverse = new LinkedList<>();
            if (prefix.isEmpty()) {
                for (Trie[] tries : map) {
                    if (tries[suffix.charAt(suffix.length() - 1) - CHAR] != null) {
                        traverse.offer(tries[suffix.charAt(suffix.length() - 1) - CHAR]);
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
