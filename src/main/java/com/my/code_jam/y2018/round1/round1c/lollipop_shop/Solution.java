package com.my.code_jam.y2018.round1.round1c.lollipop_shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/5/18.
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();

		int index = 1;
		while (index <= T) {

			int N = in.nextInt();

			int[] counts = new int[N];
			boolean[] used = new boolean[N];

			for (int k = 1; k <= N; k++) {

				List<Integer> flavors = readFlavors(in, N);

				int flavor = chooseFlavor(counts, flavors, used);
				System.out.print(flavor + "\n");
				System.out.flush();
			}
			index++;
		}
	}

	private static int chooseFlavor(int[] counts, List<Integer> flavors, boolean[] used) {
		if (flavors.size() == 0) return -1;

		int result = -1;
		int resultCount = Integer.MAX_VALUE;

		for (Integer flavor : flavors) {
			if (!used[flavor]) {
				if (counts[flavor] < resultCount) {
					result = flavor;
					resultCount = counts[flavor];
				}
			}
		}
		if (result == -1) return result;
		used[result] = true;
		for (Integer flavor : flavors) {
			if (!used[flavor] && flavor != result) {
				counts[flavor]++;
			}
		}
		return result;
	}

	private static List<Integer> readFlavors(Scanner in, int N) {
		int D = in.nextInt();

		if (D == -1) {
			throw new RuntimeException("Runtime exccc");
		}

		List<Integer> flavors = new ArrayList<>();
		for (int i = 0; i < D; i++) {
			flavors.add(in.nextInt());
		}
		return flavors;
	}
}
