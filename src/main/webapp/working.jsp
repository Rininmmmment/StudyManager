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
</head>
<body>
	<%@include file = "menuBar.jsp" %>
	
	<p><%= bossMessage.generateMessage("working") %></p>
	
	<p>今日のシフト</p>
	<% for (int i = 0; i < todayShiftList.size(); i++) { %>
		<p><%= todayShiftList.get(i).getStartTime() %>〜<%= todayShiftList.get(i).getFinishTime() %></p>
	<% } %>
	
	<p>社員No.<%= loginAccount.getID() %></p>
	<h2><%= loginAccount.getName() %></h2>
	<h3><%= calendar.get(Calendar.YEAR) %>年 <%= calendar.get(Calendar.MONTH)+1 %>月 <%= calendar.get(Calendar.DATE) %>日 <%= calendarManager.getDayOfWeek(calendar) %></h3>
	<h1><%= calendar.getTime().getHours() %>：<%= calendar.getTime().getMinutes() %></h1>
	
	<a tabindex="-1">出勤</a>
	<a href="Leaved">退勤</a>

</body>
</html>