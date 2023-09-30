<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,model.Shift,java.util.List, java.sql.Date, java.util.Calendar, model.FormChecker" %>
<%
	// リクエストスコープから取得
	String errorMessage = (String)request.getAttribute("errorMessageLog");
	Account loginAccount = (Account)session.getAttribute("loginAccount");
	List<Shift> thisMonthShiftList = (List<Shift>)request.getAttribute("thisMonthShiftList");
	List<Shift> nextMonthShiftList = (List<Shift>)request.getAttribute("nextMonthShiftList");
	
	// 今日の日時をcalendarにセット
	long miliseconds = System.currentTimeMillis();
	Date today = new Date(miliseconds);
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(today);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>シフト提出</title>
</head>
<body>
	<%@include file = "menuBar.jsp" %>
	
	<p>来週もたくさん出勤してほしいのじゃ！</p>
	

	
	<form action="DeleteServlet" method="post">
		<p><%= loginAccount.getName() %>のカレンダー</p>
		<p><%= calendar.get(Calendar.MONTH) + 1 %>月分</p>
		
		<table>
		<tr><th></th><th>日付</th><th>開始時刻</th><th>終了時刻</th></tr>
		<% for (int i = 0; i < thisMonthShiftList.size(); i++) { %>
			<% Shift shift = thisMonthShiftList.get(i); %>
			<tr>
				<td>
					<% FormChecker formChecker = new FormChecker(); %>
					<% if (formChecker.findDateError(shift.getDate()).length() != 0) { %>
						<input type="checkbox" name="deleteShiftID" value=<%= shift.getID() %> disabled>
					<% } else { %>
						<input type="checkbox" name="deleteShiftID" value=<%= shift.getID() %>>
					<% } %>
	 			</td>
				<td><%= shift.getDate() %></td>
				<td><%= shift.getStartTime() %></td>
				<td><%= shift.getFinishTime() %></td>
			</tr>
		<% } %>
		</table>

		<p><%= calendar.get(Calendar.MONTH) + 2 %>月分</p>
		
		<table>
		<tr><th></th><th>日付</th><th>開始時刻</th><th>終了時刻</th></tr>
		<% for (int i = 0; i < nextMonthShiftList.size(); i++) { %>
			<% Shift shift = nextMonthShiftList.get(i); %>
				<tr>
					<td>
						<input type="checkbox" name="deleteShiftID" value=<%= shift.getID() %>>
		 			</td>
					<td><%= shift.getDate() %></td>
					<td><%= shift.getStartTime() %></td>
					<td><%= shift.getFinishTime() %></td>
				</tr>
		<% } %>
		</table>
		
		<button type="submit">削除</button>
	</form>
	
	
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