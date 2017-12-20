/**
 * PhoneBook is a class that demonstrates the versatility of OOP in that objects of the same class
 * can be instantiated from separate constructors. This class has no direct effect on 
 * PhoneBookApplication, and simply exists to demonstrate this aspect of OOP.
 */
import java.util.Scanner;
public class PhoneBook {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		PhoneBookEntry[] contacts = new PhoneBookEntry[3]; // Instantiates 3 objects of type PhoneBookEntry in an array
		
		/* Asks for the first object
		 * Assumptions:
		 * - the user inputs all the first object's attributes necessary 
		 * - the user inputs a string that doesn't contain spaces for the first name, last name, email, and phone number */
		System.out.println("Input the attributes of the first object in order of: id, first name, last name, email, zipcode, and phone number.");
		contacts[0] = new PhoneBookEntry(input.nextInt(), input.next(), input.next(), input.next(), input.next(), input.next());
		
		/* Ask for the second object
		 * Assumptions:
		 * - the user inputs all the second object's attributes necessary 
		 * - the user inputs a string that doesn't contain spaces for the first name and phone number */
		System.out.println("Input the attributes of the second object in order of: first name, phone number.");
		contacts[1] = new PhoneBookEntry(input.next(), input.next());
		
		/* Ask for the third object: 
		 * Assumptions:
		 * - the user inputs all the third object's attributes necessary
		 * - the user inputs a string that doesn't contain spaces for the first name */
		System.out.println("Input the attributes of the third object in order of: first name.");
		contacts[2] = new PhoneBookEntry(input.next());
		
		/* 1. Change the phone number of John Smith to 202555555 */
		contacts[0].setPhoneNum("202555555");
		System.out.println("John Smith's phone number is now " + contacts[0].getPhoneNumber());
		/* 2. Print the attributes of John Smith using the method PrintBookEntry(). */
		contacts[0].printBookEntry();
		/* 3. Assign the Zipcode of John Smith to James’s. */
		contacts[0].setZipCode(contacts[1].getZipCode());
		System.out.println("John Smith's zip code is now " + contacts[0].getZipCode());
	}

}
