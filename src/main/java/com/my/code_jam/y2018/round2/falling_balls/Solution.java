package com.my.code_jam.y2018.round2.falling_balls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author abykovsky
 * @since 5/19/18
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int C = in.nextInt();
            int[] arr = new int[C];
            for (int i = 0; i < C; i++) {
                arr[i] = in.nextInt();
            }
            List<List<Character>> result = solve(arr);
            System.out.println("Case #"+t+": "+ (result == null? "IMPOSSIBLE": result.size()));
            if (result != null) {
                for (List<Character> chars : result) {
                    for (char aChar : chars) {
                        System.out.print(aChar);
                    }
                    System.out.println();
                }
            }
        }
    }

    private static List<List<Character>> solve(int[] arr) {

        if (arr[0] == 0 || arr[arr.length - 1] == 0) return null;

        int hi = -1;
        int i = 0;
        while (i < arr.length) {
            hi += arr[i];
            i++;
        }


        /*int max = 0;
        for (int i : arr) {
            max = Math.max(max, i);
        }*/





        return null;
    }


}
