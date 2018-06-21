package com.my.leetcode.fibonacci;

/**
 * Created by alex.bykovsky on 9/17/17.
 */
public class SolutionN {

	public static void main(String[] args) {
		assertEquals(5, new SolutionN().solve(5));
		assertEquals(8, new SolutionN().solve(6));
	}

	private static void assertEquals(long expected, long actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public long solve(int n) {

		if(n <= 2) return 1;

		long prevPrev = 1;
		long prev = 1;

		for(long i = 3; i <= n; i++) {
			long current = prev + prevPrev;
			prevPrev = prev;
			prev = current;
		}

		return prev;
	}
}
