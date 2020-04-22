package sar;
import java.util.List;

public interface BoundaryInterfaceAccounts {
	  void createAccount(accounts T);
	  boolean activateAccount(int id, accounts T);
	  boolean updateAccount(int id, accounts T);
	  boolean deleteAccount(int id);
	  accounts getAccountDetail(int id);
	  List<accounts> getallAccount();
	  List<accounts> searchAccount(String s);
	  boolean createRating(ratings R, int id);
}
