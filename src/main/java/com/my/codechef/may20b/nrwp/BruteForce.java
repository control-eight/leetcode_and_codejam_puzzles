package com.my.codechef.may20b.nrwp;

import java.io.IOException;
import java.util.*;

public class BruteForce {

    public static Result solve(int[][] M, int[][] P) throws IOException {
        int V = 0;
        int[] signs = new int[P.length];
        Arrays.fill(signs, 1);

        Map<Pair, Index> ps = new HashMap<>();
        Map<Integer, Pair> reverse = new HashMap<>();
        for (int i = 0; i < P.length; i++) {
            int[] ints = P[i];
            ps.put(new Pair(ints[0], ints[1]), new Index(i, ints[2]));
            reverse.put(i, new Pair(ints[0], ints[1]));
        }
        long v = traverse(M, ps, signs);
        Result result = backtrack(M, ps, reverse, signs, -1, v);
        List<Integer> l = new ArrayList<>();
        for (int sign : result.signs) {
            l.add(sign);
        }
        return result;
    }

    private static Result backtrack(int[][] M, Map<Pair, Index> ps, Map<Integer, Pair> reverse,
                                    int[] signs, int i, long v) {
        if (i >= 0 && signs[i] != 1) {
            Pair point = reverse.get(i);
            Index index = ps.get(point);
            v += 2 * M[point.x - 1][point.y - 1] * index.p * signs[index.i];
            int[][] d = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] ints : d) {
                Pair pp = new Pair(point.x + ints[0], point.y + ints[1]);
                if (ps.containsKey(pp)) {
                    v += 2 * index.p * ps.get(pp).p * signs[index.i] * signs[ps.get(pp).i];
                }
            }
        }
        Result vr = new Result(v, Arrays.copyOf(signs, signs.length));
        if (i < ps.size() - 1) {
            signs[i + 1] = 1;
            Result r1 = backtrack(M, ps, reverse, signs, i + 1, v);
            signs[i + 1] = -1;
            Result r2 = backtrack(M, ps, reverse, signs, i + 1, v);
            signs[i + 1] = 1;

            if (r1.V > vr.V) {
                vr = r1;
            }
            if (r2.V > vr.V) {
                vr = r2;
            }
        }
        return vr;
    }

    private static long traverse(int[][] M, Map<Pair, Index> ps, int[] signs) {
        long v = 0;
        Set<Pair> seen = new HashSet<>();
        Set<Edge> seenLinks = new HashSet<>();
        for (Map.Entry<Pair, Index> entry : ps.entrySet()) {
            Pair point = entry.getKey();
            if (seen.add(point)) {
                Index index = entry.getValue();
                v += M[point.x - 1][point.y - 1] * index.p * signs[index.i];
                int[][] d = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
                for (int[] ints : d) {
                    Pair pp = new Pair(point.x + ints[0], point.y + ints[1]);
                    if (ps.containsKey(pp) && seenLinks.add(new Edge(point, pp))) {
                        v += index.p * ps.get(pp).p * signs[index.i] * signs[ps.get(pp).i];
                    }
                }
            }
        }
        return v;
    }

    private static class Pair {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Edge {
        private Pair left;
        private Pair right;
        private Set<Pair> set = new HashSet<>();

        public Edge(Pair left, Pair right) {
            this.left = left;
            this.right = right;
            this.set.add(left);
            this.set.add(right);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(set, edge.set);
        }

        @Override
        public int hashCode() {
            return Objects.hash(set);
        }
    }

    private static class Index {
        private int i;
        private int p;

        public Index(int i, int p) {
            this.i = i;
            this.p = p;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return i == index.i;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i);
        }
    }

    public static class Result {
        long V;
        int[] signs;

        public Result(long v, int[] signs) {
            V = v;
            this.signs = signs;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return V == result.V &&
                    Arrays.equals(signs, result.signs);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(V);
            result = 31 * result + Arrays.hashCode(signs);
            return result;
        }
    }
}
