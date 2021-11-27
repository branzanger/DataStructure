
/**
* BST.java
* @author Brandon Zhang
* @author Eric Tang
* CIS 22C Lab 5
*/

import java.util.NoSuchElementException;
import java.util.Comparator;

public class BST<T> {
	public class Node {
		private T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/**** CONSTRUCTORS ****/

	/**
	 * Default constructor for the Stack class
	 * 
	 * @postcondition a new Stack object with all fields assigned default values
	 */
	public BST() {
		this.root = null;
	}

	/**
	 * Copy constructor for BST
	 * 
	 * @param bst the BST of which to make a copy.
	 * @param c   the way the tree is organized
	 */
	public BST(BST<T> bst, Comparator<T> c) {
		if (bst == null) {
			return;
		} else {
			copyHelper(bst.root, c);
		}
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 * @param c    the way the tree is organized
	 */
	private void copyHelper(Node node, Comparator<T> c) {
		if (node.left != null)
			copyHelper(node.left, c);

		this.insert(node.data, c);

		if (node.right != null)
			copyHelper(node.right, c);

		return;
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getRoot(): " + "Binary Stack Tree is Empty");
		}
		return root.data;
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		if(getSize() == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null)
			return 0;
		else {
			return getSize(node.left) + 1 + getSize(node.right);
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		int leftHeight = 0;
		int rightHeight = 0;
		if (node.left != null)
			getHeight(node.left);
		if (node.right != null)
			getHeight(node.right);

		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		} else {
			return rightHeight + 1;
		}
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMin(): " + "Binary Stack Tree is Empty");
		}
		return findMin(root);
	}

	/**
	 * Recursive helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if (node.left != null) {
			return findMin(node.left);
		}
		return node.data;
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMax(): " + "Binary Stack Tree is Empty");
		}
		return findMax(root);
	}

	/**
	 * Recursive helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if (node.right != null) {
			return findMax(node.right);
		}
		return node.data;
	}

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param data   the value to search for
	 * @param update whether to update the node's data with the given data
	 * @param c      the Comparator that indicates the way the data in the tree was
	 *               ordered
	 * @return the data stored in that Node of the tree is found or null otherwise
	 */
	public T search(T data, boolean update, Comparator<T> c) {
		if(isEmpty()) {
			return null;
		}
		return search(data, root, update, c);
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return the data stored in that Node of the tree is found or null otherwise
	 */
	private T search(T data, Node node, boolean update, Comparator<T> c) {
		if(node == null) {
			return null;
		}
		int value = c.compare(data, node.data);
		if (value == 0) {
			if (update) {
				T temp = node.data;
				node.data = data;
				return temp;
			} else {
				return node.data;
			}
		} else if (value < 0) { // recursively call left
			if (node.left != null) {
				search(data, node.left, update, c);
			} else {
				return null;
			}
		} else { // recursively call right
			if (node.right == null) {
				search(data, node.right, update, c);
			} else {
				return null;
			}
		}
		return null;
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 * @param c    the Comparator indicating how data in the tree is ordered
	 */
	public void insert(T data, Comparator<T> c) {
		if (isEmpty()) {
			root = new Node(data);
		} else {
			insert(data, root, c);
		}
		// size++;
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location to insert
	 * @param c    the Comparator indicating how data in the tree is ordered
	 */
	private void insert(T data, Node node, Comparator<T> c) {
		// recursively call left
		if (c.compare(data, node.data) <= 0) {
			if (node.left != null) {
				insert(data, node.left, c);
			} else {
				node.left = new Node(data);
			}
		} else { // recursively call right
			if (node.right != null) {
				insert(data, node.right, c);
			} else {
				node.right = new Node(data);
			}
		}
	}

	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 * @param c    the Comparator indicating how data in the tree is organized Note:
	 *             updates nothing when the element is not in the tree
	 * @precondition !isEmpty()
	 * @precondition the data is located in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void remove(T data, Comparator<T> c) throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("remove(): " + "Binary Stack Tree is empty.");
		}
		root = remove(data, root, c);
	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @param c    the Comparator indicating how data in the tree is organized
	 * @return an updated reference variable
	 */
	private Node remove(T data, Node node, Comparator<T> c) {
		if (node == null) {
			return null;
		}
		int value = c.compare(data, node.data);
		if (value < 0) {
			node.left = remove(data, node.left, c);
		} else if (value > 0) {
			node.right = remove(data, node.right, c);
		} else {
			if (node.left == null && node.right == null) {
				node = null;
			} else if (node.left == null && node.right != null) {
				node = node.right;
			} else if (node.left != null && node.right == null) {
				node = node.left;
			} else {
				node.data = findMin(node.right);
				node.right = remove(node.data, node.right, c);
			}
		}
		return node;
	}

	/*** ADDITONAL OPERATIONS ***/

	/**
	 * Prints the data in pre order to the console followed by a new line
	 */
	public void preOrderPrint() {
		preOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to preOrderPrint method Prints the data in pre order to the
	 * console followed by a new line
	 */
	private void preOrderPrint(Node node) {
		if (node == null)
			return;
		System.out.print(node.data + "\n");
		preOrderPrint(node.left);
		preOrderPrint(node.right);
	}

	/**
	 * Prints the data in sorted order to the console followed by a new line
	 */
	public void inOrderPrint() {
		inOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to inOrderPrint method Prints the data in sorted order to the
	 * console followed by a new line
	 */
	private void inOrderPrint(Node node) {
		if (node == null) {
			return;
		} else {
			inOrderPrint(node.left);
			System.out.print(node.data + "\n");
			inOrderPrint(node.right);
		}
	}

	/**
	 * Prints the data in post order to the console followed by a new line
	 */
	public void postOrderPrint() {
		postOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to postOrderPrint method Prints the data in post order to the
	 * console
	 */
	private void postOrderPrint(Node node) {
		if (node == null)
			return;

		preOrderPrint(node.left);
		preOrderPrint(node.right);
		System.out.println(node.data + "\n");
	}
}