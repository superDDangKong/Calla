<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
<div id="login">
<input type="hidden" id="memberNickname" value=${memberNickname }>
<input type="hidden" id="memberId" value=${memberId }>
</div>

<div id="product">

</div>
<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">

			<h1></h1>

			<a href="register"><input type="button" value="내 물건 등록"></a>
			<a href="list"><input type="button" value="상품 목록"></a>

		</div>
		<div class="top_area">
			<div class="logo_area">
				<h1>logo area</h1>
			</div>
			<div class="search_area">
				<h1>Search area</h1>
			</div>
			<div class="login_area">
				<div class="login_button">
					<a href="/calla/member/login">로그인</a>
				</div>
				<span><a href="/calla/member/join">회원가입</a></span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="navi_bar_area">
			<h1>navi area</h1>
		</div>
		<div class="content_area">
			<h1>content area</h1>
		</div>
	</div>
</div>

