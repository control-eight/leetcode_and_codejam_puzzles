package com.my;

/**
 * Created by alex.bykovsky on 4/10/17.
 */
public class BST {

	private Node root;

	public void insert(int newValue) {

		if (root == null) {
			root = new Node(newValue);
		} else {
			insert(root, newValue);
		}

	}

	private void insert(Node parent, int newValue) {

		if(newValue < parent.value) {
			if(parent.left == null) {
				parent.left = new Node(newValue);
			} else {
				insert(parent.left, newValue);
			}
		} else {
			if(parent.right == null) {
				parent.right = new Node(newValue);
			} else {
				insert(parent.right, newValue);
			}
		}
	}

	public Node find(int value) {

		return find(root, value);
	}

	private Node find(Node parent, int value) {

		if(parent == null) {
			return null;
		}

		if(parent.value == value) {
			return parent;
		}

		if(value < parent.value) {
			return find(parent.left, value);
		} else {
			return find(parent.right, value);
		}
	}

	private void delete(int value) {

		delete(root, null, value);
	}
	// 1: leaf node
	// 2: node has left child but doesn't have right child
	// 3: node has right child

	private void delete(Node node, Node parent, int value) {

		if(node == null) {
			return;
		}

		if(node.value == value) {

			//first case
			if(node.left == null && node.right == null) {

				if(parent != null) {

					if(parent.left == node) {
						parent.left = null;
					} else {
						parent.right = null;
					}

				}
				return;

				//second case
			} else if(node.right == null) {

				if(parent.left == node) {
					parent.left = node.left;
				} else {
					parent.right = node.left;
				}
				return;
				//third case
			} else {

				Node minParent = findMin(node.right);

				if(minParent.left != null) {
					node.value = minParent.left.value;
					minParent.left = null;
				} else {
					node.value = minParent.right.value;
					minParent.right = null;
				}
				return;
			}



		}

		if(value < node.value) {
			delete(node.left, node, value);
		} else {
			delete(node.right, node, value);
		}

	}

	private Node findMin(Node parent) {
		if(parent.left != null) {
			return findMin(parent);
		} else if(parent.right != null) {
			return findMin(parent.right);
		} else {
			return parent;
		}
	}

	//   7
	//  / \
	//  3 9
	// / \
	// 1  5
	//   / \
	//   4  6
	//


	//    7
	//   / \
	//   5 9
	//  /
	//  3
	// / \
	// 1  4

	private static class Node {

		private Node left;

		private Node right;

		private int value;

		public Node(int newValue) {
			this.value = newValue;
		}

	}

}
