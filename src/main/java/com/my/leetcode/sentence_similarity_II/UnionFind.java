package com.my.leetcode.sentence_similarity_II;

public class UnionFind {

    private int[] parent;

    public UnionFind(int size) {
        this.parent = new int[size];

        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
        }
    }

    public int find(int index) {
        if (parent[index] == index) return index;
        else return find(parent[index]);
    }

    public void union(int index1, int index2) {
        int parent1 = find(index1);
        parent[parent1] = find(index2);
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(4);
        unionFind.union(1, 2);
        unionFind.union(2, 3);
        System.out.println(unionFind.find(0));
        System.out.println(unionFind.find(1));
        System.out.println(unionFind.find(2));
        System.out.println(unionFind.find(3));
    }
}
