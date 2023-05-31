<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: black;">
	<jsp:include page="../include/navHeader.jsp"/>
	<div class="container">
		<h3 style="text-align: center; padding-top: 50px; color: white;">게시판</h3>
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
						
						<td><c:out value="${board.title}" /><c:out value="${board.content}" /></td>
							
						<td><c:out value='${board.author}'/></td>
						
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                   	</tr>
				</c:forEach>
			</tbody>
		</table>
		<a class="btn btn-secondary" href="/board/?pageNum=${1}&amount=${10}}">이전 페이지</a>
		<a class="btn btn-primary" href="/board/?pageNum=${2}&amount=${20}}">다음 페이지</a>
	</div>
</body>
</html>