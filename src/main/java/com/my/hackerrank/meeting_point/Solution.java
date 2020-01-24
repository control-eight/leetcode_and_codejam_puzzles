package com.my.hackerrank.meeting_point;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    // Complete the solve function below.
    static long solve(long[][] coordinates) {
        long[][] rotated = rotate(coordinates);
        List<Pair> sortX = new ArrayList<>();
        List<Pair> sortY = new ArrayList<>();
        Pair[] distances = new Pair[rotated.length];
        for (int i = 0; i < rotated.length; i++) {
            long[] coordinate = rotated[i];
            sortX.add(new Pair(i, coordinate[0]));
            sortY.add(new Pair(i, coordinate[1]));
            distances[i] = new Pair(i, 0);
        }
        calDistances(sortX, distances);
        calDistances(sortY, distances);
        Arrays.sort(distances);
        return calcChessDistance(distances[0].point, coordinates);
    }

    private static long[][] rotate(long[][] coordinates) {
        long[][] result = new long[coordinates.length][coordinates[0].length];
        for (int i = 0; i < coordinates.length; i++) {
            long x = coordinates[i][0];
            long y = coordinates[i][1];
            result[i][0] = x - y;
            result[i][1] = x + y;
        }
        return result;
    }

    private static long calcChessDistance(int point, long[][] coordinates) {
        long result = 0;
        for (int i = 0; i < coordinates.length; i++) {
            result += Math.max(Math.abs(coordinates[i][0] - coordinates[point][0]),
                    Math.abs(coordinates[i][1] - coordinates[point][1]));
        }
        return result;
    }

    private static void calDistances(List<Pair> sort, Pair[] distances) {
        Collections.sort(sort);
        long prevDistanceForth = 0;
        Pair first = sort.get(0);
        for (int i = 1; i < sort.size(); i++) {
            prevDistanceForth += sort.get(i).coordinate - first.coordinate;
        }
        distances[first.point].coordinate += prevDistanceForth;
        long prevDistanceBack = 0;

        for (int i = 1; i < sort.size(); i++) {
            prevDistanceForth -= (sort.get(i).coordinate - sort.get(i - 1).coordinate) * (sort.size() - i);
            prevDistanceBack += (sort.get(i).coordinate - sort.get(i - 1).coordinate) * i;
            distances[sort.get(i).point].coordinate += (prevDistanceForth + prevDistanceBack);
        }
    }

    private static class Pair implements Comparable<Pair> {
        private int point;
        private long coordinate;

        public Pair(int point, long coordinate) {
            this.point = point;
            this.coordinate = coordinate;
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(this.coordinate, o.coordinate);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[][] coordinates = new long[n][2];

        for (int i = 0; i < n; i++) {
            String[] coordinatesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                long coordinatesItem = Long.parseLong(coordinatesRowItems[j]);
                coordinates[i][j] = coordinatesItem;
            }
        }

        long result = solve(coordinates);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

