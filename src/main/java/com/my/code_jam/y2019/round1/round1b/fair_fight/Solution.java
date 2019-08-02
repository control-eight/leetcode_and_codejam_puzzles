package com.my.code_jam.y2019.round1.round1b.fair_fight;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int K = in.nextInt();
            ArrayList<Integer> C = new ArrayList<>(N);
            for (int z = 1; z <= N; z++) {
                C.add(in.nextInt());
            }
            ArrayList<Integer> D = new ArrayList<>(N);
            for (int z = 1; z <= N; z++) {
                D.add(in.nextInt());
            }
            solve(i, N, K, C, D);
        }
    }

    private static void solve(int i, int N, int K, ArrayList<Integer> C, ArrayList<Integer> D) {
        SegmentTreeMathMonoid<Value> segmentTreeC = new SegmentTreeMathMonoid<>(N,
                SegmentTreeMathMonoid.MathMonoid.INT_MAX_VALUE, (k, l) -> l);
        SegmentTreeMathMonoid<Value> segmentTreeD = new SegmentTreeMathMonoid<>(N,
                SegmentTreeMathMonoid.MathMonoid.INT_MAX_VALUE, (k, l) -> l);
        for (int jj = 0; jj < N; jj++) {
            segmentTreeC.updateDiff(jj, new Value(C.get(jj), jj));
            segmentTreeD.updateDiff(N - jj - 1, new Value(D.get(N - jj - 1), N - jj - 1));
        }
        HashSet<Interval> m = new HashSet<>();
        int result = traverse(segmentTreeC, segmentTreeD, m, K, 0, N);
        System.out.println("Case #" + i + ": " + result);
        //System.out.println(m.size());
        //System.out.println(o);
    }

    private static int o;

    private static int traverse(SegmentTreeMathMonoid<Value> segmentTreeC, SegmentTreeMathMonoid<Value> segmentTreeD,
                                Set<Interval> m, int K, int i, int j) {
        o++;
        if (i >= j) return 0;
        if (!m.add(new Interval(i, j))) return 0;

        //System.out.println(i + " " + j);

        Value c = segmentTreeC.rangeQuery(i, j);
        Value d = segmentTreeD.rangeQuery(i, j);

        //System.out.println("c = " + c.index + " d = " + d.index);

        int result = 0;
        int lo = Math.min(c.index, d.index);
        int hi = Math.max(c.index, d.index);

        if (Math.abs(c.value - d.value) <= K) {
            //System.out.println(i + " " + j);
            result += (((j - 1) - hi) + 1) * ((lo - i) + 1);
        }

        result += traverse(segmentTreeC, segmentTreeD, m, K, i, hi);
        result += traverse(segmentTreeC, segmentTreeD, m, K, lo + 1, j);
        //result += traverse(segmentTreeC, segmentTreeD, m, K, lo == hi? hi + 1: hi, j);

        return result;
    }

    private static class SegmentTreeMathMonoid<T> {

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
                this.tree.add(monoid.identityEl);
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
                tree.set(i, this.monoid.associativeFunc.apply(tree.get(i), value));
            } while (i > 0);
        }

        private T rangeQueryInternal(int lo, int hi, int llo, int lhi, int index) {
            if (lo >= llo && hi <= lhi) {
                return tree.get(index);
            }
            if (lo > lhi || hi < llo)
                return this.monoid.identityEl;
            int mid = lo + (hi - lo) / 2;
            if (mid == hi || mid == lo) return this.monoid.identityEl;
            return this.monoid.associativeFunc.apply(rangeQueryInternal(lo, mid, llo, lhi, leftChild(index)),
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

        public static class MathMonoid<T> {

            private BiFunction<T, T, T> associativeFunc;

            private T identityEl;

            public MathMonoid(BiFunction<T, T, T> associativeFunc, T identityEl) {
                this.associativeFunc = associativeFunc;
                this.identityEl = identityEl;
            }

            public static SegmentTreeMathMonoid.MathMonoid<Integer> INT_SUM = new SegmentTreeMathMonoid.MathMonoid<>
                    ((i, j) -> i + j, 0);

            public static SegmentTreeMathMonoid.MathMonoid<Integer> INT_MAX = new SegmentTreeMathMonoid.MathMonoid<>
                    (Math::max, Integer.MIN_VALUE);

            public static SegmentTreeMathMonoid.MathMonoid<Value> INT_MAX_VALUE = new SegmentTreeMathMonoid.MathMonoid<>
                    (Value::max, new Value(Integer.MIN_VALUE, -1));
        }
    }

    private static class Value {
        private int value;
        private int index;

        public Value(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public static Value max(Value one, Value two) {
            if (one.value > two.value) {
                return one;
            } else {
                return two;
            }
        }
    }

    private static class Interval {
        private int lo;
        private int hi;

        public Interval(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return lo == interval.lo &&
                    hi == interval.hi;
        }

        @Override
        public int hashCode() {

            return Objects.hash(lo, hi);
        }
    }
}