package com.my.hackerrank.week_of_code_38.a_time_saving_affair;

import java.io.*;
import java.util.*;

/**
 * @author abykovsky
 * @since 6/20/18
 */
public class Solution {

    // Complete the leastTimeToInterview function below.
    static long leastTimeToInterview(int n, int k, int m) {
        // Return the least amount of time needed to reach the interview location in seconds.

        Node from = readGraph(m);

        PriorityQueue<Key> heap = new PriorityQueue<>(m);
        heap.offer(new Key(from, 0));
        return findShortestPath(n, heap, m, k);
    }

    private static Node readGraph(int m) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int from = readInt();
            int to = readInt();
            int time = readInt();
            nodeMap.putIfAbsent(from, new Node(from));
            nodeMap.putIfAbsent(to, new Node(to));

            nodeMap.get(from).edgeList.add(new Edge(nodeMap.get(from), nodeMap.get(to), time));
            nodeMap.get(to).edgeList.add(new Edge(nodeMap.get(to), nodeMap.get(from), time));
        }
        return nodeMap.get(1);
    }

    private static int findShortestPath(int target, PriorityQueue<Key> heap, int size, int k) {
        Key start = heap.poll();
        int[] distances = new int[size + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[1] = 0;

        while(start != null) {
            for (Edge edge : start.node.edgeList) {
                int startDistance = start.distance;

                if (Math.floor((double) startDistance / k) % 2 == 1) {
                    startDistance += k - (startDistance % k);
                }

                int nextMinDistance = startDistance + edge.value;
                if(nextMinDistance < distances[edge.right.id]) {
                    distances[edge.right.id ] = nextMinDistance;
                    heap.offer(new Key(edge.right, nextMinDistance));
                }
            }
            start = heap.poll();
        }

        return distances[target];
    }

    static class Key implements Comparable<Key> {
        Node node;
        int distance;
        public Key(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
        @Override
        public int compareTo(Key o) {
            return distance - o.distance;
        }
    }

    static class Node {
        Integer id;
        List<Edge> edgeList = new ArrayList<>();
        public Node(Integer id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }

    static class Edge {
        Node left;
        Node right;
        Integer value;
        public Edge(Node left, Node right, Integer value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
        @Override
        public String toString() {
            return "Edge{" +
                    "left=" + left +
                    ", right=" + right +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        buffer = getChars(System.in);
        long result = leastTimeToInterview(readInt(), readInt(), readInt());

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
