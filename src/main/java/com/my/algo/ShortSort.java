package com.my.algo;

import java.util.*;

public class ShortSort {

    public static void main(String[] args) {
        test(arr -> new ShortSort().shortSort(arr));
    }

    private void shortSort(int[] arr) {
        List<Integer> sortedList = new ArrayList<>();
        for (int val : arr) {
            int index = Collections.binarySearch(sortedList, val);
            sortedList.add(index >= 0? index: -index - 1, val);
        }
        for (int i = 0; i < sortedList.size(); i++) arr[i] = sortedList.get(i);
    }

    private interface Sort {
        void sort(int[] arr);
    }

    private static void test(Sort sort) {
        Random random = new Random(55);
        for(int i = 0; i < 1; i++) {
            int length = (int) (random.nextDouble() * 10);

            int[] origin = new int[length];
            int[] anArr1 = new int[length];
            int[] anArr2 = new int[length];
            for(int j = 0; j < length; j++) {
                anArr1[j] = (int) (random.nextDouble() * 10);
                anArr2[j] = anArr1[j];
                origin[j] = anArr1[j];
            }
            sort.sort(anArr1);
            Arrays.sort(anArr2);

            if(!Arrays.equals(anArr1, anArr2)) {
                System.out.println(Arrays.toString(anArr1) + " from " + Arrays.toString(origin));
            }
        }
    }
}
