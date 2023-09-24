package model;

import java.util.List;

public class AccountManager {
	// メールアドレスからアカウントが存在するか
	public Account isEmailExist(LoginInfo loginInfo, List<Account> accountList) {
		// 全探索
		int accountListSize = accountList.size();
		for (int i = 0; i < accountListSize; i++) {
			Account account = accountList.get(i);
			if (account.getEmail().equals(loginInfo.getEmail())) {
				return account;
			}
		}
		// なければnullのAccountを返す
		return new Account();
	}
	
	// ログイン判定
	public boolean canLogin(LoginInfo loginInfo, List<Account> accountList) {
		int accountListSize = accountList.size();
		for (int i = 0; i < accountListSize; i++) {
			if (loginInfo.getPass().equals(accountList.get(i).getPass())) return true;
		}
		return false;
	}
}
