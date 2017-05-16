package com.my.lca;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by alex.bykovsky on 3/24/17.
 */
public class Solution {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if(root == null) return null;

		return lca(root, p, q);
	}

	private TreeNode lca(TreeNode parent, TreeNode p, TreeNode q) {

		if(parent == null) {
			return null;
		}

		TreeNode resultLeft = null;

		if(parent.left != null) {
			resultLeft = lca(parent.left, p, q);
		}

		TreeNode resultRight = null;

		if(parent.right != null) {
			resultRight = lca(parent.right, p, q);
		}

		TreeNode current = (parent == p || parent == q)? parent: null;

		if(resultLeft != null) {
			if(resultRight != null) {
				return parent;
			} else if(resultLeft == p && current == q || resultLeft == q && current == p) {
				return current;
			} else {
				return resultLeft;
			}
		} else if(resultRight != null) {
			if(resultRight == p && current == q || resultRight == q && current == p) {
				return current;
			} else {
				return resultRight;
			}
		} else {
			return current;
		}
	}

	public static void main(String[] args) {
		test1();
		test2();
	}

	private static void test1() {
		TreeNode root = new TreeNode(1);

		root.left = new TreeNode(2);

		System.out.println(new Solution().lowestCommonAncestor(root, root, root.left));
	}

	private static void test2() {
		TreeNode root = new TreeNode(-1);
		root.left = new TreeNode(0);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(-2);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(8);

		System.out.println(new Solution().lowestCommonAncestor(root, root.left.left, root.right.left));
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
					'}';
		}
	}
}
