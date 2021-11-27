package BLab3;

import java.util.NoSuchElementException;

/**
 * Defines a doubly-linked list class
 * 
 * @author Brandon_Zhang
 * @author Melisa Sever
 */

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
		this.iterator = null;
		this.length = 0;
	}

	/**
	 * Copy constructor for the List class
	 * 
	 * @param original the List to copy
	 * @postcondition a new List object which is an identical, but distinct, copy of
	 *                original
	 */
	public List(List<T> original) {
		this.first = original.first;
		this.last = original.last;
		this.iterator = original.iterator;
		this.length = original.length;

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
		if (isEmpty()) {
			throw new NoSuchElementException("getFirst(): " + "List is empty.");
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
		if (isEmpty()) {
			throw new NoSuchElementException("getLast(): " + "List is Empty.");
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

	/**
	 * Returns the element current pointed at by the iterator
	 * 
	 * @precondition iterator must not be null / !offEnd()
	 * @return element pointed at by iterator
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getIterator() throws NoSuchElementException {
		if (offEnd()) {
			throw new NoSuchElementException("getIterator(): " + "Iterator is not pointing to anything.");
		}
		return iterator.data;
	}

	/**
	 * returns whether the iterator is off the end of the list, i.e. is NULL
	 * 
	 * @returns whether iterator is off the end of the list
	 */
	public boolean offEnd() {
		if (iterator == null) {
			return true;
		}
		return false;
	}

	/**
	 * overrides the equals method for object to compares this list to another list
	 * to see if they contain the same data in the same order.
	 * 
	 * @param L the List to compare to this List
	 * @return whether the two Lists are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof List)) {
			return false;
		} else {
			List<T> L = (List<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;

				while (temp1 != null) {
					if (!(temp1.data.equals(temp2.data))) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
			}
			return true;
		}
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

		if (isEmpty()) {
			first = last = N;
		} else {
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

		if (isEmpty()) {
			first = last = N;

		} else {
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
		if (isEmpty()) { // precondition
			throw new NoSuchElementException("removeFirst(): " + "List is empty.");
		} else if (length == 1) { // edge case
			first = last = null;
		} else { // general case
			first = first.next;
		}
		length--;
	}

	/**
	 * removes the element at the end of the list
	 * 
	 * @precondition list is not empty
	 * @postcondition removes last node
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast(): list is empty. Nothing to remove");
		} else if (length == 1) { // edge case
			first = last = null;
		} else { // general case
			last = last.prev;
		}
		length--;
	}

	/**
	 * moves the iterator the start of the list
	 * 
	 * @postcondition moves the iterator to the start of the list
	 */
	public void placeIterator() {
		iterator = first;
	}

	/**
	 * removes the element current pointed to by the iterator
	 * 
	 * @precondition iterator must be on the list / !offEnd()
	 * @postcondition iterator then points to null
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeIterator() {
		if (offEnd()) {
			throw new NoSuchElementException("removeIterator(): " + "Iterator is not on list");
		} else if (iterator == first) { // edge case
			removeFirst();
		} else if (iterator == last) { // edge case
			removeLast();
		} else { // general case
			iterator.prev.next = iterator.next;
			iterator = null;
		}
		length--;
	}

	/**
	 * inserts an element after the node currently pointed to by the iterator
	 * 
	 * @precondition iterator must be on the list / !offEnd()
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void addIterator(T data) {
		if (offEnd()) {
			throw new NoSuchElementException("addIterator(): " + "Iterator is not on list");
		}else if (iterator == last) { // edge case
			addLast(data);
		} else {                      // general case
			
			Node N = new Node(data);
			N.next = iterator.next;
			N.prev = iterator;
			iterator.next.prev = N;
			iterator.next = N;
		}
		length++;
	}
	
	/**
	 * moves the iterator up by one node
	 * 
	 * @precondition iterator must be on the list / offEnd()
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void advanceIterator() {
		if(offEnd()) {
			throw new NoSuchElementException("advanceIterator():" + "Iterator is null and cannot advance");
		}
		iterator = iterator.next;
	}
	
	/**
	 * moves the iterator down by one node
	 * 
	 * @precondition iterator must be on the list / offEnd()
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void reverseIterator() {
		if(offEnd()) {
			throw new NoSuchElementException("advanceIterator():" + "Iterator is null and cannot reverse");
		}
		iterator = iterator.prev;
	}
	
	/**** ADDITIONAL OPERATIONS ****/
	
	/**
	 * prints the contents of the linked list to the screen in the format #: <element> followed by a newline
	 * 
	 * @return the List as a String formatted with index and new lines i.e. 1: <element> \n2: <element>
	 */
	public String printNumberedList() {
		String result = "";
		Node temp = first;
		
		for(int i = 0;i < length;i++) {
			result += (i+1) + ": " + temp.data + "\n";
			temp = temp.next;
		}
		return result + "\n";
	}
	
	/**
	 * List with each value on its own line At the end of the List a new line
	 * 
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;
		while (temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		return result;
	}
}
