package com.my.leetcode.del_node_linked_list;

/**
 * Created by alex.bykovsky on 3/26/18.
 */

import com.my.leetcode.sort_list.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

	public static void main(String[] args) {
		ListNode root = new ListNode(1);
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(4);

		root.next = node1;
		node1.next = node2;
		node2.next = node3;

		new Solution().deleteNode(node2);

		print(root);
	}

	private static void print(ListNode node) {
		while (node != null) {
			System.out.println(node.val);
			System.out.println(" -> ");
			node = node.next;
		}
	}

	public void deleteNode(ListNode node) {
		//tail node
		if (node == null || node.next == null) {
			return;
		}

		ListNode next = node.next;
		while (node.next.next != null) {
			node.val = next.val;
			node = next;
			next = next.next;
		}
		node.val = next.val;
		node.next = null;
	}
}