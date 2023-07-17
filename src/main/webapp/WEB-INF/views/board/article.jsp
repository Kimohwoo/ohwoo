<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
</head>
<body class="bg-secondary">
	<jsp:include page="../include/navHeader.jsp"/>
	
	<div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-5">
                <!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel-heading">Board Read Page</div>
						<!--  /.panel-heading -->
						<div class="panel-body">
							
							<div class="form-group">
								<c:out value="${article.no }"/>
							</div>
							
							<div class="form-group">
								<c:out value="${article.title}"/>
							</div>
							
							<div class="form-group">
								<c:out value="${article.author}" />
							</div>
							
							<div class="form-group">
								<c:out value="${article.content}" />
							</div>
							
							<hr/>

           			 	</div>
       			 	</div>
	  			</div>
    		</div>
  		</div>
  	</div>
	<script>
	$.ajax({
	      type: "GET",
	      url: "/board/user",
	      data: JSON.stringify(),
	      contentType: "application/json",
	      dataType: "json",
	      headers: {
	        "Authorization": getCookie("Authorization")
	      },
	      success: function(response) {
	        //유저 닉네임 검사후 뷰 포워딩
			if(response.nickName == '${article.author}'){
				location.href = "/board/myarticle?no=" + '${article.no}';
			}
	      },
	      error: function(xhr, status, error) {
	        console.log("비회원");
	      }
		});
	</script>
</body>
</html>