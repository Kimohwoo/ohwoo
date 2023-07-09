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
					<th>작성자</th>
					<th>제목</th>
					<th>내용</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="board">
					<tr>
						<td><c:out value='${board.author}'/></td>
					
						<td><a href="javascript:article(<c:out value="${board.no}"/>)"><c:out value="${board.title}" /><c:out value="${board.content}" /></a></td>
						
						<td><c:out value="${board.content }" /></td>
						
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                   	</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination justify-content-end">
		    <li class="page-item">
		        <a href="javascript:page(<c:out value='${(pages.pageNum-1 == 0) ? 1 : pages.pageNum-1}' />, 5)" aria-label="Previous" class="page-link" id="pre">
		            <span aria-hidden="true">이전</span>
		        </a>
		    </li>
			
		    <li class="page-item">
		        <a href="javascript:page(<c:out value='${pages.pageNum+1}' />, 5)" aria-label="Next" class="page-link" id="next">
		            <span aria-hidden="true">다음</span>
		        </a>
		    </li>
		</ul>
	</div>
	
	<script>
		$().ready(function(){
			
			function newBoard(){
			        window.location.href = '/board/new';
				}
			
			$("#newBoard").on("click", newBoard);
		})
		
		var list = '${list}';
		function page(pageNum, amount){
	       location.href="/board/list?pageNum=" + pageNum +
			"&amount=" + amount
		}
		if (list.length == 2) {
	        document.getElementById('next').style.display = 'none';
	    }
		
		
		function article(no){
			location.href="/board/article?no=" + no;	
		}
		
	</script>
</body>
</html>