package com.my.hackerrank.hack_the_interview_II.maximal_char_requests;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result2 {

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
        List<Integer> result = new ArrayList<>(queries.size());

        double sqrt = (int) Math.sqrt(s.length());
        int sqrtInt = (int) sqrt;
        Pair[] buckets = new Pair[s.length() / sqrtInt];
        int n = 0;
        for (int i = 0; i <= s.length() - sqrtInt;) {
            buckets[n] = getMax(s, i, i + sqrtInt - 1);
            n++;
            i = i + sqrtInt;
        }

        for (List<Integer> query : queries) {
            List<Pair> pairs = new ArrayList<>();
            int start = (int) Math.ceil(query.get(0) / sqrt);
            int end = (int) Math.floor(query.get(1) / sqrt);

            if (start > end) {
                result.add(getMax(s, query.get(0), query.get(1)).count);
            } else {
                pairs.add(getMax(s, query.get(0), start * sqrtInt - 1));
                for (int i = start; i < end; i++) {
                    pairs.add(buckets[i]);
                }
                pairs.add(getMax(s, end * sqrtInt, query.get(1)));
                result.add(getMax(pairs));
            }
        }
        System.out.println(result);
        return result;
    }

    private static Pair getMax(String s, int lo, int hi) {
        int[] count = new int[26];
        Pair result = new Pair(0, 0);
        //if (lo > hi) return result;
        for (int i = lo; i <= hi; i++) {
            count[s.charAt(i) - 'a']++;
            if (s.charAt(i) - 'a' >= result.c) {
                result.count = count[s.charAt(i) - 'a'];
                result.c = s.charAt(i) - 'a';
            }
        }
        return result;
    }

    private static int getMax(List<Pair> pairs) {
        int[] count = new int[26];
        Pair result = new Pair(0, 0);
        for (Pair pair : pairs) {
            count[pair.c] += pair.count;
            if (pair.c >= result.c) {
                result.count = count[pair.c];
                result.c = pair.c;
            }
        }
        return result.count;
    }

    private static class Pair {
        private int c;
        private int count;
        private Pair(int c, int count) {
            this.c = c;
            this.count = count;
        }

        @Override
        public String toString() {
            return c + ":" + count;
        }
    }
}

public class Solution2 {
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

