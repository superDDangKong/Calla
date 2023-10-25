<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<%@ include file="../header.jspf" %> 	
<input type="hidden" id="memberId" value=${memberId }>
<h2>마이페이지</h2>
	
	<ul>
		<li>MY 쇼핑</li>
			<ul>
				<li><a href="order">주문 목록</a></li>
				<li><a href="cancel">취소/반품/교환/환불 내역</a></li>
			</ul>
		<li>MY 활동</li>
			<ul>
				<li>작성글</li>
				<li>작성댓글</li>
				<li><a href="likes">좋아요 리스트</a></li>
			</ul>
		<li>MY 정보</li>
			<ul>
				<li><a href="update">개인정보확인/수정</a>
			</ul>
	</ul>
		
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$()
		}) // end document.ready
	</script>

</body>
</html>