package com.my.leetcode.dijkstras;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static com.my.leetcode.dijkstras.DijkstrasTest.Key;

/**
 * Created by alex.bykovsky on 3/19/17.
 */
public class DijkstrasNotOptShort {

	int findShortestPath(int target, PriorityQueue<Key> heap, int size) {
		Key next = heap.poll();
		Set<Key> visited = new HashSet<>();
		int max = 0;
		int result = Integer.MAX_VALUE;
		while(next != null) {
			//if (!visited.contains(next.node.id)) {
				if (next.node.id == target) {
					//System.out.println("max: " + max);
					//result = Math.min(next.distance, result);
					return next.distance;
				}
				visited.add(next);
				for (Dijkstras.Edge edge : next.node.edgeList) {
					Key edgeKey = new Key(edge.right, next.distance + edge.value);
					if (visited.add(edgeKey)) {
						heap.offer(edgeKey);
					}
				}
			//}
			next = heap.poll();
			max = Math.max(heap.size(), max);
		}
		return result;
	}
}
