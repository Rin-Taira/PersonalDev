<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文が確定されました</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<c:if test="${empty user}">
		<c:redirect url="/index" />
	</c:if>
	<div class="header">
		<h1 class="site_logo">
			<a href="menu.html">キッチントライ弁当注文</a>
		</h1>
		<div class="user">
			<c:if test="${not empty user}">
				<p class="user_name">${user.name}さん、こんにちは</p>
			</c:if>
			<form class="logout_form" action="logout" method="get">
				<button class="logout_btn" type="submit">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	
	<c:if test="${not empty msg}">
		<p class="error">${msg}</p>
	</c:if>
	<h1>ありがとうございます。注文が確定されました。</h1>
	<p>${order.menuName}</p>
	<div>サイズ:
		<c:if test="${order.bigFlag == 0}">小</c:if>
		<c:if test="${order.bigFlag == 1}">大</c:if>
	</div>
	<div>ご飯の種類:
		<c:if test="${order.brownFlag == 0}">白米</c:if>
		<c:if test="${order.brownFlag == 1}">玄米</c:if>
	</div>
	<div>ご飯の量:
		<c:if test="${order.riceIncFlag == 0}">普通盛り</c:if>
		<c:if test="${order.riceIncFlag == 1}">大盛り</c:if>
	</div>
	<p>価格: ${order.price}<p>
	
</body>
</html>
<script src="./js/commons.js"></script>