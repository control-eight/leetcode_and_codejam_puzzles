package com.my.misc;

import java.util.HashMap;
import java.util.Map;

public class UnionFindMapBased {

    private Map<Integer, Integer> parents;

    public UnionFindMapBased() {
        parents = new HashMap<>();
    }

    public int find(int index) {
        if (!parents.containsKey(index)) {
            parents.put(index, index);
        }
        if (parents.get(index) == index) return index;
        int parent = find(parents.get(index));
        parents.put(index, parent);
        return parent;
    }

    public int union(int index1, int index2) {
        int parent1 = find(index1);
        int parent2 = find(index2);
        this.parents.put(parent1, parent2);
        return parent1 != parent2? 1: 0;
    }
}
