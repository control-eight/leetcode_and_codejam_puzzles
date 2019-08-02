package com.my.leetcode.find_shortest_superstr;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().shortestSuperstring(new String[] {
                "alex","loves","leetcode","alex"
        }));
        //alexlovesleetcode
        //alexlovesleetcode
        System.out.println(new Solution().shortestSuperstring(new String[] {
                "catg","ctaagt","gcta","ttca","atgcatc"
        }));
        //gctaagttcatgcatc
        //catgctagcatcagttca

        /*LinkedHashSet<Integer> s = new LinkedHashSet<>();
        s.add(2);s.add(1);s.add(3);s.add(0);s.add(4);
        System.out.println(new Solution().merge(new String[] {
                "catg","ctaagt","gcta","ttca","atgcatc"
        }, s));*/
        long start = System.currentTimeMillis();
        System.out.println(new Solution().shortestSuperstring(new String[] {
                "uhozqhxcqmkifljvcie","epuhozqhxcqmkifljvci","ugmqnepuhozqhxcqm","iexdudtvurjkrovrhmpa","rovrhmpaasblgaosw","qmkifljvciexdudtv","zsgtuowskyzphybeugm","uowskyzphybeugmq","qhxcqmkifljvciex"
        }));
        System.out.println(System.currentTimeMillis() - start);
    }

    public String shortestSuperstring(String[] A) {
        return solve(filterOutDuplicates(A), new LinkedHashSet<>());
    }

    private String solve(String[] a, LinkedHashSet<Integer> order) {
        if (order.size() == a.length) {
            return merge(a, order);
        }
        int l = Integer.MAX_VALUE;
        String result = null;
        for (int i = 0; i < a.length; i++) {
            if (!order.contains(i)) {
                order.add(i);
                String r = solve(a, order);
                order.remove(i);
                if (r.length() < l) {
                    result = r;
                    l = r.length();
                }
            }
        }
        return result;
    }

    private String merge(String[] a, LinkedHashSet<Integer> order) {
        Iterator<Integer> iter = order.iterator();
        StringBuilder output = new StringBuilder(a[iter.next()]);
        while (iter.hasNext()) {
            String n = a[iter.next()];
            boolean found = false;
            for (int i = 0; i < output.length(); i++) {
                if (n.startsWith(output.substring(i))) {
                    output.append(n.substring(output.length() - i));
                    found = true;
                    break;
                }
            }
            if (!found) {
                output.append(n);
            }
        }
        return output.toString();
    }

    private String[] filterOutDuplicates(String[] A) {
        Set<String> b = new LinkedHashSet<>();
        b.addAll(Arrays.asList(A));
        String[] B = new String[b.size()];
        int i = 0;
        for (String s : b) {
            B[i++] = s;
        }
        return B;
    }
}
