package lab3;

/**
 * Block.java
 * @author
 * @author
 * CIS 22C Lab2
 */

public class Block implements Transaction{

    private int transactionId;
    private String firstName;
    private String lastName;
    private String description;
    private long timeStampMillis;
    private long hashNextBlock;
    private static int numBlocks = 0;

    /**
     * Constructor for a Block
     * @param id the transaction id
     * @param first the customer first name
     * @param last the customer last name
     * @param description the transaction description
     * Calls System's currentTimeMillis() method
     * to assign the timeStampMillis
     * Increases the numBlocks
     */

    public Block(int transactionId, String firstName, String lastName, String description) {
		super();
		this.transactionId = transactionId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
	}

	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public long getTimeStampMillis() {
		return timeStampMillis;
	}



	public void setTimeStampMillis(long timeStampMillis) {
		this.timeStampMillis = timeStampMillis;
	}



	public long getHashNextBlock() {
		return hashNextBlock;
	}



	public void setHashNextBlock(long hashNextBlock) {
		this.hashNextBlock = hashNextBlock;
	}



	public static void setNumBlocks(int numBlocks) {
		Block.numBlocks = numBlocks;
	}



	/**
     * Returns the current number of blocks
     * @return the number of blocks
     */
    public static int getNumBlocks() {
        return numBlocks;
    }

    /**
     * Calculates the hash for the next
     * block
     * @param id the next block's id
     * @param firstName the customer first
     * name for the next block
     * @param lastName the customer last
     * name of the next block
     */
    public void computeHash(int id, String firstName, String lastName) {
        String name = firstName + lastName;
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            sum += name.charAt(i);
        }
        hashNextBlock = sum * 10000 + id;
    }
    
    public long getMyHash() {
        String name = firstName + lastName;
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            sum += name.charAt(i);
        }
        return sum * 10000 + transactionId;
    }

    @Override
	public String toString() {
		return "Block [transactionId=" + transactionId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", description=" + description + ", timeStampMillis=" + timeStampMillis + ", hashNextBlock="
				+ hashNextBlock + "]";
	} 
}