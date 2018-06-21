package com.my.leetcode.maximal_square;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by alex.bykovsky on 9/20/17.
 */
public class Solution2 {

	public static void main(String[] args) {
		assertEquals(4, new Solution2().maximalSquare(new int[][]{
				{1,1,0},
				{1,1,1},
				{1,0,0}
		}));
		assertEquals(9, new Solution2().maximalSquare(new int[][]{
				{0,0,1,0},
				{1,1,1,1},
				{1,1,1,1},
				{1,1,1,0},
				{1,1,0,0},
				{1,1,1,1},
				{1,1,1,0}
		}));
		assertEquals(4, new Solution2().maximalSquare(new int[][]{
				{1,0,1,0,0},
				{1,0,1,1,1},
				{1,1,1,1,1},
				{1,0,0,1,0}
		}));
		assertEquals(9, new Solution2().maximalSquare(new int[][]{
				{1,0,1,0,0},
				{1,0,1,1,1},
				{1,1,1,1,1},
				{1,0,1,1,1}
		}));
		assertEquals(4, new Solution2().maximalSquare(new int[][]{
				{1,1},
				{1,1}
		}));
		assertEquals(4, new Solution2().maximalSquare(new int[][]{
				{0,0,1,0,0},
				{1,0,1,1,1},
				{1,1,1,1,1},
				{1,0,0,1,0}
		}));
		assertEquals(16, new Solution2().maximalSquare(new int[][]{
				{0,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1}
		}));
		assertEquals(4, new Solution2().maximalSquare(new int[][]{
				{0,1},
				{1,1},
				{1,1},
				{1,1}
		}));
		assertEquals(4, new Solution2().maximalSquare(new int[][]{
				{0,1,0,1,0},
				{1,1,1,1,0},
				{1,1,1,0,1},
				{1,1,0,1,1}
		}));
		assertEquals(9, new Solution2().maximalSquare(new int[][]{
				{0,1,0,1,0},
				{1,1,1,1,0},
				{1,1,1,0,1},
				{1,1,1,1,1}
		}));
		assertEquals(25, new Solution2().maximalSquare(new int[][]{
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1}
		}));
		assertEquals(16, new Solution2().maximalSquare(new int[][]{
				{1,1,0,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1}
		}));
		assertEquals(9, new Solution2().maximalSquare(new int[][]{
				{1,1,0,1,1,1,1},
				{1,1,1,1,1,0,0},
				{1,1,1,0,1,0,0},
				{1,1,1,1,0,0,1},
				{1,1,1,0,0,01,1}
		}));
		assertEquals(1, new Solution2().maximalSquare(new int[][]{
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

		Deque<int[]> topLeftSquares = new LinkedList<>();
		int size = 1;
		for(int i = 0; i < rowLength; i++) {
			for(int j = 0; j < colLength; j++) {
				if(matrix[i][j] == '1') {
					topLeftSquares.offer(new int[]{i,j});
				}
			}
		}

		int[][] countOnesInRows = new int[rowLength][colLength];
		for(int i = 0; i < rowLength; i++) {
			int count = 0;
			for(int j = 0; j < colLength; j++) {
				if(matrix[i][j] == '1') {
					count++;
				}
				countOnesInRows[i][j] = count;
			}
		}

		int[][] countOnesInCols = new int[rowLength][colLength];
		for(int j = 0; j < colLength; j++) {
			int count = 0;
			for(int i = 0; i < rowLength; i++) {
				if(matrix[i][j] == '1') {
					count++;
				}
				countOnesInCols[i][j] = count;
			}
		}

		while(!topLeftSquares.isEmpty() && size <= rowLength && size <= colLength) {
			size++;

			Iterator<int[]> iter = topLeftSquares.iterator();
			outer: while (iter.hasNext()) {

				int[] topLeftSquare = iter.next();

				int newI = topLeftSquare[0] + size - 1;
				int newJ = topLeftSquare[1] + size - 1;

				if(newI < rowLength && newJ < colLength) {
					if(countOnesInRows[newI][newJ] - countOnesInRows[newI][topLeftSquare[1]] != size - 1
							|| matrix[newI][newJ] != '1' || matrix[newI][topLeftSquare[1]] != '1') {
						iter.remove();
						continue outer;
					}

					if(countOnesInCols[newI][newJ] - countOnesInCols[topLeftSquare[0]][newJ] != size - 1
							|| matrix[newI][newJ] != '1' || matrix[topLeftSquare[0]][newJ] != '1') {
						iter.remove();
						continue outer;
					}
				} else {
					iter.remove();
				}
			}
		}

		return (size - 1)*(size - 1);
	}
}
