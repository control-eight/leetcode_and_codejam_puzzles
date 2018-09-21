package com.my.leetcode.word_search_II;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findWords(new char[][]{
                {'a', 'b'},
                {'c', 'd'},
        }, new String[]{"cdba"}));
        System.out.println(new Solution().findWords(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"oath", "pea", "eat", "rain"}));
        System.out.println(new Solution().findWords(new char[][]{
                {'a', 'b'},
                {'a', 'a'}
        }, new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"}));
    }

    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0) return Collections.emptyList();
        Trie trie = new Trie(words);
        Set<String> result = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                visited[i][j] = true;
                backtracking(board, i, j, trie, result, visited);
            }
        }
        return new ArrayList<>(result);
    }

    private void backtracking(char[][] board, int i, int j, Trie trie, Set<String> result, boolean[][] visited) {
        Trie nextTrie = trie.map[board[i][j] - 'a'];
        if (nextTrie == null) {
            return;
        } else if (nextTrie.isWord) {
            result.add(nextTrie.word);
            nextTrie.isWord = false;
            nextTrie.word = null;
        }

        if (i > 0 && !visited[i - 1][j]) {
            visited[i - 1][j] = true;
            backtracking(board, i - 1, j, nextTrie, result, visited);
            visited[i - 1][j] = false;
        }
        if (j > 0 && !visited[i][j - 1]) {
            visited[i][j - 1] = true;
            backtracking(board, i, j - 1, nextTrie, result, visited);
            visited[i][j - 1] = false;
        }
        if (i < board.length - 1 && !visited[i + 1][j]) {
            visited[i + 1][j] = true;
            backtracking(board, i + 1, j, nextTrie, result, visited);
            visited[i + 1][j] = false;
        }
        if (j < board[0].length - 1 && !visited[i][j + 1]) {
            visited[i][j + 1] = true;
            backtracking(board, i, j + 1, nextTrie, result, visited);
            visited[i][j + 1] = false;
        }
    }

    private static class Trie {

        private Trie[] map = new Trie[26];
        private boolean isWord;
        private String word;

        public Trie(boolean isWord, String word) {
            this.isWord = isWord;
            this.word = word;
        }

        public Trie(String[] words) {
            for (String word : words) {
                Trie[] aMap = map;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    boolean isWord = i == chars.length - 1;
                    if (aMap[c - 'a'] != null) {
                        if (isWord) {
                            aMap[c - 'a'].isWord = true;
                            aMap[c - 'a'].word = word;
                        }
                    } else {
                        aMap[c - 'a'] = new Trie(isWord, word);
                    }
                    aMap = aMap[c - 'a'].map;
                }
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(map);
        }
    }
}
