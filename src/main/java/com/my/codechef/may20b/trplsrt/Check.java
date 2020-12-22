package com.my.codechef.may20b.trplsrt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Check {

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
    static Integer[] generateRandom(int n, Random random) {
        ArrayList<Integer> v = new ArrayList<>(n);

        // Fill the vector with the values
        // 1, 2, 3, ..., n
        for (int i = 0; i < n; i++)
            v.add(i + 1);

        Integer[] result = new Integer[v.size()];
        int i = 0;
        while (v.size() > 0) {
            result[i] = getNum(v, random);
            i++;
        }
        return result;
    }

    // Driver code
    public static void main(String[] args) throws IOException {
        //test1();
        Random random = new Random(5555);
        for (int n = 6; n <= 7; n++) {
            System.out.println("n = " + n);
            for (int i = 0; i < 200; i++) {
                Integer[] r = generateRandom(n, random);
                check(r);
                System.out.println(i);
            }
        }
        Codechef.finish();
    }

    private static void check(Integer[] r) throws IOException {
        int r1 = Codechef.solve(Arrays.copyOf(r, r.length), r.length / 2);
        if (r1 == -1) return;

        BruteForce.Result solvable = BruteForce.solve(Arrays.copyOf(r, r.length));
        if (r1 == -1 && !solvable.isSolvable() || r1 == 0 && (solvable.isSolvable() && solvable.getMoves() == null))
            return;

        if (r1 > 0 && !solvable.isSolvable()) {
            System.out.println("Correct: unsolvable");
            return;
        }
        if (r1 == -1 || r1 > r.length / 2) {
            System.out.println("Correct: " + solvable.getMoves().size() + " vs " + r1);
            System.out.println(Arrays.toString(r));
            System.out.println(solvable);
        }
    }

    private static void test1() {
        System.out.println(BruteForce.solve(3, 2, 4, 1));
        System.out.println(BruteForce.solve(7, 2, 1, 3, 5, 4, 6));
        System.out.println(BruteForce.solve(3, 2, 6, 4, 5, 1, 7));
        System.out.println(BruteForce.solve(3, 2, 1, 5, 4));
        System.out.println(BruteForce.solve(3, 2, 1, 4));
        System.out.println(BruteForce.solve(3, 2, 1, 5, 4));
        System.out.println(BruteForce.solve(2, 3, 1));
        /*System.out.println(BruteForce.solve(4, 5, 6, 1, 2, 3));*/
        System.out.println(BruteForce.solve(7, 3, 5, 6, 2, 4, 1));
        System.out.println(BruteForce.solve(2, 1, 7, 5, 4, 3, 6));
        System.out.println(BruteForce.solve(5, 3, 7, 6, 1, 4, 2));
        System.out.println(BruteForce.solve(6, 4, 7, 5, 2, 1, 3));
        System.out.println(BruteForce.solve(6, 4, 7, 5, 2, 1, 3));
        System.out.println(BruteForce.solve(5, 3, 7, 6, 1, 4, 2));
        System.out.println(BruteForce.solve(3, 4, 1, 6, 7, 2, 5));
        System.out.println(BruteForce.solve(2, 1, 6, 5, 4, 7, 3));
    }
}
