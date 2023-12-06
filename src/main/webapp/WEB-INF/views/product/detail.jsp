<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
	
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS -->
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
    
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossorigin="anonymous"
    />
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
      integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
      integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"
    ></script>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet" />
     <!-- Bootstrap core JS-->
	<script
	   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
      * {
        font-family: "Jua", sans-serif;
      }
      .product-img {
        width: 500px;
        height: 300px;
        background-position: center;
        background-size: cover;
      }
      .product-desc {
        width: 500px;
        margin-top: 20px;
        margin-bottom: 20px;
      }

      .price {
        font-size: 20px;
        font-style: italic;
      }

      .item-order {
        width: 500px;
      }

      .btn-order {
        margin: auto;
        width: 100px;

        display: block;
      }
      .wrap {
        width: 500px;
        padding-top: 50px;
        margin: auto;
      }
     .productImage {
	    width: 500px;
	    height: 300px;
	    background-position: center;
	    background-size: cover;
     }
     .productTextarea {
     	width: 100%; /* Full width */
   		height: auto; /* Automatically set height */
    	max-width: 100%; /* Maximum width */
    	resize: none; /* Disable resizing */
     }
     
     #comment_page {
  		list-style-type: none;
  		display: flex;
  		justify-content: center; /* 가로 가운데 정렬 */
  		align-items: center; /* 세로 가운데 정렬 */
  		padding: 0; /* 기본 패딩 제거 */
	}

	#comment_page li {
  		display: inline;
	}
	
	/* 각 별들의 기본 설정 */
	.starR{
	  display: inline-block;
	  width: 30px;
	  height: 30px;
	  color: transparent;
	  text-shadow: 0 0 0 #f0f0f0;
	  font-size: 1.8em;
	  box-sizing: border-box;
	  cursor: pointer;
	}
	
	/* 별 이모지에 마우스 오버 시 */
	.starR:hover {
	  text-shadow: 0 0 0 #ccc;
	}
	
	/* 별 이모지를 클릭 후 class="on"이 되었을 경우 */
	.starR.on{
	  text-shadow: 0 0 0 #ffbc00;
	}
	
	
	
		
    </style>




<meta charset="UTF-8">
<title>${vo.productName }</title>
</head>
<%@ include file="../header.jspf" %> 
<body>
	<input type="hidden" id="productId" name="productId" value="${vo.productId }" >
	<input type="hidden" id="productId" value="${productId}" />
	<input type="hidden" id="memberId" value="${memberId}" />
	<input type="hidden" id="productPrice" value="${productPrice}" />
	<input type="hidden" id="productOrderListId" value="${productOrderListId}" />
	
	<c:set var="memberNickname" value="${memberNickname }" />
	<c:set var="voMemberNickname" value="${vo.memberNickname }" />
	<c:set var="memberLevel" value="${memberLevel }" />
	<c:set var="voMemberLevel" value="${vo.memberLevel }" />
	<c:set var="productOrderId" value="${productOrderId }" />
	
	
	<div class="wrap">
		<div class="product-img">
		    <div class="slider">
		        <c:forEach var="image" items="${imageArray}">
		            <img src="display?fileName=${image}" class="productImage" style="display:none;" />
		        </c:forEach>
		    </div>
		    <button class="prevBtn">이전</button>
		    <button class="nextBtn">다음</button>
		</div>
		<br>
	 	<div class="product-desc">
        	<h2>
          	${vo.productName }
        	</h2>
        	<textarea class="productTextarea" rows="20" cols="120" readonly>${vo.productContent }</textarea>
        	<fmt:formatDate value="${vo.productCreatedDate }"
								pattern="yyyy.MM.dd" var="productCreatedDate" />                            
        	<p>상품 등록일 : ${productCreatedDate }</p>
			<p>카테고리 : ${vo.productCategori }</p>
          	<span class="price"> 가격: ${vo.productPrice} </span>
      	</div>
	    <c:if test="${memberNickname != null}">  	
	      	<div>
	    		<label for="productAmount">수량:</label>
	    		<input type="number" id="productAmount" name="productAmount" min="1" value="1" required>
	    		
	    		<div style="display: inline-block">
					<c:if test="${productOrderListId == 0 }">
						<button id="orderListBtn">장바구니 넣기</button>		
					</c:if>
					<c:if test="${productOrderListId != 0 }">
						<button id="orderListBtn">장바구니 삭제</button>				
					</c:if>
				</div>
			</div>
		</c:if>
		<div style="display: inline-block">
    		<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
			<c:if test="${memberLevel > 1}">
				<a href="update?productId=${vo.productId }&page=${page }">
					<input type="button" class="productUpdateBtn" value="상품 수정">
				</a>
			</c:if>
		</div>
		<div style="display: inline-block">
			<c:if test="${memberLevel > 1}">
				<form action="delete" method="POST">
					<input type="hidden" id="productId" name="productId" value="${vo.productId }"> 
					<input type="submit" class="productDeleteBtn" value="상품 삭제">
				</form>
			</c:if>
		</div>
		<p></p>
		<c:if test="${memberNickname != null}">
			<div style="display: inline-block">
				<input type="hidden" id="memberNickname" value=${memberNickname }>
			<c:if test="${productLikeId == 0 }">
				<button id="likeBtn">좋아요</button>		
			</c:if>
			<c:if test="${productLikeId != 0 }">
				<button id="likeBtn">좋아요취소</button>		
			</c:if>
				<span id="likeCnt">${vo.productLikes }</span> 
				<input type="hidden" id="productLikeId" name="productLikeId" value="${productLikeId }">
			</div>
			<div>
				<a href="orderList?memberId=${memberId}&productId=${vo.productId}"><input type="button" value="장바구니 이동"></a>
			</div>
			 <c:if test="${productOrderListId == 0 }">
			<div>
			    <a href="orderList?memberId=${memberId}&productId=${vo.productId}" ><input type="button" id="orderBtn" value="바로 구매"></a>
			</div>
			</c:if>
		</c:if>
	</div>
		
	<c:if test="${memberNickname == null }">
		<div style="text-align: center;">
	        <br> 구매하실려면 로그인해 주세요.
	    </div>
	</c:if>
	<p></p>
	<c:if test="${memberNickname != null}">
	<div style="text-align: center;">
		<div class="productRated">
			${memberNickname}
			<span class="starR on">⭐</span>
		    <span class="starR">⭐</span>
		    <span class="starR">⭐</span>
		    <span class="starR">⭐</span>
		    <span class="starR">⭐</span>
		    <input type="hidden" id="selectedRating" value="0" />
		</div>
		<input type="hidden" id="memberNickname" value=${memberNickname }>
		<textarea rows="3" cols="120" id="productCommentContent" required></textarea>
		<button id="btnCommentAdd">작성</button> 
	</div>
	</c:if>
	<c:if test="${memberNickname == null}">
		<div style="text-align: center;">
	        <br> 댓글 / 답글을 작성하려면 로그인해 주세요.
	    </div>
	</c:if>
	
	<hr>
	
	<div style="text-align: center;">
		<div id="comments" style="display: inline-block; text-align: left;"></div>
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
	
	<!-- 댓글 수정 Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body" style="max-height: 300px; overflow-y: auto;">
	        <input type="text" class="updateCommentContent form-control" required style="width: 100%;">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary">수정</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- 답글 수정 Modal -->
	<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel1">답글 수정</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <input type="text" class="updateReplyContent form-control" required style="width: 100%;">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">수정</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			getAllComments();
			$('.productRated span').click(function() {
		        var selectedRating = $(this).index() + 1;
		        $(this).parent().find('.selectedRating').val(selectedRating);

			    $(this).parent().children('span').removeClass('on');
			    $(this).addClass('on').prevAll('span').addClass('on');
			    return false;
			});

			$('#btnCommentAdd').click(function(){
				
				var productId = $('#productId').val(); // 상품 번호 데이터
				var memberNickname = $('#memberNickname').val(); // 닉네임 데이터
				var productCommentContent = $('#productCommentContent').val(); // 댓글 내용
				var productRated = $('.productRated span.on').length;
				var obj = {
						'productId' : productId,
						'memberNickname' : memberNickname,
						'productCommentContent' : productCommentContent,
						'productRated' : productRated
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
							window.location.reload();
							console.log("getAllcomments");
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
								
								function generateStars(rating) {
								    let stars = '';
								    for (let i = 1; i <= 5; i++) {
								        if (i <= rating) {
								            stars += '⭐'; 
								        } else {
								            stars += ''; 
								        }
								    }
								    return stars;
								}
								
								function formatDate(date) {
							        var year = date.getFullYear();
							        var month = ('0' + (date.getMonth() + 1)).slice(-2);
							        var day = ('0' + date.getDate()).slice(-2);
							        var hours = ('0' + date.getHours()).slice(-2);
							        var minutes = ('0' + date.getMinutes()).slice(-2);
							        var seconds = ('0' + date.getSeconds()).slice(-2);
							        
							        return year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
							    }
								
								list += '<div class="comment_item">'
									+ '<pre>'
									+ '<input type="hidden" class="productCommentId" value="' + this.productCommentId + '">'
									+ '<div class="comment-header">'
									+ '별점 : ' + generateStars(this.productRated) + '&nbsp;&nbsp;' + '<strong>' + this.memberNickname + '</strong>'
									+ '&nbsp;&nbsp;' + '<span class="comment-date">' + formatDate(productCommentCreatedDate) + '</span>'
									+ '</div>'
									+ '<div class="productCommentContent">'
									+ '<textarea class="commentContent" rows="3" cols="120" style="border=1px;" readonly>'
									+ this.productCommentContent
									+ '</textarea>'
									+ '</div>'
									+ '<button class="btnCommentUpdate1" data-bs-toggle="modal" data-bs-target="#exampleModal" ' + disabled + '>수정</button>'
									+ '<button class="btnCommentDelete" ' + disabled + '>삭제</button>'
									+ '<button class="btnReply">답글</button>'
									+ '<br>'
									+ '</pre>'
									+ '</div>';
								 
							}); // end each()
							list += '<ul id="comment_page" style="list-style-type: none; display: flex;">' // 리스트 시작
								if(pageMaker_hasPrev) {
								    list += '<li style="display: inline;"><button class="btn_comment_prev">이전</button></li>'
								}

								for(var num = pageMaker_startPageNo; num <= pageMaker_endPageNo; num++) {
								    list += '<li style="display: inline;"><button class="btn_comment_page" value='+num+'>'+num+'</button></li>'
								}

								if(pageMaker_hasNext) {
								    list += '<li style="display: inline;"><button class="btn_comment_next">이후</button></li>'
								}
								list += '</ul>' // 리스트 닫기
								$('#comments').html(list);
								
								$('.btnCommentUpdate1').on('click', function() {
						            var productCommentId = $(this).closest('.comment_item').find('.productCommentId').val();
						            console.log('선택된 댓글 번호:' + productCommentId);
						            $('#exampleModal').data('productCommentId', productCommentId);
						        }); // end btnCommentUpdate1 click
								
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
			
			$('#exampleModal').on('click', '.btn-primary', function(){
				var productCommentId = $(this).closest('#exampleModal').data('productCommentId');
			    var productCommentContent = $(this).closest('.modal').find('.updateCommentContent').val();
			    console.log("선택된 댓글 번호 : " + productCommentId);
			    
			    $.ajax({
			        type: 'PUT',
			        url: 'comments/' + productCommentId,
			        headers : {
						'Content-Type' : 'application/json'
					},
					data : productCommentContent,
			        success: function(result){
			            console.log(result);
			            if(result == '1'){
			                alert('댓글 수정 성공');
			                $('#exampleModal').modal('hide');
			                getAllComments();
			            }
			        }
			    }); // end ajax
			}); // end exampleModal
			
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
		
		
		var selectedReplyBtn; // 클릭 이벤트 변수 선언
		$('#comments').on('click', '.comment_item .btnReply', function(event){			
			selectedReplyBtn = event.currentTarget
			if($('#memberNickname').val() == null) {
				alert('답글을 작성하려면 로그인 해 주세요')
				return;
			}
			console.log(this);
			var productCommentId = $(this).closest('.comment_item').find('.productCommentId');
			getAllReplies(productCommentId);
		}); // end btn_Reply()
		
		function getAllReplies(productCommentId ) {
			console.log("productCommentId: ")
			console.log(productCommentId)
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
						
						function formatDate(date) {
					        var year = date.getFullYear();
					        var month = ('0' + (date.getMonth() + 1)).slice(-2);
					        var day = ('0' + date.getDate()).slice(-2);
					        var hours = ('0' + date.getHours()).slice(-2);
					        var minutes = ('0' + date.getMinutes()).slice(-2);
					        var seconds = ('0' + date.getSeconds()).slice(-2);
					        
					        return year + '/' + month + '/' + day + ' ' + hours + ':' + minutes + ':' + seconds;
					    }
						
						list += '<div class="reply_item">'
							+ '<pre>'
							+ '<input type="hidden" class="productReplyId" value="' + this.productReplyId + '">'
							+ this.memberNickname
							+ '&nbsp;&nbsp;' // 공백
							+ '<input type="text" class="productReplyContent" value="' + this.productReplyContent + '" readonly>'	 
							+ '&nbsp;&nbsp;' // 공백
							+ formatDate(productReplyCreatedDate)
							+ '&nbsp;&nbsp;' // 공백
							+ '<button class="btnReplyUpdate1" data-bs-toggle="modal" data-bs-target="#exampleModal1" ' + disabled + '>수정</button>'
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
						
						$('.btnReplyUpdate1').on('click', function(){
							var productReplyId = $(this).closest('.reply_item').find('.productReplyId').val();
							var productCommentId = $(this).closest('.comment_item').find('.productCommentId').val();
					        console.log('선택된 댓글 번호:' + productCommentId);
							console.log('선택된 답글 번호 :' + productReplyId);
							
					        $('#exampleModal1').data('productCommentId', productCommentId);
							$('#exampleModal1').data('productReplyId', productReplyId);
						}); // end btnReplyUpdate1 Click
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

            var commentRegisterNick = commentItem.find('.commentRegisterNickname').val();
            var commentContent = commentItem.find('.productCommentContent').val();
            var productId = $('#productId').val();
		    
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
						socket.send(
                                commentRegisterNick + "," + "새 답글" + "," + "공용상품" + "," +
                                productReplyContent + "," +
                                memberNickname + "," + commentContent + "," + productId
                            );
						getAllReplies(productCommentId);
					}
				}
			}); // end ajax()
		}); // end btnReplyAdd.click()
		
		$('#exampleModal1').on('click', '.btn-primary', function(){

			var commentItem = $(this).closest('.comment_item');
			var productCommentId = $(this).closest('#exampleModal1');
			var productReplyId = $(this).closest('#exampleModal1').data('productReplyId');
			var productReplyContent = $(this).closest('.modal').find('.updateReplyContent').val();
			
			console.log("commentItem :" + commentItem + ", commentId : " + productCommentId);
			console.log("선택된 답글 번호 : " + productReplyId);
			
			$.ajax({
				type : 'PUT',
				url : 'replies/' + productReplyId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : productReplyContent,
				success : function(result){
					console.log(result)
						if(result == '1'){
							alert('답글 수정 성공');
							$('#exampleModal1').modal('hide');
							selectedReplyBtn.click();
							
						}
				}
			}); // end ajax
		}); // end primary1 click
		
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
		
		$(document).ready(function() {
		    var likeBtn = $('#likeBtn');

		    likeBtn.click(function() {
		        var productId = $('#productId').val();
		        var memberId = $('#memberId').val();

		        if ($(this).text() === '좋아요') { 
		            $.ajax({
		                type: 'POST',
		                url: 'likes',
		                headers: {
		                    'Content-Type': 'application/json'
		                },
		                data: JSON.stringify({ productId: productId, memberId: memberId }),
		                success: function(result) {
		                    if (result.result == 1) {
		                        alert('좋아요 등록');
		                        likeBtn.text('좋아요 취소');
		                        updateLikeCount(1);
		                    }
		                }
		            });
		        } else { // 좋아요 취소
		            $.ajax({
		                type: 'DELETE',
		                url: 'likes/' + productId + '/' + memberId,
		                headers: {
		                    'Content-Type': 'application/json'
		                },
		                success: function(result) {
		                    console.log(result);
		                    if (result.result == 1) {
		                        alert('좋아요 삭제');
		                        likeBtn.text('좋아요');
		                        updateLikeCount(-1);
		                    }
		                }
		            });
		        }
		    });
		    
		    function updateLikeCount(change) {
		        var currentCount = parseInt($('#likeCnt').text());
		        $('#likeCnt').text(currentCount + change);
		    }
		}); // end document 

		$(document).ready(function(){
			var orderListBtn = $('#orderListBtn');
			var orderBtn =$('#orderBtn');
			
			orderBtn.click(function(){
				console.log('구매버튼 클릭');
				console.log('클릭');
				var productId = $('#productId').val();
		        var memberId = $('#memberId').val();
				var productPrice = $('#productPrice').val();
				var productAmount = parseInt(document.getElementById('productAmount').value, 10);

				
				var obj = {
						'productId' : productId, 
						'memberId' : memberId,
						'productAmount' : productAmount
				};
				$.ajax({
		        	type : 'POST',
		        	url : 'orderLists',
		        	headers: {
	                    'Content-Type': 'application/json'
	                },
	                data : JSON.stringify(obj),
	                success : function(result){
	                	console.log(result);
	                	if(result == 1){
	                		console.log('바로 구매');
	                	}
	                }
		        }); // end ajax
			}); // end orderBtn
			
			orderListBtn.click(function(){
				console.log('클릭');
				var productId = $('#productId').val();
		        var memberId = $('#memberId').val();
				var productPrice = $('#productPrice').val();
				var productAmount = parseInt(document.getElementById('productAmount').value, 10);

				
				var obj = {
						'productId' : productId, 
						'memberId' : memberId,
						'productAmount' : productAmount
				};
				if($(this).text() === '장바구니 넣기'){
			        $.ajax({
			        	type : 'POST',
			        	url : 'orderLists',
			        	headers: {
		                    'Content-Type': 'application/json'
		                },
		                data : JSON.stringify(obj),
		                success : function(result){
		                	console.log(result);
		                	if(result == 1){
		                		alert('장바구니 등록');
		                		orderListBtn.text('장바구니 삭제');
		                		window.location.reload();
		                	}
		                }
			        }); // end ajax
				} else{
					$.ajax({
						type : 'DELETE',
						url : 'orderLists/' + productId + '/' + memberId,
						headers: {
			                   'Content-Type': 'application/json'
			               },
			            success: function(result) {
			                 console.log(result);
			                 if (result == 1) {
			                     alert('장바구니 삭제');
			                     orderListBtn.text('장바구니 넣기');
			                     window.location.reload();
			                 }
			             }  
					}); // end ajax
				}
			}); // end click
			
		}); // end document

	</script>
	
	<script>
		var currentImageIndex = 0;
		var images = document.querySelectorAll('.slider img'); // 모든 이미지 요소
		var prevBtn = document.querySelector('.prevBtn'); // 이전 버튼 요소
		var nextBtn = document.querySelector('.nextBtn'); // 다음 버튼 요소
	
		function showImage(index) {
		    images.forEach(function(image) {
		        image.style.display = 'none'; // 모든 이미지 숨김
		    });
		    images[index].style.display = 'block'; // 주어진 인텍스의 이미지 표출
		}
	
		prevBtn.addEventListener('click', function() {
		    currentImageIndex = (currentImageIndex === 0) ? images.length - 1 : currentImageIndex - 1; // 이전 이미지 인덱스
		    showImage(currentImageIndex);
		});
	
		nextBtn.addEventListener('click', function() {
		    currentImageIndex = (currentImageIndex === images.length - 1) ? 0 : currentImageIndex + 1; // 다음 이미지 인덱스
		    showImage(currentImageIndex);
		});
	
		// 초기 이미지 표시
		showImage(currentImageIndex);

	</script>
	

	
</body>


</html>