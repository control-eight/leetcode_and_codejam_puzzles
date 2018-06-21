package com.my.leetcode.kth_largest_element_array;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 4/1/18.
 */
public class Solution {

	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[k];
	}
}
