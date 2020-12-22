package com.my.codechef.may20b.nbots;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef {

    public static int solve(int[][] M, int F, int K) throws IOException {
        int S = 0;
        List<int[]> shots = new ArrayList<>();

        int[] INDEX = new int[4 * M.length];
        for (int i = 0; i < 4 * M.length; i++) {
            int index = i / M.length;
            //R
            if (index == 2) {
                INDEX[i] = M.length - 1;
                //D
            } else if (index == 3) {
                INDEX[i] = M.length - 1;
            }
        }
        boolean[][] destroyed = new boolean[M.length][M.length];
        while (true) {
            int bestShot = Integer.MAX_VALUE;
            int bestShotIndex = -1;
            int bestCount = -1;
            for (int i = 0; i < 4 * M.length; i++) {
                int index = i / M.length;
                int ii = i % M.length;
                //L
                if (index == 0) {
                    Pair value = calcShotLeft(M, ii, INDEX, i, F, destroyed);
                    int[] compare = compare(value, bestShot, bestCount, F);
                    if (compare[0] < 0) {
                        bestShot = compare[1];
                        bestShotIndex = i;
                        bestCount = value.count;
                    }
                    //U
                } else if (index == 1) {
                    Pair value = calcShotUp(M, ii, INDEX, i, F, destroyed);
                    int[] compare = compare(value, bestShot, bestCount, F);
                    if (compare[0] < 0) {
                        bestShot = compare[1];
                        bestShotIndex = i;
                        bestCount = value.count;
                    }
                    //R
                } else if (index == 2) {
                    Pair value = calcShotRight(M, ii, INDEX, i, F, destroyed);
                    int[] compare = compare(value, bestShot, bestCount, F);
                    if (compare[0] < 0) {
                        bestShot = compare[1];
                        bestShotIndex = i;
                        bestCount = value.count;
                    }
                    //D
                } else if (index == 3) {
                    Pair value = calcShotDown(M, ii, INDEX, i, F, destroyed);
                    int[] compare = compare(value, bestShot, bestCount, F);
                    if (compare[0] < 0) {
                        bestShot = compare[1];
                        bestShotIndex = i;
                        bestCount = value.count;
                    }
                }
            }
            if (bestShotIndex == -1) {
                break;
            }
            setDestroyed(destroyed, INDEX, bestShotIndex, bestShotIndex / M.length, bestShotIndex % M.length,
                    bestCount);
            shots.add(new int[] {bestShotIndex / M.length, bestShotIndex % M.length + 1});
            S++;
        }
        print(S, shots);
        return S;
    }

    private static void print(int S, List<int[]> shots) {
        System.out.println(S);
        for (int[] shot : shots) {
            if (shot[0] == 0) {
                System.out.println("L " + shot[1]);
            } else if (shot[0] == 1) {
                System.out.println("U " + shot[1]);
            } else if (shot[0] == 2) {
                System.out.println("R " + shot[1]);
            } else if (shot[0] == 3) {
                System.out.println("D " + shot[1]);
            }
        }
    }

    private static int[] compare(Pair value, int bestShot, int bestCount, int F) {
        if (value.count == 0) {
            return new int[] {1, 0};
        }
        if (value.result == bestShot) {
            return new int[] {Integer.compare(bestCount, value.count), value.result};
        }
        return new int[] {Integer.compare(value.result, bestShot), value.result};
        /*int v;
        if (value.result > F/2) {
            //v = (int) (Math.log(value.count) * (3 * F + (F - value.result)));
            v = (int) (Math.sqrt(value.count) * (3 * F + value.result));
        } else {
            //v = (int) (Math.log(value.count) * (F - value.result));
            v = (int) (Math.sqrt(value.count) * value.result);
        }
        return new int[] {Integer.compare(v, bestShot), v};*/
    }

    private static void setDestroyed(boolean[][] destroyed, int[] INDEX, int bestShotIndex, int index, int ii,
                                     int bestCount) {
            //L
        if (index == 0) {
            for (int k = INDEX[bestShotIndex]; k < INDEX[bestShotIndex] + bestCount; k++) {
                destroyed[ii][k] = true;
            }
            //U
        } else if (index == 1) {
            for (int k = INDEX[bestShotIndex]; k < INDEX[bestShotIndex] + bestCount; k++) {
                destroyed[k][ii] = true;
            }
            //R
        } else if (index == 2) {
            for (int k = INDEX[bestShotIndex]; k > INDEX[bestShotIndex] - bestCount; k--) {
                destroyed[ii][k] = true;
            }
            //D
        } else if (index == 3) {
            for (int k = INDEX[bestShotIndex]; k > INDEX[bestShotIndex] - bestCount; k--) {
                destroyed[k][ii] = true;
            }
        }
    }

    private static Pair calcShotLeft(int[][] M, int ii, int[] index, int i, int F, boolean[][] destroyed) {
        int result = F;
        int count = 0;
        while (index[i] < M.length && destroyed[ii][index[i]]) {
            index[i]++;
        }
        for (int k = index[i]; k < M.length; k++) {
            if (!destroyed[ii][k]) {
                if (result - M[ii][k] < 0) {
                    return new Pair(result, count);
                }
                result -= M[ii][k];
                count++;
            }
        }
        return new Pair(result, count);
    }

    private static Pair calcShotUp(int[][] M, int ii, int[] index, int i, int F, boolean[][] destroyed) {
        int result = F;
        int count = 0;
        while (index[i] < M.length && destroyed[index[i]][ii]) {
            index[i]++;
        }
        for (int k = index[i]; k < M.length; k++) {
            if (!destroyed[k][ii]) {
                if (result - M[k][ii] < 0) {
                    return new Pair(result, count);
                }
                result -= M[k][ii];
                count++;
            }
        }
        return new Pair(result, count);
    }

    private static Pair calcShotRight(int[][] M, int ii, int[] index, int i, int F, boolean[][] destroyed) {
        int result = F;
        int count = 0;
        while (index[i] >= 0 && destroyed[ii][index[i]]) {
            index[i]--;
        }
        for (int k = index[i]; k >= 0; k--) {
            if (!destroyed[ii][k]) {
                if (result - M[ii][k] < 0) {
                    return new Pair(result, count);
                }
                result -= M[ii][k];
                count++;
            }
        }
        return new Pair(result, count);
    }

    private static Pair calcShotDown(int[][] M, int ii, int[] index, int i, int F, boolean[][] destroyed) {
        int result = F;
        int count = 0;
        while (index[i] >= 0 && destroyed[index[i]][ii]) {
            index[i]--;
        }
        for (int k = index[i]; k >= 0; k--) {
            if (!destroyed[k][ii]) {
                if (result - M[k][ii] < 0) {
                    return new Pair(result, count);
                }
                result -= M[k][ii];
                count++;
            }
        }
        return new Pair(result, count);
    }

    private static class Pair {
        private int result;
        private int count;

        public Pair(int result, int count) {
            this.result = result;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int N = readInt();
        int F = readInt();
        int[][] M = new int[N][N];
        for (int k = 0; k < N; k++) {
            String line = readString(System.lineSeparator());
            String[] split = line.split("\\s");
            for (int l = 0; l < N; l++) {
                M[k][l] = Integer.parseInt(split[l]);
            }
        }
        int K = readInt();
        Object result = solve(M, F, K);
        //print(bufferedWriter, result);
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