package com.my.leetcode.campus_bikes;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int[] res = new Solution().assignBikes(new int[][] {{0,0}, {1,1}, {2,0}}, new int[][] {{1,0}, {2,2}, {2,1}});
        System.out.println(Arrays.toString(res));
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[][][] workersSortedBikes = sortDistancesForEachWorker(workers, bikes);

        PriorityQueue<int[]> min = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        int[] bikePointer = new int[workers.length];
        for (int i = 0; i < workersSortedBikes.length; i++) {
            min.offer(new int[] {i, workersSortedBikes[i][0][0], workersSortedBikes[i][0][1]});
            bikePointer[i] = 0;
        }

        Map<Integer, Integer> pairs = new HashMap<>();
        boolean[] bikesGiven = new boolean[bikes.length];
        while(pairs.size() < workers.length) {
            int[] triple = min.poll();
            if (!bikesGiven[triple[1]]) {
                pairs.put(triple[0], triple[1]);
                bikesGiven[triple[1]] = true;
            } else {
                bikePointer[triple[0]]++;
                min.offer(new int[] {triple[0], workersSortedBikes[triple[0]][bikePointer[triple[0]]][0],
                        workersSortedBikes[triple[0]][bikePointer[triple[0]]][1]});
            }
        }
        return convertToResult(workers, pairs);
    }

    private int[][][] sortDistancesForEachWorker(int[][] workers, int[][] bikes) {
        int[][][] workersSortedBikes = new int[workers.length][bikes.length][2];
        for (int i = 0; i < workers.length; i++) {
            int[][] distances = bikeDistances(workers[i], bikes);
            Arrays.sort(distances, Comparator.comparingInt(o -> o[1]));
            workersSortedBikes[i] = distances;
        }
        return workersSortedBikes;
    }

    private int[] convertToResult(int[][] workers, Map<Integer, Integer> pairs) {
        int[] result = new int[workers.length];
        for (int i = 0; i < workers.length; i++) {
            result[i] = pairs.get(i);
        }
        return result;
    }

    private int[][] bikeDistances(int[] worker, int[][] bikes) {
        int[][] distances = new int[bikes.length][2];
        for (int i = 0; i < bikes.length; i++) {
            distances[i] = new int[] {i, distance(worker, bikes[i])};
        }
        return distances;
    }

    private int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}