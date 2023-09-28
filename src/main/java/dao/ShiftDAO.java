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

import model.Shift;

public class ShiftDAO {
//	private final String JDBC_URL = "jdbc:h2:tcp://my-h2/my-db-name";
	private final static String JDBC_URL = "jdbc:h2:tcp://localhost/~/StudyManager";
	private final static String DB_USER = "admin";
	private final static String DB_PASS = "1234";
	
	// 全アカウントの取得
	public static List<Shift> findOneMonthShift(int targetUserID, Date StartDate, Date FinishDate) {
		List<Shift> shiftList = new ArrayList<Shift>();
		// JDBCドライバを読み込む
		try { Class.forName("org.h2.Driver"); }
		catch (ClassNotFoundException e) { throw new IllegalStateException("JDBCドライバを読み込めませんでした"); }
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	
			// SELECT文の準備
			String sql = "SELECT ID, USER_ID, DATE, START_TIME, FINISH_TIME FROM SHIFT WHERE DATE BETWEEN ? AND ? AND USER_ID = ? ORDER BY DATE;"; // 日付順に取得
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
				Shift shift = new Shift(ID, userID, date, startTime, finishTime);
				shiftList.add(shift);
			}
	    }
		catch (SQLException e) {
	    	e.printStackTrace();
	      return null;
		}
		return shiftList;
	}
	
	// 新規アカウント作成
	public static boolean createShift(List<Shift> shiftList) {
		// JDBCドライバを読み込む
		try { Class.forName("org.h2.Driver"); }
		catch (ClassNotFoundException e) { throw new IllegalStateException("JDBCドライバを読み込めませんでした"); }
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			int size = shiftList.size();
			for (int i = 0; i < size; i++) {
				Shift shift = shiftList.get(i);
				// INSERT文の準備
				String sql = "INSERT INTO SHIFT (USER_ID, DATE, START_TIME, FINISH_TIME) VALUES (?, ?, ?, ?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// INSERT文中の「?」に使用する値を設定しSQLを完成
				pStmt.setInt(1, shift.getUserID());
				pStmt.setDate(2, shift.getDate());
				pStmt.setTime(3, shift.getStartTime());
				pStmt.setTime(4, shift.getFinishTime());
				
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
}
