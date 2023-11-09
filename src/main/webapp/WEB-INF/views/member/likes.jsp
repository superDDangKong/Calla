<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
<title>좋아요 목록</title>
</head>
<body>
	<input type="hidden" id="memberNickname" value=${memberNickname }>
	<input type="hidden" id="memberId" value=${memberId }>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="nav nav-pills nav-justified">
				    <input type="hidden" id="option" value="a">
				    <button class="nav-item nav-link btn-custom read_all">전체</button>
				    <button class="nav-item nav-link btn-custom read_product">공용상품</button>
				    <button class="nav-item nav-link btn-custom read_u_product">중고상품</button>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th style="width: 200px"></th>
							<th style="width: 200px">게시판</th>
							<th style="width: 120px">제목</th>
							<th style="width: 200px">가격</th>
							<th style="width: 200px">작성일</th>
							<th style="width: 200px">분류</th>
							<th style="width: 200px">조회수</th>
							<th style="width: 200px">좋아요수</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="deleteBtn"></div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
	
	<script type="text/javascript">
		$(document).ready(function(){
			readLike();
			
			function readLike() {
				console.log("readLike 호출");
				var productList = "";
				var uProductList = "";
				var option = $('#option').val();
				var memberNickname = $('#memberNickname').val();
				var memberId = $('#memberId').val();
				$('.table tbody').empty();

				$.ajax({
					type : 'GET',
					url : 'likes/' + memberNickname,
					success : function(lists) {
						$(lists.productLikeList).each(function(){
							var productCreatedDate = new Date(this.productCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							productList +=  
								 '<tr>'
								+ '<td><input type="checkbox" class="check_box"></td>'
								+ '<td>&lt;공용상품&gt;</td>'
								+ '<td><a href="/calla/product/detail?productId=' + this.productId + '&memberId=' + memberId + '">' + this.productName + '</a></td>'
								+ '<td>' + this.productPrice + '</td>'
								+ '<td>' + productCreatedDate + '</td>'
								+ '<td>' + this.productCategori + '</td>'
								+ '<td>' + this.productViews + '</td>'
								+ '<td>' + this.productLikes + '</td>'
								+ '<input type="hidden" class="productId" value=' + this.productId + '>'
								+ '<input type="hidden" class="productLikeId" value=' + this.productLikeId + '>'
							  	+ '</tr>'  							
						}) // end productList.each
						
						$(lists.uProductLikeList).each(function(){
							var uProductCreatedDate = new Date(this.uProductCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							uProductList += 
								 '<tr>'
								+ '<td><input type="checkbox" class="check_box"></td>'
								+ '<td>&lt;중고상품&gt;</td>'
								+ '<td><a href="/calla/uProduct/detail?uProductId=' + this.uProductId + '&memberId=' + memberId + '">' + this.uProductName + '</a></td>'
								+ '<td>' + this.uProductPrice + '</td>'
								+ '<td>' + uProductCreatedDate + '</td>'
								+ '<td>' + this.uProductCategori + '</td>'
								+ '<td>' + this.uProductViews + '</td>'
								+ '<td>' + this.uProductLikes + '</td>'
								+ '<input type="hidden" class="uProductId" value=' + this.uProductId + '>'
								+ '<input type="hidden" class="uProductLikeId" value=' + this.uProductLikeId + '>'
							  	+ '</tr>'  							
						}) // end uProductList.each
						
						
						if(option == "a") {
							$('.table tbody').append(productList);
							$('.table tbody').append(uProductList);
						} else if (option == "p") {
							$('.table tbody').append(productList);	
						} else if (option == "u") {
							$('.table tbody').append(uProductList);
						} 
						$('.deleteBtn').html('<button id="btn_delete" style="font-size:10px; float: right;">좋아요 삭제</button>')
					}// end success
				}); // end ajax
			} // end readLike(option)	
			
			$('.read_all').click(function(){
				$('#option').val("a");
				readLike();				
			}) // end read_all.click
			
			$('.read_product').click(function(){
				$('#option').val("p");
				readLike();	
			}) // end read_product.click
			
			$('.read_u_product').click(function(){
				$('#option').val("u");
				readLike();	
			}) // end read_u_product.click
			
			$(document).on('click', '#btn_delete', function(){
				var checkedCheckboxes = $('.check_box:checked');
				var productIdList = [];
				var uProductIdList = [];
				var productLikeIdList = [];
				var uProductLikeIdList = [];
				// 선택된 체크박스의 값(또는 다른 속성) 가져오기
				checkedCheckboxes.each(function() {
					var productId = $(this).closest('tr').find('.productId').val();
					var uProductId = $(this).closest('tr').find('.uProductId').val();
					var productLikeId = $(this).closest('tr').find('.productLikeId').val();
					var uProductLikeId = $(this).closest('tr').find('.uProductLikeId').val();
					
					if(productLikeId != null){
						productIdList.push(productId);
						productLikeIdList.push(productLikeId);
					} else {
						uProductIdList.push(uProductId);
						uProductLikeIdList.push(uProductLikeId);
					}
				}); // end checkedCheckboxes.each
				var obj = {
						'productIdList' : productIdList,
						'productLikeIdList' : productLikeIdList,
						'uProductIdList' : uProductIdList,
						'uProductLikeIdList' : uProductLikeIdList
					};
				
				$.ajax({
					type : 'DELETE',
					url : 'deleteLikes',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj),
					success : function(result) {
						if(result == 1) {
							console.log('좋아요 삭제 성공')
						}
					}// end success
				}); // end ajax
				
				readLike();
			}) // end btn_delete
		}) // end document.ready
	
	</script>
</body>
</html>