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
<title>${vo.productName }</title>
</head>
<body>
	
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
	
	
	<c:if test="${memberNickname == voMemberNickname}">
	<a href="update?ProductId=${vo.productId }&page=${page }"><input type="button" value="상품 수정"></a>
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
		<div id="productComments"></div>
	</div>
	<hr>
	
	
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
			getAllProductComments();
			
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
					url : 'productComments',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result){
						console.log(result);
						if(result == 1){
							console.log('댓글 입력 성공');
							alert('댓글 입력 성공');
							getAllProductComments();
						}
					}
				});
			}); // end btnAdd
			
			// 댓글 전체 가져오기
			function getAllProductComments(){
				console.log("getAllProductComments() 호출");
				var productId = $('#productId').val();
				var commentPage = $('#pageMaker_commentPage').val();
				console.log("page = " + commentPage);
				var commentNumsPerPage = $('pageMaker_commentNumsPerPage').val();
				var url = 'productComments/all/' + productId + '/' + commentPage + '/' + commentNumsPerPage;
				
				$.getJSON(
						url,
						function(data){
							console.log(data);
							var pageMaker_hasPrev = Boolean(data.pageMaker.hasPrev);
							var pageMaker_hasNext = Boolean(data.pageMaker.hasNext);
							$('#pageMaker_startPageNo').val(data.pageMaker.startPageNo);
							$('#pageMaker_endPageNo').val(data.pageMaker.endPageNo);
							var pageMaker_startPageNo = +$('#pageMaker_startPageNo').val();
							var pageMaker_endPageNo = +$('#pageMaker_endPageNo').val(); 
							
							var memberNickname = $('#memberNickname').val();
							var list = '';
							
							$(data.list).each(function(){
								console.log(this);
								
								var productCommentCreatedDate = new Date(this.productCommentCreatedDate);
								var disabled = 'disabled';
								var readonly = 'readonly';
								
								if(memberNickname == this.memberNickname){
									disabled = '';
									readonly = '';
								}
								
								
								
								list += '<div class="product_comment_item">'
									+'<pre>'
									+'<input type="hidden" id="productCommentId" value="' + this.productCommentId + '">'
									+ this.memberNickname
									+ '&nbsp;&nbsp;'
									+'<input type="text" id="productCommentContent" value="' + this.productCommentContent + '">'
									+ '&nbsp;&nbsp;'
									+ productCommentCreatedDate
									+ '&nbsp;&nbsp;'
									+'<button class="btn_update"> 수정</button>'
									+'<button class="btn_delete"> 삭제</button>'
									+'<button class="btn_Reply">답글</button>'
									+ '</pre>'
									+ '</div>';			
							}); // end each()
							list +='<ul id="productComment_page">'
							
							if(pageMaker_hasPrev){
								list += '<li><button class="btn_productComment_prev">이전</button></li>'
							}
							
							for(var num = pageMaker_startPageNo; num <= pageMaker_endPageNo; num++){
								list += '<li><button class="btn_productComment_page" value='+num+'>'+num+'</button></li>'
							}
							
							if(pageMaker_hasNext) {
								list += '<li><button class="btn_productComment_next">이후</button></li>'
								}
							
							list += '</ul>'
							
							$('#productComments').html(list);
						}
						});
			} // end getAllproductComments
			
			$(document).on('click', '.btn_productComment_page', function(){				
				$('#pageMaker_commentPage').val($(this).val());
				getAllproductComments();
			})// end btn_productComment_page.click()
			$(document).on('click', '.btn_productComment_prev', function(){				
				$('#pageMaker_commentPage').val(+$('#pageMaker_startPageNo').val() - 1);
				getAllproductComments();
			})// end btn_productComment_prev.click()
			$(document).on('click', '.btn_productComment_next', function(){				
				$('#pageMaker_commentPage').val(+$('#pageMaker_endPageNo').val() + 1);
				getAllproductComments();
			})// end btn_productComment_prev.click()
			
			$("#productComments").on('click', '.product_comment_item .btn_update', function(){
				console.log(this);
				
				var productCommentId = $(this).prevAll('#productCommentId').val();
				var productCommentContent = $(this).prevAll('#productCommentContent').val();
				console.log("선택된 댓글 번호 : " + productCommentId + ", 댓글 내용 : " + productCommentContent);
				
				// ajax 요청
				$.ajax({
					type : 'PUT',
					url : 'productComments/' + productCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : productCommentContent,
					success : function(result){
						console.log(result);
						if(result == '1'){
							alert('댓글 수정 성공')
							getAllProductComments();
						}
					}
				}); // end ajax
			}); // end click
			
			$("#productComments").on('click', '.product_comment_item .btn_delete', function(){
				console.log(this);
				
				var productId = $('#productId').val();
				var productCommentId = $(this).prevAll('#productCommentId').val();
				console.log("선택된 댓글 번호 : " + productCommentId);
			
				$.ajax({
					type : 'DELETE',
					url : 'productComments/' + productCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : productId,
					success : function(result){
						console.log(result);
						if(result == 1){
							alert('댓글 삭제 성공')
							getAllProductComments();
						}
					}
				}); //end ajax
			}); // end click
			
			
		}); // end document
		
		$('#productComments').on('click', '.product_comment_item . btn_Reply', function(){
			if($('#memberNickname').val() == null){
				alert('답글을 작성하려면 로그인 해주세요')
				return;
			}
			console.log(this);
			var productCommentId = $(this).closest('.product_comment_item').find('.productCommentId');
			getAllProductReplies(productCommentId);
		}); // end btn_Reply()
		
		function getAllProductReplies(productCommentId){
			$('.productReplies').html('');
			console.log("getAllProductReplies() 호출");
			var url = 'productReplies/all/' + productCommentId.val();
			var product_comment_item = productCommentId.closest('.product_comment_item');
			$.getJSON(
				url,
				function(data){
					console.log(data);
					
					var memberNickname = $('#memerNickname').val();
					var list = '';
					
					$(data).each(function(){
						console.log(this);
						
						var productReplyCreatedDate = new Date(this.productReplyCreatedDate);
						var disabled = 'disabled';
						var readonly = 'readonly';
						
						if(memberNickname == this.memberNickname){
							console.log("nickname 일치")
							disabled = '';
							readonly = '';
						}
						
						list += '<div class="product_reply_item">'
								+ '<pre>'
								+ '<input type="hidden" class="productReplyId" value="' + this.productReplyId + '">'
								+ this.memberNickname
								+ '&nbsp;&nbsp;' 
								+ '<input type="text" class=productReplyContent" value="' + this.productReplyContent + '" required>'
								+ '&nbsp;&nbsp;' 
								+ productReplyCreatedDate
								+ '&nbsp;&nbsp;' 
								+ '<button class="btn_reply_update"' + disabled + '>수정</button>'
								+ '<button class="btn_reply_delete"' + disabled + '>삭제</button>'
								+ '<br>'
								+ '</pre>'
								+ '</div>';
					}); // end each
					
					list += '<div style="text=align: center;">'
							+ memberNickname
							+ '&nbsp;&nbsp;' 
							+ '<input type="text" class="productReplyContent" reauired>'
							+ '&nbsp;&nbsp;' 
							+ '<button class="btn_reply_add">작성</button>'
							+ '</div>'
					product_comment_item.append('<div class="productReplies">' + list + '</div>');
				}
			); // end getJSON()
		} // end getAllProductReplies
		
		$(document).on('click', '.btn_reply_add', function(){
			console.log(this);
			var productCommentItem = $(this).closest('.product_comment_item');
			var productCommentId = $(this).closest('.product_comment_item').find('.productCommentId');
			var productCommentIdVal = $(this).closest('.product_comment_item').find('.productCommentId').val();
			var memberNickname = $('#memberNickname').val();
			var productReplyContent = $(this).prevAll('.productReplyContent').val();
			
				var obj = {
						'productCommentId' : productCommentIdVal,
						'memberNickname' : memberNickname,
						'productReplyContent' : productReplyContent
				};
				console.log(obj);
				
				$.ajax({
					type : 'POST',
					url : 'productReplies',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj), // JSON으로 변환
					success : function(result){
						console.log(result);
						if(result == 1){
							alert('답글 입력 성공');
							getAllProductReplies(productCommentId);
						}
					}
				}); // end ajax()
		}); // end btnreplyadd()
		
		$(document).on('click', '/btn_reply_update', function(){
			console.log(this);
			var productCommentItem = $(this).closest('.product_comment_item');
			var productCommentId = $(this).closest('.product_comment_item').find('.productCommentId');
			var productReplyId = $(this).preAll('.productReplyId').val();
			console.log("선택된 답글 번호 : " + productReplyId + ", 답글 내용 : " + productReplyContent);
			
			$.ajax({
				type : 'PUT',
				url : 'productReplies/' + productReplyId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : productReplyContent,
				success : function(result){
					console.log(result);
					if(result == 1){
						alert('답글 수정 성공');
						getAllProductReplies(productCommentId);
					}
				}
			}); // end ajax()
		}); // end btnreplyupdate()
		
		$(document).on('click', '/btn_reply_delete', function(){
			console.log(this);
			var productCommentItem = $(this).closest('.product_comment_item');
			var productCommentId = $(this).closest('.product_comment_item').find('.productCommentId');
			var productReplyId = $(this).prevAll('.productReplyId').val();
			
			console.log("선택된 댓글 번호 : " + productReplyId);
			
			$.ajax({
				type : 'DELETE',
				url : 'productReplies/' + productReplyId,
				headers : {
					'Content-Type' : 'application/json'
				},
				success : function(result){
					console.log(result);
					if(result == 1){
						alert('답글 삭제 성공');
						getAllProductReplies(productCommentId);
					}
				}
			}); // end ajax()
		}); // end btnreplydelete()
	</script>
	
</body>


</html>