package com.my.leetcode.design;

/**
 * Created by alex.bykovsky on 9/21/17.
 */
public class MedianFinder {

	private Node root;

	private int size;

	/** initialize your data structure here. */
	public MedianFinder() {
		root = null;
	}

	public void addNum(int num) {
		if(root == null) {
			root = new Node(num);
		} else {
			insert(root, num);
		}
		size++;
	}

	public double findMedian() {
		if(root == null) {
			return 0;
		}
		return size % 2 == 0? (findByIndex(root, size / 2, 0) + findByIndex(root, size / 2 - 1, 0))/2:
				findByIndex(root, size / 2, 0);
	}

	private static class Node {

		private Node left;
		private Node right;

		private int num;

		private int leftSize;
		private int rightSize;

		public Node(int num) {
			this.num = num;
		}
	}

	private void insert(Node parent, int num) {

		if(num < parent.num) {
			if(parent.left == null) {
				parent.left = new Node(num);
			} else {
				insert(parent.left, num);
			}
			parent.leftSize++;
		} else {
			if(parent.right == null) {
				parent.right = new Node(num);
			} else {
				insert(parent.right, num);
			}
			parent.rightSize++;
		}
	}

	private double findByIndex(Node parent, int index, int indexSoFar) {

		if(index == indexSoFar + parent.leftSize) {
			return parent.num;
		}

		if(parent.leftSize > 0 && index <= parent.leftSize + indexSoFar) {
			return findByIndex(parent.left, index, indexSoFar);
		} else {
			return findByIndex(parent.right, index, indexSoFar + 1 + parent.leftSize);
		}
	}

	public static void main(String[] args) {
		MedianFinder medianFinder;


		/*medianFinder = new MedianFinder();
		medianFinder.addNum(1);
		medianFinder.addNum(2);
		System.out.println(medianFinder.findMedian()); //1.5
		medianFinder.addNum(3);
		System.out.println(medianFinder.findMedian()); //2.0
		medianFinder.addNum(4);
		System.out.println(medianFinder.findMedian()); //2.5
		medianFinder.addNum(5);
		System.out.println(medianFinder.findMedian()); //3.0*/

		/*medianFinder = new MedianFinder();
		medianFinder.addNum(-1);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(-2);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(-3);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(-4);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(-5);
		System.out.println(medianFinder.findMedian());*/

		medianFinder = new MedianFinder();
		medianFinder.addNum(6);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(10);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(2);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(6);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(5);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(0);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(6);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(3);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(1);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(0);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(0);
		System.out.println(medianFinder.findMedian());


	}
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
