package com.my.codechef.may20b.corus;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef {

    private static Object solve(String S, List<Integer> queries) {
        int[] freq = new int[26];
        for (int i = 0; i < S.length(); i++) {
            freq[S.charAt(i) - 'a']++;
        }
        Object[] result = new Object[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            int sum = 0;
            for (int f : freq) {
                int diff = queries.get(i) - f;
                sum += diff > 0? 0: -diff;
            }
            result[i] = sum;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            int N = readInt();
            int Q = readInt();
            String S = readString(N);
            List<Integer> queries = new ArrayList<>(Q);
            for (int j = 0; j < Q; j++) {
                queries.add(readInt());
            }
            Object result = solve(S, queries);
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
        if (result instanceof Object[]) {
            for (Object obj : ((Object[]) result)) {
                print(bufferedWriter, obj);
            }
        } else if (result instanceof Collection) {
            for (Object obj : ((Collection) result)) {
                bufferedWriter.write(obj + " ");
            }
            bufferedWriter.newLine();
        } else {
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
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

    private static String readString(int length) {
        String result = String.valueOf(buffer, pos, length);
        pos += length + 1;
        return result;
    }
}