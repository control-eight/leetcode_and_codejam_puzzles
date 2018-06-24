package com.my.hackerrank.week_of_code_38.cyclical_queries;

import java.io.*;
import java.util.*;

public class EtalonSolution {
    static class Branch {
        List<Integer> ids = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
    }

    static class Node {
        final int id;
        Node next;
        final int distanceToNext;

        List<Branch> branches = new ArrayList<>();

        public Node(int id, int distanceToNext) {
            this.id = id;
            this.distanceToNext = distanceToNext;
        }
    }

    static class QueryProcessor {
        Node[] nodes;
        int lastNodeId;

        QueryProcessor(Node[] nodes) {
            this.nodes = nodes;
            lastNodeId = nodes.length - 1;
        }

        long distanceBetweenNodes(Node start, Node end) {
            long distance = 0;
            while (start != end) {
                distance += start.distanceToNext;
                start = start.next;
            }
            return distance;
        }

        Branch findFarthestBranch(Node node) {
            long bestDistance = -1;
            long bestRate = -1;
            Branch bestBranch = null;

            for (Branch branch : node.branches) {
                long candidate = 0;
                for (Integer weight : branch.weights) {
                    candidate += weight;
                }
                if (candidate > bestDistance) {
                    bestDistance = candidate;
                    bestBranch = branch;
                }
                else if (candidate == bestDistance) {
                    if (!branch.ids.isEmpty() && branch.ids.get(branch.ids.size() - 1) > bestRate) {
                        bestDistance = candidate;
                        bestBranch = branch;
                    }
                }
            }

            return bestBranch;
        }

        Node findNodeOfFarthestPath(int nodeNum) {
            if (nodes.length == 1) {
                return nodes[0];
            }

            long bestDistance = -1;
            long bestRate = -1;
            Node bestNode = null;
            int cnt = 0;

            Node currentNode = nodes[nodeNum];
            long distanceToNode = 0;
            while (cnt < nodes.length){
                for (Branch branch : currentNode.branches) {
                    long candidate = distanceToNode;
                    for (Integer weight : branch.weights) {
                        candidate += weight;
                    }
                    if (candidate > bestDistance) {
                        bestDistance = candidate;
                        bestNode = currentNode;
                    }
                    else if (candidate == bestDistance) {
                        if (!branch.ids.isEmpty() && branch.ids.get(branch.ids.size() - 1) > bestRate) {
                            bestDistance = candidate;
                            bestNode = currentNode;
                        }
                    }
                }
                distanceToNode += currentNode.distanceToNext;
                currentNode = currentNode.next;
                ++cnt;

                if (cnt < nodes.length && distanceToNode > bestDistance) {
                    bestDistance = distanceToNode;
                    bestNode = currentNode;
                }
            }

            return bestNode;
        }

        void addNodeToFarthest(int nodeNum, int weight) {
            Node node = findNodeOfFarthestPath(nodeNum);
            Branch branchOfFarthestPath = findFarthestBranch(node);
            if (branchOfFarthestPath == null) {
                branchOfFarthestPath = new Branch();
                node.branches.add(branchOfFarthestPath);
            }
            branchOfFarthestPath.ids.add(++lastNodeId);
            branchOfFarthestPath.weights.add(weight);
        }

        void addNodeToItself(int nodeNum, int weight) {
            Node node = nodes[nodeNum];
            Branch branch = new Branch();
            branch.ids.add(++lastNodeId);
            branch.weights.add(weight);
            node.branches.add(branch);
        }

        void deleteFarthestNode(int nodeNum) {
            Node node = findNodeOfFarthestPath(nodeNum);
            Branch branchOfFarthestPath = findFarthestBranch(node);
            if (branchOfFarthestPath == null) return;
            branchOfFarthestPath.ids.remove(branchOfFarthestPath.ids.size() - 1);
            branchOfFarthestPath.weights.remove(branchOfFarthestPath.weights.size() - 1);
            if (branchOfFarthestPath.ids.isEmpty()) node.branches.remove(branchOfFarthestPath);
        }

        long distanceToFarthest(int nodeNum) {
            Node node = findNodeOfFarthestPath(nodeNum);
            long result = distanceBetweenNodes(nodes[nodeNum], node);
            Branch branchOfFarthestPath = findFarthestBranch(node);
            if (branchOfFarthestPath != null) {
                for (Integer weight : branchOfFarthestPath.weights) result += weight;
            }
            return result;
        }
    }

    public static void main(String[] params) throws IOException {
        InputReader reader = new InputReader(System.in);

        int nodesNum = reader.readInt();
        int[] distances = new int[nodesNum];
        reader.readIntArray(distances, 0, nodesNum);

        Node[] nodes = new Node[nodesNum];
        long originalDistance = 0;
        for (int i = 0; i < nodesNum; originalDistance += distances[i], ++i) {
            nodes[i] = new Node(i, distances[i]);
            if (i == nodesNum - 1) nodes[i].next = nodes[0];
            if (i > 0) nodes[i - 1].next = nodes[i];
        }

        QueryProcessor queryProcessor = new QueryProcessor(nodes);
        int queriesNum = reader.readInt();
        for (int i = 0; i < queriesNum; ++i) {
            int queryType = reader.readInt();
            int nodeNum = 0;
            int weight = 0;
            switch (queryType) {
                case 1:
                    nodeNum = reader.readInt() - 1;
                    weight = reader.readInt();
                    queryProcessor.addNodeToFarthest(nodeNum, weight);
                    break;
                case 2:
                    nodeNum = reader.readInt() - 1;
                    weight = reader.readInt();
                    queryProcessor.addNodeToItself(nodeNum, weight);
                    break;
                case 3:
                    nodeNum = reader.readInt() - 1;
                    queryProcessor.deleteFarthestNode(nodeNum);
                    break;
                case 4:
                    nodeNum = reader.readInt() - 1;
                    System.out.println(queryProcessor.distanceToFarthest(nodeNum));
                    break;
            }
        }

    }

    static class InputReader {
        private char[] content;
        private int pos = 0;
        private int[] readTo = new int[1];

        public InputReader(InputStream in) throws IOException {
            Reader charReader = new InputStreamReader(in);
            content = new char[in.available()];
            charReader.read(content, 0, content.length);
            charReader.close();
        }

        public int readInt() {
            readIntArray(readTo, 0, 1);
            return readTo[0];
        }

        public void readIntArray(int[] arr, int from, int length) {
            for (int i = from, k = 0; k < length; ++k, ++i) {
                int num = 0;
                int sign = 1;
                if (content[pos] == '-') {
                    sign = -1;
                    ++pos;
                } else if (content[pos] == '+') ++pos;

                while (pos < content.length) {
                    char ch = content[pos++];
                    if (ch < '0') {
                        break;
                    } else {
                        int digit = ch - '0';
                        num = (num << 3) + (num << 1) + digit;
                    }
                }
                arr[from++] = num * sign;
            }
        }
    }

}