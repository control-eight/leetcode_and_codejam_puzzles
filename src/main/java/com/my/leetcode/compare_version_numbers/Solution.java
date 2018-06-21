package com.my.leetcode.compare_version_numbers;

/**
 * @author abykovsky
 * @since 4/1/18
 */
public class Solution {

    public int compareVersion(String version1, String version2) {
        int i = 0;
        int j = 0;
        while (!(i >= version1.length() && j >= version2.length())) {

            long number1 = 0;
            if (i < version1.length()) {
                int endI = i;
                while (endI < version1.length() && version1.charAt(endI) != '.') {
                    endI++;
                }
                number1 = Long.valueOf(version1.substring(i, endI));
                i = endI + 1;
            }

            long number2 = 0;
            if (j < version2.length()) {
                int endJ = j;
                while (endJ < version2.length() && version2.charAt(endJ) != '.') {
                    endJ++;
                }
                number2 = Long.valueOf(version2.substring(j, endJ));
                j = endJ + 1;
            }

            if (number1 > number2) return 1;
            else if (number2 > number1) return -1;
        }
        return 0;
    }
}
