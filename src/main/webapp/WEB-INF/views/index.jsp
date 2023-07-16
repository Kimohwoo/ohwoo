<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Home</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
	<body id="page-top">
<!-- 			헤더 -->
		<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/">개인 프로젝트</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link" href="/board/list?pageNum=1&amount=5">커뮤니티</a></li>
                        <li class="nav-item"><a class="nav-link" href="#portfolio">Portfolio</a></li>
                        <li class="nav-item" id="udUser"><a class="nav-link" href="#contact"></a></li>
                        <li class="nav-item" id="user"><a class="nav-link" href="/customLogin">로그인</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Masthead-->
        <header class="masthead">
            <div class="container px-4 px-lg-5 h-100">
                <div class="row gx-4 gx-lg-5 h-100 align-items-center justify-content-center text-center">
                    <div class="col-lg-8 align-self-end">
                        <h1 class="text-white font-weight-bold">토이 프로젝트</h1>
                        <hr class="divider" />
                    </div>
                    <div class="col-lg-8 align-self-baseline">
                        <p class="text-white-75 mb-5">팀 프로젝트에서 하지 못했던 그리고 하고싶었던 여러 기능들을 만들고 싶다는 목표로 시작하게된 가벼운 프로젝트입니다!! </p>
                        <a class="btn btn-primary btn-xl" href="/user/user-reg" id="usrBtn1">회원가입</a>
                        <a class="btn btn-primary btn-xl" href="/customLogin" id="usrBtn2">로그인</a>
                    </div>
                </div>
            </div>
        </header>
        <!-- About-->
        <section class="page-section bg-primary" id="about">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 text-center">
                        <h2 class="text-white mt-0">어서오세요!</h2>
                        <h2 class="text-white mt-0">금일 방문자 수 <span style="color: black;">[${TodayVisitor}]</span></h2>
                        <h2 class="text-white mt-0">총 방문자 수 <span style="color: black;">[${AllVisitor}]</span></h2>
                        <hr class="divider divider-light" />
                        <p class="text-white-75 mb-4"></p>
                        <a class="btn btn-light btn-xl" href="#services">Get Started!</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- Services-->
        <!-- <section class="page-section" id="services">
            <div class="container px-4 px-lg-5">
                <h2 class="text-center mt-0">At Your Service</h2>
                <hr class="divider" />
                <div class="row gx-4 gx-lg-5">
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-gem fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Sturdy Themes</h3>
                            <p class="text-muted mb-0">Our themes are updated regularly to keep them bug free!</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-laptop fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Up to Date</h3>
                            <p class="text-muted mb-0">All dependencies are kept current to keep things fresh.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-globe fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Ready to Publish</h3>
                            <p class="text-muted mb-0">You can use this design as is, or you can make changes!</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <div class="mb-2"><i class="bi-heart fs-1 text-primary"></i></div>
                            <h3 class="h4 mb-2">Made with Love</h3>
                            <p class="text-muted mb-0">Is it really open source if it's not made with love?</p>
                        </div>
                    </div>
                </div>
            </div>
        </section> -->
        <!-- Portfolio-->
        <div id="portfolio">
            <div class="container-fluid p-0">
                <div class="row g-0">
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="/resources/assets/img/portfolio/fullsize/1.jpg" title="스프링 프로젝트">
                            <img class="img-fluid" src="/resources/assets/img/portfolio/thumbnails/1.jpg" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">팀 프로젝트</div>
                                <div class="project-name">스프링 프로젝트</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="/resources/assets/img/portfolio/fullsize/2.jpg" title="안드로이드 프로젝트">
                            <img class="img-fluid" src="/resources/assets/img/portfolio/thumbnails/2.jpg" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">팀 프로젝트</div>
                                <div class="project-name">안드로이드 프로젝트</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="/resources/assets/img/portfolio/fullsize/3.jpg" title="토이 프로젝트">
                            <img class="img-fluid" src="/resources/assets/img/portfolio/thumbnails/3.jpg" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">개인 프로젝트</div>
                                <div class="project-name">토이 프로젝트</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="/resources/assets/img/portfolio/fullsize/4.jpg" title="Next Step">
                            <img class="img-fluid" src="/resources/assets/img/portfolio/thumbnails/4.jpg" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">다음 프로젝트</div>
                                <div class="project-name">계획중</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="/resources/assets/img/portfolio/fullsize/5.jpg" title="Next Step">
                            <img class="img-fluid" src="/resources/assets/img/portfolio/thumbnails/5.jpg" alt="..." />
                            <div class="portfolio-box-caption">
                                <div class="project-category text-white-50">다음 프로젝트</div>
                                <div class="project-name">계획중</div>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <a class="portfolio-box" href="/resources/assets/img/portfolio/fullsize/6.jpg" title="Next Step">
                            <img class="img-fluid" src="/resources/assets/img/portfolio/thumbnails/6.jpg" alt="..." />
                            <div class="portfolio-box-caption p-3">
                                <div class="project-category text-white-50">다음 프로젝트</div>
                                <div class="project-name">계획중</div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Call to action-->
        <section class="page-section bg-dark text-white">
            <div class="container px-4 px-lg-5 text-center">
                <h2 class="mb-4">나의 깃허브로 어서오세요!!</h2>
                <a class="btn btn-light btn-xl" href="https://github.com/Kimohwoo" target="blank">깃허브로 가기</a>
            </div>
        </section>
        <!-- Contact-->
        <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Let's Get In Touch!</h2>
                        <hr class="divider" />
                        <p class="text-muted mb-5">아래의 연락처로 전화주세요!</p>
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-4 text-center mb-5 mb-lg-0">
                        <i class="bi-phone fs-2 mb-3 text-muted"></i>
                        <div>010 - 2328 -7006</div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="./include/footer.jsp"></jsp:include>
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
					$("#udUser").html("<a class='nav-link' href='/user/" + response.username + "'>회원수정</a>")
					$("#usrBtn1").attr("href", "/user/" + response.username).text("회원수정");
					$("#usrBtn2").attr("href", "/logout").text("로그아웃");
				},
				error: function(xhr, status, error) {
				// 에러 처리
				console.log("에러");
				}
			});
        }
        </script>
    </body>
</html>
