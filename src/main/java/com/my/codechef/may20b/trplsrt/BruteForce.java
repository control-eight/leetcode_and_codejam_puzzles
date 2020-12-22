package com.my.codechef.may20b.trplsrt;

import java.util.*;

public class BruteForce {

    public static void main(String[] args) {
        /*solve(1, 2, 3);
        solve(3, 1, 2);
        solve(4, 1, 2, 3);
        solve(4, 1, 3, 2);
        solve(5, 1, 2, 3, 4);
        solve(6, 1, 3, 2, 4, 5);
        solve(6, 1, 2, 3, 4, 5);
        solve(4, 5, 6, 1, 2, 3);
        solve(3, 4, 1, 5, 2, 6);
        solve(3, 4, 1, 5, 2);
        solve(1, 5, 2, 4, 3, 6, 7);
        solve(7, 3, 5, 6, 2, 4, 1);
        solve(1, 2, 3, 5, 4);
        solve(3, 2, 1, 6, 4, 5);
        solve(1, 5, 2, 4, 3, 6, 7);
        solve(1, 6, 2, 3, 4, 5, 7);
        solve(7, 3, 5, 6, 2, 4, 1);
        solve(2, 1, 7, 5, 4, 3, 6);
        solve(5, 3, 7, 6, 1, 4, 2);
        solve(6, 4, 7, 5, 2, 1, 3);
        solve(5, 3, 7, 6, 1, 4, 2);*/
        solve(4, 5, 6, 1, 2, 3);
    }

    public static Result solve(Integer ... permutation) {
        return solve(permutation, new HashSet<>(), new ArrayList<>());
    }

    private static Result solve(Integer[] permutation, Set<Key> allMoves, List<List<Integer>> moves) {
        if (moves.size() > permutation.length / 2) return new Result(false, null);
        if (checkSorted(permutation)) {
            return new Result(true, moves);
        }
        Result result = new Result(false, null);
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                for (int k = 0; k < permutation.length; k++) {
                    if (k != i && permutation[k] != k + 1) {
                        for (int l = 0; l < permutation.length; l++) {
                            if (l != i && l != k && permutation[l] != l + 1) {
                                Integer[] copy = Arrays.copyOf(permutation, permutation.length);
                                List<List<Integer>> movesCopy = new ArrayList<>(moves);
                                set(copy, i, k, l);
                                movesCopy.add(Arrays.asList(i + 1, k + 1, l + 1));
                                if (allMoves.add(new Key(copy, movesCopy))) {
                                    Result x = solve(copy, allMoves, movesCopy);
                                    if (x.solvable && x.moves.size() <= permutation.length / 2)
                                        return x;
                                    if (x.solvable && (!result.solvable || x.moves.size() < result.moves.size())) {
                                        result = x;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean solve2(Integer ... permutation) {
        Result solve = solve2(permutation, new HashSet<>(), 0);
        return solve.solvable;
    }
    private static Result solve2(Integer[] permutation, Set<Key> allMoves, int move) {
        if (move > permutation.length + 6) {
            return new Result(false, null);
        }
        if (!allMoves.add(new Key(permutation, null))) {
            return new Result(false, null);
        }
        if (checkSorted(permutation)) {
            return new Result(true, null);
        }
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                for (int k = 0; k < permutation.length; k++) {
                    if (k != i && permutation[k] != k + 1) {
                        for (int l = 0; l < permutation.length; l++) {
                            if (l != i && l != k && permutation[l] != l + 1) {
                                Integer[] copy = Arrays.copyOf(permutation, permutation.length);
                                set(copy, i, k, l);
                                Result x = solve2(copy, allMoves, move + 1);
                                if (x.solvable) return x;
                            }
                        }
                    }
                }
            }
        }
        return new Result(false, null);
    }

    private static void set(Integer[] permutation, int i, int k, int l) {
        int tmp = permutation[i];
        permutation[i] = permutation[l];
        permutation[l] = permutation[k];
        permutation[k] = tmp;
    }

    private static void unset(Integer[] permutation, int i, int k, int l) {
        int tmp = permutation[i];
        permutation[i] = permutation[k];
        permutation[k] = permutation[l];
        permutation[l] = tmp;
    }

    private static boolean checkSorted(Integer[] permutation) {
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) return false;
        }
        return true;
    }

    private static class Key {
        private Integer[] permutation;
        private List<List<Integer>> moves;

        public Key(Integer[] permutation, List<List<Integer>> moves) {
            this.permutation = permutation;
            this.moves = moves;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Arrays.equals(permutation, key.permutation) &&
                    Objects.equals(moves, key.moves);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(moves);
            result = 31 * result + Arrays.hashCode(permutation);
            return result;
        }
    }

    public static class Result {
        private boolean solvable;
        private List<List<Integer>> moves;

        public Result(boolean solvable, List<List<Integer>> moves) {
            this.solvable = solvable;
            this.moves = moves;
        }

        public boolean isSolvable() {
            return solvable;
        }

        public List<List<Integer>> getMoves() {
            return moves;
        }

        @Override
        public String toString() {
            if (!solvable) return -1 + "";
            if (moves.size() == 0) return 0 + "";

            StringBuilder sb = new StringBuilder(moves.size() + "\n");
            int i = 0;
            for (List<Integer> move : moves) {
                String s = "";
                if (i != moves.size() - 1) {
                    s = "\n";
                }
                sb.append(move.get(0)).append(" ").append(move.get(1)).append(" ")
                        .append(move.get(2)).append(s);
                i++;
            }
            return sb.toString();
        }
    }
}
