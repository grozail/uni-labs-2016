package grozail.lab7;

import java.util.function.Function;

/**
 * Created by grozail on 26.10.16.
 * bt
 */
public class BinaryTree<T extends Comparable<T>> {
	private class Node {
		private T key;
		private Node left;
		private Node right;
		private Node parent;

		private Node() {
		}

		Node(T insertedElem, Node parent) {
			key = insertedElem;
			left = null;
			right = null;
			this.parent = parent;
			if (root != null) {
				switch (insertedElem.compareTo(parent.key)) {
					case -1: {
						parent.left = this;
						break;
					}
					case 0: {
						parent.right = this;
						break;
					}
					case 1: {
						parent.right = this;
						break;
					}
				}
			}
		}
	}

	private Node root;
	private Node current;
	private int wayLength;
	private Node minimum(Node node) {
		if (node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	public BinaryTree() {
		root = null;
		current = null;
		wayLength = 0;
	}

	public T getKey(Node node) {
		return node.key;
	}

	public void inorderTraversal() {
		inorderTraversal(root);
	}

	private void inorderTraversal(Node node) {      // sorted traversal
		if (node != null) {
			inorderTraversal(node.left);
			System.out.println(node.key);
			inorderTraversal(node.right);
		}
	}

	public void preorderTraversal() {
		preorderTraversal(root);
	}

	private void preorderTraversal(Node node) {
		if (node != null) {
			System.out.println(node.key);
			preorderTraversal(node.left);
			preorderTraversal(node.right);
		}
	}

	public void postorderTraversal() {
		postorderTraversal(root);
	}

	private void postorderTraversal(Node node) {
		if (node != null) {
			postorderTraversal(node.left);
			postorderTraversal(node.right);
			System.out.println(node.key);
		}
	}

	public void insert(T insertedElem) {
		if (root == null) {
			root = new Node(insertedElem, null);
		}
		else {
			Node localParent = null;
			current = root;
			while (current != null) {
				switch (insertedElem.compareTo(current.key)) {
					case -1: {
						localParent = current;
						current = current.left;
						break;
					}
					case 0: {
						localParent = current;
						current = current.right;
						break;
					}
					case 1: {
						localParent = current;
						current = current.right;
						break;
					}
				}
			}
			current = new Node(insertedElem, localParent);
		}
	}

	public T find(T searchedElem) {
		wayLength = 0;
		if (root == null) {
			return null;
		}
		current = root;
		while (current != null && current.key.compareTo(searchedElem) != 0) {
			switch (searchedElem.compareTo(current.key)) {
				case -1: {
					current = current.left;
					break;
				}
				case 1: {
					current = current.right;
					break;
				}
			}
			wayLength++;
		}

		if (current == null) {
			return null;
		}
		return current.key;
	}

	public void remove(T removedElem) {
		if (find(removedElem) != null) {
			if (current.left == null && current.right == null) {            //this is leave
				if (current.parent.left == current) {
					current.parent.left = null;
				}
				if (current.parent.right == current) {
					current.parent.right = null;
				}
			}
			else if (current.left != null && current.right == null) {     //has left descendant
				if (current.parent.left == current) {
					current.parent.left = current.left;
				}
				if (current.parent.right == current) {
					current.parent.right = current.left;
				}
				current.left.parent = current.parent;
			}
			else if (current.left == null) {                              //has right descendant
				if (current.parent.left == current) {
					current.parent.left = current.right;
				}
				if (current.parent.right == current) {
					current.parent.right = current.right;
				}
				current.right.parent = current.parent;
			}
			else {                                                        // both descendants exist 00A779 989898
				Node toRemove = current;
				Node toSwap = minimum(current.right);
				if (toRemove.parent.left == toRemove) {
					toRemove.parent.left = toSwap;
				}
				if (toRemove.parent.right == toRemove) {
					toRemove.parent.right = toSwap;
				}
				if (toSwap.parent.left == toSwap) {
					toSwap.parent.left = null;
				}
				if (toSwap.parent.right == toSwap) {
					toSwap.parent.right = null;
				}
				toSwap.parent = toRemove.parent;
				toSwap.left = toRemove.left;
				toSwap.right = toRemove.right;
			}
		}
	}

	public void clear() {
		clearingTraversal(root);
		root = null;
		current = null;
	}

	private void clearingTraversal(Node node) {
		if (node != null) {
			clearingTraversal(node.left);
			node.parent = null;
			clearingTraversal(node.right);

		}
	}

	public void functionalTraversal(Function<T, ?> function) {
		functionalTraversal(root, function);
	}


	private void functionalTraversal(Node node, Function<T, ?> function) {
		if (node != null) {
			functionalTraversal(node.left, function);
			function.apply(node.key);
			functionalTraversal(node.right, function);
		}
	}

	public Node findAncestor(T child1, T child2) {
		if (find(child1) == null) {
			return null;
		}
		Node childNode1 = current;
		int way1 = wayLength;
		if (find(child2) == null) {
			return null;
		}
		Node childNode2 = current;
		int way2 = wayLength;
		if (way1 == way2) {
			return current.parent;
		}
		int delta = 0;
		if (way1 > way2) {
			delta = way1 - way2;
			for (int i = 0; i < delta; i++) {
				childNode1 = childNode1.parent;
			}
		}
		else {
			delta = way2 - way1;
			for (int i = 0; i < delta; i++) {
				childNode2 = childNode2.parent;
			}
		}
		while (childNode1.key.compareTo(childNode2.key) != 0) {
			childNode1 = childNode1.parent;
			childNode2 = childNode2.parent;
		}
		System.out.println("+++++SUBTREE+++++");
		inorderTraversal(childNode1);
		System.out.println("+++++ANCESTOR+++++");
		return childNode1;
	}

}