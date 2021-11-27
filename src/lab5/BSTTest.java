/**
* BST.java
* @author Brandon Zhang
* @author Eric Tang
* CIS 22C Lab 5
*/

public class BSTTest {

	public static void main(String[] args) {
		IntegerComparator c = new IntegerComparator();
		BST<Integer> tree = new BST<Integer>();
		System.out.println(tree.search(5, false, c));
		List<Integer> list = new List<Integer>();
		int[] array = new int[] {4, 3, 79, 78, 35, 10, 22, 89, 67, 86, 73, 82, 34, 83, 82, 19, 56, 76, 59, 31};
		System.out.println("isEmpty: " + tree.isEmpty());
		int value = 0;
		
		tree.insert(5, c);
		tree.insert(6, c);
		tree.inOrderPrint();
		tree.remove(5, c);
		tree.inOrderPrint();
		
		
		//inserting into BST
//		for(int i=0;i<20;i++) {
//			tree.insert(array[i], c);
//			list.addLast(i);
//		}
		
		
		//testing List
//		for(int i: array) {
//			System.out.print(i + " ");
//		}
//		list.iteratorToIndex(0);
//		System.out.println(list.getIterator());
//		System.out.println(list.linearSearch(0));
		
		
		
		
		
		//testing BST
//		System.out.println("\nsize: " + tree.getSize());
//		System.out.print("inOrder: ");
//		tree.inOrderPrint();
//		System.out.print("preOrder: ");
//		tree.preOrderPrint();
//		System.out.println("findMax: " + tree.findMax());
//		System.out.println("findMin: " + tree.findMin());
//		
//		
//		System.out.println("\nisEmpty: " + tree.isEmpty());
//		tree.remove(4, c);
//		tree.search(3, true, c);
//		System.out.println("\nsize: " + tree.getSize());
//		System.out.print("inOrder: ");
//		tree.inOrderPrint();
//		System.out.print("preOrder: ");
//		tree.preOrderPrint();
//		System.out.println("findMax: " + tree.findMax());
//		System.out.println("findMin: " + tree.findMin());
	}

}
