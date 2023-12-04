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
	<input type="hidden" id="allBoardPage" value='1'>
	<input type="hidden" id="fBoardPage" value='1'>
	<input type="hidden" id="qBoardPage" value='1'>
	<input type="hidden" id="uProductPage" value='1'>
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
				<div id="more" class="text-center"></div>
				<div id="close" class="text-center"></div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
	
	<script type="text/javascript">
		$(document).ready(function(){
			readBoard();
			
			function readBoard() {
				console.log("readBoard 호출");
				$('#more').html("");
				$('#close').html("");
				var list = "";
				var option = $('#option').val();
				var memberNickname = $('#memberNickname').val();
				var memberId = $('#memberId').val();
				var page = "";
				if (option == "a") {
					page = $('#allBoardPage').val();
					$('#uProductPage').val('1');
					$('#fBoardPage').val('1');
					$('#qBoardPage').val('1');
				} else if(option == "au"){
					page = $('#uProductPage').val();
					$('#allBoardPage').val('1');
					$('#fBoardPage').val('1');
					$('#qBoardPage').val('1');
				} else if (option == "af") {
					page = $('#fBoardPage').val();
					$('#allBoardPage').val('1');
					$('#uProductPage').val('1');
					$('#qBoardPage').val('1');
				} else if (option == "aq") {
					page = $('#qBoardPage').val();
					$('#allBoardPage').val('1');
					$('#uProductPage').val('1');
					$('#fBoardPage').val('1');
				}

				$.ajax({
					type : 'GET',
					url : 'boards/' + memberNickname + '/' + page + '/' + option,
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

							if (this.category=="au") {
								list += '<tr>'	
									+ '<td>&lt;중고상품&gt;</td>'
									+ '<td><a href="/calla/uProduct/detail?uProductId=' + this.uProductId + '">' + this.uProductName + '</a></td>'
								} else if (this.category=="af") {
									list += '<tr>'
										+ '<td>&lt;자유게시판&gt;</td>'
										+ '<td><a href="/calla/fBoard/detail?fBoardId=' + this.uProductId + '">' + this.uProductName + '</a></td>'
								} else if (this.category=="aq") {
									list += '<tr>'
										+ '<td>&lt;문의게시판&gt;</td>'
										+ '<td><a href="/calla/qBoard/detail?qBoardId=' + this.uProductId + '">' + this.uProductName + '</a></td>'
								}
								list += '<td>' + uProductCreatedDate + '</td>'
								+ '<td>' + this.uProductViews + '</td>'
								+ '<td>' + this.uProductCommentCount + '</td>'
							  	+ '</tr>'  							
						}) // end list.each
						
						$('.table tbody').append(list);	
						
						if(args.pageMaker.hasNext) {
							$('#more').html('<button id="btn_more" style="font-size:10px;">더보기<br>↓</button>');
						} else {
							$('#more').html('마지막 입니다.')
						}
						
						if(args.pageMaker.hasPrev) {
							$('#close').html('<button id="btn_close" style="font-size:10px;">접기<br>↑</button>');
						} 
						
					},// end success
					beforeSend: function() {
						console.log('beforeSend 호출')
						$('#btnLogin').html('<div class="loading"></div>');
						$('#btnLogin').attr('disabled',true);
					},
					complete: function() {
						console.log('complete 호출')
						$('#btnLogin').attr('disabled',false);
					}
				}); // end ajax
			} // end readBoard(option)	
			
			$(document).on('click', '#btn_more', function(){
				console.log("btn more 호출")
				var option = $('#option').val();
				if(option == 'a') {
					$('#allBoardPage').val(+$('#allBoardPage').val() + 1);
					readBoard();
				} else if (option == 'au') {
					$('#uProductPage').val(+$('#uProductPage').val() + 1);
					readBoard();
				} else if (option == 'af') {
					$('#fBoardPage').val(+$('#fBoardPage').val() + 1);
					readBoard();
				} else if (option == 'aq') {
					$('#qBoardPage').val(+$('#qBoardPage').val() + 1);
					readBoard();
				}
				
			})// end btn_more.click
			
			$(document).on('click', '#btn_close', function(){
				console.log("btn close 호출")
				$('.table tbody').empty();
				var option = $('#option').val();
				if(option == 'a') {
					$('#allBoardPage').val('1');
					readBoard();
				} else if (option == 'au') {
					$('#uProductPage').val('1');
					readBoard();
				} else if (option == 'af') {
					$('#fBoardPage').val('1');
					readBoard();
				} else if (option == 'aq') {
					$('#qBoardPage').val('1');
					readBoard();
				}

			})// end btn_more.click
			
			$('.read_all').click(function(){
				$('#option').val("a");
				$('.table tbody').empty();
				console.log('read_all 호출')
				readBoard();				
			}) // end read_all.click
			
			$('.read_u_product').click(function(){
				$('#option').val("au");
				$('.table tbody').empty();
				readBoard();	
			}) // end read_u_product.click

			$('.read_f_board').click(function(){
				$('#option').val("af");
				$('.table tbody').empty();
				readBoard();				
			}) // end read_f_board.click
			
			$('.read_q_board').click(function(){
				$('#option').val("aq");
				$('.table tbody').empty();
				readBoard();	
			}) // end read_q_board.click
		}) // end document.ready
	
	</script>
	
	
</body>
</html>