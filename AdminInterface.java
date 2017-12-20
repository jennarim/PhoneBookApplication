
public interface AdminInterface {
	public void userAdd(PhoneBookEntry entry);
	public void editEntry(String fName, String lName);
	public int deleteEntry(int id);
	public void sortDirById();
	public PhoneBookEntry searchWithLinearSearch(String PhoneNumber);
	public void searchByBinarySearch(int id);
	public void changePassword(String password);
	public void changeUsername(String username);
}
