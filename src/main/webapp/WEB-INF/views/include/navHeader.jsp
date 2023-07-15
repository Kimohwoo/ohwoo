<%@page import="org.springframework.web.filter.HiddenHttpMethodFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- Navigation-->
<!--         <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav"> -->
		<nav class="navbar navbar-expand-lg" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/">개인 프로젝트</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link" href="/board/list?pageNum=1&amount=5">커뮤니티</a></li>
                        <li class="nav-item"><a class="nav-link" href="/">홈</a></li>
                        <li class="nav-item" id="user"><a class="nav-link" href="/customLogin">로그인</a></li>
                        <li class="nav-item"><a class="nav-link" href="#contact"></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <script>
        var cookie = getCookie("Authorization")
        //로그인 유저
        if(cookie != null){
			$.ajax({
				type: "POST",
				url: "/user/user-check",
				contentType: "application/json",
				headers: {
				"Authorization": cookie
				},
				success: function(response) {
				// 수정 성공 시 동작
					$("#user").html("<a class='nav-link' href='/logout'>로그아웃</a>")
					$("#userBtn").html("")
				},
				error: function(xhr, status, error) {
				// 에러 처리
				console.log("에러");
				}
			});
        }
        </script>