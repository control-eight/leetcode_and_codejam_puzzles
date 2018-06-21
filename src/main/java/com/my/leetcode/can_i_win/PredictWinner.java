package com.my.leetcode.can_i_win;

/**
 * Created by alex.bykovsky on 9/13/17.
 */
public class PredictWinner {

	public static void main(String[] args) {
		assertEquals(true, new PredictWinner().PredictTheWinner(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}));
		assertEquals(false, new PredictWinner().PredictTheWinner(new int[] {1, 5, 2}));
		assertEquals(true, new PredictWinner().PredictTheWinner(new int[] {1, 5, 233, 7}));
		assertEquals(false, new PredictWinner().PredictTheWinner(new int[] {2, 4, 55, 6, 8}));
	}

	private static void assertEquals(boolean expected, boolean actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public boolean PredictTheWinner(int[] nums) {

		if(nums.length <= 2) return true;

		int sum = 0;
		for (int num : nums) {
			sum += num;
		}

		int first = predictTheWinner(nums, 0, nums.length, sum);
		int secondOne = predictTheWinner(nums, 1, nums.length, sum - nums[1]);
		int secondTwo = predictTheWinner(nums, 0, nums.length - 1, sum - nums[nums.length - 1]);

		return first > Math.min(secondOne, secondTwo);
	}

	private Integer[] cache = new Integer[2020];

	private int predictTheWinner(int[] nums, int lo, int hi, int sum) {

		if((lo >= hi) || (lo < 0 || hi > nums.length)) return 0;

		if(hi - lo == 1) {
			return nums[lo];
		}

		int key = lo + hi * 100;
		Integer solution = cache[key];
		if(solution != null) {
			return solution;
		}

		int oneSecond = predictTheWinner(nums, lo + 1, hi, sum - nums[lo]);
		int twoSecond = predictTheWinner(nums, lo, hi - 1, sum - nums[hi - 1]);
		
		int oneFirst = sum - oneSecond;
		int twoFirst = sum - twoSecond;

		boolean firstBigger = (oneFirst - oneSecond) > (twoFirst - twoSecond);

		if (firstBigger) {
			cache[key] = oneFirst;
			return oneFirst;
		} else {
			cache[key] = twoFirst;
			return twoFirst;
		}
	}
}
