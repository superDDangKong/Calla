
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
ul {
	list-style-type : none;
}

li {
	display : inline-block;
}
</style>
<meta charset="UTF-8">
<title>${vo.fBoardTitle }</title>
</head>
<body>
	<%@ include file="../header.jspf" %> 	
	<h2>글 보기</h2>
	<div>
		<p>글 번호 : ${vo.fBoardId }</p>
	</div>
	<div>
		<p>제목 : </p> 
		<p>${vo.fBoardTitle } </p>
	</div>
	<div>
		<p>작성자 : ${vo.memberNickname }</p>
		<p>작성일 : ${vo.fBoardCreatedDate }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.fBoardContent }</textarea>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="글 목록"></a>
	<input type="hidden" id="fBoardId" name="fBoardId" value="${vo.fBoardId }">
	
	
	<c:set var="memberNickname" value="${memberNickname }" />
	<c:set var="voMemberNickname" value="${vo.memberNickname }" />
	
	<c:if test="${memberNickname == voMemberNickname}">
	<a href="update?fBoardId=${vo.fBoardId }&page=${page }"><input type="button" value="글 수정"></a>
	
	<form action="delete" method="POST">
		<input type="hidden" id="fBoardId" name="fBoardId" value="${vo.fBoardId }">
		<input type="submit" value="글 삭제">
	</form>
	</c:if>
	
	<c:if test="${memberNickname != null}">
	<div style="text-align: center;">
		${memberNickname}
		<input type="hidden" id="memberNickname" value=${memberNickname }>
		<input type="text" id="fBoardCommentContent" required>
		<button id="btnCommentAdd">작성</button> 
	</div>
	</c:if>
	<c:if test="${memberNickname == null}">
		<br> 댓글을 작성하려면 로그인해 주세요.
	</c:if>
	<hr>
	
	<div style="text-align: center;">
		<div id="comments"></div>
	</div>
	
	<input type="hidden" id="pageMaker_hasPrev" value="${pageMaker.hasPrev}">
	<input type="hidden" id="pageMaker_hasNext" value="${pageMaker.hasNext}">
	<input type="hidden" id="pageMaker_startPageNo" value="${pageMaker.startPageNo}">
	<input type="hidden" id="pageMaker_endPageNo" value="${pageMaker.endPageNo}">
	<input type="hidden" id="pageMaker_commentPage" value="${pageMaker.criteria.page}">
	<input type="hidden" id="pageMaker_commentNumsPerPage" value="${pageMaker.criteria.numsPerPage}">
	<div>
		<br><br><br><br><br><br><br><br><br><br>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			getAllComments();
			$('#btnCommentAdd').click(function(){
				var fBoardId = $('#fBoardId').val(); // 게시판 번호 데이터
				var memberNickname = $('#memberNickname').val(); // 작성자 데이터
				var fBoardCommentContent = $('#fBoardCommentContent').val(); // 댓글 내용
				var obj = {
						'fBoardId' : fBoardId, 
						'memberNickname' : memberNickname,
						'fBoardCommentContent' : fBoardCommentContent
						
				};
				console.log(obj);
				
				// $.ajax로 송수신
				$.ajax({
					type : 'POST',
					url : 'comments',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result) {
						console.log(result);
						if(result == 1) {
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
				console.log("page = " + commentPage);
				var commentNumsPerPage = $('#pageMaker_commentNumsPerPage').val();
				var url = 'comments/all/' + fBoardId + '/' + commentPage + '/' + commentNumsPerPage;
				
				$.getJSON(
					url,
					function(data) {
						// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
						// getJSON()에서 json 데이터는
						// javascript object로 자동 parsing됨.
						var pageMaker_hasPrev = Boolean(data.pageMaker.hasPrev);
						var pageMaker_hasNext = Boolean(data.pageMaker.hasNext);
						$('#pageMaker_startPageNo').val(data.pageMaker.startPageNo);
						$('#pageMaker_endPageNo').val(data.pageMaker.endPageNo);
						var pageMaker_startPageNo = +$('#pageMaker_startPageNo').val();
						var pageMaker_endPageNo = +$('#pageMaker_endPageNo').val(); 
						
						var memberNickname = $('#memberNickname').val();
						var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
						
						// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
						$(data.list).each(function(){
							// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this);
							
							var fBoardCommentCreatedDate = new Date(this.fBoardCommentCreatedDate);
							var disabled = 'disabled';
							var readonly = 'readonly';
							
							if(memberNickname == this.memberNickname) { // 댓글 작성자랑 로그인한 id가 같을때
								disabled = '';
								readonly = '';
							}
							
							list += '<div class="comment_item">'
								+ '<pre>'
								+ '<input type="hidden" class="fBoardCommentId" value="' + this.fBoardCommentId + '">'
								+ this.memberNickname
								+ '&nbsp;&nbsp;' // 공백
								+ '<input type="text" class="fBoardCommentContent" value="' + this.fBoardCommentContent + '" required>'	 
								+ '&nbsp;&nbsp;' // 공백
								+ fBoardCommentCreatedDate
								+ '&nbsp;&nbsp;' // 공백
								+ '<button class="btnCommentUpdate" ' + disabled + '>수정</button>'
								+ '<button class="btnCommentDelete" ' + disabled + '>삭제</button>'
								+ '<button class="btnReply">답글</button>'
								+ '<br>'
								+ '</pre>'
								+ '</div>';
								
						}); // end each()
						list += '<ul id="comment_page">'
						
						if(pageMaker_hasPrev) {
							list += '<li><button class="btn_comment_prev">이전</button></li>'
							}
							
						for(var num = pageMaker_startPageNo; num <= pageMaker_endPageNo; num++) {
							list += '<li><button class="btn_comment_page" value='+num+'>'+num+'</button></li>'
							
							}		
						
						if(pageMaker_hasNext) {
							list += '<li><button class="btn_comment_next">이후</button></li>'
							}
						
						list += '</ul>'
							
						$('#comments').html(list);
					}
				); // end getJSON()
			} // end getAllReplies()
			/* $('#comment_page').on('click', '.comment_page_num .btn_comment_page', function() { */
			$(document).on('click', '.btn_comment_page', function(){				
				$('#pageMaker_commentPage').val($(this).val());
				getAllComments();
			})// end btn_comment_page.click()
			
			$(document).on('click', '.btn_comment_prev', function(){				
				$('#pageMaker_commentPage').val(+$('#pageMaker_startPageNo').val() - 1);
				getAllComments();
			})// end btn_comment_prev.click()
			
			$(document).on('click', '.btn_comment_next', function(){				
				$('#pageMaker_commentPage').val(+$('#pageMaker_endPageNo').val() + 1);
				getAllComments();
			})// end btn_comment_prev.click()
			
			// 수정 버튼을 클릭하면 선택된 댓글 수정
			$('#comments').on('click', '.comment_item .btnCommentUpdate', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId, replyContent 값을 저장
				// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근
				var fBoardCommentId = $(this).prevAll('.fBoardCommentId').val();
				var fBoardCommentContent = $(this).prevAll('.fBoardCommentContent').val();
				console.log("선택된 댓글 번호 : " + fBoardCommentId + ", 댓글 내용 : " + fBoardCommentContent);	
				
				// ajax 요청
				$.ajax({
					type : 'PUT',
					url : 'comments/' + fBoardCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : fBoardCommentContent,
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('댓글 수정 성공!');
							getAllComments();
						}
					}
				}) // end ajax()
			}); // end comments.on()
			
			
			// 삭제 버튼을 클릭하면 선택된 댓글 삭제
			$('#comments').on('click', '.comment_item .btnCommentDelete', function(){
				console.log(this);
				
				var fBoardId = $('#fBoardId').val();
				var fBoardCommentId = $(this).prevAll('.fBoardCommentId').val();
				console.log("선택된 댓글 번호 : " + fBoardCommentId);	
				
				// ajax 요청
				$.ajax({
					type : 'DELETE',
					url : 'comments/' + fBoardCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : fBoardId,
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('댓글 삭제 성공!');
							getAllComments();
						}
					}
				}) // end ajax()
			}); // end comments.on()
			
			$('#comments').on('click', '.comment_item .btnReply', function(){
				if($('#memberNickname').val() == null) {
					alert('답글을 작성하려면 로그인 해 주세요')
					return;
				}
				console.log(this);
				var fBoardCommentId = $(this).closest('.comment_item').find('.fBoardCommentId');
				getAllReplies(fBoardCommentId);
				
			}); // end btnReply.click()
			
			function getAllReplies(fBoardCommentId) {
				$('.replies').html('');
				console.log("getAllReplies() 호출");
				var url = 'replies/all/' + fBoardCommentId.val();
				var comment_item = fBoardCommentId.closest('.comment_item');
				$.getJSON(
					url,
					function(data) {
						// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
						// getJSON()에서 json 데이터는
						// javascript object로 자동 parsing됨.
						console.log(data);
						
						var memberNickname = $('#memberNickname').val();
						var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
						
						// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
						$(data).each(function(){
							// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this);
							
							var fBoardReplyCreatedDate = new Date(this.fBoardReplyCreatedDate);
							var disabled = 'disabled';
							var readonly = 'readonly';
							
							if(memberNickname == this.memberNickname) { // 댓글 작성자랑 로그인한 id가 같을때
								console.log("nickname 일치")
								disabled = '';
								readonly = '';
							}
							
							list += '<div class="reply_item">'
								+ '<pre>'
								+ '<input type="hidden" class="fBoardReplyId" value="' + this.fBoardReplyId + '">'
								+ this.memberNickname
								+ '&nbsp;&nbsp;' // 공백
								+ '<input type="text" class="fBoardReplyContent" value="' + this.fBoardReplyContent + '" required>'	 
								+ '&nbsp;&nbsp;' // 공백
								+ fBoardReplyCreatedDate
								+ '&nbsp;&nbsp;' // 공백
								+ '<button class="btnReplyUpdate" ' + disabled + '>수정</button>'
								+ '<button class="btnReplyDelete" ' + disabled + '>삭제</button>'
								+ '<br>'
								+ '</pre>'
								+ '</div>';
						}); // end each()
						
						list +=  '<div style="text-align: center;">'
							+ memberNickname
							+ '&nbsp;&nbsp;'
							+ '<input type="text" class="fBoardReplyContent" required>'
							+ '&nbsp;&nbsp;'
							+ '<button class="btnReplyAdd">작성</button>' 
							+ '</div>'
						comment_item.append('<div class="replies">' + list + '</div>');	
					}
				); // end getJSON()
			} // end getAllReplies()
			$(document).on('click', '.btnReplyAdd', function(){
				console.log(this);
				var commentItem = $(this).closest('.comment_item');
				var fBoardCommentId = $(this).closest('.comment_item').find('.fBoardCommentId');
				var fBoardCommentIdVal = $(this).closest('.comment_item').find('.fBoardCommentId').val();
			    var memberNickname = $('#memberNickname').val();
			    var fBoardReplyContent = $(this).prevAll('.fBoardReplyContent').val();


				var obj = {
						'fBoardCommentId' : fBoardCommentIdVal, 
						'memberNickname' : memberNickname,
						'fBoardReplyContent' : fBoardReplyContent
				};
				console.log(obj);
				
				// $.ajax로 송수신
				$.ajax({
					type : 'POST',
					url : 'replies',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('답글 입력 성공');
							getAllReplies(fBoardCommentId);
						}
					}
				}); // end ajax()
			}); // end btnReplyAdd.click()
			
			$(document).on('click', '.btnReplyUpdate', function(){
				console.log(this);
				var commentItem = $(this).closest('.comment_item');
				var fBoardCommentId = $(this).closest('.comment_item').find('.fBoardCommentId');
				var fBoardReplyId = $(this).prevAll('.fBoardReplyId').val();
				var fBoardReplyContent = $(this).prevAll('.fBoardReplyContent').val();
				console.log("선택된 답글 번호 : " + fBoardReplyId + ", 답글 내용 : " + fBoardReplyContent);	
				
				// ajax 요청
				$.ajax({
					type : 'PUT',
					url : 'replies/' + fBoardReplyId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : fBoardReplyContent,
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('답글 수정 성공!');
							getAllReplies(fBoardCommentId);
						}
					}
				}) // end ajax()
			}); // end btnReplyUpdate.on()
				
			$(document).on('click', '.btnReplyDelete', function(){
				console.log(this);
				var commentItem = $(this).closest('.comment_item');
				var fBoardCommentId = $(this).closest('.comment_item').find('.fBoardCommentId');
				var fBoardReplyId = $(this).prevAll('.fBoardReplyId').val();
				
				console.log("선택된 댓글 번호 : " + fBoardReplyId);	
				
				// ajax 요청
				$.ajax({
					type : 'DELETE',
					url : 'replies/' + fBoardReplyId,
					headers : {
						'Content-Type' : 'application/json'
					},
					success : function(result) {
						console.log(result);
						if(result == 1) {
							alert('답글 삭제 성공!');
							getAllReplies(fBoardCommentId);
						}
					}
				}) // end ajax()
			}); // end btnReplyDelete.on()
		}); // end document
	</script>
	
</body>
</html>

