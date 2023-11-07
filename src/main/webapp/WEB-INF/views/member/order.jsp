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
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>

			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<input type="hidden" id="memberId" value="${memberId }"> <input
					type="hidden" id="memberNickname" value="${memberNickname }">
				<div id="allOrderList">전체</div>
				<div id="prouctOrderList">공용상품</div>
				<div id="uProductOrderList">중고상품</div>
				<div id="orderList"></div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#allOrderList').click(function() {
				getAllOrderList();
			}) // allOrderList.click

			$('#productOrderList').click(function() {
				getProductOrderList();
			}) // productOrderList.click

			$('#uProductOrderList').click(function() {
				getuProductOrderList();
			}) // uProductOrderList.click

			function getAllOrderList() {
				console.log("getAllOrderList() 호출");
				var memberId = $('#memberId').val();
				var memberNickname = $('#memberNickname').val();
				var url = 'member/info/orderList/all/' + memberId;

				$.getJSON(
					url,
					function(data) {
						var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
						$(data.list).each(function() {// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this);
							list += '<div class="comment_item">'
								+ '<input type="hidden" class="fBoardCommentId" value="' + this.fBoardCommentId + '">'
								+ this.memberNickname
								+ '<br>'
								+ '<textarea class="form-control fBoardCommentContent" rows="1" style="border:none;">'
								+ this.fBoardCommentContent
								+ '</textarea>'
								+ fBoardCommentCreatedDate
								+ '<br>'
								+ '<button class="btnCommentUpdate" ' + disabled + '>수정</button>'
								+ '<button class="btnCommentDelete" ' + disabled + '>삭제</button>'
								+ '<button class="btnReply">답글</button>'
								+ '<br>'
								+ '<hr>'
								+ '</div>';
						}); // end each()
					$('#orerList').html(list);
				}); // end getJSON()
			} // end getAllOrderList

			function getProductOrderList() {

			} // end getProductOrderList()

			function getUProductOrderList() {

			} // end getUProductOrderList()
		}) // end document.ready
	</script>
</body>
</html>