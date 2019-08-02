package com.my.leetcode.race_car;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().racecar(3));
        //2 AA 0->1->3
        System.out.println(new Solution().racecar(6));
        //5 AAARA 0->1->3->7->7->6
    }

    public int racecar(int target) {
        int[][] solutions = new int[target * 2][target * 2];
        for (int[] solution : solutions) {
            Arrays.fill(solution, -1);
        }
        return backtracking(0, 1, 0, -1, target * 2, target, solutions, new HashSet<>());
    }

    private int backtracking(int point, int speed, int path, int min, int max, int target, int[][] solutions,
                             Set<Integer> pointsVisited) {
        if (point <= min || point >= max) return Integer.MAX_VALUE - target;
        if (point == target) return 0;
        if (!pointsVisited.add(point)) return Integer.MAX_VALUE - target;

        if (solutions[point][speed < 0? -speed*2: speed] != -1 && solutions[point][speed < 0? -speed*2: speed] < path) {
            return solutions[point][speed < 0? -speed*2: speed];
        }

        int result = backtracking(point + speed, speed * 2, path + 1,
                speed < 0? min: (target - point)/2 - 1, max, target, solutions, pointsVisited) + 1;
        pointsVisited.remove(point + speed);
        result = Math.min(result,
                backtracking(point, speed > 0? -1: 1, path, min, point + 1, target, solutions, pointsVisited) + 1);
        pointsVisited.remove(point);

        solutions[point][speed < 0? -speed*2: speed] = Math.min(solutions[point][speed < 0? -speed*2: speed], result);

        return result;
    }
}
