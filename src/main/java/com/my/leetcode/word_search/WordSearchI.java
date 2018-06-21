package com.my.leetcode.word_search;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alex.bykovsky on 9/28/17.
 */
public class WordSearchI {

	public static void main(String[] args) {
		System.out.println(new WordSearchI().exist(new char[][]{{'a','a'}}, "aaa"));
		System.out.println(new WordSearchI().exist(new char[][]{{'a','b', 'c', 'e'},{'s', 'f', 'c', 's'},
				{'a', 'd', 'e', 'e'}}, "abcced"));
		System.out.println(new WordSearchI().exist(new char[][]{{'a','b', 'c', 'e'},{'s', 'f', 'c', 's'},
				{'a', 'd', 'e', 'e'}}, "see"));
		System.out.println(new WordSearchI().exist(new char[][]{{'a','b', 'c', 'e'},{'s', 'f', 'c', 's'},
				{'a', 'd', 'e', 'e'}}, "abcb"));
		System.out.println(new WordSearchI().exist(new char[][]{{'a','b'}}, "ba"));
	}

	public boolean exist(char[][] board, String word) {
		if(board.length == 0 || board[0].length == 0) return false;
		if(word.length() == 0) return false;

		return search(board, word, 0, 0, 0, new HashSet<>(), new HashSet<>());
	}

	private boolean search(char[][] board, String word, int nextToFind, int i, int j, Set<Key> included, Set<Key> visited) {

		if(nextToFind >= word.length()) return true;
		if(i < 0 || i >= board.length) return false;
		if(j < 0 || j >= board[0].length) return false;

		if(board[i][j] == word.charAt(nextToFind)) {
			nextToFind++;
			included = include(i, j, included);
		} else if(nextToFind > 0) {
			return false;
		}

		if(nextToFind == 1) {
			visited = new HashSet<>();
			visited.add(new Key(i, j));
		} else {
			visited.add(new Key(i, j));
		}

		boolean found = false;
		if(!included.contains(new Key(i - 1, j)) && !visited.contains(new Key(i - 1, j))) {
			found = search(board, word, nextToFind, i - 1, j, included, visited);
		}
		if(!included.contains(new Key(i + 1, j)) && !visited.contains(new Key(i + 1, j))) {
			found |= search(board, word, nextToFind, i + 1, j, included, visited);
		}
		if(!included.contains(new Key(i, j - 1)) && !visited.contains(new Key(i, j - 1))) {
			found |= search(board, word, nextToFind, i, j - 1, included, visited);
		}
		if(!included.contains(new Key(i, j + 1)) && !visited.contains(new Key(i, j + 1))) {
			found |= search(board, word, nextToFind, i, j + 1, included, visited);
		}

		return found;
	}

	private Set<Key> include(int i, int j, Set<Key> included) {
		Set<Key> newincluded = new HashSet<>(included);
		newincluded.add(new Key(i, j));
		return newincluded;
	}

	private static class Key {
		private int i;
		private int j;

		public Key(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Key key = (Key) o;

			if (i != key.i) return false;
			return j == key.j;
		}

		@Override
		public int hashCode() {
			int result = i;
			result = 31 * result + j;
			return result;
		}
	}
}
