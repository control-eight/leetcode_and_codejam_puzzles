package com.my.leetcode.zigzag_iterator;

import java.util.Arrays;
import java.util.List;

public class ZigzagIterator {

    private int[] indexes;

    private int listIndex;

    private List<List<Integer>> lists;

    private int generalCursor;

    private int size;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        indexes = new int[2];
        listIndex = 0;
        generalCursor = 0;
        lists = Arrays.asList(v1, v2);
        size = v1.size() + v2.size();
    }

    public int next() {
        while (indexes[listIndex] == lists.get(listIndex).size()) {
            listIndex++;
            if (listIndex == lists.size()) {
                listIndex = 0;
            }
        }
        int result = lists.get(listIndex).get(indexes[listIndex]);
        generalCursor++;
        indexes[listIndex]++;
        listIndex++;
        if (listIndex == lists.size()) {
            listIndex = 0;
        }
        return result;
    }

    public boolean hasNext() {
        return generalCursor < size;
    }
}
