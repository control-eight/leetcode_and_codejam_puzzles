package com.my.leetcode.bin_tree_longest_consec_seq;

public class Solution {

    public static void main(String[] args) {

    }

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        return traverse(root, 1);
    }

    public int traverse(TreeNode root, int path) {
        int result = path;
        if (root.left != null) {
            result = Math.max(result, traverse(root.left, root.left.val - root.val == 1? path + 1: 1));
        }
        if (root.right != null) {
            result = Math.max(result, traverse(root.right, root.right.val - root.val == 1? path + 1: 1));
        }
        return result;
    }
}
