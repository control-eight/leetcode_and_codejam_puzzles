package com.my.leetcode.robot_room_cleaner;

import java.util.*;

public class TestBfsUndiscovered {

    public static void main(String[] args) {

        Node node1 = new Node(true, 1);
        Node node2 = new Node(true, 2);
        Node node3 = new Node(true, 3);
        Node node4 = new Node(true, 4);
        Node node5 = new Node(false, 5);

        addEdge(node1, node2);
        addEdge(node2, node3);
        addEdge(node3, node4);
        addEdge(node4, node5);

        List<Node> path = bfsUndiscovered(node1);
        System.out.println(path);

        Node node6 = new Node(true, 6);
        addEdge(node4, node6);

        List<Node> path2 = bfsUndiscovered(node6);
        System.out.println(path2);

        node1 = new Node(true, 1);
        node2 = new Node(true, 2);
        node3 = new Node(true, 3);
        node4 = new Node(true, 4);
        node5 = new Node(true, 5);
        node6 = new Node(true, 6);
        Node node7 = new Node(true, 7);
        Node node8 = new Node(true, 8);
        addEdge(node1, node2);
        addEdge(node2, node3);
        addEdge(node3, node4);
        addEdge(node4, node5);
        addEdge(node1, node6);
        addEdge(node6, node7);
        addEdge(node6, node8);
        addEdge(node8, node7);
        addEdge(node4, node7);
        addEdge(node1, node7);

        path = bfsUndiscovered(node1);
        System.out.println(path);
    }

    private static void addEdge(Node node1, Node node2) {
        node1.edges.add(new Edge(node1, node2));
        node2.edges.add(new Edge(node2, node1));
    }

    private static List<Node> bfsUndiscovered(Node current) {
        Deque<Node> siblings = null;
        Map<Node, Integer> visited = new HashMap<>();
        Node found = null;
        int distance = 1;
        do {
            Node next;
            if (siblings == null) {
                siblings = new LinkedList<>();
                next = current;
                visited.put(current, 0);
            } else {
                next = siblings.pollFirst();
            }
            for (Edge edge : next.edges) {
                if (edge.right != null && !visited.containsKey(edge.right)) {
                    visited.put(edge.right, distance);
                    siblings.offerLast(edge.right);
                    if (!edge.right.isDiscoveredFully()) {
                        siblings.clear();
                        found = edge.right;
                        break;
                    }
                }
            }
            distance++;
        } while (!siblings.isEmpty());

        if (found == null) return Collections.emptyList();

        //System.out.println("Found: " + found);

        distance = visited.get(found);
        Node target = found;
        List<Node> path = new LinkedList<>();
        path.add(target);
        while (target != current) {
            for (Edge edge : target.edges) {
                if (edge.right != null && visited.containsKey(edge.right) && visited.get(edge.right) < distance) {
                    distance = visited.get(edge.right);
                    visited.remove(edge.right);
                    path.add(edge.right);
                    target = edge.right;
                    break;
                }
            }
        }
        Collections.reverse(path);
        return path;
    }

    private static class Node {
        private List<Edge> edges = new ArrayList<>();
        private boolean fullyDiscovered;
        private int number;

        public Node(boolean fullyDiscovered, int number) {
            this.fullyDiscovered = fullyDiscovered;
            this.number = number;
        }

        private boolean isDiscoveredFully() {
            return fullyDiscovered;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "number=" + number +
                    '}';
        }
    }

    private static class Edge {
        private Node left;
        private Node right;
        public Edge(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }
}
