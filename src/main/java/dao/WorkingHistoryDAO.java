package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.WorkingHistory;

public class WorkingHistoryDAO {
//	private final String JDBC_URL = "jdbc:h2:tcp://my-h2/my-db-name";
	private final static String JDBC_URL = "jdbc:h2:tcp://localhost/~/StudyManager";
	private final static String DB_USER = "admin";
	private final static String DB_PASS = "1234";
	
	// 全履歴の取得
	public static List<WorkingHistory> findOneMonthWorkingHistory(int targetUserID, Date StartDate, Date FinishDate) {
		List<WorkingHistory> workingHistoryList = new ArrayList<WorkingHistory>();
		// JDBCドライバを読み込む
		try { Class.forName("org.h2.Driver"); }
		catch (ClassNotFoundException e) { throw new IllegalStateException("JDBCドライバを読み込めませんでした"); }
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	
			// SELECT文の準備
			String sql = "SELECT ID, USER_ID, DATE, START_TIME, FINISH_TIME, START_SHULACK, FINISH_SHULACK FROM WORKING_HISTORY WHERE DATE BETWEEN ? AND ? AND USER_ID = ? ORDER BY DATE;"; // 日付順に取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setDate(1, StartDate);
			pStmt.setDate(2, FinishDate);
			pStmt.setInt(3, targetUserID);
	
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
	
			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int ID = rs.getInt("ID");
				int userID = rs.getInt("USER_ID");
				Date date = rs.getDate("DATE");
				Time startTime = rs.getTime("START_TIME");
				Time finishTime = rs.getTime("FINISH_TIME");
				String startShulack = rs.getString("START_SHULACK");
				String finishShulack = rs.getString("FINISH_SHULACK");
				WorkingHistory workingHistory = new WorkingHistory(ID, userID, date, startTime, finishTime, startShulack, finishShulack);
				workingHistoryList.add(workingHistory);
			}
	    }
		catch (SQLException e) {
	    	e.printStackTrace();
	      return null;
		}
		return workingHistoryList;
	}
	
	// 勤務履歴登録
	public static boolean createWorkingHistory(List<WorkingHistory> workingHistoryList) {
		// JDBCドライバを読み込む
		try { Class.forName("org.h2.Driver"); }
		catch (ClassNotFoundException e) { throw new IllegalStateException("JDBCドライバを読み込めませんでした"); }
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int size = workingHistoryList.size();
			for (int i = 0; i < size; i++) {
				WorkingHistory workingHistory = workingHistoryList.get(i);
				// INSERT文の準備
				String sql = "INSERT INTO WORKING_HISTORY (USER_ID, DATE, START_TIME, FINISH_TIME, START_SHULACK, FINISH_SHULACK) VALUES (?, ?, ?, ?, ?, ?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				pStmt.setInt(1, workingHistory.getUserID());
				pStmt.setDate(2, workingHistory.getDate());
				pStmt.setTime(3, workingHistory.getStartTime());
				pStmt.setTime(4, workingHistory.getFinishTime());
				pStmt.setString(5, workingHistory.getStartShulack());
				pStmt.setString(6, workingHistory.getFinishShulack());
				
				// INSERT文を実行（resultには追加された行数が代入される）
				int result = pStmt.executeUpdate();
				if (result != 1) return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// 勤務履歴削除
	public static boolean deleteWorkingHistory(int[] workingHistoryIDArr) {
		// JDBCドライバを読み込む
		try { Class.forName("org.h2.Driver"); }
		catch (ClassNotFoundException e) { throw new IllegalStateException("JDBCドライバを読み込めませんでした"); }
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int size = workingHistoryIDArr.length;
			for (int i = 0; i < size; i++) {
				int workingHistoryID = workingHistoryIDArr[i];
				// DELETE文の準備
				String sql = "DELETE FROM WORKING_HISTORY WHERE ID = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// DELETE文中の「?」に使用する値を設定しSQLを完成
				pStmt.setInt(1, workingHistoryID);
				
				// DELETE文を実行
				int result = pStmt.executeUpdate();
				if (result != 1) return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
