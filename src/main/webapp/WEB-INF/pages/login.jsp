<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginSystem</title>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<link href="${contextRoot}/bootstrap.css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container text-center mx-auto">
		<h3>LoginSystem</h3>
		<div class="row align-items-center justify-content-center">
		<form action="${contextRoot}/message/login" method="post" class="form-label">
			<table>
				<tr>
					<td>UserName:</td>
					<td><input type="text" name="account" class="form-control"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" class="form-control"></td>
				</tr>
				<tr>
					<td><button type="submit" value="login" class="btn btn-success">登入</button></td>
				</tr>
			</table>
			<h2 style="color:red">${error}</h2>
		</form>
		</div>
	</div>


	<script type="text/javascript"
		src="${contextRoot}/js/jquery-3.6.1.min.js"></script>
	<script type="text/javascript"
		src="${contextRoot}/js/bootstrap.bundle.js"></script>
	<script type="text/javascript" src="${contextRoot}/js/popper.min.js"></script>
</body>
</html>