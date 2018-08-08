package com.my.leetcode.next_closest_time;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().nextClosestTime("17:01"));
        System.out.println(new Solution().nextClosestTime("19:34"));
        System.out.println(new Solution().nextClosestTime("23:59"));
        System.out.println(new Solution().nextClosestTime("01:37"));
    }

    public String nextClosestTime(String time) {
        String hh = time.split(":")[0];
        String mm = time.split(":")[1];

        String digits = time.replace(":", "");

        Integer nextMmStr = findClosestNextTo(Integer.parseInt(mm), digits, 60);
        if (nextMmStr > Integer.parseInt(mm)) {
            return hh + ":" + String.format("%02d", nextMmStr);
        }

        Integer nextHhStr = findClosestNextTo(Integer.parseInt(hh), digits, 24);
        String minMMStr = findMin(digits);

        return String.format("%02d", nextHhStr) + ":" + minMMStr;
    }

    private Integer findClosestNextTo(int hours, String digitsStr, int max) {
        Set<Integer> digits = new HashSet<>();
        for (char c : digitsStr.toCharArray()) {
            digits.add(c - '0');
        }

        List<Integer> numbers = new ArrayList<>();
        for (Integer digit : digits) {
            for (Integer integer : digits) {
                int hh = digit * 10 + integer;
                if (hh < max) {
                    numbers.add(hh);
                }
            }
        }

        int minBigger = Integer.MAX_VALUE;
        for (Integer number : numbers) {
            if (number > hours) {
                minBigger = Math.min(minBigger, number);
            }
        }
        if (minBigger < Integer.MAX_VALUE) return minBigger;

        int min = Integer.MAX_VALUE;
        for (Integer number : numbers) {
            min = Math.min(min, number);
        }
        return min;
    }

    private String findMin(String digits) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < digits.length(); i++) {
            min = Math.min(min, digits.charAt(i) - '0');
        }
        return min + "" + min;
    }
}
