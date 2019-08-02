package com.my.misc;

public class UnionFind {

    private int[] parent;

    public UnionFind(int size) {
        this.parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            this.parent[i] = i;
        }
    }

    public int find(int index) {
        if (index == -1) return -1;
        if (parent[index] == index) return index;
        int parentIndex = find(parent[index]);
        parent[index] = parentIndex;
        return parentIndex;
    }

    public void union(int index1, int index2) {
        int parent1 = find(index1);
        this.parent[parent1] = find(index2);
    }
}
