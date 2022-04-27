package com.my.codechef.jan21b.antschef;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Codechef {

    private static Object solve(List<List<Long>> lines) {
        Map<Long, Long> all_pos_count = new HashMap<>();
        Map<Long, Long> all_pos = new HashMap<>();
        for (List<Long> line : lines) {
            for (int i = 0; i < line.size(); i++) {
                Long ant = line.get(i);
                long key = Math.abs(ant);
                all_pos_count.putIfAbsent(key, 0L);
                all_pos_count.put(key, all_pos_count.get(key) + 1);
                all_pos.putIfAbsent(key, 0L);
                if (ant > 0) {
                    all_pos.put(key, all_pos.get(key) + (line.size() - i - 1));
                } else {
                    all_pos.put(key, all_pos.get(key) + i);
                }
            }
        }

        Map<Long, List<Long>> excluded = new HashMap<>();
        for (int k = 0; k < lines.size(); k++) {
            List<Long> line = lines.get(k);
            for (int i = 0; i < line.size(); i++) {
                long key = Math.abs(line.get(i));
                if (all_pos_count.get(key) > 1) {
                    excluded.putIfAbsent((long) k, new ArrayList<>());
                    excluded.get((long) k).add(line.get(i));
                    line.remove(i);
                    i--;
                }
            }
        }

        long ans = 0;
        for (int k = 0; k < lines.size(); k++) {
            List<Long> line = lines.get(k);
            if (line.isEmpty()) {
                continue;
            }

            long positive_count = 0;
            long negative_count = 0;
            for (long ant : line) {
                if (ant > 0) {
                    positive_count++;
                }
                if (ant < 0) {
                    negative_count++;
                }
            }
            for (long ant : line) {
                if (ant < 0) {
                    ans += positive_count;
                }
            }
            if (excluded.containsKey((long) k)) {
                for (long ant : excluded.get((long) k)) {
                    if (ant < 0) {
                        int index = Collections.binarySearch(line, -ant - 1);
                        if (index != -1) {
                            if (index < 0) {
                                index = -(index + 1);
                            }
                            if (index == line.size()) {
                                index--;
                            }
                            if (line.get(index) >= Math.abs(ant)) {
                                index--;
                            }
                            if (index != -1 && line.get(index) > 0) {
                                ans += (index - negative_count + 1);
                            }
                        }
                    } else {
                        int index = Collections.binarySearch(line, -ant + 1);
                        if (index < 0) {
                            index = -(index + 1);
                        }
                        if (index != line.size() && line.get(index) < 0) {
                            ans += (negative_count - index);
                        }
                    }
                }
            }
        }
        for (Map.Entry<Long, Long> entry : all_pos_count.entrySet()) {
            if (entry.getValue() > 1) {
                ans += all_pos.get(entry.getKey());
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            int N = readInt();
            List<List<Long>> lines = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int M = readInt();
                List<Long> line = new ArrayList<>();
                for (int k = 0; k < M; k++) {
                    line.add((long) readInt());
                }
                lines.add(line);
            }
            Object result = solve(lines);
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
        boolean minus = false;
        while (pos < buffer.length) {
            char ch = buffer[pos++];
            if (ch == '-') {
                minus = true;
            } else if (ch < '0' || ch > '9') {
                break;
            } else {
                int digit = ch - '0';
                num = (num << 3) + (num << 1) + digit;
            }
        }
        return minus ? -num : num;
    }

    private static String readString(int length) {
        String result = String.valueOf(buffer, pos, length);
        pos += length + 1;
        return result;
    }
}
