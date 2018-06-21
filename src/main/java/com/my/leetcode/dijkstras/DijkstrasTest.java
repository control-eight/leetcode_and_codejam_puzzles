package com.my.leetcode.dijkstras;

import edu.princeton.cs.algorithms.DirectedEdge;
import edu.princeton.cs.algorithms.EdgeWeightedDigraph;

import java.util.Map;
import java.util.PriorityQueue;

import static com.my.leetcode.dijkstras.Dijkstras.Node;

/**
 * Created by alex.bykovsky on 3/19/17.
 */
public class DijkstrasTest {

	public static void main(String[] args) {

		int size = 700000;

		Map<Integer, Node> nodeMap = Dijkstras.generateDirectedWeightedGraph(size);
		EdgeWeightedDigraph g = generateForOpt(nodeMap, size);

		new DijkstrasMostOpt(g, 0).findShortestPath(size - 1);
		PriorityQueue<Key> heap = new PriorityQueue<>(size);
		heap.offer(new Key(nodeMap.get(0), 0));
		new DijkstrasNotOpt().findShortestPath(size - 1, heap, size);
		heap = new PriorityQueue<>(size);
		heap.offer(new Key(nodeMap.get(0), 0));
		new DijkstrasOpt().findShortestPath(size - 1, heap, size);

		System.out.println("findShortestPath...");
		long start1 = System.currentTimeMillis();
		int minKey1 = new DijkstrasMostOpt(g, 0).findShortestPath(size - 1);
		long end1 = System.currentTimeMillis() - start1;

		System.out.println("findShortestPath Not Opt...");
		long start22 = System.currentTimeMillis();
		heap = new PriorityQueue<>(size);
		heap.offer(new Key(nodeMap.get(0), 0));
		int minKey22 = new DijkstrasNotOpt().findShortestPath(size - 1, heap, size);
		long end22 = System.currentTimeMillis() - start22;

		System.out.println("findShortestPath Opt...");
		long start33 = System.currentTimeMillis();
		heap = new PriorityQueue<>(size);
		heap.offer(new Key(nodeMap.get(0), 0));
		int minKey33 = new DijkstrasOpt().findShortestPath(size - 1, heap, size);
		long end33 = System.currentTimeMillis() - start33;

		System.out.println(minKey1 + " vs " + minKey22 + " vs " + minKey33);
		System.out.println(end1 + " vs " + end22 + " vs " + end33);
	}

	static EdgeWeightedDigraph generateForOpt(Map<Integer, Dijkstras.Node> nodeMap, int size) {
		EdgeWeightedDigraph result = new EdgeWeightedDigraph(size);
		for (Map.Entry<Integer, Dijkstras.Node> entry : nodeMap.entrySet()) {
			for (Dijkstras.Edge edge : entry.getValue().edgeList) {
				result.addEdge(new DirectedEdge(entry.getValue().id, edge.right.id, edge.value));
			}
		}
		return result;
	}


	static class Key implements Comparable<Key> {

		Dijkstras.Node node;

		int distance;

		public Key(Dijkstras.Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(Key o) {
			return distance - o.distance;
		}
	}

}
