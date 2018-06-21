package com.my.code_jam.y2018.round1.round1a.waffle_choppers;

import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/4/18.
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			int R = in.nextInt();
			int C = in.nextInt();
			int H = in.nextInt();
			int V = in.nextInt();

			boolean[][] matrix = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				String line = in.next();
				for (int j = 0; j < C; j++) {
					matrix[i][j] = line.charAt(j) == '@';
				}
			}
			System.out.println("Case #"+t+": "+(solve(matrix, H, V)? "POSSIBLE": "IMPOSSIBLE"));
		}
	}

	private static boolean solve(boolean[][] matrix, int H, int V) {

		int chocCount = 0;
		for (boolean[] booleans : matrix) {
			for (boolean aBoolean : booleans) {
				chocCount += aBoolean? 1: 0;
			}
		}

		if (chocCount == 0) return true;

		int horizChocCount = chocCount / (H + 1);
		int vertChocCount = chocCount / (V + 1);

		int count = chocCount / ((V + 1)*(H + 1));

		if ((double) chocCount / (H + 1) > horizChocCount) {
			return false;
		}

		if ((double) chocCount / (V + 1) > vertChocCount) {
			return false;
		}

		int[] horizCoordinates = checkRows(matrix, horizChocCount, H);
		if (horizCoordinates == null) return false;

		int[] vertCoordinates = checkCols(matrix, vertChocCount, V);
		if (vertCoordinates == null) return false;

		for (int h = 0; h < horizCoordinates.length; h++) {
			for (int v = 0; v < vertCoordinates.length; v++) {
				int c = 0;
				for (int i = (h == 0? 0: horizCoordinates[h - 1] + 1); i <= horizCoordinates[h]; i++) {
					for (int j = (v == 0? 0: vertCoordinates[v - 1] + 1); j <= vertCoordinates[v]; j++) {
						c += matrix[i][j]? 1: 0;
					}
				}
				if (c != count) {
					return false;
				}
			}
		}

		return true;
	}

	private static int[] checkRows(boolean[][] matrix, int horizChocCount, int H) {
		int rowsChocCount = 0;
		int[] result = new int[H];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				rowsChocCount += matrix[i][j]? 1: 0;
			}
			if (rowsChocCount == horizChocCount) {
				rowsChocCount = 0;
				if (H > 0) {
					result[result.length - H] = i;
				}
				H--;
				if (H < -1) {
					return null;
				}
			} else if (rowsChocCount > horizChocCount) {
				return null;
			}
		}
		return result;
	}

	private static int[] checkCols(boolean[][] matrix, int vertChocCount, int V) {
		int colsChocCount = 0;
		int[] result = new int[V];
		for (int j = 0; j < matrix[0].length; j++) {
			for (int i = 0; i < matrix.length; i++) {
				colsChocCount += matrix[i][j]? 1: 0;
			}
			if (colsChocCount == vertChocCount) {
				colsChocCount = 0;
				if (V > 0) {
					result[result.length - V] = j;
				}
				V--;
				if (V < -1) {
					return null;
				}
			} else if (colsChocCount > vertChocCount) {
				return null;
			}
		}
		return result;
	}
}
