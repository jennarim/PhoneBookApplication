/**
 * PhoneBookAdmin is a child class of User that has greater access to his/her phonebook,
 * such as the ability to add, edit, delete, sort, search, and change username/password
 */
public class PhoneBookAdmin extends User implements AdminInterface {
	private String email;
	
	/* Default constructor */
	public PhoneBookAdmin() {
		super();
	}
	
	/* Constructor that takes username, password, phonebook, and email */
	public PhoneBookAdmin(String username, String password, PhoneBookDirectory phonebook, String email) {
		super(username, password, phonebook);
		this.email = email;
	}
	
	// Getters and setters
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Overridden print method
	public void PrintUserInfo() {
		super.PrintUserInfo();
		System.out.println("E-mail: " + email);
	}
	
	/* Add an entry to the phonebook */
	public void userAdd(PhoneBookEntry entry) {
		int check = phonebook.addEntry(entry);
		if (check == 0)
			System.out.println("Phonebook is too full.");
		else if (check == 1)
			System.out.println("Entry was successfully added.");
	}
	
	/* Edit an entry given a first and last name */
	public void editEntry(String fName, String lName) {
		if (phonebook.Edit(fName, lName) == 1)
			System.out.println("Entry successfully edited.");
		else	
			System.out.println("Your entry doesn't exist.");
	}
	
	/* Delete an entry (replace the attributes) */
	public int deleteEntry(int id) {
		if (phonebook.DeleteEntry(id) == 0) {
			System.out.println("Your entry doesn't exist.");
			return 0;
		}
		else {
			System.out.println("Entry successfully deleted.");
			return 1;
		}
	}
	
	/* Sort the entries by id */
	public void sortDirById() {
		phonebook.sortEntriesById();
	}
	
	/* Searches phonebook by phone number and returns entry found */
	public PhoneBookEntry searchWithLinearSearch(String PhoneNumber) { 
		return phonebook.LinearSearchByPhoneNumber(PhoneNumber);
	}
	
	/* Searches phonebook by id */
	public void searchByBinarySearch(int id) {
		if (phonebook.SearchbyIdBinarySearch(id).getId() != -1) {
			System.out.println("Entry found: ");
			phonebook.SearchbyIdBinarySearch(id).printBookEntry();
		}
	}
	
	/* Change password */
	public void changePassword(String password) {
		super.password = password;
	}
	
	/* Change username */
	public void changeUsername(String username) {
		super.username = username;
	}
}
