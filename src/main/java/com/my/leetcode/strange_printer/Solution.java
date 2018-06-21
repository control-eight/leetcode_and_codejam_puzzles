package com.my.leetcode.strange_printer;

import java.util.Random;

/**
 * Created by alex.bykovsky on 10/6/17.
 */
public class Solution {

	public static void main(String[] args) {
		assertEquals(4, new Solution().strangePrinter("xasxsax"));

		assertEquals(2, new Solution().strangePrinter("aaabbb"));
		assertEquals(5, new Solution().strangePrinter("abcabc"));
		assertEquals(3, new Solution().strangePrinter("ababa"));
		assertEquals(3, new Solution().strangePrinter("abc"));
		assertEquals(2, new Solution().strangePrinter("aba"));
		assertEquals(3, new Solution().strangePrinter("aabaabaa"));
		assertEquals(5, new Solution().strangePrinter("aabaabaabccb"));

		assertEquals(6, new Solution().strangePrinter("kgwkwgnkpk"));
		assertEquals(5, new Solution().strangePrinter("fqqptqtpq"));

		assertEquals(7, new Solution().strangePrinter("bdbdbccbcjcbccb"));
		assertEquals(7, new Solution().strangePrinter("bdbbadabcaba"));
		assertEquals(4, new Solution().strangePrinter("bcbcbd"));
		assertEquals(15, new Solution().strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) throw new RuntimeException(expected + " " + actual);
	}

	public int strangePrinter(String s) {
		if(s.length() == 0) return 0;

		int[][][] solution = new int[s.length()][s.length()][s.length()];
		solution[0][0][0] = 1;

		for (int k = 1; k < s.length(); k++) {
			for (int i = 0; i <= k; i++) {


				for (int j = 0; j <= i; j++) {

					int current = solution[k - 1][i][j] + 1;

					




				}
			}
		}

		return solution[s.length() - 1][0][s.length() - 1];
	}
}
