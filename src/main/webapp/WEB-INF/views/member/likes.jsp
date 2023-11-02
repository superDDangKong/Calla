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
<title>좋아요 목록</title>
</head>
<body>
<%@ include file="../header.jspf" %> 
<h1>공용상품</h1>
	<table>
		<thead>
			<tr>
				<th style="width : 120px">작성자</th>
				<th style="width : 700px">내용</th>
				<th style="width : 100px">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="productCommentVo" items="${lists.ProductCommentList }">
				<tr>
					<td>${productCommentVo.memberNickname }</td>
					<td><a href="/calla/product/detail?productId=${productCommentVo.productId}&memberId=${memberId}">${productCommentVo.productCommentContent }</a></td>
					<fmt:formatDate value="${productCommentVo.productCommentCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="productCommentCreatedDate"/>
					<td>${productCommentCreatedDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<%@ include file="../footer.jspf" %> 
</body>
</html>