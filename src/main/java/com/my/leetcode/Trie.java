package com.my.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 3/21/17.
 */
public class Trie {

	private Node root;

	private int size;

	/** Initialize your data structure here. */
	public Trie() {
		root = null;
		size = 0;
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

	private static class Node {

		private Map<Character, Node> childs = new HashMap<>();

		private String word;
	}

	public static void main(String[] args) {

		Trie obj = new Trie();

		obj.insert("a");
		obj.insert("at");
		obj.insert("ate");
		obj.insert("ate");
		obj.insert("ear");
		obj.insert("east");
		obj.insert("eat");

		System.out.println(obj.search("at"));
		System.out.println(obj.search("ate"));
		System.out.println(obj.search("east"));
		System.out.println(obj.search("east1"));
		System.out.println(obj.search("a1"));
		System.out.println(obj.search("b"));
		System.out.println(obj.search("e"));
		System.out.println(obj.search("t"));


		System.out.println(obj.startsWith("at"));
		System.out.println(obj.startsWith("ate"));
		System.out.println(obj.startsWith("east"));
		System.out.println(obj.startsWith("east1"));
		System.out.println(obj.startsWith("a1"));
		System.out.println(obj.startsWith("b"));
		System.out.println(obj.startsWith("e"));
		System.out.println(obj.startsWith("eas"));
		System.out.println(obj.startsWith("eas1"));
	}
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
