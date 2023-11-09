<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="header.jspf"%>
<title>Calla</title>
</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<c:if test="${not empty memberNickname }">
					<h5>${memberNickname }님 환영합니다!</h5>
					<hr>
					회원님을 위한 추천 상품 리스트 ↓ ↓ ↓
				</c:if>
				<div class="container">
					<h1 class="display-4">공용상품</h1>
				</div>
				<section class="py-5">
					<div class="container px-4 px-lg-5 mt-5">
						<div
							class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
							<input type="hidden" id="test" value='${lists}'>
							<c:forEach var="ProductVO" items="${lists.productList }">
								<div class="col mb-5">
									<div class="card h-100">
										<%-- <img class="card-img-top" src="display?fileName=${ProductVO.productImagePath}" width="200px" height="150px" alt="..." /> --%>
										<div class="card-body p-4">
											<div class="text-center">
												<h5 class="fw-bolder">
													<a href="product/detail?productId=${ProductVO.productId }&memberId=${memberId}">${ProductVO.productName }</a>
												</h5>
												<div>
													<h6>${ProductVO.productCategori }</h6>
												</div>
												<div
													class="d-flex justify-content-center small text-warning mb-2">
													좋아요 : ${ProductVO.productLikes } 조회수 :
													${ProductVO.productViews }</div>
												가격 : ${ProductVO.productPrice }
											</div>
										</div>
										<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
											<div class="text-center">
												<a class="btn btn-outline-dark mt-auto"
													href="product/detail?productId=${ProductVO.productId }&memberId=${memberId}">상품 보기</a>
											</div>
										</div>
									</div>
								</div>

							</c:forEach>
						</div>
					</div>
				</section>

				<div class="container">
					<h1 class="display-4">중고상품</h1>
				</div>

				<section class="py-5">
					<div class="container px-4 px-lg-5 mt-5">
						<div
							class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

							<c:forEach var="uProductVO" items="${lists.uProductList }">

								<div class="col mb-5">
									<div class="card h-100">
										<!-- Product image-->
										<img class="card-img-top"
											src="display?fileName=${uProductVO.uProductImagePath}"
											alt="..." />
										<!-- Product details-->
										<div class="card-body p-4">
											<div class="text-center">
												<!-- Product name-->
												<h5 class="fw-bolder">

													<a href="uProduct/detail?uProductId=${uProductVO.uProductId }">${uProductVO.uProductName }</a>

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
												${uProductVO.uProductPrice }

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
			</main>
			<%@ include file="sidebarRight.jspf"%>
		</div>
	</div>
	<script type="text/javascript">
		// end document.ready
	</script>
	<%@ include file="footer.jspf"%>
</body>
</html>
