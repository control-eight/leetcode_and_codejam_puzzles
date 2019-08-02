package com.my.misc;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SuffixTrie {
    
    public static final char CHAR = 'A';

    private SuffixTrie[] map = new SuffixTrie[26];
    private boolean isWord;
    private String word;
    private int index;

    public SuffixTrie(boolean isWord, String word, int index) {
        this.isWord = isWord;
        this.word = word;
        this.index = index;
    }

    public SuffixTrie(String[] words) {
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            if (word.trim().isEmpty()) continue;

            SuffixTrie[] aMap = map;
            char[] chars = word.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                char c = chars[i];
                boolean isWord = i == 0;
                if (aMap[c - CHAR] != null) {
                    if (isWord) {
                        aMap[c - CHAR].isWord = true;
                        aMap[c - CHAR].word = word;
                        aMap[c - CHAR].index = j;
                    }
                } else {
                    aMap[c - CHAR] = new SuffixTrie(isWord, isWord? word: null, j);
                }
                aMap = aMap[c - CHAR].map;
            }
        }
    }

    public List<SuffixTrie> find(String suffix) {
        List<SuffixTrie> result = new ArrayList<>();
        find((reverse(suffix)).toCharArray(), 0, this, result);
        return result;
    }

    private static String reverse(String substring) {
        if (substring == null) return null;

        StringBuilder result = new StringBuilder();
        char[] carr = substring.toCharArray();
        for (int i = carr.length - 1; i >= 0; i--) {
            result.append(carr[i]);
        }
        return result.toString();
    }

    private void find(char[] suffix, int index, SuffixTrie curr, List<SuffixTrie> acc) {
        SuffixTrie trie;
        if (suffix.length == index) {
            trie = curr;
        } else {
            trie = curr.map[suffix[index] - CHAR];
        }
        if (trie != null) {
            if (index >= suffix.length - 1) {
                Deque<SuffixTrie> tries = new LinkedList<>();
                tries.add(trie);
                do {
                    SuffixTrie next = tries.pollFirst();
                    if (next.isWord) {
                        acc.add(next);
                    }
                    for (SuffixTrie trie1 : next.map) {
                        if (trie1 != null) {
                            tries.offerLast(trie1);
                        }
                    }
                } while (!tries.isEmpty());
            } else {
                find(suffix, index + 1, trie, acc);
            }
        }
    }

    @Override
    public String toString() {
        return word;
    }
}
