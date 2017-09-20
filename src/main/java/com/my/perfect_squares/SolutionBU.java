package com.my.perfect_squares;

/**
 * Created by alex.bykovsky on 9/12/17.
 */
public class SolutionBU {

	public static void main(String[] args) {
		assertEquals(1, new SolutionBU().numSquares(4));
		assertEquals(3, new SolutionBU().numSquares(12));
		assertEquals(2, new SolutionBU().numSquares(13));
		assertEquals(4, new SolutionBU().numSquares(15));
		assertEquals(1, new SolutionBU().numSquares(16));
		assertEquals(4, new SolutionBU().numSquares(31));
		assertEquals(4, new SolutionBU().numSquares(239));

		long start = System.currentTimeMillis();
		int max = 0;
		for(int i = 1; i < 250; i++) {
			int actual = new SolutionBU().numSquares(i);
			assertEquals(new Solution().numSquares(i), actual);
			max = Math.max(max, actual);
		}
		System.out.println(System.currentTimeMillis() - start);
		System.out.println("Max: " + max);

		assertEquals(4, new SolutionBU().numSquares(7168));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int numSquares(int n) {

		int[] solutions = new int[n + 1];

		solutions[0] = 0;
		solutions[1] = 1;

		for(int i = 2; i <= n; i++) {
			int square = 1;
			int add = 3;
			int min = Integer.MAX_VALUE;
			while(square <= i) {
				min = Math.min(solutions[i - square] + 1, min);
				square += add;
				add += 2;
			}
			solutions[i] = min;
		}
		return solutions[solutions.length - 1];
	}
}
