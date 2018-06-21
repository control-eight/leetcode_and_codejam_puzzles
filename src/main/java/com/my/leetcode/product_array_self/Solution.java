package com.my.leetcode.product_array_self;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 7/10/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution().productExceptSelf(new int[] {1,2,3,4})));
		//24 12 8 6
		System.out.println(Arrays.toString(new Solution().productExceptSelf(new int[] {2,3,4,5})));
		//60 40 30 24
	}

	//1 2 6 24
	//60 20 5 1

	public int[] productExceptSelf(int[] nums) {
		int[] output = new int[nums.length];
		Arrays.fill(output, 1);

		for(int i = 0; i < nums.length; i++) {
			if(i > 0) output[i] = output[i - 1] * nums[i - 1];
		}

		int next = 1;
		for(int i = nums.length - 1; i >= 0; i--) {
			output[i] = next * output[i];
			next *= nums[i];
		}

		return output;
	}
}
