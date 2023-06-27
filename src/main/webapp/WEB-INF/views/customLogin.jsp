<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	CustomLogin 페이지 입니다.
	<h2><c:out value="${error}"/></h2>
	<h2><c:out value="${logout}"/></h2>
	<form>
		<input type="text" name="username" id="username">
		<input type="password" name="password" id="password">
		<input type="button" value="Submit" onclick="submitForm()">
	</form>
	<script>
            function submitForm() {
                var formData = {
                    username: $("#username").val(),
                    password: $("#password").val()
                };

                $.ajax({
                    type: "POST",
                    url: "/login",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    success: function(response) {
                        // 처리 완료 후의 동작
                    },
                    error: function(xhr, status, error) {
                        // 에러 처리
                        console.log("에러")
                    }
                });
            }
        </script>
</body>
</html>