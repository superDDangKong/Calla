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
<title>작성 글</title>
</head>
<body>
<%@ include file="../header.jspf" %> 

	
<h1>중고상품</h1>
	<table>
		<thead>
			<tr>
				<th style="width : 120px">상품명</th>
				<th style="width : 700px">가격</th>
				<th style="width : 100px">작성일</th>
				<th style="width : 100px">조회수</th>
				<th style="width : 100px">댓글수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="uProductVo" items="${lists.uProductList }">
				<tr>
					<td><a href="/calla/uProduct/detail?uProductId=${uProductVo.uProductId}">${uProductVo.uProductName }</a></td>
					<td>${uProductVo.uProductPrice }</td>
					<fmt:formatDate value="${uProductVo.uProductCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="uProductCreatedDate"/>
					<td>${uProductCreatedDate }</td>
					<td>${uProductVo.uProductViews }</td>
					<td>${uProductVo.uProductCommentCount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<h1>자유게시판</h1>
	<table>
		<thead>
			<tr>
				<th style="width : 120px">제목</th>
				<th style="width : 700px">내용</th>
				<th style="width : 100px">작성일</th>
				<th style="width : 100px">조회수</th>
				<th style="width : 100px">댓글수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="fBoardVo" items="${lists.fBoardList }">
				<tr>
					<td><a href="/calla/fBoard/detail?fBoardId=${fBoardVo.fBoardId}">${fBoardVo.fBoardTitle }</a></td>
					<td>${fBoardVo.fBoardContent }</td>
					<fmt:formatDate value="${fBoardVo.fBoardCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="fBoardCreatedDate"/>
					<td>${fBoardCreatedDate }</td>
					<td>${fBoardVo.fBoardViews }</td>
					<td>${fBoardVo.fBoardCommentCount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<h1>Q&A게시판</h1>
	<table>
		<thead>
			<tr>
				<th style="width : 120px">제목</th>
				<th style="width : 700px">내용</th>
				<th style="width : 100px">작성일</th>
				<th style="width : 100px">조회수</th>
				<th style="width : 100px">댓글수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="qBoardVo" items="${lists.qBoardList }">
				<tr>
					<td><a href="/calla/qBoard/detail?qBoardId=${qBoardVo.qBoardId}">${qBoardVo.qBoardTitle }</a></td>
					<td>${qBoardVo.qBoardContent }</td>
					<fmt:formatDate value="${qBoardVo.qBoardCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="qBoardCreatedDate"/>
					<td>${qBoardCreatedDate }</td>
					<td>${qBoardVo.qBoardViews }</td>
					<td>${qBoardVo.qBoardCommentCount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="../footer.jspf"%>	
</body>
</html>