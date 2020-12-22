package com.my.codechef.may20b.sortvs;

import java.util.*;

public class Сheck {

    private static int solve(int[] permutation, int[][] swap) {
        boolean[][] M = new boolean[permutation.length][permutation.length];
        for (int[] ints : swap) {
            M[ints[0] - 1][ints[1] - 1] = true;
            M[ints[1] - 1][ints[0] - 1] = true;
        }
        Result result = solveInternal(permutation, M, new HashSet<>(), 0,
                new ArrayList<>(), checkSorted(permutation), checkSorted(permutation));
        /*for (List<Integer> move : result.moves) {
            System.out.println((move.get(0) + 1) + " " + (move.get(1) + 1));
        }*/
        return result.min;
    }

    private static Result solveInternal(int[] permutation, boolean[][] M, Set<Key> visited, int result,
                                        List<List<Integer>> moves, int untilSorted, int untilSortedStart) {
        if (result >= permutation.length) return new Result(Integer.MAX_VALUE, null);
        if (!visited.add(new Key(permutation, result))) return new Result(Integer.MAX_VALUE, null);
        if (result - (untilSortedStart - untilSorted) > 2) return new Result(Integer.MAX_VALUE, null);
        if (untilSorted == 0) return new Result(result, moves);

        Result min = new Result(Integer.MAX_VALUE, null);
        for (int i = 0; i < permutation.length; i++) {
            for (int j = i + 1; j < permutation.length; j++) {
                int[] copy = Arrays.copyOf(permutation, permutation.length);
                swapIndex(copy, i, j);
                //List<List<Integer>> mm = new ArrayList<>(moves);
                //mm.add(Arrays.asList(i, j));
                Result tmp;
                if (!M[i][j]) {
                    tmp = solveInternal(copy, M, visited, result + 1, moves, checkSorted(copy), untilSortedStart);
                } else {
                    tmp = solveInternal(copy, M, visited, result, moves, checkSorted(copy), untilSortedStart);
                }
                if (tmp.min < min.min) {
                    min = tmp;
                }
            }
        }
        return min;
    }

    private static class Result {
        private int min;
        private List<List<Integer>> moves;
        public Result(int min, List<List<Integer>> moves) {
            this.min = min;
            this.moves = moves;
        }
    }

    private static int checkSorted(int[] permutation) {
        int result = 0;
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                result++;
            }
        }
        return result;
    }

    private static void swapIndex(int[] permutation, int i, int j) {
        int tmp = permutation[j];
        permutation[j] = permutation[i];
        permutation[i] = tmp;
    }

    private static class Key {
        private int[] permutation;
        private int result;

        public Key(int[] permutation, int result) {
            this.permutation = permutation;
            this.result = result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return result == key.result &&
                    Arrays.equals(permutation, key.permutation);
        }

        @Override
        public int hashCode() {
            int result1 = Objects.hash(result);
            result1 = 31 * result1 + Arrays.hashCode(permutation);
            return result1;
        }
    }

    public static void main(String[] args) {
        //runTest1();
        randomTest();
        //System.out.println(solve(new int[] {3, 6, 7, 4, 1, 5, 2}, new int[][] {{2, 4}, {3, 6}}));
        //System.out.println(solve(new int[] {2, 5, 6, 3, 1, 4}, new int[][] {{2, 4}, {5, 6}, {1, 3}}));
        //System.out.println(solve(new int[] {4, 3, 5, 7, 1, 2, 6}, new int[][] {{3, 4}, {3, 7}}));
        //System.out.println(solve(new int[] {5, 2, 3, 4, 1, 6, 7}, new int[][] {{1, 3}, {5, 7}, {2, 4}, {4, 7}, {2, 3}}));
        //System.out.println(solve(new int[] {9, 6, 7, 1, 2, 5, 8, 3, 4}, new int[][] {{2, 3}, {5, 9}, {1, 8}}));
        //System.out.println(solve(new int[] {2, 5, 6, 3, 1, 4}, new int[][] {{2, 4}, {5, 6}, {1, 3}}));
    }

    private static void runTest1() {
        List<Value> values = test1();
        for (Value value : values) {
            System.out.println(BestBruteForce.solve(value.permutation, value.swap));;
        }
    }

    private static void randomTest() {
        //List<Value> values = generate(20000, 18, 10, new Random(5555));

        for (int n = 5; n <= 18; n++) {
            for (int m = 0; m <= 4; m++) {
                System.out.println("n = " + n + " m = " + m);
                List<Value> values = generate(5000, n, m, new Random(5555));
                for (int i = 0; i < values.size(); i++) {
                    Value value = values.get(i);
                    //int r1 = BestBruteForce.solve(Arrays.copyOf(value.permutation, value.permutation.length), value.swap);
                    int r1 = solve(Arrays.copyOf(value.permutation, value.permutation.length), value.swap);
                    int r2 = (int) Codechef.solve(Arrays.copyOf(value.permutation, value.permutation.length), value.swap);
                    if (r1 != r2) {
                        System.out.println("Correct: " + r1 + " != " + r2);
                        System.out.println(value);
                        //break;
                    }
                }
            }
        }
    }

    private static List<Value> generate(int l, int n, int m, Random random) {
        System.out.println("length = " + l + ", N = " + n + ", M = " + m);
        List<Value> result = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            result.add(new Value(generateRandom(n, random), generatePairs(n, m, random)));
        }
        return result;
    }

    private static int[][] generatePairs(int n, int m, Random random) {
        int[][] result = new int[m][2];
        Set<List> generated = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int i1 = (int) (random.nextDouble() * n);
            int i2 = (int) (random.nextDouble() * n);
            while (i1 == i2 || !generated.add(Arrays.asList(i1, i2))) {
                i1 = (int) (random.nextDouble() * n);
                i2 = (int) (random.nextDouble() * n);
            }
            if (i2 < i1) {
                int tmp = i1;
                i1 = i2;
                i2 = tmp;
            }
            result[i][0] = i1 + 1;
            result[i][1] = i2 + 1;
        }
        return result;
    }

    private static List<Value> test1() {
        return Arrays.asList(
                    new Value(new int[] {2, 3, 1}, new int[][] {{2, 3}}),
                    new Value(new int[] {1, 3, 2}, new int[][] {{2, 3}}),
                    new Value(new int[] {3, 1, 4, 2}, new int[][] {{2, 3}}),
                    new Value(new int[] {3, 1, 4, 2}, new int[][] {{1, 2}}),
                    new Value(new int[] {2, 4, 3, 1}, new int[][] {{4, 1}}),
                    new Value(new int[] {3, 2, 4, 1}, new int[][] {{1, 3}, {3, 4}}),
                    new Value(new int[] {3, 1, 2, 4}, new int[][] {{1, 3}, {2, 3}}),
                    new Value(new int[] {4, 3, 5, 2, 1}, new int[][] {{1, 3}}),
                    new Value(new int[] {4, 1, 5, 2, 3}, new int[][] {{1, 3}})
            );
    }

    private static class Value {
        private int[] permutation;
        private int[][] swap;

        public Value(int[] permutation, int[][] swap) {
            this.permutation = permutation;
            this.swap = swap;
        }

        @Override
        public String toString() {
            String s1 = "permutation= " + Arrays.toString(permutation);

            StringBuilder s2 = new StringBuilder(" swap = ");
            for (int[] ints : swap) {
                s2.append(Arrays.toString(ints));
            }
            return s1 + s2;
        }
    }

    // Function to return the next random number
    static int getNum(ArrayList<Integer> v, Random random) {
        // Size of the vector
        int n = v.size();

        // Make sure the number is within
        // the index range
        int index = (int) (random.nextDouble() * n);

        // Get random number from the vector
        int num = v.get(index);

        // Remove the number from the vector
        v.set(index, v.get(n - 1));
        v.remove(n - 1);

        // Return the removed number
        return num;
    }

    // Function to generate n
    // non-repeating random numbers
    static int[] generateRandom(int n, Random random) {
        ArrayList<Integer> v = new ArrayList<>(n);

        // Fill the vector with the values
        // 1, 2, 3, ..., n
        for (int i = 0; i < n; i++)
            v.add(i + 1);

        int[] result = new int[v.size()];
        int i = 0;
        while (v.size() > 0) {
            result[i] = getNum(v, random);
            i++;
        }
        return result;
    }
}
