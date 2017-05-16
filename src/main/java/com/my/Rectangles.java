package com.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by alex.bykovsky on 3/19/17.
 */
public class Rectangles {

	public static void main(String[] args) {
		testCases(20, 1000);
		testCases(20, 198);
		testCases(30, 1000);
		testCases(30, 212);
		testCases(30, 38);
		testCases(50, 1000);
		testCases(50, 97);
		testCases(50, 18);
		testCases(50, 8);
		testCases(2000, 20000);
		testCases(2000, 290);
		testCases(2000, 195);
		testCases(2000, 46);
		testCases(2000, 6);
		testCases(5000, 50000);
		testCases(5000, 4988);
		testCases(5000, 492);
		testCases(5000, 152);
		testCases(5000, 76);
		testCases(5000, 31);
		testCases(5000, 25);
		testCases(13000000, 1000);
	}

	private static void testCases(int size, int secondNotMoreThan) {
		List<Rectangle> rectangleList = generateList(size);

		Node bstRoot = new Rectangles().createBst(rectangleList.iterator(), null);

		Integer[] array = rectangleList.stream().map(v -> v.second).toArray(Integer[]::new);
		Arrays.sort(array);

		Integer[] ordered = new Integer[rectangleList.size()];
		inOrderTraversal(bstRoot, ordered, 0);

		boolean res = Arrays.equals(array, ordered);
		if(!res) {
			throw new RuntimeException("Aren't equal");
		}

		findAndSetMins(bstRoot);

		long start1 = System.currentTimeMillis();
		Rectangle rectangle = findMin(bstRoot, secondNotMoreThan);
		System.out.println("size = " + size + " secondNotMoreThan = " + secondNotMoreThan + " " + rectangle);
		long end1 = System.currentTimeMillis() - start1;

		long start2 = System.currentTimeMillis();
		Rectangle other = rectangleList.stream().filter(v -> v.second < secondNotMoreThan)
				.min(Comparator.comparingInt(o -> o.first)).get();
		System.out.println((System.currentTimeMillis() - start2) + " vs " + end1);

		if(!Objects.equals(rectangle.first, other.first) && !Objects.equals(rectangle.second, other.second))
			throw new RuntimeException("Aren't equal");
	}

	private static Rectangle findMin(Node parent, int secondNotMoreThan) {

		if(parent == null) {
			return null;
		}

		if(parent.rectangle.second < secondNotMoreThan) {
			return min(parent.min, findMin(parent.right, secondNotMoreThan));
		} else {
			return findMin(parent.left, secondNotMoreThan);
		}
	}

	private static Rectangle min(Rectangle left, Rectangle right) {
		if(left != null && right != null && left.first < right.first || left != null && right == null) {
			return left;
		} else {
			return right;
		}
	}

	private static int inOrderTraversal(Node parent, Integer[] arr, int index) {

		if(parent == null) {
			return index;
		}

		index = inOrderTraversal(parent.left, arr, index);
		arr[index] = parent.rectangle.second;
		index++;
		index = inOrderTraversal(parent.right, arr, index);

		return index;
	}

	private static Rectangle findAndSetMins(Node parent) {

		if(parent == null) {
			return null;
		}

		Rectangle minLeftRight = findAndSetMins(parent.left);

		Rectangle minRight = findAndSetMins(parent.right);

		Rectangle minLeft = null;
		if(parent.left != null) {
			minLeft = parent.left.min;
		}

		parent.min = min(min(minLeft, minLeftRight), parent.rectangle);

		return minRight == null? parent.min : min(parent.min, minRight);
	}

	private Node createBst(Iterator<Rectangle> iterator, Node root) {

		if(!iterator.hasNext()) {
			return root;
		}

		if(root == null) {
			root = new Node(null, iterator.next());
			return createBst(iterator, root);
		} else {
			Node node = new Node(iterator.next());
			insertNode(node, root);
			return createBst(iterator, root);
		}
	}

	private void insertNode(Node child, Node parent) {

		if(parent == null) {
			return;
		}

		if(child.rectangle.second >= parent.rectangle.second) {
			if(parent.right != null) {
				insertNode(child, parent.right);
			} else {
				child.parent = parent;
				child.parent.right = child;
			}
		} else {
			if(parent.left != null) {
				insertNode(child, parent.left);
			} else {
				child.parent = parent;
				child.parent.left = child;
			}
		}
	}

	private static List<Rectangle> generateList(int size) {
		List<Rectangle> result = new ArrayList<Rectangle>(size);

		Random random = new Random(size);

		for(int i = 0; i < size; i++) {
			result.add(new Rectangle((int)(random.nextDouble() * size * 10), (int)(random.nextDouble() * size * 10)));
		}

		return result;
	}

	private static class Node {

		private Node parent;
		private Node left;
		private Node right;

		private Rectangle rectangle;

		private Rectangle min;

		public Node(Node parent, Rectangle rectangle) {
			this.parent = parent;
			this.rectangle = rectangle;
		}

		public Node(Rectangle rectangle) {
			this.rectangle = rectangle;
		}
	}

	private static class Rectangle {

		private Integer first;

		private Integer second;

		public Rectangle(Integer first, Integer second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public String toString() {
			return "Rectangle{" +
					"first=" + first +
					", second=" + second +
					'}';
		}
	}
}
