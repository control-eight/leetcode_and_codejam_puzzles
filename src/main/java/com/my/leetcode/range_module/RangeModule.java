package com.my.leetcode.range_module;

import java.util.*;

public class RangeModule {

    public static void main(String[] args) {
        RangeModule rangeModule;

        rangeModule = new RangeModule();
        rangeModule.addRange(10, 20);
        rangeModule.removeRange(14, 16);
        System.out.println(rangeModule.queryRange(10, 14));
        //true
        System.out.println(rangeModule.queryRange(13, 15));
        //false
        System.out.println(rangeModule.queryRange(16, 17));
        //true

        System.out.println("---");

        rangeModule = new RangeModule();
        rangeModule.addRange(10, 180);
        rangeModule.addRange(150, 200);
        rangeModule.addRange(250, 500);
        System.out.println(rangeModule.queryRange(50, 100));
        //true
        System.out.println(rangeModule.queryRange(180, 300));
        //false
        System.out.println(rangeModule.queryRange(600, 1000));
        //false
        rangeModule.removeRange(50, 150);
        System.out.println(rangeModule.queryRange(50, 100));
        //false

        System.out.println("---");

        rangeModule = new RangeModule();
        rangeModule.addRange(6,8);
        rangeModule.removeRange(7,8);
        rangeModule.removeRange(8,9);
        rangeModule.addRange(8,9);
        rangeModule.removeRange(1,3);
        rangeModule.addRange(1,8);
        System.out.println(rangeModule.queryRange(2,4));
        //true
        System.out.println(rangeModule.queryRange(2,9));
        //true
        System.out.println(rangeModule.queryRange(4,6));
        //true
    }

    private TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        List<int[]> subIntervals = getIntervals(left, right);
        for (int[] entry : subIntervals) {
            intervals.remove(entry[0]);
        }
        intervals.put(Math.min(left, subIntervals.isEmpty()? Integer.MAX_VALUE: subIntervals.get(0)[0]),
                Math.max(right, subIntervals.isEmpty()? 0: subIntervals.get(subIntervals.size() - 1)[1]));
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> floor = intervals.floorEntry(left);
        return floor != null && floor.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        List<int[]> subIntervals = getIntervals(left, right);
        for (int[] entry : subIntervals) {
            intervals.remove(entry[0]);
        }
        if (!subIntervals.isEmpty() && subIntervals.get(0)[0] < left) {
            intervals.put(subIntervals.get(0)[0], left);
        }
        if (!subIntervals.isEmpty() && subIntervals.get(subIntervals.size() - 1)[1] > right) {
            intervals.put(right, subIntervals.get(subIntervals.size() - 1)[1]);
        }
    }

    private List<int[]> getIntervals(int left, int right) {
        Map.Entry<Integer, Integer> floor = intervals.floorEntry(left);
        Map.Entry<Integer, Integer> ceil = intervals.ceilingEntry(right);
        SortedMap<Integer, Integer> sortedMap = intervals.subMap(left, right);

        List<int[]> res = new ArrayList<>();
        if (floor != null && floor.getValue() >= left) {
            res.add(new int[] {floor.getKey(), floor.getValue()});
        }
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            res.add(new int[] {entry.getKey(), entry.getValue()});
        }
        if (ceil != null && ceil.getKey() <= right) {
            res.add(new int[] {ceil.getKey(), ceil.getValue()});
        }
        return res;
    }
}
