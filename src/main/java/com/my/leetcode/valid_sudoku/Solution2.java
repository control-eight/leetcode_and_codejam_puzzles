package com.my.leetcode.valid_sudoku;

/**
 * Created by alex.bykovsky on 3/31/18.
 */
public class Solution2 {

	public static void main(String[] args) {
		char[][] m = new char[][]{
			{'.','8','7','6','5','4','3','2','1'},
			{'2','.','.','.','.','.','.','.','.'},
			{'3','.','.','.','.','.','.','.','.'},
			{'4','.','.','.','.','.','.','.','.'},
			{'5','.','.','.','.','.','.','.','.'},
			{'6','.','.','.','.','.','.','.','.'},
			{'7','.','.','.','.','.','.','.','.'},
			{'8','.','.','.','.','.','.','.','.'},
			{'9','.','.','.','.','.','.','.','.'}
		};

		int one = '1';
		System.out.println('1' - one);
		System.out.println('9' - one);
	}

	public boolean isValidSudoku(char[][] board) {
		if (board.length == 0) return true;

		int one = '1';

		boolean[][] colMatrix = new boolean[board[0].length][9];
		boolean[][] boxMatrix = new boolean[(board.length * board[0].length) / 9][9];

		for (int i = 0; i < board.length; i++) {
			boolean[] rowArr = new boolean[9];
			for (int j = 0; j < board[0].length; j++) {

				int aChar = board[i][j];

				if (checkChar(rowArr, one, aChar)) return false;
				if (checkChar(colMatrix[j], one, aChar)) return false;
				if (checkChar(boxMatrix[(i / 3) * 3 + j / 3], one, aChar)) return false;
			}
		}

		return true;
	}

	private boolean checkChar(boolean[] arr, int one, int aChar) {
		if ('.' != aChar) {
			if (arr[aChar - one]) return true;
			arr[aChar - one] = true;
		}
		return false;
	}
}
