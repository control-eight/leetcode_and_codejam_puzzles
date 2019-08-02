package com.my.leetcode.output_contest_matches;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findContestMatch(2));
        //(1,2)
        System.out.println(new Solution().findContestMatch(4));
        //((1,4),(2,3))
        System.out.println(new Solution().findContestMatch(8));
        //(((1,8),(4,5)),((2,7),(3,6)))
        System.out.println(new Solution().findContestMatch(16));
        //((((1,16),(8,9)),((4,13),(5,12))),(((2,15),(7,10)),((3,14),(6,11))))
    }

    public String findContestMatch(int n) {
        List<String> pairs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            pairs.add(i + "");
        }
        return generate(pairs);
    }

    private String generate(List<String> pairs) {
        if (pairs.size() == 2) {
            return pair(pairs.get(0), pairs.get(1));
        }

        int i = 0;
        int j = pairs.size() - 1;
        List<String> newPairs = new ArrayList<>();
        while (i < j) {
            newPairs.add(pair(pairs.get(i), pairs.get(j)));
            i++; j--;
        }
        return generate(newPairs);
    }

    private String pair(String one, String two) {
        return "(" + one + "," + two + ")";
    }
}
