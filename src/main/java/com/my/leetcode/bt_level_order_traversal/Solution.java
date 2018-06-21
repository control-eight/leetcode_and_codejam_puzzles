package com.my.leetcode.bt_level_order_traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex.bykovsky on 3/31/18.
 */
public class Solution {

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		levelOrder(root, 0, result);
		return result;
	}

	private void levelOrder(TreeNode root, int level, List<List<Integer>> acc) {
		if (root == null) {
			return;
		}
		if (acc.size() <= level) {
			acc.add(new ArrayList<>());
		}
		acc.get(level).add(root.val);
		levelOrder(root.left, level + 1, acc);
		levelOrder(root.right, level + 1, acc);
	}
}
