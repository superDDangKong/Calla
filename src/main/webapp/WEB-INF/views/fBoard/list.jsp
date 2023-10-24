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
<title>자유게시판</title>
</head>
<body>
	<%@ include file="../header.jspf" %> 	
	<h1>자유 게시판</h1>
	<button id="home"><a href="/calla/">홈</a></button>
	<input type="hidden" id="selectedOption" value=${option }>
	<input type="hidden" id="sessionNickname" value=${memberNickname }>
	
	<div id="register">
		<a href="register"><input type="button" value="글 작성"></a>
	</div>
	
	<form action="list" method="GET">
		<select id="option" name="option">
			<option value="searchMemberNickname" selected>작성자</option>
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
				<th style="width : 700px">제목</th>
				<th style="width : 120px">작성자</th>
				<th style="width : 100px">작성일</th>
				<th style="width : 50px">댓글수</th>
				<th style="width : 50px">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.fBoardId }</td>
					<td><a href="detail?fBoardId=${vo.fBoardId }&page=${pageMaker.criteria.page}">${vo.fBoardTitle }</a></td>
					<td>${vo.memberNickname }</td>
					<fmt:formatDate value="${vo.fBoardCreatedDate }"
					pattern="yyyy-MM-dd HH:mm:ss" var="fBoardCreatedDate"/>
					<td>${fBoardCreatedDate }</td>
					<td>${vo.fBoardCommentCount }</td>
					<td>${vo.fBoardViews }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<ul>
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" 
			var="num">
			<li><a href="list?page=${num }&option=${option}&keyword=${keyword}">${num }</a></li>
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
			
			if($('#sessionNickname').val() == "") {
				var list = 
					"게시글 작성은 로그인이 필요한 기능입니다."
					+ "&nbsp&nbsp"
					+ "<a href='/calla/member/login?targetURL=/fBoard/register'>로그인 하러 가기</a>"
				$("#register").html(list);
			}
		}) // end document.ready()
	</script>
	
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