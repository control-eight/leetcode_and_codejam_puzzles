package com.my.leetcode.search_matrix;

/**
 * Created by alex.bykovsky on 3/27/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		int[][] theMatrix = {
				{1,  4,  7,  11, 15},
				{2,  5,  8,  12, 19},
				{3,  6,  9,  16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}
		};

		/*System.out.println(new Solution().binarySearchRow(theMatrix, 12, 1, 0, 5).found);
		System.out.println(new Solution().binarySearchRow(theMatrix, 12, 0, 0, 5).found);
		System.out.println(new Solution().binarySearchCol(theMatrix, 12, 3, 0, 5).found);
		System.out.println(new Solution().binarySearchCol(theMatrix, 12, 2, 0, 5).found);*/

		/*System.out.println(new Solution().binarySearchDiag(theMatrix, 1, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 5, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 9, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 17, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 30, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 12, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 40, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 4, 0, 5, 0, 0).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 18, 0, 5, 0, 0).found);

		System.out.println(new Solution().binarySearchDiag(theMatrix, 7, 0, 2, 0, 2).found);
		System.out.println(new Solution().binarySearchDiag(theMatrix, 12, 0, 2, 0, 2).found);*/

		System.out.println("----------------------------------");

		theMatrix = new int[][] {
				{1,  4,  7,  11, 15},
				{2,  5,  8,  12, 19},
				{3,  6,  9,  16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}
		};

		System.out.println(new Solution2().searchMatrix(theMatrix, 13));
		System.out.println(new Solution2().searchMatrix(theMatrix, 5));
		System.out.println(new Solution2().searchMatrix(theMatrix, 20));

		System.out.println(new Solution2().searchMatrix(new int[][]{
				{-1, 3}
		}, 3));

		System.out.println(new Solution2().searchMatrix(new int[][]{
				{1, 4},
				{2, 5}
		}, 5));

		System.out.println(new Solution2().searchMatrix(new int[][]{
				{1,   3,  5,  7,  9},
				{2,   4,  6,  8, 10},
				{11, 13, 15, 17, 19},
				{12, 14, 16, 18, 20},
				{21, 22, 23, 24, 25}
		}, 13));

		System.out.println(new Solution2().searchMatrix(new int[][]{
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

		return searchMatrix(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, target);
	}

	private boolean searchMatrix(int[][] matrix, int i1, int j1, int i2, int j2, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) return false;

		if ((i2 - i1) == 0 || (j2 - j1) == 0) {
			if (i1 == i2) {
				return binarySearchRow(matrix, target, i1, j1, j2).found;
			} else {
				return binarySearchCol(matrix, target, i1, j1, j2).found;
			}
		}

		int end;
		if (i2 - i1 + 1 > j2 - j1 + 1) {
			end = (j2 - j1) + i1 + 1;
		} else {
			end = (i2 - i1) + i1 + 1;
		}

		Pair res = binarySearchDiag(matrix, target, i1, end, 0, 0);
		if (res.found) return true;

		if (res.value > i1 && res.value > j1) {
			//left bottom part
			if (searchMatrix(matrix, i1 + res.value + 1, j1, i2, j1 + res.value, target)) {
				return true;
			}
			//right top part
			if (searchMatrix(matrix, i1, j1 + res.value + 1, i1 + res.value, j2, target)) {
				return true;
			}
		}

		if (i2 - i1 + 1 > j2 - j1 + 1) {
			int delta = (j2 - j1) + 1;
			if (searchMatrix(matrix, i1 + delta, j1, i2 + delta, j2, target)) {
				return true;
			}
		} else {
			int delta = (i2 - i1) + 1;
			if (searchMatrix(matrix, i1, j1 + delta, i2, j2 + delta, target)) {
				return true;
			}
		}
		return false;
	}

	private Pair binarySearchDiag(int[][] arr, int value, int start, int end, int iOffset, int jOffset) {
		int mid = start + (end - start)/2;
		while (start < end - 1) {
			if (value < arr[mid + iOffset][mid + jOffset]) {
				return binarySearchDiag(arr, value, start, mid, iOffset, jOffset);
			} else if (value > arr[mid + iOffset][mid + jOffset]) {
				return binarySearchDiag(arr, value, mid, end, iOffset, jOffset);
			} else {
				return new Pair(mid, true);
			}
		}

		if (arr[mid + iOffset][mid + jOffset] == value) {
			return new Pair(mid, true);
		} else if (arr[mid + iOffset][mid + jOffset] > value) {
			return new Pair(mid  -(1), false);
		} else if (mid < arr.length - 1 && arr[mid + 1 +  + iOffset][mid + 1 + jOffset] > value) {
			return new Pair(mid + 1 -(1), false);
		}  else {
			return new Pair(-1, false);
		}
	}

	private Pair binarySearchRow(int[][] arr, int value, int row, int start, int end) {
		int mid = start + (end - start)/2;
		while (start < end - 1) {
			if (value < arr[row][mid]) {
				return binarySearchRow(arr, value, row, start, mid);
			} else if (value > arr[row][mid]) {
				return binarySearchRow(arr, value, row, mid, end);
			} else {
				return new Pair(mid, true);
			}
		}

		if (arr[row][mid] == value) {
			return new Pair(mid, true);
		} else if (arr[row][mid] > value) {
			return new Pair(mid  -(1), false);
		} else if (mid < arr.length - 1 && arr[row][mid + 1] > value) {
			return new Pair(mid + 1 -(1), false);
		} else {
			return new Pair(-1, false);
		}
	}

	private Pair binarySearchCol(int[][] arr, int value, int col, int start, int end) {
		int mid = start + (end - start) / 2;
		while (start < end - 1) {
			if (value < arr[mid][col]) {
				return binarySearchCol(arr, value, col, start, mid);
			} else if (value > arr[mid][col]) {
				return binarySearchCol(arr, value, col, mid, end);
			} else {
				return new Pair(mid, true);
			}
		}

		if (arr[mid][col] == value) {
			return new Pair(mid, true);
		} else if (arr[mid][col] > value) {
			return new Pair(mid  -(1), false);
		} else if (mid < arr.length - 1 && arr[mid + 1][col] > value) {
			return new Pair(mid + 1 -(1), false);
		} else {
			return new Pair(-1, false);
		}
	}

	private class Pair {
		private int value;
		private boolean found;
		public Pair(int value, boolean found) {
			this.value = value;
			this.found = found;
		}
	}
}
