package com.my.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class SegmentTreeMathMonoid<T> {

    public static void main(String[] args) {
        testSum();
        testMax();
    }

    private static void testSum() {
        SegmentTreeMathMonoid<Integer> segmentTree = new SegmentTreeMathMonoid<>(6, MathMonoid.INT_SUM, (i, j) -> i + j);
        segmentTree.updateDiff(0, 1);
        segmentTree.updateDiff(1, 2);
        segmentTree.updateDiff(2, 3);
        segmentTree.updateDiff(3, 4);
        segmentTree.updateDiff(4, 5);
        segmentTree.updateDiff(5, 6);
        System.out.println(segmentTree.rangeQuery(0, 1));
        System.out.println(segmentTree.rangeQuery(2, 4));
        System.out.println(segmentTree.rangeQuery(2));
        System.out.println(segmentTree.rangeQuery(0, 6));
        segmentTree.updateDiff(2, 1000);
        System.out.println(segmentTree.rangeQuery(0, 3));
        System.out.println(segmentTree.rangeQuery(1, 4));
    }

    private static void testMax() {
        SegmentTreeMathMonoid<Integer> segmentTree = new SegmentTreeMathMonoid<>(6, MathMonoid.INT_MAX, (i, j) -> j);
        segmentTree.updateDiff(0, 1);
        segmentTree.updateDiff(1, 2);
        segmentTree.updateDiff(2, 3);
        segmentTree.updateDiff(3, 4);
        segmentTree.updateDiff(4, 5);
        segmentTree.updateDiff(5, 6);
        System.out.println(segmentTree.rangeQuery( 0, 1));
        System.out.println(segmentTree.rangeQuery(2, 4));
        System.out.println(segmentTree.rangeQuery(2));
        System.out.println(segmentTree.rangeQuery(0, 6));
        segmentTree.updateDiff(2, 1000);
        System.out.println(segmentTree.rangeQuery(0, 3));
        System.out.println(segmentTree.rangeQuery(1, 4));
    }

    private List<T> tree;

    private int startIndex;

    private MathMonoid<T> monoid;

    private BiFunction<T, T, T> updater;

    public SegmentTreeMathMonoid(int length, MathMonoid<T> monoid, BiFunction<T, T, T> updater) {
        int initLength = init(length);
        initTreeWithIdentityElement(monoid, initLength);
        this.monoid = monoid;
        this.updater = updater;
    }

    private void initTreeWithIdentityElement(MathMonoid<T> monoid, int initLength) {
        this.tree = new ArrayList<>(initLength);
        for (int i = 0; i < initLength; i++) {
            this.tree.add(monoid.getIdentityEl());
        }
    }

    private int init(int length) {
        int height = (int) (Math.ceil(Math.log(length)) / Math.log(2)) + 1;
        this.startIndex = (int) Math.pow(2, height) - 1;
        return 2 * (int) Math.pow(2, height) - 1;
    }

    public void updateDiff(int index, T value) {
        int i = startIndex + index;
        tree.set(i, this.updater.apply(tree.get(i), value));
        do {
            i = parent(i);
            tree.set(i, this.monoid.apply(tree.get(i), value));
        } while (i > 0);
    }

    private T rangeQueryInternal(int lo, int hi, int llo, int lhi, int index) {
        if (lo >= llo && hi <= lhi) {
            return tree.get(index);
        }
        if (lo > lhi || hi < llo)
            return this.monoid.getIdentityEl();
        int mid = lo + (hi - lo) / 2;
        if (mid == hi || mid == lo) return this.monoid.getIdentityEl();
        return this.monoid.apply(rangeQueryInternal(lo, mid, llo, lhi, leftChild(index)),
                rangeQueryInternal(mid, hi, llo, lhi, rightChild(index)));
    }

    public T rangeQuery(int lo, int hi) {
        return rangeQueryInternal(0, (tree.size() + 1)/2, lo, hi, 0);
    }

    public T rangeQuery(int hi) {
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
