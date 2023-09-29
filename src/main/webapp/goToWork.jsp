<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,model.Shift,java.util.List, java.sql.Date, java.util.Calendar, model.FormChecker" %>
<%
	// リクエストスコープから取得
	String errorMessage = (String)request.getAttribute("errorMessageLog");
	Account loginAccount = (Account)session.getAttribute("loginAccount");
	List<Shift> thisMonthShiftList = (List<Shift>)request.getAttribute("thisMonthShiftList");
	
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
	<a href="#">出勤</a>
	<a href="#">シフト</a>
	<a href="#">Cafe</a>
	<a href="#">管理</a>
	<a href="#">パラメータ</a>
	<a href="#">ヘルプ</a>
	<a href="#">ログアウト</a>
	
	<p>来週もたくさん出勤してほしいのじゃ！</p>
	

	
</body>
</html>