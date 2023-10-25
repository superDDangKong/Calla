<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.multipart.MultipartFile" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"></script>

</head>
<body>
	<h2>상품 등록</h2>
	<form action="register" method="post" enctype="multipart/form-data">
	
		<div>
			<p>닉네임</p>
			<input type="text" name="memberNickname" value="${sessionScope.memberNickname }" readonly="readonly">
		</div>
		<div>
			<p>상품 이름</p>
			<input type="text" name="uProductName" placeholder="이름 입력" required>
		</div>
		<div>
			<p>작성자 주소</p>
			<input type="text" name="memberAddress" placeholder="이름 입력" required>
		</div>
		<div>
			<p>가격</p>
			<input type="text" name="uProductPrice" placeholder="가격" required>
		</div>
		<div>
			<p>카테고리</p>
			<input type="text" name="uProductCategori" placeholder="카테고리" required>
		</div>
		<div>
			<p>내용</p>
			<input type="text" name="uProductContent" placeholder="내용" required>
		</div>
		<div>
			<p>이미지</p>
			<input id="productImage" type="file" name="productImage" placeholder="이미지" >
		</div>
		
			<input type="submit" value="등록">
		</div>
	</form>
	
	
</body>
</html>