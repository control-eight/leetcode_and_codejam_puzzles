package com.my.test;

import java.util.ArrayList;
import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new Test3().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7}));
        System.out.println((System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(new Test3().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4}));
        System.out.println((System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(new Test3().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10}));
        System.out.println((System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(new Test3().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10, 3, 10}));
        System.out.println((System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(new Test3().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10, 3, 10, 15, 22}));
        System.out.println((System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(new Test3().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10, 3, 10, 15, 22, 1, 12}));
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }

    public int optimum_task_assignment(int[] task_durations) {
        int N = task_durations.length;
        return solve(task_durations, N, new boolean[N], new ArrayList<>());
    }

    public int solve(int[] task_durations, int N, boolean[] selected, List<int[]> pairs) {
        if (pairs.size() == N / 2) {
            return calcMinTime(pairs);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                for (int j = i + 1; j < N; j++) {
                    if (!selected[j]) {
                        setValues(task_durations, selected, pairs, i, j);
                        min = Math.min(solve(task_durations, N, selected, pairs), min);
                        unsetValues(selected, pairs, i, j);
                    }
                }
            }
        }
        return min;
    }

    private int calcMinTime(List<int[]> pairs) {
        int min = 0;
        for (int[] pair : pairs) {
            min = Math.max(pair[0] + pair[1], min);
        }
        return min;
    }

    private void setValues(int[] task_durations, boolean[] selected, List<int[]> pairs, int i, int j) {
        selected[i]  = true;
        selected[j]  = true;
        pairs.add(new int[] {task_durations[i], task_durations[j]});
    }

    private void unsetValues(boolean[] selected, List<int[]> pairs, int i, int j) {
        pairs.remove(pairs.size() - 1);
        selected[i]  = false;
        selected[j]  = false;
    }
}
