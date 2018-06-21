package com.my.leetcode.washing_machines;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by alex.bykovsky on 9/18/17.
 */
public class SolutionMod2 {

	public static void main(String[] args) {
		assertEquals(3, new SolutionMod2().findMinMoves(new int[]{5,0,5,5,0}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{0,3,0}), new SolutionMod2().findMinMoves(new int[]{0,3,0}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{0,3,0}), new SolutionMod2().findMinMoves(new int[]{0,3,0}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{0,2,4}), new SolutionMod2().findMinMoves(new int[]{0,2,4}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{3,0,3}), new SolutionMod2().findMinMoves(new int[]{3,0,3}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{1,2,3}), new SolutionMod2().findMinMoves(new int[]{1,2,3}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{3,2,1}), new SolutionMod2().findMinMoves(new int[]{3,2,1}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{1,0,5}), new SolutionMod2().findMinMoves(new int[]{1,0,5}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{0,2,0}), new SolutionMod2().findMinMoves(new int[]{0,2,0}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{0,3,2,3}), new SolutionMod2().findMinMoves(new int[]{0,3,2,3}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{0,4,0,4}), new SolutionMod2().findMinMoves(new int[]{0,4,0,4}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{4,0,0,4}), new SolutionMod2().findMinMoves(new int[]{4,0,0,4}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{4,0,4,0}), new SolutionMod2().findMinMoves(new int[]{4,0,4,0}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{0,0,10,0,0}), new SolutionMod2().findMinMoves(new int[]{0,0,10,0,0}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{10,0,0,10,0}), new SolutionMod2().findMinMoves(new int[]{10,0,0,10,0}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{5,0,5,0,5}), new SolutionMod2().findMinMoves(new int[]{5,0,5,0,5}));
		assertEquals(new SolutionMod1().findMinMoves(new int[]{5,0,5,5,0}), new SolutionMod2().findMinMoves(new int[]{5,0,5,5,0}));

		int length = 8;
		int maxNumber = 8;
		int count = 1000;
		Random random = new Random(length * maxNumber);
		for(int i = 0; i < count; i++) {
			int[] arr = new int[length];
			for(int j = 0; j < arr.length; j++) {
				arr[j] = (int) (random.nextDouble() * maxNumber);
			}
			try {
				assertEquals(new SolutionMod1().findMinMoves(arr), new SolutionMod2().findMinMoves(arr));
				//System.out.println(i);
			} catch (Throwable e) {
				System.out.println(e + " " + Arrays.toString(arr));
			}
		}
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
			int diff;
			if(prev < 0 && machines[i] - avg > 0 && machines[i] - avg > Math.abs(prev)) {
				diff = machines[i] - avg - Math.abs(prev);
			} else {
				diff = machines[i] - avg + prev;
			}
			prev = diff;
			max = Math.max(Math.max(Math.abs(diff), max), Math.min(machines[i] - avg, diff));
		}
		return max;
	}
}
