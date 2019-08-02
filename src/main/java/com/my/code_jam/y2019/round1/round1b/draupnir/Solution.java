package com.my.code_jam.y2019.round1.round1b.draupnir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {

    private static boolean isDebug = false;

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int W = in.nextInt();
        int index = 1;

        File out = new File("/tmp/out");
        if (out.exists()) {
            out.delete();
            out.createNewFile();
        }
        try (PrintWriter debug = new PrintWriter(new FileWriter(out))) {
            solution(in, T, index, debug);
        }
    }

    private static void solution(Scanner in, int t, int index, PrintWriter debug) {
        while (index <= t) {
            long first = 200;
            send(debug, first);

            long total = in.nextLong();
            debug.println(total);
            long four = findRing(total, Math.pow(2, first / 4), 100);
            long five = findRing(total - four * (long) Math.pow(2, first / 4), (long) Math.pow(2, first / 5), 100);

            long second = 44;
            send(debug, second);
            total = in.nextLong();
            debug.println(total);

            total -= four * (long) Math.pow(2, second / 4);
            total -= five * (long) Math.pow(2, second /5 );

            long one = findRing(total, Math.pow(2, second), 100);
            total -= one * (long) Math.pow(2, second);
            long two = findRing(total, (long) Math.pow(2, second / 2), 100);
            total -= two * (long) Math.pow(2, second / 2);
            long three = findRing(total, Math.pow(2, second / 3), 100);
            total -= three * (long) Math.pow(2, second / 3);

            long six = total / (long) Math.pow(2, second / 6);


            send(debug, one, two, three, four, five, six);

            if (in.nextInt() != 1) throw new RuntimeException();
            index++;
        }
    }

    private static long findRing(long total, double pow, long max) {
        for (long i = 0; i <= max; i++) {
            if (pow * i > total) return i - 1;
        }
        return max;
    }

    private static void send(PrintWriter debug, Object ... object) {
        String[] strings = new String[object.length];
        for (int i = 0; i < object.length; i++) {
            strings[i] = object[i].toString();
        }
        System.out.print(String.join(" ", strings) + "\n");
        System.out.flush();

        if (isDebug) {
            debug.println(String.join(" ", strings));
            debug.flush();
        }
    }
}
