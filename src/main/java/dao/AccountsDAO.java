package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;

public class AccountsDAO {
//	private final String JDBC_URL = "jdbc:h2:tcp://my-h2/my-db-name";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/StudyManager";
	private final String DB_USER = "admin";
	private final String DB_PASS = "1234";
	
	// 全アカウントの取得
	public List<Account> findAll() {
		List<Account> accountList = new ArrayList<Account>();
		// JDBCドライバを読み込む
		try { Class.forName("org.h2.Driver"); }
		catch (ClassNotFoundException e) { throw new IllegalStateException("JDBCドライバを読み込めませんでした"); }
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

		// SELECT文の準備
		String sql = "SELECT ID, EMAIL, PASS, NAME FROM ACCOUNTS ORDER BY EMAIL;"; // メールアドレスで辞書順に取得
		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SELECTを実行
		ResultSet rs = pStmt.executeQuery();

		// SELECT文の結果をArrayListに格納
		while (rs.next()) {
			int ID = rs.getInt("ID");
			String email = rs.getString("EMAIL");
			String pass = rs.getString("PASS");
			String name = rs.getString("NAME");
	        Account account = new Account(ID, email, pass, name);
	        accountList.add(account);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
		
	    return accountList;
	}
	
	// 新規アカウント作成
	public boolean create(Account newAccount) {
		// JDBCドライバを読み込む
		try { Class.forName("org.h2.Driver"); }
		catch (ClassNotFoundException e) { throw new IllegalStateException("JDBCドライバを読み込めませんでした"); }
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備
			String sql = "INSERT INTO ACCOUNTS (EMAIL, PASS, NAME) VALUES (?, ?, ?);"; // メールアドレスで辞書順に取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, newAccount.getEmail());
			pStmt.setString(2, newAccount.getPass());
			pStmt.setString(3, newAccount.getName());
			
			// INSERT文を実行（resultには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			if (result != 1) return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
