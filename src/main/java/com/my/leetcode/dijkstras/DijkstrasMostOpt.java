package com.my.leetcode.dijkstras;

import edu.princeton.cs.algorithms.DijkstraSP;
import edu.princeton.cs.algorithms.EdgeWeightedDigraph;

/**
 * Created by alex.bykovsky on 3/19/17.
 */
public class DijkstrasMostOpt {

	private DijkstraSP dijkstraSP;

	public DijkstrasMostOpt(EdgeWeightedDigraph G, int source) {
		dijkstraSP = new DijkstraSP(G, source);
	}

	int findShortestPath(int target) {
		return (int) dijkstraSP.distTo(target);
	}
}
