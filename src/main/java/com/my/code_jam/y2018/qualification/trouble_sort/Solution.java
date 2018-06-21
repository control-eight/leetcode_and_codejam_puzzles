package com.my.code_jam.y2018.qualification.trouble_sort;

import java.util.Random;

/**
 * Created by alex.bykovsky on 4/7/18.
 */
public class Solution {

	public static void main(String[] args) {
		/*Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();

		for (int i = 1; i <= t; ++i) {
			int N = in.nextInt();

			int[] arr = new int[N];
			for (int k = 0; k < N; k++) {
				arr[k] = in.nextInt();
			}
			solve(i, arr);
		}*/

		int[] arr = new int[100000];
		Random random = new Random(arr.length);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (arr.length * random.nextDouble());
		}
		long start = System.currentTimeMillis();
		solve(1, arr);
		System.out.println(System.currentTimeMillis() - start);
	}

	private static void solve(int testCase, int[] arr) {
		int wrongIndex = sort(arr);

		if (wrongIndex > -1) {
			System.out.println("Case #" + testCase + ": " + wrongIndex);
		} else {
			System.out.println("Case #" + testCase + ": OK");
		}
	}

	private static int sort(int[] arr) {
		boolean done = false;
		while (!done) {
			done = true;
			boolean prevFixed = true;
			for (int i = 0; i < arr.length - 2; i++) {
				if (arr[i] > arr[i + 2]) {
					done = false;
					reverse(arr, i);
					prevFixed = false;
				}
				if (i >= 2) {
					if (prevFixed && arr[i - 2] <= arr[i]) {
						if (arr[i - 2] > arr[i - 1]) {
							return i - 2;
						} else if (arr[i - 1] > arr[i]) {
							return i - 1;
						}
					} else {
						prevFixed = false;
					}
				}
			}

			if (arr.length >= 4) {
				if (prevFixed && arr[arr.length - 4] <= arr[arr.length - 2]) {
					if (arr[arr.length - 4] > arr[arr.length - 3]) {
						return arr.length - 4;
					} else if (arr[arr.length - 3] > arr[arr.length - 2]) {
						return arr.length - 3;
					}
				} else {
					prevFixed = false;
				}
			}
			if (arr.length >= 3) {
				if (prevFixed && arr[arr.length - 3] <= arr[arr.length - 1]) {
					if (arr[arr.length - 3] > arr[arr.length - 2]) {
						return arr.length - 3;
					} else if (arr[arr.length - 2] > arr[arr.length - 1]) {
						return arr.length - 2;
					}
				}
			}
		}
		return -1;
	}

	private static void reverse(int[] arr, int i) {
		int tmp = arr[i];
		arr[i] = arr[i + 2];
		arr[i + 2] = tmp;
	}
}


