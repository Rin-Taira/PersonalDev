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
	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="menu.html">キッチントライ弁当注文</a>
			</h1>
			<div class="user">
				<c:if test="${not empty user}">
					<p class="user_name">${user.name}さん、こんにちは</p>
				</c:if>
				<form class="logout_form" action="logout" method="post">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png">ログアウト
					</button>
				</form>
			</div>
		</div>

		<hr>

		<c:if test="${user.roleFlag == 0}">
			<div class="btn">
				<a class="basic_btn regist" href="insert">新規弁当登録</a>
			</div>
		</c:if>
		<p>
			<c:if test="${not empty msg}">
				<p class="user_name">${msg}</p>
			</c:if>
		</p>
		
		<div>
			<h1>${orderMsg}</h1>
			<h1>本日の注文担当</h1>
			<p>${todayManager.name}</p>
			<p>${todayManager.introduce}</p>
			<c:if test="${todayManager.paypayFlag == 1}">
				<p>paypay対応</p>
			</c:if>
			<a href="orderDetail">本日の注文を確認する</a>
		</div>
		<div>
			<h1>本日のあなたの注文</h1>
			<c:if test="${empty myOrderList}">
				<p>本日はまだ注文していませんよ。おわすれなく！</p>
			</c:if>
			<c:forEach var="myTodayOrder" items="${myOrderList}">
				<p>${myTodayOrder.menuName}</p>
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
			</c:forEach>
			<a href="deleteTodayOrder">本日の注文を取り消す</a>
		</div>
		<h1>${category.name}</h1>
		<c:forEach var="menuList" items="${menuListList}" varStatus="status">
			<h1>${categoryList[status.index].name}</h1>
			<c:forEach var="menu" items="${menuList}">
				<div>
					<div class="open" id="${menu.id}">
						<h2>${menu.menuName} レビュー件数: ${menu.reviewAmount}
							<c:if test="${menu.reviewAmount != 0}">評価: ${menu.reviewStarAmount / menu.reviewAmount}</c:if>
						</h2>
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
							<button type="submit">注文を確定する</button>
						</form>
						<span class="close" id="${menu.id}">△</span>
					</div>
					<c:if test="${menu.reviewAmount > 0}">
						<a href="review?id=${menu.id}">レビューを見る</a>
					</c:if>
					<a href="reviewPost?menuId=${menu.id}">レビューを投稿する</a>
				</div>
			</c:forEach>
		</c:forEach>
		
	</div>
	<footer></footer>

</body>
</html>
