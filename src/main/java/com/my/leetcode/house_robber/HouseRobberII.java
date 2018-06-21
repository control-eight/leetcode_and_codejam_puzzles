package com.my.leetcode.house_robber;

/**
 * Created by alex.bykovsky on 9/21/17.
 */
public class HouseRobberII {

	public static void main(String[] args) {
		assertEquals(580, new HouseRobberII().rob(new int[] {10, 30, 40, 50, 20, 70, 500}));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int rob(int[] nums) {
		if(nums.length == 0) return 0;
		if(nums.length == 1) return nums[0];

		int leftRobe = nums[0];
		int leftNothing = 0;
		int rightRobe = nums[nums.length - 1];
		int rightNothing = 0;

		for(int i = 1; i < nums.length - 1; i++) {

			int prevLeftRobe = leftRobe;
			leftRobe = Math.max(leftNothing + nums[i], leftRobe);
			leftNothing = prevLeftRobe;

			int prevRightNothing = rightRobe;
			rightRobe = Math.max(rightNothing + nums[nums.length - i - 1], rightRobe);
			rightNothing = prevRightNothing;
		}

		return Math.max(Math.max(rightRobe, rightNothing), Math.max(leftRobe, leftNothing));
	}
}
