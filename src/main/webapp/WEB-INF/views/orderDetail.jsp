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
				<c:if test="${not empty userName}">
					<p class="user_name">${userName}さん、こんにちは</p>
				</c:if>
				<form class="logout_form" action="logout" method="post">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png">ログアウト
					</button>
				</form>
			</div>
		</div>

		<hr>
		
		<div>
			<h1>${orderMsg}</h1>
			<h1>本日の注文担当</h1>
			<p>${todayManager.name}</p>
			<p>${todayManager.introduce}</p>
			<c:if test="${todayManager.paypayFlag == 1}">
				<p>paypay対応</p>
			</c:if>
		</div>
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
