package com.my.leetcode.fizz_buzz;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().fizzBuzz(15));
    }

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            boolean b1 = i % 3 == 0;
            boolean b2 = i % 5 == 0;
            if (b1 && b2) {
                result.add("FizzBuzz");
            } else if (b1) {
                result.add("Fizz");
            } else if (b2) {
                result.add("Buzz");
            } else {
                result.add(i + "");
            }
        }
        return result;
    }
}
