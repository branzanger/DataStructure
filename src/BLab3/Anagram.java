package BLab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import lab2.Block;

/**
 * Class for Anagram
 * 
 * @author Brandon Zhang
 * @author
 */


public class Anagram {
	
	private static ArrayList<List<Character>> wordList = new ArrayList<List<Character>>();
	private static Scanner scanner = null;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		stuff();
		scanner.close();
		System.out.println("Bye!");
		saveWordList("output.txt");
	}
	
	public static void stuff() {
		String filename;
		boolean validName = false;
		System.out.println("Welcome to the Anagram Arranger!\n");
		
		while(!validName) {
			System.out.print("Please enter the name of your input file: \n");
			filename = scanner.nextLine();
			//filename = "input.txt";// = scanner.next();
			
			try {
				readInput(filename);
				validName = true;
			}catch(Exception e) {
				System.out.println("\nSorry. I cannot find a file by that name!");
			}
		}
		
		for(int i=0;i<wordList.size();i++) {
			rearranger(i);
		}
		
	}
	
	/**
	 * Returns a String array of the input file separated by line
	 * 
	 * @precondition filename must be a valid filename
	 * @param String filename
	 */
	public static void readInput(String filename){
		try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			
			String line;
			int lineIndex = 0;
			while((line = br.readLine()) != null) {
				wordList.add(new List<Character>());
				
				for(int i = 0;i<line.length();i++) {
					wordList.get(lineIndex).addLast(line.charAt(i));
				}
				lineIndex++;
			}
			fr.close();
			br.close();
			//debugging
//			for(List<Character> list : wordList) {
//				System.out.println(list);
//			}
		}catch(Exception e) {
			//e.printStackTrace();
		}
	}
	
	public static void rearranger(int index) {
		List<Character> list = wordList.get(index);
		int[] positions = new int[2];
		String word = list.toString().replaceAll("\\s", "");
		System.out.println("\nWord #" + (index+1) + " is " + word);
		
		boolean done = false;
		while(!done) {
			list = wordList.get(index);
			System.out.print(list.printNumberedList());
			
			positions = getPosition(list.getLength());
			System.out.print("\n" + list);
			
			String str = "";
			for(int i = 0;i<positions[1];i++) {
				if(positions[0] == (i+1) || positions[1] == (i+1)) {
					str += "^";
				}else {
					str += " ";
				}
				str += " ";
			}
			System.out.println(str + "\n");
			if(confirm("Are these the letters you wish to swap? (y/n): ")) {
				swap(positions, index);
			}
			System.out.println("\nThe new word is: " + wordList.get(index));
			if(!confirm("Want to keep rearranging? (y/n): ")) {
				done = true;
			}
		}
	}
	
	public static void swap(int[] positions, int index) {
		List<Character> list = wordList.get(index);
		list.placeIterator();
		for(int i = 0;i<positions[0]-1;i++) {
			list.advanceIterator();
		}
		char temp = list.getIterator();
		list.placeIterator();
		for(int i = 0;i<positions[1]-1;i++) {
			list.advanceIterator();
		}
		char temp2 = list.getIterator();
		list.placeIterator();
		List<Character> newList = new List<Character>();
		for(int i=0;i<list.getLength();i++) {
			if(i == (positions[0]-1)) {
				newList.addLast(temp2);
			}else if(i == (positions[1]-1)) {
				newList.addLast(temp);
			}else {
				newList.addLast(list.getIterator());
			}
			list.advanceIterator();
		}
		wordList.set(index, newList);
	}
	
	public static boolean confirm(String text) {
		boolean valid = false;
		while(!valid) {
			System.out.print(text);
			String input = scanner.next();
			if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
				valid = true;
				return true;
			}else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
				valid = true;
				return false;
			}else {
				System.out.println("Invalid Entry!\n");
			}
		}
		return false;
	}
	
	public static int[] getPosition(int length) {
		boolean valid = false;
		String input;
		String[] strArray;
		int[] index = new int[1];
		while(!valid) {
			System.out.print("\nEnter the position numbers of the two letter you wish to swap: ");
			input = scanner.nextLine();
			if(input.equals("")) {
				input = scanner.nextLine();
			}
			System.out.println(input);
			strArray = input.split("\\s");
			index = new int[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
			    index[i] = Integer.parseInt(strArray[i]);
			}
			if(index.length == 2 && index[0] != index[1] && index[0] < index[1] && index[1] <= length) {
				valid = true;
			}else {
				System.out.println("Invalid Entry!");
			}
		}
		return index;
	}
	
	public static void saveWordList(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			for (List<Character> word : wordList) {
				word.placeIterator();
				writer.append(word.toString() + "\n");
			}
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}

































