package com.my.leetcode.count_of_smaller_numbers_after_self;

public class FenwickTree {

    private int[] tree;

    public FenwickTree(int size) {
        tree = new int[size + 1];
    }

    public void update(int i, int value) {
        i++;
        while (i < tree.length) {
            tree[i] += value;
            i += i & (-i);
        }
    }

    public int rangeQuery(int i) {
        i++;
        int value = 0;
        while (i > 0) {
            value += tree[i];
            i -= i & (-i);
        }
        return value;
    }

    public int rangeQuery(int i, int j) {
        return rangeQuery(j) - rangeQuery(i);
    }
}
