package com.my.leetcode.valid_sudoku;

/**
 * Created by alex.bykovsky on 3/31/18.
 */
public class Solution {

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

		for (char[] chars : board) {
			boolean[] arr = new boolean[9];
			for (char aChar : chars) {
				if (checkChar(arr, one, aChar)) return false;
			}
		}

		for(int j = 0; j < board[0].length; j++) {
			boolean[] arr = new boolean[9];
			for (int i = 0; i < board.length; i++) {
				if (checkChar(arr, one, board[i][j])) return false;
			}
		}

		for (int i = 0; i < board.length; i += 3) {
			for (int j = 0; j < board[0].length; j += 3) {
				boolean[] arr = new boolean[9];
				for (int ii = i; ii < i + 3; ii++) {
					for (int jj = j; jj < j + 3; jj++) {
						if (checkChar(arr, one, board[ii][jj])) return false;
					}
				}
			}
		}

		return true;
	}

	private boolean checkChar(boolean[] arr, int one, char aChar) {
		if ('.' != aChar) {
			if (arr[aChar - one]) return true;
			arr[aChar - one] = true;
		}
		return false;
	}
}
