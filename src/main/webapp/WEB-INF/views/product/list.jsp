<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<h1>List</h1>
	<a href="register"><input type="button" value="상품등록"></a>
	
	<hr>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이미지</th>
				<th>이름</th>
				<th>가격</th>
				<th>카테고리</th>
				<th>작성일</th>
				<th>리뷰수</th>
				<th>좋아요수</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.productId }</td>					
					<td>
						<div>
							<img src="display?fileName=${vo.productImagePath}" width="100px" height="100px">
						</div>
					</td>
					<td><a href="detail?productId=${vo.productId }&page=${pageMaker.criteria.page}">${vo.productName }</a></td>
					<td>${vo.productPrice }</td>
					<td>${vo.productCategori }</td>
					<fmt:formatDate value="${vo.productCreatedDate }"
						pattern="yyyy-MM-dd HH:mm:ss" var="productCreatedDate"/>
					<td>${productCreatedDate }</td>
					<td>${vo.productCommentCount }</td>
					<td>${vo.productLikes }</td>
					<td>${vo.productViews }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul>
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }"
			end="${pageMaker.endPageNo }" var="num">
			<li><a href="list?page=${num }">${num }</a></li>
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>
	</ul>
	
	<input type="hidden" id="insertAlert" value="${insert_result }">
	
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success'){
			alert('새 상품 등록 완료');
		}
		
		
	</script>
</body>
</html>