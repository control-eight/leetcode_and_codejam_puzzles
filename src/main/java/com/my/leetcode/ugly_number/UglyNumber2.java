package com.my.leetcode.ugly_number;

/**
 * Created by alex.bykovsky on 9/14/17.
 */
public class UglyNumber2 {

	public static void main(String[] args) {
		assertEquals(true, new UglyNumber2().isUgly(6));
		assertEquals(true, new UglyNumber2().isUgly(8));
		assertEquals(false, new UglyNumber2().isUgly(7));
	}

	private static void assertEquals(boolean expected, boolean actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	/*Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
	For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.*/

	public boolean isUgly(int num) {

		if(num == 0) return false;
		if(num == 1) return true;

		int[] factors = new int[] {2, 3, 5};

		while (num != 1) {
			boolean divided = false;
			for (int factor : factors) {
				if(num % factor == 0) {
					num = num / factor;
					divided = true;
				}
			}
			if(!divided) return false;
		}

		return true;
	}
}
