package com.my.leetcode.random_pick_with_weight;

import java.util.Random;

public class SolutionBst {

    private double[][] arr;

    private Random random;

    public SolutionBst(int[] w) {
        double sum = 0.0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
        }
        double avg = sum / w.length;
        arr = new double[w.length][2];

        for (int i = 0; i < w.length; i++) {




        }


        random = new Random();
    }

    public int pickIndex() {
        /*double rnd = random.nextDouble() * sum;*/
        /*return binarySearch(prefixSum, 0, prefixSum.length, rnd);*/
        return 0;
    }

    private int binarySearch(int[] prefixSum, int start, int end, double sum) {
        int mid = start + (end - start)/2;
        while (start < end - 1) {
            if (prefixSum[mid] == sum) return mid;
            else if (sum < prefixSum[mid]) return binarySearch(prefixSum, start, mid, sum);
            else return binarySearch(prefixSum, mid, end, sum);
        }
        return prefixSum[mid] > sum? mid - 1: mid;
    }

    public static void main(String[] args) {
        SolutionBst s = new SolutionBst(new int[]{1,3});
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
    }
}
