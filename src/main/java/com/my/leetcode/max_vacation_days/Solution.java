package com.my.leetcode.max_vacation_days;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxVacationDays(new int[][] {
                {0,1,1},{1,0,1},{1,1,0}
        }, new int[][] {
                {1,3,1},{6,0,3},{3,3,3}
        }));
        //12
        System.out.println(new Solution().maxVacationDays(new int[][] {
                {0,0,0},{0,0,0},{0,0,0}
        }, new int[][] {
                {1,1,1},{7,7,7},{7,7,7}
        }));
        //3
        System.out.println(new Solution().maxVacationDays(new int[][] {
                {0,1,1},{1,0,1},{1,1,0}
        }, new int[][] {
                {7,0,0},{0,7,0},{0,0,7}
        }));
        //21
    }

    public int maxVacationDays(int[][] flights, int[][] days) {
        int K = days[0].length;
        int[][] cityAndWeek = new int[flights.length][K];
        for (int i = 0; i < cityAndWeek.length; i++) {
            Arrays.fill(cityAndWeek[i], -1);
        }
        for (int i = 0; i < flights.length; i++) {
            flights[i][i] = 1;
        }
        return dfs(flights, days, 0,0, K, cityAndWeek);
    }

    private int dfs(int[][] flights, int[][] days, int city, int week, int K, int[][] cityAndWeek) {
        if (week == K) {
            return 0;
        }
        if (cityAndWeek[city][week] > -1) {
            return cityAndWeek[city][week];
        }
        int result = 0;
        for (int j = 0; j < flights[city].length; j++) {
            if (flights[city][j] == 1) {
                result = Math.max(result, dfs(flights, days, j, week + 1, K, cityAndWeek) + days[j][week]);
            }
        }
        cityAndWeek[city][week] = result;
        return result;
    }
}
