<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文ページ</title>
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
	<p>${menu.menuName}</p>
	<p>基本価格: ${menu.price}</p>
	<p>${menu.introduce}</p>
	<form action="orderAdd">
		<c:if test="${menu.categoryId == 2}">
			<input type="checkbox" name="big">大にする
		</c:if>
		<c:if test="${menu.categoryId == 2 || menu.categoryId == 3}">
			<input type="checkbox" name="brown">玄米にする
			<input type="checkbox" name="rice_big">ごはんを大盛にする
		</c:if>
		<input type="submit">
	</form>
	<h1>レビュー</h1>
	<p>レビュー: ${menu.reviewAmount}件</p>
	<c:if test="${menu.reviewAmount > 0}">
		<p>評価: ${menu.reviewStarAmount / menu.reviewAmount}</p>
	</c:if>
	<c:if test="${not empty reviewList}">
		<c:forEach var="review" items="${reviewList}">
			<p>${review.userName}</p>
			<p>${review.star}</p>
			<p>${review.review}</p>
			<p>${review.reviewDate}</p>
		</c:forEach>
	</c:if>
	
</body>
</html>
<script src="./js/commons.js"></script>