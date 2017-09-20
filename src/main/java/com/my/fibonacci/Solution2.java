package com.my.fibonacci;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.bykovsky on 9/17/17.
 */
public class Solution2 {

	private static int count = 0;

	public static void main(String[] args) {
		/*count = 0;
		new Solution2().solve(1549);
		System.out.println(count + " " + Math.log10(1549)/Math.log10(2));

		count = 0;
		new Solution2().solve(15490);
		System.out.println(count + " " + Math.log10(15490)/Math.log10(2));

		count = 0;
		new Solution2().solve(154900);
		System.out.println(count + " " + Math.log10(154900)/Math.log10(2));

		new SolutionN().solve(154900);*/

		/*assertEquals(0, new Solution2().solve(0));
		assertEquals(1, new Solution2().solve(1));
		assertEquals(1, new Solution2().solve(2));
		assertEquals(2, new Solution2().solve(3));
		assertEquals(3, new Solution2().solve(4));
		assertEquals(5, new Solution2().solve(5));
		assertEquals(8, new Solution2().solve(6));
		assertEquals(13, new Solution2().solve(7));
		assertEquals(21, new Solution2().solve(8));
		assertEquals(34, new Solution2().solve(9));
		assertEquals(987, new Solution2().solve(16));
		assertEquals(102334155, new Solution2().solve(40));*/

		long start = System.nanoTime();
		for(int i = 1000000000; i < 1000000020; i++) {
			new Solution2().solve(i);
		}
		System.out.println(System.nanoTime() - start);

		start = System.nanoTime();
		for(int i = 1000000000; i < 1000000020; i++) {
			new SolutionN().solve(i);
		}
		System.out.println(System.nanoTime() - start);

		for(int i = 1000000000; i < 1000000020; i++) {
			assertEquals(new SolutionN().solve(i), new Solution2().solve(i));
		}
	}

	/*T[1] = 1;
	T[2] = 1;
	T[3] = 2;
	T[4] = 3;
	T[5] = 5;
	T[6] = 8;
	T[7] = 13;
	T[8] = 21;
	T[9] = 34;*/

	private static void assertEquals(long expected, long actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	//logn
	public long solve(int n) {
		if(n == 0) return 0;
		return solve(n, new HashMap<>());
	}

	//T[k] * T[n - k + 1] + T[k - 1] * T[n - k]
	public long solve(int n, Map<Integer, Long> cache) {
		if(n == 1 || n == 2) return 1;
		if(cache.containsKey(n)) return cache.get(n);
		//count++;
		int k = n - n / 2;
		long solution = solve(k, cache) * solve(n - k + 1, cache) + solve(k - 1, cache) * solve(n - k, cache);
		cache.put(n, solution);
		return solution;
	}


}
