<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body class="bg-black">
	<jsp:include page="../include/navHeader.jsp" />
    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">회원 수정</h1>
                            </div>
                            <form id="updateform">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="username" name="username" placeholder="회원 ID" value="${user.username}">
                                    </div>
                                    <div class="col-sm-3" id="checkDiv">
                                        
                                    </div>
                                </div>
                                <%-- <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user" id="password" name="password" placeholder="Password" value="${user.password}" readonly="readonly">
                                    </div>
<!--                                     <div class="col-sm-6"> -->
<!--                                         <input type="password" class="form-control form-control-user" id="repeatPassword" placeholder="Repeat Password"> -->
<!--                                     </div> -->
                                </div> --%>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="name" name="name" placeholder="이름" value="${user.name}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="nickName" name="nickName" placeholder="닉네임" value="${user.nickName}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="address" name="address" placeholder="주소" value="${user.address}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="phone" name="phone" placeholder="휴대번호" value="${user.phone}">
                                </div>
                                <button type="submit" class="btn btn-primary btn-user btn-block" onclick="modify()">
                                	회원 정보 수정
                                </button>
                                <button type="submit" class="btn btn-primary btn-user btn-block" onclick="remove()" >
                                	회원 정보 삭제
                                </button>
                                <hr>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
<script type="text/javascript">

//수정하기
function modify(){
	var form = document.getElementById("updateform");
	var formData = {
			username: form.elements["username"].value,
			name: form.elements["name"].value,
			nickName: form.elements["nickName"].value,
			address: form.elements["address"].value,
			phone: form.elements["phone"].value
	    };
    // AJAX 요청
    $.ajax({
        type: "PATCH",
        url: "/user/" + formData.username,
        data: JSON.stringify(formData),
        contentType: "application/json",
        headers: {
	        "Authorization": getCookie("Authorization")
	      },
        success: function(response) {
            // 수정 성공 시 동작
            location.reload();
        },
        error: function(xhr, status, error) {
            // 에러 처리
            console.log("에러");
        }
    });
}

//회원 삭제 
function remove(){
	var form = document.getElementById("updateform");
	var formData = {
			username: form.elements["username"].value,
			name: form.elements["name"].value,
			nickName: form.elements["nickName"].value,
			address: form.elements["address"].value,
			phone: form.elements["phone"].value
	    };
    // AJAX 요청
    $.ajax({
        type: "DELETE",
        url: "/user/" + formData.username,
        data: JSON.stringify(formData),
        contentType: "application/json",
        headers: {
	        "Authorization": getCookie("Authorization")
	      },
        success: function(response) {
            // 수정 성공 시 동작
            location.href = "/";
        },
        error: function(xhr, status, error) {
            // 에러 처리
            console.log(response);
        }
    });
}

$.ajax({
     type: "POST",
     url: "/user/user-check",
     data: JSON.stringify(),
     contentType: "application/json",
     dataType: "json",
     headers: {
       "Authorization": getCookie("Authorization")
     },
     success: function(response) {
       // 성공적으로 처리된 후 동작
	   	 $("#username").val(response.username);
	     $("#name").val(response.name);
	     $("#nickName").val(response.nickName);
	     $("#address").val(response.address);
	     $("#phone").val(response.phone);
     },
     error: function(xhr, status, error) {
       alert("로그인 후 이용가능합니다.");
       location.href = "/customLogin";
     }
   });

</script>
</body>
</html>