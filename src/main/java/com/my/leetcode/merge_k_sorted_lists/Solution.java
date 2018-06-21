package com.my.leetcode.merge_k_sorted_lists;

import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

	public ListNode mergeKLists(ListNode[] lists) {
		if(lists.length == 0) return null;

		PriorityQueue<Pair> heap = new PriorityQueue<>(lists.length);
		for (ListNode list : lists) {
			heap.add(new Pair(list.val, list));
		}

		if(heap.isEmpty()) return null;

		ListNode first = heap.poll().node;
		ListNode last = first;
		if(last.next != null) heap.add(new Pair(last.next.val, last.next));

		while(!heap.isEmpty()) {
			ListNode ln = heap.poll().node;
			last.next = ln;
			last = ln;
			if(last.next != null) heap.add(new Pair(last.next.val, last.next));
		}
		return first;
	}

	private static class Pair implements Comparable<Pair> {
		int val;
		ListNode node;
		public Pair(int val, ListNode node) {
			this.val = val;
			this.node = node;
		}
		@Override
		public int compareTo(Pair o) {
			return val - o.val;
		}
	}

	private static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}
