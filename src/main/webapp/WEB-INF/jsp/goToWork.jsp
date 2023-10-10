<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,model.Shift,java.util.List, java.sql.Date, java.util.Calendar, model.FormChecker, model.CalendarManager, model.BossMessage" %>
<%
	// リクエストスコープから取得
	String errorMessage = (String)request.getAttribute("errorMessageLog");
	Account loginAccount = (Account)session.getAttribute("loginAccount");
	List<Shift> todayShiftList = (List<Shift>)request.getAttribute("todayShiftList");
	
	CalendarManager calendarManager = new CalendarManager();
	BossMessage bossMessage = new BossMessage();
	
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
<title>出勤</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/overallLayout.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/reset.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/goToWork.css">
<script src="https://kit.fontawesome.com/6c7cc9867d.js" crossorigin="anonymous"></script>
</head>

<body>
	<%@include file = "header.jsp" %>
	
	<div class="nav-main-container">
		<%@include file = "menuBar.jsp" %>
		
		<main>
			<!-- タイトル -->
			<div class="title-container">
				<h1 class="title">出勤</h1>
			</div>
			
			<!-- 猫のセリフ -->
			<div class="cat-container">
				<p class="cat"><%= bossMessage.generateMessage("before") %></p>
			</div>
			
			<div class="gotowork-container">
				<div class="left-container">
					<div class="data-container">
						<div class="shift-container">
							<p class="data">今日のシフト</p>
							<% for (int i = 0; i < todayShiftList.size(); i++) { %>
							<p><%= todayShiftList.get(i).getStartTime() %>〜<%= todayShiftList.get(i).getFinishTime() %></p>
							<% } %>
						</div>

						<div class="parameter-container">
							<p class="data">パラメータ</p>
						</div>
					</div>
					
					<div class="left-container">
						<p class="timecard">勤怠システム <i class="fa-solid fa-circle-question"></i></p>
						<%@include file = "timecard.jsp" %>
					</div>
				</div>
				
				<div class="shulack-container">
					<p class="shulack">Shulack <i class="fa-solid fa-circle-question"></i></p>
					<%@include file = "shulack.jsp" %>
				</div>
				
			</div>
		</main>
	</div>

</body>
</html>