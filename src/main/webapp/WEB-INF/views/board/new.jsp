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
			<label>번호</label>
			<input class="form-control" name='no' type="text" />
		</div>
		
		<div class="form-group">
			<label>제목</label>
			<input class="form-control" name='title' type="text" />
		</div>
		
		<div class="form-group">
			<label>닉네임</label>
			<input class="form-control" name='userId' value="${article.author}" readonly="readonly">
		</div>
		
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="5" name='content'></textarea>
		</div>
		<button class="btn btn-primary" type="submit" >작성</button>
		<buttom class="btn btn-secondary" type="button" id="back">취소</buttom>
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
	</script>
</body>
</html>