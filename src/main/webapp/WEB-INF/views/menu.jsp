<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<p class="user_name">${userName}さん、こんにちは</p>
		<a href="logout">ログアウト</a>
	</header>
	
	<main class="flex">
		<div class="left">
			<div>
				<h1>${orderMsg}</h1>
				<p class="little_title">本日の注文担当</p>
				<p>
					<img src="../images/cat.png" class="cat_icon">
					${todayManager.name}
					<c:if test="${todayManager.paypayFlag == 1}">
						<img src="../images/paypay.jpg" class="paypay_icon">
					</c:if>
				</p>
				<p>${todayManager.introduce}</p>
				<a href="orderDetail">本日の注文を確認する</a>
				<c:if test="${orderFlag == 0}">
					<h1>未注文</h1>
				</c:if>
				<c:if test="${orderFlag == 1}">
					<h1>注文済</h1>
				</c:if>
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
							<p class="little_title">${menu.menuName} レビュー件数: ${menu.reviewAmount}
								<c:if test="${menu.reviewAmount != 0}">評価: ${menu.reviewStarAmount / menu.reviewAmount}</c:if>
							</p>
							<p>基本価格: ${menu.price}</p>
							<p>${menu.description}</p>
						</div>
						<div class="passive ${menu.id}">
							<form action="orderCommit" method="get">
								<input type="hidden" name="id" value="${menu.id}">
								<div>サイズ
								<input type="radio" name="big" value="0" checked>小
									<c:if test="${menu.categoryId == 1}">
										<input type="radio" name="big" value="1">大
									</c:if>
								</div>
								<div>お米の種類
									<input type="radio" name="brown" value="0" checked>白米
									<c:if test="${menu.categoryId == 1 || menu.categoryId == 2}">
										<input type="radio" name="brown" value="1">玄米
									</c:if>
								</div>
								<div>ご飯の量
									<input type="radio" name="rice_big" value="0" checked>普通盛り
									<c:if test="${menu.categoryId == 1 || menu.categoryId == 2}">
										<input type="radio" name="rice_big" value="1">大盛り
									</c:if>
								</div>
								<c:if test="${orderFlag == 0}">
									<div id="flex">
										<button class="btn btn-outline-primary" type="submit">注文を追加する</button>
										<svg id="${menu.id}" class="close" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-short" viewBox="0 0 16 16">
	  										<path fill-rule="evenodd" d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"/>
										</svg>
									</div>
								</c:if>
								<c:if test="${orderFlag == 1}">
									<p>本日の注文受付は終了しました。</p>
									<svg id="${menu.id}" class="close" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-short" viewBox="0 0 16 16">
  										<path fill-rule="evenodd" d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"/>
									</svg>
								</c:if>
							</form>
						</div>
						<c:if test="${menu.reviewAmount > 0}">
							<a href="review?id=${menu.id}">レビューを見る</a>
						</c:if>
						<a href="reviewPost?menuId=${menu.id}">レビューを投稿する</a>
					</div>
				</c:forEach>
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
