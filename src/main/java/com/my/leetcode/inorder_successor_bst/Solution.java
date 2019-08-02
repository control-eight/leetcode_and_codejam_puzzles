package com.my.leetcode.inorder_successor_bst;

public class Solution {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;

        if (root == p) {
            return mostLeftInRightSubtree(root.right);
        }
        TreeNode res;
        if (p.val < root.val) {
            res = inorderSuccessor(root.left, p);
        } else {
            res = inorderSuccessor(root.right, p);
        }

        if (res != null && root.val > p.val && root.val < res.val || res == null && root.val > p.val) {
            return root;
        } else {
            return res;
        }
    }

    private TreeNode mostLeftInRightSubtree(TreeNode root) {
        if (root == null) return null;
        return root.left == null? root: mostLeftInRightSubtree(root.left);
    }
}
