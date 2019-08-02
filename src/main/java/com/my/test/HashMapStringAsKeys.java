package com.my.test;

import org.apache.commons.lang.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

public class HashMapStringAsKeys {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        int[] check = new int[] {1, 10, 100, 500, 1000, 3000, 5000, 10000, 20000, 50000, 100000, 200000, 500000};
        int count = 100000;
        for (int i : check) {
            String[] list = new String[count];
            for (int c = 0; c < count; c++) {
                list[c] = RandomStringUtils.randomAlphabetic(i);
            }
            long start = System.currentTimeMillis();
            for (int c = 0; c < count; c++) {
                map.put(list[c], c);
            }
            System.out.println(i + " " + (System.currentTimeMillis() - start));
        }
    }
}
