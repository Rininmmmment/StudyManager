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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/overallLayout.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/shiftSubmitPage.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reset.css">
</head>

<body>

	<%@include file = "header.jsp" %>
	
	<div class="nav-main-container">
		<%@include file = "menuBar.jsp" %>
		
		<main>
			<!-- タイトル -->
			<div class="title-container">
				<h1 class="title">シフト</h1>
			</div>
			
			<!-- 猫のセリフ -->
			<div class="cat-container">
				<p class="cat">来週もたくさん出勤してほしいのじゃ！</p>
			</div>
			
			<!-- シフト部分 -->
			<div class="shift-container">
				<!-- シフト削除 -->
				<div class="shift-delete-container">
					<div class="subtitle-container">
						<h2 class="subtitle">シフト確認・削除</h2>
					</div>
					<form action="DeleteServlet" method="post">
						<div class="month-container">
							<p class="month"><%= calendar.get(Calendar.MONTH) + 1 %>月</p>
						</div>
						
						<table class="shift delete">
							<tr class="shift-head"><th></th><th>日付</th><th>開始時刻</th><th>終了時刻</th></tr>
							<% for (int i = 0; i < thisMonthShiftList.size(); i++) { %>
								<% Shift shift = thisMonthShiftList.get(i); %>
								<tr>
									<td>
										<% FormChecker formChecker = new FormChecker(); %>
										<% if (formChecker.findDateError(shift.getDate()).length() != 0) { %>
											<input class="checkbox" type="checkbox" name="deleteShiftID" value=<%= shift.getID() %> disabled>
										<% } else { %>
											<input class="checkbox" type="checkbox" name="deleteShiftID" value=<%= shift.getID() %>>
										<% } %>
						 			</td>
									<td><%= shift.getDate() %></td>
									<td><%= shift.getStartTime() %></td>
									<td><%= shift.getFinishTime() %></td>
								</tr>
							<% } %>
						</table>
						<hr>
						<div class="working-time-container">
							<p class="working-time sum">勤務時間合計</p>
							<p class="working-time wage">推定給与</p>
						</div>
						
						<div class="month-container">
							<p class="month"><%= calendar.get(Calendar.MONTH) + 2 %>月</p>
						</div>
						
						<table class="shift delete">
							<tr class="shift-head"><th></th><th>日付</th><th>開始時刻</th><th>終了時刻</th></tr>
							<% for (int i = 0; i < nextMonthShiftList.size(); i++) { %>
								<% Shift shift = nextMonthShiftList.get(i); %>
									<tr>
										<td>
											<input class="checkbox" type="checkbox" name="deleteShiftID" value=<%= shift.getID() %>>
							 			</td>
										<td><%= shift.getDate() %></td>
										<td><%= shift.getStartTime() %></td>
										<td><%= shift.getFinishTime() %></td>
									</tr>
							<% } %>
						</table>
						<hr>
						<div class="working-time-container">
							<p class="working-time sum">勤務時間合計</p>
							<p class="working-time wage">推定給与</p>
						</div>
						
						<button type="submit" class="shift delete">選択した項目を削除</button>
					</form>
					
				</div>
				
				<!-- シフト追加 -->
				<div class="shift-create-container">
					<div class="subtitle-container">
						<h2 class="subtitle">シフト追加</h2>
					</div>
					<div class="month-container">
					</div>
					
					<form action="ShiftManagement" method="post">
						<% if (errorMessage != null) { %>
						<p><%= errorMessage %></p>
						<% } %>
						<table class="shift create">
							<tr class="shift-head"><th>日付</th><th>開始時刻</th><th>終了時刻</th></tr>
							<% for (int i = 0; i < 10; i++) { %>
								<tr>
									<td><input class="shift" type="date" name="date_<%= i %>"></td>
									<td><input class="shift" type="time" name="start_<%= i %>"></td>
									<td><input class="shift" type="time" name="finish_<%= i %>"></td>
								</tr>
							<% } %>
						</table>
						<button type="submit"  class="shift create">登録</button>
				</form>
				</div>
			</div>
			
		</main>
	</div>
	
</body>
</html>