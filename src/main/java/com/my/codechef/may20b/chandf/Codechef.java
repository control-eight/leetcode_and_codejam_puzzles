package com.my.codechef.may20b.chandf;

import java.io.*;
import java.math.BigInteger;
import java.util.Collection;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef {

    public static Object solve(long X, long Y, long L, long R) {
        if (X == 0 || Y == 0) return L;
        // L <= X,Y <= R
        if (L <= (X | Y) && R >= (X | Y)) {
            //X * Y
            return X | Y;
        }
        long S = 1;
        long Shelp = R;
        BigInteger max = BigInteger.valueOf(X).and(BigInteger.valueOf(R))
                .multiply(BigInteger.valueOf(Y).and(BigInteger.valueOf(R)));
        long Z = R;
        while (S <= R) {
            if ((S & R) > 0) {
                Shelp ^= S;
                Shelp |= S - 1;
                if (Shelp < L) {
                    break;
                }
                //(X & Shelp) * (Y & Shelp)
                BigInteger r = BigInteger.valueOf(X).and(BigInteger.valueOf(Shelp))
                        .multiply(BigInteger.valueOf(Y).and(BigInteger.valueOf(Shelp)));
                if (r.compareTo(max) >= 0) {
                    max = r;
                    Z = Shelp;
                }
            }
            S <<= 1;
        }
        S = 1;
        while (S < Z) {
            S <<= 1;
        }
        while (S > 0) {
            if ((S & Z) > 0 && (S & X) == 0 && (S & Y) == 0) {
                if ((Z ^ S) >= L) {
                    Z ^= S;
                }
            }
            S >>= 1;
        }
        return max.equals(BigInteger.ZERO)? L: Z;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = init();

        int T = readInt();
        for (int i = 0; i < T; i++) {
            long X = readLong();
            long Y = readLong();
            long L = readLong();
            long R = readLong();
            Object result = solve(X, Y, L, R);
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