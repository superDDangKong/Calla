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
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
<title>댓글 목록</title>
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
				    <button class="nav-item nav-link btn-custom read_f_board">자유게시판</button>
				    <button class="nav-item nav-link btn-custom read_q_board">문의게시판</button>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th style="width: 150px">게시판</th>
							<th style="width: 200px">내용</th>
							<th style="width: 150px">작성일</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
	
	<script type="text/javascript">
		$(document).ready(function(){
			readComment();
			
			function readComment() {
				console.log("readComment 호출");
				var productList = "";
				var uProductList = "";
				var fBoardList = "";
				var qBoardList = "";
				var option = $('#option').val();
				var memberNickname = $('#memberNickname').val();
				var memberId = $('#memberId').val();
				$('.table tbody').empty(); // 이전 내용을 지웁니다.
				$.ajax({
					type : 'GET',
					url : 'comments/' + memberNickname,
					success : function(lists) {
						$(lists.productCommentList).each(function(){
							var productCommentCreatedDate = new Date(this.productCommentCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							productList += '<tr>'
								+ '<td>&lt;공용상품&gt;</td>'
								+ '<td><a href="/calla/product/detail?productId=' + this.productId + '&memberId=' + memberId + '">' + this.productCommentContent + '</a></td>'
								+ '<td>' + productCommentCreatedDate + '</td>'
							  	+ '</tr>'  							
						}) // end productList.each
						
						$(lists.uProductCommentList).each(function(){
							var uProductCommentCreatedDate = new Date(this.uProductCommentCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							uProductList += '<tr>'
								+ '<td>&lt;중고상품&gt;</td>'
								+ '<td><a href="/calla/uProduct/detail?uProductId=' + this.uProductId + '">' + this.uProductCommentContent + '</a></td>'
								+ '<td>' + uProductCommentCreatedDate + '</td>'
							  	+ '</tr>'  							
						}) // end uProductList.each
						
						$(lists.fBoardCommentList).each(function(){
							var fBoardCommentCreatedDate = new Date(this.fBoardCommentCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							fBoardList += '<tr>'
								+ '<td>&lt;자유게시판&gt;</td>'
								+ '<td><a href="/calla/fBoard/detail?fBoardId=' + this.fBoardId + '">' + this.fBoardCommentContent + '</a></td>'
								+ '<td>' + fBoardCommentCreatedDate + '</td>'
							  	+ '</tr>'  							
						}) // end fBoardList.each
						
						$(lists.qBoardCommentList).each(function(){
							var qBoardCommentCreatedDate = new Date(this.qBoardCommentCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							qBoardList += '<tr>'
								+ '<td>&lt;문의게시판&gt;</td>'
								+ '<td><a href="/calla/qBoard/detail?qBoardId=' + this.qBoardId + '">' + this.qBoardCommentContent + '</a></td>'
								+ '<td>' + qBoardCommentCreatedDate + '</td>'
							  	+ '</tr>'  							
						}) // end fBoardList.each
						
						if(option == "a") {
							$('.table tbody').append(productList);
							$('.table tbody').append(uProductList);
							$('.table tbody').append(fBoardList);
							$('.table tbody').append(qBoardList);
						} else if (option == "p") {
							$('.table tbody').append(productList);	
						} else if (option == "u") {
							$('.table tbody').append(uProductList);
						} else if (option == "f") {
							$('.table tbody').append(fBoardList);
						} else if (option == "q") {
							$('.table tbody').append(qBoardList);
						}
					}// end success
				}); // end ajax
			} // end readBoard(option)	
			
			$('.read_all').click(function(){
				$('#option').val("a");
				readComment();				
			}) // end read_all.click
			
			$('.read_product').click(function(){
				$('#option').val("p");
				readComment();	
			}) // end read_product.click
			
			$('.read_u_product').click(function(){
				$('#option').val("u");
				readComment();	
			}) // end read_u_product.click

			$('.read_f_board').click(function(){
				$('#option').val("f");
				readComment();				
			}) // end read_f_board.click
			
			$('.read_q_board').click(function(){
				$('#option').val("q");
				readComment();	
			}) // end read_q_board.click
		}) // end document.ready
	
	
	</script>
	
	
</body>
</html>