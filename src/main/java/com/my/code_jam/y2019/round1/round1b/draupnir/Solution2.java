package com.my.code_jam.y2019.round1.round1b.draupnir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solution2 {

    private static boolean isDebug = true;

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int W = in.nextInt();
        int index = 1;

        if (W != 6) return;

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
        int[] rings = new int[6];
        while (index <= t) {
            send(debug, 63 * 5);
            long six = in.nextLong() / (long) Math.pow(2, 63 * 5 / 6);

            send(debug, 63 * 4);
            long five = (in.nextLong()
                    - (long) Math.pow(2, 63 * 4 / 6) * six)
                    / (long) Math.pow(2, 63 * 4 / 5);

            send(debug, 63 * 3);
            long four = (in.nextLong()
                    - (long) Math.pow(2, 63 * 3 / 6) * six
                    - (long) Math.pow(2, 63 * 3 / 5) * five)
                    / (long) Math.pow(2, 63 * 3 / 4);

            send(debug, 63 * 2);
            long three = (in.nextLong() -
                    - (long) Math.pow(2, 63 * 2 / 6) * six
                    - (long) Math.pow(2, 63 * 2 / 5) * five
                    - (long) Math.pow(2, 63 * 2 / 4) * four)
                    / (long) Math.pow(2, 63 * 2 / 3);

            send(debug, 63);
            long two = (in.nextLong() -
                    - (long) Math.pow(2, 63 / 6) * six
                    - (long) Math.pow(2, 63 / 5) * five
                    - (long) Math.pow(2, 63 / 4) * four
                    - (long) Math.pow(2, 63 / 3) * three)
                    / (long) Math.pow(2, 63 / 2);

            send(debug, 1);
            long one = (in.nextLong() - (six + five + four + three + two))/2;

            send(debug, one, two, three, four, five, six);

            if (in.nextInt() != 1) throw new RuntimeException();
            index++;
        }
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
