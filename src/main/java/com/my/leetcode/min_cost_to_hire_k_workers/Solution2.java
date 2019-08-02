package com.my.leetcode.min_cost_to_hire_k_workers;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;

public class Solution2 {

    public static void main(String[] args) throws IOException {
        /*System.out.println(new Solution2().mincostToHireWorkers(new int[] {3,1,10,10,1}, new int[] {4,8,2,2,7}, 3));
        System.out.println(new Solution2().mincostToHireWorkers(new int[] {10,20,5}, new int[] {70,50,30}, 2));
        System.out.println(new Solution2().mincostToHireWorkers(new int[] {14,56,59,89,39,26,86,76,3,36},
                new int[] {90,217,301,202,294,445,473,245,415,487}, 2));*/

        System.out.println(new Solution2().mincostToHireWorkers(new int[] {25,68,35,62,52,57,35,83,40,51},
                new int[] {147,97,251,129,438,443,120,366,362,343}, 6));

        /*int[] quality = readIntArray("/Users/oleksandrbykovskyi/Documents/1.txt");
        int[] wage = readIntArray("/Users/oleksandrbykovskyi/Documents/2.txt");
        long time = System.currentTimeMillis();
        System.out.println(new Solution2().mincostToHireWorkers(quality, wage,7933));
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

    /*
    Example 1:
    Input: quality = [10,20,5], wage = [70,50,30], K = 2
    Output: 105.00000
    Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.

    //2.5, 6, 7

    Example 2:

    Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
    Output: 30.66667
    Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.*/

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double min = Integer.MAX_VALUE;
        List<Integer> minSolution = new ArrayList<>();
        double minSolutionRatio = Double.MAX_VALUE;
        for (int i = 0; i < quality.length; i++) {
            double ratio = BigDecimal.valueOf(wage[i])
                    .divide(BigDecimal.valueOf(quality[i]), 15, RoundingMode.HALF_UP)
                    .doubleValue();
            Container[] qualities = new Container[10001];
            int k = 0;
            for (int j = 0; j < quality.length; j++) {
                if (ratio * quality[j] >= wage[j] - 10e-5) {
                    if (qualities[quality[j]] == null) {
                        qualities[quality[j]] = new Container(0);
                    }
                    qualities[quality[j]].count++;
                    qualities[quality[j]].indexSet.add(j);
                    k++;
                }
            }
            if (k >= K) {
                double sum = 0;
                List<Integer> solution = new ArrayList<>();
                for (int z = 0, size = 0; z < qualities.length && size < K; z++) {
                    if (qualities[z] != null) {
                        while (qualities[z].count > 0 && size < K) {
                            sum += z;
                            size++;
                            qualities[z].count--;
                            solution.add(qualities[z].indexSet.poll());
                        }
                    }
                }
                if (ratio * sum < min) {
                    min = Math.min(min, ratio * sum);
                    minSolution = solution;
                    minSolutionRatio = Math.min(minSolutionRatio, ratio);
                }
            }
        }
        System.out.println(minSolutionRatio);
        System.out.println(minSolution);
        return min;
    }

    private static class Container {
        private int count;
        private Queue<Integer> indexSet = new LinkedList<>();

        public Container(int count) {
            this.count = count;
        }
    }

    //N^2 * log(N)
}
