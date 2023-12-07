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
					<h4>
						<br>
						<a href="uproductnickname?memberNickname=${sellerNickname }&page=${pageMaker.criteria.page}"> 
						판매물품 </a> &nbsp;&nbsp;
						<a href="reviewboard?sellerNickname=${sellerNickname }&page=${pageMaker.criteria.page}"> 
						구매후기 </a>
					</h4>
					<input type="hidden" id="selectedOption" value=${option }>
					<hr>
					
					<table class="table table-hover text-center"
						style="border: 1px solid;">
						<thead>
							<tr>
								<th style="width: 700px" class="bg-primary text-white">제목</th>
								<th style="width: 150px" class="bg-primary text-white">작성자</th>
								<th style="width: 120px" class="bg-primary text-white">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vo" items="${list }">
								<tr>
									<td>${vo.uProductReviewTitle }</td>
									<td>${vo.memberNickname }</td>
									<fmt:formatDate value="${vo.uProductReviewCreatedDate }"
										pattern="yyyy.MM.dd." var="uProductReviewCreatedDate" />
									<td>${uProductReviewCreatedDate }</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<ul class="pagination justify-content-center">
						<c:if test="${pageMaker.hasPrev }">
							<li class="text-secondary" style="margin-right: 5px"><a
								href="reviewboard?page=${pageMaker.startPageNo - 1 }">◀</a></li>
						</c:if>

						<c:forEach begin="${pageMaker.startPageNo }"
							end="${pageMaker.endPageNo }" var="num">
							<li class="text-secondary" style="margin-right: 5px"><a
								href="reviewboard?page=${num }&option=${option}&keyword=${keyword}">${num }</a></li>
						</c:forEach>

						<c:if test="${pageMaker.hasNext }">
							<li class="text-secondary" style="margin-right: 5px"><a
								href="reviewboard?page=${pageMaker.endPageNo + 1 }">▶</a></li>
						</c:if>
					</ul>
					<hr>
			
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