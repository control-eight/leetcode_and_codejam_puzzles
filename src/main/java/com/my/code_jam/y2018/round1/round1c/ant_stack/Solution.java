package com.my.code_jam.y2018.round1.round1c.ant_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by alex.bykovsky on 5/5/18.
 */
public class Solution {

	public static final long LONG = (long) 1e14;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {

			int N = in.nextInt();

			List<Ant> ants = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				ants.add(new Ant(i, in.nextInt()));
			}

			System.out.println("Case #"+t+": "+solve(ants));
		}
	}

	private static long solve(List<Ant> ants) {
		Pair[] prevSolutions = new Pair[ants.size() + 1];
		for (int i = 0; i <= ants.size(); i++) {
			prevSolutions[i] = new Pair(LONG, 0);
		}

		Pair[] currentSolutions = new Pair[ants.size() + 1];
		int maxCount = 0;
		for (int j = 1; j <= ants.size(); j++) {
			currentSolutions[ants.size()] = new Pair(LONG, 0);
			for (int i = ants.size() - 1; i >= 0; i--) {

				Pair prevCount = prevSolutions[i + 1];
				Pair prevSame = currentSolutions[i + 1];

				Ant currentAnt = ants.get(i);

				Pair current = null;
				if (prevCount.minWeight >= currentAnt.weight) {
					current = new Pair(Math.min(currentAnt.weight * 6, prevCount.minWeight - currentAnt.weight), prevCount.count + 1);
				}

				if (current != null && (current.minWeight >= prevSame.minWeight || current.count > prevSame.count)) {
					currentSolutions[i] = current;
				} else if (prevSame.count > prevSolutions[i].count || (
						prevSame.count == prevSolutions[i].count && prevSame.minWeight > prevSolutions[i].count)) {
					currentSolutions[i] = prevSame;
				} else {
					currentSolutions[i] = prevSolutions[i];
				}
			}
			if (currentSolutions[0].count <= maxCount) {
				return maxCount;
			} else {
				maxCount = currentSolutions[0].count;
			}

			prevSolutions = currentSolutions;
			currentSolutions = new Pair[ants.size() + 1];
		}

		return maxCount;
	}

	private static class Ant {
		private int length;
		private long weight;
		public Ant(int length, long weight) {
			this.length = length;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Ant{" +
					"length=" + length +
					", weight=" + weight +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Ant ant = (Ant) o;

			return length == ant.length;
		}

		@Override
		public int hashCode() {
			return length;
		}
	}

	private static class Pair {
		private long minWeight;
		private int count;

		public Pair(long minWeight, int count) {
			this.minWeight = minWeight;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Pair{" +
					"minWeight=" + minWeight +
					", count=" + count +
					'}';
		}
	}
}
