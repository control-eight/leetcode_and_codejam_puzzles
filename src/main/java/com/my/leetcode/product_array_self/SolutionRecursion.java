package com.my.leetcode.product_array_self;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 7/10/17.
 */
public class SolutionRecursion {

	public static void main(String[] args) {
		int[] result = new SolutionRecursion().productExceptSelf(new int[] {1,2,3,4});
		System.out.println(Arrays.toString(result));
		//[24,12,8,6]
	}

	public int[] productExceptSelf(int[] nums) {
		int[] output = new int[nums.length];
		productExceptSelf(nums, output, 0, 1);
		return output;
	}

	private int productExceptSelf(int[] nums, int[] output, int index, int prevPrevCum) {

		if(index >= nums.length) {
			return 1;
		}

		int prevCum = prevPrevCum;
		if(index > 0) {
			prevCum *= nums[index - 1];
		}

		int nextCum = productExceptSelf(nums, output, index + 1, prevCum);

		output[index] = prevCum *  nextCum;

		return nums[index] * nextCum;
	}
}
