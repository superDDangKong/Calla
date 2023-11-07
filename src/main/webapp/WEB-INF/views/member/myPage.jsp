<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<title>마이페이지</title>
</head>
<body>
<%@ include file="../header.jspf" %> 	
<input type="hidden" id="memberId" value=${memberId }>
<input type="hidden" id="memberNickname" value=${memberNickname }>
<input type="hidden" id="memberNickname" value=${memberLevel }>
<h2>마이페이지</h2>
	
	<ul>
		<li>MY 쇼핑</li>
			<ul>
				<li><a href="order">주문 목록</a></li>
				<li><a href="cancel">취소/반품/교환/환불 내역</a></li>
				<li><a href="myuproduct">내가 올린 상품</a></li>
				<li><a href="/calla/uProduct/uproductsell">판매 목록(중고)</a></li>
				<li><a href="/calla/uProduct/uproductbuy">구매 목록(중고)</a></li>
			</ul>
		<li>MY 활동</li>
			<ul>
				<li><a href="boards">작성글</a></li>
				<li><a href="comments">작성댓글</a></li>
				<li><a href="likes">좋아요 리스트</a></li>
			</ul>
		<li>MY 정보</li>
			<ul>
				<li><a href="update">개인정보확인/수정</a>
				<c:if test="${memberLevel == 3}">
				<li><a href="manageMember">회원등업(관리자 전용)</a>
				</c:if>
			</ul>
	</ul>
<%@ include file="../footer.jspf" %> 
</body>
</html>