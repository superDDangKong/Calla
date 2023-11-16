<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
.slide_div img {
	margin: auto;
}

.slide_div_wrap {
	padding: 15px 0 15px 0;
	background: #e6e9f6;
}

.image_wrap img {
	max-width: 85%;
	height: auto;
	display: block;
	margin: auto;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>구트 쇼핑몰</title>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="/calla/">Goott mall</a>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarSupportedContent">
				<c:if test="${empty memberNickname }">
					<form action="/calla/member/login" method="get">
						<input type="submit" value="로그인">
					</form>
					<form action="/calla/member/join" method="get">
						<input type="submit" value="회원가입">
					</form>
				</c:if>
				<c:if test="${not empty memberNickname }">
					${memberNickname }&nbsp;님&nbsp; 
					<form action="/calla/member/logout" method="get">
						<input type="submit" value="로그아웃">
					</form>
					<form action="/calla/member/order" method="get">
						<input type="submit" value="마이페이지"> <input type="hidden"
							name="memberId" value="${memberId }">
					</form>
				</c:if>
			</div>
		</div>
	</nav>

	<div class="slide_div_wrap">

		<div class="slide_div">
			<div>
				<a> <img src="../resources/img/bnA_w01_a8daff.jpg"
					style="width: 1000px">
				</a>
			</div>
			<div>
				<a> <img src="../resources/img/bnD_w01_c3c5f7.jpg"
					style="width: 1000px">
				</a>
			</div>
			<div>
				<a> <img src="../resources/img/bnK_w01_c3c5f7.jpg"
					style="width: 1000px">
				</a>
			</div>
		</div>
	</div>


	<div id="navigator" class="text-center bg-success py-3">
		<div class="container">
			<div class="nav justify-content-center">
				<a href="/calla/product/list" class="nav-link mx-2 text-white">공용상품</a>
				<a href="/calla/uProduct/list" class="nav-link mx-2 text-white">중고상품</a>
				<a href="/calla/fBoard/list" class="nav-link mx-2 text-white">자유게시판</a>
				<a href="/calla/qBoard/list" class="nav-link mx-2 text-white">문의게시판</a>
			</div>
		</div>
	</div>

	<script>
		var $j = jQuery.noConflict();
		$j(document).ready(function() {
			$j(".slide_div").slick({
				dots : true,
				autoplay : true,
				autoplaySpeed : 5000
			});
		});
	</script>

</body>
</html>