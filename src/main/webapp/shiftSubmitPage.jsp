<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,model.Shift,java.util.List" %>
<%
	// リクエストスコープから取得
	String errorMessage = (String)request.getAttribute("errorMessageLog");
	Account loginAccount = (Account)session.getAttribute("loginAccount");
	List<Shift> thisMonthShiftList = (List<Shift>)request.getAttribute("thisMonthShiftList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>シフト提出</title>
</head>
<body>
	<a href="#">出勤</a>
	<a href="#">シフト</a>
	<a href="#">Cafe</a>
	<a href="#">管理</a>
	<a href="#">パラメータ</a>
	<a href="#">ヘルプ</a>
	
	<p>来週もたくさん出勤してほしいのじゃ！</p>
	
	<p><%= loginAccount.getName() %>のカレンダー</p>
	<table>
	<tr><th>日付</th><th>開始時刻</th><th>終了時刻</th></tr>
	<% for (int i = 0; i < thisMonthShiftList.size(); i++) { %>
		<% Shift shift = thisMonthShiftList.get(i); %>
		<tr>
			<td><%= shift.getDate() %></td>
			<td><%= shift.getStartTime() %></td>
			<td><%= shift.getFinishTime() %></td>
		</tr>
	<% } %>
	</table>
	
	
	<p>給与計算</p>
	
	<form action="ShiftManagement" method="post">
		<% if (errorMessage != null) { %>
		<p><%= errorMessage %></p>
		<% } %>
		<table>
			<tr><th>日付</th><th>開始時刻</th><th>終了時刻</th></tr>
			<% for (int i = 0; i < 10; i++) { %>
				<tr>
					<td><input type="date" name="date_<%= i %>"></td>
					<td><input type="time" name="start_<%= i %>"></td>
					<td><input type="time" name="finish_<%= i %>"></td>
				</tr>
			<% } %>
		</table>
		<input class="form-btn" type="submit" value="確定">
	</form>

</body>
</html>