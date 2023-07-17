<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body class="bg-secondary">
	<div class="position-absolute top-50 start-50 translate-middle">
		<form action="/user/login" method="post" onsubmit="submitForm(); return false;">
		  <div class="form-group">
		    <label for="exampleInputEmail1">아이디</label>
		    <input type="text" name="username" id="username" class="form-control form-control-lg" placeholder="아이디">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">비밀번호</label>
		    <input type="password" class="form-control form-control-lg" name="password" id="password" placeholder="비밀번호">
		  </div>
		  <button type="submit" class="btn btn-primary w-100">로그인</button>
		</form>
	</div>
	<script>
	    function submitForm() {
	       var formData = {
	           username: $("#username").val(),
	           password: $("#password").val()
	       };
	
	       $.ajax({
	           type: "POST",
	           url: "/user/login",
	           data: JSON.stringify(formData),
	           contentType: "application/json",
	           /* dataType: "json", // JSON 파싱을 자동으로 처리 */
	           success: function(response, status, xhr) {
	               // 처리 완료 후의 동작
					var jwtToken = xhr.getResponseHeader("Authorization");
					document.cookie = "Authorization=" + jwtToken;
					location.replace("/"); 
	           },
	           error: function(xhr, status, error) {
	               // 에러 처리
	        	   alert("Id 또는 비밀번호를 확인해주세요");
	           }
	       });
	   } 
	</script>
</body>
</html>