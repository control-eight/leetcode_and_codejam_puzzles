package com.my.code_jam.y2019.round1.round1a.pylons;

import java.util.*;

/**
 * Created by alex.bykovsky on 5/4/18.
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int R = in.nextInt();
            int C = in.nextInt();
            solve(t, R, C);
        }
    }

    private static void solve(int t, int R, int C) {
        List<List<Integer>> moves;
        if (R <= 5 && C <= 5) {
            moves = solve2(-1, -1, R, C);
        } else {
            moves = solve(R, C);
        }
        System.out.println("Case #"+t+": "+ ((moves.size() == R * C)? "POSSIBLE": "IMPOSSIBLE"));
        for (List<Integer> move : moves) {
            System.out.println(move.get(0) + " " + move.get(1));
        }
    }

    private static List<List<Integer>> solve(int R, int C) {
        List<List<Integer>> possibleMoves = new ArrayList<>(R * C);
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                possibleMoves.add(Arrays.asList(i, j));
            }
        }
        List<List<Integer>> moves = new ArrayList<>();
        Random random = new Random(555);
        do {
            solve(possibleMoves, random, moves);
        } while (!possibleMoves.isEmpty());
        return moves;
    }

    private static void solve(List<List<Integer>> possibleMoves, Random random, List<List<Integer>> moves) {
        if (possibleMoves.isEmpty()) return;

        int index, ri, ci, i, j;
        Set<Integer> tmp = new HashSet<>();
        do  {
            if (tmp.size() == possibleMoves.size()) return;
            index = random.nextInt(possibleMoves.size());
            ri = moves.isEmpty()? -1: moves.get(moves.size() - 1).get(0);
            ci = moves.isEmpty()? -1: moves.get(moves.size() - 1).get(1);
            i = possibleMoves.get(index).get(0);
            j = possibleMoves.get(index).get(1);
            tmp.add(index);
        } while (ri == i || ci == j || ri - ci == i - j || ri + ci == i + j);

        moves.add(possibleMoves.get(index));
        possibleMoves.remove(index);
        solve(possibleMoves, random, moves);
        if (!possibleMoves.isEmpty()) {
            possibleMoves.add(index, moves.get(moves.size() - 1));
            moves.remove(moves.size() - 1);
        }
    }

    private static List<List<Integer>> solve2(int ri, int ci, int R, int C) {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (ri != i && ci != j && ri - ci != i - j && ri + ci != i + j) {
                    List<List<Integer>> moves = new ArrayList<>(R * C);
                    Set<Integer> cache = new HashSet<>();
                    cache.add((i - 1) * 20 + j);
                    moves.add(Arrays.asList(i, j));
                    solve(i, j, R, C, moves, cache);
                    if (moves.size() == R * C) return moves;
                }
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
