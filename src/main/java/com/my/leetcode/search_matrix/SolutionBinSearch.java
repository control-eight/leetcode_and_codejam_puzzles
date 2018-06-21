package com.my.leetcode.search_matrix;

/**
 * Created by alex.bykovsky on 3/27/18.
 */
public class SolutionBinSearch {

	public static void main(String[] args) {
		/*System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 13, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 21, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 2, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 15, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 25, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 0, 0, 5));*/

		System.out.println(new SolutionBinSearch().searchMatrix(new int[][]{
				{-5}
		}, -5));

		int[][] theMatrix = {
				{1,  4,  7,  11, 15},
				{2,  5,  8,  12, 19},
				{3,  6,  9,  16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}
		};
		System.out.println(new SolutionBinSearch().searchMatrix(theMatrix, 5));
		System.out.println(new SolutionBinSearch().searchMatrix(theMatrix, 13));
		System.out.println(new SolutionBinSearch().searchMatrix(theMatrix, 20));

		System.out.println(new SolutionBinSearch().searchMatrix(new int[][]{
				{-1, 3}
		}, 3));

		System.out.println(new SolutionBinSearch().searchMatrix(new int[][]{
				{1, 4},
				{2, 5}
		}, 5));

		System.out.println(new SolutionBinSearch().searchMatrix(new int[][]{
				{1,   3,  5,  7,  9},
				{2,   4,  6,  8, 10},
				{11, 13, 15, 17, 19},
				{12, 14, 16, 18, 20},
				{21, 22, 23, 24, 25}
		}, 13));

		System.out.println(new SolutionBinSearch().searchMatrix(new int[][]{
				{1, 2,  3,  7,  8},
				{5, 10, 14, 16, 19},
				{8, 10, 18, 19, 23},
				{9, 12, 22, 24, 29}
		}, 14));
	}

	//r1 = 3, r2 = 1; c1 = 0, c2 = 4.

	//iter r1 to c1
	//iter r1 - 1 from c1 + 1 to

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) return false;

		for (int i = 0; i < matrix.length; i++) {
			if (binarySearch(matrix[i], target, 0, matrix[i].length) > - 1) {
				return true;
			}
		}
		return false;
	}

	private int binarySearch(int[] arr, int value, int start, int end) {
		int mid = start + (end - start)/2;
		while (start < end - 1) {
			if (value < arr[mid]) {
				return binarySearch(arr, value, start, mid);
			} else if (value > arr[mid]) {
				return binarySearch(arr, value, mid, end);
			} else {
				return mid;
			}
		}
		return arr[mid] == value? mid: -1;
	}
}
