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
							
							<div class="form-group">
								<label>Post_id</label>
								<input class="form-control" name='post_id' value='<c:out value="${article.no }"/>' readonly="readonly">
							</div>
							
							<div class="form-group">
								<label>Title</label>
								<input class="form-control" name='title' value='<c:out value="${article.title}"/>' readonly="readonly">
							</div>
							
							<div class="form-group">
								<label>User Nickname</label>
								<%-- <% String sessionId = (String) session.getAttribute("sessionId"); %> --%>
								<input class="form-control" name='userId' value="${article.author}" readonly="readonly">
							</div>
							
							<div class="form-group">
								<label>Text area</label>
								<textarea class="form-control" rows="5" name='content' readonly="readonly"><c:out value="${board.content}" /></textarea>
							</div>
							
							<form id='operForm' action="/board/modify" method="get"><!-- get방식, 수정창 띄우는거니까 -->
								<input type='hidden' id='post_id' name='post_id' value='<c:out value="${board.post_id}"/>'>
								<input type='hidden' id='userId' name='userId' value='<c:out value="${board.userId}"/>'>
								<button type="submit" data-oper='modify' class="btn btn-primary">Modify</button>
								<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
								<button type="submit" data-oper='list' class="btn btn-info">List</button>
							</form>
							
							<hr/>

							<!-- end panel-body -->
           			 	</div>
           			 	
					<table width="100%">
                    	<thead>
                    		<tr>
                    			<th>댓글 작성자</th>
                    			<th>내용</th>
                    			<th>등록일</th>
                    		</tr>
                    	</thead>
						<c:forEach items="${reply}" var="reply">
							<tr>	
                    			<td>
                    				<c:out value="${reply.userId}"/>
                    			</td>
                    			
                    			<td>
                    				<c:out value="${reply.content}"/>
                    			</td>
	                     		
	                     		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${reply.created_at}"/></td>
	  							<td>
	  								<a href="/reply/modify?post_id=${board.post_id}&comment_id=${reply.comment_id}">수정  |  </a>
	  								<a href="/reply/remove?post_id=${board.post_id}&comment_id=${reply.comment_id}">삭제</a>
	  							</td>	  							
                        	</tr>
						</c:forEach>
					</table>
           			 	
           			<hr/>
            		<div>						
						<form id='reply123' action="/reply/write" method="post">
						
							<div>
								<input type="hidden" name="post_id" value="${board.post_id}">
							</div>
							<div>
								<label>댓글 작성자</label><br>
								<%-- <% String sessionId = (String) session.getAttribute("sessionId"); %>
								<input class="form-control" name='user_id' value='<%=sessionId %>' readonly="readonly"> --%>
								<input name='nickName' value="${nickName}" readonly="readonly">
							</div>
							<div>
								<label>댓글</label><br>
								<textarea rows="5" cols="50" name="content"></textarea>
							</div>
								 <button id="reply" type="submit" >댓글 작성</button> 
								 <button id="reset" type="reset" >취소</button>
						</form>
					</div>
           			 	
       			 	</div>
	  			</div>
    		</div>
  		</div>
  	</div>
	<script type="text/javascript">
	$(function() {
		
		var likecheck = ${likecheck};
		 

		if (document.forms["operForm"]) {
		    let formObj = $("#operForm");
		    
		    $('button', formObj).on("click",
		    function(e) {
		      e.preventDefault();
		      const operation = $(this).data("oper");
		      console.log(operation);
		      
		      if (operation === 'remove') {
		        formObj.attr("action", "/board/remove");
		      }
		      
		      else if (operation === 'list') {
		        formObj.find("#post_id").remove();
		        formObj.attr("action", "/board/list")
		      }
		      formObj.submit();
		    });
		  }
	});
	</script>
</body>
</html>