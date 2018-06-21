package com.my.leetcode.out_of_boundary_paths;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 9/25/17.
 */
public class Solution2 {

	public static void main(String[] args) {
		assertEquals(4, new Solution2().findPaths(1, 1, 1, 0, 0));
		assertEquals(6, new Solution2().findPaths(2, 2, 2, 0, 0));
		assertEquals(12, new Solution2().findPaths(1, 3, 3, 0, 1));
		assertEquals(390153306, new Solution2().findPaths(36, 5, 50, 15, 3));
		assertEquals(109, new Solution2().findPaths(3, 3, 5, 0, 1));
	}

	private static void assertEquals(long expected, long actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	private static final int INT = ((int) Math.pow(10, 9) + 7);

	private Map<Key, Long> cache = new HashMap<>();

	public long findPaths(int m, int n, int N, int i, int j) {

		if(N == 0) return 0;
		if(i < 0 || i >= m) return 0;
		if(j < 0 || j >= n) return 0;

		Key key = new Key(N, i, j);

		if(cache.containsKey(key)) {
			return cache.get(key);
		}

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

		count += findPaths(m, n, N - 1, i - 1, j);
		count += findPaths(m, n, N - 1, i + 1, j);
		count += findPaths(m, n, N - 1, i, j - 1);
		count += findPaths(m, n, N - 1, i, j + 1);

		cache.put(key, count % INT);

		return count % INT;
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
}
