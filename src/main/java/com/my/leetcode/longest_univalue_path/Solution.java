package com.my.leetcode.longest_univalue_path;

import java.util.LinkedList;

public class Solution {

    public static void main(String[] args) {

    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;

        int result = 0;
        LinkedList<TreeNode> toVisit = new LinkedList<>();
        toVisit.add(root);
        while (!toVisit.isEmpty()) {
            TreeNode poll = toVisit.poll();
            result = Math.max(result, follow(poll.left, toVisit, poll.val)
                    + follow(poll.right, toVisit, poll.val));
        }
        return result;
    }

    private int follow(TreeNode node, LinkedList<TreeNode> toVisit, int val) {
        if (node == null) {
            return 0;
        }
        if (node.val != val) {
            toVisit.add(node);
            return 0;
        }
        return 1 + Math.max(follow(node.left, toVisit, val), follow(node.right, toVisit, val));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

