package com.my.num_islands;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by alex.bykovsky on 3/23/17.
 */
public class Solution {

	public int numIslands(char[][] grid) {

		if(grid.length == 0) return 0;

		Deque<Node> deque = new LinkedList<>();
		Node firstNode = new Node(0, 0);
		deque.offerFirst(firstNode);

		Set<Node> visitedSet = new HashSet<>();

		char nextId = '1';
		while(!deque.isEmpty()) {

			Node node = deque.pollFirst();

			if(!visitedSet.add(node)) continue;

			char cell = grid[node.i][node.j];

			Node left = new Node(node.i, node.j - 1);
			Node top = new Node(node.i - 1, node.j);
			Node right = new Node(node.i, node.j + 1);
			Node bottom = new Node(node.i + 1, node.j);

			if(cell == '0') {
				if(left.j >= 0) {
					deque.addLast(left);
				}
				if(right.j < grid[right.i].length) {
					deque.addLast(right);
				}
				if(bottom.i < grid.length) {
					deque.addLast(bottom);
				}
				if(top.i >= 0) {
					deque.addLast(top);
				}
			} else {
				if(cell == '1') {
					nextId = (char)(nextId + 1);
					grid[node.i][node.j] = nextId;
				}

				char id = grid[node.i][node.j];

				if(left.j >= 0) {
					addNewNode(grid, deque, left, id);
				}
				if(right.j < grid[right.i].length) {
					addNewNode(grid, deque, right, id);
				}
				if(bottom.i < grid.length) {
					addNewNode(grid, deque, bottom, id);
				}
				if(top.i >= 0) {
					addNewNode(grid, deque, top, id);
				}
			}
		}

		return nextId - '1';
	}

	private void addNewNode(char[][] visited, Deque<Node> deque, Node node, char id) {
		if(visited[node.i][node.j] == '0') {
			deque.offerLast(node);
		} else {
			visited[node.i][node.j] = id;
			deque.offerFirst(node);
		}
	}

	private static class Node {

		private int i;

		private int j;

		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Node node = (Node) o;

			if (i != node.i) return false;
			return j == node.j;
		}

		@Override
		public int hashCode() {
			int result = i;
			result = 31 * result + j;
			return result;
		}
	}

	public static void main(String[] args) {
		char c [][] = new char[4][5];
		/*c[0] = new char[]{'1','1','1','1','0'};
		c[1] = new char[]{'1','1','0','1','0'};
		c[2] = new char[]{'1','1','0','0','0'};
		c[3] = new char[]{'0','0','0','0','0'};

		System.out.println(new Solution().numIslands(c));*/

		c = new char[4][5];
		c[0] = new char[]{'1','1','0','0','0'};
		c[1] = new char[]{'1','1','0','0','0'};
		c[2] = new char[]{'0','0','1','0','0'};
		c[3] = new char[]{'0','0','0','1','1'};
		System.out.println(new Solution().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'1','1','0','1','1'};
		c[1] = new char[]{'0','1','1','1','0'};
		c[2] = new char[]{'0','0','0','0','0'};
		c[3] = new char[]{'0','0','0','1','1'};
		System.out.println(new Solution().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'0','1','0','1','1'};
		c[1] = new char[]{'0','1','0','1','0'};
		c[2] = new char[]{'0','1','1','1','0'};
		c[3] = new char[]{'0','0','0','1','1'};
		System.out.println(new Solution().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'0','1','1','1','1'};
		c[1] = new char[]{'0','0','1','1','0'};
		c[2] = new char[]{'0','1','1','1','0'};
		c[3] = new char[]{'0','1','0','1','1'};
		System.out.println(new Solution().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'1','0','1','1','1'};
		c[1] = new char[]{'1','1','0','1','0'};
		c[2] = new char[]{'0','1','0','1','0'};
		c[3] = new char[]{'0','1','1','1','0'};
		System.out.println(new Solution().numIslands(c));

		c = new char[4][5];
		c[0] = new char[]{'0','0','0','0','0'};
		c[1] = new char[]{'0','0','1','0','1'};
		c[2] = new char[]{'1','0','1','0','1'};
		c[3] = new char[]{'1','1','1','1','1'};
		System.out.println(new Solution().numIslands(c));

		c = new char[1][1];
		c[0] = new char[]{'1'};
		System.out.println(new Solution().numIslands(c));
	}
}
