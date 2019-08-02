package com.my.misc;

import java.util.Arrays;
import java.util.List;

public class RangeMaxQuery {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(7, 2, 3, 0, 5, 10, 3, 12, 18);
        RangeMaxQuery rmq = new RangeMaxQuery(list);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j <= list.size(); j++) {
                System.out.println("[" + i + ", " + j + "] = " + rmq.query(i, j));
            }
        }
    }

    private int[][] lookup;

    private List<Integer> list;

    public RangeMaxQuery(int length) {
        lookup = new int[length][(int) (Math.ceil(Math.log(length) / Math.log(2)))];
    }

    public RangeMaxQuery(List<Integer> list) {
        this(list.size());
        preprocess(list);
        this.list = list;
    }

    private void preprocess(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            lookup[i][0] = i;
        }
        for (int j = 1; (1 << j) <= list.size(); j++) {
            for (int i = 0; (i + (1 << j) - 1) < list.size(); i++) {
                if (list.get(lookup[i][j - 1]) > list.get(lookup[i + (1 << (j - 1))][j - 1])) {
                    lookup[i][j] = lookup[i][j - 1];
                } else {
                    lookup[i][j] = lookup[i + (1 << (j - 1))][j - 1];
                }
            }
        }
    }

    public int query(int lo, int hi) {
        int j = (int) (Math.floor(Math.log(hi - lo) / Math.log(2)));

        if (list.get(lookup[lo][j]) >= list.get(lookup[(hi - 1) - (1 << j) + 1][j])) {
            return list.get(lookup[lo][j]);
        } else {
            return list.get(lookup[(hi - 1) - (1 << j) + 1][j]);
        }
    }
}
