<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/timecard.css">

<div class="timecard">
	<div class="timecard-clock">
		<div class="clock-centering">
			<h3><%= calendar.get(Calendar.YEAR) %>年<%= calendar.get(Calendar.MONTH)+1 %>月<%= calendar.get(Calendar.DATE) %>日<%= calendarManager.getDayOfWeek(calendar) %></h3>
			<h2><%= calendar.getTime().getHours() %>：<%= calendar.getTime().getMinutes() %></h1>
		</div>
	</div>
	
	<div class="timecard-right">
		<div class="timecard-name">
			<p>社員No.<%= loginAccount.getID() %></p>
			<h2><%= loginAccount.getName() %></h2>
		</div>
		<div class="timecard-button">
			<a class="timecard-button go" href="Working">出勤</a>
			<a class="timecard-button leave" tabindex="-1">退勤</a>
		</div>
	</div>
</div>