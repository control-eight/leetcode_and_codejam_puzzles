package com.my.leetcode.out_of_boundary_paths;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by alex.bykovsky on 9/25/17.
 */
public class Solution4 {

	public static void main(String[] args) {
		assertEquals(14, new Solution4().findPaths(2, 2, 3, 0, 0));
		assertEquals(12, new Solution4().findPaths(1, 3, 3, 0, 1));
		assertEquals(16, new Solution4().findPaths(1, 5, 3, 0, 2));
		assertEquals(4, new Solution4().findPaths(1, 1, 1, 0, 0));
		assertEquals(6, new Solution4().findPaths(2, 2, 2, 0, 0));
		assertEquals(109, new Solution4().findPaths(3, 3, 5, 0, 1));
		assertEquals(390153306, new Solution4().findPaths(36, 5, 50, 15, 3));
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

		List<Key> ordered = new ArrayList<>(m * n);
		Set<Key> visited = new HashSet<>();
		Deque<Key> deque = new LinkedList<>();
		deque.offerLast(new Key(i, j, N - 1));
		while (!deque.isEmpty()) {
			Key current = deque.poll();

			visited.add(current);
			ordered.add(current);

			int ii = current.i;
			int jj = current.j;
			int lastN = current.N;

			if(ii > 0) {
				addToQueue(visited, deque, new Key(ii - 1, jj, lastN - 1));
			}
			if(ii < m - 1) {
				addToQueue(visited, deque, new Key(ii + 1, jj, lastN - 1));
			}
			if(jj > 0) {
				addToQueue(visited, deque, new Key(ii, jj - 1, lastN - 1));
			}
			if(jj < n - 1) {
				addToQueue(visited, deque, new Key(ii, jj + 1, lastN - 1));
			}
		}

		int prevI = 1;
		int currentI = 2;
		for(int NN = 2; NN <= N; NN++) {
			for (Key current : ordered) {
				int ii = current.i;
				int jj = current.j;

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

				solutions[ii][jj][0] = (solutions[ii][jj][0] + prev) % INT;
				solutions[ii][jj][currentI] = prev % INT;
			}
			prevI = prevI == 1? 2: 1;
			currentI = currentI == 1? 2: 1;
		}

		long count = 0;

		for(int ii = 0; ii < m; ii++) {
			for(int jj = 0; jj < n; jj++) {
				if(ii == 0) {
					count += solutions[ii][jj][0] % INT;
				}
				if(jj == 0) {
					count += solutions[ii][jj][0] % INT;
				}
				if(ii == m - 1) {
					count += solutions[ii][jj][0] % INT;
				}
				if(jj == n - 1) {
					count += solutions[ii][jj][0] % INT;
				}
			}
		}

		return (int) (count % INT);
	}

	private void addToQueue(Set<Key> visited, Deque<Key> deque, Key next) {
		if(next.N < 0) return;

		if(!visited.contains(next)) {
			deque.offerLast(next);
			visited.add(next);
		}
	}

	private static class Key {
		private int i;
		private int j;
		private int N;

		public Key(int i, int j, int n) {
			this.i = i;
			this.j = j;
			N = n;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Key key = (Key) o;

			if (i != key.i) return false;
			return j == key.j;
		}

		@Override
		public int hashCode() {
			int result = i;
			result = 31 * result + j;
			return result;
		}
	}
}
