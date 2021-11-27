package lab2;


/**
 * Defines a singly-linked list class
 * @author
 * @author
 */
 
import java.util.NoSuchElementException;
 
public class List<T> {
    private class Node {
        private T data;
        private Node next;
       
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
   
    private int length;
    private Node first;
    private Node last;
   
    /****CONSTRUCTOR****/
   
    /**
     * Instantiates a new List with default values
     * @postcondition
     */
    public List() {
    	length = 0;
    	first = null;
    	last = null;
    }
   
    /****ACCESSORS****/
   
    /**
     * Returns the value stored in the first node
     * @precondition length != 0
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
    	  if (length == 0) {
              throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
          }
          return first.data; 
    }
   
    /**
     * Returns the value stored in the last node
     * @precondition length != 0
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
    	if (length == 0) {
            throw new NoSuchElementException("getLast: List is Empty. No data to access!");
        }
        return last.data;
    }
   
    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }
   
    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }
   
    /****MUTATORS****/
   
    /**
     * Creates a new first element
     * @param data the data to insert at the
     * front of the list
     * @postcondition
     */
    public void addFirst(T data) {
        Node node = new Node(data);
        node.next = first;
        first = node;
        if (last == null)
        	last = first;
        length++;
    }
   
    /**
     * Creates a new last element
     * @param data the data to insert at the
     * end of the list
     * @postcondition
     */
    public void addLast(T data) {
        Node node = new Node(data);
        if (last != null) 	
        	last.next = node;
        last = node;
        if (first == null)
        	first = last;
        length++;
    }
   
    /**
    * removes the element at the front of the list
    * @precondition length != 0
    * @postcondition 
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
    	if (length == 0) { //precondition
    		throw new NoSuchElementException("removeFirst: list is empty. Cannot remove.");
    	} else if (length == 1) { //edge case
    		first = last = null;
    	} else 
    		
    	first = first.next; 
    	length--;
    }

    /**
     * removes the element at the end of the list
     * @precondition length != null
     * @postcondition
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
    	if (length == 0) { //precondition
    		throw new NoSuchElementException("removeFirst: list is empty. Cannot remove.");
    	} else if (length == 1) { //edge case
    		first = last = null;
    	} 
    	// length >= 2
    	Node prev = first;
    	Node curr = first.next;
    	while (curr.next != null) {
    		prev = curr;
    		curr = curr.next;
    	}
    	last = prev;
    	length--;
    }
   
    /****ADDITIONAL OPERATIONS****/
   
    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
       return "";
    }
   
 
}