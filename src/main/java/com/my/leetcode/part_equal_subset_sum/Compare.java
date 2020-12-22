package com.my.leetcode.part_equal_subset_sum;

import java.util.*;

public class Compare {

    public static void main(String[] args) {
        //runTests();
        runRandomTests();
    }

    private static void runTests() {
        List<CanPartition> list = Arrays.asList(new SolutionDPOpt(), /*new SolutionDfs(),
                new SolutionDfsOpt(),*/ new SolutionSuperOpt(), new SolutionSuperOptB(), new Hybrid());
        runTests(list,
                Arrays.asList(
                        new int[]{1, 1, 1, 1, 1, 1, 1, 9, 10, 10},
                        new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 3, 10, 11, 12, 14, 18, 19, 19, 19, 19, 19},
                        new int[]{1, 1, 1, 1, 11, 11, 15, 15, 16, 17, 17, 17, 17, 17, 17, 18, 18, 20, 20, 20},
                        new int[]{0, 0, 0, 0, 0, 1, 3, 15, 16, 17, 21, 22, 22, 23, 23, 24, 24, 24, 25, 25, 25, 25, 26, 26, 26, 26, 27, 28, 29, 29},
                        new int[]{4, 6, 6, 8, 8, 8, 8, 8, 10, 10, 10, 12, 14, 16, 16, 16, 16, 18, 20, 20},
                        new int[] {20, 18, 6, 8, 2, 16, 2, 18, 12, 2, 12, 10, 18, 12, 18, 20, 12, 6, 16, 2},
                        new int[] {2, 2, 6, 6, 6, 6, 8, 10, 10, 10, 10, 12, 12, 12, 12, 12, 18, 20, 20, 20, 20, 26, 26, 26, 28, 30, 30, 30, 30, 30},
                        new int[] {8, 8, 8, 10, 10, 10, 12, 12, 12, 14, 14, 14, 16, 16, 16, 18, 18, 18, 20, 20},
                        new int[] {2, 5, 5, 5, 5, 5, 5, 5, 5, 10},
                        new int[] {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60, 63, 66, 69, 72, 81, 85, 166}
                ), 100);
    }

    private static void runRandomTests() {
        List<CanPartition> list = Arrays.asList(new SolutionDPOpt(),/*, new SolutionDfs(),
                new SolutionDfsOpt(), new SolutionSuperOpt(), new SolutionSuperOptB(),*/ new Hybrid());
        runGeneratorTest2(list);
    }

    private static void runGeneratorTest(List<CanPartition> list) {
        Random random = new Random(5555);
        Map<Class<? extends CanPartition>, Long> map = new HashMap<>();
        int[] arr = new int[40];
        for (int i = 0; i < 200_000_000; i++) {
            for (int n = 0; n < arr.length; n++) {
                arr[n] = (int) (random.nextDouble() * 300) + 1;
            }
            for (CanPartition canPartition : list) {
                int[] copy = Arrays.copyOf(arr, arr.length);
                long start = System.nanoTime();
                canPartition.canPartition(copy);
                long end = System.nanoTime() - start;
                Class<? extends CanPartition> aClass = canPartition.getClass();
                map.put(aClass, map.getOrDefault(aClass, 0L) + end);
            }
        }
        List<Class<? extends CanPartition>> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.comparing(Class::toString));
        for (Class<? extends CanPartition> key : keys) {
            System.out.println(key + " " + (map.get(key)/1_000_000.0) + "ms");
        }
    }

    private static void runGeneratorTest2(List<CanPartition> list) {
        Random random = new Random(5555);
        Map<Class<? extends CanPartition>, Long> map = new HashMap<>();
        int maxLength = 80;
        for (int i = 0; i < 200_000_000; i++) {
            int[] arr = new int[(int) (random.nextDouble() * maxLength + 1)];
            for (int n = 0; n < arr.length; n++) {
                arr[n] = (int) (random.nextDouble() * 200) + 1;
            }
            for (CanPartition canPartition : list) {
                int[] copy = Arrays.copyOf(arr, arr.length);
                long start = System.nanoTime();
                canPartition.canPartition(copy);
                long end = System.nanoTime() - start;
                Class<? extends CanPartition> aClass = canPartition.getClass();
                map.put(aClass, map.getOrDefault(aClass, 0L) + end);
            }
        }
        List<Class<? extends CanPartition>> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.comparing(Class::toString));
        for (Class<? extends CanPartition> key : keys) {
            System.out.println(key + " " + (map.get(key)/1_000_000.0) + "ms");
        }
    }

    private static void runTests(List<CanPartition> list, List<int[]> input, int count) {
        Map<Class<? extends CanPartition>, Long> map = new HashMap<>();
        for (int[] arr : input) {
            for (int i = 0; i < count; i++) {
                for (CanPartition canPartition : list) {
                    int[] copy = Arrays.copyOf(arr, arr.length);
                    long start = System.nanoTime();
                    canPartition.canPartition(copy);
                    long end = System.nanoTime() - start;
                    Class<? extends CanPartition> aClass = canPartition.getClass();
                    map.put(aClass, map.getOrDefault(aClass, 0L) + end);
                }
            }
        }
        List<Class<? extends CanPartition>> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.comparing(Class::toString));
        for (Class<? extends CanPartition> key : keys) {
            System.out.println(key + " " + (map.get(key)/1_000_000.0) + "ms");
        }
    }
}
