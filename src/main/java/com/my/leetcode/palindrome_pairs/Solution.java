package com.my.leetcode.palindrome_pairs;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().palindromePairs(new String[] {"ba","abc"}));
        //[1, 0]
        System.out.println(new Solution().palindromePairs(new String[] {"ababa", "ba"}));
        //[0, 1]
        System.out.println(new Solution().palindromePairs(new String[] {"ab","ba","abc","cba"}));
        //[[0,1],[1,0],[2,1],[2,3],[0,3],[3,2]]
        System.out.println(new Solution().palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));//, "cba"
        //[[0,1],[1,0],[3,2],[2,4]]
        System.out.println(new Solution().palindromePairs(new String[] {"a","b","c","ab","ac","aa"}));
        //[[3,0],[1,3],[4,0],[2,4],[5,0],[0,5]]
        System.out.println(new Solution().palindromePairs(new String[] {"a",""}));
        //[[0,1],[1,0]]
        System.out.println(new Solution().palindromePairs(new String[] {"bat","tab","cat"}));
        //[[0,1],[1,0]]
    }

    public List<List<Integer>> palindromePairs(String[] words) {

        Collection<List<Integer>> result = new HashSet<>();
        String[] reversedWords = new String[words.length];
        Map<String, Integer> palindromes = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            reversedWords[i] = reverse(word);
            if (isPalindrome(word, "")) {
                palindromes.put(word, i);
            }
        }
        Trie trieReverse = new Trie(reversedWords);
        Trie trie = new Trie(words);

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                List<Trie> results = trieReverse.find(reverse(word));

                for (Trie result1 : results) {
                    if (i != result1.index) {
                        if (isPalindrome(result1.reversedWord, word)) {
                            result.add(Arrays.asList(result1.index, i));
                        }
                    }
                }

                results = trieReverse.find(word);
                for (Trie result1 : results) {
                    if (i != result1.index) {
                        if (isPalindrome(word, result1.reversedWord)) {
                            result.add(Arrays.asList(i, result1.index));
                        }
                    }
                }

                results = trie.find(word);
                for (Trie result1 : results) {
                    if (i != result1.index) {
                        if (isPalindrome(word, result1.word)) {
                            result.add(Arrays.asList(i, result1.index));
                        }
                    }
                }

                results = trie.find(reverse(word));
                for (Trie result1 : results) {
                    if (i != result1.index) {
                        if (isPalindrome(result1.word, word)) {
                            result.add(Arrays.asList(result1.index, i));
                        }
                    }
                }

            } else {
                for (Map.Entry<String, Integer> entry : palindromes.entrySet()) {
                    if (i != entry.getValue()) {
                        result.add(Arrays.asList(entry.getValue(), i));
                        result.add(Arrays.asList(i, entry.getValue()));
                    }
                }
            }
        }
        return new ArrayList<>(result);
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

    private static boolean isPalindrome(String str1, String str2) {
        char[] charArr = str2.isEmpty()? str1.toCharArray(): (str1 + str2).toCharArray();
        int i = 0;
        int j = charArr.length - 1;
        while (i < j) {
            if (charArr[i] != charArr[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private static class Trie {

        private Trie[] map = new Trie[26];
        private boolean isWord;
        private String word;
        private String reversedWord;
        private int index;

        public Trie(boolean isWord, String word, int index) {
            this.isWord = isWord;
            this.word = word;
            this.reversedWord = reverse(word);
            this.index = index;
        }

        public Trie(String[] words) {
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                if (word.trim().isEmpty()) continue;

                Trie[] aMap = map;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    boolean isWord = i == chars.length - 1;
                    if (aMap[c - 'a'] != null) {
                        if (isWord) {
                            aMap[c - 'a'].isWord = true;
                            aMap[c - 'a'].word = word;
                            aMap[c - 'a'].reversedWord = reverse(word);
                            aMap[c - 'a'].index = j;
                        }
                    } else {
                        aMap[c - 'a'] = new Trie(isWord, isWord? word: null, j);
                    }
                    aMap = aMap[c - 'a'].map;
                }
            }
        }

        public List<Trie> find(String prefix) {
            List<Trie> result = new ArrayList<>();
            find(prefix.toCharArray(), 0, this, result);
            return result;
        }

        private void find(char[] prefix, int index, Trie curr, List<Trie> acc) {
            Trie trie;
            if (prefix.length == index) {
                trie = curr;
            } else {
                trie = curr.map[prefix[index] - 'a'];
            }
            if (trie != null) {
                if (index >= prefix.length - 1) {
                    Deque<Trie> tries = new LinkedList<>();
                    tries.add(trie);
                    do {
                        Trie next = tries.pollFirst();
                        if (next.isWord) {
                            acc.add(next);
                        }
                        for (Trie trie1 : next.map) {
                            if (trie1 != null) {
                                tries.offerLast(trie1);
                            }
                        }
                    } while (!tries.isEmpty());
                } else {
                    find(prefix, index + 1, trie, acc);
                }
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(map);
        }
    }
}
