package test;

import java.util.List;

import dao.AccountsDAO;
import model.Account;
import model.AccountManager;
import model.LoginInfo;

public class AccountManagerTest {
	public static void main(String[] args) {
		AccountsDAO accountsDAO = new AccountsDAO();
		AccountManager accountManager = new AccountManager();
		
		// アカウント存在確認テスト
		LoginInfo loginInfo1 = new LoginInfo("aaa@gmail.com", "abcde");
		LoginInfo loginInfo2 = new LoginInfo("aaa@gmail.com", "BadPassword");
		List<Account> accountList = accountsDAO.findAll();
		Account account = accountManager.findAccount(loginInfo1, accountList);
		System.out.println(account.getName());
		
		// ログインテスト
		System.out.println(accountManager.canLogin(loginInfo1, accountList));
		System.out.println(accountManager.canLogin(loginInfo2, accountList));
	}
}
