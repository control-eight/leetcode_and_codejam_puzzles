package com.my.leetcode.my_calendar_II;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarTwo {

    public static void main(String[] args) {
        MyCalendarTwo myCalendar = new MyCalendarTwo();
        int[][] intput = new int[][] {
                {10,20},{50,60},{10,40},{5,15},{5,10},{25,55}
        };
        for (int[] ints : intput) {
            System.out.println(myCalendar.book(ints[0], ints[1]));;
        }
    }

    private TreeMap<Integer, int[]> intervals;

    public MyCalendarTwo() {
        intervals = new TreeMap<>();
    }

    public boolean book(int start, int end) {

        Map.Entry<Integer, int[]> prevStart = intervals.floorEntry(start);
        //overlap
        if (prevStart.getValue()[0] > start) {
            if (prevStart.getValue()[1] == 2) return false;
            else {
                intervals.remove(prevStart.getKey());
                intervals.put(prevStart.getKey(), new int[] {start, 2});
                intervals.put(start, new int[] {prevStart.getValue()[0], 2});
            }
        }





        return true;
    }
}
