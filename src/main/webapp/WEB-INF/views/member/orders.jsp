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
<title>작성 글</title>
</head>
<body>
	<input type="hidden" id="page" value="1">
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>

			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<input type="hidden" id="memberId" value="${memberId }"> 
				<input type="hidden" id="memberNickname" value="${memberNickname }">
				<table class="table">
					<thead>
						<tr>
							<th style="width: 300px">상품</th>
							<th style="width: 150px">구매일</th>
							<th style="width: 100px">수량</th>
							<th style="width: 100px">단가</th>
							<th style="width: 100px">결제금액</th>
							<th style="width: 100px">상태</th>
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
		$(document).ready(function() {
			readOrder();
			
			function readOrder() {
				console.log("readOrder 호출");
				$('#more').html("");
				$('#close').html("");
				var memberNickname = $('#memberNickname').val();
				var list = "";
				var page = $('#page').val();
				
				$.ajax({
					type : 'GET',
					url : 'orders/' + page,
					success : function(args) {
						$(args.list).each(function(){
						var productOrderCreatedDate = new Date(this.productOrderCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
							list += '<tr>'
								+ '<td><a href="/calla/product/detail?productId=' + this.productId + '">' + this.productName + '</a></td>'
								+ '<td>' + productOrderCreatedDate + '</td>'
								+ '<td>' + this.productAmount + '</td>'
								+ '<td>' + this.productPrice + '</td>'
								+ '<td>' + this.productAmount * this.productPrice  + '</td>'
								+ '<td>배송완료</td>'
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
					} // end success
				}) // end ajax
			} // end readOrderList()
			$(document).on('click', '#btn_more', function(){
				console.log("btn more 호출")
				$('#page').val(+$('#page').val() + 1);
				readOrder();
				
			})// end btn_more.click
			
			$(document).on('click', '#btn_close', function(){
				console.log("btn close 호출")
				$('.table tbody').empty();
				$('#page').val('1');
				readOrder();
			})// end btn_more.click
		}) // end document.ready
	</script>
</body>
</html>