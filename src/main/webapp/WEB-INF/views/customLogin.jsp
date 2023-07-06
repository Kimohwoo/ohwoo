<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp" %>
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
	<form action="/user/login" method="post">
		<input type="text" name="username" id="username">
		<input type="password" name="password" id="password">
		<input type="submit" value="Submit">
	</form>
	<script>
           /*  function submitForm() {
                var formData = {
                    username: $("#username").val(),
                    password: $("#password").val()
                };

                $.ajax({
                    type: "POST",
                    url: "/login",
                    data: JSON.stringify(formData),
                    contentType: "application/json",
                    dataType: "json", // JSON 파싱을 자동으로 처리
                    success: function(response, status, xhr) {
                        // 처리 완료 후의 동작
                        var jwtToken = xhr.getResponseHeader("Authorization");
                        document.cookie = "Authorization=" + jwtToken;
                        alert(jwtToken);
                        location.replace("/");
                    },
                    error: function(xhr, status, error) {
                        // 에러 처리
                        console.log("에러")
                    }
                });
            } */
        </script>
</body>
</html>