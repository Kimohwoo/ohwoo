<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	CustomLogin 페이지 입니다.
	<h2><c:out value="${error}"/></h2>
	<h2><c:out value="${logout}"/></h2>
	<form action="/login" method="post">
		<input type="text" name="username">
		<input type="password" name="password">
		<input type="submit">
	</form>
</body>
</html>