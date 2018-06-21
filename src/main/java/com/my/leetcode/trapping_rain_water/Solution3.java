package com.my.leetcode.trapping_rain_water;

/**
 * Created by alex.bykovsky on 4/1/18.
 */
public class Solution3 {

	public static void main(String[] args) {
		System.out.println(new Solution3().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
		//6
		System.out.println(new Solution3().trap(new int[] {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3}));
		//83
	}

	public int trap(int[] height) {

		int left = 0;
		int right = height.length - 1;

		int nextLeft = left;
		int nextRight = right;

		int result = 0;
		while (nextLeft < nextRight) {
			if (height[nextLeft] < height[nextRight]) {
				while (nextLeft < nextRight && height[nextLeft] < height[nextRight]) {
					nextLeft++;
					left = checkMax(height, left, nextLeft);
					result += pos(height[left] - height[nextLeft]);
				}
			} else if (height[nextRight] < height[nextLeft]) {
				while (nextLeft < nextRight && height[nextRight] < height[nextLeft]) {
					nextRight--;
					right = checkMax(height, right, nextRight);
					result += pos(height[right] - height[nextRight]);
				}
			} else {
				while (nextLeft < nextRight && height[nextLeft] <= height[nextRight]) {
					nextLeft++;
					left = checkMax(height, left, nextLeft);
					result += pos(height[left] - height[nextLeft]);
				}
			}
			left = nextLeft;
			right = nextRight;
		}
		return result;
	}

	private int checkMax(int[] height, int left, int nextLeft) {
		if (height[nextLeft] > height[left]) {
			return nextLeft;
		}
		return left;
	}

	private int pos(int res) {
		return res > 0? res: 0;
	}
}
