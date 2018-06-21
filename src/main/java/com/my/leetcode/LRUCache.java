package com.my.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 3/22/17.
 */
public class LRUCache {

	private Map<Integer, Value> valueMap;

	private Node root;

	private Node tail;

	private int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.valueMap = new HashMap<>(capacity);
	}

	public int get(int key) {
		if(!valueMap.containsKey(key)) return -1;

		Value value = valueMap.get(key);

		moveNodeToTheEnd(value.node);

		return value.value;
	}

	public void put(int key, int value) {

		if(capacity == 0) {
			return;
		}

		if(valueMap.containsKey(key)) {
			Value valueObj = valueMap.get(key);
			valueObj.value = value;
			moveNodeToTheEnd(valueObj.node);

			return;
		}

		Value valueObj = new Value();
		valueObj.value = value;

		if(valueMap.size() >= capacity) {
			evict();
		}

		valueMap.put(key, valueObj);

		Node node = new Node();
		node.key = key;

		if(root == null) {
			root = node;
			tail = root;
		} else {
			tail.right = node;
			node.left = tail;
			tail = node;
		}

		valueObj.node = node;
	}

	private void evict() {
		valueMap.remove(root.key);

		if(root.right != null) {
			root = root.right;
			root.left = null;
		}
	}

	private void moveNodeToTheEnd(Node node) {
		if(node == root) {
			tail.right = node;
			node.left = tail;
			tail = node;

			if(node.right != null) {
				root = node.right;
				root.left = null;
			}
		} else if(node != tail) {
			node.left.right = node.right;
			node.right.left = node.left;

			tail.right = node;
			node.left = tail;
			tail = node;
		}
	}

	public static void main(String[] args) {
		//test1();
		//test2();

		LRUCache cache = new LRUCache(10);

		String input = "[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]";

		for (String part : input.split("\\]")) {

			if(part.startsWith(",")) {
				part = part.substring(1);
			}
			if(part.startsWith("[")) {
				part = part.substring(1);
			}

			String[] arr = part.split(",");

			if(arr.length == 1) {
				cache.get(Integer.parseInt(arr[0]));
			} else {
				cache.put(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			}

		};
	}

	private static void test2() {
		LRUCache cache = new LRUCache(3);

		cache.put(1,1);
		cache.put(2,2);
		cache.put(3,3);
		cache.put(4,4);

		System.out.println(cache.get(4));
		System.out.println(cache.get(3));
		System.out.println(cache.get(2));
		System.out.println(cache.get(1));

		cache.put(5,5);

		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
		System.out.println(cache.get(5));
	}

	private static void test1() {
		LRUCache cache = new LRUCache( 2 /* capacity */ );

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		;       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));
		;       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));
		;       // returns -1 (not found)
		System.out.println(cache.get(3));
		;       // returns 3
		System.out.println(cache.get(4));
		;       // returns 4
	}

	private static class Value {

		private int value;

		private Node node;

	}

	private static class Node {

		private Node left;

		private Node right;

		private int key;
	}
}
