package com.my.leetcode.bin_tree_longest_consec_seq_ii;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {

    }

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int max = Math.max(traverse(root, 1, true), traverse(root, 1, false));
        return Math.max(max, traverse2(root));
    }

    public int traverse(TreeNode root, int path, boolean decreasing) {
        int result = path;
        if (root.left != null) {
            result = Math.max(result, traverse(root.left, isValid(root, root.left, decreasing)? path + 1: 1, decreasing));
        }
        if (root.right != null) {
            result = Math.max(result, traverse(root.right, isValid(root, root.right, decreasing)? path + 1: 1, decreasing));
        }
        return result;
    }

    private boolean isValid(TreeNode root, TreeNode left, boolean decreasing) {
        return decreasing? root.val - left.val == 1: left.val - root.val == 1;
    }

    private int traverse2(TreeNode root) {
        int result = 0;
        int p1 = 0;
        int p2 = 0;
        if (root.left != null) {
            result = Math.max(result, traverse2(root.left));
            p1 += traverse3(root, root.left, true);
            p2 += traverse3(root, root.left, false);
        }
        if (root.right != null) {
            result = Math.max(result, traverse2(root.right));
            p1 += traverse3(root, root.right, false);
            p2 += traverse3(root, root.right, true);
        }
        return Math.max(Math.max(result, p1 + 1), p2 + 1);
    }

    private int traverse3(TreeNode parent, TreeNode child, boolean decreasing) {
        if (child == null) return 0;
        if (decreasing && parent.val - child.val != 1) return 0;
        if (!decreasing && child.val - parent.val != 1) return 0;
        return Math.max(traverse3(child, child.left, decreasing) + 1, traverse3(child, child.right, decreasing) + 1);
    }
}
