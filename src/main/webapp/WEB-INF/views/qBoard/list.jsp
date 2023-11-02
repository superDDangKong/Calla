<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<%@ include file="../header.jspf" %>
</head>
<body>
	
	
	<h1>Q&A 게시판</h1>
	<a href="register"><input type="button" value="글 작성"></a>
	<hr>
	<div>
		<%@ include file="../sidebar.jspf" %>
		<div style="width : 1140px">
			<table>
				<thead>
					<tr>
						<th style="width : 60px">번호</th>
						<th style="width : 700px">제목</th>
						<th style="width : 120px">작성자</th>
						<th style="width : 150px">작성일</th>
						<th style="width : 100px">댓글수</th>
						<th style="width : 100px">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td>${vo.qBoardId }</td>
							<td><a href="detail?qBoardId=${vo.qBoardId }&page=${pageMaker.criteria.page}">${vo.qBoardTitle }</a></td>
							<td>${vo.memberNickname }</td>
							<fmt:formatDate value="${vo.qBoardCreatedDate }"
							pattern="yyyy-MM-dd" var="qBoardCreatedDate"/>
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
	</div>
	<!-- BoardController -> registerPOST()에서 보낸 데이터 저장 -->
	<input type="hidden" id="insertAlert" value="${insert_result }">
	
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success') {
			alert('새 글 작성 성공!');
		}

	</script>
	<%@ include file="../footer.jspf"%>	
</body>
</html>















