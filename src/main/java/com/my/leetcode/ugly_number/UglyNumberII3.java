package com.my.leetcode.ugly_number;

/**
 * Created by alex.bykovsky on 9/14/17.
 */
public class UglyNumberII3 {

	public static void main(String[] args) {
		assertEquals(12, new UglyNumberII3().nthUglyNumber(10));
		assertEquals(20, new UglyNumberII3().nthUglyNumber(14));
		assertEquals(36, new UglyNumberII3().nthUglyNumber(20));
		assertEquals(937500, new UglyNumberII3().nthUglyNumber(500));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int nthUglyNumber(int n) {

		if(n == 0) return 0;
		if(n == 1) return 1;

		int twoPos = 0;
		int threePos = 0;
		int fivePos = 0;

		int[] solutions = new int[n];
		solutions[0] = 1;
		for(int i = 1; i < n; i++) {
			int two = solutions[twoPos] * 2;
			int three = solutions[threePos] * 3;
			int five = solutions[fivePos] * 5;

			int min =  Math.min(two, Math.min(three, five));

			if(min == two) {
				twoPos++;
				solutions[i] = two;
				if(two == three) threePos++;
				if(two == five) fivePos++;
			} else if(min == three) {
				threePos++;
				solutions[i] = three;

				if(three == five) fivePos++;
			} else if(min == five) {
				fivePos++;
				solutions[i] = five;
			}
		}
		return solutions[solutions.length - 1];
	}
}
