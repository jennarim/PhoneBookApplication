/**
 * User is the parent class that defines what a basic user should be. A User object
 * must have a username, password, and his/her own phonebook.
 */
public class User {
	protected String username;
	protected String password;
	protected PhoneBookDirectory phonebook;
	
	/* Default constructor */
	public User() {
		username = "n/a";
		password = "n/a";
		phonebook = new PhoneBookDirectory();
	}
	
	/* Constructor that takes username, password, and phonebook */
	public User(String username, String password, PhoneBookDirectory phonebook) {
		this.username = username;
		this.password = password;
		this.phonebook = phonebook;
	}
	
	/* Print user's info */
	public void PrintUserInfo() {

		System.out.println("* Username: " + username);
		System.out.println("* Password: " + password);
		System.out.println("* Phonebook: ");
		phonebook.printEntries();
	}

	/* Getters and setters */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PhoneBookDirectory getPhonebook() {
		return phonebook;
	}

	public void setPhonebook(PhoneBookDirectory phonebook) {
		this.phonebook = phonebook;
	}
	
	
}
