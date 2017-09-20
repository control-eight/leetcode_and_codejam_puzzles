package com.my.ugly_number;

/**
 * Created by alex.bykovsky on 9/14/17.
 */
public class UglyNumberII2 {

	public static void main(String[] args) {
		assertEquals(12, new UglyNumberII2().nthUglyNumber(10));
		assertEquals(20, new UglyNumberII2().nthUglyNumber(14));
		assertEquals(36, new UglyNumberII2().nthUglyNumber(20));
		assertEquals(937500, new UglyNumberII2().nthUglyNumber(500));
		//assertEquals(1399680000, new UglyNumberII2().nthUglyNumber(1600));

		for(int i = 1; i < 1690; i++) {
			try {
				assertEquals(new UglyNumberII().nthUglyNumber(i), new UglyNumberII2().nthUglyNumber(i));
			} catch (RuntimeException e) {
				System.out.println(e + " " + i);
			}
		}
	}

	private static void assertEquals(int expected, int actual) {
		if(expected != actual) {
			throw new RuntimeException(expected + " " + actual);
		}
	}

	public int nthUglyNumber(int n) {

		if(n == 0) return 0;
		if(n == 1) return 1;

		int twoStart = 1;
		int threeStart = 3;
		int fiveStart = 1;

		int last = 0;

		int[] solutionsForTwo = new int[n + 1];
		solutionsForTwo[0] = 0;
		solutionsForTwo[1] = 1;

		int[] solutionsForFive = new int[n + 1];
		solutionsForFive[0] = 0;
		solutionsForFive[1] = 1;

		int fiveTrack = 1;
		for(int i = 2; i < n + 1; i++) {

			int two = solutionsForTwo[twoStart] * 2;
			int five = solutionsForFive[fiveStart] * 5;

			if(five < 0) five = Integer.MAX_VALUE;

			int min = Math.min(threeStart, five);

			if(two < min) {
				last = two;
				twoStart++;
			} else if(threeStart == min) {
				last = threeStart;
				solutionsForFive[++fiveTrack] = last;
				threeStart *= 3;

				if(threeStart < 0) threeStart = Integer.MAX_VALUE;

			} else {
				last = five;
				solutionsForFive[++fiveTrack] = last;
				fiveStart++;
			}
			solutionsForTwo[i] = last;
		}
		return last;
	}
}
