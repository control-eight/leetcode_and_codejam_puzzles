package com.my.test;

import java.util.Arrays;
import java.util.Random;

public class Test2 {

    public static void main(String[] args) {
        System.out.println(new Test2().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7}));
        System.out.println(new Test2().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4}));
        System.out.println(new Test2().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10}));
        System.out.println(new Test2().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10, 3, 10}));
        System.out.println(new Test2().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10, 3, 10, 15, 22}));
        System.out.println(new Test2().optimum_task_assignment(new int[] {1, 4, 4, 5, 6, 7, 3, 4, 1, 10, 3, 10, 15, 22, 1, 12}));

        int[] s = new int[100000000];
        Random random = new Random();
        for (int i = 0; i < s.length; i++) {
            s[i] = random.nextInt(1000);
        }

        long start = System.currentTimeMillis();
        System.out.println(new Test2().optimum_task_assignment(s));
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }

    public int optimum_task_assignment(int[] task_durations) {
        Arrays.sort(task_durations);
        int min = 0;
        for (int i = 0; i < task_durations.length; i++) {
            min = Math.max(min, task_durations[i]
                    + task_durations[task_durations.length - i - 1]);
        }
        return min;
    }


}
