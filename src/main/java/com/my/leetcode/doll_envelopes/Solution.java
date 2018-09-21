package com.my.leetcode.doll_envelopes;

import java.util.*;

public class Solution {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;

        Arrays.sort(envelopes, (o1, o2) -> {
            int r1 = Integer.compare(o1[0], o2[0]);
            return r1 == 0? Integer.compare(o1[1], o2[1]): r1;
        });

        List<Container> solutions = new LinkedList<>();
        solutions.add(new Container(envelopes[0], 1));
        int max = 1;
        for(int i = 1; i < envelopes.length; i++) {
            int maxLocalCount = 0;
            for (Container envelope : solutions) {
                if (envelopes[i][0] > envelope.value[0] && envelopes[i][1] > envelope.value[1]) {
                    maxLocalCount = Math.max(maxLocalCount, envelope.count);
                }
            }
            solutions.add(new Container(envelopes[i], maxLocalCount + 1));
            max = Math.max(maxLocalCount + 1, max);
        }
        return max;
    }

    private static class Container {
        private int[] value;
        private int count;

        public Container(int[] value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
