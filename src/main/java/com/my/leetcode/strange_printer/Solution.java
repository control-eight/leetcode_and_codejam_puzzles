package com.my.leetcode.strange_printer;

/**
 * Created by alex.bykovsky on 10/6/17.
 */
public class Solution {

	public static void main(String[] args) {
        assertEquals(4, new Solution().strangePrinter("xasxsax"));
        assertEquals(4, new Solution().strangePrinter("xaxsax"));
        assertEquals(3, new Solution().strangePrinter("ababa"));
        assertEquals(3, new Solution().strangePrinter("abc"));
        assertEquals(2, new Solution().strangePrinter("aba"));

        assertEquals(14, new Solution().strangePrinter("dddccbdbababaddcbcaabdbdddcccddbba"));
        assertEquals(10, new Solution().strangePrinter("abababaabababaabababa"));
        assertEquals(1, new Solution().strangePrinter("aaaaaaaaaaaaaaaaaaa"));
        assertEquals(2, new Solution().strangePrinter("aaabbb"));
        assertEquals(5, new Solution().strangePrinter("abcabc"));
		assertEquals(3, new Solution().strangePrinter("aabaabaa"));
		assertEquals(6, new Solution().strangePrinter("kgwkwgnkpk"));
		assertEquals(5, new Solution().strangePrinter("fqqptqtpq"));
		assertEquals(4, new Solution().strangePrinter("bcbcbd"));
        assertEquals(7, new Solution().strangePrinter("bdbbadabcaba"));
        assertEquals(5, new Solution().strangePrinter("aabaabaabccb"));
        assertEquals(7, new Solution().strangePrinter("bdbdbccbcjcbccb"));
        assertEquals(15, new Solution().strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"));
        assertEquals(19, new Solution().strangePrinter("baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa"));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) throw new RuntimeException(expected + " " + actual);
	}

	public int strangePrinter(String s) {
		if(s.length() == 0) return 0;
        cache = new int[s.length()][s.length()];
		return calc(s.toCharArray(), 0, s.length() - 1);
	}

	private int[][] cache;

    private int calc(char[] sArr, int lo, int hi) {
	    if (lo > hi) {
	        return 0;
        }
        if (cache[lo][hi] > 0) {
	        return cache[lo][hi];
        }

        int result = calc(sArr, lo + 1, hi) + 1;
        for (int i = hi; i > lo; i--) {
	        if (sArr[lo] == sArr[i]) {
	            result = Math.min(result, calc(sArr, lo + 1, i - 1) + calc(sArr, i, hi));
            }
        }

        cache[lo][hi] = result;
        return result;
    }
}
