package com.my.leetcode.out_of_boundary_paths;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 9/25/17.
 */
public class Solution {

	public static void main(String[] args) {
		assertEquals(6, new Solution().findPaths(2, 2, 2, 0, 0));
		assertEquals(12, new Solution().findPaths(1, 3, 3, 0, 1));
		assertEquals(390153306, new Solution().findPaths(36, 5, 50, 15, 3));
	}

	private static void assertEquals(long expected, long actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	private Map<Key, BigInteger> cache = new HashMap<>();

	public int findPaths(int m, int n, int N, int i, int j) {
		return findPathsInternal(m, n, N, i, j).mod(BigInteger.valueOf((int)Math.pow(10, 9) + 7)).intValue();
	}

	public BigInteger findPathsInternal(int m, int n, int N, int i, int j) {

		if(N == 0) return BigInteger.ZERO;
		if(i < 0 || i >= m) return BigInteger.ZERO;
		if(j < 0 || j >= n) return BigInteger.ZERO;

		Key key = new Key(N, i, j);

		if(cache.containsKey(key)) {
			return cache.get(key);
		}

		BigInteger count = BigInteger.ZERO;

		if(i == 0) {
			count = count.add(BigInteger.ONE);
		}
		if(j == 0) {
			count = count.add(BigInteger.ONE);
		}
		if(i == m - 1) {
			count = count.add(BigInteger.ONE);
		}
		if(j == n - 1) {
			count = count.add(BigInteger.ONE);
		}

		count = count.add(findPathsInternal(m, n, N - 1, i - 1, j));
		count = count.add(findPathsInternal(m, n, N - 1, i + 1, j));
		count = count.add(findPathsInternal(m, n, N - 1, i, j - 1));
		count = count.add(findPathsInternal(m, n, N - 1, i, j + 1));

		cache.put(key, count);

		return count;
	}

	private static class Key {

		private int n;

		private int i;

		private int j;

		public Key(int n, int i, int j) {
			this.n = n;
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Key key = (Key) o;

			if (n != key.n) return false;
			if (i != key.i) return false;
			return j == key.j;
		}

		@Override
		public int hashCode() {
			int result = n;
			result = 31 * result + i;
			result = 31 * result + j;
			return result;
		}
	}

	/*//if(N == 1) {
		if(i == 0 && j == 0) {
		return 2;
	} else if(i == 0 && j == n - 1) {
		return 2;
	} else if(i == m - 1 && j == 0) {
		return 2;
	} else if(i == m - 1 && j == n - 1) {
		return 2;
	} else if(i == 0) {
		return 1;
	} else if(j == 0) {
		return 1;
	} else if(i == m - 1) {
		return 1;
	} else if(j == n - 1) {
		return 1;
	}
	//}*/

	/*public int findPaths(int m, int n, int N, int i, int j) {

		if(m == 0 || n == 0) return 0;
		if(N == 0) return 0;

		int[][][] solutions = new int[N][m][n];

		for(int mm = 0; mm < m; mm++) {
			for(int nn = 0; nn < n; nn++) {

				if(mm == 0 && nn == 0) {
					solutions[0][mm][nn] += 2;
				} else if(mm == 0 && nn == n - 1 && n > 1) {
					solutions[0][mm][nn] += 2;
				} else if(mm == m - 1 && nn == 0 && m > 1) {
					solutions[0][mm][nn] += 2;
				} else if(mm == m - 1 && nn == n - 1 && (m > 1 || n > 1)) {
					solutions[0][mm][nn] += 2;
				} else if(mm == 0) {
					solutions[0][mm][nn] += 1;
				} else if(mm == m - 1) {
					solutions[0][mm][nn] += 1;
				} else if(nn == 0) {
					solutions[0][mm][nn] += 1;
				} else if(nn == n - 1) {
					solutions[0][mm][nn] += 1;
				}
			}
		}

		for(int NN = 2; NN <= N; NN++) {

			int rowStart = Math.max(i - NN - 1, 0);
			int rowEnd = Math.min(i + NN - 1, m  - 1);

			int colStart = Math.max(j - NN - 1, 0);
			int colEnd = Math.max(j + NN - 1, n - 1);

			for(int ii = rowStart; ii <= rowEnd; ii++) {
				for(int jj = colStart; jj <= colEnd; jj++) {




				}
			}
		}


		int count = 0;

		return (int) (count % (Math.pow(10, 9) + 7));
	}*/

}
