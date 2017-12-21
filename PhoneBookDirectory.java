import java.util.Scanner;

/**
 * PhoneBookDirectory is a class that represents a phone book. It has methods such as adding
 * an entry, displaying it, searching through the entries by phone number or ID, sorting, 
 * editing entries, and deleting entries.
 */
public class PhoneBookDirectory {
	private int count;
	PhoneBookEntry[] PhoneBookEntries = new PhoneBookEntry[6];// Instantiates 6 objects of type PhoneBookEntry in an array
	
	public PhoneBookDirectory() {
		count = 0;
		/* Can initialize each phonebook */
		for (int i = 0; i < PhoneBookEntries.length; i++) {
			PhoneBookEntries[i] = new PhoneBookEntry();
		}
	}
	
	public PhoneBookEntry[] getPhoneBookEntries() {
		return PhoneBookEntries;
	}
	/* 1. Add an entry to the phonebook. */
	public int addEntry(PhoneBookEntry entry) {
		if (count == 6) 
			return 0; // Returns 0 if the array is full
		PhoneBookEntries[count] = entry;
		count++;
		return 1; // Returns 1 if the object was successfully added
	}
	
	/* 2. Print all phonebook entries. */
	public void printEntries() {
		/* Print the entry only if it exists */
		for (int i = 0; i < PhoneBookEntries.length; i++)
			if (PhoneBookEntries[i].getId() != -1)
				System.out.println(i+1 + ". " + PhoneBookEntries[i] + " ");
	}
	
	/* 3. Search for an entry by Phone Number (Linear Search) */
	public PhoneBookEntry LinearSearchByPhoneNumber(String PhoneNumber) {
		for (int i = 0; i < PhoneBookEntries.length; i++) {
			if (PhoneNumber.equals(PhoneBookEntries[i].getPhoneNumber()))
				return PhoneBookEntries[i]; // Returns the entry if phone number is found
		}
		return null; // Returns null if phone number is not found
	}
	
	/* 4. Search for an entry by id (Using Binary Search) */
	public PhoneBookEntry SearchbyIdBinarySearch(int id) {
		/* Don't assume the array will be sorted */
		sortEntriesById();

		int low = 0;
		int high = count - 1;

		while (high >= low) {
			int mid = (low + high)/2;
			if (id > PhoneBookEntries[mid].getId()) {
				low = mid + 1;
			} else if (id < PhoneBookEntries[mid].getId()) {
				high = mid - 1;
			} else { // If the id is found ...
				return PhoneBookEntries[mid]; // ... return the entry
			}
		}
		return null; // Returns null if id not found in entries
	}
	
	/* 5. Sort Phone Book Entries by id using the comparable interface and selection sort*/
	public void sortEntriesById() {
		for (int i = 0; i < count - 1; i++) {
			PhoneBookEntry currentMin = PhoneBookEntries[i];
			int currentMinIndex = i;
			for (int j = i + 1; j < count; j++) {
				if ((PhoneBookEntries[i + 1].compareTo(PhoneBookEntries[i])) < 0)
					currentMinIndex = i + 1;
			}
			if (currentMinIndex != i) {
				currentMin = PhoneBookEntries[currentMinIndex];
				PhoneBookEntries[currentMinIndex] = PhoneBookEntries[i];
				PhoneBookEntries[i] = currentMin;
			}
		}
	}
	
	// Note: SearchByName method is used to edit an entry
	public int SearchByName(String fName, String lName) {
		for (int i = 0; i < count; i++) {
			// If the first name and last name are found ... 
			if (fName.equals(PhoneBookEntries[i].getfName()) && lName.equals(PhoneBookEntries[i].getlName())) {
				return i; // ... return its index
			}
		}
		return -1; // Returns -1 if first and last names are not found
	}
	
	/* 6. Edit an entry given first and last name
	 * Assumption: User only inputs integers from 1 - 6 and nothing else */
	public int Edit(String firstName, String lastName) {
		Scanner input = new Scanner(System.in);
		if (SearchByName(firstName, lastName) == -1) {
			return -1; // Returns 0 if entry not found
		}
		int indexOfFoundEntry = SearchByName(firstName, lastName);
		
		System.out.println("* What would you like to edit? Input the corresponding integer.");
		System.out.println("1. ID\n2. First Name\n3. Last Name\n4. E-mail\n5. Zip Code\n6. Phone Number");
		int userChoice = input.nextInt();
		switch (userChoice) {
			case 0:
				break;
			case 1:
				System.out.println("* What would you like to change the ID to?");
				PhoneBookEntries[indexOfFoundEntry].setId(input.nextInt());
				break;
			case 2:
				System.out.println("* What would you like to change the first name to?");
				PhoneBookEntries[indexOfFoundEntry].setfName(input.next());
				break;
			case 3:
				System.out.println("* What would you like to change the last name to?");
				PhoneBookEntries[indexOfFoundEntry].setlName(input.next());
				break;
			case 4:
				System.out.println("* What would you like to change the e-mail to?");
				PhoneBookEntries[indexOfFoundEntry].setEmail(input.next());
				break;
			case 5:
				System.out.println("* What would you like to change the zip code to?");
				PhoneBookEntries[indexOfFoundEntry].setZipCode(input.next());
				break;
			case 6:
				System.out.println("* What would you like to change the phone number to?");
				PhoneBookEntries[indexOfFoundEntry].setPhoneNum(input.next());
				break;
			default:
				System.out.println("* Invalid input.");
				break;
		}
		return 1; // Returns 1 if object is found and successfully edited
		
	}
	
	/* 7. Delete an entry of a given id */
	public int DeleteEntry(int id) {
		PhoneBookEntry deletedEntry = SearchbyIdBinarySearch(id);
		/* If id can't be found, meaning deletedEntry is a default object which has an ID of -1, 
		 * then return 1.
		 * Assumption:
		 * - the user won't ever edit an ID to be -1.
		 */
		if (deletedEntry.getId() == -1)
			return 0;
		// Sets all attributes to defaults values (as defined in the default constructor)
		deletedEntry.setId(-1);
		deletedEntry.setfName("n/a");
		deletedEntry.setlName("n/a");
		deletedEntry.setEmail("n/a");
		deletedEntry.setZipCode("n/a");
		deletedEntry.setPhoneNum("n/a");
		/* Don't forget to decrement count and push the entry */
		count--;
		PhoneBookEntries[count] = deletedEntry;

		return 1; // Returns 1 if the item of the id is found and deleted
	}
}
