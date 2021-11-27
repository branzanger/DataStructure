package lab5;


/**
* CustomerInterface.java
* @author Brandon Zhang
* @author Eric Tang
* CIS 22C, Lab 5
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CustomerInterface {
	private static Scanner scanner = new Scanner(System.in);
	private static NameComparator nameComparator = new NameComparator();
	private static ValueComparator valueComparator = new ValueComparator();

	public static void main(String[] args) {
		BST<MutualFundAccount> account_value = new BST<>();
		BST<MutualFundAccount> account_name = new BST<>();
		List<MutualFund> funds = new List<>();

		String first, last, email, password, mutualName, ticker;
		double cash, sharePrice, numShares, fee;

		readInput("mutual_funds.txt", funds);

		System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!");
		baseChoice(account_value, account_name, funds);

	}

	public static void baseChoice(BST<MutualFundAccount> account_value, BST<MutualFundAccount> account_name,
			List<MutualFund> funds) {
		boolean exit = false;
		String input = "";
		while (!exit) {
			System.out.println("\nPlease select from the following options:\n");

			System.out.println("A. Purchase a Fund");
			System.out.println("B. Sell a Fund");
			System.out.println("C. Display Your Current Funds");
			System.out.println("X. Exit");

			System.out.print("\nEnter your Choice: ");
			input = scanner.next();

			if ((input.equalsIgnoreCase("a"))) {
				purchaseFund(account_value, account_name, funds);
			} else if ((input.equalsIgnoreCase("b"))) {
				sellFunds(account_value, account_name);
			} else if ((input.equalsIgnoreCase("c"))) {
				displayFunds(account_value, account_name);
			} else if ((input.equalsIgnoreCase("x"))) {
				exit = true;
			} else {
				System.out.println("\nInvalid Choice!");
			}

		}
	}

	public static void purchaseFund(BST<MutualFundAccount> account_value, BST<MutualFundAccount> account_name,
			List<MutualFund> funds) {
		boolean valid = false;
		String input = "";
		int fundIndex = 0, numOfShares = 0;

		while (!valid) {
			System.out.println("\nPlease select from the options below:\n");

			funds.placeIterator();
			for (int i = 0; i < funds.getLength(); i++) {
				System.out.println((i + 1) + ". " + funds.getIterator().toString());
				funds.advanceIterator();
			}

			try {
				System.out.print("\nEnter your Choice: (1-" + funds.getLength() + "): ");
				input = scanner.next();
				fundIndex = Integer.parseInt(input);

				if (fundIndex > 0 && fundIndex <= funds.getLength()) {
					System.out.print("\nEnter the number of shares to purchase: ");
					input = scanner.next();
					numOfShares = Integer.parseInt(input);
					valid = true;
				}
			} catch (NumberFormatException e) {
				// aSystem.out.println(e.getLocalizedMessage());
			}

			// if input is valid we update shares
			if (valid) {
				funds.iteratorToIndex(fundIndex - 1);
				update(funds.getIterator(), account_value, account_name, numOfShares);
			} else {
				System.out.println("\nInvalid Choice!");
			}

		}
	}

	public static void sellFunds(BST<MutualFundAccount> account_value, BST<MutualFundAccount> account_name) {
		
		if (account_name.isEmpty()) {
			System.out.println("\nYou don't have any funds to display at this time.");
			return;
		}
		
		System.out.println("\nYou own the following mutual funds: ");
		account_name.inOrderPrint();
		
		scanner.nextLine();
		System.out.print("Enter the name of the fund to sell: ");
		String fundName = scanner.nextLine();
		System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");
		String sharesToSell = scanner.nextLine();
		
		fundName = fundName.trim();
		MutualFundAccount fundToSell = new MutualFundAccount(new MutualFund(fundName));
		MutualFundAccount temp = null;
		int shares = 0;
		boolean valid = false;
		if (account_name.search(fundToSell, false, nameComparator) != null) {
			temp = account_name.search(fundToSell, false, nameComparator);
			try {
				if (sharesToSell.equalsIgnoreCase("all")) {
					shares = (int)temp.getNumShares();
					valid = true;
				}else {
					shares = Integer.parseInt(sharesToSell);
					if(shares >= 0|| shares <= temp.getNumShares()) {
						valid = true;
					}
				}
			} catch (NumberFormatException e) {
				// System.out.println(e.getLocalizedMessage());
			}
		}
		
		if(valid) {
			update(temp.getMf(), account_value, account_name, (shares*-1));
		}else {
			System.out.println("\nInvalid Choice!");
		}

	}
	
	/**
	 * Displays a Binary stack Tree
	 * 
	 * @param account_value Binary stack tree that holds account Values
	 * @param account_name Binary stack tree that holds account Names
	 */
	public static void displayFunds(BST<MutualFundAccount> account_value, BST<MutualFundAccount> account_name) {
		String input = "";
		int choice = 0;

		if (account_name.isEmpty()) {
			System.out.println("\nYou don't have any funds to display at this time.");
			return;
		}

		System.out.println("\nView Your Mutual Funds By:\n");
		System.out.println("1. Name");
		System.out.println("2. Value");

		try {
			System.out.print("\nEnter your Choice (1 or 2): ");
			input = scanner.next();
			choice = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// System.out.println(e.getLocalizedMessage());
		}

		System.out.println();
		if (choice == 1) {
			account_name.inOrderPrint();
		} else if (choice == 2) {
			account_value.inOrderPrint();
		} else {
			System.out.println("\nInvalid Choice!");
		}
	}

	public static void update(MutualFund mf, BST<MutualFundAccount> account_value, BST<MutualFundAccount> account_name,
			int numOfShares) {
		MutualFundAccount acc = new MutualFundAccount(mf, numOfShares);
		
		if (account_name.search(acc, false, nameComparator) == null) {
			account_name.insert(acc, nameComparator);
			account_value.insert(acc, valueComparator);
		} else {
			MutualFundAccount original = account_name.search(acc, false, nameComparator);
			account_name.search(acc, true, nameComparator);
			account_value.remove(original, valueComparator);
			acc.updateShares(acc.getNumShares() + original.getNumShares());
			account_value.insert(acc, valueComparator);
		}
		MutualFundAccount temp = new MutualFundAccount(mf, 0);
		account_value.remove(temp, valueComparator);
		account_name.remove(temp, valueComparator);
	}

	public static void readInput(String filename, List<MutualFund> funds) {
		try {
			File file = new File(filename);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String fundName, ticker;
			double pricePerShare;
			while ((fundName = br.readLine()) != null) {
				ticker = br.readLine();
				pricePerShare = Double.parseDouble(br.readLine());
				MutualFund mf = new MutualFund(fundName, ticker, pricePerShare);
				funds.addLast(mf);
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
