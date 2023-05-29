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
	<jsp:include page="../include/navHeader.jsp" />
	<div class="container">
		<h3 style="text-align: center; padding-top: 50px;">게시판</h3>
		<table class="table table-border table-striped table-dark table-hover">
			<thead>
				<tr>
					<th>no</th>
					<th>title</th>
					<th>author</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="board">
					<tr>
						<td><c:out value="${board.no}" /></td>
						
						<td><c:out value="${board.title}" /><c:out value="${board.content}" /></td>
							
						<td><c:out value='${board.author}'/></td>
						
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                   	</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>