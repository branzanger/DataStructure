
/**
* CustomerInterface.java
* @author PARTNER1_NAME_HERE
* @authoer PARTNER2_NAME_HERE
* CIS 22C, Lab 6
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CustomerInterface2 {

	public static void main(String[] args) throws IOException {
		final int NUM_MUTUAL_FUNDS = 7;
		final int NUM_CUSTOMERS = 100;
		HashTable<MutualFund> funds = new HashTable<>(NUM_MUTUAL_FUNDS * 2);
		HashTable<Customer> customers = new HashTable<>(NUM_CUSTOMERS);

		DecimalFormat df = new DecimalFormat("###,##0.00");

		String first, last, email, password, mutualName, ticker;
		double cash, sharePrice, numShares, fee;

		File file1 = new File("mutual_funds.txt");
		File file2 = new File("customers.txt");
		
		

	}
}
