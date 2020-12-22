package com.my.codechef.may20b.sortvs;

import java.io.*;
import java.util.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef {

    public static Object solve(int[] permutation, int[][] swap) {
        boolean[][] M = new boolean[permutation.length][permutation.length];
        for (int[] ints : swap) {
            M[ints[0] - 1][ints[1] - 1] = true;
            M[ints[1] - 1][ints[0] - 1] = true;
        }
        Set<Integer>[] traverseMap = new Set[permutation.length];
        Set<Integer> left = new HashSet<>();
        zeroOps(permutation, M);
        for (int i = 0; i < permutation.length; i++) {
            traverseMap[i] = traverse(permutation, M, i);
            if (permutation[i] != i + 1) {
                left.add(i);
            }
        }
        long l = System.currentTimeMillis();
        int backtrack = backtrack(permutation, M, new HashMap<>(), 0, traverseMap);
        /*System.out.println((System.currentTimeMillis() - l) + "ms");
        System.out.println("ops = " + ops);*/
        return backtrack;
    }

    private static int maxDepth = 0;
    private static int ops = 0;

    private static int backtrack(int[] permutation, boolean[][] M, Map<Key, Integer> seen, int depth,
                                 Set<Integer>[] traverseMap) {
        Integer result = seen.get(new Key(permutation));
        if (result != null) {
            return result;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < permutation.length; i++) {
            if (permutation[i] != i + 1) {
                int v = permutation[i];
                boolean foundOneToOne = false;
                for (Integer ovi : traverseMap[v - 1]) {
                    int ov = permutation[ovi];
                    if (permutation[ovi] != ovi + 1 && ov != v) {
                        if (traverseMap[i].contains(ov - 1)) {
                            ops++;
                            foundOneToOne = true;
                            int tmp = permutation[ov - 1];
                            permutation[ov - 1] = v;
                            permutation[i] = tmp;
                            min = proceed(permutation, M, seen, depth, traverseMap, min, ov - 1, v, ovi, ov);
                            permutation[ov - 1] = tmp;
                            permutation[i] = v;
                            break;
                        }
                    }
                }
                if (!foundOneToOne) {
                    ops++;
                    int ovi = v - 1;
                    int ov = permutation[ovi];
                    min = proceed(permutation, M, seen, depth, traverseMap, min, i, v, ovi, ov);
                }
            }
        }
        result = min == Integer.MAX_VALUE ? 0 : min;
        seen.put(new Key(Arrays.copyOf(permutation, permutation.length)), result);
        return result;
    }

    private static int proceed(int[] permutation, boolean[][] M, Map<Key, Integer> seen, int depth,
                               Set<Integer>[] traverseMap, int min, int i, int v, Integer ovi, int ov) {
        permutation[i] = ov;
        int tmp = -1;
        if (ovi != v - 1) {
            tmp = permutation[v - 1];
            permutation[v - 1] = v;
            permutation[ovi] = tmp;
        } else {
            permutation[ovi] = v;
        }
        min = Math.min(min, backtrack(permutation, M, seen, depth + 1, traverseMap)
                + (M[i][ovi] ? 0 : 1));
        if (ovi != v - 1) {
            permutation[v - 1] = tmp;
        }
        permutation[ovi] = ov;
        permutation[i] = v;
        return min;
    }

    private static class Key {
        private int[] permutation;

        public Key(int[] permutation) {
            this.permutation = permutation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Arrays.equals(permutation, key.permutation);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(permutation);
        }
    }

    private static Set<Integer> traverse(int[] permutation, boolean[][] M, int i) {
        Set<Integer> result = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        seen.add(i);
        result.add(i);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            Integer next = queue.poll();
            for (int k = 0; k < M[next].length; k++) {
                if (M[next][k] && seen.add(k)) {
                    if (permutation[k] != k + 1) {
                        result.add(k);
                    }
                    queue.offer(k);
                }
            }
        }
        return result;
    }

    private static int zeroOps(int[] permutation, boolean[][] M) {
        int result = 0;
        for (int i = 0; i < permutation.length; i++) {
            while (permutation[i] != i + 1) {
                if (isReachable(permutation, M, i, permutation[i])) {
                    swapIndex(permutation, i, permutation[i] - 1);
                    result++;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private static boolean isReachable(int[] permutation, boolean[][] M, int i, int v) {
        boolean[] visited = new boolean[permutation.length];
        visited[i] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
            Integer next = queue.poll();
            for (int k = 0; k < M[next].length; k++) {
                if (M[next][k]) {
                    if (k == v - 1) {
                        return true;
                    } else if (!visited[k]) {
                        visited[k] = true;
                        queue.offer(k);
                    }
                }
            }
        }
        return false;
    }

    private static int swapIndex(int[] permutation, int i, int j) {
        int tmp = permutation[j];
        permutation[j] = permutation[i];
        permutation[i] = tmp;
        return tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            int N = readInt();
            int M = readInt();
            int[] permutation = new int[N];
            for (int j = 0; j < N; j++) {
                permutation[j] = readInt();
            }
            int[][] swap = new int[M][2];
            for (int j = 0; j < M; j++) {
                swap[j] = new int[]{readInt(), readInt()};
            }
            Object result = solve(permutation, swap);
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

    private static long readLong() {
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
        return num;
    }

    private static String readString(int length) {
        String result = String.valueOf(buffer, pos, length);
        pos += length + 1;
        return result;
    }
}