package com.my.maximal_rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex.bykovsky on 5/8/17.
 */
public class IntervalTree {

	private Node root;

	private int row;

	public IntervalTree(int row) {
		this.row = row;
	}

	public void insert(int start, int end) {

		if(root == null) {
			root = new Node(start, end, null);
		} else {
			insert(root, start, end);
		}
	}

	private void insert(Node parent, int start, int end) {

		if(end > parent.maxRight) {
			parent.maxRight = end;
		}

		if(start < parent.start) {

			if(parent.left == null) {
				parent.left = new Node(start, end, parent);
			} else {
				insert(parent.left, start, end);
			}

		} else {

			if(parent.right == null) {
				parent.right = new Node(start, end, parent);
			} else {
				insert(parent.right, start, end);
			}
		}
	}

	public List<Solution.Interval> findIntersections(Solution.Interval interval) {
		List<Solution.Interval> result = new ArrayList<>();
		findIntersections(root, interval, result);
		return result;
	}

	private void findIntersections(Node parent, Solution.Interval interval, List<Solution.Interval> acc) {

		if(parent == null) {
			return;
		}

		if(interval.getStart() <= parent.start && interval.getEnd() >= parent.start) {
			acc.add(toInterval(parent));
		} else if(interval.getStart() > parent.start && interval.getStart() <= parent.end) {
			acc.add(toInterval(parent));
		}

		if(interval.getStart() <= parent.start) {
			findIntersections(parent.left, interval, acc);
		}

		if(interval.getEnd() >= parent.start) {
			findIntersections(parent.right, interval, acc);
		}
	}

	private Solution.Interval toInterval(Node node) {
		return new Solution.Interval(node.start, node.end, row, node.length);
	}

	private static class Node {

		private final int start;

		private final int end;

		private int maxRight;

		private final int length;

		private Node parent;

		private Node left;

		private Node right;

		public Node(int start, int end, Node parent) {
			this.start = start;
			this.end = end == start? ++end: end;
			this.maxRight = end;
			this.length = end - start;
			this.parent = parent;
		}
	}

	public static void main(String[] args) {
		IntervalTree intervalTree = new IntervalTree(0);
		intervalTree.insert(10, 20);
		intervalTree.insert(5, 25);
		intervalTree.insert(2, 4);
		intervalTree.insert(7, 15);

		intervalTree.insert(25, 40);
		intervalTree.insert(14, 25);
		intervalTree.insert(30, 50);

		System.out.println(intervalTree.findIntersections(new Solution.Interval(0, 15)));

		System.out.println(intervalTree.findIntersections(new Solution.Interval(24, 100)));

		System.out.println(intervalTree.findIntersections(new Solution.Interval(0, 5)));

		System.out.println(intervalTree.findIntersections(new Solution.Interval(10, 20)));
	}
}
