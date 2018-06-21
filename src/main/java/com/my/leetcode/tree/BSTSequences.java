package com.my.leetcode.tree;

import com.my.leetcode.Timed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex.bykovsky on 10/3/2017.
 */
public class BSTSequences {

	/*public static void main(String[] args) {
		System.out.println(new Timed<List<LinkedList<Integer>>>().act(a ->
				new BSTSequences().allSequences(treeThree())).getTime() + "ms");
	}*/

	public static void main(String[] args) {
		/*System.out.println(new Timed<List<List<Integer>>>().act(a ->
				new BSTSequences().allSequences3(treeThree())).getTime() + "ms");*/

		Timed.TimedResult<List<List<Integer>>> act1 = new Timed<List<List<Integer>>>().act(a -> convert(new BSTSequences().allSequences(treeThree())));
		Timed.TimedResult<List<List<Integer>>> act2 = new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeThree()));
		compare(act1, act2);
	}

	/*public static void main(String[] args) {
		compare(new Timed<List<List<Integer>>>().act(a -> convert(new BSTSequences().allSequences(treeOneStar()))),
				new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeOneStar())));

		compare(new Timed<List<List<Integer>>>().act(a -> convert(new BSTSequences().allSequences(treeOne()))),
				new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeOne())));

		compare(new Timed<List<List<Integer>>>().act(a -> convert(new BSTSequences().allSequences(treeTwo()))),
				new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeTwo())));

		compare(new Timed<List<List<Integer>>>().act(a -> convert(new BSTSequences().allSequences(treeFive()))),
				new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeFive())));

		compare(new Timed<List<List<Integer>>>().act(a -> convert(new BSTSequences().allSequences(treeFour()))),
				new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeFour())));

		*//*Timed.TimedResult<List<List<Integer>>> act1 = new Timed<List<List<Integer>>>().act(a -> convert(new BSTSequences().allSequences(treeThree())));
		Timed.TimedResult<List<List<Integer>>> act2 = new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeThree()));
		compare(act1, act2);

		System.out.println(new Timed<ArrayList<LinkedList<Integer>>>().act(a -> new BSTSequences().allSequences(treeThree())).getTime());
		System.out.println(new Timed<List<List<Integer>>>().act(a -> new BSTSequences().allSequences3(treeThree())).getTime());*//*

		new BSTSequences().allSequences3(treeThree());

		long start = System.currentTimeMillis();
		List list1 = convert(new BSTSequences().allSequences(treeThree()));
		System.out.println((System.currentTimeMillis() - start) + "ms" + " " + list1.size());

		start = System.currentTimeMillis();
		List list2 = new BSTSequences().allSequences3(treeThree());
		System.out.println((System.currentTimeMillis() - start) + "ms" + " " + list2.size());
	}*/

	private static void compare(Timed.TimedResult<List<List<Integer>>> expected, Timed.TimedResult<List<List<Integer>>> actual) {
		System.out.println("expected size = " + expected.getResult().size());
		System.out.println("actual size = " + actual.getResult().size());
		System.out.println("result = " + actual.getResult().equals(expected.getResult()));
		System.out.println("time = " + expected.getTime() + "ms vs " + actual.getTime() + "ms");
	}

	private List<List<Integer>> allSequences3(TreeNode node) {
		return processSequences(node);
	}

	private List<List<Integer>> processSequences(TreeNode root) {

		if(root == null) {
			return new ArrayList<>();
		}

		List<List<Integer>> leftResult = processSequences(root.left);
		List<List<Integer>> rightResult = processSequences(root.right);

		List<List<Integer>> result = new ArrayList<>();

		if(leftResult.isEmpty() && rightResult.isEmpty()) {
			List<Integer> list = new ArrayList<>();
			list.add(root.data);
			result.add(list);
			return result;
		}

		if(rightResult.isEmpty()) {
			result.addAll(leftResult);
		} else if (leftResult.isEmpty()) {
			result.addAll(rightResult);
		} else {
			for (List<Integer> leftIntegers : leftResult) {
				for (List<Integer> rightIntegers : rightResult) {
					result.addAll(generatePermutations(leftIntegers, rightIntegers));
				}
			}
		}

		for (List<Integer> integers : result) {
			integers.add(0, root.data);
		}

		return result;
	}

	private List<List<Integer>> generatePermutations(List<Integer> left, List<Integer> right) {

		if(left.isEmpty()) {
			List<List<Integer>> result = new ArrayList<>();
			result.add(right);
			return result;
		}

		List<List<Integer>> result = new ArrayList<>();

		for(int i = 0; i <= right.size(); i++) {
			List<List<Integer>> nextResult = generatePermutations(left.subList(1, left.size()),
					right.subList(i, right.size()));
			for (List<Integer> integers : nextResult) {
				List<Integer> newResult = new ArrayList<>();
				newResult.addAll(right.subList(0, i));
				newResult.addAll(integers);
				newResult.add(i, left.get(0));
				result.add(newResult);
			}
		}

		return result;
	}

	private static TreeNode treeOne() {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		return root;
	}

	private static TreeNode treeOneStar() {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		return root;
	}

	private static TreeNode treeTwo() {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);

		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);

		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		return root;
	}

	private static TreeNode treeThree() {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(4);
		root.right = new TreeNode(12);

		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(6);

		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(3);

		//root.left.right.left = new TreeNode(5);
		root.left.right.right = new TreeNode(7);

		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(14);

		root.right.left.left = new TreeNode(9);
		root.right.left.right = new TreeNode(11);

		root.right.right.left = new TreeNode(13);
		root.right.right.right = new TreeNode(15);

		root.right.right.right.right = new TreeNode(16);

		return root;
	}

	private static TreeNode treeFour() {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(4);
		root.right = new TreeNode(12);

		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(6);

		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(3);

		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(14);

		root.right.left.left = new TreeNode(9);
		root.right.left.right = new TreeNode(11);

		root.right.right.left = new TreeNode(13);
		root.right.right.right = new TreeNode(15);

		return root;
	}

	private static TreeNode treeFive() {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(4);
		root.right = new TreeNode(12);

		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(6);

		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(14);

		root.right.left.left = new TreeNode(9);
		root.right.left.right = new TreeNode(11);

		root.right.right.left = new TreeNode(13);
		root.right.right.right = new TreeNode(15);

		return root;
	}

	private static List<List<Integer>> convert(ArrayList<LinkedList<Integer>> in) {
		List<List<Integer>> out = new ArrayList<>();
		for (LinkedList<Integer> integers : in) {
			out.add(integers);
		}
		return out;
	}

	ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<>();
		if (node == null) {
			result.add(new LinkedList<>());
			return result;
		}

		LinkedList<Integer> prefix = new LinkedList<>(); prefix.add(node.data);
		ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
		for(LinkedList<Integer> left :leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
				weaveLists(left, right, weaved, prefix);
				result.addAll(weaved);
			}
		}
		return result;
	}

	void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
					ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {

		if(first.size() == 0 || second.size() == 0) {
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(first);
			result.addAll(second);
			results.add(result);
			return;
		}

		int headFirst =first.removeFirst();
		prefix.addLast(headFirst);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		first.addFirst(headFirst);

		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		weaveLists(first, second, results, prefix);
		prefix.removeLast();
		second.addFirst(headSecond);
	}

	private static class TreeNode {
		private Integer data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(Integer data) {
			this.data = data;
		}
	}
}
