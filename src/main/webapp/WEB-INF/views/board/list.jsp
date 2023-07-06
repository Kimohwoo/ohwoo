<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body style="background-color: black;">
	<jsp:include page="../include/navHeader.jsp"/>
	<div class="container">
		<h3 style="text-align: center; padding-top: 50px; color: white;">게시판</h3>
		<button class="btn btn-primary" id="newBoard">글 작성</button>
		<table class="table table-border table-striped table-dark table-hover">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="board">
					<tr>
						<td><a><c:out value="${board.no}" /></a></td>
						
						<td><a href="/board/article?no=<c:out value="${board.no}"/>"><c:out value="${board.title}" /><c:out value="${board.content}" /></a></td>
							
						<td><c:out value='${board.author}'/></td>
						
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                   	</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		$().ready(function(){
			
			function newBoard(){
				window.location.href = "/board/new";
			}
			
			$("#newBoard").on("click", newBoard);
		})
	</script>
</body>
</html>