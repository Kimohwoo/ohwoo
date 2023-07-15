<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
</head>
<body style="background-color: black;">
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
							<form id='operForm' action="/board/article" method="post">
								<div class="form-group">
									<label>등록날짜</label>
									<input class="form-control" name='regdate' id='regdate' value='<c:out value="${article.regdate}"/>' readonly="readonly">
								</div>
								
								<div class="form-group">
									<label>제목</label>
									<input class="form-control" name='title' id="title" value='<c:out value="${article.title}"/>'>
								</div>
								
								<div class="form-group">
									<label>닉네임</label>
									<input class="form-control" name='author' id="author" value="${article.author}" readonly="readonly">
								</div>
								
								<div class="form-group">
									<label>내 용</label>
									<textarea class="form-control" rows="5" name='content' id="content"><c:out value="${article.content}" /></textarea>
								</div>
									<button type="button" onclick="modify()" class="btn btn-primary">글 수정</button>
								    <button type="button" onclick="remove()" class="btn btn-danger">글 삭제</button>
								    <button type="button" onclick="goBack()" class="btn btn-info">돌아가기</button>
							</form>
							<hr/>
							<!-- end panel-body -->
           			 	</div>
       			 	</div>
	  			</div>
    		</div>
  		</div>
  	</div>
	<script type="text/javascript">
	function modify() {
		var form = document.getElementById("operForm");
		var formData = {
				no: ${article.no},
		        regdate: form.elements["regdate"].value,
		        title: form.elements["title"].value,
		        author: form.elements["author"].value,
		        content: form.elements["content"].value
		    };
        
        // AJAX 요청
        $.ajax({
            type: "PUT",
            url: "/board/article",
            data: JSON.stringify(formData),
            contentType: "application/json",
            headers: {
    	        "Authorization": getCookie("Authorization")
    	      },
            success: function(response) {
                // 수정 성공 시 동작
                alert("수정이 완료되었습니다.")
                location.href = "/board/list?pageNum=1&amount=5";
            },
            error: function(xhr, status, error) {
                // 에러 처리
                // ...
                alert("수정 실패.")
            }
        });
    }

    function remove() {
    	var form = document.getElementById("operForm");
        var formData = {
        		no: ${article.no},
		        regdate: form.elements["regdate"].value,
		        title: form.elements["title"].value,
		        author: form.elements["author"].value,
		        content: form.elements["content"].value
		    };
        
        // AJAX 요청
        $.ajax({
            type: "DELETE",
            url: "/board/article",
            data: JSON.stringify(formData),
            contentType: "application/json",
            headers: {
    	        "Authorization": getCookie("Authorization")
    	      },
            success: function(response) {
                // 삭제 성공 시 동작
				alert("삭제 완료");
				location.href = "/board/list?pageNum=1&amount=5";
            },
            error: function(xhr, status, error) {
                // 에러 처리
                alert("삭제 실패");
            }
        });
    }

    function goBack() {
        location.href = "/board/list?pageNum=1&amount=5";
    }
	
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
	        // 성공적으로 처리된 후 동작
	    	  if(response.nickName == '${article.author}'){
			  } else {
				  location.href = "/board/article?no=" + '${article.no}';
			  }
	        
	      },
	      error: function(xhr, status, error) {
	        console.log("에러");
	        location.href = "/board/article?no=" + '${article.no}';
	      }
	    });
	
	</script>
</body>
</html>