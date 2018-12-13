package com.my.hackerrank.week_of_code_38.cargo_delivery;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the minimumBrokenness function below.
    static int minimumBrokenness(int n, int m, int k, int t) {
        // Return the minimum possible brokenness of a truck among all k trucks driving from city 0 to city n-1. Take the information about roads from standard input.

        Map<Integer, Node> nodeMap = new HashMap<>();
        List<Edge> edgeList = new ArrayList<>();
        buildGraph(m, nodeMap, edgeList);

        Node start = nodeMap.get(0);
        Node end = nodeMap.get(n - 1);

        //solve(start, end, 589, n, k, t);

        return binarySearchOverExpectedValues(n, t, edgeList, start, end, k, 0, k);
    }

    private static int binarySearchOverExpectedValues(int n, int t, List<Edge> edgeList, Node start, Node end, int k, int lo, int hi) {
        int mid = lo + (hi - lo) / 2; //expectedMinResult
        while (lo < hi) {
            reset(edgeList);
            if (!solve(start, end, mid, n, k, t)) {
                return binarySearchOverExpectedValues(n, t, edgeList, start, end, k, mid + 1, hi);
            } else {
                int result = binarySearchOverExpectedValues(n, t, edgeList, start, end, k, lo, mid);
                if (result == k) {
                    return mid;
                } else {
                    return result;
                }
            }
        }
        return k;
    }

    private static void buildGraph(int m, Map<Integer, Node> nodeMap, List<Edge> edgeList) {
        for (int i = 0; i < m; i++) {
            int node1 = readInt();
            int node2 = readInt();
            nodeMap.putIfAbsent(node1, new Node(node1));
            nodeMap.putIfAbsent(node2, new Node(node2));

            Edge edge1 = new Edge(nodeMap.get(node1), nodeMap.get(node2));
            Edge edge2 = new Edge(nodeMap.get(node2), nodeMap.get(node1));
            nodeMap.get(node1).add(edge1);
            nodeMap.get(node2).add(edge2);
            edgeList.add(edge1);
            edgeList.add(edge2);
        }
    }

    private static void reset(List<Edge> edgeList) {
        for (Edge edge : edgeList) {
            edge.brokenness = 0;
        }
    }

    private static boolean solve(Node start, Node dest, int expectedMinResult, int n, int k, int t) {
        Container c = new Container(t);
        int count = 0;
        while (count < k) {
            int next = move(start, dest, n, expectedMinResult, c);
            if (next == 0) return false;
            count += next;
        }
        return true;
    }

    private static class Container {
        int t;
        public Container(int t) {
            this.t = t;
        }
    }

    private static int move(Node start, Node dest, int size, int expectedMinResult, Container t) {
        Edge[] pred = new Edge[size];
        int max = findShortestPath(start, dest, size, expectedMinResult, pred);
        Node current = dest;
        if (max >= expectedMinResult) {
            while (current != start) {
                Edge prev = pred[current.id];
                if (prev.brokenness > expectedMinResult) {
                    prev.brokenness = expectedMinResult;
                    current.edgeMap.get(prev.left).brokenness = expectedMinResult;
                    t.t -= 1;
                }
                prev.brokenness++;
                current.edgeMap.get(prev.left).brokenness++;
                current = prev.left;
            }
            return t.t < 0? 0: 1;
        } else {
            while (current != start) {
                Edge prev = pred[current.id];
                prev.brokenness = expectedMinResult - max;
                current.edgeMap.get(prev.left).brokenness = expectedMinResult - max;
                current = prev.left;
            }
            return expectedMinResult - max;
        }
    }

    private static int findShortestPath(Node nodeStart, Node nodeDest, int size, int expectedMinResult, Edge[] pred) {
        PriorityQueue<Key> heap = new PriorityQueue<>();
        heap.offer(new Key(nodeStart, 0));

        Key start = heap.poll();
        int[] distances = new int[size];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;

        int max = 0;
        while (start != null) {
            for (Edge edge : start.node.edgeList) {
                int nextMinDistance = start.distance + Math.max(0, edge.brokenness - expectedMinResult);
                if (nextMinDistance < distances[edge.right.id]) {
                    pred[edge.right.id] = edge;
                    distances[edge.right.id] = nextMinDistance;
                    heap.offer(new Key(edge.right, nextMinDistance));
                    max = Math.max(edge.brokenness, max);
                }
            }
            if (start.node == nodeDest) break;
            start = heap.poll();
        }
        //return distances[nodeDest.id];
        return max;
    }

    private static class Key implements Comparable<Key> {
        Node node;
        int distance;
        public Key(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
        @Override
        public int compareTo(Key o) {
            return Integer.compare(distance, o.distance);
        }
    }

    private static class Node {
        private int id;
        private List<Edge> edgeList = new ArrayList<>();
        private Map<Node, Edge> edgeMap = new HashMap<>();

        public Node(int id) {
            this.id = id;
        }

        public void add(Edge edge) {
            edgeList.add(edge);
            edgeMap.put(edge.right, edge);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }

    private static class Edge {
        private Node left;
        private Node right;
        private int brokenness;

        public Edge(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "right=" + right +
                    ", brokenness=" + brokenness +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        buffer = getChars(System.in);
        int result = minimumBrokenness(readInt(), readInt(), readInt(), readInt());

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    private static int pos;
    private static char[] buffer;

    private static char[] getChars(InputStream in) {
        try {
            //first char should be ASCII, and from proper encoding
            int firstByte = System.in.read();

            char[] buffer = new char[in.available()];
            new InputStreamReader(in).read(buffer, 0, buffer.length);

            char[] newBuffer = new char[buffer.length + 1];
            System.arraycopy(buffer, 0, newBuffer, 1, buffer.length);
            newBuffer[0] = (char) firstByte;
            return newBuffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int readInt() {
        int num = 0;
        while (pos < buffer.length) {
            char ch = buffer[pos++];
            if (ch < '0' || ch > '9') {
                break;
            } else {
                int digit = ch - '0';
                num = (num << 3) + (num << 1) + digit;
            }
        }
        return num;
    }
}