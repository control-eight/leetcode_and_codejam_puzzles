package com.my.codechef.may20b.nrwp;


import java.io.IOException;
import java.util.*;

public class Check {


    public static void main(String[] args) throws IOException {
        //test1();
        test3();
        //generate();
    }

    private static void test1() throws IOException {
        BruteForce.Result r = BruteForce.solve(new int[][] {
                {-1000, 35},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1}
        }, new int[][] {
                {1, 1, 30}, {1, 2, 30},
                {2, 1, 10}, {2, 2, 10},
                {3, 1, 10}, {3, 2, 10},
                {4, 1, 10}, {4, 2, 10},
                {5, 1, 10}, {5, 2, 10},
        });
        print(r.V, r.signs);
    }

    private static void test2() throws IOException {
        BruteForce.Result r = BruteForce.solve(new int[][] {
                {1, -2},
                {7, -11},
        }, new int[][] {
                {1, 1, 4}, {1, 2, 2},
                {2, 1, 1}, {2, 2, 1},
        });
        print(r.V, r.signs);
    }

    private static void test3() throws IOException {
        BruteForce.Result r = BruteForce.solve(new int[][] {
                {2, -1},
                {-5, -2},
        }, new int[][] {
                {1, 1, 3}, {1, 2, 3},
                {2, 1, 4}, {2, 2, 1},
        });
        print(r.V, r.signs);
    }

    private static void generate() throws IOException {
        int size = 15000;
        int H = 2;
        int W = 2;
        int maxV = 12;
        int maxP = 5;
        int fillSize = Math.max(4, (int) (H * W * 0.7));
        Random random = new Random(5555);
        List<Pair> mList = generateM(H, W, maxV, maxP, size, fillSize, random);
        int c = 0;
        for (int t = 0; t < mList.size(); t++) {
            if (t == 307) continue;
            if (t != 2002) continue;
            Pair pair = mList.get(t);
            BruteForce.Result r1 = BruteForce.solve(pair.M, pair.P);
            Codechef.Result r2 = Codechef.solve(pair.M, pair.P);
            if (r1.V != r2.V) {
                c++;
                System.out.println("T = " + t);
                print(pair);
                System.out.println("r1 = ");
                print(r1.V, r1.signs);
                System.out.println("r2 = ");
                print(r2.V, r2.signs);
                break;
            }
            //System.out.println(t);
        }
        System.out.println("Incorrect: " + c);
    }

    private static List<Pair> generateM(int H, int W, int maxV, int maxP, int size, int fillSize, Random random) {
        List<Pair> result = new ArrayList<>();
        for (int n = 0; n < size; n++) {
            int[][] M = new int[H][W];
            int[][] P = new int[fillSize][3];

            Set<List<Integer>> indexes = new HashSet<>();
            for (int k = 0; k < fillSize; k++) {
                List<Integer> index = Arrays.asList((int) (random.nextDouble() * H), (int) (random.nextDouble() * W));
                while (!indexes.add(index)) {
                    index = Arrays.asList((int) (random.nextDouble() * H), (int) (random.nextDouble() * W));
                }
                P[k] = new int[] {index.get(0) + 1, index.get(1) + 1, checkZero(maxP, random)};
                M[index.get(0)][index.get(1)] = checkZero(maxV, random) * (random.nextDouble() > 0.5? 1: -1);
            }
            Arrays.sort(P, Arrays::compare);
            result.add(new Pair(M, P));
        }
        return result;
    }

    private static int checkZero(int maxV, Random random) {
        int r = (int) (random.nextDouble() * maxV);
        return r == 0? 1: r;
    }

    private static class Pair {
        private int[][] M;
        private int[][] P;

        public Pair(int[][] m, int[][] p) {
            M = m;
            P = p;
        }
    }

    private static void print(Pair p) {
        System.out.println("M = ");
        for (int[] ints : p.M) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("P = ");
        for (int[] ints : p.P) {
            System.out.println((ints[0] - 1) + " " + (ints[1] - 1) + " " + ints[2]);
        }
    }

    private static void print(long V, int[] signs) {
        System.out.println("V = " + V);
        System.out.println(Arrays.toString(signs));
    }
}
