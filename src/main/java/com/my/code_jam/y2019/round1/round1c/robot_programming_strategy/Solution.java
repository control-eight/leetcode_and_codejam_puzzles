package com.my.code_jam.y2019.round1.round1c.robot_programming_strategy;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            //2^K - 1
            int A = in.nextInt();
            int K = (int) (Math.log(A + 1) / Math.log(2));
            List<String> programs = new ArrayList<>(A);
            for (int j = 0; j < A; j++) {
                programs.add(in.next());
            }
            System.out.print("Case #" + i + ": ");
            solve(K, programs);
        }
    }

    private static void solve(int K, List<String> programs) {
        Set[][] moves = new Set[500][3];
        for (int i = 0; i < 500; i++) {
            moves[i][0] = new HashSet();
            moves[i][1] = new HashSet();
            moves[i][2] = new HashSet();
        }
        for (int i = 0; i < programs.size(); i++) {
            String program = programs.get(i);
            for (int m = 0; m < 500; m++) {
                switch (program.charAt(m % program.length())) {
                    case 'R': moves[m][0].add(i); break;
                    case 'P': moves[m][1].add(i); break;
                    case 'S': moves[m][2].add(i); break;
                }
            }
        }
        //RPS
        Set<Integer> beaten = new HashSet<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < moves.length; i++) {
            Set[] move = moves[i];
            move[0].removeAll(beaten);
            move[1].removeAll(beaten);
            move[2].removeAll(beaten);
            if (!move[0].isEmpty() && !move[1].isEmpty() && !move[2].isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }
            if (!move[0].isEmpty()) {
                if (move[2].isEmpty()) {
                    beaten.addAll(move[0]);
                    result.append("P");
                } else {
                    beaten.addAll(move[2]);
                    result.append("R");
                }
            } else if (!move[1].isEmpty()) {
                beaten.addAll(move[1]);
                result.append("S");
            } else {
                beaten.addAll(move[2]);
                result.append("R");
            }
            if (beaten.size() == programs.size()) break;
        }
        if (beaten.size() == programs.size()) {
            System.out.println(result);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }
}
