package com.my.leetcode.cat_mouse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution5 {

    public static void main(String[] args) {
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}
        }));
        //0
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {3},{3},{3},{0,1,2}
        }));
        //2
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {2,3,4},{2,4},{0,1,4},{0,4},{0,1,2,3}
        }));
        //2
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {3,4},{3,5},{3,6},{0,1,2},{0,5,6},{1,4},{2,4}
        }));
        //1
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {1,2},{0},{0}
        }));
        //1
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {3,4},{3,5},{3},{0,1,2},{0,5},{1,4}
        }));
        //1
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {6},{4},{9},{5},{1,5},{3,4,6},{0,5,10},{8,9,10},{7},{2,7},{6,7}
        }));
        //1
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {6},{4},{9},{5},{1,5},{3,4,6},{0,5,10},{8,9,10},{7},{2,7},{6,7}
        }));
        //1
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {6},{4,11},{9,12},{5},{1,5,11},{3,4,6},{0,5,10},{8,9,10},{7},{2,7,12},{6,7},{1,4},{2,9}
        }));
        //1
        System.out.println(new Solution5().catMouseGame(new int[][] {
                {3,4,6,7,9,15,16,18},{4,5,8,19},{3,4,6,9,17,18},{0,2,11,15},{0,1,10,6,2,12,14,16},{1,10,7,9,15,17,18},{0,10,4,7,9,2,11,12,13,14,15,17,19},{0,10,5,6,9,16,17},{1,9,14,15,16,19},{0,10,5,6,7,8,2,11,13,15,16,17,18},{4,5,6,7,9,18},{3,6,9,12,19},{4,6,11,15,17,19},{6,9,15,17,18,19},{4,6,8,15,19},{0,3,5,6,8,9,12,13,14,16,19},{0,4,7,8,9,15,17,18,19},{5,6,7,9,2,12,13,16},{0,10,5,9,2,13,16},{1,6,8,11,12,13,14,15,16}
        }));
        //1
    }

    public int catMouseGame(int[][] graph) {
        int r = c(graph);
        int[][][] solutions = new int[graph.length][graph.length][2];
        for (int[][] solution : solutions) {
            for (int[] ints : solution) {
                Arrays.fill(ints, -1);
            }
        }
        boolean[][][] visitedStates = new boolean[graph.length][graph.length][2];
        visitedStates[1][2][1] = true;
        int r2 = remap(play(graph, true, 1, 2, visitedStates, solutions));
        return r == 1? 1: r2;
    }

    private int remap(int play) {
        switch (play) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 2;
        }
        throw new RuntimeException();
    }

    public int play(int[][]graph, boolean mouseMove, int mousePos, int catPos, boolean[][][] visitedStates, int[][][] cache) {
        if (mousePos == 0) return 0;
        if (catPos == mousePos) return 2;
        if (cache[mousePos][catPos][mouseMove? 0: 1] != -1) {
            return cache[mousePos][catPos][mouseMove? 0: 1];
        }
        int res = mouseMove? Integer.MAX_VALUE: -1;
        int pos = mouseMove? mousePos: catPos;
        for (int node : graph[pos]) {
            if (mouseMove || node != 0) {
                int l;
                if (visitedStates[mouseMove? node: mousePos][mouseMove? catPos: node][mouseMove? 0: 1]) {
                    l = 1;
                } else {
                    visitedStates[mouseMove? node: mousePos][mouseMove? catPos: node][mouseMove? 0: 1] = true;
                    l = play(graph, !mouseMove, mouseMove ? node : mousePos, mouseMove ? catPos : node, visitedStates, cache);
                    visitedStates[mouseMove? node: mousePos][mouseMove? catPos: node][mouseMove? 0: 1] = false;
                }
                res = mouseMove? Math.min(res,l): Math.max(res, l);
            }
        }
        cache[mousePos][catPos][mouseMove ? 0 : 1] = res;
        return res;
    }

    private int c(int[][] graph) {

        Set<Integer> mouseVisited = new HashSet<>();
        Set<Integer> mouse = new HashSet<>();
        mouseVisited.add(1);
        mouse.add(1);

        Set<Integer> catVisited = new HashSet<>();
        Set<Integer> cat = new HashSet<>();
        catVisited.add(0);
        catVisited.add(2);
        cat.add(2);

        while (true) {

            if (mouse.isEmpty()) {
                return 2;
            }

            Set<Integer> newMouse = new HashSet<>();
            for (Integer integer : mouse) {
                for (int i : graph[integer]) {
                    if (mouseVisited.add(i) && !cat.contains(i)) {
                        if (i == 0) return 1;
                        newMouse.add(i);
                    }
                }
            }
            mouse = newMouse;

            mouse.removeAll(cat);
            if (mouse.isEmpty()) {
                return 2;
            }

            Set<Integer> newCat = new HashSet<>();
            for (Integer integer : cat) {
                for (int i : graph[integer]) {
                    if (catVisited.add(i)) {
                        newCat.add(i);
                    }
                }
            }
            cat = newCat;
            mouse.removeAll(cat);
        }
    }
}
