<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body class="bg-secondary">
<!-- 		<div> -->
 			<jsp:include page="../include/navHeader.jsp" />
<!-- 		</div> -->
		<div class="position-absolute top-50 start-50 translate-middle">
		<form id="form" method="post">
			<!-- Name input-->
			<div class="form-floating mb-3">
				<input class="form-control form-control-lg" id="username" name="username" type="text" placeholder="아이디를 입력해주세요" data-sb-validations="required" />
				<label for="username">아이디</label>
				<div class="invalid-feedback" data-sb-feedback="id:required">Id가 필요합니다.</div>
			</div>
<!-- 			Password input -->
			<div class="form-floating mb-3">
			    <input class="form-control form-control-lg"  id="password" name="password" type="password" placeholder="비밀번호를 입력해주세요" data-sb-validations="required" />
			    <label for="password">비밀번호</label>
			    <div class="invalid-feedback" data-sb-feedback="password:required">Password가 필요합니다.</div>
			</div>
			<div class="form-floating mb-3">
			    <input class="form-control form-control-lg" id="nickName" name="nickName" type="text" placeholder="닉네임" data-sb-validations="required" />
			    <label for="email">닉네임</label>
			    <div class="invalid-feedback" data-sb-feedback="nickName:required">닉네임이 필요합니다</div>
			</div>
			<!-- Phone number input-->
			<div class="form-floating mb-3">
			    <input class="form-control" id="phone" name="phone" type="tel" placeholder="-제외한 번호를 입력해주세요" data-sb-validations="required" />
			    <label for="phone">'-' 제외한 휴대번호</label>
			</div>
	    	<button class="btn btn-primary w-100" id="submitButton" type="submit">Submit</button>
		</form>
		</div>
		<script>
		  $(document).ready(function() {
		    $("form").on("submit", function(event) {
		      event.preventDefault(); // 기본 제출 동작 방지 
		
		      // 폼 데이터를 JSON 객체로 변환
		      var formData = {
		        username: $("input[name='username']").val(),
		        password: $("input[name='password']").val(),
		        nickName: $("input[name='nickName']").val(),
		        phone: $("input[name='phone']").val()
		      };
			if(formData.username == null || formData.username == ""){
				alert("아이디를 입력하세요");
				form.username.focus();
			} else if(formData.username.indexOf(" ")>0){
				alert("아이디에 공백이 있습니다. 공백을 제거해주세요.");
				form.username.focus();
			} else if(formData.username.length < 4){
				alert("아이디는 4자 이상이어야 합니다.");
			} else if(formData.password == null || formData.password == ""){
				alert("비밀번호를 입력하세요");
				form.password.focus();
			} else if(formData.password.length < 6 || formData.password.length > 32){
				alert("비밀번호는 6자 이상 32자 이하 입니다.")
			} else if(formData.nickName == null || formData.nickName == ""){
				alert("닉네임을 입력해주세요")
				form.nickName.focus();
			} else if(formData.nickName.indexOf(" ")>0){
				alert("닉네임에 공백이 있습니다. 공백을 제거해주세요.");
				form.nickName.focus();
			} else if(formData.phone.length !== 11){
				alert("휴대번호를 정확히 입력해주세요.");
				form.phone.focus();
			} else {
				$.ajax({
			        type: "POST",
			        url: "/user/"+formData.username,
			        data: JSON.stringify(formData),
			        contentType: "application/json",
			        success: function(response) {
			          // 성공적으로 처리된 후 동작
			         if(response == "Use"){
			        	 alert("사용중인 아이디 입니다. 아이디를 변경해주세요");
			        	 form.username.focus();
			         } else {
			        	 $.ajax({
						        type: "POST",
						        url: "/user/user-reg",
						        data: JSON.stringify(formData),
						        contentType: "application/json",
						        dataType: "json",
						        success: function(response) {
						          // 성공적으로 처리된 후 동작
						          alert("회원가입이 정상적으로 완료되었습니다!!");
						          // 다른 동작 수행
						          window.location.href = "/customLogin";
						        },
						        error: function(xhr, status, error) {
						          console.log("에러");
						        }
					      });
			         }
			        },
			        error: function(xhr, status, error) {
			          alert("중복된 아이디 입니다. 아이디를 변경해주세요");
			        }
			      });
		      }
		    });
		  });
		</script>
	</body>
</html>