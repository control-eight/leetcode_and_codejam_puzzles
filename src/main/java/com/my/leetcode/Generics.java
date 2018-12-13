package com.my.leetcode;

public class Generics {

    public static void main(String[] args) {
        operate(new Cat());
    }

    private static <T extends Cat> void operate(T t) {
        System.out.println(t);
    }

    private static class Cat {

    }

    private static class BetterCat extends Cat {

    }
}
