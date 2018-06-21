package com.my.leetcode.search_matrix;

/**
 * Created by alex.bykovsky on 3/27/18.
 */
public class Solution {

	public static void main(String[] args) {
		/*System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 13, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 21, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 2, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 15, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 25, 0, 5));
		System.out.println(new Solution().binarySearch(new int[] {1,2,15,20,24}, 0, 0, 5));*/

		int[][] theMatrix = {
				{1,  4,  7,  11, 15},
				{2,  5,  8,  12, 19},
				{3,  6,  9,  16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}
		};
		System.out.println(new Solution().searchMatrix(theMatrix, 5));
		System.out.println(new Solution().searchMatrix(theMatrix, 13));
		System.out.println(new Solution().searchMatrix(theMatrix, 20));

		System.out.println(new Solution().searchMatrix(new int[][]{
				{-1, 3}
		}, 3));

		System.out.println(new Solution().searchMatrix(new int[][]{
				{1, 4},
				{2, 5}
		}, 5));

		System.out.println(new Solution().searchMatrix(new int[][]{
				{1,   3,  5,  7,  9},
				{2,   4,  6,  8, 10},
				{11, 13, 15, 17, 19},
				{12, 14, 16, 18, 20},
				{21, 22, 23, 24, 25}
		}, 13));

		System.out.println(new Solution().searchMatrix(new int[][]{
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

		int rowIndex = binarySearch(getCol(matrix, 0), target, 0, matrix.length);
		int colIndex = binarySearch(matrix[0], target, 0, matrix[0].length);

		System.out.println(target + " = " + rowIndex + " " + colIndex);

		if (rowIndex == -1 && colIndex == -1) {
			rowIndex = matrix.length - 1;
			colIndex = matrix[0].length -1;
		} else if (rowIndex == -1) {
			rowIndex = binarySearch(getCol(matrix, colIndex), target, 0, matrix.length);
		} else if (colIndex == -1) {
			colIndex = binarySearch(matrix[rowIndex], target, 0, matrix[rowIndex].length);
		}

		if (rowIndex == -1 || colIndex == -1) return false;

		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[rowIndex][i] == target) return true;
		}
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][colIndex] == target) return true;
		}

		if (rowIndex > 0) {
			for (int i = 0; i < matrix[0].length; i++) {
				if (matrix[rowIndex - 1][i] == target) return true;
			}
		}
		if (colIndex > 0) {
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i][colIndex - 1] == target) return true;
			}
		}

		return false;
	}

	private int[] getCol(int[][] matrix, int col) {
		int[] res = new int[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			res[i] = matrix[i][col];
		}
		return res;
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

		if (arr[mid] > value) {
			return mid  -(1);
		} else if (mid < arr.length - 1 && arr[mid + 1] > value) {
			return mid + 1 -(1);
		} else if (arr[mid] == value) {
			return mid;
		} else {
			return -1;
			//return mid;
		}
	}
}
