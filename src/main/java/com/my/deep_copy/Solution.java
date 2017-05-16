package com.my.deep_copy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by alex.bykovsky on 3/24/17.
 */
public class Solution {


	public RandomListNode copyRandomList(RandomListNode head) {
		return copyRandomList(head, new HashMap<>());
	}

	private RandomListNode copyRandomList(RandomListNode head, Map<RandomListNode, RandomListNode> visited) {

		if(head == null) {
			return null;
		}

		RandomListNode copyNode = new RandomListNode(head.label);

		visited.put(head, copyNode);

		copyNode.next =  processNode(head.next, visited);
		copyNode.random =  processNode(head.random, visited);

		return copyNode;
	}

	private RandomListNode processNode(RandomListNode head, Map<RandomListNode, RandomListNode> visited) {
		if(visited.containsKey(head)) {
			return visited.get(head);
		} else {
			return copyRandomList(head, visited);
		}
	}

	private static class RandomListNode {

		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	}

	public static void main(String[] args) {

		Map<Integer, RandomListNode> map = new HashMap<>();
		for(int i = 0; i < 6; i++) {
			map.put(i, new RandomListNode(i));
		}

		RandomListNode head = map.get(0);
		head.next = map.get(1);
		head.random = map.get(4);

		map.get(1).next = map.get(2);
		map.get(2).next = map.get(3);
		map.get(2).random = map.get(0);
		map.get(4).next = map.get(5);
		map.get(5).random = map.get(5);

		RandomListNode result = new Solution().copyRandomList(head);
		System.out.println();
	}
}
