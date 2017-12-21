/**
 * NormalUser is a child class of User that has basic access his/her a phonebook, such as
 * the ability to add, edit, sort, and search entries.
 */
public class NormalUser extends User implements NormalUserInterface {
	private int id;
	
	/* Default constructor */
	public NormalUser() {
		super();
	}
	
	/* Constructor that takes username, password, phonebook, and email */
	public NormalUser(String username, String password, PhoneBookDirectory phonebook, int id) {
		super(username, password, phonebook);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	// Overridden print method
	public void PrintUserInfo() {
		super.PrintUserInfo();
		System.out.println("ID: " + id);
	}
	
	/* Add an entry to the phonebook */
	public void userAdd(PhoneBookEntry entry) {
		int check = phonebook.addEntry(entry);
		if (check == 0)
			System.out.println("* Phonebook is too full.");
		else if (check == 1)
			System.out.println("* Entry was successfully added.");
	}
	
	/* Edit an entry given a first and last name */
	public void editEntry(String fName, String lName) {
		if (phonebook.Edit(fName, lName) == 1)
			System.out.println("* Entry successfully edited.");
		else	
			System.out.println("* Your entry doesn't exist.");
	}
	
	/* Sort the entries by id */
	public void sortDirById() {
		phonebook.sortEntriesById();
	}
	
	/* Searches phonebook by phone number and returns entry found */
	public PhoneBookEntry searchWithLinearSearch(String PhoneNumber) {
		return phonebook.LinearSearchByPhoneNumber(PhoneNumber);
	}
}
