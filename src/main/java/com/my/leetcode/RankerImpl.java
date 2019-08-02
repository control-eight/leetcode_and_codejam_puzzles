package com.my.leetcode;

import java.util.*;

interface Ranker {
    List<Integer> rank(List<Integer> ids);
}

public class RankerImpl implements Ranker {

    private List<String> window;
    private Map<String, Ranker> rankers;

    public RankerImpl(List<String> window, Map<String, Ranker> rankers) {
        this.window = window;
        this.rankers = rankers;
    }

    public static void main(String[] args) {
        Map<String, Ranker> rankers = new HashMap<>();
        rankers.put("B", ids -> Arrays.asList(1,2,3,4,5,6,7,8));
        rankers.put("New", ids -> Arrays.asList(5,6));
        rankers.put("Ch", ids -> Arrays.asList(1,3,5,2,6,4,7));
        RankerImpl ranker = new RankerImpl(Arrays.asList("B", "New", "B", "Ch"), rankers);
        System.out.println(ranker.rank(Arrays.asList(2,4,1,5,3,7,8,6,9)));
        //expected: 1 5 2 3 4 6 7 8
    }

    //ordered
    public List<Integer> rank(List<Integer> ids) {
        Map<String, List<Integer>> orderLists = createOrderLists(rankers, ids);
        Map<String, Integer> pointers = new HashMap<>();

        Set<Integer> visited = new HashSet<>();
        List<Integer> result = new ArrayList<>(ids.size());

        List<String> window = new ArrayList<>(this.window);
        while (!window.isEmpty()) {
            for (int i = 0; i < window.size(); i++) {
                String w = window.get(i);
                pointers.putIfAbsent(w, 0);
                while (pointers.get(w) < orderLists.get(w).size()) {
                    Integer newItem = orderLists.get(w).get(pointers.get(w));
                    if (visited.add(newItem)) {
                        result.add(newItem);
                        break;
                    }
                    pointers.put(w, pointers.get(w) + 1);
                }
                if (pointers.get(w) == orderLists.get(w).size()) {
                    window.remove(i);
                }
            }
        }
        return result;
    }
    // O(R*N)

    private Map<String, List<Integer>> createOrderLists(Map<String, Ranker> rankers, List<Integer> ids) {
        Map<String, List<Integer>> result = new HashMap<>();
        for (String key : rankers.keySet()) {
            result.put(key, rankers.get(key).rank(ids));
        }
        return result;
    }
}