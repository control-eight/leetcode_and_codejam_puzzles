package com.my.leetcode.bt_vert_order_traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by alex.bykovsky on 4/2/18.
 */
public class Solution {

	public static void main(String[] args) {
		TreeNode bst = createBst(new Integer[]{3, 9, 8, 4, 0, 1, 7, null, null, null, 2, 5});
		System.out.println(new Solution().verticalOrder(bst));
		System.out.println(new Solution().verticalOrder(createBst(new Integer[] {3,9,20,null,null,15,7})));
		System.out.println(new Solution().verticalOrder(createBst(new Integer[] {3,9,8,4,0,1,7})));
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		if (root == null) return Collections.emptyList();

		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		result.get(0).add(root.val);
		traverse(root, 0, result);
		return result;
	}

	private int traverse(TreeNode root, int pointer, List<List<Integer>> result) {
		int leftAdded = 0;
		if (root.left != null) {
			if (pointer - 1 < 0) {
				result.add(0, new ArrayList<>());
				result.get(0).add(root.left.val);
				leftAdded += traverse(root.left, 0, result) + 1;
			} else {
				result.get(pointer - 1).add(root.left.val);
				leftAdded += traverse(root.left, pointer - 1, result);
			}
		}
		if (root.right != null) {
			pointer += leftAdded;
			if (pointer == result.size() - 1) {
				result.add(new ArrayList<>());
				result.get(result.size() - 1).add(root.right.val);
				traverse(root.right, result.size() - 1, result);
			} else {
				result.get(pointer + 1).add(0, root.right.val);
				traverse(root.right, pointer + 1, result);
			}
		}
		return leftAdded;
	}

	private static TreeNode createBst(Integer[] integers) {

		TreeNode root = new TreeNode(integers[0]);
		List<TreeNode> level = new ArrayList<>();
		level.add(root);
		for (int i = 1; i < integers.length;) {
			List<TreeNode> newLevel = new ArrayList<>();
			for (TreeNode treeNode : level) {
				if (i == integers.length) break;
				treeNode.left = integers[i] == null? null: new TreeNode(integers[i]);
				i++;
				if (i == integers.length) break;
				treeNode.right = integers[i] == null? null: new TreeNode(integers[i]);
				i++;
				newLevel.add(treeNode.left);
				newLevel.add(treeNode.right);
			}
			level = newLevel;
		}
		return root;
	}
}
