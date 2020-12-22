package com.my.codechef.may20b.nrwp;

import java.io.*;
import java.util.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef2 {

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
            //both
            if (prevUp != null && prevSide != null) {
                calcTwoPrev(M, solutions, current, index, prevUpPoint, prevUp, prevSidePoint, prevSide, ps);
            } else if (prevUp != null) {
                calcOnePrev(M, solutions, current, index, prevUp);
            } else if (prevSide != null) {
                calcOnePrev(M, solutions, current, index, prevSide);
            } else {
                solutions[index.i][0] = new Solution(index.p * M[current.x][current.y], index.i, 1);
                solutions[index.i][1] = new Solution(index.p * M[current.x][current.y] * -1, index.i, -1);
            }
        }
    }

    private static void calcOnePrev(int[][] m, Solution[][] solutions, Pair current, Index index, Index prevUp) {
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
        calcPartTwoPrev(M, solutions, current, index, prevUp, prevUpPoint, prevLeft, prevLeftPoint, ps, 1);
        calcPartTwoPrev(M, solutions, current, index, prevUp, prevUpPoint, prevLeft, prevLeftPoint, ps, -1);
    }

    private static void calcPartTwoPrev(int[][] M, Solution[][] solutions, Pair current, Index index,
                                        Index prevUp, Pair prevUpPoint,
                                        Index prevLeft, Pair prevLeftPoint,
                                        Map<Pair, Index> ps, int newOneSign) {
        Integer prevUpInPrevLeftSign = solutions[prevLeft.i][0].signs.get(prevUp.i);
        Integer prevUpInNegativePrevLeftSign = solutions[prevLeft.i][1].signs.get(prevUp.i);
        int prevSignIndex = 0;
        long max = Integer.MIN_VALUE;
        long sum1 = solutions[prevLeft.i][0].V
                + sumEdge(prevLeft, index, 1, newOneSign)
                + sumEdge(prevUp, index, 1, newOneSign)
                + sumM(current, index, newOneSign, M);
        if (prevUpInPrevLeftSign != null && prevUpInPrevLeftSign != 1) {
            sum1 += 2 * nodeSum(M, solutions[prevLeft.i][0].signs, ps, prevUpPoint, prevUp, -prevUpInPrevLeftSign);
        }
        //TODO add modified sign
        if (sum1 > max) {
            max = sum1;
        }

        long sum2 = solutions[prevLeft.i][0].V
                + sumEdge(prevLeft, index, 1, newOneSign)
                + sumEdge(prevUp, index, -1, newOneSign)
                + sumM(current, index, newOneSign, M);
        if (prevUpInPrevLeftSign != null && prevUpInPrevLeftSign != -1) {
            sum2 += 2 * nodeSum(M, solutions[prevLeft.i][0].signs, ps, prevUpPoint, prevUp, -prevUpInPrevLeftSign);
        }
        //TODO add modified sign
        if (sum2 > max) {
            max = sum2;
        }

        long sum3 = solutions[prevLeft.i][1].V
                + sumEdge(prevLeft, index, -1, newOneSign)
                + sumEdge(prevUp, index, 1, newOneSign)
                + sumM(current, index, newOneSign, M);
        if (prevUpInNegativePrevLeftSign != null && prevUpInNegativePrevLeftSign != 1) {
            sum3 += 2 * nodeSum(M, solutions[prevLeft.i][1].signs, ps, prevUpPoint, prevUp, -prevUpInNegativePrevLeftSign);
        }
        //TODO add modified sign
        if (sum3 > max) {
            max = sum3;
            prevSignIndex = 1;
        }

        long sum4 = solutions[prevLeft.i][1].V
                + sumEdge(prevLeft, index, -1, newOneSign)
                + sumEdge(prevUp, index, -1, newOneSign)
                + sumM(current, index, newOneSign, M);
        if (prevUpInNegativePrevLeftSign != null && prevUpInNegativePrevLeftSign != -1) {
            sum4 += 2 * nodeSum(M, solutions[prevLeft.i][1].signs, ps, prevUpPoint, prevUp, -prevUpInNegativePrevLeftSign);
        }
        //TODO add modified sign
        if (sum4 > max) {
            max = sum4;
            prevSignIndex = 1;
        }

        solutions[index.i][newOneSign == 1? 0: 1]
                = new Solution(max, solutions[prevLeft.i][prevSignIndex].signs, index.i, newOneSign);
        solutions[prevLeft.i][0].terminal = false;
        solutions[prevLeft.i][1].terminal = false;
    }

    private static Solution[] calcNextSolution(int[][] M, Solution[] prevSolutions, Pair current, Index index, Index prev) {
        Solution[] result = new Solution[2];

        long sum1  = prevSolutions[0].V + sum(prev, current, index, 1, 1, M);
        long sum2  = prevSolutions[1].V + sum(prev, current, index, -1, 1, M);
        if (sum1 > sum2) {
            result[0] = addNewSolution(prevSolutions, index, sum1, 1, 1);
        } else {
            result[0] = addNewSolution(prevSolutions, index, sum2, 1, -1);
        }

        sum1  = prevSolutions[0].V + sum(prev, current, index, 1, -1, M);
        sum2  = prevSolutions[1].V + sum(prev, current, index, -1, -1, M);
        if (sum1 > sum2) {
            result[1] = addNewSolution(prevSolutions, index, sum1, -1, 1);
        } else {
            result[1] = addNewSolution(prevSolutions, index, sum2, -1, -1);
        }

        prevSolutions[0].terminal = false;
        prevSolutions[1].terminal = false;

        return result;
    }

    private static Solution addNewSolution(Solution[] prevSolutions, Index index, long sum, int sign, int signPrev) {
        return new Solution(sum, prevSolutions[signPrev == 1 ? 0 : 1].signs, index.i, sign);
    }

    private static long sum(Index prev, Pair currentPair, Index current, int currentSign, int newOneSign, int[][] M) {
        long edge = sumEdge(prev, current, currentSign, newOneSign);
        long val = sumM(currentPair, current, newOneSign, M);
        return edge + val;
    }

    private static long sumEdge(Index prev, Index current, int currentSign, int newOneSign) {
        return (long) (prev.p * currentSign * current.p * newOneSign);
    }

    private static long sumM(Pair currentPair, Index current, int newOneSign, int[][] M) {
        return current.p * M[currentPair.x][currentPair.y] * newOneSign;
    }

    private static long nodeSum(int[][] M, Map<Integer, Integer> signs, Map<Pair, Index> ps, Pair point, Index index, int sign) {
        long neighborsSum = neighborsSum(point, index, ps, signs, sign);
        long local = sumM(point, index, sign, M);
        return neighborsSum + local;
    }

    private static long neighborsSum(Pair point, Index index, Map<Pair, Index> ps, Map<Integer, Integer> signs, int sign) {
        int[][] d = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
        int result = 0;
        for (int[] ints : d) {
            Pair pp = new Pair(point.x + ints[0], point.y + ints[1]);
            // TODO
            if (ps.containsKey(pp) && signs.containsKey(ps.get(pp).i)) {
                result += index.p * ps.get(pp).p * sign * signs.get(ps.get(pp).i);
            }
        }
        return result;
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

        public Solution(long v, int index, int sign) {
            V = v;
            signs = new HashMap<>();
            signs.put(index, sign);
            terminal = true;
        }

        public Solution(long v, Map<Integer, Integer> signs, int index, int sign) {
            this(v, index, sign);
            this.signs.putAll(signs);
            terminal = true;
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
        String s = String.valueOf(buffer, start, pos - start - (stopFound? stop.length(): 0));
        return s;
    }
}