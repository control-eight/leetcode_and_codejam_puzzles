package com.my.leetcode.count_of_smaller_numbers_after_self;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class SegmentTreeMathGroup<T> {

    public static void main(String[] args) {
        SegmentTreeMathGroup segmentTree = new SegmentTreeMathGroup<>(new Integer[] {1,2,3,4,5,6},
                new MathGroup<>(
                        (i, j) -> i + j,
                        (y, i) -> y - i,
                        0),
                (i, j) -> i + j);
        System.out.println(segmentTree.tree);
        System.out.println(segmentTree.rangeQuery(6));
        System.out.println(segmentTree.rangeQuery(2, 4));
        System.out.println(segmentTree.rangeQuery(0, 6));

        segmentTree = new SegmentTreeMathGroup<>(new Integer[] {1,2,3,4,5,6},
                new MathGroup<>(
                        (i, j) -> i * j,
                        (y, i) -> y / i,
                        1),
                (i, j) -> i + j);
        System.out.println(segmentTree.tree);
        System.out.println(segmentTree.rangeQuery(6));
        System.out.println(segmentTree.rangeQuery(0, 2));
        System.out.println(segmentTree.rangeQuery(2, 4));
        System.out.println(segmentTree.rangeQuery(0, 6));
    }

    private List<T> tree;

    private int startIndex;

    private MathGroup<T> group;

    private BiFunction<T, T, T> updater;

    public SegmentTreeMathGroup(T[] nums, MathGroup<T> group, BiFunction<T, T, T> updater) {
        this(nums, group.identityEl, group, updater);
    }

    public SegmentTreeMathGroup(T[] nums, T defaultValue, MathGroup<T> group, BiFunction<T, T, T> updater) {
        init(nums.length, group, updater);
        for (int i = 0; i < nums.length; i++) {
            T value = nums[i] == null ? defaultValue : nums[i];
            updateDiff(i, value);
        }
    }

    public SegmentTreeMathGroup(int length, T defaultValue, MathGroup<T> group, BiFunction<T, T, T> updater) {
        init(length, group, updater);
        for (int i = 0; i < length; i++) {
            updateDiff(i, defaultValue);
        }
    }

    private void init(int length, MathGroup<T> group, BiFunction<T, T, T> updater) {
        int height = (int) (Math.ceil(Math.log(length)) / Math.log(2)) + 1;
        this.startIndex = (int) Math.pow(2, height) - 1;
        int size = 2 * (int) Math.pow(2, height) - 1;

        this.group = group;
        this.updater = updater;
        tree = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            tree.add(this.group.identityEl);
        }
    }

    public void updateDiff(int index, T diff) {
        int i = startIndex + index;
        this.tree.set(i, this.updater.apply(this.tree.get(i), diff));
        do {
            i = parent(i);
            tree.set(i, this.group.associativeFunc.apply(tree.get(i), diff));
        } while (i > 0);
    }

    private T rangeQueryInternal(int i, int prev, T result) {
        if (prev == rightChild(i)) {
            result = this.group.associativeFunc.apply(tree.get(leftChild(i)), result);
        }
        int nextParent = parent(i);
        if (nextParent == i) return result;
        return rangeQueryInternal(nextParent, i, result);
    }

    public T rangeQuery(int hi) {
        if (hi == 0 || hi >= tree.size() - startIndex) return this.group.identityEl;

        int i = startIndex + hi - 1;
        return rangeQueryInternal(parent(i), i, tree.get(i));
    }

    public T rangeQuery(int lo, int hi) {
        return this.group.reverseFunc.apply(rangeQuery(hi), rangeQuery(lo));
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

    public static class MathGroup<T> {

        private BiFunction<T, T, T> associativeFunc;

        private BiFunction<T, T, T> reverseFunc;

        private T identityEl;

        public MathGroup(BiFunction<T, T, T> associativeFunc, BiFunction<T, T, T> reverseFunc, T identityEl) {
            this.associativeFunc = associativeFunc;
            this.reverseFunc = reverseFunc;
            this.identityEl = identityEl;
        }

        public static MathGroup<Integer> INT_ADDITION = new MathGroup<>
                ((i, j) -> i + j,
                        (y, i) -> y - i,
                        0);
    }
}
