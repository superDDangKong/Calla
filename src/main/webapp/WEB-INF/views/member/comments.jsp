<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>댓글 목록</title>
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
	
<h1>중고상품</h1>
	<table>
		<thead>
			<tr>
				<th style="width : 120px">작성자</th>
				<th style="width : 700px">내용</th>
				<th style="width : 100px">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="uProductCommentVo" items="${lists.uProductCommentList }">
				<tr>
					<td>${uProductCommentVo.memberNickname }</td>
					<td><a href="/calla/uProduct/detail?uProductId=${uProductCommentVo.uProductId }">${uProductCommentVo.uProductCommentContent }</a></td>
					<fmt:formatDate value="${uProductCommentVo.uProductCommentCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="uProductCommentCreatedDate"/>
					<td>${uProductCommentCreatedDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<h1>자유게시판</h1>
	<table>
		<thead>
			<tr>
				<th style="width : 120px">작성자</th>
				<th style="width : 700px">내용</th>
				<th style="width : 100px">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="fBoardCommentVo" items="${lists.fBoardCommentList }">
				<tr>
					<td>${fBoardCommentVo.memberNickname }</td>
					<td><a href="/calla/fBoard/detail?fBoardId=${fBoardCommentVo.fBoardId}">${fBoardCommentVo.fBoardCommentContent }</a></td>
					<fmt:formatDate value="${fBoardCommentVo.fBoardCommentCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="fBoardCommentCreatedDate"/>
					<td>${fBoardCommentCreatedDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<h1>Q&A게시판</h1>
	<table>
		<thead>
			<tr>
				<th style="width : 120px">작성자</th>
				<th style="width : 700px">내용</th>
				<th style="width : 100px">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qBoardCommentVo" items="${lists.qBoardCommentList }">
				<tr>
					<td>${qBoardCommentVo.memberNickname }</td>
					<td><a href="/calla/qBoard/detail?qBoardId=${qBoardCommentVo.qBoardId}">${qBoardCommentVo.qBoardCommentContent }</a></td>
					<fmt:formatDate value="${qBoardCommentVo.qBoardCommentCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="qBoardCommentCreatedDate"/>
					<td>${qBoardCommentCreatedDate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../footer.jspf"%>	
</body>
</html>