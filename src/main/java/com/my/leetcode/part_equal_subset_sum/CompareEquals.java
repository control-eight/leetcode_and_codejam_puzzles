package com.my.leetcode.part_equal_subset_sum;

import java.util.*;

public class CompareEquals {

    public static void main(String[] args) {
        runRandomTests();
    }

    private static void runRandomTests() {
        runGeneratorTest(new SolutionDPOpt(), new SolutionSuperOptB());
    }

    private static void runGeneratorTest(CanPartition trust, CanPartition check) {
        Random random = new Random(5555);
        int[] arr = new int[100];
        for (int i = 0; i < 10_000_000; i++) {
            for (int n = 0; n < arr.length; n++) {
                arr[n] = (int) (random.nextDouble() * 100) + 1;
            }
            int[] copy = Arrays.copyOf(arr, arr.length);
            boolean expected = trust.canPartition(copy);
            boolean actual = check.canPartition(copy);
            if (actual != expected) {
                System.out.println(actual + " != " + expected + " for " + check.getClass()
                        + " arr = " + Arrays.toString(arr));
            }
        }
    }
}
