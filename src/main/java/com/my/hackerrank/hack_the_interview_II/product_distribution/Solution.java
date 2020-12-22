package com.my.hackerrank.hack_the_interview_II.product_distribution;

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

    private static final long MODULO = (long) (Math.pow(10, 9) + 7);

    /*
     * Complete the 'maxScore' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER m
     */

    public static int maxScore(List<Integer> a, int m) {
        Collections.sort(a);
        long sum = 0;
        long n = 1;
        int end = a.size() - (a.size() % m);
        for (int i = 0; i < end;) {
            for (int j = 0; j < m; j++) {
                sum += ((n * a.get(i)) % MODULO);
                i++;
            }
            sum %= MODULO;
            n++;
        }
        for (int i = end; i < a.size(); i++) {
            sum += (((n - 1) * a.get(i)) % MODULO);
        }
        sum %= MODULO;
        return (int) sum;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        buffer = getChars(System.in);

        int n = readInt();
        int m = readInt();

        List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            a.add(readInt());
        }
        long result = Result.maxScore(a, m);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    private static int pos;
    private static char[] buffer;

    private static char[] getChars(InputStream in) {
        try {
            //first char should be ASCII, and from proper encoding
            int firstByte = System.in.read();

            char[] buffer = new char[in.available()];
            new InputStreamReader(in).read(buffer, 0, buffer.length);

            char[] newBuffer = new char[buffer.length + 1];
            System.arraycopy(buffer, 0, newBuffer, 1, buffer.length);
            newBuffer[0] = (char) firstByte;
            return newBuffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int readInt() {
        long num = 0;
        while (pos < buffer.length) {
            char ch = buffer[pos++];
            if (ch < '0' || ch > '9') {
                break;
            } else {
                int digit = ch - '0';
                num = (num << 3) + (num << 1) + digit;
            }
        }
        return (int) num;
    }
}

