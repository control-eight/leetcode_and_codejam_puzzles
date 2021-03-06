package com.my.codechef.may20b.nrwp;

import java.io.*;
import java.util.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef3 {

    public static Result solve(int[][] M, int[][] P) throws IOException {
        int[] signs = new int[P.length];
        Arrays.fill(signs, 1);
        Arrays.sort(P, Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));
        Map<Pair, Index> ps = new HashMap<>();
        for (int i = 0; i < P.length; i++) {
            int[] ints = P[i];
            ps.put(new Pair(ints[0] - 1, ints[1] - 1), new Index(i, ints[2]));
        }

        Solution[][] solutions = new Solution[P.length][2];
        boolean flip = false;
        for (int i = 0; i < M.length; i++) {
            if (!flip) {
                for (int j = 0; j < M[0].length; j++) {
                    proceed(M, ps, solutions, i, j, false);
                }
            } else {
                for (int j = M[0].length - 1; j >= 0; j--) {
                    proceed(M, ps, solutions, i, j, true);
                }
            }
            flip = !flip;
        }
        Result result = getResult(solutions, P.length);
        //print(result);
        return result;
    }

    private static void proceed(int[][] M, Map<Pair, Index> ps, Solution[][] solutions, int i, int j, boolean flip) {
        Pair current = new Pair(i, j);
        Index index = ps.get(current);
        Pair prevUpPoint = new Pair(i - 1, j);
        Index prevUp = ps.get(prevUpPoint);
        Pair prevSidePoint;
        if (!flip) {
            prevSidePoint = new Pair(i, j - 1);
        } else {
            prevSidePoint = new Pair(i, j + 1);
        }
        Index prevSide = ps.get(prevSidePoint);
        if (index != null) {
            if (prevUp != null || prevSide != null) {
                calcTwoPrev(M, solutions, current, index, prevUpPoint, prevUp, prevSidePoint, prevSide, ps);
            } else {
                solutions[index.i][0] = new Solution(index.p * M[current.x][current.y], index.i, 1);
                solutions[index.i][1] = new Solution(index.p * M[current.x][current.y] * -1, index.i, -1);
            }
        }
    }

    private static Result getResult(Solution[][] solutions, int PLength) {
        int[] signs = new int[PLength];
        long V = 0;
        for (Solution[] solution : solutions) {
            if (solution[0].terminal) {
                if (solution[0].V > solution[1].V) {
                    V += solution[0].V;
                    for (Map.Entry<Integer, Integer> entry : solution[0].signs.entrySet()) {
                        signs[entry.getKey()] = entry.getValue();
                    }
                } else {
                    V += solution[1].V;
                    for (Map.Entry<Integer, Integer> entry : solution[1].signs.entrySet()) {
                        signs[entry.getKey()] = entry.getValue();
                    }
                }
            }
        }
        return new Result(V, signs);
    }

    private static void calcTwoPrev(int[][] M, Solution[][] solutions, Pair current, Index index,
                                    Pair prevUpPoint, Index prevUp, Pair prevLeftPoint, Index prevLeft,
                                    Map<Pair, Index> ps) {
        Solution[] prevLeftSolutions = new Solution[] {new Solution(Integer.MIN_VALUE), new Solution(Integer.MIN_VALUE)};
        if (prevLeft != null) {
            prevLeftSolutions = calcPartTwoPrev(M, solutions[prevLeft.i], current, index, prevLeft, prevUp);
        }
        Solution[] prevUpSolutions = new Solution[] {new Solution(Integer.MIN_VALUE), new Solution(Integer.MIN_VALUE)};
        if (prevUp != null) {
            prevUpSolutions = calcPartTwoPrev(M, solutions[prevUp.i], current, index, prevLeft, prevUp);
        }
        solutions[index.i][0] = max(prevLeftSolutions[0], prevUpSolutions[0], index.i, 0);
        solutions[index.i][1] = max(prevLeftSolutions[1], prevUpSolutions[1], index.i, 1);

        for (int i = 0; i < solutions.length; i++) {
            if (solutions[i][0] != null && i != index.i &&
                    (prevLeft != null && solutions[i][0].signs.containsKey(prevLeft.i) ||
                            prevUp != null && solutions[i][0].signs.containsKey(prevUp.i))) {
                Solution[] prevSolutions = calcPartTwoPrevReverse(M, solutions[i], current, index, prevLeft, prevUp);
                solutions[i][0] = max(prevSolutions[0], prevSolutions[0], i, 0);
                solutions[i][1] = max(prevSolutions[1], prevSolutions[1], i, 1);
            }
        }
    }

    private static Solution max(Solution first, Solution second, int index, int sign) {
        if (first.V > second.V) {
            return new Solution(first.V, first.signs, index, sign, first.terminal);
        } else {
            return new Solution(second.V, second.signs, index, sign, second.terminal);
        }
    }

    private static Solution[] calcPartTwoPrev(int[][] M, Solution[] prevSolutions,
                                              Pair current, Index index,
                                              Index prevLeft, Index prevUp) {
        Solution[] result = new Solution[2];

        long sum1 = prevSolutions[0].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[0].signs.get(prevLeft.i), 1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[0].signs.get(prevUp.i),   1))
                + sumM(current, index, 1, M);

        long sum2 = prevSolutions[1].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[1].signs.get(prevLeft.i), 1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[1].signs.get(prevUp.i),   1))
                + sumM(current, index, 1, M);

        if (sum1 > sum2) {
            result[0] = new Solution(sum1, prevSolutions[0].signs, index.i, 1, true);
        } else {
            result[0] = new Solution(sum2, prevSolutions[1].signs, index.i, 1, true);
        }

        long sum3 = prevSolutions[0].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[0].signs.get(prevLeft.i), -1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[0].signs.get(prevUp.i),   -1))
                + sumM(current, index, -1, M);

        long sum4 = prevSolutions[1].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[1].signs.get(prevLeft.i), -1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[1].signs.get(prevUp.i),   -1))
                + sumM(current, index, -1, M);

        if (sum3 > sum4) {
            result[1] = new Solution(sum3, prevSolutions[0].signs, index.i, -1, true);
        } else {
            result[1] = new Solution(sum4, prevSolutions[1].signs, index.i, -1, true);
        }

        prevSolutions[0].terminal = false;
        prevSolutions[1].terminal = false;

        return result;
    }

    private static Solution[] calcPartTwoPrevReverse(int[][] M, Solution[] prevSolutions,
                                                     Pair current, Index index,
                                                     Index prevLeft, Index prevUp) {
        Solution[] result = new Solution[2];

        long sum1 = prevSolutions[0].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[0].signs.get(prevLeft.i), 1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[0].signs.get(prevUp.i),   1))
                + sumM(current, index, 1, M);

        long sum2 = prevSolutions[0].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[0].signs.get(prevLeft.i), -1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[0].signs.get(prevUp.i),   -1))
                + sumM(current, index, -1, M);

        if (sum1 > sum2) {
            result[0] = new Solution(sum1, prevSolutions[0].signs, index.i, 1, false);
        } else {
            result[0] = new Solution(sum2, prevSolutions[0].signs, index.i, -1, false);
        }

        long sum3 = prevSolutions[1].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[1].signs.get(prevLeft.i), 1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[1].signs.get(prevUp.i),   1))
                + sumM(current, index, 1, M);

        long sum4 = prevSolutions[1].V
                + (prevLeft == null? 0: sumEdge(prevLeft, index, prevSolutions[1].signs.get(prevLeft.i), -1))
                + (prevUp == null?   0: sumEdge(prevUp,   index, prevSolutions[1].signs.get(prevUp.i),   -1))
                + sumM(current, index, -1, M);

        if (sum3 > sum4) {
            result[1] = new Solution(sum3, prevSolutions[1].signs, index.i, 1, false);
        } else {
            result[1] = new Solution(sum4, prevSolutions[1].signs, index.i, -1, false);
        }

        return result;
    }

    private static long sumEdge(Index prev, Index current, int currentSign, int newOneSign) {
        return (long) (prev.p * currentSign * current.p * newOneSign);
    }

    private static long sumM(Pair currentPair, Index current, int newOneSign, int[][] M) {
        return current.p * M[currentPair.x][currentPair.y] * newOneSign;
    }

    private static class Pair implements Comparable<Pair> {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Pair o) {
            int result = Integer.compare(x, o.x);
            return result == 0? Integer.compare(y, o.y): result;
        }
    }

    private static class Index {
        private int i;
        private int p;

        public Index(int i, int p) {
            this.i = i;
            this.p = p;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return i == index.i;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i);
        }
    }

    public static class Result {
        long V;
        int[] signs;

        public Result(long v, int[] signs) {
            V = v;
            this.signs = signs;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return V == result.V &&
                    Arrays.equals(signs, result.signs);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(V);
            result = 31 * result + Arrays.hashCode(signs);
            return result;
        }
    }

    public static class Solution {
        long V;
        Map<Integer, Integer> signs;
        boolean terminal;

        public Solution(long v) {
            this.V = v;
            signs = new HashMap<>();
        }

        public Solution(long v, int index, int sign) {
            this(v);
            signs.put(index, sign);
            terminal = true;
        }

        public Solution(long v, Map<Integer, Integer> signs, int index, int sign, boolean terminal) {
            this(v, index, sign);
            this.signs.putAll(signs);
            this.terminal = terminal;
        }

        @Override
        public String toString() {
            return V + " " + signs;
        }
    }

    private static void print(Result result) throws IOException {
        print(result.V);
        List<Integer> l = new ArrayList<>();
        for (int sign : result.signs) {
            l.add(sign);
        }
        print(l);
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            int H = readInt();
            int W = readInt();
            int N = readInt();

            int[][] M = new int[H][W];
            for (int k = 0; k < H; k++) {
                String line = readString(System.lineSeparator());
                String[] split = line.split("\\s");
                for (int l = 0; l < W; l++) {
                    M[k][l] = Integer.parseInt(split[l]);
                }
            }
            int[][] P = new int[N][3];
            for (int p = 0; p < N; p++) {
                String line = readString(System.lineSeparator());
                String[] split = line.split("\\s");
                P[p][0] = Integer.parseInt(split[0]);
                P[p][1] = Integer.parseInt(split[1]);
                P[p][2] = Integer.parseInt(split[2]);
            }
            Object result = solve(M, P);
            //print(bufferedWriter, result);
        }
        finish(bufferedWriter);
    }

    private static BufferedWriter init() throws IOException {
        bufferedWriter = new BufferedWriter(new PrintWriter(System.out));
        buffer = getChars(System.in);
        return bufferedWriter;
    }

    private static void print(Object result) throws IOException {
        if (bufferedWriter == null) return;

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

    private static void finish(BufferedWriter bufferedWriter) throws IOException {
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

    private static String readString(char stop) {
        int start = pos;
        while (pos < buffer.length && buffer[pos] != stop) {
            pos++;
        }
        String s = String.valueOf(buffer, start, pos - start);
        pos++;
        return s;
    }

    private static String readString(String stop) {
        int start = pos;
        boolean stopFound = false;
        while (pos < buffer.length) {
            if (!(pos - start < stop.length() ||
                    !stop.equals(String.valueOf(buffer, pos - stop.length(), stop.length())))) {
                stopFound = true;
                break;
            }
            pos++;
        }
        return String.valueOf(buffer, start, pos - start - (stopFound? stop.length(): 0));
    }
}