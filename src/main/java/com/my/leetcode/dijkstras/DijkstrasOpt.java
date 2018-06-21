package com.my.leetcode.dijkstras;

import java.util.Arrays;
import java.util.PriorityQueue;

import static com.my.leetcode.dijkstras.DijkstrasTest.*;

/**
 * Created by alex.bykovsky on 3/19/17.
 */
public class DijkstrasOpt {

	int findShortestPath(int target, PriorityQueue<Key> heap, int size) {

		DijkstrasTest.Key start = heap.poll();
		int[] distances = new int[size];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[0] = 0;

		int max = 0;
		while(start != null) {
			for (Dijkstras.Edge edge : start.node.edgeList) {
				int nextMinDistance = start.distance + edge.value;
				if(nextMinDistance < distances[edge.right.id]) {
					distances[edge.right.id] = nextMinDistance;
					heap.offer(new DijkstrasTest.Key(edge.right, nextMinDistance));
				}
			}
			start = heap.poll();
			max = Math.max(heap.size(), max);
		}
		System.out.println("max: " + max);

		return distances[target];
	}
}
