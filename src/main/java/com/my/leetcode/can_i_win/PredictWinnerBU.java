package com.my.leetcode.can_i_win;

/**
 * Created by alex.bykovsky on 9/13/17.
 */
public class PredictWinnerBU {

	public static void main(String[] args) {
		assertEquals(false, new PredictWinnerBU().PredictTheWinner(new int[] {1, 3, 1}));
		assertEquals(true, new PredictWinnerBU().PredictTheWinner(new int[] {1, 5, 233, 7}));
		assertEquals(false, new PredictWinnerBU().PredictTheWinner(new int[] {1, 5, 2}));
		assertEquals(false, new PredictWinnerBU().PredictTheWinner(new int[] {2, 4, 55, 6, 8}));
		assertEquals(true, new PredictWinnerBU().PredictTheWinner(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}));
	}

	private static void assertEquals(boolean expected, boolean actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public boolean PredictTheWinner(int[] nums) {

		if(nums.length <= 2) return true;

		int[] solutions = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			solutions[i] = nums[i];
		}

		int sum = nums[0];
		for(int l = 2; l <= nums.length; l++) {
			int localSum = sum;
			for(int i = 0; i < nums.length - l + 1; i++) {
				localSum += nums[i + l - 1];
				solutions[i] = Math.max(localSum - solutions[i + 1], localSum - solutions[i]);
				localSum -= nums[i];
			}
			sum += nums[l - 1];
		}

		return solutions[0] >= solutions[1];
	}

	/*public boolean PredictTheWinner(int[] nums) {

		if(nums.length <= 2) return true;

		int[] solutions = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			solutions[i] = nums[i];
		}

		for(int l = 2; l <= nums.length; l++) {
			for(int i = 0; i < nums.length - l + 1; i++) {
				solutions[i] = Math.max(nums[i] - solutions[i + 1], nums[i + l - 1] - solutions[i]);
			}
		}

		return solutions[0] >= 0;
	}*/
}
