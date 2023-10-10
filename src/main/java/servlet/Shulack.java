package servlet;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.ShulackChecker;

@WebServlet("/Shulack")
public class Shulack extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータが出勤か退勤かチェック
		request.setCharacterEncoding("UTF-8");
		String shulackMsg = request.getParameter("shulackMsg");
		
		// ログインユーザー取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		if (loginAccount == null) response.sendRedirect("loginPage.jsp");
		// ログインできた場合
		else {
			// 勤務中ならアプリケーションスコープに登録する。勤務中でなければ出勤ページへ飛ばす。
			try {
				// 送信メッセージ保存
				ServletContext application = this.getServletContext();
				Time startTime = (Time)application.getAttribute("startTime");
				application.setAttribute(ShulackChecker.checkShulackMsg(shulackMsg), shulackMsg);
				System.out.println(ShulackChecker.checkShulackMsg(shulackMsg) + shulackMsg);
				// 返信を生成し保存
			}
			catch (Exception e) { // 出勤ページへ飛ばす
				response.sendRedirect("BeforeWork");
			}
		}
		
		// リダイレクト
		response.sendRedirect("Working");
	}
}
