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
<title>작성 글</title>
</head>
<body>
	<input type="hidden" id="memberNickname" value=${memberNickname }>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="nav nav-pills nav-justified">
				    <input type="hidden" id="option" value="a">
				    <button class="nav-item nav-link btn-custom read_all">전체</button>
				    <button class="nav-item nav-link btn-custom read_u_product">중고상품</button>
				    <button class="nav-item nav-link btn-custom read_f_board">자유게시판</button>
				    <button class="nav-item nav-link btn-custom read_q_board">문의게시판</button>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th style="width: 150px">게시판</th>
							<th style="width: 300px">제목</th>
							<th style="width: 100px">작성일</th>
							<th style="width: 100px">조회수</th>
							<th style="width: 100px">댓글수</th>
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
			console.log("document ready 호출");
			readBoard();
			
			function readBoard() {
				console.log("readBoard 호출");
				var uProductList = "";
				var fBoardList = "";
				var qBoardList = "";
				var option = $('#option').val();
				var memberNickname = $('#memberNickname').val();
				$('.table tbody').empty(); // 이전 내용을 지웁니다.
				$.ajax({
					type : 'GET',
					url : 'boards/' + memberNickname,
					success : function(lists) {
						console.log(lists)
						$(lists.uProductList).each(function(){
							var uProductCreatedDate = new Date(this.uProductCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							uProductList += '<tr>'
								+ '<td>&lt;중고상품&gt;</td>'
								+ '<td><a href="/calla/uProduct/detail?uProductId=' + this.uProductId + '">' + this.uProductName + '</a></td>'
								+ '<td>' + uProductCreatedDate + '</td>'
								+ '<td>' + this.uProductViews + '</td>'
								+ '<td>' + this.uProductCommentCount + '</td>'
							  	+ '</tr>'  							
						}) // end uProductList.each
						
						$(lists.fBoardList).each(function(){
							var fBoardCreatedDate = new Date(this.fBoardCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							fBoardList += '<tr>'
								+ '<td>&lt;자유게시판&gt;</td>'
								+ '<td><a href="/calla/fBoard/detail?fBoardId=' + this.fBoardId + '">' + this.fBoardTitle + '</a></td>'
								+ '<td>' + fBoardCreatedDate + '</td>'
								+ '<td>' + this.fBoardViews + '</td>'
								+ '<td>' + this.fBoardCommentCount + '</td>'
							  	+ '</tr>'  							
						}) // end fBoardList.each
						
						$(lists.qBoardList).each(function(){
							var qBoardCreatedDate = new Date(this.qBoardCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							qBoardList += '<tr>'
								+ '<td>&lt;문의게시판&gt;</td>'
								+ '<td><a href="/calla/qBoard/detail?qBoardId=' + this.qBoardId + '">' + this.qBoardTitle + '</a></td>'
								+ '<td>' + qBoardCreatedDate + '</td>'
								+ '<td>' + this.qBoardViews + '</td>'
								+ '<td>' + this.qBoardCommentCount + '</td>'
							  	+ '</tr>'  							
						})
						if(option == "a") {
							$('.table tbody').append(uProductList);
							$('.table tbody').append(fBoardList);
							$('.table tbody').append(qBoardList);
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
				console.log('read_all 호출')
				readBoard();				
			}) // end read_all.click
			
			$('.read_u_product').click(function(){
				$('#option').val("u");
				console.log('read_u 호출')
				readBoard();	
			}) // end read_u_product.click

			$('.read_f_board').click(function(){
				$('#option').val("f");
				console.log('read_f 호출')
				readBoard();				
			}) // end read_f_board.click
			
			$('.read_q_board').click(function(){
				$('#option').val("q");
				console.log('read_q 호출')
				readBoard();	
			}) // end read_q_board.click
		}) // end document.ready
	
	
	</script>
	
	
</body>
</html>