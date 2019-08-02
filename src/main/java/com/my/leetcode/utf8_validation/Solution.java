package com.my.leetcode.utf8_validation;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().validUtf8(new int[] {197, 130, 1}));
        System.out.println(new Solution().validUtf8(new int[] {235, 140, 4}));
    }

    public boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int d = data[i];
            int nextCount = countFirstOneBytes(d);
            if (nextCount == 1 || nextCount > 4) return false;
            for (int j = 1; j < nextCount; j++) {
                if (i + j >= data.length || !checkLeadingBytes(data[i + j])) return false;
            }
            i += nextCount > 0? nextCount - 1: 0;
        }
        return true;
    }

    private boolean checkLeadingBytes(int datum) {
        return ((datum & (1 << 7)) != 0) && ((datum & (1 << 6)) == 0);
    }

    private int countFirstOneBytes(int d) {
        int mask = 1 << 7;
        int count = 0;
        while ((mask & d) != 0) {
            mask >>= 1;
            count++;
        }
        return count;
    }
}
