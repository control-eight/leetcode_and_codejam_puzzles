package com.my.codechef.may20b.trplsrt;

import java.io.*;
import java.util.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef {

    public static int solve(Integer[] permutation, int K) throws IOException {
        List<List<Integer>> result = new ArrayList<>();
        return resolveLeft(permutation, K, result);
    }

    private static int resolveLeft(Integer[] permutation, int K, List<List<Integer>> result) throws IOException {
        //queue
        check3(permutation, result);
        check2(permutation, result);
        check3(permutation, result);
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> ones = new HashMap<>();
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                queue.offer(i);
                int p1 = permutation[i];
                int p2 = permutation[p1 - 1];
                if (p2 - 1 == i) {
                    ones.put(i, p1 - 1);
                }
            }
        }
        if ((ones.size() / 2) % 2 == 1) {
            print(-1);
            return -1;
        }

        K = Math.min(K, permutation.length / 2);
        while (result.size() < K && !queue.isEmpty()) {
            int i1 = queue.poll();
            int p1 = permutation[i1];
            ones.remove(i1);
            ones.remove(p1 - 1);
            if (ones.isEmpty()) break;
            Map.Entry<Integer, Integer> next = ones.entrySet().iterator().next();
            set(permutation, result, i1, p1, next.getKey() + 1);
            set(permutation, result, i1, next.getValue() + 1, next.getKey() + 1);
            ones.remove(next.getKey());
            ones.remove(next.getValue());
            spill(queue, permutation);
        }
        spill(queue, permutation);
        if (print(result, K, queue)) return -1;
        return result.size();
    }

    private static boolean print(List<List<Integer>> result, int K, Queue<Integer> queue) throws IOException {
        if (bufferedWriter == null) {
            bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
        }
        if (!queue.isEmpty() || result.size() > K) {
            print(-1);
            return true;
        } else {
            print(result.size());
            for (List<Integer> integers : result) {
                print(integers);
            }
        }
        return false;
    }

    private static void set(Integer[] permutation, List<List<Integer>> result, int i, int p1, int p2) {
        result.add(Arrays.asList(i + 1, p1, p2));
        int tmp = permutation[i];
        permutation[i] = permutation[p2 - 1];
        permutation[p2 - 1] = permutation[p1 - 1];
        permutation[p1 - 1] = tmp;
    }

    private static void spill(Queue<Integer> queue, Integer[] permutation) {
        while (!queue.isEmpty() && permutation[queue.peek()] == queue.peek() + 1) {
            queue.poll();
        }
    }

    private static void check3(Integer[] permutation, List<List<Integer>> result) {
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                int p1 = permutation[i];
                int p2 = permutation[permutation[i] - 1];
                int p3 = permutation[p2 - 1];
                if (p2 - 1 != i && p3 - 1 == i) {
                    set(permutation, result, i, p1, p2);
                }
            }
        }
    }

    private static void check2(Integer[] permutation, List<List<Integer>> result) {
        boolean check = true;
        while (check) {
            check = false;
            for (int i = 0; i < permutation.length; i++) {
                if (permutation[i] != i + 1) {
                    int p1 = permutation[i];
                    int p2 = permutation[permutation[i] - 1];
                    if (p2 - 1 != i) {
                        set(permutation, result, i, p1, p2);
                        check = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            int N = readInt();
            int K = readInt();
            Integer[] permutation = new Integer[N];
            for (int j = 0; j < N; j++) {
                permutation[j] = readInt();
            }
            Object result = solve(permutation, K);
            //print(bufferedWriter, result);
        }
        finish();
    }

    private static BufferedWriter init() throws IOException {
        bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
        buffer = getChars(System.in);
        return bufferedWriter;
    }

    private static void print(Object result) throws IOException {
        if (result instanceof Object[]) {
            for (Object obj : ((Object[]) result)) {
                print(obj);
            }
        } else if (result instanceof Collection) {
            Collection result1 = (Collection) result;
            Iterator i = result1.iterator();

            if (i.hasNext()) {
                bufferedWriter.write(i.next() +  "");
            }
            while (i.hasNext()) {
                bufferedWriter.write(" " + i.next());
            }
            bufferedWriter.newLine();
        } else {
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

    public static void finish() throws IOException {
        bufferedWriter.close();
    }

    private static int pos;
    private static char[] buffer;
    private static BufferedWriter bufferedWriter;

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