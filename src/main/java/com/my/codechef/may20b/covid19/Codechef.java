package com.my.codechef.may20b.covid19;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    private static List<Integer> solve(List<Integer> points) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int series = 1;
        Collections.sort(points);
        for (int i = 1; i < points.size(); i++) {
            if (Math.abs(points.get(i - 1) - points.get(i)) <= 2) {
                series++;
            } else {
                min = Math.min(min, series);
                max = Math.max(max, series);
                series = 1;
            }
        }
        min = Math.min(min, series);
        max = Math.max(max, series);
        return Arrays.asList(min, max);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            int N = readInt();
            List<Integer> points = new ArrayList<>(N);
            for (int j = 0; j < N; j++) {
                points.add(readInt());
            }
            Object result = solve(points);
            print(bufferedWriter, result);
        }
        finish(bufferedWriter);
    }

    private static BufferedWriter init() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
        buffer = getChars(System.in);
        return bufferedWriter;
    }

    private static void print(BufferedWriter bufferedWriter, Object result) throws IOException {
        if (result instanceof Collection) {
            for (Object obj : ((Collection) result)) {
                bufferedWriter.write(obj + " ");
            }
        } else {
            bufferedWriter.write(String.valueOf(result));
        }
        bufferedWriter.newLine();
    }

    private static void finish(BufferedWriter bufferedWriter) throws IOException {
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
        int num = 0;
        while (pos < buffer.length) {
            char ch = buffer[pos++];
            if (ch < '0' || ch > '9') {
                break;
            } else {
                int digit = ch - '0';
                num = (num << 3) + (num << 1) + digit;
            }
        }
        return num;
    }
}