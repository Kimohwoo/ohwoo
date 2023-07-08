<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="../include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../include/navHeader.jsp"/>
	<form>
		<div class="form-group">
			<label>제목</label>
			<input class="form-control" name='title' type="text" />
		</div>
		
		<div class="form-group">
			<label>닉네임</label>
			<input class="form-control" name='nickName' value="${article.author}" readonly="readonly">
		</div>
		
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="5" name='content'></textarea>
		</div>
		<button class="btn btn-primary" type="submit" >작성</button>
		<button class="btn btn-secondary" type="button" id="back">돌아가기</button>
	</form>
	<script>
		$(document).ready(function() {
	    // 이전 페이지로 돌아가는 함수
		    function goBack() {
		      window.history.back();
		    }

	    // 버튼 클릭 이벤트 핸들러 등록
		    $("#back").on("click", goBack);
	    
		  });
		
		 $("form").on("submit", function(event) {
 			    event.preventDefault(); // 기본 제출 동작 방지 

			    var formData = {
			      title: $("input[name='title']").val(),
			      nickName: $("input[name='nickName']").val(),
			      content: $("textarea[name='content']").val()
			    };
				
			    $.ajax({
			      type: "POST",
			      url: "/board/article",
			      data: JSON.stringify(formData),
			      contentType: "application/json",
			      dataType: "json",
			      headers: {
			        "Authorization": getCookie("Authorization")
			      },
			      success: function(response) {
			        alert("글 작성이 완료되었습니다.");
			        // 성공적으로 처리된 후 동작
			        location.href = '/board/list';
			      },
			      error: function(xhr, status, error) {
			        console.log("에러");
			      }
			    });
		 });
	</script>
</body>
</html>