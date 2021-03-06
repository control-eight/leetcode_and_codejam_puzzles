package com.my.hackerrank.hack_the_interview_II.min_string_coeff;

import java.io.*;
import java.util.*;

class Result1 {

    /*
     * Complete the 'minStringCoeff' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER p
     */

    public static int minStringCoeff(String s, int p) {
        // Write your code here
        if (s.length() <= 2) return 0;

        int originalSolution = estimate(s);
        //if (p == 0) return originalSolution;

        if (originalSolution == 0) return 0;

        List<Integer> leftArr = calc(s, p);
        List<Integer> rightArr = calc(new StringBuilder(s).reverse().toString(), p);

        int leftSum = 0;
        for (Integer integer : leftArr) {
            leftSum += integer;
        }

        int rightSum = 0;
        int min = originalSolution - leftSum;
        for (int i = 0; i < leftArr.size(); i++) {
            leftSum -= leftArr.get(leftArr.size() - i - 1);
            rightSum += rightArr.get(i);

            min = Math.min(originalSolution - leftSum - rightSum, min);
        }
        //System.out.println(s + " " + p + " estimate = " + estimate(s));
        //System.out.println(leftArr + " " + rightArr);
        return Math.max(0, min);
    }

    private static List<Integer> calc(String s, int p) {
        int i = 0;
        int p1 = 0;
        List<Integer> result = new ArrayList<>();
        do {
            int start = i;
            do {
                i++;
            } while (i < s.length() && s.charAt(i) == s.charAt(start));
            if (s.charAt(i - 1) != s.charAt(0)) {
                p1++;
                if (p1 > p) break;
            }
            if (p1 > 0) {
                if (s.charAt(i - 1) != s.charAt(0)) {
                    result.add(i - start);
                } else {
                    result.set(result.size() - 1, result.get(result.size() - 1) + (i - start));
                }
            }
        } while (i < s.length());
        return result;
    }

    private static int estimate(String s1) {
        int left = 0;
        do {
            left++;
        } while (left < s1.length() && s1.charAt(left) == s1.charAt(0));

        int right = s1.length() - 1;
        do {
            right--;
        } while (right >= 0 && s1.charAt(right) == s1.charAt(s1.length() - 1));

        int result = right - left + 1;
        return result > 0? result: 0;
    }

    public static int minStringCoeff2(String s, int p) {
        // Write your code here
        if (s.length() <= 2) return 0;

        List<char[]> generate = gen(s.toCharArray(),0, p);

        int min = Integer.MAX_VALUE;
        String s2 = null;
        for (char[] sc : generate) {
            String s1 = String.valueOf(sc);
            int result = estimate(s1);
            if (result < min) {
                min = result;
                s2 = s1;
            }
        }
        System.out.println(s + " " + estimate(s) + " res = " + min + " " + s2);
        return min;
    }

    private static List<char[]> gen(char[] s, int cursor, int p) {
        if (p == 0 || cursor == s.length) {
            return Collections.singletonList(s);
        }
        List<char[]> r = new ArrayList<>(gen(s, cursor + 1, p));
        for (int i = cursor; i < s.length; i++) {
            char[] s1 = new char[s.length];
            System.arraycopy(s, 0, s1, 0, s.length);
            for (int j = cursor; j <= i; j++) {
                s1[j] = s[j] == '1'? '0': '1';
            }
            r.addAll(gen(s1, i + 1, p - 1));
        }
        return r;
    }

    public static void main(String[] args) {
        List<String> test = Arrays.asList(
                "1101",
                "1011010",
                "101010100011",
                "100001",
                "111111",
                "000000",
                "10",
                "01",
                "100010001000",
                "11001110011",
                "11001010011",
                "11111110000",
                "11111111111",
                "111000111",
                "1110010111000",
                "101011100011",
                "1010101010000000001111111111",
                "1010101010001101010111",
                "10001010101101111",
                "0000111100001111",
                "00001111000011110000"
        );
        /*for (String s : test) {
            System.out.println(s + " " + Result1.estimate(s));
        }*/
        run(test, 1);
        //run(test, 2);
        //run(test, 3);
        //run(test, 4);

        //generate();
    }

    private static void generate() {
        int length = 15;
        int testCases = 150000;
        int p = 5;
        Random random = new Random(543545);
        for (int t = 0; t < testCases; t++) {
            char[] carr = new char[length];
            for (int i = 0; i < length; i++) {
                carr[i] = random.nextDouble() >= 0.5? '1': '0';
            }
            for (int p1 = 0; p1 <= p; p1++) {
                String str = new String(carr);
                int r1 = Result1.minStringCoeff(str, p1);
                int r2 = Result1.minStringCoeff2(str, p1);
                if (r1 != r2) {
                    System.out.println(str + " p = " + p1 + " estimate = " + Result1.estimate(str));
                    System.out.println(r1 + " vs " + r2);
                }
            }
            //System.out.println(t);
        }
    }

    private static void run(List<String> test, int p) {
        System.out.println("------------ p = " + p + " ------------");
        for (int i = 0; i < test.size(); i++) {
            int r1 = Result1.minStringCoeff(test.get(i), p);
            int r2 = Result1.minStringCoeff2(test.get(i), p);
            if (r1 != r2) {
                System.out.println(test.get(i) + " estimate = " + Result1.estimate(test.get(i)));
                System.out.println(r1 + " vs " + r2);
            }
        }
    }

}

public class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int p = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        int res = Result1.minStringCoeff(s, p);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
