import java.util.LinkedList;	// Import a linkedlist, so we don't have to create one.

/**
 * The Dictionary class uses a hash table to store game records with a separate chaining method.
 * @author Sam
 *
 */
public class Dictionary implements DictionaryADT {
	
	/**
	 * Declare and initialize the instance variables of this class.
	 */
	LinkedList<Record>[] dict;	// We will use an array of linked lists of type Record.
	int dictSize;
	final int HASH_COEFFICIENT = 33;	// The hash coefficient we will use in our hash function.
	int numElements = 0;	// This will be used to keep track of the number of records.
	
	/**
	 * The constructor for the dictionary takes in a size and will initialize each position in the array.
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public Dictionary(int size) {
		dictSize = size;
		dict = (LinkedList<Record>[]) new LinkedList[size];
		// Create a linked list of Records for each position in the array.
		for (int i = 0; i < size; i++) 
			dict[i] = new LinkedList<Record>();
	}

	/**
	 * This method inserts a record into the dictionary.
	 * Will throw an exception if that entry already exists.
	 * 
	 * @return 0 if no collisions occurred while inserting; -1 if one did occur.
	 */
	@Override
	public int insert(Record pair) throws DictionaryException {
		// Find the position of the linked list to store the record in by calling the hash function.
		LinkedList<Record> position = dict[hashFunction(pair.getConfig())];
		
		// If no records exists on the linked list, this will be the first record, so it is added.
		if (position.size() == 0) {
			dict[hashFunction(pair.getConfig())].add(pair);
			numElements ++;	// Note the addition of a new record.
			return 0;
		}
		
		// If a separate chain already exists...
		else {
			// For every Record in the linked list, check if it is the same Record as the one we want to insert.
			int i = 0;
			while (i < position.size()) {
				Record temp_rec = position.get(i);
				// If it is, throw an exception.
				if (temp_rec.getConfig().equals(pair.getConfig()))
					throw new DictionaryException();
				else i++;
			}
			
			// If no error is thrown,  add the Record to the end of the list.
			dict[hashFunction(pair.getConfig())].add(pair);
			numElements ++;
			return 1;
		}
	}
	
	/**
	 * Remove a specific record given its key.
	 * Throws an exception if no such record exists.
	 */
	@Override
	public void remove(String config) throws DictionaryException {
		// Use hash function to determine position of the chain where the record resides in.
		LinkedList<Record> position = dict[hashFunction(config)];
		
		int i = 0;
		boolean found = false;
		// Loop through the chain until a matching key is found.
		while (i < position.size() && found == false) {
			Record temp_rec = position.get(i);
			if (temp_rec.getConfig().equals(config)) {
				position.remove(i);
				numElements--;	// If successfully removed, reduce the count of records.
				found = true;	// Switch the flag to terminate the loop.
			}
			else i++;
		}
		// If we iterate through the loop and the key is still not found, throw an exception.
		if (found == false)
			throw new DictionaryException();
	}
	
	/**
	 * Gets the score of a record given its configuration.
	 * 
	 * @return the score.
	 */
	@Override
	public int get(String config) {
		int score = 0;
		// Use the hash function to determine which chain the record should reside in.
		LinkedList<Record> position = dict[hashFunction(config)];
		
		// If no such chain exists, then the record does not exists: -1 is returned.
		if (position.size() == 0) {
			return score = -1;
		}
		
		// If a chain exists there...
		else {
			int i = 0;
			boolean exists = false;
			// Check every record within the chain and compare the keys.
			while (i < position.size() && !exists) {
				// If the key is found, switch the flag to terminate the loop and return the record's score.
				if (position.get(i).getConfig().equals(config) == true) {
					exists = true;
					return score = position.get(i).getScore();
				}
				else i++;
			}
			// If we iterate through the entire loop and the key is somehow not found, return -1.
			if (!exists)
				return score = -1;
		}
		return score;
	}
	
	/**
	 * Returns the number of records in the hash table.
	 */
	@Override
	public int numElements() {
		return numElements;
	}
	
	/**
	 * Hash function will calculate a specific position to place a given key.
	 * It uses the polynomial function with a Horner's implementation.
	 * 
	 * @param config: the desired key.
	 * @return the position.
	 */
	private int hashFunction(String config) {
		// Convert each character into an integer and make that a coefficient.
		int value = (int)config.charAt(0);
		for (int i = 1; i < config.length(); i++) {
			value = (value * HASH_COEFFICIENT + (int)config.charAt(i)) % dictSize;	// We use the modulus operation to ensure that every position lies within the table size.
		}
		return value % dictSize;
	}
}
