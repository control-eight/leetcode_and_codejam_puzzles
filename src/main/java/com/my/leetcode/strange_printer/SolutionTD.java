package com.my.leetcode.strange_printer;

/**
 * Created by alex.bykovsky on 10/6/17.
 */
public class SolutionTD {

	public static void main(String[] args) {
		assertEquals(4, new SolutionTD().strangePrinter("xasxsax"));

		assertEquals(2, new SolutionTD().strangePrinter("aaabbb"));
		assertEquals(5, new SolutionTD().strangePrinter("abcabc"));
		assertEquals(3, new SolutionTD().strangePrinter("ababa"));
		assertEquals(3, new SolutionTD().strangePrinter("abc"));
		assertEquals(2, new SolutionTD().strangePrinter("aba"));
		assertEquals(3, new SolutionTD().strangePrinter("aabaabaa"));
		assertEquals(5, new SolutionTD().strangePrinter("aabaabaabccb"));

		assertEquals(6, new SolutionTD().strangePrinter("kgwkwgnkpk"));
		assertEquals(5, new SolutionTD().strangePrinter("fqqptqtpq"));

		assertEquals(7, new SolutionTD().strangePrinter("bdbdbccbcjcbccb"));
		assertEquals(7, new SolutionTD().strangePrinter("bdbbadabcaba"));
		assertEquals(4, new SolutionTD().strangePrinter("bcbcbd"));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) throw new RuntimeException(expected + " " + actual);
	}

	public int strangePrinter(String s) {
		if(s.length() == 0) return 0;

		Integer[][] cache = new Integer[s.length()][s.length()];
		strangePrinterInt(s, 0, s.length() - 1, cache);
		return cache[0][cache.length - 1];
	}

	private int strangePrinterInt(String s, int i, int j, Integer[][] cache) {

		if(i > j) return 0;

		if(cache[i][j] != null) {
			return cache[i][j];
		}

		int min = strangePrinterInt(s, i + 1, j, cache) + 1;

		for(int k = i + 1; k <= j; k++) {
			if(s.charAt(k) == s.charAt(i)) {
				min = Math.min(min, strangePrinterInt(s, i, k - 1, cache) + strangePrinterInt(s, k + 1, j, cache));
			}
		}

		cache[i][j] = min;

		return min;
	}
}
