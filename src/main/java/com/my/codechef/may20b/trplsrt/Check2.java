package com.my.codechef.may20b.trplsrt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Check2 {

    // Driver code
    public static void main(String[] args) throws IOException {
        Random random = new Random(5555);
        for (int n = 3; n <= 300; n++) {
            System.out.println("n = " + n);
            for (int i = 0; i < 5000; i++) {
                Integer[] r = Check.generateRandom(n, random);
                int r1 = Codechef.solve(Arrays.copyOf(r, r.length), r.length / 2);
                boolean solvable = BruteForce.solve2(Arrays.copyOf(r, r.length));
                if ((r1 == -1 || r1 > r.length / 2) && solvable) {
                    System.out.println("!!!1");
                    System.out.println(Arrays.toString(r));
                } else if (r1 > -1 && !solvable) {
                    System.out.println("!!!2");
                    System.out.println(Arrays.toString(r));
                }
            }
        }
        Codechef.finish();
    }
}
