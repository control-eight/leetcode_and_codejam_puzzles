package com.my.leetcode.product_array_self;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 7/10/17.
 */
public class SolutionWithMemory {

	public static void main(String[] args) {
		int[] result = new SolutionWithMemory().productExceptSelf(new int[] {1,2,3,4});
		//int[] result = new Solution().productExceptSelf(new int[] {2,3,4,5});
		System.out.println(Arrays.toString(result));
		//[24,12,8,6]
	}

	//1 2 6 24
	//1 60 30 5

	public int[] productExceptSelf(int[] nums) {
		int[] output = new int[nums.length];

		int[] prevCum = new int[nums.length];
		Arrays.fill(prevCum, 1);

		for(int i = 0; i < nums.length; i++) {
			if(i > 0) {
				prevCum[i] = prevCum[i - 1] * nums[i - 1];
			}
		}

		int[] nextCum = new int[nums.length];
		Arrays.fill(nextCum, 1);

		for(int i = nums.length - 1; i >= 0; i--) {
			if(i > 0) {
				nextCum[i - 1] = nextCum[i] * nums[i];
			}
		}

		for(int i = 0; i < nums.length; i++) {
			output[i] = prevCum[i] * nextCum[i];
		}

		return output;
	}
}
