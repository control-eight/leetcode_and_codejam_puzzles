package com.my.hackerrank.week_of_code_38.which_section;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author abykovsky
 * @since 6/18/18
 */
public class Solution {

    // Complete the whichSection function below.
    static int whichSection(int n, int k, int[] a) {
        // Return the section number you will be assigned to assuming you are student number k.
        int s = 0;
        for (int i = 0; i < a.length; i++) {
            if (k > s && k <= s + a[i]) {
                return i + 1;
            } else {
                s += a[i];
            }
        }
        return a.length;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nkm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nkm[0]);

            int k = Integer.parseInt(nkm[1]);

            int m = Integer.parseInt(nkm[2]);

            int[] a = new int[m];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < m; i++) {
                int aItem = Integer.parseInt(aItems[i]);
                a[i] = aItem;
            }

            int result = whichSection(n, k, a);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
