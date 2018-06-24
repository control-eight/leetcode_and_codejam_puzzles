package com.my.hackerrank.week_of_code_38.cyclical_queries;

import java.util.Arrays;

/**
 * @author abykovsky
 * @since 6/23/18
 */
class SegmentTreeRMQ {
    TreeNode st[];

    TreeNode maxValue(TreeNode x, TreeNode y) {
        return (x.max > y.max) ? x : y;
    }

    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    TreeNode RMQUtil(int ss, int se, int qs, int qe, int index) {
        if (qs <= ss && qe >= se)
            return st[index];
        if (se < qs || ss > qe)
            return new TreeNode(null, Integer.MIN_VALUE);

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return maxValue(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
                RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
    }

    TreeNode RMQ(int n, int qs, int qe) {
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return null;
        }
        return RMQUtil(0, n - 1, qs, qe, 0);
    }

    TreeNode constructSTUtil(TreeNode arr[], int ss, int se, int si) {
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }
        int mid = getMid(ss, se);
        st[si] = maxValue(constructSTUtil(arr, ss, mid, si * 2 + 1),
                constructSTUtil(arr, mid + 1, se, si * 2 + 2));
        return st[si];
    }

    void constructST(TreeNode arr[], int n) {
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        st = new TreeNode[max_size];
        constructSTUtil(arr, 0, n - 1, 0);
    }

    void updateValSegTree(int treeIndex, int lo, int hi, int arrIndex, Node node, long val)
    {
        if (lo == hi) {                 // leaf node. update element.
            st[treeIndex] = new TreeNode(node, val);
            return;
        }

        int mid = lo + (hi - lo) / 2;   // recurse deeper for appropriate child

        if (arrIndex > mid)
            updateValSegTree(2 * treeIndex + 2, mid + 1, hi, arrIndex, node, val);
        else if (arrIndex <= mid)
            updateValSegTree(2 * treeIndex + 1, lo, mid, arrIndex, node, val);

        // merge updates
        TreeNode treeNode = st[2 * treeIndex + 1];
        TreeNode treeNode1 = st[2 * treeIndex + 2];

        if (treeNode.max > treeNode1.max) {
            st[treeIndex] = treeNode;
        } else {
            st[treeIndex] = treeNode1;
        }
    }

    public static void main(String args[]) {
        TreeNode[] arr = new TreeNode[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = new TreeNode(new Node(i), i + 1);
        }

        int n = arr.length;
        SegmentTreeRMQ tree = new SegmentTreeRMQ();

        tree.constructST(arr, n);

        System.out.println(tree.RMQ(n, 0, 0));
        System.out.println(tree.RMQ(n, 0, 1));
        System.out.println(tree.RMQ(n, 0, 2));
        System.out.println(tree.RMQ(n, 0, 3));
        System.out.println(tree.RMQ(n, 0, 4));

        System.out.println(Arrays.toString(tree.st));

        System.out.println("------------------------");

        tree.updateValSegTree(0, 0, n - 1, 3, arr[3].node, 13);

        System.out.println(tree.RMQ(n, 0, 0));
        System.out.println(tree.RMQ(n, 0, 1));
        System.out.println(tree.RMQ(n, 0, 2));
        System.out.println(tree.RMQ(n, 0, 3));
        System.out.println(tree.RMQ(n, 0, 4));

        System.out.println("------------------------");

        tree.updateValSegTree(0, 0, n - 1, 3, arr[3].node, 4);

        System.out.println(tree.RMQ(n, 0, 0));
        System.out.println(tree.RMQ(n, 0, 1));
        System.out.println(tree.RMQ(n, 0, 2));
        System.out.println(tree.RMQ(n, 0, 3));
        System.out.println(tree.RMQ(n, 0, 4));
    }

    private static class Node {
        private int index;
        public Node(int index) {
            this.index = index;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    '}';
        }
    }

    private static class TreeNode {
        private Node node;
        private long max;
        public TreeNode(Node node, long max) {
            this.node = node;
            this.max = max;
        }
        @Override
        public String toString() {
            return "TreeNode{" +
                    "node=" + node +
                    ", max=" + max +
                    '}';
        }
    }
}