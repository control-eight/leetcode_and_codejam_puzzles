package com.my.leetcode.exclusive_time_of_functions;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().exclusiveTime(2, Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6"))));
        System.out.println(Arrays.toString(new Solution().exclusiveTime(1, Arrays.asList("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"))));
        System.out.println(Arrays.toString(new Solution().exclusiveTime(2, Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"))));
        System.out.println(Arrays.toString(new Solution().exclusiveTime(1, Arrays.asList("0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5"))));
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] functionsTime = new int[n];
        int[] executionTime = new int[logs.size()];
        Deque<Container> stack = new LinkedList<>();
        for (int i = 0; i < logs.size(); i++) {
            String log = logs.get(i);
            String[] split = log.split(":");
            int id = Integer.parseInt(split[0]);
            boolean start = "start".equals(split[1]);
            int time = Integer.parseInt(split[2]);
            if (!start) {
                Container prev = stack.pollFirst();
                int deltaPrev = i > 0 ? executionTime[i - 1] : 0;
                int delta = time - deltaPrev - prev.time + 1;
                functionsTime[id] += delta;
                executionTime[i] += (delta + deltaPrev);
                if (prev.timeIndex > 0) {
                    executionTime[i] += executionTime[prev.timeIndex - 1];
                }
            } else {
                stack.offerFirst(new Container(i, time));
                executionTime[i] = 0;
            }
        }
        return functionsTime;
    }

    private static class Container {
        private int timeIndex;
        private int time;
        public Container(int timeIndex, int time) {
            this.timeIndex = timeIndex;
            this.time = time;
        }
    }
}
