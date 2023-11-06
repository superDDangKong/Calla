<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
<title>좋아요 목록</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<h1>공용상품</h1>
				<table class="table">
					<thead>
						<tr>
							<th style="width: 120px">상품명</th>
							<th style="width: 120px">상품가격</th>
							<th style="width: 200px">상품 카테고리</th>
							<th style="width: 200px">상품 설명</th>
							<th style="width: 200px"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="productVo" items="${lists.productLikesList }">
							<tr>
								<input type="hidden" name="productLikeId" value="${productVo.productLikeId}">
								<td><a
									href="/calla/product/detail?productId=${productVo.productId}&memberId=${memberId}">${productVo.productName }</a></td>
								<td>${productVo.productPrice }</td>
								<td>${productVo.productCategori }</td>
								<td>${productVo.productContent }</td>
								<td><button>좋아요 취소</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<h1>중고상품</h1>
				<table class="table">
					<thead>
						<tr>
							<th style="width: 120px">상품명</th>
							<th style="width: 120px">상품가격</th>
							<th style="width: 200px">상품 카테고리</th>
							<th style="width: 200px">상품 설명</th>
							<th style="width: 200px"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="uProductVo" items="${lists.uProductLikesList }">
							<tr>
								<input type="hidden" name="uProductLikeId" value="${uProductVo.uProductLikeId}">
								<td><a
									href="/calla/uProduct/detail?uProductId=${uProductVo.uProductId}">${uProductVo.uProductName }</a></td>
								<td>${uProductVo.uProductPrice }</td>
								<td>${uProductVo.uProductCategori }</td>
								<td>${uProductVo.uProductContent }</td>
								<td><button>좋아요 취소</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
</body>
</html>