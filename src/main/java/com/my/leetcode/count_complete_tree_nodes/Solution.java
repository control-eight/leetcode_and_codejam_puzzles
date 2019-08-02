package com.my.leetcode.count_complete_tree_nodes;

public class Solution {

    public int countNodes(TreeNode root) {
        int height = height(root, 1);
        if (height <= 1) return height;

        int min = (int) Math.pow(2, height - 1);
        int max = (int) (Math.pow(2, height) - 1);
        int result = binarySearch(root, min, max, 1, height);
        return result == Integer.MAX_VALUE? max: result - 1;
    }

    private int binarySearch(TreeNode parent, int min, int max, int currHeight, int height) {
        int mid = min + (max - min) / 2;
        int lastNullAt = Integer.MAX_VALUE;
        if (min < max - 1) {
            if (traverseTo(parent.left,currHeight + 1, height)) {
                lastNullAt = mid;
                lastNullAt = Math.min(binarySearch(parent.left, min, mid, currHeight + 1, height), lastNullAt);
            } else {
                lastNullAt = Math.min(binarySearch(parent.right, mid, max, currHeight + 1, height), lastNullAt);
            }
        } else {
            if (traverseTo(parent, currHeight, height)) {
                lastNullAt = Math.min(max, lastNullAt);
            }
        }
        return lastNullAt;
    }

    private boolean traverseTo(TreeNode parent, int currHeight, int height) {
        if (currHeight == height) {
            return parent == null;
        } else {
            return traverseTo(parent.right, currHeight + 1, height);
        }
    }

    private int height(TreeNode root, int count) {
        if (root == null) return count - 1;
        else return height(root.left, count + 1);
    }
}