package com.my.leetcode.find_mode_in_bin_search_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        System.out.println(Arrays.toString(new Solution().findMode(root)));
    }

    public int[] findMode(TreeNode root) {
        List<int[]> modes = new ArrayList<>();
        addPrev(traverseInOrder(root, new int[] {-1, 0}, modes), modes);
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
             result[i] = modes.get(i)[0];
        }
        return result;
    }

    private int[] traverseInOrder(TreeNode parent, int[] prev, List<int[]> modes) {
        if (parent == null) return prev;

        prev = traverseInOrder(parent.left, prev, modes);
        prev = solve(parent, prev, modes);
        return traverseInOrder(parent.right, prev, modes);
    }

    private int[] solve(TreeNode parent, int[] prev, List<int[]> modes) {
        if (parent.val != prev[0]) {
            addPrev(prev, modes);
            prev = new int[] {parent.val, 1};
        } else {
            prev = new int[] {prev[0], prev[1] + 1};
        }
        return prev;
    }

    private void addPrev(int[] prev, List<int[]> modes) {
        if (prev[1] > 0) {
            if (!modes.isEmpty() && modes.get(0)[1] < prev[1]) {
                modes.clear();
            }
            if (modes.isEmpty() || modes.get(0)[1] == prev[1]) {
                modes.add(new int[]{prev[0], prev[1]});
            }
        }
    }
}
