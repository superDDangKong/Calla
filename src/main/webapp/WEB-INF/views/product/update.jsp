<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.productName }</title>
</head>
<body>
	<h2>상품 수정 페이지</h2>
	<form action="update" method="POST" enctype="multipart/form-data">		
		<input type="hidden" name="page" value="${page }">
		<div>
			<p>상품 번호 : ${vo.productId }</p>
			<input type="hidden" name="productId" value="${vo.productId }">
		</div>
		<div>
			<P>제목 : </P>
			<input type="text" name="productName" value="${vo.productName }">
		</div>
		<div>	
			<p>작성일 : ${vo.productCreatedDate }</p>
			<p>가격 : 
			<input type="text" name="productPrice" value="${vo.productPrice }"></p>
			<p>카테고리 : 
			<input type="text" name="productCategori" value="${vo.productCategori }"></p>
		</div>	
		<div>
    		<p>이미지</p>
    		<input type="hidden" name="productImagePath" value="${vo.productImagePath}"> 	
    		<input id="productImage" type="file" name="productImage" placeholder="이미지"> 		
		</div>	
		<div>
			<textarea rows="20" cols="120" name="productContent">${vo.productContent }</textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
</body>
</html>