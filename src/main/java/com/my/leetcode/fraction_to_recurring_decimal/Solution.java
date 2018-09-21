package com.my.leetcode.fraction_to_recurring_decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().fractionToDecimal(2, 1));
        System.out.println(new Solution().fractionToDecimal(1, 2));
        System.out.println(new Solution().fractionToDecimal(2, 3));
        System.out.println(new Solution().fractionToDecimal(20, 6));
        System.out.println(new Solution().fractionToDecimal(2000, 6));
        System.out.println(new Solution().fractionToDecimal(1333, 77));
        System.out.println(new Solution().fractionToDecimal(500000, 25000));
        System.out.println(new Solution().fractionToDecimal(1, 6));
        System.out.println(new Solution().fractionToDecimal(1, 17));
        System.out.println(new Solution().fractionToDecimal(1, 19));
        System.out.println(new Solution().fractionToDecimal(89, 27));
        System.out.println(new Solution().fractionToDecimal(1, Integer.MAX_VALUE));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        return "";
    }
}
