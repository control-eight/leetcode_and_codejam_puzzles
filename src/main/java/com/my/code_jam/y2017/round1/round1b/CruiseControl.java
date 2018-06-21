package com.my.code_jam.y2017.round1.round1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 4/8/17.
 */
public class CruiseControl {

	public static void main(String[] args) {

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; ++i) {

			int D = in.nextInt();
			int N = in.nextInt();

			List<Pair> horses = new ArrayList<>();
 			for(int j = 0; j < N; j++) {
				horses.add(new Pair(in.nextInt(), in.nextInt()));
			}

			solve(i, D, horses);

		}
	}

	private static void solve(int i, long d, List<Pair> horses) {
		double time;
		if(horses.size() == 1) {
			time = getTimeForOne(d, horses.get(0));
		} else if(horses.size() == 2) {

			double time1 = getTimeForOne(d, horses.get(0));
			double time2 = getTimeForOne(d, horses.get(1));

			time = (horses.get(0).k - horses.get(1).k) /(horses.get(1).s - horses.get(0).s);

			if(time == 0 || Math.abs(time) > Math.max(time1, time2)) {

				if(time1 > time2) {
					time = getTimeForOne(d, horses.get(0));
				} else {
					time = getTimeForOne(d, horses.get(1));
				}

			} else {
				time += getTimeForOne(d, new Pair(horses.get(0).k + horses.get(0).s * time,
						Math.min(horses.get(0).s, horses.get(1).s)));
			}
		} else {
			 throw new RuntimeException("Unsupported operation");
		}

		System.out.println("Case #" + i + ": " +
				BigDecimal.valueOf(d).divide(BigDecimal.valueOf(time), 6, RoundingMode.HALF_UP).doubleValue());
	}

	private static double getTimeForOne(long d, Pair horse) {
		return (d - horse.k) / horse.s;
	}

	private static class Pair {

		private double k;

		private long s;

		public Pair(double k, long s) {
			this.k = k;
			this.s = s;
		}
	}
}
