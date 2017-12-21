/**
 * PhoneBookEntry is a class that represents one phone book entry. An entry contains
 * information such as ID, first and last name, email, zipcode, and phone number.
 */
public class PhoneBookEntry implements Comparable <PhoneBookEntry>{
	private int id;
	private String fName;
	private String lName;
	private String email;
	private String zipCode;
	private String phoneNumber;
	
	/* Default Constructor */
	public PhoneBookEntry() {
		id = -1;
		fName = "n/a";
		lName = "n/a";
		email = "n/a";
		zipCode = "n/a";
		phoneNumber = "n/a";
	}
	
	/* Constructor that takes in only first name */
	public PhoneBookEntry(String fName) {
		this.fName = fName;
	}
	
	/* Constructor that takes in only first name and phone number */
	public PhoneBookEntry(String fName, String phoneNumber) {
		this.fName = fName;
		this.phoneNumber = phoneNumber;
	}
	
	/* Constructor that takes in all attributes */
	public PhoneBookEntry(int id, String fName, String lName, String email, String zipCode, String phoneNumber) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
	}
	
	/* Getters and Setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNum(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public int compareTo(PhoneBookEntry entry) {		
		return this.id > entry.id ? 1 : (this.id < entry.id ? -1 : 0); 
	}
	
	/* Prints all the data fields */
	public void printBookEntry() {
		System.out.println("   ID: " + id + "\n   First Name: " + fName +
				           "\n   Last Name: " + lName + "\n   E-mail: " + email + 
				           "\n   Zip code: " + zipCode + "\n   Phone #: " + phoneNumber);
	}
	
	public String toString() {
		return "\n   Name: " + fName + " " + lName + "\n   ID: " + id + "\n   Email: " + email + "\n   Zipcode: " + zipCode +
				"\n   Phone number: " + phoneNumber;
	}
}
