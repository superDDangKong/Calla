<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
	
</script>
<meta charset="UTF-8">
<title>${vo.productName }</title>
</head>
<body>
	<h2>상품 보기</h2>
	<div>
		<p>상품 번호 : ${vo.productId }</p>
	</div>
	<div>
		<p>상품 이름 : ${vo.productName }</p>
	</div>
	<div>
		<p>상품 이미지 : </p>
		<img src="display?fileName=${vo.productImagePath}" width="100px" height="100px">
	</div>
	<div>
		<p>작성일 : ${vo.productCreatedDate }</p>
		<p>카테고리 : ${vo.productCategori }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.productContent }</textarea>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
	
	<a href="update?productId=${vo.productId }&page=${page }"><input type="button" value="글 수정"></a>
	<form action="delete" method="POST">
		<input type="hidden" id="productId" name="productId" value="${vo.productId }">
		<input type="submit" value="상품 삭제">
	</form>
</body>


</html>