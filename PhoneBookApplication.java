import java.io.*;
import java.lang.Integer;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * PhoneBookApplication is a program that asks the user for a username and password, checks if
 * the user is an admin or a normal user, then gives access to certain functions accordingly.
 */


/* Assumptions: 
 * Neither the username or the password contain spaces.
 * Each entry is unique, so two entries should not have the same ID, phone number, first and last names
 * When the user adds an entry, the user inputs exactly the number of necessary attributes
 */
public class PhoneBookApplication {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		/* PhoneBookAdmin is instantiated, and its attributes are read from a file. */
		PhoneBookAdmin theAdmin = new PhoneBookAdmin();
		String[] adminParamList = null; // Array that holds theAdmin's parameters

		/* Attributes of theAdmin are being read from a file */
		String fileName = "adminInfo.txt";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				adminParamList = line.split(", ");
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println(fileName + " does not exist.");
		} catch (IOException ex) {
			System.out.println("IO Exception");
		}

		/* Sets theAdmin's attributes taken from the read file */
		theAdmin.setUsername(adminParamList[0]);
		theAdmin.setPassword(adminParamList[1]);
		theAdmin.setEmail(adminParamList[2]);

		/*  NormalUser is instantiated, and its attributes are read from a file. */
		NormalUser theUser = new NormalUser();
		String[] normalParamList = null; // Array that holds theUser's parameters

		/* Attributes of theUser are being read from a file */
		String fileName2 = "normalInfo.txt";
		String line2 = null;

		try {
			FileReader fileReader = new FileReader(fileName2);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line2 = bufferedReader.readLine()) != null) {
				normalParamList = line2.split(", ");
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println(fileName + " does not exist.");
		} catch (IOException ex) {
			System.out.println("IO Exception");
		} 

		/* Sets theUser's attributes taken from the read file */
		theUser.setId(Integer.parseInt(normalParamList[0]));
		theUser.setUsername(normalParamList[1]);
		theUser.setPassword(normalParamList[2]);

		/* Ask the user to login, check if the user is an admin or a normal user, then display the menu */
		int nextAction;
		int adminOrUser = 2; // random value of 2
		
		/* Keep asking user until login is successful */
		while ((adminOrUser != 0) && (adminOrUser != 1)) {
			adminOrUser = askAndCheckUser(adminParamList, normalParamList);
		}

		/* If the user is an admin */
		try {
			if (adminOrUser == 0) {
				do {
					displayAdminMenu();

					nextAction = input.nextInt();
					switch (nextAction) {
					case 1: //1. Add an entry
						theAdmin.userAdd(addedEntry());
						break;
					case 2: //2. Edit a phone entry of a given first name and last name
						System.out.println("* Input the first and last name of the entry you want to edit.");
						String editF = input.next();						
						String editL = input.next();
						theAdmin.editEntry(editF, editL);
						break;
					case 3: //3. Delete a phone entry of a given ID
						System.out.println("* Input the ID of the entry you want to delete.");
						int delID = input.nextInt();
						try {
							theAdmin.deleteEntry(delID);
						} catch (NullPointerException e) {
							System.out.println("* Your entry does not exist.");
						}
						break;
					case 4: //4. Sort the PhoneBookDirectory by ID
						theAdmin.sortDirById();
						System.out.println("* Successfully sorted.");
						break;
					case 5: //5. Search by phone number using Linear Search
						System.out.println("* Input the phone # of the entry you want to search.");
						String searchPhone = input.next();
						try {
							if (theAdmin.searchWithLinearSearch(searchPhone).getId() != -1) {
								System.out.println("* Entry found:");
								theAdmin.phonebook.LinearSearchByPhoneNumber(searchPhone).printBookEntry();
							}
						}
						catch (NullPointerException e) {
							System.out.println("Not found.");
						}
						break;
					case 6: //6. Search by ID using Binary Search
						System.out.println("* Input the ID of the entry you want to search.");
						int searchID = input.nextInt();
						try {
							if (theAdmin.phonebook.SearchbyIdBinarySearch(searchID).getId() != -1) {
								System.out.println("* Entry found:");
								System.out.println(theAdmin.phonebook.SearchbyIdBinarySearch(searchID));
							}
						} catch (NullPointerException e) {
							System.out.println("* Not found.");
						}
						break;
					case 7: //7. Print Admin’s info
						theAdmin.PrintUserInfo();
						break;
					case 8: //8. Change Password
						System.out.println("* Input the new password.");
						String newPW = input.next();
						theAdmin.changePassword(newPW);
						System.out.println("* The password has been changed.");
						break;
					case 9: //9. Change Username
						System.out.println("* Input the new username.");
						String newUN = input.next();
						theAdmin.changeUsername(newUN);
						System.out.println("* The username has been changed.");
						break;
					case 0: //0. Exit the program
						System.out.println("* Goodbye.");
						System.exit(0);
						break;
					default:
						System.out.println("* Invalid.");
					}

				} while (nextAction != 0);
			}

		
		/* If user is a normal user */
			if (adminOrUser == 1) {
				do {
					displayNormalMenu();
					nextAction = input.nextInt();
					switch (nextAction) {
					case 1: //1. Add an entry
						theUser.userAdd(addedEntry());
						break;
					case 2: //2. Edit a phone entry of a given first name and last name
						try {
							System.out.println("* Input the first and last name of the entry you want to edit.");
							String editF = input.next();
							String editL = input.next();
							theUser.editEntry(editF, editL);
						}
						catch (NullPointerException e) {
							System.out.println("* Not found.");
						}
						break;
					case 3: //3. Sort the PhoneBookDirectory
						theUser.sortDirById();
						System.out.println("* Successfully sorted.");
						break;
					case 4: //4. Search by phone number using Linear Search
						System.out.println("* Input the phone # of the entry you want to search.");
						String searchPhone = input.next();
						try {
							if (theUser.searchWithLinearSearch(searchPhone).getId() != -1) {
								System.out.println("* Entry found:");
								theUser.phonebook.LinearSearchByPhoneNumber(searchPhone).printBookEntry();
							}
						} catch (NullPointerException e) {
							System.out.println("* Not found.");
						}
						break;
					case 5: //5. Print user’s info
						theUser.PrintUserInfo();
						break;
					case 0: //0. Exit the program.
						System.out.println("* Goodbye.");
						System.exit(0);
						break;
					default:
						System.out.println("* Invalid.");
					}
				} while (nextAction != 0);
			}
		} catch (InputMismatchException ex) {
			System.out.println("Invalid input.");
		}

	}
	
	/* Method that asks the user to input the username and password, then logs them in if correct */
	public static int askAndCheckUser(String[] adminParamList, String[] normalParamList) {
		Scanner input = new Scanner(System.in);
		System.out.println("* Input username and password. ");
		String inputUser = " ";
		String inputPW = " ";
		try {
			inputUser = input.next();
			inputPW = input.next();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid input.");
		}

		if (inputUser.equals(adminParamList[0]) && inputPW.equals(adminParamList[1])) {
			System.out.println("* Admin successfully logged in.");
			return 0; // Returns 0 if user is an admin
		} else if (inputUser.equals(normalParamList[1]) && inputPW.equals(normalParamList[2])) {
			System.out.println("* Normal user successfully logged in.");
			return 1; // Returns 1 if user is a normal user
		} else {
			System.out.println("* Incorrect username and/or password.");
			return 2; // Returns 2 if user inputs incorrect username and password
		}
	}

	/* Method that displays the admin menu */
	public static void displayAdminMenu() {
		System.out.println("===========================\n" + "1. Add an entry\n" + "2. Edit a phone entry of a given first name and last name\n"
				+ "3. Delete a phone entry of a given ID\n" + "4. Sort the PhoneBookDirectory by ID\n"
				+ "5. Search by phone number (Linear Search)\n" + "6. Search by ID (Binary Search)\n"
				+ "7. Print Admin’s info\n" + "8. Change Password\n" + "9. Change Username\n" + "0. Exit the program.");
	}
	
	/* Method that displays the normal user menu */
	public static void displayNormalMenu() {
		System.out.println("1. Add an entry\n" + "2. Edit a phone entry of a given first name and last name\n"
				+ "3. Sort the PhoneBookDirectory\n" + "4. Search by phone number using Linear Search\n"
				+ "5. Print user’s info\n" + "0. Exit the program.");
	}

	/* Returns the entry that the user wants to add */
	public static PhoneBookEntry addedEntry() {
		Scanner input = new Scanner(System.in);
		PhoneBookEntry entry = new PhoneBookEntry();
		System.out.println("* Input an entry's id, first name, last name, email, zipcode, and phone number.");
		try {
			entry.setId(input.nextInt());
			entry.setfName(input.next());
			entry.setlName(input.next());
			entry.setEmail(input.next());
			entry.setZipCode(input.next());
			entry.setPhoneNum(input.next());
		} catch (InputMismatchException ex) {
			System.out.println("Invalid input.");
		}
		return entry;
	}
}
