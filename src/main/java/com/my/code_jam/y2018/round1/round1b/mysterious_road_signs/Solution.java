package com.my.code_jam.y2018.round1.round1b.mysterious_road_signs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by alex.bykovsky on 4/29/18.
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 1; i <= T; i++) {
			int S = in.nextInt();

			Sign[] arr = new Sign[S];
			for (int j = 0; j < S; j++) {
				arr[j] = new Sign(in.nextInt(), in.nextInt(), in.nextInt());
			}
			solve(i, arr);
		}
	}

	private static void solve(int t, Sign[] arr) {
		int max = findMax(arr);
		Set<String> counts = findCounts(arr, max);
		System.out.println("Case #"+t+": "+max+" "+counts.size());
	}

	private static int findMax(Sign[] arr) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr.length - i < max) break;
			int count = 0;
			Integer M = arr[i].M;
			Integer N = null;
			for (int ii = i; ii < arr.length; ii++) {
				if (arr[ii].M != M) {
					if (N == null) {
						N = arr[ii].N;
					} else if (arr[ii].N != N) {
						break;
					}
				}
				count++;
			}
			max = Math.max(max, count);

			count = 0;
			M = null;
			N = arr[i].N;
			for (int ii = i; ii < arr.length; ii++) {
				if (arr[ii].N != N) {
					if (M == null) {
						M = arr[ii].M;
					} else if (arr[ii].M != M) {
						break;
					}
				}
				count++;
			}
			max = Math.max(max, count);
		}
		return max;
	}

	private static Set<String> findCounts(Sign[] arr, int max) {
		Set<String> counts = new HashSet<>();

		for (int i = 0; i < arr.length; i++) {
			if (arr.length - i < max) break;
			int count = 0;
			Integer M = arr[i].M;
			Integer N = null;
			int last = arr.length - 1;
			for (int ii = i; ii < arr.length; ii++) {
				if (arr[ii].M != M) {
					if (N == null) {
						N = arr[ii].N;
					} else if (arr[ii].N != N) {
						last = ii - 1;
						break;
					}
				}
				count++;
			}
			if (count == max) {
				counts.add(i + "_" + last);
			}

			count = 0;
			M = null;
			N = arr[i].N;
			last = arr.length - 1;
			for (int ii = i; ii < arr.length; ii++) {
				if (arr[ii].N != N) {
					if (M == null) {
						M = arr[ii].M;
					} else if (arr[ii].M != M) {
						last = ii - 1;
						break;
					}
				}
				count++;
			}
			if (count == max) {
				counts.add(i + "_" + last);
			}
		}
		return counts;
	}

	private static class Sign {
		private int M;
		private int N;
		public Sign(int d, int a, int b) {
			M = d + a;
			N = d - b;
		}
	}
}
