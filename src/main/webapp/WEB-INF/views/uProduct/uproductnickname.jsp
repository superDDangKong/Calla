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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
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
						<a href="uproductnickname?memberNickname=${vo.memberNickname }&page=${pageMaker.criteria.page}"> 판매물품 </a> &nbsp;&nbsp;
						<a href="reviewboard?sellerNickname=${vo.memberNickname }&page=${pageMaker.criteria.page}"> 구매후기 </a>
						
					</h4>
					<input type="hidden" id="selectedOption" value=${option }>
					<hr>
					
					<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<c:forEach var="vo" items="${list }">

					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top"
								src="display?fileName=${vo.uProductImagePath}" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">

										<a
											href="detail?uProductId=${vo.uProductId }&page=${pageMaker.criteria.page}">${vo.uProductName }</a>

									</h5>
									<!-- Product reviews-->
									<div
										class="d-flex justify-content-center small text-warning mb-2">
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
										<div class="bi-star-fill"></div>
									</div>
									<!-- Product price-->
									${vo.uProductPrice }

								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">Add to
										cart</a>
								</div>
							</div>
						</div>
					</div>


				</c:forEach>

			</div>
		</div>


	</section>

					
					
					
					
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