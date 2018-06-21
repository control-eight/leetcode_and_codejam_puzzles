package com.my.hackerrank.week_of_code_38.minute_to_win_it;

/**
 * @author abykovsky
 * @since 6/20/18
 */
import java.io.*;
import java.util.*;

public class Solution {

    // Complete the minuteToWinIt function below.
    static int minuteToWinIt(int[] a, int k) {
        int max = 0;
        Map<Long, Integer> countMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            Long key = (long) a[i] - (long) i * k;
            countMap.putIfAbsent(key, 0);
            int value = countMap.get(key) + 1;
            countMap.put(key, value);
            max = Math.max(value, max);
        }
        return a.length - max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = minuteToWinIt(a, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
