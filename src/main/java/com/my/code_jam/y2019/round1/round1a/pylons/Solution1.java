package com.my.code_jam.y2019.round1.round1a.pylons;

import java.util.*;

/**
 * Created by alex.bykovsky on 5/4/18.
 */
public class Solution1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			int R = in.nextInt();
			int C = in.nextInt();
			solve(T, R, C);
		}
	}

	private static void solve(int T, int R, int C) {
		List<List<Integer>> moves = solve(R, C);
		System.out.println("Case #"+T+": "+ ((moves.size() == R * C)? "POSSIBLE": "IMPOSSIBLE"));
		for (List<Integer> move : moves) {
			System.out.println(move.get(0) + " " + move.get(1));
		}
	}

	private static List<List<Integer>> solve(int R, int C) {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				List<List<Integer>> moves = new ArrayList<>(R * C);
				Set<Integer> cache = new HashSet<>();
				cache.add((i - 1) * 20 + j);
				moves.add(Arrays.asList(i, j));
				solve(i, j, R, C, moves, cache);
				if (moves.size() == R * C) return moves;
			}
		}
		return Collections.emptyList();
	}

	private static void solve(int ri, int ci, int R, int C, List<List<Integer>> moves, Set<Integer> cache) {
		if (moves.size() == R * C) return;

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (ri != i && ci != j && ri - ci != i - j && ri + ci != i + j) {
					int move = (i - 1) * 20 + j;
					if (cache.add(move)) {
						moves.add(Arrays.asList(i, j));
						solve(i, j, R, C, moves, cache);
						if (moves.size() == R * C) return;
						moves.remove(moves.size() - 1);
						cache.remove(move);
					}
				}
			}
		}
	}
}
