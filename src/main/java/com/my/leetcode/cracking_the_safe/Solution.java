package com.my.leetcode.cracking_the_safe;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().crackSafe(1, 2));
        System.out.println(new Solution().crackSafe(2, 2));
        System.out.println(new Solution().crackSafe(2, 1));
        System.out.println(new Solution().crackSafe(3, 2));
        System.out.println(new Solution().crackSafe(3, 3));
    }

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append("0");
        }
        StringBuilder acc = new StringBuilder();
        dfs(sb.toString(), k, new HashSet<>(), acc);
        acc.append(sb);
        return acc.toString();
    }

    private void dfs(String node, int k, Set<String> seen, StringBuilder acc) {
        for (int i = 0; i < k; i++) {
            String edge = node + i;
            if (seen.add(edge)) {
                dfs(edge.substring(1), k, seen, acc);
                acc.append(i);
            }
        }
    }
}
