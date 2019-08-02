package com.my.code_jam.y2019.qualification.сryptopangrams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int N = in.nextInt();
            int L = in.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= L; j++) {
                list.add(in.nextInt());
            }
            solve(i, N, list);
        }
    }

    public static String solve(int testCase, int N, List<Integer> list) {
        Set<Integer> set = new HashSet<>();
        int[][] chars = new int[list.size()][2];
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) {
                if (list.get(0) % i == 0) {
                    chars[0] = new int[]{ i, list.get(0) / i };
                    set.add(i);set.add(list.get(0) / i);
                    break;
                }
            }
        }

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) % chars[i - 1][0] == 0) {
                chars[i] = new int[]{ list.get(i) / chars[i - 1][0], chars[i - 1][0]};
            } else {
                chars[i] = new int[]{ list.get(i) / chars[i - 1][1], chars[i - 1][1]};
            }
            set.add(chars[i][0]);set.add(chars[i][1]);
        }

        if (set.size() != 26) {
            throw new RuntimeException();
        }

        Map<Integer, Character> map = createIndexMap(set);
        System.out.print("Case #" + testCase + ": ");

        boolean connected = false;
        if (list.get(1) % chars[0][0] == 0) {
            swap(chars, 0);
            connected = connected(chars, 1);
            if (!connected) {
                swap(chars, 0);
            }
        }
        if (!connected) {
            connected(chars, 1);
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i][0] != chars[i - 1][1]) {
                throw new RuntimeException();
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(map.get(chars[i][0]));
            result.append(map.get(chars[i][0]));
        }
        System.out.println(map.get(chars[chars.length - 1][1]));
        result.append(map.get(chars[chars.length - 1][1]));
        return result.toString();
    }

    private static Map<Integer, Character> createIndexMap(Set<Integer> set) {
        List<Integer> solutionsList = new ArrayList<>(set);
        Collections.sort(solutionsList);
        Map<Integer, Character> map = new HashMap<>();
        int i = 0;
        int A = (int)'A';
        for (Integer integer : solutionsList) {
            map.put(integer, (char) (A + i));
            i++;
        }
        return map;
    }

    private static boolean connected(int arr[][], int i) {
        if (i == arr.length) return true;
        if (i > 0 && (arr[i][0] != arr[i - 1][1] && arr[i][1] != arr[i - 1][1])) return false;
        if (i > 0 && arr[i][1] == arr[i - 1][1]) swap(arr, i);
        return connected(arr, i + 1);
    }

    private static void swap(int[][] arr, int i) {
        int tmp = arr[i][1];
        arr[i][1] = arr[i][0];
        arr[i][0] = tmp;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0) return false;
        return true;
    }
}
