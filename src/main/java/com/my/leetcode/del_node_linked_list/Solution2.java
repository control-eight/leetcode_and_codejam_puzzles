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
class Solution2 {

	public static void main(String[] args) {
		ListNode root = new ListNode(1);
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(4);

		root.next = node1;
		node1.next = node2;
		node2.next = node3;

		new Solution2().deleteNode(node2);

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
		node.val = node.next.val;
		node.next = node.next.next;
	}
}