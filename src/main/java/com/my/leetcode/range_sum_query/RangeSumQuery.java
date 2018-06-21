package com.my.leetcode.range_sum_query;

/**
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.

 https://leetcode.com/problems/range-sum-query-immutable/description/
 */
public class RangeSumQuery {

	public static void main(String[] params) {
		NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1, 1, 5, 15, 2, 5, 3, 1, 10, 12, 200, 202});
		System.out.println(numArray.sumRange(0, 2));
		System.out.println(numArray.sumRange(2, 5));
		System.out.println(numArray.sumRange(0, 5));
	}

	private static class NumArray {
		private int[] bit;

		public NumArray(int[] nums) {
			bit = new int[nums.length + 1];
			for (int i = 0; i < nums.length; ++i) addValue(bit, nums[i], i + 1);
		}

		public int sumRange(int i, int j) {
			return query(bit, j + 1) - query(bit, i);
		}

		private void addValue(int[] bit, int value, int index) {
			while (index < bit.length) {
				bit[index] += value;
				index += index & (-index);
			}
		}

		private int query(int[] bit, int index) {
			int result = 0;
			while (index > 0) {
				result += bit[index];
				index -= index & (-index);
			}
			return result;
		}
	}
}