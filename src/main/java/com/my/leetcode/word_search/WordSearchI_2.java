package com.my.leetcode.word_search;

/**
 * Created by alex.bykovsky on 9/28/17.
 */
public class WordSearchI_2 {

	public static void main(String[] args) {
		System.out.println(new WordSearchI_2().exist(new char[][]{{'a','b'}}, "ba"));
		System.out.println(new WordSearchI_2().exist(new char[][]{{'a','a'}}, "aaa"));
		System.out.println(new WordSearchI_2().exist(new char[][]{{'a','b', 'c', 'e'},{'s', 'f', 'c', 's'},
				{'a', 'd', 'e', 'e'}}, "abcced"));
		System.out.println(new WordSearchI_2().exist(new char[][]{{'a','b', 'c', 'e'},{'s', 'f', 'c', 's'},
				{'a', 'd', 'e', 'e'}}, "see"));
		System.out.println(new WordSearchI_2().exist(new char[][]{{'a','b', 'c', 'e'},{'s', 'f', 'c', 's'},
				{'a', 'd', 'e', 'e'}}, "abcb"));
		System.out.println(new WordSearchI_2().exist(new char[][]{{'A','B','C','E'}, {'S','F','E','S'}, {'A','D','E','E'}},
				"ABCESEEEFS"));
	}

	public boolean exist(char[][] board, String word) {
		if(board.length == 0 || board[0].length == 0) return false;
		if(word.length() == 0) return false;

		boolean[][] visited = new boolean[board.length][board[0].length];

		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(word.charAt(0) == board[i][j]) {
					visited[i][j] = true;
					boolean result = search(board, word, 0, i, j, visited);
					if(result) return true;
					visited[i][j] = false;
				}
			}
		}

		return false;
	}

	private boolean search(char[][] board, String word, int nextToFind, int i, int j, boolean[][] visited) {

		if(board[i][j] == word.charAt(nextToFind)) {
			nextToFind++;
		} else if(nextToFind > 0) {
			return false;
		}

		if(nextToFind >= word.length()) return true;

		boolean found = false;
		if(i - 1 >= 0 && !visited[i - 1][j]) {
			visited[i - 1][j] = true;
			found = search(board, word, nextToFind, i - 1, j, visited);
			if(!found) visited[i - 1][j] = false;
		}

		if(i + 1 < board.length && !visited[i + 1][j]) {
			visited[i + 1][j] = true;
			found |= search(board, word, nextToFind, i + 1, j, visited);
			if(!found) visited[i + 1][j] = false;
		}

		if(j - 1 >= 0 && !visited[i][j - 1]) {
			visited[i][j - 1] = true;
			found |= search(board, word, nextToFind, i, j - 1, visited);
			if(!found) visited[i][j - 1] = false;
		}

		if(j + 1 < board[0].length && !visited[i][j + 1]) {
			visited[i][j + 1] = true;
			found |= search(board, word, nextToFind, i, j + 1, visited);
			if(!found) visited[i][j + 1] = false;
		}

		return found;
	}
}
