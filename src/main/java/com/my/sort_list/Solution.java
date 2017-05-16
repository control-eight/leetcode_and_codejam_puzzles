package com.my.sort_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by alex.bykovsky on 4/3/17.
 */
public class Solution {

	public ListNode sortList(ListNode head) {

		if(head == null || head.next == null) return head;

		int size = 1;
		ListNode current = head;
		while((current = current.next) != null) {
			size++;
		}

		ListNode result = mergeSort(head, 0, size);
		return result;
	}

	private ListNode mergeSort(ListNode startNode, int start, int end) {

		if(end - start == 1) {
			startNode.next = null;
			return startNode;
		}

		ListNode node = findNode(startNode, (end - start) / 2);

		ListNode left = mergeSort(startNode, start, start + (end - start) / 2);
		ListNode right = mergeSort(node, start + (end - start) / 2, end);

		ListNode result = merge(left, right);

		return result;
	}

	private ListNode merge(ListNode left, ListNode right) {

		ListNode currentLeft = left;
		ListNode currentRight = right;

		ListNode headResult;
		ListNode currentResult;

		if(currentLeft.val < currentRight.val) {
			headResult = currentLeft;
			currentLeft = currentLeft.next;
		} else {
			headResult = currentRight;
			currentRight = currentRight.next;
		}
		currentResult = headResult;

		while(currentLeft != null && currentRight != null) {
			ListNode currentResultNext;
			if(currentLeft.val < currentRight.val) {
				currentResultNext = currentLeft;
				currentLeft = currentLeft.next;
			} else {
				currentResultNext = currentRight;
				currentRight = currentRight.next;
			}
			currentResult.next = currentResultNext;
			currentResult = currentResult.next;
		}

		if(currentLeft != null) {

			currentResult.next = currentLeft;
			currentResult = currentResult.next;
			while((currentLeft = currentLeft.next) != null) {
				currentResult.next = currentLeft;
				currentResult = currentResult.next;
			}

		}

		if(currentRight != null) {

			currentResult.next = currentRight;
			currentResult = currentResult.next;
			while((currentRight = currentRight.next) != null) {
				currentResult.next = currentRight;
				currentResult = currentResult.next;
			}
		}

		return headResult;
	}

	private ListNode findNode(ListNode start, int index) {

		int i = 0;
		ListNode current = start;
		if(i == index) {
			return current;
		}
		while((current = current.next) != null) {
			i++;
			if(i == index) {
				return current;
			}
		}
		throw new RuntimeException();
	}

	public static void main(String[] args) {

		//test1();

		ListNode listNode1 = new ListNode(2);
		listNode1.next = new ListNode(1);
		listNode1.next.next = new ListNode(5);
		listNode1.next.next.next = new ListNode(3);

		ListNode l2 = new Solution().sortList(listNode1);
		System.out.println(l2.val + " " + l2.next.val + " " + l2.next.next.val + " " + l2.next.next.next.val);
	}

	private static void test1() {
		ListNode listNode1 = new ListNode(2);
		listNode1.next = new ListNode(1);

		ListNode l2 = new Solution().sortList(listNode1);
		System.out.println(l2.val + " " + l2.next.val);
	}
}
