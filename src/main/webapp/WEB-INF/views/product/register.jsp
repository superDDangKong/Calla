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
			<p>상품 이름 :</p>
			<input type="text" name="productName" placeholder="이름 입력" required>
		</div>
		<div>
			<p>가격 :</p>
			<input type="number" name="productPrice" placeholder="가격" required>
		</div>
		<div>
			<label for="productCategori">카테고리 :</label>
			<select id="productCategori" name="productCategori">
				<option value="만화">만화</option>
				<option value="굿즈">굿즈</option>
				<option value="캐릭터">캐릭터</option>
			</select>
		</div>
		<div>
			<p>내용 :</p>
			<textarea name="productContent" placeholder="내용" rows="4" cols="50" required></textarea>
		</div>
		<div>
			<p>이미지 :</p>
			<input id="productImage" type="file" name="productImage" placeholder="이미지" >
		</div>
		<div>
			<input type="submit" value="등록">
			<input type="button" value="취소" onclick="window.location.href='list';">
        </div>
		</div>
		
	</form>
	
	
</body>
</html>