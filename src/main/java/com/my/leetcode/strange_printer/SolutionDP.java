package com.my.leetcode.strange_printer;

import java.util.Random;

/**
 * Created by alex.bykovsky on 10/6/17.
 */
public class SolutionDP {

	public static void main(String[] args) {
		assertEquals(4, new SolutionDP().strangePrinter("xasxsax"));

		assertEquals(2, new SolutionDP().strangePrinter("aaabbb"));
		assertEquals(5, new SolutionDP().strangePrinter("abcabc"));
		assertEquals(3, new SolutionDP().strangePrinter("ababa"));
		assertEquals(3, new SolutionDP().strangePrinter("abc"));
		assertEquals(2, new SolutionDP().strangePrinter("aba"));
		assertEquals(3, new SolutionDP().strangePrinter("aabaabaa"));
		assertEquals(5, new SolutionDP().strangePrinter("aabaabaabccb"));

		assertEquals(6, new SolutionDP().strangePrinter("kgwkwgnkpk"));
		assertEquals(5, new SolutionDP().strangePrinter("fqqptqtpq"));

		assertEquals(7, new SolutionDP().strangePrinter("bdbdbccbcjcbccb"));
		assertEquals(7, new SolutionDP().strangePrinter("bdbbadabcaba"));
		assertEquals(4, new SolutionDP().strangePrinter("bcbcbd"));

		//assertEquals(15, new Solution().strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"));

		int length = 100;
		int count = 1000000;
		Random random = new Random(length * count);

		for (int i = 0; i < count; i++) {

			char[] arr = new char[length];
			for (int j = 0; j < length; j++) {
				arr[j] = (char) ('a' + random.nextInt(25));
			}
			try {
				assertEquals(new SolutionTD().strangePrinter(new String(arr)), new SolutionDP().strangePrinter(new String(arr)));
			} catch (RuntimeException e) {
				System.out.println(e + " arr = " + new String(arr));
			}
		}
	}

	private static void assertEquals(int expected, int actual) {
		if (expected != actual) throw new RuntimeException(expected + " " + actual);
	}

	public int strangePrinter(String s) {
		if (s.length() == 0) return 0;

		int[][] cache = new int[s.length() + 1][s.length() + 1];
		for (int i = 0; i < s.length() + 1; i++) {
			for (int j = 0; j < s.length() + 1; j++) {
				if(i == j) cache[i][j] = 1;
				else cache[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 1; i < s.length() - 1; i++) {
			for (int j = 0; j < s.length() - i; j++) {
				for (int k = j + 1; k < i + 1; k++) {
					int solution1 = cache[j - 1][i] + 1;
					int solution2 = Integer.MAX_VALUE;
					if(s.charAt(i) == s.charAt(k)) {
						solution2 = cache[i][k - 1] + cache[k][j - 1];
					}
					cache[i][j] = Math.min(Math.min(cache[i][j], solution1), solution2);
				}
			}
		}

		return cache[0][s.length()];
	}
}
