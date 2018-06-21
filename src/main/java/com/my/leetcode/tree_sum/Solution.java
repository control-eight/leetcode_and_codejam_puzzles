package com.my.leetcode.tree_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex.bykovsky on 7/6/17.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().threeSum(new int[]{-1,0,1,2,-1,-4}));
		System.out.println(new Solution().threeSum(new int[]{0,0,0}));
		System.out.println(new Solution().threeSum(new int[]{3,0,-2,-1,1,2}));
		System.out.println(new Solution().threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
		System.out.println(new Solution().threeSum(new int[]{0,0}));
		System.out.println(new Solution().threeSum(new int[]{-6,3}));
	}

	public List<List<Integer>> threeSum(int[] nums) {

		Arrays.sort(nums);

		List<List<Integer>> result = new ArrayList<>();

		for(int i = 0; i < nums.length - 2; i++) {

			if((i==0) || (i > 0 && nums[i] != nums[i-1])) {

				int num = nums[i];

				int lo = i + 1;
				int hi = nums.length - 1;

				while (lo < hi) {

					int currentLoValue = nums[lo];
					int currentHiValue = nums[hi];

					int sum = num + currentLoValue;

					if (currentHiValue + sum < 0) {
						lo++;
					} else if (currentHiValue + sum > 0) {
						hi--;
					} else if (currentHiValue + sum == 0) {
						result.add(Arrays.asList(num, currentLoValue, currentHiValue));

						lo++;
						hi--;
						while(lo < hi && nums[lo] == currentLoValue && nums[hi] == currentHiValue) {
							lo++;
							hi--;
						}
					}
				}
			}
		}

		return result;

	}
}
