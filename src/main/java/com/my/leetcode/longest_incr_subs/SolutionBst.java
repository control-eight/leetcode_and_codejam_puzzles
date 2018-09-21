package com.my.leetcode.longest_incr_subs;

public class SolutionBst {

    public static void main(String[] args) {
        System.out.println(new SolutionBst().lengthOfLIS(new int[] {3,5,6,2,5,4,19,5,6,7,12}));
        System.out.println(new SolutionBst().lengthOfLIS(new int[] {10,9,2,5,3,4}));
        System.out.println(new SolutionBst().lengthOfLIS(new int[] {1,3,6,7,9,4,10,5,6}));
        System.out.println(new SolutionBst().lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
        System.out.println(new SolutionBst().lengthOfLIS(new int[] {1,2,3}));
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        TreeNode root = new TreeNode(nums[0]);
        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            TreeNode treeNode = new TreeNode(nums[i]);
            insert(root, treeNode);
            max = Math.max(max, treeNode.maxCount);
        }
        return max;
    }

    private static class TreeNode {
        private int num;
        private int maxCount;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int num) {
            this.num = num;
            this.maxCount = 1;
        }
    }

    private void insert(TreeNode parent, TreeNode treeNode) {
        if (treeNode.num <= parent.num) {
            if (parent.left == null) {
                parent.left = treeNode;
            } else{
                insert(parent.left, treeNode);
            }
            parent.maxCount = Math.max(parent.maxCount, treeNode.maxCount);
        } else {
            treeNode.maxCount = Math.max(treeNode.maxCount, parent.maxCount + 1);
            if (parent.right == null) {
                parent.right = treeNode;
            } else{
                insert(parent.right, treeNode);
            }
        }
    }
}
