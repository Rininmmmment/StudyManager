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

import dao.AccountsDAO;
import model.Account;
import model.AccountManager;
import model.FormChecker;
import model.LoginInfo;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
	    
		// 入力値チェック(正しいメールアドレス形式・PWがnullでない)
		FormChecker formChecker = new FormChecker();
		String errorMessage = formChecker.findEmailError(email) + formChecker.findPassError(pass);
		System.out.println(errorMessage);
		// 入力値にエラーがある場合
		if (errorMessage.length() != 0) {
			request.setAttribute("errorMessageLog", errorMessage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
			dispatcher.forward(request, response);
		}
		// 入力値にエラーがない場合
		else {
			AccountManager accountManager = new AccountManager();
			LoginInfo loginInfo = new LoginInfo(email, pass);
			AccountsDAO accountsDAO = new AccountsDAO();
			// ログイン処理
			List<Account> accountsList = accountsDAO.findAll();
			if (accountManager.canLogin(loginInfo, accountsList)) { // ログイン可能の場合
				Account loginAccount = AccountManager.findAccount(loginInfo, accountsList); //ログインアカウント
		        // ユーザー情報をセッションスコープに保存
		        HttpSession session = request.getSession();
		        session.setAttribute("loginAccount", loginAccount);
		        
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOKPage.jsp");
				dispatcher.forward(request, response);
			}
			else { // ログイン不可の場合
				errorMessage = "メールアドレスまたはパスワードが間違っています";
				request.setAttribute("errorMessageLog", errorMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}
}
