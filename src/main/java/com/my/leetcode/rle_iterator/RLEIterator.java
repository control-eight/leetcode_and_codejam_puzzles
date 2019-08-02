package com.my.leetcode.rle_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RLEIterator {

    public static void main(String[] args) {
        RLEIterator iterator = new RLEIterator(new int [] {3,8,0,9,2,5});
        System.out.println(iterator.next(2));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(2));
    }

    private Iterator<EncodingIterator> iter;

    private EncodingIterator current;

    public RLEIterator(int[] A) {
        List<EncodingIterator> iteratorList = new ArrayList<>(A.length / 2);
        for (int i = 0; i < A.length; i += 2) {
            if (A[i] > 0) {
                iteratorList.add(new EncodingIterator(A[i], A[i + 1]));
            }
        }
        iter = iteratorList.iterator();
    }

    public int next(int n) {
        int result = -1;
        while (n > 0 && (iter.hasNext() || current != null && current.times > 0)) {
            if (current == null || current.times < 0) {
                current = iter.next();
            }
            n -= current.next(n);
            result = current.times >= 0? current.value: -1;
        }
        return result;
    }

    private static class EncodingIterator {

        private int times;

        private int value;

        public EncodingIterator(int times, int value) {
            this.times = times;
            this.value = value;
        }

        public int next(int n) {
            int result = readCount(n);
            times -= n;
            return result;
        }

        private int readCount(int n) {
            if (times <= 0) return -1;
            else return Math.min(times, n);
        }
    }
}
