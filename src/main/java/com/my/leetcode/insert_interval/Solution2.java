package com.my.leetcode.insert_interval;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution2 {

    public static void main(String[] args) {
        System.out.println(Arrays.binarySearch(new int [] {13,17}, 17));
        // [[1,3],[6,9]], newInterval = [2,5]
        // [[1,5],[6,9]]
        //System.out.println(Arrays.binarySearch(new int [] {1,6}, 2));
        //System.out.println(Arrays.binarySearch(new int [] {1,6}, 5));
        // [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        // [[1,2],[3,10],[12,16]]
        //System.out.println(Arrays.binarySearch(new int []{1,3,6,8,12}, 4));
        //System.out.println(Arrays.binarySearch(new int []{1,3,6,8,12}, 8));
        System.out.println(new Solution2().insert(Arrays.asList(new Interval(1,5), new Interval(6, 8)),
                new Interval(3, 7)));
        //[[1,8]]
        System.out.println(new Solution2().insert(Arrays.asList(new Interval(1,3), new Interval(6, 9)),
                new Interval(2, 5)));
        // [[1,5],[6,9]]
        System.out.println(new Solution2().insert(Arrays.asList(new Interval(0,5), new Interval(9, 12)), new Interval(7, 16)));
        //[[0,5],[7,16]]
        System.out.println(new Solution2().insert(Arrays.asList(new Interval(1,5)), new Interval(0, 3)));
        //[[0,5]]
        System.out.println(new Solution2().insert(Arrays.asList(new Interval(1,5)), new Interval(2,3)));
        //[[1,5]]
        System.out.println(new Solution2().insert(Arrays.asList(new Interval(1,5)), new Interval(6, 7)));
        //[[1,5],[6,7]
        System.out.println(new Solution2().insert(Collections.emptyList(), new Interval(5, 7)));
        //[[5,7]]
        System.out.println(new Solution2().insert(Arrays.asList(new Interval(1,2), new Interval(3, 5),
                new Interval(6, 7),new Interval(8, 10),new Interval(12, 16)),
                new Interval(4, 8)));
        // [[1,2],[3,10],[12,16]]
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.isEmpty()) {
            return Collections.singletonList(newInterval);
        }

        int[] arr = new int[intervals.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = intervals.get(i).start;
        }

        int s1 = Arrays.binarySearch(arr, newInterval.start);
        int s2 = Arrays.binarySearch(arr, newInterval.end);

        s1 = s1 < 0? Math.abs(s1) - 1: s1;
        s2 = s2 < 0? Math.abs(s2) - 1: s2;

        List<Interval> result = new LinkedList<>();

        if (s1 > 0) {
            if (intervals.get(s1 - 1).end < newInterval.start) {
                result.addAll(intervals.subList(0, s1));
            } else {
                result.addAll(intervals.subList(0, s1 - 1));
            }
        }

        newInterval = mergeIntervals(intervals, s1 - 1, newInterval);
        newInterval = mergeIntervals(intervals, s1, newInterval);
        newInterval = mergeIntervals(intervals, s2 - 1, newInterval);
        newInterval = mergeIntervals(intervals, s2, newInterval);

        result.add(newInterval);

        if (s2 < intervals.size()) {
            if (intervals.get(s2).start <= newInterval.end) {
                result.addAll(intervals.subList(s2 + 1, intervals.size()));
            } else {
                result.addAll(intervals.subList(s2, intervals.size()));
            }
        }

        return result;
    }

    private Interval mergeIntervals(List<Interval> intervals, int i, Interval interval) {
        if (i < 0 || i >= intervals.size()) return interval;
        Interval edgeInterval = intervals.get(i);
        if (edgeInterval.end < interval.start || edgeInterval.start > interval.end) return interval;
        return new Interval(Math.min(edgeInterval.start, interval.start), Math.max(edgeInterval.end, interval.end));
    }
}
