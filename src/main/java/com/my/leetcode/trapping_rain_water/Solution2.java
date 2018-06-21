package com.my.leetcode.trapping_rain_water;

/**
 * Created by alex.bykovsky on 3/26/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		System.out.println(new Solution2().trap(new int[] {4,0,2,1,5}));
		//9
		System.out.println(new Solution2().trap(new int[] {4,2,0,3,2,5}));
		//9
		System.out.println(new Solution2().trap(new int[] {2,1,0,1,2}));
		//4
		System.out.println(new Solution2().trap(new int[] {1,0,2}));
		//1
		System.out.println(new Solution2().trap(new int[] {0,1,0,2}));
		//1
		System.out.println(new Solution2().trap(new int[] {2,1,0,1,3}));
		//4
		System.out.println(new Solution2().trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
		//6
		System.out.println(new Solution2().trap(new int[] {4,2,0,5}));
		//6
		System.out.println(new Solution2().trap(new int[] {0,5,0,5,0,5}));
		//10
		System.out.println(new Solution2().trap(new int[] {5,4,3,2,1,5}));
		//10
		System.out.println(new Solution2().trap(new int[] {2,0,10}));
		//2
	}

	public int trap(int[] heights) {
		int result = 0;
		Container prev = null;
		for (int i = 0; i < heights.length - 1; i++) {
			Container current = new Container(i, i, heights[i]);
			result = processCurrent(heights, result, prev, current);
			prev = current;
		}
		return result;
	}

	private int processCurrent(int[] heights, int result, Container prev, Container current) {
		if (prev == null) return result;

		if (prev.height >= current.height && heights[current.indexRight + 1] >= current.height) {
			int minHeight = Math.min(heights[current.indexRight + 1], prev.height);
			result += (minHeight - current.height) * (current.indexRight - (current.indexLeft - 1));
			prev.indexRight++;

			current.height = minHeight;

			if (prev.indexLeft - 1 >= 0) {
				result = processCurrent(heights, result,
						new Container(prev.indexLeft - 1, prev.indexLeft - 1, heights[prev.indexLeft - 1]), prev);

				current.indexLeft = prev.indexLeft;
				current.indexRight = prev.indexRight;
				current.height = prev.height;
			}
		}
		return result;
	}

	/*private int processCurrent(int[] heights, int result, Container prev, Container current) {
		if (prev == null && current.indexLeft == 0) return result;
		if (prev == null && current.indexRight >= heights.length - 1) return result;

		int prevHeight = prev != null? prev.height: heights[current.indexLeft - 1];

		if (prevHeight >= current.height && heights[current.indexRight + 1] >= current.height) {

			int minHeight = Math.min(heights[current.indexRight + 1], prevHeight);
			result += (minHeight - current.height) * (current.indexRight - (current.indexLeft - 1));

			if (prev != null) {
				prev.indexRight++;
			} else {
				if (heights[current.indexRight + 1] > prevHeight) {
					current.indexLeft--;
				} else {
					current.indexRight++;
				}
			}

			current.height = minHeight;
			result = processCurrent(heights, result,
					new Container(prev.indexLeft - 1, prev.indexLeft - 1, heights[prev.indexLeft - 1]), prev);
		}
		return result;
	}*/

	private static class Container {
		int indexLeft;
		int indexRight;
		int height;

		Container(int indexLeft, int indexRight, int height) {
			this.indexLeft = indexLeft;
			this.indexRight = indexRight;
			this.height = height;
		}
	}
}
