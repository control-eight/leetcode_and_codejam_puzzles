package com.my.leetcode.bt_vert_order_traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex.bykovsky on 4/2/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(new Solution2().verticalOrder(createBst(new Integer[] {3,9,20,null,null,15,7})));
		System.out.println(new Solution2().verticalOrder(createBst(new Integer[] {3,9,8,4,0,1,7})));
		System.out.println(new Solution2().verticalOrder(createBst(new Integer[]{3, 9, 8, 4, 0, 1, 7, null, null, null, 2, 5})));

	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		if (root == null) return Collections.emptyList();

		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		result.get(0).add(root.val);

		List<TreeNode> levelList = new LinkedList<>();
		levelList.add(root);

		while (!levelList.isEmpty()) {

			double index = 0;
			List<TreeNode> newLevelList = new LinkedList<>();
			for (TreeNode treeNode : levelList) {

				if (treeNode != null && treeNode.left != null) {
					if (index == 0) {
						result.add(0, new ArrayList<>());
						result.get(0).add(treeNode.left.val);
					} else {
						result.get((int) index).add(treeNode.left.val);
					}
				}
				if (treeNode != null && treeNode.left != null) {
					newLevelList.add(treeNode.left);
				}

				if (index == 0) {
					index += 2;
				} else {
					index += 0.5;
				}

				if (treeNode != null && treeNode.right != null) {
					if (index >= result.size() - 1) {
						result.add(new ArrayList<>());
						result.get(result.size() - 1).add(treeNode.right.val);
					} else {
						result.get((int) index).add(treeNode.right.val);
					}
				}
				if (treeNode != null && treeNode.right != null) {
					newLevelList.add(treeNode.right);
				}

				index += 0.5;
			}
			levelList = newLevelList;
		}
		return result;
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
