
public interface NormalUserInterface {
	public void userAdd(PhoneBookEntry entry);
	public void editEntry(String fName, String lName);
	public void sortDirById();
	public PhoneBookEntry searchWithLinearSearch(String PhoneNumber);
}
