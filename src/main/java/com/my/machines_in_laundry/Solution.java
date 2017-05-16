package com.my.machines_in_laundry;

/**
 * Created by alex.bykovsky on 4/2/17.
 */
public class Solution {

	public int solve(int[] inArr) {

		int sum = 0;
		for (int i : inArr) {
			sum += i;
		}

		int avg = sum / inArr.length;

		if(sum != avg * inArr.length) {
			return -1;
		}

		int leftAmount = 0;
		int rightAmount = 0;

		int rounds = 0;
		int lastSplit = 0;
		for(int i = 0; i < inArr.length; i++) {

			if(inArr[i] < avg) {
				if(rightAmount > 0) {
					if(rightAmount < avg - inArr[i]) {
						leftAmount += avg - inArr[i] - rightAmount;
					}
					rightAmount -= avg - inArr[i];
					if(rightAmount < 0) {
						rightAmount = 0;
					}
				} else {
					leftAmount += avg - inArr[i];
				}
			} else if(inArr[i] > avg) {

				int diff = inArr[i] - avg;
				int left = diff/2;
				int right = diff - left;

				rightAmount += right;

				rounds += right;

				leftAmount -= left;
				if(left > 0) {
					lastSplit = left;
				}

				if(leftAmount < 0) {
					rounds += -leftAmount;
					rightAmount += -leftAmount;
					leftAmount = 0;
				}
			}
			inArr[i] = avg;

			if(i == inArr.length - 1 && rightAmount > 0) {
				rounds += rightAmount - (rightAmount - lastSplit);
			}
		}
		return rounds;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().solve(new int[] {5,3}));
		System.out.println(new Solution().solve(new int[] {2,3,4}));
		System.out.println(new Solution().solve(new int[] {0,6,0}));
		System.out.println(new Solution().solve(new int[] {0,0,6}));
		System.out.println(new Solution().solve(new int[] {0,2,10,8,2,2}));
		System.out.println(new Solution().solve(new int[] {0,4,10,8,2,2,9}));
		System.out.println(new Solution().solve(new int[] {6,7,7,9,7,7,6}));
		System.out.println(new Solution().solve(new int[] {12,7,7,11,7,7,12}));
		System.out.println(new Solution().solve(new int[] {2,6,6,2}));
		System.out.println(new Solution().solve(new int[] {6,1,1,0,12,5,4,3}));
		System.out.println(new Solution().solve(new int[] {2,2,1,12,2,1,1}));
		System.out.println(new Solution().solve(new int[] {3,1,3,1,3,1}));
	}
}