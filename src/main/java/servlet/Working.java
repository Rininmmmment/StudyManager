package servlet;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShiftDAO;
import model.Account;
import model.Shift;

public class Working extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// ログインユーザー取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) response.sendRedirect("loginPage.jsp");
		// ログインできた場合
		else {
			// 今日のシフトを取得し、リクエストスコープに保存
			List<Shift> todayShiftList = ShiftDAO.findTodayShift(loginAccount.getID());
			request.setAttribute("todayShiftList", todayShiftList);
			// 出勤時刻をアプリケーションスコープに保存
			Date now = new Date();
			Time startTime = Time.valueOf(now.getHours() + ":" + now.getMinutes() + ":00");
			ServletContext application = this.getServletContext();
			application.setAttribute("startTime", startTime);
		}
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("working.jsp");
		dispatcher.forward(request, response);
	}
}
