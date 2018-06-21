package com.my.leetcode.merge_sorted_lists;

import com.my.leetcode.sort_list.ListNode;

/**
 * Created by alex.bykovsky on 3/31/18.
 */
public class Solution {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1 == null && l2 == null) return null;

		ListNode result;
		if (l1 != null && l2 == null) {
			result = new ListNode(l1.val);
			l1 = l1.next;
		} else if (l1 == null) {
			result = new ListNode(l2.val);
			l2 = l2.next;
		} else if (l1.val < l2.val) {
			result = new ListNode(l1.val);
			l1 = l1.next;
		} else {
			result = new ListNode(l2.val);
			l2 = l2.next;
		}
		ListNode next = result;

		while (!(l1 == null && l2 == null)) {
			if (l1 != null && l2 == null) {
				next.next = new ListNode(l1.val);
				l1 = l1.next;
			} else if (l1 == null) {
				next.next = new ListNode(l2.val);
				l2 = l2.next;
			} else if (l1.val < l2.val) {
				next.next = new ListNode(l1.val);
				l1 = l1.next;
			} else {
				next.next = new ListNode(l2.val);
				l2 = l2.next;
			}
			next = next.next;
		}

		return result;
	}
}
