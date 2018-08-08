package com.my.leetcode.count_of_smaller_numbers_after_self;

public class SegmentTreeSumMonoid {

    public static void main(String[] args) {
        SegmentTreeSumMonoid segmentTree = new SegmentTreeSumMonoid(6);
        segmentTree.updateDiff(0, 1);
        segmentTree.updateDiff(1, 2);
        segmentTree.updateDiff(2, 3);
        segmentTree.updateDiff(3, 4);
        segmentTree.updateDiff(4, 5);
        segmentTree.updateDiff(5, 6);
        System.out.println(segmentTree.rangeQuery(2, 4));
        System.out.println(segmentTree.rangeQuery(2));
        System.out.println(segmentTree.rangeQuery(0, 6));
    }

    private int[] tree;

    private int startIndex;

    public SegmentTreeSumMonoid(int length) {
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

    private int rangeQueryInternal(int lo, int hi, int llo, int lhi, int index) {
        if (lo >= llo && hi <= lhi) {
            return tree[index];
        }
        if (lo > lhi || hi < llo)
            return 0;
        int mid = lo + (hi - lo) / 2;
        if (mid == hi || mid == lo) return 0;
        return rangeQueryInternal(lo, mid, llo, lhi, leftChild(index)) +
                rangeQueryInternal(mid, hi, llo, lhi, rightChild(index));
    }

    public int rangeQuery(int lo, int hi) {
        return rangeQueryInternal(0, (tree.length + 1)/2, lo, hi, 0);
    }

    public int rangeQuery(int hi) {
        return rangeQuery(0, hi);
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
