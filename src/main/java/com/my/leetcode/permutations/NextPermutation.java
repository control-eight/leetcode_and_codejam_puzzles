package com.my.leetcode.permutations;

import java.util.Arrays;

/**
 * Created by alex.bykovsky on 9/28/17.
 */
public class NextPermutation {

	public static void main(String[] args) {
		run(new int[]{1,2,3});
		run(new int[]{2,3,1});
		run(new int[]{3,2,1});
		run(new int[]{1,1,5});
		run(new int[]{1,1,10,4});
		run(new int[]{1,3,2});
		run(new int[]{1,1,4,5,8});
		run(new int[]{1,1,8,5,4});
		run(new int[]{1,2,4,3,2,1});
	}

	private static void run(int[] arr) {
		new NextPermutation().nextPermutation(arr);
		System.out.println(Arrays.toString(arr));
	}

	public void nextPermutation(int[] nums) {

		if(nums.length <= 1) return;

		int found = -1;
		for(int i = nums.length - 1; i >= 1; i--) {
			if(nums[i] > nums[i - 1]) {
				found = i - 1;
				break;
			}
		}

		if(found != -1) {
			int bigger = -1;
			for (int i = found + 1; i < nums.length; i++) {
				if (nums[i] > nums[found]) {
					bigger = i;
				} else {
					break;
				}
			}

			int temp = nums[found];
			nums[found] = nums[bigger];
			nums[bigger] = temp;
		}

		for(int i = found + 1; i < found + 1 + (nums.length - found - 1)/2; i++) {
			int temp = nums[i];
			nums[i] = nums[nums.length - i + found];
			nums[nums.length - i + found] = temp;
		}
	}
}
