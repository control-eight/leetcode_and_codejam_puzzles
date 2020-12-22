package com.my.misc.string_hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Author: Oleksandr Bykovskyi
 * Date: 2020-05-17
 */
public class Test {

    public static void main(String[] args) {
        Map<StringHash, Integer> stringHashesMap = new HashMap<>();
        Map<String, Integer> stringsMap = new HashMap<>();
        int lc = 3;
        int strLength = 100000;
        int maxSubstrLength = 500;
        int hashMapWordsMaxSize = 10000;
        char start = 'a';

        Random random = new Random(5555);
        String str = randomString(strLength, lc, random);
        addRandomSubstrs(str, stringHashesMap, stringsMap, random, lc, hashMapWordsMaxSize, maxSubstrLength, start);
        speedCheck(stringHashesMap, stringsMap, lc, maxSubstrLength, str, start);
        //testCheck(stringHashesMap, stringsMap, lc, maxSubstrLength, str, start);
    }

    private static void speedCheck(Map<StringHash, Integer> stringHashesMap, Map<String, Integer> stringsMap, int lc,
                                  int maxSubstrLength, String str, char start) {
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < str.length(); i++) {
            StringHash stringHash = new StringHash(lc, start);
            for (int j = i; j < Math.min(i + maxSubstrLength, str.length()); j++) {
                stringHash.add(str.charAt(j));
                stringHashesMap.get(stringHash);
            }
        }
        System.out.println("StringHash: " + (System.currentTimeMillis() - start1) + "ms");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < str.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < Math.min(i + maxSubstrLength, str.length()); j++) {
                sb.append(str.charAt(j));
                stringsMap.get(sb.toString());
            }
        }
        System.out.println("Strings: " + (System.currentTimeMillis() - start2) + "ms");
    }

    private static void testCheck(Map<StringHash, Integer> stringHashesMap, Map<String, Integer> stringsMap, int lc,
                                  int maxSubstrLength, String str, char start) {
        int hashMapHits = 0;
        for (int i = 0; i < str.length(); i++) {
            StringHash stringHash = new StringHash(lc, start);
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < Math.min(i + maxSubstrLength, str.length()); j++) {
                stringHash.add(str.charAt(j));
                sb.append(str.charAt(j));

                Integer r1 = stringHashesMap.get(stringHash);
                Integer r2 = stringsMap.get(sb.toString());
                if (!Objects.equals(r1, r2)) {
                    throw new RuntimeException();
                }
                if (r1 != null) {
                    hashMapHits++;
                }
            }
        }
        System.out.println("hashMap hits: " + hashMapHits);
        System.out.println("all hits [StringHash]: " + StringHash.allGets);
        System.out.println("nonPossibleCollisionHits hits [StringHash]: " + StringHash.nonPossibleCollisionHits);
    }

    private static void addRandomSubstrs(String str, Map<StringHash, Integer> stringHashesMap,
                                         Map<String, Integer> stringsMap, Random random,
                                         int lc, int size, int maxSubstrLength, char start) {
        for (int n = 0; n < size; n++) {
            int lo = (int) (random.nextDouble() * str.length());
            int hi = Math.min(str.length(), lo + ((int) (random.nextDouble() * maxSubstrLength) + 1));
            String substring = str.substring(lo, hi);
            stringHashesMap.put(new StringHash(lc, start, substring), n);
            stringsMap.put(substring, n);
        }
    }

    private static String randomString(int l, int lc, Random random) {
        char[] c = new char[l];
        for (int i = 0; i < l; i++) {
            c[i] = (char) ((int) (random.nextDouble() * lc) + 'a');
        }
        return String.valueOf(c);
    }
}
