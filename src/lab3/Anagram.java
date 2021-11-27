package lab3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagram {
	
	private List<String> word = null;

	public static void main(String args[])  
	{  
		System.out.println("Welcome to the Anagram Arranger!");
		System.out.println("Please enter the name of your input file: ");
		
		Scanner scanner = new Scanner(System.in);
		Anagram myWords = new Anagram();
		myAnagram.readWords();
		myAnagram.swapWords();
	}
	
	public void swapWords() {
		System.out.println("Welcome to Block Chain!");
		System.out.println("Total Number of Blocks: " + blockchain.getLength());
		Scanner scanner = new Scanner(System.in);
		String ans, name,  desc;
		String[] fullname;
		int id;
		while (true) {
			try {
				System.out.println("Would you like to add another transaction (y/n)?: ");
				ans = scanner.nextLine();
				if (!ans.equalsIgnoreCase("Y")) 
					break;
				System.out.println("Enter the transaction id: ");
				id = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter the customer first and last name: ");
				name = scanner.nextLine();
				fullname = name.split(" ");
				System.out.println("Enter the transaction description: ");
				desc = scanner.nextLine();
				System.out.println("full:" + name + "," + fullname.length);
				Block block = new Block(id, fullname[0], fullname[1], desc);
				this.addBlock(block);
				System.out.println("Total Number of Blocks: " + blockchain.getLength());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("Total Number of Blocks: " + blockchain.getLength());
		System.out.println("Enter the name of the output file: "); 
		String filename = scanner.next();
		this.saveBlockChain(filename);
		System.out.println("Goodbye!"); 
		
		scanner.close();
	}
	Public List<Character> swapWords(int[] pos, List<Character> words) {
		List<Character> newword = new List<Character>();
		for (int i=0; i<pos[1]; i++)
			words.advanceIterator();
		Node pos1 = 
	}
	
	public void saveBlockChain(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			Block current = blockchain.getFirst();			
			while (current != null) {
				//System.out.println("current:" + current.toString());
				writer.append("Id: " + current.getTransactionId() + "\n");
				writer.append("Name: " + current.getFirstName() + " "
						+ current.getLastName()+ "\n");
				writer.append("Description: " + current.getDescription() + "\n");
				writer.append("Time Stamp: " + System.currentTimeMillis() + "\n");
				blockchain.removeFirst();
				if (!blockchain.isEmpty()) {
					current = blockchain.getFirst();
					writer.append("Hash of Next Block: " + current.getMyHash() + "\n\n");
				}
				else {
					writer.append("Hash of Next Block: 0\n");
					break;
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void readWords() {
		
		try  
		{  
			System.out.println("Welcome to Block Chain!");
			System.out.println("Total Number of Blocks: " + blockchain.getLength());
			Scanner scanner = new Scanner(System.in);
			File file=new File(filename);    //creates a new file instance  
			FileReader fr=new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			//StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
			String line;  
			while((line=br.readLine())!=null)  
			{  
				int transaction_id = Integer.parseInt(line);
				//sb.append(line);      //appends line to string buffer  
				//sb.append("\n");     //line feed   
				line = br.readLine();
				String[] fullname = line.split(" ");
				line = br.readLine();
				Block block = new Block(transaction_id, fullname[0], fullname[1], line);
				//System.out.println(block.toString());
				addBlock(block);
			}  
			fr.close();    //closes the stream and release the resources  
			//System.out.println("Contents of File: ");  
			//System.out.println(sb.toString());   //returns a string that textually represents the object  
		}  catch(IOException e)  {
			e.printStackTrace();  
		}  
		
	}
	
	public int getBlockLength() {
		return blockchain.getLength();
	}
	
	public void addBlock(Block block) {
		//System.out.println(block.toString());
		//if (blockchain.isEmpty())
		//blockchain.addFirst(block);
		//else 
		blockchain.addLast(block);
	}

}
