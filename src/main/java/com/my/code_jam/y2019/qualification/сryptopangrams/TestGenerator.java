package com.my.code_jam.y2019.qualification.сryptopangrams;

import java.util.*;

public class TestGenerator {

    public static void main(String[] args) {
        int testCases = 312321312;
        Random random = new Random(55);
        for (int i = 1; i < testCases; i++) {
            int N = Math.max(101, random.nextInt(10000));
            Set<Integer> numbers = new HashSet<>();
            List<Integer> primeNumbers = generatePrimeNumbers(N);
            for (int j = 0; j < 26; j++) {
                int number = random.nextInt(primeNumbers.size());
                while (numbers.contains(primeNumbers.get(number))) {
                    number = random.nextInt(primeNumbers.size());
                }
                numbers.add(primeNumbers.get(number));
            }
            List<Integer> sorted = new ArrayList<>(numbers);
            Collections.sort(sorted);

            Map<Integer, Integer> indexMap = new HashMap<>();
            for (int j = 0; j < 26; j++) {
                indexMap.put(j, sorted.get(j));
            }

            Set<Integer> chars = new HashSet<>();
            List<Integer> resChars = new ArrayList<>();
            StringBuilder expected = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                int number = random.nextInt(26);
                while (!chars.add(number)) {
                    number = random.nextInt(26);
                }
                resChars.add(number);
                expected.append((char)((int) 'A' + number));
            }
            for (int j = 0; j < random.nextInt(25); j++) {
                int number = random.nextInt(26);
                chars.add(number);
                resChars.add(number);
                expected.append((char)((int) 'A' + number));
            }

            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < resChars.size() - 1; j++) {
                list.add(indexMap.get(resChars.get(j)) * indexMap.get(resChars.get(j + 1)));
            }

            String result = Solution.solve(i, N, list);
            if (!expected.toString().equals(result)) {
                System.out.println(expected.toString() + " != " + result + "; " + "N = " + N);
                return;
            } else {
                System.out.println(expected.toString() + " == " + result);
            }
        }
    }

    private static List<Integer> generatePrimeNumbers(int N) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0) return false;
        return true;
    }
}
