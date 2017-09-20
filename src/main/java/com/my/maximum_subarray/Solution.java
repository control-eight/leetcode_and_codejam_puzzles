package com.my.maximum_subarray;

/**
 * Created by alex.bykovsky on 9/5/17.
 */
public class Solution {

	//4,-1,2,1

	public static void main(String[] args) {
		System.out.println(new Solution().maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
		System.out.println(new Solution().maxSubArray(new int[] {-1}));
	}

	public int maxSubArray(int[] nums) {

		int lastSum = 0;
		int sum = 0;
		int max = Integer.MIN_VALUE;

		for(int i = 0; i < nums.length; i++) {

			if(nums[i] < 0) {
				if(lastSum == 0) {
					lastSum = sum;
				} else if(sum > lastSum) {
					lastSum = sum;
				}
			}

			sum += nums[i];

			if(sum < 0) {
				sum = 0;
			}

			max = Math.max(max, nums[i]);
		}

		return max < 0? max: Math.max(sum, lastSum);
	}
}
