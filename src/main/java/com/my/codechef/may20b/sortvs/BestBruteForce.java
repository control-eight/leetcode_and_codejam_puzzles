package com.my.codechef.may20b.sortvs;

import java.util.*;

public class BestBruteForce {

    public static int solve(int[] permutation, int[][] swap) {
        boolean[][] M = new boolean[permutation.length][permutation.length];
        for (int[] ints : swap) {
            M[ints[0] - 1][ints[1] - 1] = true;
            M[ints[1] - 1][ints[0] - 1] = true;
        }
        Map<Integer, Integer> future = new HashMap<>();
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                future.put(i, permutation[i]);
            }
        }

        zeroOps(permutation, M);
        int result = 0;
        result += oneToOne(permutation);
        result += findMeInTheEdges(permutation, M);

        return solveInternal(permutation, future, M, new HashSet<>(), result,
                checkSorted(permutation), checkSorted(permutation));
    }

    private static int solveInternal(int[] permutation, Map<Integer, Integer> future, boolean[][] M,
                                     Set<Map<Integer, Integer>> visited, int result,
                                     int untilSorted, int untilSortedStart) {
        if (result >= permutation.length + 10) return Integer.MAX_VALUE;
        if (!visited.add(future)) return Integer.MAX_VALUE;
        if (result - (untilSortedStart - untilSorted) > 2) return Integer.MAX_VALUE;
        if (untilSorted == 0) return result;

        int min = Integer.MAX_VALUE;
        for (int i : future.keySet()) {
            for (int j : future.keySet()) {
                if (j > i) {
                    Map<Integer, Integer> copy = new HashMap<>(future);
                    if (M[i][j]) {
                        if (isReachable(permutation, M, i, j)) {
                            swapIndex(copy, i, j);
                        }
                        int sorted = process(i, j, copy);
                        if (sorted > 0) {
                            int tmp = solveInternal(permutation, copy, M, visited, result + 1,
                                    untilSorted - sorted, untilSortedStart);
                            min = Math.min(min, tmp);
                        }
                    } else {
                        swapIndex(copy, i, j);
                        if (future.get(future.get(i) - 1) == i + 1) {
                            swapIndex(copy, i, j);
                            result++;
                        }
                        int sorted = process(i, j, copy);
                        int tmp = solveInternal(permutation, copy, M, visited, result + 1,
                                untilSorted - sorted, untilSortedStart);
                        min = Math.min(min, tmp);
                    }
                }
            }
        }
        return min;
    }

    private static boolean isReachable(int[] permutation, boolean[][] M, int i, int v) {
        boolean[] visited = new boolean[permutation.length];
        visited[i] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            Integer next = queue.poll();
            for (int k = 0; k < M[next].length; k++) {
                if (M[next][k]) {
                    if (k == v - 1) {
                        return true;
                    } else if (!visited[k]) {
                        visited[k] = true;
                        queue.offer(k);
                    }
                }
            }
        }
        return false;
    }

    private static int process(int i, int j, Map<Integer, Integer> copy) {
        int sorted = 0;
        if (copy.get(j) == j + 1) {
            copy.remove(j);
            sorted++;
        }
        if (copy.get(i) == i + 1) {
            copy.remove(i);
            sorted++;
        }
        return sorted;
    }

    private static int checkSorted(int[] permutation) {
        int result = 0;
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                result++;
            }
        }
        return result;
    }

    private static void swapIndex(Map<Integer, Integer> permutation, int i, int j) {
        int tmp = permutation.get(j);
        permutation.put(j, permutation.get(i));
        permutation.put(i, tmp);
    }

    private static void swapIndex(int[] permutation, int i, int j) {
        int tmp = permutation[j];
        permutation[j] = permutation[i];
        permutation[i] = tmp;
    }

    private static int zeroOps(int[] permutation, boolean[][] M) {
        int result = 0;
        for (int i = 0; i < permutation.length; i++) {
            while (permutation[i] != i + 1) {
                if (isReachable(permutation, M, i, permutation[i])) {
                    swapIndex(permutation, i, permutation[i] - 1);
                    result++;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private static int oneToOne(int[] permutation) {
        int result = 0;
        for (int i = 0; i < permutation.length; i++) {
            int v = permutation[i];
            if (v != i + 1 && permutation[v - 1] == i + 1) {
                swapIndex(permutation, i, v - 1);
                result++;
            }
        }
        return result;
    }

    private static int findMeInTheEdges(int[] permutation, boolean[][] M) {
        int result = 0;
        for (int i = 0; i < permutation.length; i++) {
            int v = permutation[i];
            if (v != i + 1) {
                int k = findValue(permutation, M, i, v);
                if (k != -1) {
                    swapIndex(permutation, i, k);
                    zeroOps(permutation, M);
                    result++;
                }
            }
        }
        return result;
    }

    private static int findValue(int[] permutation, boolean[][] M, int i, int v) {
        Set<Integer> v1 = bfs(permutation, M, i);
        Set<Integer> v2 = bfs(permutation, M, v - 1);
        for (Integer j : v2) {
            if (v1.contains(permutation[j] - 1)) {
                return j;
            }
        }
        return -1;
    }

    private static Set<Integer> bfs(int[] permutation, boolean[][] M, int i) {
        Set<Integer> visited = new HashSet<>();
        visited.add(i);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            Integer next = queue.poll();
            for (int k = 0; k < M[next].length; k++) {
                if (M[next][k]) {
                    if (visited.add(k)) {
                        queue.offer(k);
                    }
                }
            }
        }
        return visited;
    }
}
