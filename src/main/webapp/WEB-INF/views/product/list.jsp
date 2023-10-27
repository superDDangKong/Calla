<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
table, th, td {
	border-style : solid;
	border-width : 1px;
	text-align : center;
}

ul {
	list-style-type : none;
}

li {
	display : inline-block;
}
</style>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<h1>List</h1>
	<%@ include file="../header.jspf" %> 	
	<h1>상품 게시판</h1>
	<button id="home"><a href="/calla/">홈</a></button>
	<input type="hidden" id="selectedOption" value=${option }>
	<input type="hidden" id="sessionNickname" value=${memberNickname }>
	<input type="hidden" id="sessionLevel" value=${memberLevel }>
	
	
	<div id="register">
		<a href="register"><input type="button" value="상품 등록"></a>
	</div>
	
	<form action="list" method="GET">
		<select id="option" name="option">
			<option value="searchTitleOrContent">제목&내용</option>
		</select> 
		<input type="text" name="keyword" value="${keyword }"> 
		<input type="submit" value="검색">
	</form>
	
	<hr>
	<table>
		<thead>
			<tr>
				<th style="width : 60px">번호</th>
				<th style="width : 60px">이미지</th>
				<th style="width : 60px">이름</th>
				<th style="width : 60px">가격</th>
				<th style="width : 60px">카테고리</th>
				<th style="width : 60px">작성일</th>
				<th style="width : 60px">리뷰수</th>
				<th style="width : 60px">좋아요수</th>
				<th style="width : 60px">조회수</th>
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
	
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#selectedOption").val() != ""){
				$("#option").val($("#selectedOption").val());
			}
			
			if(!($('#sessionLevel').val() >= 2)) {
				var list = 
					"상품등록은 관리자기능입니다."
					+ "&nbsp&nbsp"
				$("#register").html(list);
			}
			
		}) // end document.ready()
	</script>
	
	<input type="hidden" id="insertAlert" value="${insert_result }">
	
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success'){
			alert('새 상품 등록 완료');
		}
		
		
	</script>
</body>
</html>