package com.my.code_jam.y2019.round1.round1c.power_arrangers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Solution {

    private static boolean isDebug = false;

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int F = in.nextInt();
        int index = 1;

        File out = initDebug();
        try (PrintWriter debug = new PrintWriter(new FileWriter(out))) {
            solution(in, T, index, debug);
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

    private static void solution(Scanner in, int t, int index, PrintWriter debug) {
        while (index <= t) {
            StringBuilder sb = new StringBuilder();
            Map<Character, List<Integer>> counter = new HashMap<>();
            Set<Character> found = new HashSet<>();
            Set<Integer> skip = new HashSet<>();
            int count = 0;
            initCounter(counter, found);
            for (int k = 1; k <= 5; k++) {
                for (int i = 0; i < 119; i++) {
                    if (!skip.contains(i)) {
                        count++;
                        send(debug, i * 5 + k);
                        counter.get(in.next().charAt(0)).add(i);
                    }
                }

                int min = Integer.MAX_VALUE;
                Character minChar = null;
                debug.println(counter);
                for (Map.Entry<Character, List<Integer>> entry : counter.entrySet()) {
                    if (entry.getValue().size() < min) {
                        min = entry.getValue().size();
                        minChar = entry.getKey();
                    }
                }
                sb.append(minChar);
                found.add(minChar);
                for (Map.Entry<Character, List<Integer>> entry : counter.entrySet()) {
                    if (!entry.getKey().equals(minChar)) {
                        skip.addAll(entry.getValue());
                    }
                }
                debug.println(sb);
                initCounter(counter, found);
            }
            send(debug, sb);
            debug.println("count = " + count);
            if (!"Y".equals(in.next())) throw new RuntimeException();
            index++;
        }
    }

    private static void initCounter(Map<Character, List<Integer>> counter, Set<Character> found) {
        for (Character c : Arrays.asList('A', 'B', 'C', 'D', 'E')) {
            if (!found.contains(c)) {
                counter.put(c, new ArrayList<>());
            } else {
                counter.remove(c);
            }
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
