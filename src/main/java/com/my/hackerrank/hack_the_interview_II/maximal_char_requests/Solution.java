package com.my.hackerrank.hack_the_interview_II.maximal_char_requests;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getMaxCharCount' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> getMaxCharCount(String s, List<List<Integer>> queries) {
        // queries is a n x 2 array where queries[i][0] and queries[i][1] represents x[i] and y[i] for the ith query.
        s = s.toLowerCase();
        int[][] frequencies = new int[s.length()][26];
        frequencies[0][s.charAt(0) - 'a']++;
        for (int i = 1; i < s.length(); i++) {
            System.arraycopy(frequencies[i - 1], 0, frequencies[i], 0, 26);
            frequencies[i][s.charAt(i) - 'a']++;
        }

        List<Integer> result = new ArrayList<>(queries.size());
        for (List<Integer> query : queries) {
            int[] diff = query.get(0) == 0? frequencies[query.get(1)]:
                    diff(frequencies[query.get(1)], frequencies[query.get(0) - 1]);
            result.add(getMax(s, diff));
        }
        return result;
    }

    private static int[] diff(int[] frequency, int[] frequency1) {
        int[] f = new int[frequency.length];
        for (int i = 0; i < frequency.length; i++) {
            f[i] = frequency[i] - frequency1[i];
        }
        return f;
    }

    private static int getMax(String s, int[] count) {
        int max = 0;
        int maxIndex = 0;
        for(int i = 0; i < count.length; i++) {
            if (count[i] > 0 && i >= maxIndex) {
                maxIndex = i;
                max = count[i];
            }
        }
        return max;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> query = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                query.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = Result.getMaxCharCount(s, query);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

