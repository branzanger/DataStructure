package lab3;
import java.util.NoSuchElementException;

/**
 * Class to test List.java
 * 
 * @author Brandon Zhang
 * @author
 */
public class ListTest {
	public static void main(String[] args) {
		List<Integer> L = new List<>();
		System.out.println("Should print nothing (an empty list): " + L);

		System.out.println("**Testing addFirst**");
		L.addFirst(2);
		System.out.println("Should print 2: " + L);
		L.addFirst(1);
		System.out.println("Should print 1 2: " + L);

		System.out.println("**Testing getFirst**");
		System.out.println("Should print 1: " + L.getFirst()); // testing general case
		List<String> L2 = new List<>();
		try { // testing precondition
			System.out.println("Should print error: " + L2.getFirst());
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

		// Code to test addLast goes here!

		System.out.println("**Testing removeFirst**");
		L.removeFirst();
		System.out.println("Should print 2: " + L);
		L.removeFirst();
		System.out.println("Should print an empty list: " + L);
		System.out.println("Should print an error message for an empty List: ");
		try {
			L.removeFirst();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		// Tests for removeLast go here!

		System.out.println("**Testing isEmpty**");
		List<Integer> L3 = new List<>();
		System.out.println("Should print true: " + L3.isEmpty());
		// add another test for isEmpty() here!
		L3.addFirst(3);
		System.out.println("Should print false: " + L3.isEmpty());
		// add tests for getLength() here!
		System.out.println("**Testing getLength**");
		System.out.println("Should print 1: " + L3.getLength());
		L3.removeFirst();
		System.out.println("Should print 0: " + L3.getLength());
		
	}

}