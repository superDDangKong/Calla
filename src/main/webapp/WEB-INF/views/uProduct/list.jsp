<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}

ul {
	list-style-type: none;
	text-align: center;
}

li {
	display: inline-block;
	text-align: center;
}
</style>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<script src="https://kit.fontawesome.com/ef717dbcd3.js" crossorigin="anonymous"></script>
<title>메인페이지</title>
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<%@ include file="../header.jspf" %> 
</head>
<body>

	<input type="hidden" id="selectedName" value=${name }>
	<input type="hidden" id="sessionCategori" value=${uProductCategori }>

	<form action="list" method="GET">
		<select id="name" name="name">
			<option value="searchName" selected>전체</option>
			<option value="searchDate">신상품</option>
			<option value="searchlike">좋아요순</option>
		</select>
			
			 <input type="text" name="keyword" value="${keyword }"> <input
			type="submit" value="검색">
			
			<a href="register"><input type="button" value="상품등록"></a>
			
	</form>
	
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<div>

					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
						<li class="nav-item"></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list">전체상품</a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list?name=searchDate&keyword=">신상품</a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list?name=searchName&keyword=장난감">장난감</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/calla/uProduct/list?name=searchName&keyword=야채">야채 </a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list?name=searchName&keyword=의류">의류</a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/map">매물지도</a></li>

					</ul>
					
				</div>

			</div>
		</div>
	</nav>
	<!-- Section-->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

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
									
									<hr>
									
									<span style="color:red;">
									<i class="fa-solid fa-heart fa-sm"></i>
									</span>
									
									<span>
									${vo.uProductLikes } &nbsp;
									</span>
									
									
									
									<span >
									<i class="fa-regular fa-comment"></i>
									</span>
									
									<span>
									${vo.uProductCommentCount }
									</span>
									
									<br>
									<br>
									
									<div>
									${vo.uProductPrice }원
									</div>						
								
									
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">${vo.uProductStatement }</a>
								</div>
							</div>
						</div>
					</div>


				</c:forEach>

			</div>
		</div>

		<ul>
			<c:if test="${pageMaker.hasPrev }">
				<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
			</c:if>

			 <c:if test="${keyword == ''}">
			 
				<c:forEach begin="${pageMaker.startPageNo }"
				end="${pageMaker.endPageNo }" var="num">
				<li><a href="list?page=${num }&name=${name}&keyword=">${num }</a></li>
				</c:forEach>

			</c:if>
			
			 <c:if test="${keyword != ''}">
			 
			<c:forEach begin="${pageMaker.startPageNo }"
				end="${pageMaker.endPageNo }" var="num">
				<li><a href="list?page=${num }&name=${name}&keyword=${keyword}">${num }</a></li>
			</c:forEach>
			
			</c:if>
			
			<c:if test="${pageMaker.hasNext }">
				<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
			</c:if>
		</ul>

	</section>
	<!-- Footer-->
	<%@ include file="footer.jsp"%>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../resources/js/scripts.js"></script>
</body>
</html>