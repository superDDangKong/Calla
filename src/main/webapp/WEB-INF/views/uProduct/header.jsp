<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js" 
integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous">
</script>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>구트 쇼핑몰</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="photos/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="resources/css/styles.css" rel="stylesheet" />
<!-- jQuery library -->
<!-- Popper Js -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

</head>
<body>
	<!-- Navigation-->
	<!-- header1 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="#!">Goot mall</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"></li>
					<li class="nav-item"><a class="nav-link" href="#!">About</a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#!">All Products</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="#!">Popular Items</a></li>
							<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
						</ul></li>
					<input type="text" id="keyword" class="head_input"
						value="${keyword }" placeholder="동네 이름, 물품명 등을 검색해보세요 !" />
					<button class="btn btn-outline-secondary" type="button" value="확인1"
						id="button-addon2">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>

				</ul>
				<!-- <form class="d-flex"> -->


				<!-- 로그인 전 -->
				<c:if test="${SessionScope.id==null}">
					<!-- Button trigger modal -->
					<c:if test="${empty memberNickname }">
							<a href="/calla/member/login">로그인</a>
						</c:if>
						<c:if test="${not empty memberNickname }">
							<a href="/calla/member/login">로그아웃</a>
						</c:if>

					<!-- Modal -->
					<div class="modal fade" id="login" tabindex="-1" role="dialog"
						aria-labelledby="loginLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<!-- Modal Header -->
								<div class="modal-header">
									<h5 class="modal-title" id="loginLabel">로그인/회원가입</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>

								<!-- Modal Body -->
								<div class="modal-body">
									<form action="" class="was-validated">
										<div class="form-group">
											<label for="memberId">아이디 </label> <input type="text"
												class="form-control" id="memberId" placeholder="아이디를 입력하세요"
												name="memberId" required>
											<div class="valid-feedback">확인</div>
											<div class="invalid-feedback">아이디를 입력해주세요</div>
										</div>
										<div class="form-group">
											<label for="memberPw">비밀번호 </label> <input type="password"
												class="form-control" id="userPw" placeholder="비밀번호를 입력하세요"
												name="memberPw" required>
											<div class="valid-feedback">확인</div>
											<div class="invalid-feedback">비밀번호를 입력해주세요</div>
										</div>


										<button type="submit" class="btn btn-primary"
											style="margin-left: 50%;">로그인</button>
										<button type="button" class="btn btn-secondary" onclick="location.href='/member/joinForm'" >회원가입</button>

									</form>

								</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">취소</button>
								</div>



							</div>
						</div>
					</div>
					
				</c:if>

				<!-- 로그인 후 -->
				<c:if test="${SessionScope.id!=null}">
						ㅇㅇㅇ님 반갑습니다.
						<!-- 일반사용자 -->
					<c:choose>

						<c:when test="${SessionScope.adminId==null}">
							<button class="btn btn-outline-dark" type="submit">
								<i class="bi-cart-fill me-1"></i> Cart <span
									class="badge bg-dark text-white ms-1 rounded-pill">0</span>
							</button>

							<!-- Example single danger button -->
							<div class="btn-group">
								<button type="button" class="btn btn-danger dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded="false">마이페이지</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">주문목록</a></li>
									<li><a class="dropdown-item" href="#">찜한상품</a></li>
									<li><a class="dropdown-item" href="#">최근본상품</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="#">회원정보변경</a></li>
									<li><a class="dropdown-item" href="#">1:1문의</a></li>
									<li><a class="dropdown-item" href="#">로그 아웃</a></li>
								</ul>
							</div>
						</c:when>
						<c:otherwise>
							<div class="btn-group">
								<button type="button" class="btn btn-danger dropdown-toggle"
									data-bs-toggle="dropdown" aria-expanded="false">관리자페이지</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">주문목록</a></li>
									<li><a class="dropdown-item" href="#">찜한상품</a></li>
									<li><a class="dropdown-item" href="#">최근본상품</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="#">회원정보변경</a></li>
									<li><a class="dropdown-item" href="#">1:1문의</a></li>
									<li><a class="dropdown-item" href="#">로그 아웃</a></li>
								</ul>
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
				<!-- 		</form> -->
			</div>
		</div>
	</nav>
	<!-- header1 end -->
	<!-- header2 -->
	<!-- Header-->
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">
					없는거 빼고 다 있는<br>구트 쇼핑몰!
				</h1>
				<p class="lead fw-normal text-white-50 mb-0">프리미엄 쇼핑몰 </p>
			</div>
		</div>
	</header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"></li>
					<li class="nav-item"><a class="nav-link" href="#!">전체상품</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">신상품</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">베스트</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">야채</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">의류/악세서리</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">목욕/미용</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">장난감</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">용품</a></li>

				</ul>
			</div>
		</div>
	</nav>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>