package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShiftDAO;
import model.Account;
import model.Shift;

@WebServlet("/BeforeWork")
public class BeforeWork extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// ログインユーザー取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) response.sendRedirect("loginPage.jsp");
		// ログインできた場合
		else {
			// 今日のシフト取得
			List<Shift> todayShiftList = ShiftDAO.findTodayShift(loginAccount.getID());
			// スコープに保存
			request.setAttribute("todayShiftList", todayShiftList);
		}
			
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/goToWork.jsp");
		dispatcher.forward(request, response);
	}
}
