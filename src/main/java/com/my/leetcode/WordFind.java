package com.my.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by alex.bykovsky on 3/21/17.
 */
public class WordFind {

	public String[] findWords(String[] grid, String[] wordList) {

		//init trie
		Trie trie = new Trie();
		for (String word : wordList) {
			trie.insert(word);
		}

		Map<String, String> resultMap = new HashMap<>();

		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length(); j++) {

				//horizontal - right direction
				List<String> foundWords = trie.findAll(grid[i].substring(j));

				for (String foundWord : foundWords) {
					resultMap.putIfAbsent(foundWord, i + " " + j);
				}
			}
		}

		String[] result = new String[wordList.length];
		for (int i = 0; i < wordList.length; i++) {
			String word = wordList[i];
			if(resultMap.containsKey(word)) {
				result[i] = resultMap.get(word);
			} else {
				result[i] = "";
			}
		}
		return result;
	}

	public String[] findWordsBruteForce(String[] grid, String[] wordList) {

		Map<String, String> resultMap = new HashMap<>();

		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length(); j++) {

				//horizontal - right direction

				StringBuilder wordBuilder = new StringBuilder();
				List<String> tempWordList = new ArrayList<>(Arrays.asList(wordList));
				for(int k = j; k < grid[i].length(); k++) {

					wordBuilder.append(grid[i].charAt(k));
					String newWord = wordBuilder.toString();

					Iterator<String> iter = tempWordList.iterator();
					while (iter.hasNext()) {
						String word = iter.next();
						if(word.equals(newWord)) {
							resultMap.putIfAbsent(word, i + " " + j);
						} else if(!word.startsWith(newWord)) {
							iter.remove();
						}
					}
				}
			}
		}

		String[] result = new String[wordList.length];
		for (int i = 0; i < wordList.length; i++) {
			String word = wordList[i];
			if(resultMap.containsKey(word)) {
				result[i] = resultMap.get(word);
			} else {
				result[i] = "";
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Trie.main(args);

		test1();
		test2();
		test3();
	}

	private static void test1() {
		String[] grid = new String[]{"TEST", "GOAT", "BOAT"};
		String[] wordList = new String[]{"GOAT", "BOAT", "TEST"};

		String[] result = new WordFind().findWords(grid, wordList);
		System.out.println(Arrays.toString(result));

		String[] result2 = new WordFind().findWordsBruteForce(grid, wordList);
		System.out.println("findWordsBruteForce: " + Arrays.toString(result2));

		System.out.println("Result: " + Arrays.equals(result, result2));
	}

	private static void test2() {
		String[] grid = new String[] {
				"FASDFSAFASDFSDAF",
				"QWEQWEAFDSFQEFAF",
				"HKGHLKBKFLAKFFEE",
				"FADSFMBKLEQWEKLL",
				"FASDFQWEWIOIOIEK",
				"FADSFKDSALBWREII",
				"HGJKLSMGAFMRWERW",
				"AKVBAPORWETWRKGD",
				"FSADKFBLKREWRWEE",
				"BVASVAFSKFLFEWRQ"
		};

		String[] wordList = new String[] {
				"LEQ",
				"BAPO",
				"FFEE",
				"FASDF",
				"FASDFS",
				"NONE"
		};

		//3 8, 7 3, 2 12, 0 0, 0 0, empty

		String[] result = new WordFind().findWords(grid, wordList);
		System.out.println(Arrays.toString(result));

		String[] result2 = new WordFind().findWordsBruteForce(grid, wordList);
		System.out.println("findWordsBruteForce: " + Arrays.toString(result2));

		System.out.println("Result: " + Arrays.equals(result, result2));
	}

	private static void test3() {

		int length = 500;
		Random random = new Random(length);

		StringBuilder[] gridBuilder = new StringBuilder[length];
		for(int i = 0; i < gridBuilder.length; i++) {
			gridBuilder[i] = new StringBuilder();
			for (int j = 0; j < length; j++) {
				char c = (char)(65 + (int)(random.nextDouble() * 26));
				gridBuilder[i].append(c);
			}
		}

		String[] grid = new String[length];
		for(int i = 0; i < grid.length; i++) {
			grid[i] = gridBuilder[i].toString();
		}

		System.out.println(Arrays.toString(grid));

		String[] wordList = new String[length];
		for(int i = 0; i < wordList.length; i++) {

			int row = (int) (random.nextDouble() * length);
			int column = (int) (random.nextDouble() * length);
			int wordLength = (int) (random.nextDouble() * length) + 1;

			String part = grid[row].substring(column);

			wordList[i] = part.substring(0, Math.min(part.length(), wordLength));
		}

		System.out.println(Arrays.toString(wordList));

		long start = System.currentTimeMillis();
		String[] result = new WordFind().findWords(grid, wordList);
		System.out.println(Arrays.toString(result));
		System.out.println((System.currentTimeMillis() - start) + "ms");

		long start2 = System.currentTimeMillis();
		String[] result2 = new WordFind().findWordsBruteForce(grid, wordList);
		System.out.println("findWordsBruteForce: " + Arrays.toString(result2));
		System.out.println((System.currentTimeMillis() - start2) + "ms");

		System.out.println("Result: " + Arrays.equals(result, result2));
	}

	private static class Trie {

		private Node root;

		/** Initialize your data structure here. */
		public Trie() {
			root = null;
		}

		/** Inserts a word into the trie. */
		public void insert(String word) {
			if(word == null) throw new NullPointerException();
			if(word.isEmpty()) return;
			if(root == null) {
				root = new Node();
			}
			insert(root, word, 0);
		}

		private void insert(Node parent, String word, int index) {

			if(index == word.length()) {
				parent.word = word;
				return;
			}

			Character c = word.charAt(index);

			if(parent.childs.containsKey(c)) {
				insert(parent.childs.get(c), word, ++index);
			} else {
				Node child = new Node();
				parent.childs.put(c, child);
				insert(child, word, ++index);
			}
		}

		/** Returns if the word is in the trie. */
		public boolean search(String word) {
			if(word == null) throw new NullPointerException();
			if(word.isEmpty()) return false;

			return search(root, word, -1);
		}

		private boolean search(Node parent, String word, int index) {

			if(parent != null && parent.word != null && word.equals(parent.word)) return true;

			index++;

			if(parent == null || word.length() == index) {
				return false;
			}

			return search(parent.childs.get(word.charAt(index)), word, index);
		}

		/** Returns if there is any word in the trie that starts with the given prefix. */
		public boolean startsWith(String prefix) {
			if(prefix == null) throw new NullPointerException();
			if(prefix.isEmpty()) return true;

			return startsWith(root, prefix, -1);
		}

		private boolean startsWith(Node parent, String prefix, int index) {

			if(parent != null && parent.word != null && parent.word.startsWith(prefix)) return true;

			index++;

			if(parent == null) {
				return false;
			}

			if(index < prefix.length()) {
				return startsWith(parent.childs.get(prefix.charAt(index)), prefix, index);
			} else {
				return !parent.childs.isEmpty();
			}
		}

		public List<String> findAll(String line) {
			if(line == null) throw new NullPointerException();
			if(line.isEmpty()) return Collections.emptyList();

			List<String> result = new ArrayList<>();
			findAll(root, line, 0, result);
			return result;
		}

		private void findAll(Node parent, String line, int index, List<String> acc) {

			String prefix = line.substring(0, index);

			if(parent != null && parent.word != null && parent.word.equals(prefix)) {
				acc.add(parent.word);
			}

			if(parent == null) {
				return;
			}

			index++;

			if(index - 1 < line.length()) {
				findAll(parent.childs.get(line.charAt(index-1)), line, index, acc);
			}
		}

		private static class Node {

			private Map<Character, Node> childs = new HashMap<>();

			private String word;
		}

		public static void main(String[] args) {
			Trie trie = new Trie();

			trie.insert("a");
			trie.insert("at");
			trie.insert("ate");
			trie.insert("ate");
			trie.insert("ear");
			trie.insert("east");
			trie.insert("easting");
			trie.insert("eat");

			System.out.println(trie.findAll("easting"));
		}
	}
}
