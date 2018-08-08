package com.my.leetcode.count_of_smaller_numbers_after_self;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionBst {

    public static void main(String[] args) {
        System.out.println(new SolutionBst().countSmaller(new int[] {-1, -1}));
        System.out.println(new SolutionBst().countSmaller(new int[] {}));
        System.out.println(new SolutionBst().countSmaller(new int[] {5,2,6,1}));
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();

        Node[] nodes = new Node[nums.length];
        Node root = new Node(nums[0], null);
        nodes[0] = root;
        for (int i = 1; i < nums.length; i++) {
            nodes[i] = addNode(root, nums[i]);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            result.add(findCountLessThan(root, nums[i]));
            nodes[i].deleted = true;
            decrementCount(nodes[i]);
        }
        return result;
    }

    private void decrementCount(Node node) {
        if (node.parent == null) return;
        if (node.parent.left == node) {
            node.parent.count--;
        }
        decrementCount(node.parent);
    }

    private Node addNode(Node parent, int value) {
        if (value < parent.value) {
            parent.count++;
            if (parent.left == null) {
                parent.left = new Node(value, parent);
                return parent.left;
            } else {
                return addNode(parent.left, value);
            }
        } else {
            if (parent.right == null) {
                parent.right = new Node(value, parent);
                return parent.right;
            } else {
                return addNode(parent.right, value);
            }
        }
    }

    private int findCountLessThan(Node parent, int num) {
        if (parent == null) return 0;

        int result = 0;
        if (parent.value < num) {
            result += parent.deleted? 0: 1;
            result += parent.count;
            result += findCountLessThan(parent.right, num);
        } else {
            result += findCountLessThan(parent.left, num);
        }
        return result;
    }

    private static class Node {
        private int value;
        private boolean deleted;
        private Node parent;
        private Node left;
        private Node right;
        private int count;
        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
