package com.my.washing_machines;

/**
 * Created by alex.bykovsky on 9/15/17.
 */
public class Solution2 {

	public static void main(String[] args) {

		assertEquals(3, new Solution2().findMinMoves(new int[]{5,4,3,2,1}));
		assertEquals(1, new Solution2().findMinMoves(new int[]{3,0,3}));

		assertEquals(2, new Solution2().findMinMoves(new int[]{0,3,0}));
		assertEquals(8, new Solution2().findMinMoves(new int[]{0,4,12,0}));

		assertEquals(8, new Solution2().findMinMoves(new int[]{0,0,10,0,0,0,10,0,0,0}));
		assertEquals(11, new Solution2().findMinMoves(new int[]{0,0,14,0,10,0,0,0}));

		assertEquals(1, new Solution2().findMinMoves(new int[]{1,2,3}));
		assertEquals(2, new Solution2().findMinMoves(new int[]{0,2,4}));
		assertEquals(1, new Solution2().findMinMoves(new int[]{3,2,1}));

		assertEquals(2, new Solution2().findMinMoves(new int[]{0,0,2,2}));
		assertEquals(3, new Solution2().findMinMoves(new int[]{0,0,0,4}));
		assertEquals(2, new Solution2().findMinMoves(new int[]{0,0,2,0,3}));
		assertEquals(7, new Solution2().findMinMoves(new int[]{0,1,1,10}));
		assertEquals(3, new Solution2().findMinMoves(new int[]{2,2,2,6}));
		assertEquals(3, new Solution2().findMinMoves(new int[]{1,0,5}));
		assertEquals(3, new Solution2().findMinMoves(new int[]{1,2,4,5}));
		assertEquals(2, new Solution2().findMinMoves(new int[]{0,2,4,2}));

		assertEquals(66, new Solution2().findMinMoves(new int[]{100,1,1}));
		assertEquals(2, new Solution2().findMinMoves(new int[]{2,2,0,0}));
		assertEquals(2, new Solution2().findMinMoves(new int[]{4,0,4,0}));
		assertEquals(2, new Solution2().findMinMoves(new int[]{4,0,0,4}));
		assertEquals(2, new Solution2().findMinMoves(new int[]{0,4,0,4,0,4}));
		assertEquals(6, new Solution2().findMinMoves(new int[]{7,6,5,4,3,2,1}));
		assertEquals(4, new Solution2().findMinMoves(new int[]{0,0,4,0,5,0,5,0,4}));

		assertEquals(50000, new Solution2().findMinMoves(new int[]{0,100000,0,100000,0,100000,0,100000,0,100000,0,100000}));
		assertEquals(50000, new Solution2().findMinMoves(new int[]{100000,0,100000,0,100000,0,100000,0,100000,0,100000,0}));

		assertEquals(-1, new Solution2().findMinMoves(new int[]{0,2,0}));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}


	public int findMinMoves(int[] machines) {
		int sum = 0;
		for (int machine : machines) {
			sum += machine;
		}
		int length = machines.length;
		if (sum % length != 0) return -1;
		int avg = sum / length;

		int max = 0;
		int prev = 0;
		for(int i = 0; i < length; i++) {
			int diff = machines[i] - avg + prev;
			prev = diff;
			max = Math.max(Math.max(Math.abs(diff), max), machines[i] - avg);
		}
		return max;
	}
}
