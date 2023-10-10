<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%
	// リクエストスコープからエラーメッセージ取得
	String errorMessage = (String)request.getAttribute("errorMessageLog");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン / Study Manager</title>
</head>
<body>
	<header>
	
	</header>
	
	<div>
		<p>Study Manager</p>
		<h1>ログイン</h1>
	</div>
	
	<div>
		<form action="LoginServlet" method="post">
			<% if (errorMessage != null) { %>
				<p><%= errorMessage %></p>
			<% } %>
			<p>メールアドレス</p>
			<input type="email" name="email"><br>
			<p>パスワード</p>
			<input type="password" name="pass"><br>
			<input class="form-btn" type="submit" value="ログイン">
			<p><a href="resettingPage.jsp">パスワードを忘れた</a></p>
			<p><a href="registrationPage.jsp">アカウント作成</a></p>
		</form>
	</div>
	
		

</body>
</html>