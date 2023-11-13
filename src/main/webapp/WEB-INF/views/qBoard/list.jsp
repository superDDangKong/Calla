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

/* .container {
	display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.table {
	border-collapse: collapse;
    width: 50%;
}

.th, .td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
} */

ul {
	list-style-type : none;
}

li {
	display : inline-block;
}

body {
	margin : 0;
	padding : 0;
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
	<form action="list" method="get">
		<div class="row">
			<div class="col-md-4">
				<select id="option" name="option" class="form-control">
					<option value="searchMemberNickname" selected>작성자</option>
					<option value="searchTitle">제목</option>
				</select>
			</div>
			<div class="col-md-4">
				<input type="text" id="keyword" name="keyword" class="form-control" value="${keyword}" placeholder="검색어를 입력해주세요">
			</div>
			<div class="col-md-4">
			        <button type="submit" class="btn btn-success form-control">검색</button>
			    </div>
		</div>
	</form>
	<div>
		
		<div class="container" style="width : 1140px">
			<table class="table">
				<thead>
					<tr>
						<th class="th" style="width : 60px">번호</th>
						<th class="th" style="width : 700px">제목</th>
						<th class="th" style="width : 120px">작성자</th>
						<th class="th" style="width : 150px">작성일</th>
						<th class="th" style="width : 100px">댓글수</th>
						<th class="th" style="width : 100px">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${list }">
						<tr>
							<td class="td">${vo.qBoardId }</td>
							<td class="td"><a href="detail?qBoardId=${vo.qBoardId }&page=${pageMaker.criteria.page}">${vo.qBoardTitle }</a></td>
							<td class="td">${vo.memberNickname }</td>
							<fmt:formatDate value="${vo.qBoardCreatedDate }"
							pattern="yyyy-MM-dd" var="qBoardCreatedDate"/>
							<td class="td">${qBoardCreatedDate }</td>
							<td class="td">${vo.qBoardCommentCount }</td>
							<td class="td" id="aa">${vo.qBoardViews }</td>
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















