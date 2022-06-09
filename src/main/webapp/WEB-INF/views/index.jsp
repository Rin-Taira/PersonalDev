<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>ログイン</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	
	<div id="login">
		<h1>LunchOrder</h1>
		<c:if test="${not empty msg1}">
			<p>${msg1}</p>
		</c:if>
		<form:form action="login" method="post" modelAttribute="user" class="mb-3">
			<div class="mb-3">
				<form:input class="form-control" id="exampleInputEmail1" type="text" path="loginId" placeholder="ID"/>
				<form:errors path="loginId" cssStyle="color: black"/>
			</div>
			<div class="mb-3">
				<form:input  class="form-control" type="password" id="exampleInputPassword1" path="password" placeholder="PASS"/>
				<form:errors path="password" cssStyle="color: black"/>
			</div>
			<form:button class="btn btn-outline-primary" type="submit">ログイン</form:button>
		</form:form>
	</div>
	
	<footer>
		<p>©2022  RinTaira. All Rights Reserved</p>
	</footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
