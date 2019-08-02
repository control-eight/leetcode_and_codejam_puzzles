package com.my.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Test2 {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(5, 1);
        treeMap.put(7, 1);
        treeMap.put(15, 1);

        System.out.println(treeMap.floorEntry(4));
        System.out.println(treeMap.floorEntry(5));
        System.out.println(treeMap.floorEntry(7));
        System.out.println(treeMap.ceilingEntry(7));

        System.out.println(treeMap.subMap(6, 14));

        System.out.println(Integer.lowestOneBit(8));

        //System.out.println(Integer.bitCount());
        System.out.println(countFirstOneBytes(7));

        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(3);
        set2.add(4);
        System.out.println(set1.containsAll(set2));
    }

    private static int countFirstOneBytes(int d) {
        int mask = 1;
        int count = 0;
        while ((mask & d) != 0) {
            mask <<= 1;
            count++;
        }
        return count;
    }
}
