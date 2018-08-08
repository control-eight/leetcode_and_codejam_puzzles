package com.my.leetcode.count_of_smaller_numbers_after_self;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionBstBest {

    public static void main(String[] args) {
        System.out.println(new SolutionBstBest().countSmaller(new int[] {2, 1}));
        System.out.println(new SolutionBstBest().countSmaller(new int[] {-1, -1}));
        System.out.println(new SolutionBstBest().countSmaller(new int[] {}));
        System.out.println(new SolutionBstBest().countSmaller(new int[] {5,2,6,1}));
        System.out.println(new SolutionSegmnetTree2().countSmaller(new int[] {3,2,2,1}));
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();

        Node root = new Node(nums[nums.length - 1]);
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            addNode(root, nums[i]);
            result.add(findCountLessThan(root, nums[i]));
        }
        Collections.reverse(result);
        return result;
    }

    private Node addNode(Node parent, int value) {
        if (value < parent.value) {
            parent.count++;
            if (parent.left == null) {
                parent.left = new Node(value);
                return parent.left;
            } else {
                return addNode(parent.left, value);
            }
        } else {
            if (parent.right == null) {
                parent.right = new Node(value);
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
            result++;
            result += parent.count;
            result += findCountLessThan(parent.right, num);
        } else {
            result += findCountLessThan(parent.left, num);
        }
        return result;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
        private int count;
        public Node(int value) {
            this.value = value;
        }
    }
}
