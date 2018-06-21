package com.my.leetcode.out_of_boundary_paths;

/**
 * Created by alex.bykovsky on 9/25/17.
 */
public class Solution3 {

	public static void main(String[] args) {
		assertEquals(14, new Solution3().findPaths(2, 2, 3, 0, 0));
		assertEquals(72, new Solution3().findPaths(1, 3, 7, 0, 1));
		assertEquals(109, new Solution3().findPaths(3, 3, 5, 0, 1));
		assertEquals(4, new Solution3().findPaths(1, 1, 1, 0, 0));
		assertEquals(6, new Solution3().findPaths(2, 2, 2, 0, 0));
		assertEquals(390153306, new Solution3().findPaths(36, 5, 50, 15, 3));
	}

	private static void assertEquals(long expected, long actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	private static final int INT = ((int) Math.pow(10, 9) + 7);

	public long findPaths(int m, int n, int N, int i, int j) {

		int[][] solutions = new int[m][n];

		findPaths(m, n, N, i, j, solutions);

		int count = 0;

		for(int ii = 0; ii < m; ii++) {
			for(int jj = 0; jj < n; jj++) {
				if(ii == 0) {
					count += solutions[ii][jj];
				}
				if(jj == 0) {
					count += solutions[ii][jj];
				}
				if(ii == m - 1) {
					count += solutions[ii][jj];
				}
				if(jj == n - 1) {
					count += solutions[ii][jj];
				}
			}
		}

		return count % INT;
	}

	private void findPaths(int m, int n, int N, int i, int j, int[][] solutions) {

		if(N == 0) return;
		if(i < 0 || i >= m) return;
		if(j < 0 || j >= n) return;

		solutions[i][j]++;

		findPaths(m, n, N - 1, i - 1, j, solutions);
		findPaths(m, n, N - 1, i + 1, j, solutions);
		findPaths(m, n, N - 1, i, j - 1, solutions);
		findPaths(m, n, N - 1, i, j + 1, solutions);
	}
}
