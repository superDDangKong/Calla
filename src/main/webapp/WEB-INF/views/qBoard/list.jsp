<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<title>게시판 메인 페이지</title>
</head>
<body>
	<%@ include file="../header.jspf" %>
	<%@ include file="../sidebar.jspf" %>
	<h1>Q&A 게시판</h1>
	<a href="register"><input type="button" value="글 작성"></a>
	<hr>
	<div>
		<table style="margin: 0 auto;">
			<thead>
				<tr>
					<th style="width : 60px">번호</th>
					<th style="width : 700px">제목</th>
					<th style="width : 120px">작성자</th>
					<th style="width : 100px">작성일</th>
					<th style="width : 60px">댓글수</th>
					<th style="width : 60px">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.qBoardId }</td>
						<td><a href="detail?qBoardId=${vo.qBoardId }&page=${pageMaker.criteria.page}">${vo.qBoardTitle }</a></td>
						<td>${vo.memberNickname }</td>
						<fmt:formatDate value="${vo.qBoardCreatedDate }"
						pattern="yyyy-MM-dd HH:mm:ss" var="qBoardCreatedDate"/>
						<td>${qBoardCreatedDate }</td>
						<td>${vo.qBoardCommentCount }</td>
						<td id="aa">${vo.qBoardViews }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<ul style="text-align:center">
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }"
			var="num">
			<li><a href="list?page=${num }">${num }</a></li>	
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>

	</ul>
	
	<!-- BoardController -> registerPOST()에서 보낸 데이터 저장 -->
	<input type="hidden" id="insertAlert" value="${insert_result }">
	
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success') {
			alert('새 글 작성 성공!');
		}

	</script>
	
</body>
</html>















