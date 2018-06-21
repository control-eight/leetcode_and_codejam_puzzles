package com.my.leetcode.maximum_depth_bt;

/**
 * Created by alex.bykovsky on 4/1/18.
 */
public class Solution {

	public int maxDepth(TreeNode root) {
		return dfs(root, 1);
	}

	private int dfs(TreeNode root, int depth) {
		if (root == null) {
			return depth - 1;
		}
		return Math.max(dfs(root.left, depth + 1), dfs(root.right, depth + 1));
	}
}
