package lab3;
/**
 * Defines a Double-linked list class
 * @author Brandon_Zhang
 * @author
 */

import java.util.NoSuchElementException;

public class List<T> {

	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTOR ****/

	/**
	 * Instantiates a new List with default values
	 * 
	 * @postcondition Constructor is initialized with default values
	 */
	public List() {
		this.first = null;
		this.last = null;
		this.length = 0;
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored in the first node
	 * 
	 * @precondition the List is not empty / length != 0
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {

		if (length == 0) {
			throw new NoSuchElementException("getFirst: " + "List is Empty. No data to access");
		}

		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition the List is not empty / length != 0
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst: " + "List is Empty. No data to access");
		}

		return last.data;
	}

	/**
	 * Returns the current length of the list
	 * 
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty
	 * 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		if (length == 0) {
			return true;
		} else
			return false;
	}

	/**** MUTATORS ****/

	/**
	 * Creates a new first element
	 * 
	 * @param data the data to insert at the front of the list
	 * @postcondition node is added to beginning of the list
	 */
	public void addFirst(T data) {

		Node N = new Node(data);

		if (length == 0) { // edge case
			first = last = N;
		} else { // general case
			N.next = first;
			first.prev = N;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * 
	 * @param data the data to insert at the end of the list
	 * @postcondition node is added end of the list
	 */
	public void addLast(T data) {

		Node N = new Node(data);

		if (length == 0) { // edge case
			first = last = N;
		} else { // general case
			last.next = N;
			N.prev = last;
			last = N;
		}
		length++;
	}

	/**
	 * removes the element at the front of the list
	 * 
	 * @precondition the list must not be empty / length != 0
	 * @postcondition first element is removed from the list
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {

		if (length == 0) {
			throw new NoSuchElementException("removeFirst: " + "Nothing to remove. List is empty.");
		} else if (length == 1) { // edge case
			first = last = null;
		} else {
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * removes the element at the end of the list
	 * 
	 * @precondition the list must not be empty / length != 0
	 * @postcondition last element is removed from the list
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {

		if (length == 0) {
			throw new NoSuchElementException("removeLast: " + "Nothing to remove. List is empty.");
		} else if (length == 1) { //edge case
			first = last = null;
		} else {
			last = last.prev;
			last.next = null;
		}
		length--;
	}

	/**
	 * moves the iterator to the start of the list
	 * 
	 * @precondition 
	 * @postcondition 
	 * @throws 
	 */
	public void placeIterator() {
		iterator = first;
	}
	
	/**
	 * removes the element currently pointed to by the iterator.
	 * 
	 * @precondition iterator is not null, list is not empty
	 * @postcondition Iterator then points to null
	 * @throws 
	 */
	public void removeIterator() throws NoSuchElementException {
		if (iterator == null) 
			throw new NoSuchElementException("removeLast: " + "Nothing to remove. Iterator is empty.");
		if (length == 0) 
			throw new NoSuchElementException("removeLast: " + "Nothing to remove. List is empty.");
		iterator.prev.next = iterator.next;
		iterator.next.prev = iterator.prev;
		length --;
	}
	
	/**
	 * removes the element currently pointed to by the iterator.
	 * 
	 * @precondition 
	 * @postcondition Iterator then points to null
	 * @throws 
	 */
	public void addIterator(Node data) {
		iterator = null;
		
	}
	/*
	removeIterator: removes the element currently pointed to by the iterator.
	Postcondition: Iterator then points to null.
	addIterator: inserts an element after the node currently pointed to by the iterator
	advanceIterator: moves the iterator up by one node
	reverseIterator: moves the iterator down by one node
	*/
	
	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * List with each value on its own line At the end of the List a new line
	 * 
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String s = "";
		Node N = first;
		
		while(N != null) {
			s += N.data + " ";
			N = N.next;
		}
		
		return s + "\n";
	}

}