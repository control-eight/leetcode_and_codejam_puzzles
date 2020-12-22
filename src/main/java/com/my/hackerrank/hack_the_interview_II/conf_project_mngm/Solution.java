package com.my.hackerrank.hack_the_interview_II.conf_project_mngm;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'configureProjectPresentation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY friendships
     */

    public static List<Integer> configureProjectPresentation(int n, List<List<Integer>> friendships) {
        // Write your code here
        Set<Integer> result = new HashSet<>();

        Map<Integer, Node> nodes = constructGraph(friendships);
        Set<Integer> visitedFromSecond = new HashSet<>();
        visitedFromSecond.add(1);

        if (nodes.containsKey(2)) {
            visitedFromSecond.add(2);
            Queue<Pair> toVisit = new LinkedList<>();
            toVisit.offer(new Pair(nodes.get(2), 0));
            while (!toVisit.isEmpty()) {
                Pair current = toVisit.poll();
                for (Node edge : current.node.edges) {
                    if (visitedFromSecond.add(edge.value)) {
                        if (current.distance == 0) {
                            toVisit.offer(new Pair(edge, current.distance + 1));
                        }
                    }
                }
            }
        }

        for (Node edge : nodes.get(1).edges) {
            if (!visitedFromSecond.contains(edge.value)) {
                result.add(edge.value);
            }
        }

        if (result.isEmpty()) {
            result.add(-1);
        }

        List<Integer> resultList = new ArrayList<>(result);
        Collections.sort(resultList);

        System.out.println(resultList);
        return resultList;
    }

    private static Map<Integer, Node> constructGraph(List<List<Integer>> friendships) {
        Map<Integer, Node> nodes = new HashMap<>();
        for (List<Integer> friendship : friendships) {
            Node from = nodes.getOrDefault(friendship.get(0), new Node(friendship.get(0)));
            Node to   = nodes.getOrDefault(friendship.get(1), new Node(friendship.get(1)));
            nodes.put(from.value, from);
            nodes.put(to.value, to);
            from.edges.add(to);
            to.edges.add(from);
        }
        return nodes;
    }

    private static class Node {
        private int value;
        private List<Node> edges = new ArrayList<>();
        public Node(int value) {
            this.value = value;
        }
    }

    private static class Pair {
        private Node node;
        private int distance;
        public Pair(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> freiendships = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        freiendships.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                List<Integer> result = Result.configureProjectPresentation(n, freiendships);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

