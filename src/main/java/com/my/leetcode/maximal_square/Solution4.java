package com.my.leetcode.maximal_square;

/**
 * Created by alex.bykovsky on 9/20/17.
 */
public class Solution4 {

	public static void main(String[] args) {

		assertEquals(9, new Solution4().maximalSquare(new int[][]{
				{0,1,1,0,1},
				{1,1,0,1,0},
				{0,1,1,1,0},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{0,0,0,0,0}
		}));
		assertEquals(1, new Solution4().maximalSquare(new int[][]{
				{0,0,0,1,0,1,1,1},
				{0,1,1,0,0,1,0,1},
				{1,0,1,1,1,1,0,1},
				{0,0,0,1,0,0,0,0},
				{0,0,1,0,0,0,1,0},
				{1,1,1,0,0,1,1,1},
				{1,0,0,1,1,0,0,1},
				{0,1,0,0,1,1,0,0},
				{1,0,0,1,0,0,0,0}
		}));
		assertEquals(9, new Solution4().maximalSquare(new int[][]{
				{1,0,1,0,0},
				{1,0,1,1,1},
				{1,1,1,1,1},
				{1,0,1,1,1}
		}));
		assertEquals(4, new Solution4().maximalSquare(new int[][]{
				{1,0,1,0,0},
				{1,0,1,1,1},
				{1,1,1,1,1},
				{1,0,0,1,0}
		}));
		assertEquals(4, new Solution4().maximalSquare(new int[][]{
				{1,1,0},
				{1,1,1},
				{1,0,0}
		}));
		assertEquals(9, new Solution4().maximalSquare(new int[][]{
				{0,0,1,0},
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,0},
				{1,1,0,0},
				{1,1,1,1},
				{1,1,1,0}
		}));
		assertEquals(4, new Solution4().maximalSquare(new int[][]{
				{1,1},
				{1,1}
		}));
		assertEquals(4, new Solution4().maximalSquare(new int[][]{
				{0,0,1,0,0},
				{1,0,1,1,1},
				{1,1,1,1,1},
				{1,0,0,1,0}
		}));
		assertEquals(16, new Solution4().maximalSquare(new int[][]{
				{0,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1}
		}));
		assertEquals(4, new Solution4().maximalSquare(new int[][]{
				{0,1},
				{1,1},
				{1,1},
				{1,1}
		}));
		assertEquals(4, new Solution4().maximalSquare(new int[][]{
				{0,1,0,1,0},
				{1,1,1,1,0},
				{1,1,1,0,1},
				{1,1,0,1,1}
		}));
		assertEquals(9, new Solution4().maximalSquare(new int[][]{
				{0,1,0,1,0},
				{1,1,1,1,0},
				{1,1,1,0,1},
				{1,1,1,1,1}
		}));
		assertEquals(25, new Solution4().maximalSquare(new int[][]{
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1}
		}));
		assertEquals(16, new Solution4().maximalSquare(new int[][]{
				{1,1,0,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1}
		}));
		assertEquals(9, new Solution4().maximalSquare(new int[][]{
				{1,1,0,1,1,1,1},
				{1,1,1,1,1,0,0},
				{1,1,1,0,1,0,0},
				{1,1,1,1,0,0,1},
				{1,1,1,0,0,01,1}
		}));
		assertEquals(1, new Solution4().maximalSquare(new int[][]{
				{1}
		}));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int maximalSquare(int[][] matrix) {
		char[][] result = new char[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				result[i][j] = matrix[i][j] == 1? '1': '0';
			}
		}
		return maximalSquare(result);
	}

	public int maximalSquare(char[][] matrix) {

		int rowLength = matrix.length;
		if(rowLength == 0) return 0;
		int colLength = matrix[0].length;
		if(colLength == 0) return 0;

		int[][] solutions = new int[rowLength][colLength];

		int max = 0;
		for(int j = 0; j < colLength; j++) {
			if (matrix[0][j] == '1') {
				solutions[0][j] = 1;
				max = 1;
			}
		}

		int[][] countOnesInCols = new int[rowLength][colLength];
		for(int j = 0; j < colLength; j++) {
			int count = 0;
			for(int i = 0; i < rowLength; i++) {
				if(matrix[i][j] == '1') {
					count++;
				} else {
					count = 0;
				}
				countOnesInCols[i][j] = count;
			}
		}

		for(int i = 1; i < rowLength; i++) {
			int onesInARow = 0;
			for(int j = 0; j < colLength; j++) {
				if(matrix[i][j] == '0') {
					onesInARow = 0;
				} else if(matrix[i][j] == '1') {
					onesInARow++;

					int leftTopI = i - 1;
					int leftTopJ = j - 1;

					int onesInACol = countOnesInCols[i][j];

					int maxSizeHere = Math.max(Math.min(Math.min(onesInARow, i + 1), onesInACol), 1);

					if (leftTopI >= 0 && leftTopJ >= 0 && maxSizeHere > 1) {
						if (solutions[leftTopI][leftTopJ] == maxSizeHere - 1) {
							solutions[i][j] = maxSizeHere;
						} else {
							solutions[i][j] = Math.max(Math.min(solutions[leftTopI][leftTopJ] + 1, maxSizeHere), 1);
						}
						max = Math.max(max, solutions[i][j]);
					} else {
						solutions[i][j] = 1;
						max = Math.max(max, 1);
					}
				}
			}
		}

		return max * max;
	}
}
