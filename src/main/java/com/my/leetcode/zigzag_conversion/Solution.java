package com.my.leetcode.zigzag_conversion;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().convert("A", 3));
        //A
        System.out.println(new Solution().convert("A", 1));
        //A
        System.out.println(new Solution().convert("ABC", 2));
        //
        System.out.println(new Solution().convert("ABCD", 3));
        //
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
        //PAHNAPLSIIGYIR
        System.out.println(new Solution().convert("PAYPALISHIRING", 4));
        //PINALSIGYAHRPI
    }

    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        StringBuilder result = new StringBuilder();
        int cols = (int) Math.ceil((double) s.length() / (numRows + (numRows - 2)));
        for (int ii = 0; ii < numRows; ii++) {
            if (ii == 0 || ii == numRows - 1) {
                for (int i = 0; i < cols + 1; i++) {
                    int index = ii + i * (numRows + (numRows - 2));
                    if (index >= s.length()) break;
                    result.append(s.charAt(index));
                }
            } else {
                int index = ii;
                if (index >= s.length()) break;
                result.append(s.charAt(index));
                for (int i = 1; i < 2 * cols; i++) {
                    if (i % 2 == 0) {
                        index = index + ii * 2;
                    } else {
                        index = index + (numRows - ii - 1) * 2;
                    }
                    if (index >= s.length()) break;
                    result.append(s.charAt(index));
                }
            }
        }
        return result.toString();
    }
}
