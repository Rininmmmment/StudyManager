package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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
import model.FormChecker;
import model.Shift;

@WebServlet("/ShiftManagement")
public class ShiftManegement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインユーザー取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) response.sendRedirect("loginPage.jsp");
		
		// 今月1ヶ月分のシフト取得
		List<Shift> thisMonthShiftList = ShiftDAO.findOneMonthShift(loginAccount.getID(), Date.valueOf("2023-9-1"), Date.valueOf("2023-09-30"));
		List<Shift> nextMonthShiftList = ShiftDAO.findOneMonthShift(loginAccount.getID(), Date.valueOf("2023-10-1"), Date.valueOf("2023-10-31"));
		
		// スコープに保存
		request.setAttribute("thisMonthShiftList", thisMonthShiftList);
		request.setAttribute("nextMonthShiftList", nextMonthShiftList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("shiftSubmitPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインユーザー取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) response.sendRedirect("loginPage.jsp");
		
		List<Shift> shiftList = new ArrayList<Shift>();
		for (int i = 0; i < 10; i++) {
			String errorMessage = "";
			
			// リクエストパラメータの取得
			String stringDate = request.getParameter("date_" + String.valueOf(i));
			String stringStart = request.getParameter("start_" + String.valueOf(i));
			String stringFinish = request.getParameter("finish_" + String.valueOf(i));
			
			// nullでなければ型変換
			if (stringDate.length() == 0 & stringStart.length() == 0 & stringFinish.length() == 0) continue;
			if (stringDate.length() == 0 | stringStart.length() == 0 | stringFinish.length() == 0) {
				errorMessage = "日時を正しく入力してください";
				break;
			}
			Date date = Date.valueOf(stringDate);
			Time start = Time.valueOf(stringStart + ":00");
			Time finish = Time.valueOf(stringFinish + ":00");
			
			// 入力値チェック(日付が現在以降、終了時刻が開始時刻より後)
			FormChecker formChecker = new FormChecker();
			errorMessage += formChecker.findDateError(date) + formChecker.findTimeError(start, finish);
			System.out.println(errorMessage);
			
			// 入力値にエラーがある場合
			if (errorMessage.length() != 0) {
				// エラーメッセージ表示
				request.setAttribute("errorMessageLog", errorMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("shiftSubmitPage.jsp");
				dispatcher.forward(request, response);
			}
			// 入力値にエラーがない場合
			else {
				// リストにシフトを追加
				shiftList.add(new Shift(-1, loginAccount.getID(), date, start, finish));
			}
		}
		// DBにシフト登録
		ShiftDAO.createShift(shiftList);
		System.out.println("完了");
		
		// 今月1ヶ月分のシフト取得
		List<Shift> thisMonthShiftList = ShiftDAO.findOneMonthShift(loginAccount.getID(), Date.valueOf("2023-9-1"), Date.valueOf("2023-09-30"));
		List<Shift> nextMonthShiftList = ShiftDAO.findOneMonthShift(loginAccount.getID(), Date.valueOf("2023-10-1"), Date.valueOf("2023-10-31"));
		
		// スコープに保存
		request.setAttribute("thisMonthShiftList", thisMonthShiftList);
		request.setAttribute("nextMonthShiftList", nextMonthShiftList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("shiftSubmitPage.jsp");
		dispatcher.forward(request, response);
	}

}
