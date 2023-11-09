<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
ul {
	list-style-type: none;
}

li {
	display: inline-block;
}
</style>
<meta charset="UTF-8">
<title>${vo.fBoardTitle }</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container">
					<br> <a href="list?page=${page }"><input type="button"
						class="btn btn-secondary float-right" value="글 목록"></a> <br>
					<div class="post-detail">
						<br>
						<h2>${vo.fBoardTitle }</h2>

						<p>${vo.memberNickname }</p>
						<fmt:formatDate value="${vo.fBoardCreatedDate }"
							pattern="yyyy.MM.dd. hh:mm" var="fBoardCreatedDate" />
						<p>${fBoardCreatedDate }</p>
						<hr>
						<div>
							<textarea style="width: 100%; height: 500px;" readonly>${vo.fBoardContent }</textarea>
						</div>
						<div>
							<img src="display?fileName=${vo.fBoardImagePath }">
						</div>

						<input type="hidden" id="fBoardId" name="fBoardId"
							value="${vo.fBoardId }">


						<c:set var="memberNickname" value="${memberNickname }" />
						<c:set var="voMemberNickname" value="${vo.memberNickname }" />

						<c:if test="${memberNickname == voMemberNickname}">
							<div class="d-flex">
								<div class="p-2">
									<a href="update?fBoardId=${vo.fBoardId}&page=${page}"
										class="btn btn-primary">글 수정</a>
								</div>
								<div class="p-2">
									<form action="delete" method="POST">
										<input type="hidden" id="fBoardId" name="fBoardId"
											value="${vo.fBoardId}"> <input type="submit"
											value="글 삭제" class="btn btn-danger">
									</form>
								</div>
							</div>
						</c:if>
						<hr>
						<br>
						<c:if test="${memberNickname != null}">
							<div>
								<p>댓글</p>
								<div class="border">
									<br> ${memberNickname}<br> <input type="hidden"
										id="memberNickname" value=${memberNickname }>
									<div class="form-group">
										<textarea id="fBoardCommentContent" class="form-control"
											rows="1" placeholder="댓글 내용을 입력해 주세요" style="border: none;"
											required></textarea>
									</div>
									<div style="text-align: right;">
										<button id="btnCommentAdd" class="btn btn-dark">작성</button>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${memberNickname == null}">
							<br> 
							댓글 작성은 <a
								href="/calla/member/login?targetURL=/fBoard/detail?fBoardId=${vo.fBoardId }">로그인</a>이 필요합니다.
						</c:if>
						<hr>
						<br>
						<div>
							<div id="comments"></div>
						</div>

						<input type="hidden" id="pageMaker_hasPrev"
							value="${pageMaker.hasPrev}"> <input type="hidden"
							id="pageMaker_hasNext" value="${pageMaker.hasNext}"> <input
							type="hidden" id="pageMaker_startPageNo"
							value="${pageMaker.startPageNo}"> <input type="hidden"
							id="pageMaker_endPageNo" value="${pageMaker.endPageNo}">
						<input type="hidden" id="pageMaker_commentPage"
							value="${pageMaker.criteria.page}"> <input type="hidden"
							id="pageMaker_commentNumsPerPage"
							value="${pageMaker.criteria.numsPerPage}"> <br>
					</div>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			getAllComments();
			$('#btnCommentAdd').click(function() {
				var fBoardId = $('#fBoardId').val(); // 게시판 번호 데이터
				var memberNickname = $('#memberNickname').val(); // 작성자 데이터
				var fBoardCommentContent = $('#fBoardCommentContent').val(); // 댓글 내용
				var obj = {
							'fBoardId' : fBoardId,
							'memberNickname' : memberNickname,
							'fBoardCommentContent' : fBoardCommentContent
							};
				console.log(obj);
				$.ajax({
					type : 'POST',
					url : 'comments',
					headers : {
							'Content-Type' : 'application/json'
							},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result) {
										console.log(result);
										if (result == 1) {
											alert('댓글 입력 성공');
											getAllComments();
											}
										}

				}); // end ajax()
			}); // end btnAdd.click()

			// 게시판 댓글 전체 가져오기
			function getAllComments() {
				console.log("getAllComments() 호출");
				var fBoardId = $('#fBoardId').val();
				var commentPage = $('#pageMaker_commentPage').val();
				var commentNumsPerPage = $('#pageMaker_commentNumsPerPage').val();
				var url = 'comments/all/' + fBoardId + '/' + commentPage + '/' + commentNumsPerPage;
					$.getJSON(
							url,
							function(data) {
									var pageMaker_hasPrev = Boolean(data.pageMaker.hasPrev);
									var pageMaker_hasNext = Boolean(data.pageMaker.hasNext);
									$('#pageMaker_startPageNo').val(data.pageMaker.startPageNo);
									$('#pageMaker_endPageNo').val(data.pageMaker.endPageNo);
									var pageMaker_startPageNo = +$('#pageMaker_startPageNo').val();
									var pageMaker_endPageNo = +$('#pageMaker_endPageNo').val();
									var memberNickname = $('#memberNickname').val();
									var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
									
										$(data.list).each(function() {
													console.log(this);
													var fBoardCommentCreatedDate = new Date(this.fBoardCommentCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
													var disabled = 'disabled';
													var readonly = 'readonly';
															if (memberNickname == this.memberNickname) { // 댓글 작성자랑 로그인한 id가 같을때
															disabled = '';
															readonly = '';
															}

														}); // end ajax()
											}); // end btnAdd.click()

							// 게시판 댓글 전체 가져오기
							function getAllComments() {
								console.log("getAllComments() 호출");
								var fBoardId = $('#fBoardId').val();
								var commentPage = $('#pageMaker_commentPage')
										.val();
								var commentNumsPerPage = $(
										'#pageMaker_commentNumsPerPage').val();
								var url = 'comments/all/' + fBoardId + '/'
										+ commentPage + '/'
										+ commentNumsPerPage;
								$
										.getJSON(
												url,
												function(data) {
													var pageMaker_hasPrev = Boolean(data.pageMaker.hasPrev);
													var pageMaker_hasNext = Boolean(data.pageMaker.hasNext);
													$('#pageMaker_startPageNo')
															.val(
																	data.pageMaker.startPageNo);
													$('#pageMaker_endPageNo')
															.val(
																	data.pageMaker.endPageNo);
													var pageMaker_startPageNo = +$(
															'#pageMaker_startPageNo')
															.val();
													var pageMaker_endPageNo = +$(
															'#pageMaker_endPageNo')
															.val();
													var memberNickname = $(
															'#memberNickname')
															.val();
													var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
													$(data.list)
															.each(
																	function() {
																		console
																				.log(this);
																		var fBoardCommentCreatedDate = new Date(
																				this.fBoardCommentCreatedDate);
																		var disabled = 'disabled';
																		var readonly = 'readonly';
																		if (memberNickname == this.memberNickname) { // 댓글 작성자랑 로그인한 id가 같을때
																			disabled = '';
																			readonly = '';
																		}
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
													list += '<div style="text-align: center;">'
															+ '<ul id="comment_page">'

													if (pageMaker_hasPrev) {
														list += '<li><button class="btn_comment_prev">이전</button></li>'
													}
													for (var num = pageMaker_startPageNo; num <= pageMaker_endPageNo; num++) {
														list += '<li><button class="btn_comment_page" value='+num+'>'
																+ num
																+ '</button></li>'
													}

													if (pageMaker_hasNext) {
														list += '<li><button class="btn_comment_next">이후</button></li>'
													}
													list += '</ul>' + '</div>'
													$('#comments').html(list);
												}); // end getJSON()
							} // end getAllReplies()
							/* $('#comment_page').on('click', '.comment_page_num .btn_comment_page', function() { */
							$(document).on(
									'click',
									'.btn_comment_page',
									function() {
										$('#pageMaker_commentPage').val(
												$(this).val());
										getAllComments();
									})// end btn_comment_page.click()

							$(document).on(
									'click',
									'.btn_comment_prev',
									function() {
										$('#pageMaker_commentPage').val(
												+$('#pageMaker_startPageNo')
														.val() - 1);
										getAllComments();
									})// end btn_comment_prev.click()

							$(document).on(
									'click',
									'.btn_comment_next',
									function() {
										$('#pageMaker_commentPage').val(
												+$('#pageMaker_endPageNo')
														.val() + 1);
										getAllComments();
									})// end btn_comment_prev.click()

							// 수정 버튼을 클릭하면 선택된 댓글 수정
							$('#comments')
									.on(
											'click',
											'.comment_item .btnCommentUpdate',
											function() {
												console.log(this);
												// 선택된 댓글의 replyId, replyContent 값을 저장
												// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근
												var fBoardCommentId = $(this)
														.prevAll(
																'.fBoardCommentId')
														.val();
												var fBoardCommentContent = $(
														this)
														.prevAll(
																'.fBoardCommentContent')
														.val();
												console.log("선택된 댓글 번호 : "
														+ fBoardCommentId
														+ ", 댓글 내용 : "
														+ fBoardCommentContent);

												// ajax 요청
												$
														.ajax({
															type : 'PUT',
															url : 'comments/'
																	+ fBoardCommentId,
															headers : {
																'Content-Type' : 'application/json'
															},
															data : fBoardCommentContent,
															success : function(
																	result) {
																console
																		.log(result);
																if (result == 1) {
																	alert('댓글 수정 성공!');
																	getAllComments();
																}
															}
														}) // end ajax()
											}); // end comments.on()

							// 삭제 버튼을 클릭하면 선택된 댓글 삭제
							$('#comments')
									.on(
											'click',
											'.comment_item .btnCommentDelete',
											function() {
												console.log(this);

												var fBoardId = $('#fBoardId')
														.val();
												var fBoardCommentId = $(this)
														.prevAll(
																'.fBoardCommentId')
														.val();
												console.log("선택된 댓글 번호 : "
														+ fBoardCommentId);

												// ajax 요청
												$
														.ajax({
															type : 'DELETE',
															url : 'comments/'
																	+ fBoardCommentId,
															headers : {
																'Content-Type' : 'application/json'
															},
															data : fBoardId,
															success : function(
																	result) {
																console
																		.log(result);
																if (result == 1) {
																	alert('댓글 삭제 성공!');
																	getAllComments();
																}
															}
														}) // end ajax()
											}); // end comments.on()

							$('#comments')
									.on(
											'click',
											'.comment_item .btnReply',
											function() {
												if ($('#memberNickname').val() == null) {
													alert('답글을 작성하려면 로그인 해 주세요')
													return;
												}
												console.log(this);
												var fBoardCommentId = $(this)
														.closest(
																'.comment_item')
														.find(
																'.fBoardCommentId');
												console.log(fBoardCommentId);
												getAllReplies(fBoardCommentId);

											}); // end btnReply.click()

							function getAllReplies(fBoardCommentId) {
								$('.replies').html('');
								console.log("getAllReplies() 호출");
								console.log("getAllReplies() 호출"
										+ fBoardCommentId.val());
								var url = 'replies/all/'
										+ fBoardCommentId.val();
								var comment_item = fBoardCommentId
										.closest('.comment_item');
								$
										.getJSON(
												url,
												function(data) {
													// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
													// getJSON()에서 json 데이터는
													// javascript object로 자동 parsing됨.
													console.log(data);

													var memberNickname = $(
															'#memberNickname')
															.val();
													var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수

													// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
													$(data)
															.each(
																	function() {
																		// this : 컬렉션의 각 인덱스 데이터를 의미
																		console
																				.log(this);

																		var fBoardReplyCreatedDate = new Date(this.fBoardReplyCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });
																		var disabled = 'disabled';
																		var readonly = 'readonly';

																		if (memberNickname == this.memberNickname) { // 댓글 작성자랑 로그인한 id가 같을때
																			console
																					.log("nickname 일치")
																			disabled = '';
																			readonly = '';
																		}

																		list += '<div class="reply_item bg-light border">'
																				+ '<pre>'
																				+ '<input type="hidden" class="fBoardReplyId" value="' + this.fBoardReplyId + '">'
																				+ 'ㄴ  '
																				+ this.memberNickname
																				+ '<br>'
																				+ '&nbsp&nbsp'
																				+ '<textarea class="fBoardReplyContent form-control bg-light" rows="1" style="border:none;">'
																				+ '&nbsp&nbsp'
																				+ this.fBoardReplyContent
																				+ '</textarea>'
																				+ '<br>'
																				+ '&nbsp&nbsp&nbsp&nbsp'
																				+ fBoardReplyCreatedDate
																				+ '<br>'
																				+ '&nbsp&nbsp&nbsp&nbsp'
																				+ '<button class="btnReplyUpdate" ' + disabled + '>수정</button>'
																				+ '&nbsp'
																				+ '<button class="btnReplyDelete" ' + disabled + '>삭제</button>'
																				+ '<br>'
																				+ '</pre>'
																				+ '</div>';
																	}); // end each()

													list += memberNickname
															+ '<br>'
															+ '<div class="form-group bg-transparent border">'
															+ '<textarea class="fBoardReplyContent form-control" rows="1" placeholder="답글 내용을 입력해 주세요." style="border:none;" required>'
															+ '</textarea> </div>'
															+ '<div style="text-align:right;">'
															+ '<button class="btnReplyAdd btn btn-dark">작성</button>'
															+ '</div>' + '<hr>'
															+ '<br>'

													comment_item
															.append('<div class="replies bg-light">'
																	+ list
																	+ '</div>');
												}); // end getJSON()
							} // end getAllReplies()

							$(document)
									.on(
											'click',
											'.btnReplyAdd',
											function() {
												console.log(this);
												var commentItem = $(this)
														.closest(
																'.comment_item');
												var fBoardCommentId = commentItem
														.find('.fBoardCommentId');
												var fBoardCommentIdVal = fBoardCommentId
														.val();
												var memberNickname = $(
														'#memberNickname')
														.val();
												var fBoardReplyContent = commentItem
														.find(
																'.fBoardReplyContent')
														.val();
												console.log(fBoardReplyContent);

												var obj = {
													'fBoardCommentId' : fBoardCommentIdVal,
													'memberNickname' : memberNickname,
													'fBoardReplyContent' : fBoardReplyContent
												};
												console.log(obj);

												// $.ajax로 송수신
												$
														.ajax({
															type : 'POST',
															url : 'replies',
															headers : {
																'Content-Type' : 'application/json'
															},
															data : JSON.stringify(obj), // JSON으로 변환
															success : function(
																	result) {
																console
																		.log(result);
																if (result == 1) {
																	alert('답글 입력 성공');
																	getAllReplies(fBoardCommentId);
																}
															}
														}); // end ajax()
											}); // end btnReplyAdd.click()

							$(document)
									.on(
											'click',
											'.btnReplyUpdate',
											function() {
												console.log(this);
												var commentItem = $(this)
														.parent()
														.closest(
																'.comment_item');
												var fBoardCommentId = commentItem
														.find('.fBoardCommentId');
												var fBoardReplyId = $(this)
														.prevAll(
																'.fBoardReplyId')
														.val();
												var fBoardReplyContent = $(this)
														.prevAll(
																'.fBoardReplyContent')
														.val();
												console.log("선택된 답글 번호 : "
														+ fBoardReplyId
														+ ", 답글 내용 : "
														+ fBoardReplyContent);

												// ajax 요청
												$
														.ajax({
															type : 'PUT',
															url : 'replies/'
																	+ fBoardReplyId,
															headers : {
																'Content-Type' : 'application/json'
															},
															data : fBoardReplyContent,
															success : function(
																	result) {
																console
																		.log(result);
																if (result == 1) {
																	alert('답글 수정 성공!');
																	getAllReplies(fBoardCommentId);
																}
															}
														}) // end ajax()
											}); // end btnReplyUpdate.on()

							$(document)
									.on(
											'click',
											'.btnReplyDelete',
											function() {
												console.log(this);
												var commentItem = $(this)
														.closest(
																'.comment_item');
												var fBoardCommentId = $(this)
														.closest(
																'.comment_item')
														.find(
																'.fBoardCommentId');
												var fBoardReplyId = $(this)
														.prevAll(
																'.fBoardReplyId')
														.val();

												console.log("선택된 댓글 번호 : "
														+ fBoardReplyId);

												// ajax 요청
												$
														.ajax({
															type : 'DELETE',
															url : 'replies/'
																	+ fBoardReplyId,
															headers : {
																'Content-Type' : 'application/json'
															},
															success : function(
																	result) {
																console
																		.log(result);
																if (result == 1) {
																	alert('답글 삭제 성공!');
																	getAllReplies(fBoardCommentId);
																}
															}
														}) // end ajax()
											}); // end btnReplyDelete.on()
						}); // end document
	</script>
	<%@ include file="../footer.jspf"%>
</body>
</html>

