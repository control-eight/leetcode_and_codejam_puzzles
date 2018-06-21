package com.my.code_jam.y2018.round1.round1a.bit_party;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/4/18.
 */
public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			int R = in.nextInt();
			int B = in.nextInt();
			int C = in.nextInt();

			Cashier[] cashiers = new Cashier[C];
			for (int i = 0; i < C; i++) {
				cashiers[i] = new Cashier(in.nextInt(), in.nextInt(), in.nextInt());
			}
			System.out.println("Case #"+t+": "+solve(R, B, cashiers));
		}
	}

	private static long solve(int R, long B, Cashier[] cashiers) {
		long result = Integer.MAX_VALUE;
		for (Cashier cashier : cashiers) {
			List<Cashier> list = new ArrayList<>();
			list.add(cashier);

			List<Cashier> cashierList = new ArrayList<>(Arrays.asList(cashiers));
			cashierList.remove(cashier);

			result = Math.min(solveC(B, cashierList, list, R), result);
		}
		return result;
	}

	private static long solveC(long B, List<Cashier> cashierList, List<Cashier> list, int R) {
		if (cashierList.isEmpty()) {
			long result = Integer.MAX_VALUE;
			for (long b = 0; b <= Math.min(list.get(0).M, B); b++) {
				result = Math.min(solveB(calcTime(0, list, b),1, B - b, list, R), result);
			}
			return result;
		} else {
			long result = Integer.MAX_VALUE;
			for (Cashier cashier : cashierList) {
				List<Cashier> listNew = new ArrayList<>();
				listNew.addAll(list);
				listNew.add(cashier);

				List<Cashier> cashierListNew = new ArrayList<>();
				cashierListNew.addAll(cashierList);
				cashierListNew.remove(cashier);

				result = Math.min(solveC(B, cashierListNew, listNew, R), result);
			}
			return result;
		}
	}

	private static long solveB(long time, int robot, long B, List<Cashier> list, int R) {
		if (B == 0) {
			return time;
		} else if (robot == R || B < 0) {
			return Integer.MAX_VALUE;
		} else {
			long newTime = Integer.MAX_VALUE;
			for (long b = 0; b <= Math.min(list.get(robot).M, B); b++) {
				newTime = Math.min(solveB(calcTime(robot, list, b),robot + 1, B - b, list, R), newTime);
			}
			return Math.max(time, newTime);
		}
	}

	private static long calcTime(int robot, List<Cashier> list, long b) {
		if (b == 0) return 0;
		return b * list.get(robot).S + list.get(robot).P;
	}

	private static class Cashier {
		private static int ID;
		private int id;
		private long M;
		private long S;
		private long P;
		public Cashier(long m, long s, long p) {
			id = ID;
			ID++;
			M = m;
			S = s;
			P = p;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Cashier cashier = (Cashier) o;

			return id == cashier.id;
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public String toString() {
			return id + "";
		}
	}
}
