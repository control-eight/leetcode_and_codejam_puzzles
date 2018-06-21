package com.my.code_jam.y2018.round2.falling_balls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author abykovsky
 * @since 5/19/18
 */
public class Solution2 {

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

        boolean allOne = true;
        for (int i : arr) {
            allOne &= i == 1;
        }
        if (allOne) {
            List<List<Character>> result = new ArrayList<>();
            List<Character> list = new ArrayList<>();
            for (int i : arr) {
                list.add('.');
            }
            result.add(list);
            return result;
        }

        arr[0]--;
        arr[arr.length - 1]--;

        char[][] chars = new char[arr.length - 2][arr.length - 2];

        List<List<Character>> result = generateAndCheck(arr, chars, 0, 0);

        return result;
    }

    private static List<List<Character>> generateAndCheck(int[] arr, char[][] chars, int i, int j) {

        if (i == arr.length - 2) {

            List<List<Character>> result = new ArrayList<>();

            for (int k = 0; k < chars.length; k++) {
                int dotCount = 0;
                for (int l = 0; l < chars[0].length; l++) {
                    if (l > 0 && (chars[k][l - 1] == '\\' && chars[k][l] == '/')) {
                        return null;
                    }
                    if (chars[k][l] == '.') {
                        dotCount++;
                    }
                }
                if (dotCount != chars[0].length) {
                    List<Character> list = new ArrayList<>();
                    for (char c : chars[k]) {
                        list.add(c);
                    }
                    result.add(list);
                }
            }

            int[] arr1 = check(arr, result);
            return Arrays.equals(arr, arr1)? correct(result): null;
        } else {
            chars[i][j] = '.';
            List<List<Character>> result = generateAndCheck(arr, chars, (j + 1 > arr.length - 3? i + 1: i), (j + 1 > arr.length - 3? 0: j + 1));

            chars[i][j] = '\\';
            List<List<Character>> result1 = generateAndCheck(arr, chars, (j + 1 > arr.length - 3? i + 1: i), (j + 1 > arr.length - 3? 0: j + 1));

            chars[i][j] = '/';
            List<List<Character>> result2 = generateAndCheck(arr, chars, (j + 1 > arr.length - 3? i + 1: i), (j + 1 > arr.length - 3? 0: j + 1));

            if (result != null && (result1 == null || result.size() <= result1.size()) && (result2 == null || result.size() <= result2.size())) {
                return result;
            } else if (result1 != null && (result == null || result1.size() <= result.size()) && (result2 == null || result1.size() <= result2.size())) {
                return result1;
            } else if (result2 != null) {
                return result2;
            } else {
                return null;
            }
        }
    }

    private static List<List<Character>> correct(List<List<Character>> result) {

        for (List<Character> characters : result) {
            characters.add(0, '.');
            characters.add('.');
        }

        List<Character> dots = new ArrayList<>();
        for (Character characters : result.get(0)) {
            dots.add('.');
        }
        result.add(dots);

        return result;
    }

    private static int[] check(int[] arr, List<List<Character>> result) {
        int[] arr1 = new int[arr.length];
        for (int l = 0; l < arr.length - 2; l++) {
            int col = l + 1;
            for (int k = 0; k < result.size(); k++) {
                if (result.get(k).get(col - 1) == '\\') {
                    col++;
                } else if (result.get(k).get(col - 1) == '/') {
                    col--;
                }
                if (col == 0 || col == arr.length - 1) break;
            }
            arr1[col]++;
        }
        return arr1;
    }
}
