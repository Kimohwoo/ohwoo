<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/head.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body id="page-top">
		<div>
			<jsp:include page="../include/navHeader.jsp" />
		</div>
		<form id="contactForm" data-sb-form-api-token="API_TOKEN">
			<!-- Name input-->
			<div class="form-floating mb-3">
				<input class="form-control" id="username" type="text" placeholder="아이디를 입력해주세요" data-sb-validations="required" />
				<label for="username">회원 아이디</label>
				<div class="invalid-feedback" data-sb-feedback="id:required">Id가 필요합니다.</div>
			</div>
			<!-- Email address input-->
			<div class="form-floating mb-3">
			    <input class="form-control" id="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
			    <label for="email">Email address</label>
			    <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
			    <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
			</div>
			<!-- Phone number input-->
			<div class="form-floating mb-3">
			    <input class="form-control" id="phone" type="tel" placeholder="(123) 456-7890" data-sb-validations="required" />
			    <label for="phone">Phone number</label>
			    <div class="invalid-feedback" data-sb-feedback="phone:required">A phone number is required.</div>
			</div>
			<!-- Message input-->
			<div class="form-floating mb-3">
			    <textarea class="form-control" id="message" type="text" placeholder="Enter your message here..." style="height: 10rem" data-sb-validations="required"></textarea>
			    <label for="message">Message</label>
			    <div class="invalid-feedback" data-sb-feedback="message:required">A message is required.</div>
			</div>
			<!-- Submit success message-->
			<!---->
			<!-- This is what your users will see when the form-->
			<!-- has successfully submitted-->
			<div class="d-none" id="submitSuccessMessage">
			    <div class="text-center mb-3">
			        <div class="fw-bolder">Form submission successful!</div>
			        To activate this form, sign up at
			        <br />
			        <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
			    </div>
			</div>
			<!-- Submit error message-->
			<!---->
			<!-- This is what your users will see when there is-->
			<!-- an error submitting the form-->
			<div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
			<!-- Submit Button-->
		    <div class="d-grid"><button class="btn btn-primary btn-xl disabled" id="submitButton" type="submit">Submit</button></div>
		</form>
	</body>
</html>