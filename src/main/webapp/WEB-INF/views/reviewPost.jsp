<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body class="login_body">
	<div class="header">
		<h1 class="site_logo">キッチントライ弁当注文</h1>
	</div>

	<div class="login_form">
		<div class="error">
			<c:if test="${not empty msg1}">
				<p>${msg1}</p>
			</c:if>
		</div>
		
		<h1>レビュー投稿</h1>
		<h1>${menu.menuName}</h1>
		<form:form action="reviewCommit" modelAttribute="review" method="get">
			<form:input type="hidden" path="menuId" value="${menu.id}"/>
			<form:input type="hidden" path="userId" value="${user.id}"/>
			<fieldset>
				<div class="cp_iptxt">
					<form:input class="base_input" type="number" path="star" placeholder="★評価"/>
					<form:errors path="star" cssStyle="color: red"/>
					<i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
				</div>

				<div>
					<form:textarea  class="base_input" path="review" placeholder="レビューを記入してください(任意)"/>
					<form:errors path="review" cssStyle="color: red"/>
				</div>
			</fieldset>
			<form:button type="submit">送信</form:button>
		</form:form>
	</div>
</body>
</html>
