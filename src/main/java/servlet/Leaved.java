package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WorkingHistoryDAO;
import model.Account;
import model.WorkingHistory;

public class Leaved extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// ログインユーザー取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) response.sendRedirect("loginPage.jsp");
		// ログインできた場合
		else {
			// 勤務開始時刻を取得し勤務履歴を登録する。できなければ出勤ページへ飛ばす。
			ServletContext application = this.getServletContext();
			try { // 勤務履歴登録
				Time startTime = (Time)application.getAttribute("startTime");
				
				long miliseconds = System.currentTimeMillis();
				Date now = new Date(miliseconds);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(now);
				
				Date today = Date.valueOf(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE));
				Time finishTime = Time.valueOf(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":00");
				
				WorkingHistory workingHistory = new WorkingHistory(-1, loginAccount.getID(), today, startTime, finishTime);
				List<WorkingHistory> workingHistoryList = new ArrayList<WorkingHistory>(Arrays.asList(workingHistory));
				WorkingHistoryDAO workingHistoryDAO = new WorkingHistoryDAO();
				WorkingHistoryDAO.createWorkingHistory(workingHistoryList);
				System.out.println("退勤登録できたよ〜");
			}
			catch (Exception e) { // 出勤ページへ飛ばす
				response.sendRedirect("BeforeWork");
			}
		}
		
		// リダイレクト
		response.sendRedirect("BeforeWork");
	}
}
