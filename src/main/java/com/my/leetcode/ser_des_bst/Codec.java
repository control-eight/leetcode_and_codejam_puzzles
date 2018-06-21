package com.my.leetcode.ser_des_bst;

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

		result.append(root.val).append(",");
		serialize(root.left, result);
		serialize(root.right, result);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {

		if(data == null || data.isEmpty()) return null;

		TreeNode root = null;
		for (String s : data.split(",")) {

			int x = Integer.parseInt(s);

			if(root == null) {
				root = new TreeNode(x);
			} else {
				insert(root, x);
			}
		}

		return root;
	}

	private void insert(TreeNode parent, Integer value) {

		if(parent == null) return;

		if(value >= parent.val) {

			if(parent.right != null) {
				insert(parent.right, value);
			} else {
				parent.right = new TreeNode(value);
			}
		} else {

			if(parent.left != null) {
				insert(parent.left, value);
			} else {
				parent.left = new TreeNode(value);
			}
		}
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

		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);

		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);

		root.right.right = new TreeNode(7);

		Codec codec = new Codec();
		System.out.println(codec.serialize(root));
		System.out.println(codec.deserialize(codec.serialize(root)));;
	}
}


