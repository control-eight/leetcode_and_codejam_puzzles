package com.my.codechef.jan21b.antschef;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-10, -8, -3, 0, 1, 5, 7, 12);
        System.out.println(Collections.binarySearch(list, -10));
        System.out.println(Collections.binarySearch(list, 5));
        System.out.println(Collections.binarySearch(list, -7));
        System.out.println(Collections.binarySearch(list, 4));
    }
}
