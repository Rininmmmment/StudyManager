package test;

import java.util.List;

import dao.AccountsDAO;
import model.Account;

public class AccountsDAOTest {
	public static void main(String[] args) {
		AccountsDAO accountsDAO = new AccountsDAO();
		
		// アカウント取得テスト
		List<Account> accountList = accountsDAO.findAll();
		System.out.println(accountList.get(0).getID());
	    System.out.println(accountList.get(0).getPass());
	    
	    // アカウント作成テスト
	    Account testAccount = new Account(-1, "aaa@gmail.com", "abcde", "すたみなたろう");
	    System.out.println(accountsDAO.create(testAccount));
	}
}
