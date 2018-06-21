package com.my.leetcode.out_of_boundary_paths;

/**
 * Created by alex.bykovsky on 9/25/17.
 */
public class Solution5Best {

	public static void main(String[] args) {
		assertEquals(14, new Solution5Best().findPaths(2, 2, 3, 0, 0));
		assertEquals(12, new Solution5Best().findPaths(1, 3, 3, 0, 1));
		assertEquals(16, new Solution5Best().findPaths(1, 5, 3, 0, 2));
		assertEquals(4, new Solution5Best().findPaths(1, 1, 1, 0, 0));
		assertEquals(6, new Solution5Best().findPaths(2, 2, 2, 0, 0));
		assertEquals(109, new Solution5Best().findPaths(3, 3, 5, 0, 1));
		assertEquals(390153306, new Solution5Best().findPaths(36, 5, 50, 15, 3));
	}

	private static void assertEquals(long expected, long actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	private static final int INT = ((int) Math.pow(10, 9) + 7);

	public int findPaths(int m, int n, int N, int i, int j) {

		if(N == 0) return 0;

		long[][][] solutions = new long[m][n][3];
		//all
		solutions[i][j][0] = 1;
		//prev
		solutions[i][j][1] = 1;
		//current
		solutions[i][j][2] = 0;

		long count = 0;
		if(i == 0) {
			count += 1;
		}
		if(j == 0) {
			count += 1;
		}
		if(i == m - 1) {
			count += 1;
		}
		if(j == n - 1) {
			count += 1;
		}

		int prevI = 1;
		int currentI = 2;

		for(int NN = 2; NN <= N; NN++) {
			for(int ii = 0; ii < m; ii++) {
				for(int jj = 0; jj < n; jj++) {
					long prev = 0;
					if (ii > 0) {
						prev += solutions[ii - 1][jj][prevI];
					}
					if (ii < m - 1) {
						prev += solutions[ii + 1][jj][prevI];
					}
					if (jj > 0) {
						prev += solutions[ii][jj - 1][prevI];
					}
					if (jj < n - 1) {
						prev += solutions[ii][jj + 1][prevI];
					}

					long prevMod = prev % INT;

					if(ii == 0) {
						count += prevMod;
					}
					if(jj == 0) {
						count += prevMod;
					}
					if(ii == m - 1) {
						count += prevMod;
					}
					if(jj == n - 1) {
						count += prevMod;
					}

					solutions[ii][jj][0] = (solutions[ii][jj][0] + prev) % INT;
					solutions[ii][jj][currentI] = prevMod;
				}
			}
			prevI = prevI == 1? 2: 1;
			currentI = currentI == 1? 2: 1;
		}

		return (int) (count % INT);
	}
}
