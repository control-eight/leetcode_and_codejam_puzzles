package com.my.leetcode.sentence_similarity_II;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().areSentencesSimilarTwo(new String[] {"great", "acting", "skills"},
                new String[] {"fine", "drama", "talent"}, new String[][] {
                        {"great", "good"},
                        {"fine", "good"},
                        {"acting","drama"},
                        {"skills","talent"}
                }));
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;

        Map<String, Node> similar = new HashMap<>();
        for (String[] pair : pairs) {
            checkAndAddNew(similar, pair[0], pair[1]);
        }
        for (int i = 0; i < words2.length; i++) {
            if (!words1[i].equals(words2[i]) && !find(similar, words1[i], words2[i])) {
                return false;
            }
        }
        return true;
    }

    private void checkAndAddNew(Map<String, Node> similar, String source, String target) {
        if (!similar.containsKey(source)) {
            similar.put(source, new Node());
        }
        if (!similar.containsKey(target)) {
            similar.put(target, new Node());
        }
        similar.get(source).edgeList.add(new Edge(similar.get(target)));
        similar.get(target).edgeList.add(new Edge(similar.get(source)));
    }

    private boolean find(Map<String, Node> similarMap, String source, String target) {

        Node sourceNode = similarMap.get(source);
        Node targetNode = similarMap.get(target);

        if (sourceNode == null || targetNode == null) return false;

        Set<Node> visited = new HashSet<>();
        visited.add(sourceNode);
        return dfs(sourceNode, targetNode, visited);
    }

    private boolean dfs(Node sourceNode, Node targetNode, Set<Node> visited) {
        if (sourceNode == targetNode) return true;

        for (Edge edge : sourceNode.edgeList) {
            if (visited.add(edge.to)) {
                if (dfs(edge.to, targetNode, visited)) return true;
            }
        }
        return false;
    }

    private static class Node {
        private List<Edge> edgeList = new ArrayList<>();
    }

    private static class Edge {
        private Node to;

        public Edge(Node to) {
            this.to = to;
        }
    }
}
