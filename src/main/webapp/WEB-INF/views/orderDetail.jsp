<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文確認</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>

<body>
	<c:if test="${empty user}">
		<c:redirect url="/index" />
	</c:if>
	<header class="flex">
		<h1>LunchOrder</h1>
		<ul class="flex">
			<li><p class="user_name">${userName}さん、こんにちは</p></li>
			<li>
				<a href="logout">ログアウト
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-closed" viewBox="0 0 16 16">
						<path d="M3 2a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3V2zm1 13h8V2H4v13z"/>
						<path d="M9 9a1 1 0 1 0 2 0 1 1 0 0 0-2 0z"/>
					</svg>
				</a>
			</li>
		</ul>
	</header>
	
	<main class="flex">
		<div class="left">
			<div>
				<h1>${orderMsg}</h1>
				<p class="little_title">本日の注文担当</p>
				<div class="balloon2">
					<c:if test="${orderFlag == 0}">
  						<p>注文済みだよ</p>
  					</c:if>
  					<c:if test="${orderFlag == 1}">
						<p>まだ注文してないよ</p>
					</c:if>
				</div>
				<p>
					<img src="../images/cat.png" class="cat_icon">
					${todayManager.name}
					<c:if test="${todayManager.paypayFlag == 1}">
						<img src="../images/paypay.jpg" class="paypay_icon">
					</c:if>
				</p>
				<div class="balloon2-top">
					<p>${todayManager.introduce}</p>
				</div>
				<a href="orderDetail">本日の注文を確認する</a>
			</div>
		</div>
		<div class="center">
			<c:if test="${orderFlag == 0}">
				<c:if test="${todayManager.name != userName}">
					<p>あなたは本日の注文者ではありません。それでも注文した場合のみ下記の操作をしてください。</p>
				</c:if>
				<a href="updateManager">注文済みにする。</a>
			</c:if>
			<c:if test="${orderFlag == 1}">
				<p>本日は注文完了しています。</p>
			</c:if>
			<c:if test="${empty orderList}">
				<h1>本日はまだ注文がありません。</h1>
			</c:if>
			<c:forEach var="order" items="${orderList}">
				<div>
					<p>${order.userName}</p>
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
					<p>価格: ${order.price}</p>
				</div>
			</c:forEach>
		</div>
		<div class="right">
			<p class="little_title">本日のあなたの注文</p>
			<c:if test="${empty myOrderList}">
				<p>あなたはまだ弁当を記入していませんよ。おわすれなく！</p>
			</c:if>
			<c:forEach var="myTodayOrder" items="${myOrderList}">
				<div class="menu">
					<p class="little_title">${myTodayOrder.menuName}</p>
					<div>サイズ:
						<c:if test="${myTodayOrder.bigFlag == 0}">小</c:if>
						<c:if test="${myTodayOrder.bigFlag == 1}">大</c:if>
					</div>
					<div>ご飯の種類:
						<c:if test="${myTodayOrder.brownFlag == 0}">白米</c:if>
						<c:if test="${myTodayOrder.brownFlag == 1}">玄米</c:if>
					</div>
					<div>ご飯の量:
						<c:if test="${myTodayOrder.riceIncFlag == 0}">普通盛り</c:if>
						<c:if test="${myTodayOrder.riceIncFlag == 1}">大盛り</c:if>
					</div>
					<p>価格: ${myTodayOrder.price}</p>
				</div>
			</c:forEach>
			<c:if test="${orderFlag == 0 && not empty myOrderList}">
				<a href="deleteTodayOrder">本日の注文を取り消す</a>
			</c:if>
		</div>
	</main>
		
	<footer>
		<p>©2022  RinTaira. All Rights Reserved</p>
	</footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
