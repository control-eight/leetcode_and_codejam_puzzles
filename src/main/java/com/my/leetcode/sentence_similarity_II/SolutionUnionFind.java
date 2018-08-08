package com.my.leetcode.sentence_similarity_II;

import java.util.*;

public class SolutionUnionFind {

    public static void main(String[] args) {
        System.out.println(new SolutionUnionFind().areSentencesSimilarTwo(new String[] {"great", "acting", "skills"},
                new String[] {"fine", "drama", "talent"}, new String[][] {
                        {"great", "good"},
                        {"fine", "good"},
                        {"acting","drama"},
                        {"skills","talent"}
                }));
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;

        Map<String, Integer> similar = new HashMap<>();
        int count = 0;
        UnionFind unionFind = new UnionFind(pairs.length * 2);
        for (String[] pair : pairs) {
            for (String word : pair) {
                if (!similar.containsKey(word)) {
                    similar.put(word, count++);
                }
            }
            unionFind.union(similar.get(pair[0]), similar.get(pair[1]));
        }

        for (int i = 0; i < words2.length; i++) {
            if (words1[i].equals(words2[i])) continue;
            if (!similar.containsKey(words1[i]) ||
                !similar.containsKey(words2[i]) ||
                unionFind.find(similar.get(words1[i])) != unionFind.find(similar.get(words2[i]))) {
                return false;
            }
        }
        return true;
    }
}
