package com.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by alex.bykovsky on 3/22/17.
 */
public class WordLedder {

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {

		Set<String> wordSet = new HashSet<>(wordList);

		Set<String> visited = new HashSet<>();

		Node startNode = new Node(beginWord);
		startNode.edgeList = new ArrayList<>();

		Queue<Node> nodeQueue = new LinkedList<>();
		nodeQueue.offer(startNode);

		while (!nodeQueue.isEmpty()) {

			Node node = nodeQueue.poll();

			for(int i = 0; i < node.word.length(); i++) {
				char[] wordChanger = node.word.toCharArray();
				for (int c = 97; c <= 122; c++) {

					wordChanger[i] = (char) c;

					String newWord = new String(wordChanger);
					if (wordSet.contains(newWord) && visited.add(newWord)) {
						Edge e = new Edge();

						e.right = new Node(newWord);
						e.right.edgeList = new ArrayList<>();

						nodeQueue.offer(e.right);

						node.edgeList.add(e);
					}
				}
			}
		}

		List<Node> nodeList = new ArrayList<>();
		nodeList.add(startNode);

		int shortestPathLength = 0;
		while(!nodeList.isEmpty()) {

			List<Node> newLevel = new ArrayList<>();

			for (Node node : nodeList) {
				if(endWord.equals(node.word)) return shortestPathLength + 1;

				for (Edge edge : node.edgeList) {
					newLevel.add(edge.right);
				}
			}

			nodeList = newLevel;
			shortestPathLength++;
		}
		return 0;
	}

	private static class Node {

		private String word;

		private List<Edge> edgeList;

		public Node(String word) {
			this.word = word;
		}
	}

	private static class Edge {

		private Node right;

	}

	public static void main(String[] args) {
		System.out.println(new WordLedder().ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
	}
}
