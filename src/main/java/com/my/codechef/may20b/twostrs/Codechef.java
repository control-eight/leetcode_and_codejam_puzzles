package com.my.codechef.may20b.twostrs;

import java.io.*;
import java.util.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Codechef {

    private static long computeIntervals(String S, Trie prefixTree, long[] prefixSums, long[] suffixSums) {
        for (int i = 0; i < S.length(); i++) {
            prefixTree.compute(S, i, i, prefixSums, suffixSums);
        }
        long sum = 0;
        for (long prefixSum : prefixSums) {
            sum += prefixSum;
        }
        return sum;
    }

    private static long calc(String A, String B, String S, long sum, long[] prefixSums, long[] suffixSums,
                             int maxL, Trie prefixTree) {
        long max = sum;
        sum = 0;
        Map<String, Pair> solutions = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            sum += suffixSums[i];
            long tmpSum = sum;
            //-------------------------------------------------------------------------
            long maxProfitLocal = 0;
            List<Pair> prefixes = new ArrayList<>();
            List<Pair> seen = new ArrayList<>();
            for (int k = i; k > Math.max(-1, i - (maxL - 1)); k--) {
                String prefix = S.substring(k, i + 1);
                if (!solutions.containsKey(prefix)) {
                    Trie trie = prefixTree.cumulativeFindPartial(S, k, i + 1);
                    if (trie != null) {
                        prefixes.add(new Pair(trie, k, prefix, S.length()));
                        maxProfitLocal += trie.maxProfit;
                    }
                } else {
                    Pair e = solutions.get(prefix);
                    maxProfitLocal += e.trie.maxProfit;
                    seen.add(e);
                }
            }
            //-------------------------------------------------------------------------
            // A[0 .. i] + B[j .. |S|]
            // <-------      ------->
            long[] sums = new long[S.length()];
            for (int j = S.length() - 1; j >= A.length(); j--) {
                tmpSum += prefixSums[j];
                sums[j] = tmpSum;
                //if (maxProfitLocal > max - tmpSum) {
                    for (Pair prefix : prefixes) {
                        int hi2 = Math.min(S.length(), j + (maxL - 1) - (i - prefix.k));
                        prefix.sums[j] = prefix.trie.cumulativeFindSum(S, j, hi2);
                        sums[j] += prefix.sums[j];
                    }
                //}
            }
            //-------------------------------------------------------------------------
            for (Pair prefix : prefixes) {
                solutions.put(prefix.prefix, prefix);
            }
            for (int j = S.length() - 1; j >= A.length(); j--) {
                for (Pair e : seen) {
                    sums[j] += e.sums[j];
                }
                max = Math.max(max, sums[j]);
            }
        }
        return max;
    }

    public static Object solve(String A, String B, Map<String, Integer> map) {
        int maxL = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            maxL = Math.max(maxL, entry.getKey().length());
        }
        String S = A + B;
        long[] prefixSums = new long[S.length()];
        long[] suffixSums = new long[S.length()];
        Trie prefixTree = new Trie(map);
        prefixTree.computeMaxProfit();
        long sum = computeIntervals(S, prefixTree, prefixSums, suffixSums);
        return calc(A, B, S, sum, prefixSums, suffixSums, maxL, prefixTree);
    }

    private static class Pair {
        private Trie trie;
        private int k;
        private String prefix;
        private long[] sums;
        public Pair(Trie trie, int k, String prefix, int sumsLength) {
            this.trie = trie;
            this.k = k;
            this.prefix = prefix;
            this.sums = new long[sumsLength];
        }
    }

    public static long ops;

    private static class Trie {

        private Trie[] map = new Trie[26];
        private boolean isWord;
        private long b;
        private long maxProfit;
        private String match;

        public Trie(boolean isWord, long b, String word) {
            this.isWord = isWord;
            if (!isWord) {
                this.b = 0;
                this.match = null;
            } else {
                this.b = b;
                this.match = word;
            }
        }

        public Trie(Map<String, Integer> words) {
            for (Map.Entry<String, Integer> entry : words.entrySet()) {
                Trie[] aMap = map;
                char[] chars = entry.getKey().toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    boolean isWord = i == chars.length - 1;
                    if (aMap[c - 'a'] != null) {
                        if (isWord) {
                            aMap[c - 'a'].isWord = true;
                            aMap[c - 'a'].b = entry.getValue();
                            aMap[c - 'a'].match = entry.getKey();
                        }
                    } else {
                        aMap[c - 'a'] = new Trie(isWord, entry.getValue(), entry.getKey());
                    }
                    aMap = aMap[c - 'a'].map;
                }
            }
        }

        public Trie cumulativeFindPartial(String s, int lo, int hi) {
            Trie current = this;
            int i = lo;
            while (i < hi) {
                Trie next = current.map[s.charAt(i) - 'a'];
                if (next == null) {
                    return null;
                }
                current = next;
                i++;
            }
            return current;
        }

        public long cumulativeFindSum(String s, int lo, int hi) {
            long result = 0;
            int i = lo;
            Trie current = this.map[s.charAt(i) - 'a'];
            ops++;
            while (i < hi && current != null) {
                result += current.b;
                i++;
                if (i < hi) {
                    current = current.map[s.charAt(i) - 'a'];
                }
                ops++;
            }
            return result;
        }

        public void computeMaxProfit() {
            long sum = 0;
            for (Trie trie : map) {
                if (trie != null) {
                    trie.computeMaxProfit();
                    sum += trie.maxProfit;
                }
            }
            if (this.isWord) {
                sum += b;
            }
            this.maxProfit = sum;
        }

        public void compute(String S, int start, int current, long[] prefixSums, long[] suffixSums) {
            if (this.isWord) {
                prefixSums[start] += this.b;
                suffixSums[current - 1] += this.b;
            }
            if (current >= S.length()) return;
            Trie next = map[S.charAt(current) - 'a'];
            if (next != null) {
                next.compute(S, start, current + 1, prefixSums, suffixSums);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            //System.lineSeparator()
            String A = readString(System.lineSeparator());
            String B = readString(System.lineSeparator());
            int N = readInt();
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < N; j++) {
                map.put(readString(' '), readInt());
            }
            Object result = solve(A, B, map);
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
        while (pos < buffer.length && (pos - start < stop.length() ||
                !stop.equals(String.valueOf(buffer, pos - stop.length(), stop.length())))) {
            pos++;
        }
        String s = String.valueOf(buffer, start, pos - start - stop.length());
        return s;
    }
}