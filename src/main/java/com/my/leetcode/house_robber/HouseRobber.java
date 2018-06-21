package com.my.leetcode.house_robber;

/**
 * Created by alex.bykovsky on 9/21/17.
 */
public class HouseRobber {

	public static void main(String[] args) {

	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int rob(int[] nums) {

		if(nums.length == 0) return 0;
		if(nums.length == 1) return nums[0];


		int robe = nums[0];
		int doNotRobe = 0;

		for(int i = 1; i < nums.length; i++) {
			int prevRobe = robe;
			robe = Math.max(doNotRobe + nums[i], robe);
			doNotRobe = prevRobe;
		}

		return Math.max(robe, doNotRobe);
	}
}
