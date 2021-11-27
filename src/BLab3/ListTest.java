package BLab3;


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
		for (int i = 0; i < 21; i++) {
			L.addLast(i);
		}

		try {
			L.addIterator(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(L);
		
		
		System.out.println("** Testing getIterator **");
		L.placeIterator();
		System.out.println(L.getIterator());
		L.advanceIterator();
		System.out.println(L.getIterator());
		L.reverseIterator();
		System.out.println(L.getIterator());
		
		
		System.out.println("\n** Testing addIterator **");
		L.advanceIterator();
		L.addIterator(99);
		System.out.println(L);
		L.advanceIterator();
		L.advanceIterator();
		L.advanceIterator();
		L.advanceIterator();
		L.reverseIterator();
		L.addIterator(98);
		System.out.println(L);
		
	}

}





































