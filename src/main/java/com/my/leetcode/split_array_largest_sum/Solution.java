package com.my.leetcode.split_array_largest_sum;

/**
 * Created by alex.bykovsky on 5/12/18.
 */
public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().splitArray(new int[] {1,2,3,4,5,6,7,8,9,10}, 2));
		System.out.println(new Solution().splitArray(new int[] {10,5,13,4,8,4,5,11,14,9,16,10,20,8}, 8));
		System.out.println(new Solution().splitArray(new int[] {1,4,4}, 3));
		System.out.println(new Solution().splitArray(new int[] {7,2,5,10,8}, 2));
		System.out.println(new Solution().splitArray(new int[] {1,2147483647}, 2));
		System.out.println(new Solution().splitArray(new int[] {1,2147483646}, 1));
	}

	public int splitArray(int[] nums, int m) {

		long[][] solutions = new long[m][nums.length];
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			solutions[0][i] = sum;
		}

		for (int k = 1; k < m; k++) {
			sum = 0;
			for (int i = k; i < nums.length; i++) {
				sum += nums[i];
				long min = Integer.MAX_VALUE;
				long localSum = sum;
				for (int j = k; j <= i; j++) {
					min = Math.min(min, Math.max(solutions[k - 1][j - 1], localSum));
					localSum -= nums[j];
				}
				solutions[k][i] = min;
			}
		}

		return (int) solutions[m - 1][nums.length - 1];
	}
}
