package com.my.hackerrank.week_of_code_38.cyclical_queries;

import java.io.*;
import java.util.*;

/**
 * @author abykovsky
 * @since 6/21/18
 */
public class Solution {

    // Complete the cyclicalQueries function below.
    static long[] cyclicalQueries(long[] w, int m) {
        if (m == 0 || w.length == 0) return new long[1];

        // Return the list of answers to all queries of type 4. Take the query information from standard input.

        SegmentTreeRMQ segmentTreeRMQ = new SegmentTreeRMQ();
        TreeNode[] treeNodes = new TreeNode[w.length];
        long[] distances = new long[w.length];
        Node[] nodes = new Node[w.length];
        long ts = 0;
        for (int i = 0; i < w.length; i++) {
            nodes[i] = new Node(i);
            distances[i] = (i > 0? distances[i - 1]: 0) + w[i];
            if (i > 0) {
                treeNodes[i] = new TreeNode(nodes[i], distances[i - 1], ts++);
            }
        }
        try {
            treeNodes[0] = new TreeNode(nodes[0], distances[distances.length - 1], ts++);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
        segmentTreeRMQ.constructST(treeNodes, treeNodes.length);

        List<Long> answers = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int queryType = readInt();
            switch (queryType) {
                case 1: {
                    int xx = readInt() - 1;
                    int ww = readInt();
                    Node farthest = findFarthest(xx, nodes, distances, segmentTreeRMQ);

                    Key key = farthest.heap.poll();
                    if (key == null) {
                        SimpleNode sn = new SimpleNode(ww);
                        farthest.heap.add(new Key(sn, ww, ts));
                    } else {
                        SimpleNode sn = new SimpleNode(ww);
                        sn.prev = key.end;
                        farthest.heap.offer(new Key(sn, key.max + sn.value, ts));
                    }
                    segmentTreeRMQ.updateValSegTree(0, 0, treeNodes.length - 1,
                            farthest.index, farthest, distance(distances, 0, farthest.index) + farthest.heap.peek().max, ts);
                    ts++;
                    break;
                }
                case 2: {
                    int xx = readInt() - 1;
                    SimpleNode sn = new SimpleNode(readInt());
                    nodes[xx].heap.offer(new Key(sn, sn.value, ts));

                    segmentTreeRMQ.updateValSegTree(0, 0, treeNodes.length - 1,
                            xx, nodes[xx], distance(distances, 0, xx) + nodes[xx].heap.peek().max, ts);
                    ts++;
                    break;
                }
                case 3: {
                    int xx = readInt() - 1;
                    Node farthest = findFarthest(xx, nodes, distances, segmentTreeRMQ);
                    Key key = farthest.heap.poll();
                    if (key != null) {
                        if (key.end.prev != null) {
                            farthest.heap.offer(new Key(key.end.prev, key.max - key.end.value, ts));
                        }
                        segmentTreeRMQ.updateValSegTree(0, 0, treeNodes.length - 1,
                                farthest.index, farthest, distance(distances, 0, farthest.index) + farthest.max(), ts);
                        ts++;
                    }
                    break;
                }
                case 4: {
                    int xx = readInt() - 1;
                    long distance = findDistance(xx, nodes, distances, segmentTreeRMQ);
                    System.out.println(distance);
                    answers.add(distance);
                    break;
                }
            }
        }
        //System.out.println(answers);
        long[] result = new long[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            result[i] = answers.get(i);
        }
        return result;
    }

    private static Node findFarthest(int from, Node[] nodes, long[] distances, SegmentTreeRMQ segmentTreeRMQ) {
        TreeNode maxTreeNode = segmentTreeRMQ.RMQ(nodes.length, 1, nodes.length - 1);

        PriorityQueue<Key2> heap0 = new PriorityQueue<>();
        heap0.offer(new Key2(maxTreeNode.node, maxTreeNode.max, maxTreeNode.ts));
        if (!nodes[from].heap.isEmpty()) {
            heap0.offer(new Key2(nodes[from], nodes[from].max(), nodes[from].heap.peek().ts));
        }

        if (from == 0 || maxTreeNode.node.index < from) {
            return heap0.poll().node;
        } else {
            TreeNode shortMaxTreeNode = segmentTreeRMQ.RMQ(nodes.length, 1, from - 1);

            long max = distance(distances, from, maxTreeNode.node.index) + maxTreeNode.node.max();
            long max1 = distance(distances, from, 0) + nodes[0].max();

            long max2 = shortMaxTreeNode == null? Integer.MIN_VALUE: distance(distances, from, shortMaxTreeNode.node.index)
                    + shortMaxTreeNode.node.max();

            PriorityQueue<Key2> heap = new PriorityQueue<>();
            heap.offer(new Key2(maxTreeNode.node, max, maxTreeNode.ts));
            heap.offer(new Key2(nodes[0], max1, nodes[0].heap.isEmpty()? -1: nodes[0].heap.peek().ts));
            if (shortMaxTreeNode != null) {
                heap.offer(new Key2(shortMaxTreeNode.node, max2, shortMaxTreeNode.ts));
            }
            if (!nodes[from].heap.isEmpty()) {
                heap.offer(new Key2(nodes[from], nodes[from].max(), nodes[from].heap.peek().ts));
            }
            return heap.poll().node;
        }
    }

    private static long findDistance(int from, Node[] nodes, long[] distances, SegmentTreeRMQ segmentTreeRMQ) {
        Node farthest = findFarthest(from, nodes, distances, segmentTreeRMQ);
        return distance(distances, from, farthest.index) + farthest.max();
    }

    private static long distance(long[] distances, int from, int to) {
        if (from == to) return 0;

        if (from == 0) {
            return distances[to - 1];
        } else if (to == 0) {
            try {
                return distances[distances.length - 1] - distances[from - 1];
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                throw e;
            }
        } else if (from > to) {
            return distance(distances, from, 0) + distance(distances, 0, to);
        } else {
            return distances[to - 1] - distances[from - 1];
        }
    }

    private static class TreeNode {
        private Node node;
        private long max;
        private long ts;
        public TreeNode(Node node, long max, long ts) {
            this.node = node;
            this.max = max;
            this.ts = ts;
        }
        @Override
        public String toString() {
            return "TreeNode{" +
                    "node=" + node +
                    ", max=" + max +
                    '}';
        }
    }

    private static class Node {
        private int index;
        private PriorityQueue<Key> heap = new PriorityQueue<>();
        public Node(int index) {
            this.index = index;
        }
        public long max() {
            return heap.isEmpty()? 0: heap.peek().max;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    '}';
        }
    }

    private static class SimpleNode {
        private int value;
        private SimpleNode prev;
        public SimpleNode(int value) {
            this.value = value;
        }
    }

    private static class Key implements Comparable<Key> {
        private SimpleNode end;
        private long max;
        private long ts;
        public Key(SimpleNode end, long max, long ts) {
            this.end = end;
            this.max = max;
            this.ts = ts;
        }
        @Override
        public int compareTo(Key o) {
            if (max == o.max) {
                return Long.compare(o.ts, ts);
            }
            return Long.compare(o.max, max);
        }
    }

    private static class Key2 implements Comparable<Key2> {
        private Node node;
        private long max;
        private long ts;
        public Key2(Node node, long max, long ts) {
            this.node = node;
            this.max = max;
            this.ts = ts;
        }
        @Override
        public int compareTo(Key2 o) {
            if (max == o.max) {
                return Long.compare(o.ts, ts);
            }
            return Long.compare(o.max, max);
        }
    }

    public static void set(TreeNode[] t, int i, Node node, long max, long ts) {
        i = node.index == 0? t.length - 1: i - 1;
        for (; i < t.length; i |= i + 1) {
            t[i] = new TreeNode(node, Math.max((t[i] != null? t[i].max: 0), max), ts);
        }
    }

    public static TreeNode max(TreeNode[] t, int i) {
        TreeNode res = new TreeNode(null, Long.MIN_VALUE, -1);
        for (; i >= 0; i = (i & (i + 1)) - 1) {
            if (t[i].max > res.max) {
                res = t[i];
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        pos = 0;
        buffer = getChars(System.in);
        int n = readInt();

        long[] w = new long[n];
        for (int i = 0; i < n; i++) {
            w[i] = readInt();
        }

        int m = readInt();
        long[] result = cyclicalQueries(w, m);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }
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

    static class SegmentTreeRMQ {
        TreeNode st[];

        TreeNode maxValue(TreeNode x, TreeNode y) {
            if (x.max == y.max) {
                return x.ts > y.ts ? x: y;
            }
            return (x.max > y.max) ? x : y;
        }

        int getMid(int s, int e) {
            return s + (e - s) / 2;
        }

        TreeNode RMQUtil(int ss, int se, int qs, int qe, int index) {
            if (qs <= ss && qe >= se)
                return st[index];
            if (se < qs || ss > qe)
                return new TreeNode(null, Integer.MIN_VALUE, -1);

            // If a part of this segment overlaps with the given range
            int mid = getMid(ss, se);
            return maxValue(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
                    RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
        }

        TreeNode RMQ(int n, int qs, int qe) {
            if (qs < 0 || qe > n - 1 || qs > qe) {
                return null;
            }
            return RMQUtil(0, n - 1, qs, qe, 0);
        }

        TreeNode constructSTUtil(TreeNode arr[], int ss, int se, int si) {
            if (ss == se) {
                st[si] = arr[ss];
                return arr[ss];
            }
            int mid = getMid(ss, se);
            st[si] = maxValue(constructSTUtil(arr, ss, mid, si * 2 + 1),
                    constructSTUtil(arr, mid + 1, se, si * 2 + 2));
            return st[si];
        }

        void constructST(TreeNode arr[], int n) {
            int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
            int max_size = 2 * (int) Math.pow(2, x) - 1;
            st = new TreeNode[max_size];
            constructSTUtil(arr, 0, n - 1, 0);
        }

        void updateValSegTree(int treeIndex, int lo, int hi, int arrIndex, Node node, long val, long ts) {
            if (lo == hi) {                 // leaf node. update element.
                st[treeIndex] = new TreeNode(node, val, ts);
                return;
            }

            int mid = lo + (hi - lo) / 2;   // recurse deeper for appropriate child

            if (arrIndex > mid)
                updateValSegTree(2 * treeIndex + 2, mid + 1, hi, arrIndex, node, val, ts);
            else if (arrIndex <= mid)
                updateValSegTree(2 * treeIndex + 1, lo, mid, arrIndex, node, val, ts);

            // merge updates
            TreeNode treeNode = st[2 * treeIndex + 1];
            TreeNode treeNode1 = st[2 * treeIndex + 2];

            if (treeNode.max == treeNode1.max) {
                if (treeNode.ts > treeNode1.ts) {
                    st[treeIndex] = treeNode;
                } else {
                    st[treeIndex] = treeNode1;
                }
            } else {
                if (treeNode.max > treeNode1.max) {
                    st[treeIndex] = treeNode;
                } else {
                    st[treeIndex] = treeNode1;
                }
            }
        }
    }
}
