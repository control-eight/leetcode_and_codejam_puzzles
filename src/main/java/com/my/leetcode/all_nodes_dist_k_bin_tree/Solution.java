package com.my.leetcode.all_nodes_dist_k_bin_tree;

import java.util.*;

public class Solution {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, Set<TreeNode>> graph = new HashMap<>();
        traverse(root, graph);

        Set<TreeNode> visited = new HashSet<>();
        Queue<Container> toVisit = new LinkedList<>();
        toVisit.add(new Container(target, 0));
        visited.add(target);
        List<Integer> result = new ArrayList<>();
        while (!toVisit.isEmpty()) {
            Container v = toVisit.poll();
            if (v.distance == K) {
                result.add(v.treeNode.val);
                continue;
            }
            for (TreeNode next : graph.get(v.treeNode)) {
                if (visited.add(next)) {
                    toVisit.offer(new Container(next, v.distance + 1));
                }
            }
        }
        return result;
    }

    private void traverse(TreeNode root, Map<TreeNode, Set<TreeNode>> graph) {
        graph.putIfAbsent(root, new HashSet<>());
        if (root.left != null) {
            graph.get(root).add(root.left);
            graph.putIfAbsent(root.left, new HashSet<>());
            graph.get(root.left).add(root);
            traverse(root.left, graph);
        }
        if (root.right != null) {
            graph.get(root).add(root.right);
            graph.putIfAbsent(root.right, new HashSet<>());
            graph.get(root.right).add(root);
            traverse(root.right, graph);
        }
    }

    private static class Container {
        private TreeNode treeNode;
        private int distance;

        public Container(TreeNode treeNode, int distance) {
            this.treeNode = treeNode;
            this.distance = distance;
        }
    }
}
