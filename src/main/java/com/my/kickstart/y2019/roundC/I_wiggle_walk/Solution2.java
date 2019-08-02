package com.my.kickstart.y2019.roundC.I_wiggle_walk;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            //5 3 6 2 3
            int N = in.nextInt();
            int R = in.nextInt();
            int C = in.nextInt();
            int posR = in.nextInt();
            int posC = in.nextInt();
            String commands = in.next();
            System.out.print("Case #" + i + ": ");
            solve(R, C, posR, posC, commands);
        }
    }

    private static void solve(int R, int C, int posR, int posC, String commands) {
        Set<Integer> visited = new HashSet<>();
        visited.add(translate(posR, posC, C));

        for (int i = 0; i < commands.length(); i++) {
            switch (commands.charAt(i)) {
                case 'N': {
                    posR--;
                    while (visited.contains(translate(posR, posC, C))) {
                        posR--;
                    }
                    break;
                }
                case 'E': {
                    posC++;
                    while (visited.contains(translate(posR, posC, C))) {
                        posC++;
                    }
                    break;
                }
                case 'S': {
                    posR++;
                    while (visited.contains(translate(posR, posC, C))) {
                        posR++;
                    }
                    break;
                }
                case 'W': {
                    posC--;
                    while (visited.contains(translate(posR, posC, C))) {
                        posC--;
                    }
                    break;
                }
            }
            visited.add(translate(posR, posC, C));
        }
        System.out.println(posR + " " + posC);
    }

    private static Integer translate(int posR, int posC, int C) {
        return (posR - 1) * C + posC;
    }
}
