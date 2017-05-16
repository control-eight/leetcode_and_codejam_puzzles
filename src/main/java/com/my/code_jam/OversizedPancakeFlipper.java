package com.my.code_jam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/8/17.
 */
public class OversizedPancakeFlipper {

	public static void main(String[] args) {

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {

			String pancakes = in.next();
			int k = in.nextInt();
			solve(i, pancakes, k);
		}

	}

	private static void solve(int testCase, String pancakes, int k) {
		String result = null;
		int flips = 0;
		char[] charArr = pancakes.toCharArray();
		for(int j = 0; j < charArr.length; j++) {

			char c = charArr[j];

			if('-' == c) {
				if(charArr.length - j >= k) {
					flips++;
					for(int i = j; i < j + k; i++) {
						charArr[i] = charArr[i] == '+'? '-': '+';
					}

				} else {
					result = "Case #" + testCase + ": IMPOSSIBLE";
					break;
				}
			}
		}
		if(result == null) {
			result = "Case #" + testCase + ": " + flips;
		}
		System.out.println(result);
	}
}
