package com.my.leetcode.cut_off_trees_golf_event;

import java.util.*;

public class Solution {

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest.get(0).get(0) == 0) return -1;

        PriorityQueue<Tree> f = new PriorityQueue<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                Integer height = forest.get(i).get(j);
                if (height > 1) {
                    f.offer(new Tree(height, new int[]{i,j}));
                }
            }
        }
        int result = 0;
        int[] prevPos = new int[]{0,0};
        while (!f.isEmpty()) {
            Tree tree = f.poll();
            int distance = distance(tree.pos, prevPos, forest);
            if (distance == -1) return -1;
            result += distance;
            prevPos = tree.pos;
        }
        return result;
    }

    private int distance(int[] pos, int[] prevPos, List<List<Integer>> forest) {
        Set<List<Integer>> visited = new HashSet<>();
        Queue<Next> toVisit = new LinkedList<>();
        toVisit.offer(new Next(0, Arrays.asList(prevPos[0], prevPos[1])));

        while (!toVisit.isEmpty()) {
            Next v = toVisit.poll();
            if (v.pos.get(0) == pos[0] && v.pos.get(1) == pos[1]) {
                return v.d;
            }
            int i = v.pos.get(0); int j = v.pos.get(1);
            if (visited.add(v.pos)) {
                if (i > 0) {
                    addToVisit(forest, toVisit, i - 1, j, v.d + 1);
                }
                if (i < forest.size() - 1) {
                    addToVisit(forest, toVisit, i + 1, j, v.d + 1);
                }
                if (j > 0) {
                    addToVisit(forest, toVisit, i, j - 1, v.d + 1);
                }
                if (j < forest.get(0).size() - 1) {
                    addToVisit(forest, toVisit, i, j + 1, v.d + 1);
                }
            }
        }
        return -1;
    }

    private void addToVisit(List<List<Integer>> forest, Queue<Next> toVisit, int i, int j, int d) {
        if (forest.get(i).get(j) >= 1) {
            toVisit.offer(new Next(d, Arrays.asList(i, j)));
        }
    }

    private static class Tree implements Comparable<Tree> {
        private int height;
        private int[] pos;

        public Tree(int height, int[] pos) {
            this.height = height;
            this.pos = pos;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(height, o.height);
        }
    }

    private static class Next {
        private int d;
        private List<Integer> pos;

        public Next(int d, List<Integer> pos) {
            this.d = d;
            this.pos = pos;
        }
    }
}
