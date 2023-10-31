<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
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
<title>${vo.productName }</title>
</head>
<body>
	<%@ include file="../header.jspf" %>
	<h2>상품 보기</h2>
	<div>
		<p>상품 번호 : ${vo.productId }</p>
	</div>
	<div>
		<p>상품 이름 : ${vo.productName }</p>
	</div>
	<div>
		<p>상품 이미지 : </p>
		<img src="display?fileName=${vo.productImagePath}" width="100px" height="100px">
	</div>
	<div>
		<p>작성일 : ${vo.productCreatedDate }</p>
		<p>카테고리 : ${vo.productCategori }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.productContent }</textarea>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
	<input type="hidden" id="productId" name="productId" value="${vo.productId }" >
	
	<c:set var="memberNickname" value="${memberNickname }" />
	<c:set var="voMemberNickname" value="${vo.memberNickname }" />
	<c:set var="memberLevel" value="${memberLevel }" />
	<c:set var="voMemberLevel" value="${vo.memberLevel }" />
	
	<c:if test="${memberLevel > 1}">
	<a href="update?productId=${vo.productId }&page=${page }"><input type="button" value="상품 수정"></a>
	<form action="delete" method="POST">
		<input type="hidden" id="productId" name="productId" value="${vo.productId }">
		<input type="submit" value="상품 삭제">
	</form>
	</c:if>
	<c:if test="${memberNickname != null}">
	<div style="text-align: center;">
		${memberNickname}
		<input type="hidden" id="memberNickname" value=${memberNickname }>
		<input type="text" id="productCommentContent" required>
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
	<hr>
	
	<input type="hidden" id="pageMaker_hasPrev" value="${pageMaker.hasPrev}">
	<input type="hidden" id="pageMaker_hasNext" value="${pageMaker.hasNext}">
	<input type="hidden" id="pageMaker_startPageNo" value="${pageMaker.startPageNo}">
	<input type="hidden" id="pageMaker_endPageNo" value="${pageMaker.endPageNo}">
	<input type="hidden" id="pageMaker_commentPage" value="${pageMaker.criteria.page}">
	<input type="hidden" id="pageMaker_commentNumsPerPage" value="${pageMaker.criteria.numsPerPage}">
	<div>
		<br>
	</div>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			getAllComments();
			$('#btnCommentAdd').click(function(){
				var productId = $('#productId').val(); // 상품 번호 데이터
				var memberNickname = $('#memberNickname').val(); // 닉네임 데이터
				var productCommentContent = $('#productCommentContent').val(); // 댓글 내용
				var obj = {
						'productId' : productId,
						'memberNickname' : memberNickname,
						'productCommentContent' : productCommentContent
				}
				console.log(obj);
				
				// $.ajax로 송수신
				$.ajax({
					type : 'POST',
					url : 'comments',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result){
						console.log(result);
						if(result == 1){
							console.log('댓글 입력 성공');
							alert('댓글 입력 성공');
							getAllComments();
							console.log("getAllcomments")
						}
					}
				});
			}); // end btnAdd
			
			// 댓글 전체 가져오기
			function getAllComments(){
				console.log("getAllComments() 호출");
				var productId = $('#productId').val();
				var commentPage = $('#pageMaker_commentPage').val();
				console.log("page = " + commentPage);
				var commentNumsPerPage = $('#pageMaker_commentNumsPerPage').val();
				var url = 'comments/all/' + productId + '/' + commentPage + '/' + commentNumsPerPage;
				
				$.getJSON(
						url,
						function(data){
							
							var pageMaker_hasPrev = Boolean(data.pageMaker.hasPrev);
							var pageMaker_hasNext = Boolean(data.pageMaker.hasNext);
							$('#pageMaker_startPageNo').val(data.pageMaker.startPageNo);
							$('#pageMaker_endPageNo').val(data.pageMaker.endPageNo);
							var pageMaker_startPageNo = +$('#pageMaker_startPageNo').val();
							var pageMaker_endPageNo = +$('#pageMaker_endPageNo').val(); 
							
							var memberNickname = $('#memberNickname').val();
							var list = '';
							
							$(data.list).each(function(){
								// this : 컬렉션의 각 인덱스 데이터를 의미
								console.log(this);
								
								var productCommentCreatedDate = new Date(this.productCommentCreatedDate);
								var disabled = 'disabled';
								var readonly = 'readonly';
								
								if(memberNickname == this.memberNickname) { // 댓글 작성자랑 로그인한 id가 같을때
									disabled = '';
									readonly = '';
								}
								
								
								
								list += '<div class="comment_item">'
									+ '<pre>'
									+ '<input type="hidden" class="productCommentId" value="' + this.productCommentId + '">'
									+ this.memberNickname
									+ '&nbsp;&nbsp;' // 공백
									+ '<input type="text" class="productCommentContent" value="' + this.productCommentContent + '" required>'	 
									+ '&nbsp;&nbsp;' // 공백
									+ productCommentCreatedDate
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
					); // end JSON()
			} // end getAllproductComments
			
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
			
			$('#comments').on('click', '.comment_item .btnCommentUpdate', function(){
				console.log(this);
				
				var productCommentId = $(this).prevAll('.productCommentId').val();
				var productCommentContent = $(this).prevAll('.productCommentContent').val();
				console.log("선택된 댓글 번호 : " + productCommentId + ", 댓글 내용 : " + productCommentContent);
				
				// ajax 요청
				$.ajax({
					type : 'PUT',
					url : 'comments/' + productCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : productCommentContent,
					success : function(result){
						console.log(result);
						if(result == '1'){
							alert('댓글 수정 성공')
							getAllComments();
						}
					}
				}); // end ajax
			}); // end click
			
			$("#comments").on('click', '.comment_item .btnCommentDelete', function(){
				console.log(this);
				
				var productId = $('#productId').val();
				var productCommentId = $(this).prevAll('.productCommentId').val();
				console.log("선택된 댓글 번호 : " + productCommentId);
			
				$.ajax({
					type : 'DELETE',
					url : 'comments/' + productCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : productId,
					success : function(result){
						console.log(result);
						if(result == 1){
							alert('댓글 삭제 성공')
							getAllComments();
						}
					}
				}); //end ajax
			}); // end click
			
		}); // end document
		
		$('#comments').on('click', '.comment_item .btnReply', function(){
			if($('#memberNickname').val() == null) {
				alert('답글을 작성하려면 로그인 해 주세요')
				return;
			}
			console.log(this);
			var productCommentId = $(this).closest('.comment_item').find('.productCommentId');
			getAllReplies(productCommentId);
		}); // end btn_Reply()
		
		function getAllReplies(productCommentId) {
			$('.replies').html('');
			console.log("getAllReplies() 호출");
			var url = 'replies/all/' + productCommentId.val();
			var comment_item = productCommentId.closest('.comment_item');
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
						
						var productReplyCreatedDate = new Date(this.productReplyCreatedDate);
						var disabled = 'disabled';
						var readonly = 'readonly';
						
						if(memberNickname == this.memberNickname) { // 댓글 작성자랑 로그인한 id가 같을때
							console.log("nickname 일치")
							disabled = '';
							readonly = '';
						}
						
						list += '<div class="reply_item">'
							+ '<pre>'
							+ '<input type="hidden" class="productReplyId" value="' + this.productReplyId + '">'
							+ this.memberNickname
							+ '&nbsp;&nbsp;' // 공백
							+ '<input type="text" class="productReplyContent" value="' + this.productReplyContent + '" required>'	 
							+ '&nbsp;&nbsp;' // 공백
							+ productReplyCreatedDate
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
						+ '<input type="text" class="productReplyContent" required>'
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
			var productCommentId = $(this).closest('.comment_item').find('.productCommentId');
			var productCommentIdVal = $(this).closest('.comment_item').find('.productCommentId').val();
		    var memberNickname = $('#memberNickname').val();
		    var productReplyContent = $(this).prevAll('.productReplyContent').val();


			var obj = {
					'productCommentId' : productCommentIdVal, 
					'memberNickname' : memberNickname,
					'productReplyContent' : productReplyContent
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
						getAllReplies(productCommentId);
					}
				}
			}); // end ajax()
		}); // end btnReplyAdd.click()
		
		$(document).on('click', '.btnReplyUpdate', function(){
			console.log(this);
			var commentItem = $(this).closest('.comment_item');
			var productCommentId = $(this).closest('.comment_item').find('.productCommentId');
			var productReplyId = $(this).prevAll('.productReplyId').val();
			var productReplyContent = $(this).prevAll('.productReplyContent').val();
			console.log("선택된 답글 번호 : " + productReplyId + ", 답글 내용 : " + productReplyContent);	
			
			// ajax 요청
			$.ajax({
				type : 'PUT',
				url : 'replies/' + productReplyId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : productReplyContent,
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('답글 수정 성공!');
						getAllReplies(productCommentId);
					}
				}
			}) // end ajax()
		}); // end btnReplyUpdate.on()
		
		$(document).on('click', '.btnReplyDelete', function(){
			console.log(this);
			var commentItem = $(this).closest('.comment_item');
			var productCommentId = $(this).closest('.comment_item').find('.productCommentId');
			var productReplyId = $(this).prevAll('.productReplyId').val();
			
			console.log("선택된 댓글 번호 : " + productReplyId);
			
			$.ajax({
				type : 'DELETE',
				url : 'replies/' + productReplyId,
				headers : {
					'Content-Type' : 'application/json'
				},
				success : function(result){
					console.log(result);
					if(result == 1){
						alert('답글 삭제 성공');
						getAllReplies(productCommentId);
					}
				}
			}); // end ajax()
		}); // end btnreplydelete()
		
		
	
		
	</script>
	
</body>


</html>