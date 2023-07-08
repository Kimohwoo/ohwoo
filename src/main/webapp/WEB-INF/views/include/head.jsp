<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico" />" />
	<!-- Bootstrap Icons-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
	<link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
	<!-- SimpleLightbox plugin CSS-->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	function getCookie(name) {
        var cookieName = name + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var cookieArray = decodedCookie.split(";");

        for (var i = 0; i < cookieArray.length; i++) {
          var cookie = cookieArray[i];
          while (cookie.charAt(0) === " ") {
            cookie = cookie.substring(1);
          }
          if (cookie.indexOf(cookieName) === 0) {
            return cookie.substring(cookieName.length, cookie.length);
          }
        }
        return null;
      }
	</script>