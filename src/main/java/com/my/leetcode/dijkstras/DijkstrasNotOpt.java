package com.my.leetcode.dijkstras;

import java.util.PriorityQueue;

import static com.my.leetcode.dijkstras.DijkstrasTest.*;

/**
 * Created by alex.bykovsky on 3/19/17.
 */
public class DijkstrasNotOpt {

	int findShortestPath(int target, PriorityQueue<Key> heap, int size) {

		Key start = heap.poll();

		boolean[] visited = new boolean[size];
		int max = 0;
		int result = Integer.MAX_VALUE;
		while(start != null) {
			if(!visited[start.node.id]) {
				if(start.node.id == target) {
					//System.out.println("max: " + max);
					result = Math.min(start.distance, result);
				}
				visited[start.node.id] = true;
				for (Dijkstras.Edge edge : start.node.edgeList) {
					if(!visited[edge.right.id]) {
						int nextMinDistance = start.distance + edge.value;
						heap.offer(new Key(edge.right, nextMinDistance));
					}
				}
			}
			start = heap.poll();
			max = Math.max(heap.size(), max);
		}

		return result;
	}
}
