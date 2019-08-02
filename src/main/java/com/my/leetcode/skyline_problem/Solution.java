package com.my.leetcode.skyline_problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        /*System.out.println(new Solution().getSkyline(new int[][] {
                {0,2,3}, {2,5,3}
        }));
        //[[0,3],[5,0]]
        System.out.println(new Solution().getSkyline(new int[][] {
                {1,2,1}, {1,2,2}, {1,2,3}
        }));
        //[[1,3],[2,0]]
        System.out.println(new Solution().getSkyline(new int[][] {
                {2,4,7}, {2,4,5}, {2,4,6}
        }));
        //[[2,7],[4,0]]
        System.out.println(new Solution().getSkyline(new int[][] {
                {2,4,7}, {2,4,5}, {2,4,6}
        }));
        //[[2,7],[4,0]]
        System.out.println(new Solution().getSkyline(new int[][] {
                {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}
        }));*/
        //[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
        System.out.println(new Solution().getSkyline(new int[][] {
                {2,4,70}, {3,8,30}, {6,100,41}, {7,15,70}, {10,30,102}, {15,25,76}, {60,80,91}, {70,90,72}, {85,120,59}
        }));
        //[[2,70],[4,30],[6,41],[7,70],[10,102],[30,41],[60,91],[80,72],[90,59],[120,0]]
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings.length == 0) return result;

        int[] building = {buildings[0][0], buildings[0][1], buildings[0][2], 0};
        int i = 0;
        while (true) {
            int[] next = giveMeHigher(buildings, building, i);
            add(result, building);
            if (next == null) {
                next = giveMeLowerMax(buildings, building);
                if (next == null) {
                    result.add(Arrays.asList(building[1], 0));
                    next = giveAfter(buildings, building, i);
                }
            }
            if (next == null) return result;
            building = next;
            i = Math.max(i, building[3]);
        }
    }

    private void add(List<List<Integer>> result, int[] building) {
        if (result.isEmpty() || result.get(result.size() - 1).get(1) != building[2]) {
            if (!result.isEmpty() && result.get(result.size() - 1).get(0) == building[0]) {
                result.remove(result.size() - 1);
            }
            result.add(Arrays.asList(building[0], building[2]));
        }
    }

    private int[] giveMeHigher(int[][] buildings, int[] building, int i) {
        for (int j = i + 1; j < buildings.length; j++) {
            if (buildings[j][1] >= building[0]) {
                int[] ints = buildings[j];
                if (ints[0] > building[1]) {
                    break;
                }
                if (ints[2] > building[2]) {
                    return new int[]{ints[0], ints[1], ints[2], j};
                }
            }
        }
        return null;
    }

    private int[] giveMeLowerMax(int[][] buildings, int[] building) {
        int[] result = null;
        int maxH = 0;
        for (int j = 0; j < buildings.length; j++) {
            if (buildings[j][1] >= building[0] && buildings[j] != building) {
                int[] ints = buildings[j];
                if (ints[0] > building[1]) {
                    break;
                }
                if (ints[2] <= building[2] && ints[1] > building[1]) {
                    if (ints[2] > maxH) {
                        maxH = ints[2];
                        result = new int[]{building[1], ints[1], ints[2], j};
                    }
                }
            }
        }
        return result;
    }

    private int[] giveAfter(int[][] buildings, int[] building, int i) {
        for (int j = i + 1; j < buildings.length; j++) {
            if (buildings[j][1] >= building[0]) {
                int[] ints = buildings[j];
                if (ints[0] > building[1]) {
                    return new int[]{ints[0], ints[1], ints[2], j};
                }
            }
        }
        return null;
    }
}
