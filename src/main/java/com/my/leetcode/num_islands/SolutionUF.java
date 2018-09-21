package com.my.leetcode.num_islands;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by alex.bykovsky on 3/23/17.
 */
public class SolutionUF {

	public int numIslands(char[][] grid) {

		if(grid.length == 0) return 0;
		UnionFind unionFind = new UnionFind(grid.length * grid[0].length);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					if (j > 0 && grid[i][j - 1] == '1') {
						unionFind.union(getIndex(i, j, grid[0].length), getIndex(i, j - 1, grid[0].length));
					}
					if (i > 0 && grid[i - 1][j] == '1') {
						unionFind.union(getIndex(i, j, grid[0].length), getIndex(i - 1, j, grid[0].length));
					}
				} else {
					unionFind.parent[getIndex(i, j, grid[0].length)] = -1;
				}
			}
		}
		Set<Integer> unique = new HashSet<>();
 		for (int i = 0; i < unionFind.parent.length; i++) {
 			int parent = unionFind.find(i);
			if (parent != -1) {
 				unique.add(parent);
			}
		}
		return unique.size();
	}

	private int getIndex(int i, int j, int length) {
		return (i * length) + j;
	}

	private static class UnionFind {

		private int[] parent;

		public UnionFind(int size) {
			this.parent = new int[size];
			for (int i = 0; i < parent.length; i++) {
				this.parent[i] = i;
			}
		}

		public int find(int index) {
			if (index == -1) return -1;
			if (parent[index] == index) return index;
			else return find(parent[index]);
		}

		public void union(int index1, int index2) {
			int parent1 = find(index1);
			this.parent[parent1] = find(index2);
		}
	}

	public static void main(String[] args) {
		char c [][];

		c = new char[4][5];
		c[0] = new char[]{'1','1','0','1','1'};
		c[1] = new char[]{'0','1','1','1','0'};
		c[2] = new char[]{'0','0','0','0','0'};
		c[3] = new char[]{'0','0','0','1','1'};
		System.out.println(new SolutionUF().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'1','1','1','1','0'};
		c[1] = new char[]{'1','1','0','1','0'};
		c[2] = new char[]{'1','1','0','0','0'};
		c[3] = new char[]{'0','0','0','0','0'};
		System.out.println(new Solution().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'1','1','0','0','0'};
		c[1] = new char[]{'1','1','0','0','0'};
		c[2] = new char[]{'0','0','1','0','0'};
		c[3] = new char[]{'0','0','0','1','1'};
		System.out.println(new SolutionUF().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'0','1','0','1','1'};
		c[1] = new char[]{'0','1','0','1','0'};
		c[2] = new char[]{'0','1','1','1','0'};
		c[3] = new char[]{'0','0','0','1','1'};
		System.out.println(new SolutionUF().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'0','1','1','1','1'};
		c[1] = new char[]{'0','0','1','1','0'};
		c[2] = new char[]{'0','1','1','1','0'};
		c[3] = new char[]{'0','1','0','1','1'};
		System.out.println(new SolutionUF().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'1','0','1','1','1'};
		c[1] = new char[]{'1','1','0','1','0'};
		c[2] = new char[]{'0','1','0','1','0'};
		c[3] = new char[]{'0','1','1','1','0'};
		System.out.println(new SolutionUF().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'0','0','0','0','0'};
		c[1] = new char[]{'0','0','1','0','1'};
		c[2] = new char[]{'1','0','1','0','1'};
		c[3] = new char[]{'1','1','1','1','1'};
		System.out.println(new SolutionUF().numIslands(c));

		c = new char[1][1];
		c[0] = new char[]{'1'};
		System.out.println(new SolutionUF().numIslands(c));
	}
}
