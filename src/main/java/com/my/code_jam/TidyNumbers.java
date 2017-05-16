package com.my.code_jam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/8/17.
 */
public class TidyNumbers {

	public static void main(String[] args) {

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {

			long N = in.nextLong();
			solve(i, N);
		}
	}

	private static void solve(int testCase, long N) {

		int tensCount = 1;

		while (Math.pow(10, tensCount + 1) <= N) {
			long currentDigit = calcDigit(N, tensCount);
			long nextDigit = calcDigit(N, tensCount + 1);

			if(nextDigit > currentDigit) {
				N = (N / (long)Math.pow(10, tensCount + 1)) * (long)Math.pow(10, tensCount + 1)  + generateNines(tensCount, nextDigit);
			}
			tensCount++;
		}

		System.out.println("Case #" + testCase + ": " + N);
	}

	private static long generateNines(int count, long next) {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < count; i++) {
			result.append('9');
		}
		result.insert(0, next - 1);
		return Long.parseLong(result.toString());
	}

	private static long calcDigit(long N, int tens) {
		String s = Long.toString(N);
		return Long.parseLong(s.substring(s.length() - tens, s.length() - tens + 1));
	}
}
