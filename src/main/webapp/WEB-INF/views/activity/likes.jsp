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
	<input type="hidden" id="allPage" value='1'>
	<input type="hidden" id="productPage" value='1'>
	<input type="hidden" id="uProductPage" value='1'>
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
				<div id="more" class="text-center"></div>
				<div id="close" class="text-center"></div>
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
				$('#more').html("");
				$('#close').html("");
				var list = "";
				var option = $('#option').val();
				var memberId = $('#memberId').val();
				var page = "";
				
				if (option == "a") {
					page = $('#allPage').val();
					$('#productPage').val('1');
					$('#uProductPage').val('1');
				} else if (option=="ap") {
					page = $('#productPage').val();
					$('#allPage').val('1');
					$('#uProductPage').val('1');
				} else if(option == "au"){
					page = $('#uProductPage').val();
					$('#allPage').val('1');
					$('#productPage').val('1');
				} 
				$.ajax({
					type : 'GET',
					url : 'likes/' + page + '/' + option,
					beforeSend: function() {
						$('#loadingContainer').remove();
						
						var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
						$('body').append(loadingContainer);
						$('#loadingContainer').css('display','block');
					},
					complete: function() {
						$('#loadingContainer').css('display','none');	
					},
					success : function(args) {
						$(args.list).each(function(){
							var uProductCreatedDate = new Date(this.uProductCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							if (this.category == 'ap') {
								list += '<tr>'
									+ '<td><input type="checkbox" class="check_box"></td>'
									+ '<td>&lt;공용상품&gt;</td>'
									+ '<td><a href="/calla/product/detail?productId=' + this.uProductId + '&memberId=' + memberId + '">' + this.uProductName + '</a></td>'
									+ '<td>' + this.uProductPrice + '</td>'
									+ '<td>' + uProductCreatedDate + '</td>'
									+ '<td>' + this.uProductCategori + '</td>'
									+ '<td>' + this.uProductViews + '</td>'
									+ '<td>' + this.uProductLikes + '</td>'
									+ '<input type="hidden" class="productId" value=' + this.uProductId + '>'
									+ '<input type="hidden" class="productLikeId" value=' + this.uProductLikeId + '>'
								  	+ '</tr>'  							
							} else if (this.category =='au') {
								list += '<tr>'
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
							}
							
						}) // end productList.each
						$('.table tbody').append(list);	
						
						if(args.pageMaker.hasNext) {
							$('#more').html('<button id="btn_more" style="font-size:10px;">더보기<br>↓</button>');
						} else {
							$('#more').html('마지막 입니다.')
						}
						
						if(args.pageMaker.hasPrev) {
							$('#close').html('<button id="btn_close" style="font-size:10px;">접기<br>↑</button>');
						} 
						
						$('.deleteBtn').html('<button id="btn_delete" style="font-size:10px; float: right;">좋아요 삭제</button>')
					}// end success
				}); // end ajax
			} // end readLike(option)	
			$(document).on('click', '#btn_more', function(){
				console.log("btn more 호출")
				var option = $('#option').val();
				if(option == 'a') {
					$('#allPage').val(+$('#allPage').val() + 1);
					readLike();
				} else if (option =='ap') {
					$('#productPage').val(+$('#productPage').val() + 1);
					readLike();
				} else if (option == 'au') {
					$('#uProductPage').val(+$('#uProductPage').val() + 1);
					readLike();
				}				
			})// end btn_more.click
			
			$(document).on('click', '#btn_close', function(){
				console.log("btn close 호출")
				$('.table tbody').empty();
				var option = $('#option').val();
				if(option == 'a') {
					$('#allPage').val('1');
					readLike();
				} else if (option =='ap') {
					$('#productPage').val('1');
					readLike();
				} else if (option == 'au') {
					$('#uProductPage').val('1');
					readLike();
				}			

			})// end btn_more.click
			
			$('.read_all').click(function(){
				$('#option').val("a");
				$('.table tbody').empty();
				readLike();				
			}) // end read_all.click
			
			$('.read_product').click(function(){
				$('#option').val("ap");
				$('.table tbody').empty();
				readLike();	
			}) // end read_product.click
			
			$('.read_u_product').click(function(){
				$('#option').val("au");
				$('.table tbody').empty();
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
					beforeSend: function() {
						$('#loadingContainer').remove();
						
						var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
						$('body').append(loadingContainer);
						$('#loadingContainer').css('display','block');
					},
					complete: function() {
						$('#loadingContainer').css('display','none');	
					},
					success : function(result) {
						console.log(result)
						if(result == 1) {
							console.log('좋아요 삭제 성공')
							$('.table tbody').empty();
							$('#allPage').val('1');
							$('#productPage').val('1');
							$('#uProductPage').val('1');
							readLike();
						}
					}// end success
				}); // end ajax
				
			}) // end btn_delete
		}) // end document.ready
	
	</script>
</body>
</html>