package lab2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BlockChain {
	
	private List<Block> blockchain = null;
	
	
	public BlockChain() {
		super();
		blockchain = new List<Block>();
		// TODO Auto-generated constructor stub
	}

	public List<Block> getBlockchain() {
		return blockchain;
	}

	public void setBlockchain(List<Block> blockchain) {
		this.blockchain = blockchain;
	}

	public static void main(String args[])  
	{  
		BlockChain myChain = new BlockChain();
		myChain.readTransaction("transactions.txt");
		myChain.inputTransaction();
	}
	
	public void inputTransaction() {
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
	
	public void readTransaction(String filename) {
		
		try  
		{  
			
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
