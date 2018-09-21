package com.my.leetcode.random_pick_with_weight;

import java.util.Random;

public class Solution {

    private int sum;

    private int[] prefixSum;

    private Random random;

    public Solution(int[] w) {
        prefixSum = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            prefixSum[i] = sum;
            sum += w[i];
        }
        random = new Random();
    }

    public int pickIndex() {
        double rnd = random.nextDouble() * sum;
        return binarySearch(prefixSum, 0, prefixSum.length, rnd);
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
        Solution s = new Solution(new int[]{1,3});
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
        System.out.println(s.pickIndex());
    }
}
