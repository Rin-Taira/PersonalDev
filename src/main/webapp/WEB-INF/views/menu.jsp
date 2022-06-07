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
		$('.' + id).show("normal");
	});
	$(".close").on("click", function() {
		var id2 = $(this).attr("id");
		$('.' + id2).hide("normal");
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

		<c:if test="${user.role_flag == 0}">
			<div class="btn">
				<a class="basic_btn regist" href="insert">新規弁当登録</a>
			</div>
		</c:if>
		<p>
			<c:if test="${not empty msg}">
				<p class="user_name">${msg}</p>
			</c:if>
		</p>
		<form method="get" action="search" class="search_container">
			<input type="text" name="keyword" size="25" placeholder="キーワード検索">
			<select name="star">
				<option value="5">★5以上</option>
				<option value="4">★4以上</option>
				<option value="3">★3以上</option>
				<option value="2">★2以上</option>
				<option value="1">★1以上</option>
			</select>
			<input type="submit" value="&#xf002">
		</form>
		
		<h1>${category.name}</h1>
		<c:forEach var="menuList" items="${menuListList}" varStatus="status">
			<h1>${categoryList[status.index].name}</h1>
			<c:forEach var="menu" items="${menuList}">
				<div>
					<h2>${menu.menuName}</h2>
					<p>${menu.price}</p>
					<p>${menu.introduce}</p>
					<c:if test="${menu.reviewAmount > 0}">
						<p>${menu.reviewAmount}</p>
						<p>${menu.reviewStarAmount / menu.reviewAmount}</p>
						<a href="review">レビューを見る</a>
					</c:if>
					<button class="open" id="${menu.menuId}">注文する</button>
					<button class="close" id="${menu.menuId}">閉じる</button>
					<div class="passive ${menu.menuId}">
						<c:if test="${menu.categoryId == 2}">
							<input type="checkbox" name="big">大にする
						</c:if>
						<c:if test="${menu.categoryId == 2 || menu.categoryId == 3}">
							<input type="checkbox" name="brown">玄米にする
							<input type="checkbox" name="rice_big">ごはんを大盛にする
						</c:if>
					</div>
				</div>
			</c:forEach>
		</c:forEach>
			
		
		<%--
		<table>
			<div class="caption">
				<p>検索結果: ${fn:length(productList)}件</p>
			</div>
			
			<thead>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>単価</th>
					<th>カテゴリ</th>
					<th>詳細</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${productList}">
					<form:form action="detail" modelAttribute="product" method="get">
						<form:input type="hidden" path="productId" value="${product.productId}"/>
						<form:input type="hidden" path="categoryId" value="${product.categoryId}"/>
						<form:input type="hidden" path="categoryName" value="${product.categoryName}"/>
						<form:input type="hidden" path="name" value="${product.name}"/>
						<form:input type="hidden" path="price" value="${product.price}"/>
						<form:input type="hidden" path="description" value="${product.description}"/>
						<form:input type="hidden" path="createdDate" value="${product.createdDate}"/>
						<form:input type="hidden" path="updatedDate" value="${product.updatedDate}"/>
						<tr>
							<td>${fn:escapeXml(product.productId)}</td>
							<td>${fn:escapeXml(product.name)}</td>
							<td>${fn:escapeXml(product.price)}</td>
							<td>${fn:escapeXml(product.categoryName)}</td>
							<td><button type="submit" class="detail_btn" name="id" value="${product.productId}">詳細</button></td>
						</tr>
						</form:form>
				</c:forEach>
			</tbody>
		</table>
		--%>
		
	</div>
	<footer></footer>

</body>
</html>
