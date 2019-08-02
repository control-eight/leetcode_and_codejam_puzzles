package com.my.leetcode.min_cost_to_hire_k_workers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().mincostToHireWorkers(new int[] {1,2}, new int[] {14,16}, 1));
        System.out.println(new Solution().mincostToHireWorkers(new int[] {25,68,35,62,52,57,35,83,40,51},
                new int[] {147,97,251,129,438,443,120,366,362,343}, 6));
        System.out.println(new Solution().mincostToHireWorkers(new int[] {10,20,5}, new int[] {70,50,30}, 2));
        System.out.println(new Solution().mincostToHireWorkers(new int[] {3,1,10,10,1}, new int[] {4,8,2,2,7}, 3));
        System.out.println(new Solution().mincostToHireWorkers(new int[] {14,56,59,89,39,26,86,76,3,36},
                new int[] {90,217,301,202,294,445,473,245,415,487}, 2));
        /*int[] quality = readIntArray("/Users/oleksandrbykovskyi/Documents/1.txt");
        int[] wage = readIntArray("/Users/oleksandrbykovskyi/Documents/2.txt");
        long time = System.currentTimeMillis();
        System.out.println(new Solution().mincostToHireWorkers(quality, wage,7933));
        System.out.println((System.currentTimeMillis() - time) + "ms");*/
    }

    private static int[] readIntArray(String file) throws IOException {
        List<Integer> acc = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        for (String s : sb.toString().split(",")) {
            acc.add(Integer.parseInt(s));
        }
        int[] arr = new int[acc.size()];
        for (int i = 0; i < acc.size(); i++) {
            arr[i] = acc.get(i);
        }
        return arr;
    }


    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {

        if (K==1) {
            int min = Integer.MAX_VALUE;
            for (int i : wage) {
                min = Math.min(min, i);
            }
            return min;
        }

        List<Container> ratios = new ArrayList<>(quality.length);
        for (int i = 0; i < quality.length; i++) {
            ratios.add(new Container(
                    BigDecimal.valueOf(wage[i])
                            .divide(BigDecimal.valueOf(quality[i]), 15, RoundingMode.HALF_UP)
                            .doubleValue(), i
            ));
        }
        Collections.sort(ratios);

        double min = Integer.MAX_VALUE;
        int sum = 0;
        PriorityQueue<Integer> maxQuality = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int i = 0; i < K - 1; i++) {
            sum += quality[ratios.get(i).index];
            maxQuality.offer(quality[ratios.get(i).index]);
        }

        for (int i = K - 1; i < quality.length; i++) {
            min = Math.min(min, (sum + quality[ratios.get(i).index]) * ratios.get(i).ratio);
            if (quality[ratios.get(i).index] < maxQuality.peek()) {
                sum -= maxQuality.poll();
                maxQuality.offer(quality[ratios.get(i).index]);
                sum += quality[ratios.get(i).index];
            }
        }
        return min;
    }

    private static class Container implements Comparable<Container> {
        private double ratio;
        private int index;

        public Container(double ratio, int index) {
            this.ratio = ratio;
            this.index = index;
        }

        @Override
        public int compareTo(Container o) {
            return Double.compare(ratio, o.ratio);
        }
    }
}
