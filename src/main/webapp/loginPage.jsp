<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サインイン / Study Manager</title>
</head>
<body>
	<form action="#" method="post">
		メールアドレス：
		<input type="text" name="email"><br>
		パスワード：
		<input type="text" name="pass"><br>
		<input class="form-btn" type="submit" value="サインイン">
	</form>
	
	<a href="${pageContext.request.contextPath}/resettingPage.jsp">忘れたら</a><br>
	
	<a href="${pageContext.request.contextPath}/registrationPage.jsp">アカウント作成</a>

	

</body>
</html>