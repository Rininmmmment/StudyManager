<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/shulack.css">

<div class="shulack">
	<div class="shulack-header">
		<h2>部内連絡用</h2>
		<p>12人のメンバー</p>
	</div>
	
	<div class="shulack-message">
	</div>
	
	<div class="shulack-input">
		<form action="Shulack" method="post">
			<textarea name="shulackMsg" placeholder="部内連絡用 へのメッセージ">
【出勤連絡】
【退勤連絡】
			</textarea>
			<button type="submit">
				<i class="fa-solid fa-play fa-2xl"></i>
			</button>
		</form>
	</div>

	<div class="shulack-bottom">
	</div>
</div>

