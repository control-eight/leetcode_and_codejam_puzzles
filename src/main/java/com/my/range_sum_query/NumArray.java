package com.my.range_sum_query;

/**
 * Created by alex.bykovsky on 8/1/17.
 */
public class NumArray {

	public static void main(String[] params) {
		NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});

		//sumRange(0, 2) -> 1
		System.out.println(numArray.sumRange(0, 2));
		//sumRange(2, 5) -> -1
		System.out.println(numArray.sumRange(2, 5));
		//sumRange(0, 5) -> -3
		System.out.println(numArray.sumRange(0, 5));
	}

	private long[] sums;

	/**
	 * Your NumArray object will be instantiated and called as such:
	 * NumArray obj = new NumArray(nums);
	 * int param_1 = obj.sumRange(i,j);
	 */
	public NumArray(int[] nums) {

		if(nums.length == 0) return;

		sums = new long[nums.length];
		sums[0] = nums[0];

		for(int i = 1; i < nums.length; i++) {
			sums[i] = sums[i - 1] + nums[i];
		}
	}

	public int sumRange(int i, int j) {
		if(sums == null) return -1;

		return (int) (sums[j] - (i == 0? 0: sums[i - 1]));
	}
}
