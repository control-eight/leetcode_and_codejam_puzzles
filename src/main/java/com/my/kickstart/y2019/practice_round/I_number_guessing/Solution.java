package com.my.kickstart.y2019.practice_round.I_number_guessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Solution {

    private static boolean isDebug = true;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int index = 1;
        File out = initDebug();
        try (PrintWriter debug = new PrintWriter(new FileWriter(out))) {
            while (index <= T) {
                solution(in, index, debug);
                index++;
            }
        }
    }

    private static File initDebug() throws IOException {
        File out = new File("/tmp/out");
        if (out.exists()) {
            out.delete();
            out.createNewFile();
        }
        return out;
    }

    private static void solution(Scanner in, int index, PrintWriter debug) {
        int A = in.nextInt();
        int B = in.nextInt();
        int N = in.nextInt();
        if (!solve(A, B + 1, in, debug)) throw new RuntimeException();
    }

    private static boolean solve(int lo, int hi, Scanner in, PrintWriter debug) {
        int mid = lo + (hi - lo) / 2;
        if (lo < hi - 1) {
            send(debug, mid);
            String s = in.next();
            switch (s) {
                case "CORRECT": return true;
                case "TOO_SMALL": return solve(mid, hi, in, debug);
                case "TOO_BIG": return solve(lo, mid, in, debug);
            }
        }
        send(debug, mid);
        return "CORRECT".equals(in.next());
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
