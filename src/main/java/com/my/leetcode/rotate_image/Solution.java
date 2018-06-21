package com.my.leetcode.rotate_image;

/**
 * Created by alex.bykovsky on 3/30/18.
 */
public class Solution {

	public static void main(String[] args) {
		int[][] matrix1 = new int[][] {
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		new Solution().rotate(matrix1);
		printMatrix(matrix1);
		/*[
		  [7,4,1],
		  [8,5,2],
		  [9,6,3]
		]*/

		int[][] matrix2 = new int[][] {
				{ 5, 1, 9,11},
				{ 2, 4, 8,10},
				{13, 3, 6, 7},
				{15,14,12,16}
		};
		new Solution().rotate(matrix2);
		printMatrix(matrix2);
		/*[
		  [15,13, 2, 5],
		  [14, 3, 4, 1],
		  [12, 6, 8, 9],
		  [16, 7,10,11]
		]*/

		int[][] matrix3 = new int[][] {
				{ 1, 2},
				{ 3, 4}
		};
		new Solution().rotate(matrix3);
		printMatrix(matrix3);
		/*[
		  [ 3, 1],
		  [ 4, 2]
		]*/
	}

	public void rotate(int[][] matrix) {
		int n = matrix.length;
		int i = 0;
		int start = 0;
		int end = n;
		while (i < n / 2) {
			for (int j = start; j < end - 1; j++) {
				int tmp1 = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = matrix[i][j];

				int ii = j;
				int jj = n - 1 - i;

				int tmp2 = matrix[jj][n - 1 - ii];
				matrix[jj][n - 1 - ii] = tmp1;

				int iii = jj;
				int jjj = n - 1 - ii;

				int tmp3 = matrix[jjj][n - 1 - iii];
				matrix[jjj][n - 1 - iii] = tmp2;

				matrix[i][j] = tmp3;
			}
			start++;
			end--;
			i++;
		}
	}

	private static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------");
	}
}
