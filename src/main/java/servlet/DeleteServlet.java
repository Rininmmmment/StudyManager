package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインユーザー取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) response.sendRedirect("loginPage.jsp");
		else {
			// 選択シフト削除
			// リクエストパラメータ(削除選択したShiftインスタンスのID)取得
			String[] tmpArr = request.getParameterValues("deleteShiftID");
			int tmpSize = tmpArr.length;
			int[] deleteShiftIDs = new int[tmpSize];
			for (int i = 0; i < tmpSize; i++) {
				deleteShiftIDs[i] = Integer.parseInt(tmpArr[i]);
			}
			// シフトを削除
			ShiftDAO shiftDAO = new ShiftDAO();
			shiftDAO.deleteShift(deleteShiftIDs);

			// シフト取得
			// 現在の日付取得し、calenderにセット
			long miliseconds = System.currentTimeMillis();
			Date today = new Date(miliseconds);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(today);
			request.setAttribute("calendar", calendar);
			// 今月1ヶ月分のシフト取得
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			List<Shift> thisMonthShiftList = ShiftDAO.findOneMonthShift(loginAccount.getID(), Date.valueOf(year+"-"+month+"-1"), Date.valueOf(year+"-"+month+"-31"));
			// 来月1ヶ月分のシフト取得
			calendar.add(Calendar.MONTH, 1);
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			List<Shift> nextMonthShiftList = ShiftDAO.findOneMonthShift(loginAccount.getID(), Date.valueOf(year+"-"+month+"-1"), Date.valueOf(year+"-"+month+"-31"));
			// シフトをスコープに保存
			request.setAttribute("thisMonthShiftList", thisMonthShiftList);
			request.setAttribute("nextMonthShiftList", nextMonthShiftList);
			
			// シフトページへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("shiftSubmitPage.jsp");
			dispatcher.forward(request, response);
		}
	}

}
