package com.my.leetcode.merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals.isEmpty()) return result;

        intervals.sort(Comparator.comparingInt(o -> o.start));
        for (int i = 0; i < intervals.size();) {
            Interval current = intervals.get(i);
            while (i < intervals.size() - 1 && current.end >= intervals.get(i + 1).start) {
                current = new Interval(current.start, Math.max(intervals.get(i + 1).end, current.end));
                i++;
            }
            result.add(current);
            i++;
        }
        return result;
    }
}
