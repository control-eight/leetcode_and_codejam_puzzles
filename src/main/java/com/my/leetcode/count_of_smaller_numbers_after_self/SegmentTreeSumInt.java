package com.my.leetcode.count_of_smaller_numbers_after_self;

public class SegmentTreeSumInt {

    private int[] tree;

    private int startIndex;

    public SegmentTreeSumInt(int length) {
        int size = init(length);
        tree = new int[size];
    }

    private int init(int length) {
        int height = (int) (Math.ceil(Math.log(length)) / Math.log(2)) + 1;
        startIndex = (int) Math.pow(2, height) - 1;
        return 2 * (int) Math.pow(2, height) - 1;
    }

    public void updateDiff(int index, int value) {
        int i = startIndex + index;
        tree[i] += value;
        do {
            i = parent(i);
            tree[i] += value;
        } while (i > 0);
    }

    private int rangeQueryInternal(int i, int prev, int result) {
        if (prev == rightChild(i)) {
            result += tree[leftChild(i)];
        }
        int nextParent = parent(i);
        if (nextParent == i) return result;
        return rangeQueryInternal(nextParent, i, result);
    }

    public int rangeQuery(int hi) {
        if (hi == 0 || hi >= tree.length - startIndex) return 0;

        int i = startIndex + hi - 1;
        return rangeQueryInternal(parent(i), i, tree[i]);
    }

    public int rangeQuery(int lo, int hi) {
        return rangeQuery(hi) - rangeQuery(lo);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return i * 2 + 2;
    }
}
