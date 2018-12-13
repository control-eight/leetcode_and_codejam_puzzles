package com.my.misc;

import java.util.*;

public class BFS_Track_Unweighted {

    private static boolean bfs(Node start, Node dest, Map<Node, Node> pred, Map<Node, Integer> dist) {
        Deque<Node> deque = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        visited.add(start);
        dist.put(start, 0);
        deque.offerLast(start);

        while (!deque.isEmpty()) {
            Node next = deque.pollFirst();
            for (Edge edge : next.edgeList) {
                if (visited.add(edge.right)) {
                    dist.put(edge.right, dist.get(next) + 1);
                    pred.put(edge.right, next);
                    deque.offerLast(edge.right);

                    if (edge.right == dest)
                        return true;
                }
            }
        }
        return false;
    }

    private static class Node {
        private List<Edge> edgeList;
    }
    private static class Edge {
        private Node right;
    }
}
