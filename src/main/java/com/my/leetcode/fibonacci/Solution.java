package com.my.leetcode.fibonacci;

/**
 * Created by alex.bykovsky on 9/17/17.
 */
public class Solution {

	public static void main(String[] args) {
		assertEquals(3, new Solution().solve(4));
		assertEquals(5, new Solution().solve(5));
		assertEquals(8, new Solution().solve(6));
		assertEquals(13, new Solution().solve(7));
		assertEquals(21, new Solution().solve(8));
		assertEquals(34, new Solution().solve(9));
		assertEquals(987, new Solution().solve(16));
		assertEquals(102334155, new Solution().solve(40));

		long start = System.nanoTime();
		for(int i = 0; i < 100000; i++) {
			new Solution().solve(i);
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for(int i = 0; i < 100000; i++) {
			new SolutionN().solve(i);
		}
		System.out.println(System.nanoTime() - start);
	}

	/*T[1] = 1;
	T[2] = 1;
	T[3] = 2;
	T[4] = 3;
	T[5] = 5;
	T[6] = 8;
	T[7] = 13;
	T[8] = 21;
	T[9] = 34;*/

	private static void assertEquals(long expected, long actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	//logn
	public long solve(int n) {
		if(n <= 2) return 1;

		return solve(n, 1, 1);
	}

	public long solve(int n, long prev, long prevPrev) {

		if(n == 0) return prev;
		if(n == 1) return prev + prevPrev;

		int degree = prev == 1? 4: 1;

		//T[n] = T[n/2]^2 + 2 * T[n/2] * T[n/2 - 1]
		//T[n - 1] = T[n/2]^2 + T[n/2 - 1]^2

		do {
			long current = prev * prev + 2 * prev * prevPrev;
			long prevCurrent = prev * prev + prevPrev * prevPrev;

			prev = current;
			prevPrev = prevCurrent;
			degree *= 2;
		} while (degree <= n);

		degree = degree / 2;

		/*for(long i = degree + 1; i <= n; i++) {
			long current = prev + prevPrev;
			prevPrev = prev;
			prev = current;
		}*/

		return solve(n - degree, prev, prevPrev);
	}
}
