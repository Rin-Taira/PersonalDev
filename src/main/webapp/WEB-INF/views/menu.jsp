<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>

$(function() {
	$(".open").on("click", function() {
		var id = $(this).attr("id");
		$('.' + id).slideDown("normal");
	});
	$(".close").on("click", function() {
		var id2 = $(this).attr("id");
		$('.' + id2).slideUp("normal");
	});
});
</script>
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
				<p class="little_title">本日の注文担当
					<img src="../images/bell.png" class="title-icon">
				</p>
				<div class="balloon2">
					<c:if test="${orderFlag == 1}">
  						<p class="warn">注文済みだよ</p>
  					</c:if>
  					<c:if test="${orderFlag == 0}">
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
				<a href="orderDetail">
					<button class="btn btn-outline-primary" type="button">みんなの注文を確認する</button>
				</a>
			</div>
		</div>
		
		<div class="center">
			<c:if test="${not empty msg}">
				<p class="user_name">${msg}</p>
			</c:if>
			<p class="little_title">${category.name}</p>
			<c:forEach var="menuList" items="${menuListList}" varStatus="status">
				<p class="category_name">${categoryList[status.index].name}</p>
				<c:forEach var="menu" items="${menuList}">
					<div class="menu">
						<div class="open" id="${menu.id}">
							<div class="flex">
								<div>
									<p class="little_title">${menu.menuName}
										<c:if test="${menu.reviewAmount != 0}"> 平均評価:
											<fmt:formatNumber value="${menu.reviewStarAmount / menu.reviewAmount}" maxFractionDigits="1"/>
										</c:if>
									</p>
									<p>${menu.description}</p>
									<p>￥${menu.price}</p>
									<c:if test="${menu.reviewAmount > 0}">
										<a href="review?id=${menu.id}">レビューを見る(${menu.reviewAmount})</a>
									</c:if>
									<br>
									<a href="reviewPost?menuId=${menu.id}">レビューを投稿する</a>
								</div>
								<img src="../images/menu.png" class="menu_img">
							</div>
							<div class="align_center ${menu.id}show">
								<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-caret-down" viewBox="0 0 16 16">
	  								<path d="M3.204 5h9.592L8 10.481 3.204 5zm-.753.659 4.796 5.48a1 1 0 0 0 1.506 0l4.796-5.48c.566-.647.106-1.659-.753-1.659H3.204a1 1 0 0 0-.753 1.659z"/>
								</svg>
							</div>
						</div>
						<div class="passive ${menu.id}">
							<div class="align_center close" id="${menu.id}">
								<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-caret-up" viewBox="0 0 16 16">
  									<path d="M3.204 11h9.592L8 5.519 3.204 11zm-.753-.659 4.796-5.48a1 1 0 0 1 1.506 0l4.796 5.48c.566.647.106 1.659-.753 1.659H3.204a1 1 0 0 1-.753-1.659z"/>
								</svg>
							</div>
							<form action="orderCommit" method="get">
								<input type="hidden" name="id" value="${menu.id}">
								<div class="flex-start">
									<c:if test="${orderFlag == 0}">
										<button class="btn btn-outline-primary" type="submit">注文に追加</button>
									</c:if>
									<c:if test="${orderFlag == 1}">
										<button class="btn btn-outline-primary" type="submit" disabled>受付終了</button>
									</c:if>
									<div>サイズ<br>
										<input type="radio" name="big" value="0" checked>小<br>
										<c:if test="${menu.categoryId == 1}">
											<input type="radio" name="big" value="1">大(+￥120)
										</c:if>
									</div>
									<div>お米の種類<br>
										<input type="radio" name="brown" value="0" checked>白米<br>
										<c:if test="${menu.categoryId == 1 || menu.categoryId == 2}">
											<input type="radio" name="brown" value="1">玄米(+￥0)
										</c:if>
									</div>
									<div>ご飯の量<br>
										<input type="radio" name="rice_big" value="0" checked>普通盛り<br>
										<c:if test="${menu.categoryId == 1 || menu.categoryId == 2}">
											<input type="radio" name="rice_big" value="1">大盛り(+￥50)
										</c:if>
									</div>
								</div>
							</form>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
		
		<div class="right">
			<p class="little_title">あなたのカート
				<img src="../images/cart.png" class="title-icon">
			</p>
			<c:if test="${empty myOrderList}">
				<p class="warn">あなたのカートはまだ空ですよ。本日の注文をおわすれなく！</p>
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
				<a href="deleteTodayOrder">
					<button class="btn btn-outline-primary" type="button">注文を取り消す</button>
				</a>
			</c:if>
		</div>
	</main>
	
	<footer>
		<p>©2022  RinTaira. All Rights Reserved</p>
	</footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
