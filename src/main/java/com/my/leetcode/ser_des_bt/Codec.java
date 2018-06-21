package com.my.leetcode.ser_des_bt;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by alex.bykovsky on 3/28/17.
 */
public class Codec {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root == null) return null;

		StringBuilder result = new StringBuilder();

		serialize(root, result);

		if(result.lastIndexOf(",") == result.length() - 1) {
			result.delete(result.length() - 1, result.length());
		}

		return result.toString();
	}

	private void serialize(TreeNode root, StringBuilder result) {

		if(root == null) {
			return;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while(!queue.isEmpty()) {

			TreeNode parent = queue.poll();

			if(parent == null) {
				result.append("null").append(",");
			} else {
				result.append(parent.val).append(",");
				queue.offer(parent.left);
				queue.offer(parent.right);
			}
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {

		if(data == null || data.isEmpty()) return null;


		String[] split = data.split(",");

		if(split.length == 0) {
			return null;
		}

		Queue<TreeNode> parentQueue = new LinkedList<>();
		TreeNode root = new TreeNode(Integer.parseInt(split[0]));
		TreeNode currentParent = root;
		boolean leftNext = true;
		for (int i = 1; i < split.length; i++) {

			String s = split[i];

			Integer value = null;
			if(!"null".equals(s)) {
				value = Integer.parseInt(s);
			}

			if(leftNext) {
				if(value == null) {
					currentParent.left = null;
				} else {
					TreeNode treeNode = new TreeNode(value);
					currentParent.left = treeNode;
					parentQueue.offer(treeNode);
				}
				leftNext = false;
			} else {
				if(value == null) {
					currentParent.right = null;
				} else {
					TreeNode treeNode = new TreeNode(value);
					currentParent.right = treeNode;
					parentQueue.offer(treeNode);
				}

				currentParent = parentQueue.poll();
				leftNext = true;
			}
		}

		return root;
	}

	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }

		@Override
		public String toString() {
			return "TreeNode{" +
					"val=" + val +
					", left=" + left +
					", right=" + right +
					'}';
		}
	}

	public static void main(String[] args) {
		//test1();
		test2();
	}

	private static void test1() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);

		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);

		root.right.right = new TreeNode(7);

		Codec codec = new Codec();
		System.out.println(codec.serialize(root));
		System.out.println(codec.deserialize(codec.serialize(root)));
	}

	private static void test2() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(10);
		root.right = new TreeNode(6);

		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(12);
		root.left.right = new TreeNode(1);

		root.right.right = new TreeNode(0);

		Codec codec = new Codec();
		System.out.println("5,10,6,2,1,null,0,12,null,null,null");
		System.out.println(codec.serialize(root));
		System.out.println(codec.deserialize(codec.serialize(root)));
	}
}


