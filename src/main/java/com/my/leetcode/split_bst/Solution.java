package com.my.leetcode.split_bst;

public class Solution {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(6);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(7);
        TreeNode[] result = new Solution().splitBST(treeNode, 2);
        System.out.println();
    }

    public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode first = createLess(copy(root), V);
        TreeNode second = createGreater(copy(root), V);
        return new TreeNode[] {first, second};
    }

    private TreeNode createLess(TreeNode parent, int V) {
        if (parent == null) {
            return null;
        }
        if (parent.val <= V) {
            parent.left = createLess(copy(parent.left), V);
            parent.right = createLess(copy(parent.right), V);
            return parent;
        } else {
            return createLess(copy(parent.left), V);
        }
    }

    private TreeNode createGreater(TreeNode parent, int V) {
        if (parent == null) {
            return null;
        }
        if (parent.val > V) {
            parent.left = createGreater(copy(parent.left), V);
            parent.right = createGreater(copy(parent.right), V);
            return parent;
        } else {
            return createGreater(copy(parent.right), V);
        }
    }

    private TreeNode copy(TreeNode root) {
        if (root == null) return null;
        TreeNode r = new TreeNode(root.val);
        r.left = root.left;
        r.right = root.right;
        return r;
    }
}
