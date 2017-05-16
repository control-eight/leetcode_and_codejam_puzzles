package com.my.code_jam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/8/17.
 */
public class BathroomStalls {

	public static void main(String[] args) {

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {

			long N = in.nextLong();
			long K = in.nextLong();
			solve(i, N, K);
		}
	}

	private static void solve(int testCase, long N, long K) {

		long fullAmountOnLevel = 1L;
		long degreeOf2 = 1L;
		int i = 0;
		while (degreeOf2 <= K) {
			fullAmountOnLevel = degreeOf2;
			degreeOf2 = 1L << (++i);
		}

		long setOnPrevious = fullAmountOnLevel - 1;

		long leftInCurrent = N - setOnPrevious;

		long partLength = leftInCurrent / fullAmountOnLevel;
		long partLeft = leftInCurrent % fullAmountOnLevel;

		if (partLeft - (K - setOnPrevious) >= 0) {
			partLength += 1;
		}

		long big = partLength / 2;
		long small = partLength % 2 == 0? partLength / 2 - 1 : partLength / 2;

		System.out.println("Case #" + testCase + ": " + big + " " + small);
	}
}
