package com.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * Created by alex.bykovsky on 3/19/17.
 */
public class Dijkstras {

	public static void main(String[] args) {

		int size = 500000;

		Map<Integer, Node> nodeMap = generateDirectedWeightedGraph(size);

		IndexMinPQ<Integer> indexMinPQ = new IndexMinPQ<>(size);
		indexMinPQ.insert(nodeMap.get(0).id, 0);

		Queue<Node> nodePool = new LinkedList<>();
		nodePool.add(nodeMap.get(0));

		long start1 = System.currentTimeMillis();
		System.out.println("findShortestPath...");
		int minKey1 = findShortestPath(nodePool, size - 1, indexMinPQ, new HashSet<>());
		long end1 = System.currentTimeMillis() - start1;

		long start22 = System.currentTimeMillis();
		System.out.println("findShortestPath2...");
		indexMinPQ = new IndexMinPQ<>(size);
		indexMinPQ.insert(nodeMap.get(0).id, 0);
		int minKey22 = findShortestPath2(size - 1, indexMinPQ, new HashSet<>(), nodeMap);
		long end22 = System.currentTimeMillis() - start22;

		long start2 = System.currentTimeMillis();
		System.out.println("straightForwardShortestPath...");
		/*List<Integer> keys = straightForwardShortestPath(nodeMap.get(0), 0, size - 1, new HashSet<>(),
				new ArrayList<>(), new ArrayList<>());*/
		List<Integer> keys = Arrays.asList(1);
		long end2 = System.currentTimeMillis() - start2;

		//System.out.println(keys);
		System.out.println(minKey1 + " vs " + keys.stream().min(Comparator.comparingInt(o -> o)).get() + " keys size = " + keys.size() + " vs " + minKey22);
		System.out.println(end1 + " vs " + end2 + " vs " + end22);
	}

	private static Integer findShortestPath(Queue<Node> nodePool, int targetId, IndexMinPQ<Integer> indexMinPQ,
											Set<Edge> visitedEdges) {

		while(!nodePool.isEmpty()) {
			Node left = nodePool.poll();
			for (Edge edge : left.edgeList) {
				if(visitedEdges.add(edge)) {
					nodePool.offer(edge.right);

					Integer leftId = edge.left.id;
					Integer rightId = edge.right.id;
					int newDistance = indexMinPQ.keyOf(leftId) + edge.value;

					if(indexMinPQ.contains(rightId)) {
						if(newDistance < indexMinPQ.keyOf(rightId)) {
							indexMinPQ.changeKey(rightId, newDistance);
							nodePool.offer(edge.right);
							visitedEdges.removeAll(edge.right.edgeList);
						}
					} else {
						indexMinPQ.insert(rightId, newDistance);
					}
				}
			}
		}
		return indexMinPQ.keyOf(targetId);
	}

	private static Integer findShortestPath2(int targetId, IndexMinPQ<Integer> indexMinPQ,
											Set<Edge> visitedEdges, Map<Integer, Node> nodeMap) {

		int result = Integer.MAX_VALUE;
		while(!indexMinPQ.isEmpty()) {

			int leftDistance = indexMinPQ.minKey();
			int index = indexMinPQ.delMin();
			Node left = nodeMap.get(index);

			if(index == targetId) {
				result = Math.min(leftDistance, result);
			}

			for (Edge edge : left.edgeList) {
				if(visitedEdges.add(edge)) {

					Integer rightId = edge.right.id;
					int newDistance = leftDistance + edge.value;

					if (indexMinPQ.contains(rightId)) {
						if (newDistance < indexMinPQ.keyOf(rightId)) {
							indexMinPQ.changeKey(rightId, newDistance);
						}
					} else {
						indexMinPQ.insert(rightId, newDistance);
					}
				}
			}
		}
		return result;
	}

	private static List<Integer> straightForwardShortestPath(Node left, int path, int targetId, Set<Node> visitedNodes,
															 List<Integer> shortestList, List<String> pathList) {

		if(left.id == targetId) {
			shortestList.add(path);

			/*if(Objects.equals(pathList.get(pathList.size() - 1), "[140]")
					|| Objects.equals(pathList.get(pathList.size() - 1), "[144]")) {
				pathList.add(left.id.toString());
				for (String id : pathList) {
					System.out.print(id + " -> ");
				}
				System.out.println("null");
			}*/
		}

		if(visitedNodes.add(left)) {
			for (Edge edge : left.edgeList) {
				//List<String> newPathList = new ArrayList<>(pathList);
				//newPathList.add(left.id.toString());
				//newPathList.add("[" + (path + edge.value) + "]");
				straightForwardShortestPath(edge.right, path + edge.value, targetId, new HashSet<>(visitedNodes),
						shortestList, pathList);
			}
		}

		return shortestList;
	}

	private static Map<Integer, Node> generateDirectedWeightedGraph(int size) {

		Random random = new Random(size);

		Map<Integer, Node> nodeMap = new HashMap<>();

		for(int currentId = 0; currentId < size; currentId++) {
			if(!nodeMap.containsKey(currentId)) {
				nodeMap.put(currentId, new Node(currentId));
			}
			Node current = nodeMap.get(currentId);

			int edgeCount = (int)(random.nextDouble() * 8) + 1;

			for(int i = 1; i <= edgeCount; i++) {

				int toId = currentId + ((int)(random.nextDouble() * 3)) + 1;

				if(toId >= size) {
					toId = (int) (random.nextDouble() * size);
				}

				if(!nodeMap.containsKey(toId)) {
					nodeMap.put(toId, new Node(toId));
				}
				current.edgeList.add(new Edge(current, nodeMap.get(toId), (int)(random.nextDouble() * 100) + 1));
			}
		}

		return nodeMap;
	}

	private static class Node {

		private Integer id;

		private List<Edge> edgeList = new ArrayList<>();

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

	private static class Edge {

		private Node left;

		private Node right;

		private Integer value;

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
}
