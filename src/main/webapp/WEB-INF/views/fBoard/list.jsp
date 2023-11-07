<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}

ul {
	list-style-type: none;
}

li {
	display: inline-block;
}
</style>
<meta charset="UTF-8">
<title>자유게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container">
					<br>
					<h1 class="text-center">
						<a href="list"
							style="text-decoration: none; color: #007BFF; font-size: 36px; font-weight: bold;">
							자유 게시판 </a>
					</h1>
					<br> <input type="hidden" id="selectedOption" value=${option }>
					<input type="hidden" id="sessionNickname" value=${memberNickname }>


					<hr>
					<br>

					<div id="register">
						<a href="register"><input type="button"
							class="btn btn-dark float-right" value="글 작성"></a>
					</div>
					<table class="table table-hover text-center"
						style="border: 1px solid;">
						<thead>
							<tr>
								<th style="width: 60px" class="bg-primary text-white">번호</th>
								<th style="width: 700px" class="bg-primary text-white">제목</th>
								<th style="width: 150px" class="bg-primary text-white">작성자</th>
								<th style="width: 120px" class="bg-primary text-white">작성일</th>
								<th style="width: 80px" class="bg-primary text-white">댓글수</th>
								<th style="width: 80px" class="bg-primary text-white">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vo" items="${list }">
								<tr>
									<td>${vo.fBoardId }</td>
									<td><a
										href="detail?fBoardId=${vo.fBoardId }&page=${pageMaker.criteria.page}">${vo.fBoardTitle }</a></td>
									<td>${vo.memberNickname }</td>
									<fmt:formatDate value="${vo.fBoardCreatedDate }"
										pattern="yyyy.MM.dd." var="fBoardCreatedDate" />
									<td>${fBoardCreatedDate }</td>
									<td>${vo.fBoardCommentCount }</td>
									<td>${vo.fBoardViews }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<ul class="pagination justify-content-center">
						<c:if test="${pageMaker.hasPrev }">
							<li class="text-secondary" style="margin-right: 5px"><a
								href="list?page=${pageMaker.startPageNo - 1 }">◀</a></li>
						</c:if>

						<c:forEach begin="${pageMaker.startPageNo }"
							end="${pageMaker.endPageNo }" var="num">
							<li class="text-secondary" style="margin-right: 5px"><a
								href="list?page=${num }&option=${option}&keyword=${keyword}">${num }</a></li>
						</c:forEach>

						<c:if test="${pageMaker.hasNext }">
							<li class="text-secondary" style="margin-right: 5px"><a
								href="list?page=${pageMaker.endPageNo + 1 }">▶</a></li>
						</c:if>
					</ul>
					<hr>
					<form action="list" method="GET">
						<div class="row">
							<div class="col-md-4">
								<select id="option" name="option"
									class="form-control custom-select">
									<option value="searchMemberNickname" selected>작성자</option>
									<option value="searchTitleOrContent">제목&내용</option>
								</select>
							</div>
							<div class="col-md-4">
								<input type="text" id="keyword" name="keyword"
									class="form-control" value="${keyword}"
									placeholder="검색어를 입력해주세요">
							</div>
							<div class="col-md-4">
								<button type="submit" class="btn btn-success form-control">검색</button>
							</div>
						</div>
					</form>
					<br>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							if ($("#selectedOption").val() != "") {
								$("#option").val($("#selectedOption").val());
							}

							if ($('#sessionNickname').val() == "") {
								var list = "게시글 작성은 <a href='/calla/member/login?targetURL=/fBoard/register'>로그인</a>이 필요합니다."
								$("#register").html(list);
							}
						}) // end document.ready()
	</script>

	<!-- BoardController -> registerPOST()에서 보낸 데이터 저장 -->
	<input type="hidden" id="insertAlert" value="${insert_result }">
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if (result == 'success') {
			alert('새 글 작성 성공!');
		}
	</script>
	<%@ include file="../footer.jspf"%>
</body>
</html>