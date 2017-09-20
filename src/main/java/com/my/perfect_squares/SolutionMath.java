package com.my.perfect_squares;

/**
 * Created by alex.bykovsky on 9/12/17.
 */
public class SolutionMath {

	public static void main(String[] args) {
		assertEquals(1, new SolutionMath().numSquares(4));
		assertEquals(3, new SolutionMath().numSquares(12));
		assertEquals(2, new SolutionMath().numSquares(13));
		assertEquals(4, new SolutionMath().numSquares(15));
		assertEquals(1, new SolutionMath().numSquares(16));
		assertEquals(4, new SolutionMath().numSquares(31));
		assertEquals(4, new SolutionMath().numSquares(239));

		long start = System.currentTimeMillis();
		for(int i = 1; i < 250; i++) {
			try {
				assertEquals(new Solution().numSquares(i), new SolutionMath().numSquares(i));
			} catch (RuntimeException e) {

			}
		}
		System.out.println(System.currentTimeMillis() - start);

		assertEquals(4, new SolutionMath().numSquares(7168));
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int numSquares(int n) {
		return numSquares(0, n, new Value[n + 1]);
	}

	private int numSquares(int size, int n, Value[] cache) {

		if(n == 0) {
			return size;
		} else if(n < 0) {
			return Integer.MAX_VALUE;
		}

		int square = (int) Math.pow((int) Math.sqrt(n), 2);
		int add = (int) Math.sqrt(square);
		add += add - 1;

		int min = Integer.MAX_VALUE;
		int i = 0;
		while(square > 0 && i < 2) {
			Integer key = n - square;
			Value value = cache[key];

			boolean cached = false;
			if (value != null) {
				if((size + 1) >= value.size) {
					min = Math.min(min, value.solution);
					cached = true;
				} else {
					cache[key] = null;
				}
			}

			if(!cached) {
				min = Math.min(min, numSquares(size + 1, n - square, cache));
			}

			square -= add;
			add -= 2;
			i++;
		}

		cache[n] = new Value(size, min);

		return min;
	}

	private static class Value {

		private int size;

		private int solution;

		public Value(int size, int solution) {
			this.size = size;
			this.solution = solution;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Value value = (Value) o;

			if (size != value.size) return false;
			return solution == value.solution;
		}

		@Override
		public int hashCode() {
			int result = size;
			result = 31 * result + solution;
			return result;
		}
	}
}
