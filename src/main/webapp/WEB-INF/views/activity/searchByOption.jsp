<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
    .btn-custom {
        background-color: gray; /* 버튼 배경색: 회색 */
        color: #000000; /* 버튼 텍스트 색상: 검정색 */
        border: 5px solid #000000; /* 테두리: 1px 검정색 실선 */
    }
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
<title>상품 list</title>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}
table {
    table-layout: auto; /* 자동 열 너비 조절을 위해 auto 사용 */
    width: 100%; /* 테이블 너비를 컨테이너의 100%로 설정 */
  }
ul {
	list-style-type: none;
}

li {
	display: inline-block;
}
</style>
</head>
<body>
	<input type="hidden"  id="memberId" value='${memberId}'>
	<input type="hidden"  id="select_option" value='${option}'>
	<input type="hidden"  id="select_category" value='${category}'>
	<input type="hidden"  id="select_keyword" value='${keyword}'>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<section class="py-5">
					<div class="container px-4 px-lg-5 mt-5">
						<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
							<c:forEach var="vo" items="${list }">
								<div class="col mb-5">
									<div class="card h-100">
										<img class="card-img-top" src="display?fileName=${vo.uProductImagePath}" width="280px" height="150px" alt="..." />
											<div class="card-body p-4">
												<div class="text-center">
													<h5 class="fw-bolder">
														<c:if test="${vo.category =='p' }">
														<a href="/calla/product/detail?productId=${vo.uProductId }&memberId=${memberId }&page=${pageMaker.criteria.page}">${vo.uProductName }</a>
														</c:if>
														<c:if test="${vo.category =='u' }">
														<a href="/calla/uProduct/detail?uProductId=${vo.uProductId }&memberId=${memberId }&page=${pageMaker.criteria.page}">${vo.uProductName }</a>
														</c:if>
													</h5>
														<div>										
															<h6>
																${vo.uProductCategori } 
															</h6>
														</div>
													<div class="d-flex justify-content-center small text-warning mb-2">
														좋아요 : ${vo.uProductLikes } 조회수 : ${vo.uProductViews }
													</div>
													가격 : ${vo.uProductPrice } 원
												</div>
											</div>
											<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
												<div class="text-center">
													<c:if test="${vo.category =='p' }">
														<a class="btn btn-outline-dark mt-auto" href="/calla/product/detail?productId=${vo.uProductId }&memberId=${memberId }&page=${pageMaker.criteria.page}">상품 보기</a>
													</c:if>
													<c:if test="${vo.category =='u' }">
														<a class="btn btn-outline-dark mt-auto" href="/calla/uProduct/detail?uProductId=${vo.uProductId }&memberId=${memberId }&page=${pageMaker.criteria.page}">상품 보기</a>
													</c:if>
												</div>
											</div>
									</div>
								</div>
								
							</c:forEach>
						</div>
					</div>
					
					<ul class="pagination justify-content-center">
						<c:if test="${pageMaker.hasPrev }">
							<li class="text-secondary" style="margin-right: 5px"><a href="searchByOption?page=${pageMaker.startPageNo - 1 }&productOption=${option}&keyword=${keyword}&category=${category}">◀</a></li>
						</c:if>
						<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num">
							<li class="text-secondary" style="margin-right: 5px"><a href="searchByOption?page=${num }&productOption=${option}&keyword=${keyword}&category=${category}">${num }</a></li>
						</c:forEach>
						<c:if test="${pageMaker.hasNext }">
							<li class="text-secondary" style="margin-right: 5px"><a href="searchByOption?page=${pageMaker.endPageNo + 1 }&productOption=${option}&keyword=${keyword}&category=${category}">▶</a></li>
						</c:if>
					</ul>
				</section>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
		<script type="text/javascript">
		$(document).ready(function(){
			$('#productOption').val($('#select_option').val());
			$('#category').val($('#select_category').val());
			$('#keyword').val($('#select_keyword').val());
			
		}) // end document.ready
	</script>
</body>
</html>