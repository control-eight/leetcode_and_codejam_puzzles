package com.my.kickstart.y2019.roundC.I_wiggle_walk;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            //5 3 6 2 3
            int N = in.nextInt();
            int R = in.nextInt();
            int C = in.nextInt();
            int posR = in.nextInt();
            int posC = in.nextInt();
            String commands = in.next();
            System.out.print("Case #" + i + ": ");
            solve(R, C, posR, posC, commands);
        }
    }

    private static int maxC = 0;
    private static boolean debug = false;

    private static void solve(int R, int C, int posR, int posC, String commands) {
        maxC = C;
        UnionFind vertical = new UnionFind();
        UnionFind horizontal = new UnionFind();

        Set<Integer> visited = new HashSet<>();
        visited.add(translate(posR, posC));

        debug(null);
        for (int i = 0; i < commands.length(); i++) {
            debug(commands.charAt(i));
            switch (commands.charAt(i)) {
                case 'N': {
                    posR--;
                    if (visited.contains(translate(posR, posC))) {
                        posR = vertical.findInterval(vertical.find(posR, posC), posR, posC, false)[0];
                        posR = Math.max(posR - 1, 1);
                    }
                    break;
                }
                case 'E': {
                    posC++;
                    if (visited.contains(translate(posR, posC))) {
                        posC = horizontal.findInterval(horizontal.find(posR, posC), posR, posC, true)[1];
                        posC = Math.min(posC + 1, C);
                    }
                    break;
                }
                case 'S': {
                    posR++;
                    if (visited.contains(translate(posR, posC))) {
                        posR = vertical.findInterval(vertical.find(posR, posC), posR, posC, false)[1];
                        posR = Math.min(posR + 1, R);
                    }
                    break;
                }
                case 'W': {
                    posC--;
                    if (visited.contains(translate(posR, posC))) {
                        posC = horizontal.findInterval(horizontal.find(posR, posC), posR, posC, true)[0];
                        posC = Math.max(posC - 1, 1);
                    }
                    break;
                }
            }
            debug(posR + " " + posC);
            if (!visited.contains(translate(posR, posC))) {
                visited.add(translate(posR, posC));
                //join
                if (posR > 1 && visited.contains(translate(posR - 1, posC))) {
                    vertical.union(posR - 1, posC, posR, posC, false);
                }
                if (posC < C && visited.contains(translate(posR, posC + 1))) {
                    horizontal.union(posR, posC + 1, posR, posC, true);
                }
                if (posR < R && visited.contains(translate(posR + 1, posC))) {
                    vertical.union(posR + 1, posC, posR, posC, false);
                }
                if (posC > 1  && visited.contains(translate(posR, posC - 1))) {
                    horizontal.union(posR, posC - 1, posR, posC, true);
                }
            }
        }
        System.out.println(posR + " " + posC);
    }

    private static void debug(Object o) {
        if (debug) {
            if (o == null) {
                System.out.println();
            } else {
                System.out.println(o);
            }
        }
    }

    private static int translate(int R, int C) {
        return (R - 1) * maxC + C;
    }

    private static class UnionFind {

        private Map<Integer, Integer> parents;

        private Map<Integer, int[]> intervals;

        public UnionFind() {
            parents = new HashMap<>();
            this.intervals = new HashMap<>();
        }

        public int[] findInterval(int parent, int posR, int posC, boolean horizontal) {
            if (!intervals.containsKey(parent)) {
                if (horizontal) {
                    intervals.put(parent, new int[]{posC, posC});
                } else {
                    intervals.put(parent, new int[]{posR, posR});
                }
            }
            return intervals.get(parent);
        }

        public int find(int index) {
            if (!parents.containsKey(index)) {
                parents.put(index, index);
            }
            if (parents.get(index) == index) return index;
            int parent = find(parents.get(index));
            parents.put(index, parent);
            return parent;
        }

        public int find(int r, int c) {
            return find(translate(r, c));
        }

        public int union(int r1, int c1, int r2, int c2, boolean horizontal) {
            int index1 = translate(r1, c1);
            int index2 = translate(r2, c2);

            int parent1 = find(index1);
            int parent2 = find(index2);
            this.parents.put(parent1, parent2);

            this.findInterval(parent1, r1, c1, horizontal);
            this.findInterval(parent2, r2, c2, horizontal);

            this.intervals.get(parent2)[0] = Math.min(this.intervals.get(parent1)[0], this.intervals.get(parent2)[0]);
            this.intervals.get(parent2)[1] = Math.max(this.intervals.get(parent1)[1], this.intervals.get(parent2)[1]);

            return parent1 != parent2? 1: 0;
        }
    }
}
