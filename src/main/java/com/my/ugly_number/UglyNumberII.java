package com.my.ugly_number;

/**
 * Created by alex.bykovsky on 9/14/17.
 */
public class UglyNumberII {

	public static void main(String[] args) {
		assertEquals(12, new UglyNumberII().nthUglyNumber(10));
		assertEquals(20, new UglyNumberII().nthUglyNumber(14));
		assertEquals(36, new UglyNumberII().nthUglyNumber(20));
		assertEquals(937500, new UglyNumberII().nthUglyNumber(500));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int nthUglyNumber(int n) {

		if(n == 0) return 0;
		if(n == 1) return 1;

		//1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25 27 30 32 36
		int[] factorsPos = new int[]{0,0,0};

		int[] solutions = new int[n];
		solutions[0] = 1;
		for(int i = 1; i < n; i++) {

			int two = solutions[factorsPos[0]] * 2;

			int tree = Integer.MAX_VALUE;
			if(solutions[factorsPos[1]] % 2 != 0) {
				tree = solutions[factorsPos[1]] * 3;
			} else {
				factorsPos[1]++;
			}

			int five = Integer.MAX_VALUE;
			if(solutions[factorsPos[2]] % 2 != 0 && solutions[factorsPos[2]] % 3 != 0) {
				five = solutions[factorsPos[2]] * 5;
			} else {
				factorsPos[2]++;
			}

			if(two <= Math.min(tree, five)) {
				factorsPos[0]++;
				solutions[i] = two;
			} else if(tree <= Math.min(two, five)) {
				factorsPos[1]++;
				solutions[i] = tree;
			} else {
				factorsPos[2]++;
				solutions[i] = five;
			}
		}
		return solutions[solutions.length - 1];
	}
}
