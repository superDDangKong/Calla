<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html lang="en">
    <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <link href="https://fonts.googleapis.com/
  		icon?family=Material+Icons rel="stylesheet">

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
    <script src="https://kit.fontawesome.com/ef717dbcd3.js" crossorigin="anonymous"></script>
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
    </style>

    <script>
      function order() {
        alert("찜하기가 완료되었습니다!")
      }
    </script>
    <title>${vo.uProductName }</title>
  </head>
<%@ include file="../header.jspf" %> 
  <body>

    <div class="wrap">
      <div class="product-img">
      <img src="display?fileName=${vo.uProductImagePath}" width="500px" height="300px">
      </div>
      
      <br>
      <div>
      
      닉네임 : <a href="reviewboard?sellerNickname=${vo.memberNickname }&page=${pageMaker.criteria.page}">${vo.memberNickname}</a>
      <input type="hidden" id="registerNick" value="${vo.memberNickname}">
      <input type="hidden" id="uProductTitle" value="${vo.uProductName}">
      <input type="hidden" id="uProductId" value="${vo.uProductId}">
   
   	 	<fmt:parseNumber value="${memberManner}"   var="memberManner"/>
   	 	
      	<c:if test="${(memberManner) >= 40}">
      	 <span style="float:right; color:orange;">
		 <i class="fa-regular fa-face-surprise"></i>
   			 ${memberManner}&deg;C
		</span>
		</c:if>
		
		<c:if test="${(memberManner) >= 35 && (memberManner) < 40}">
   		 <span style="float:right; color:green;">
		 <i class="fa-regular fa-face-smile"></i>
   			 ${memberManner}&deg;C
		</span>
		</c:if>
		
		<c:if test="${(memberManner) < 35}">
		<span style="float:right; color:blue;">
		 <i class="fa-regular fa-face-tired"></i>
   			 ${memberManner}&deg;C
		</span>
		</c:if>
     
     	
     
     
      <br>
      
      
      <div style="float:right;">매너온도</div>
       
      </div>
      
      <br>
      
      
      <div class="product-desc">
		  
        <hr style="border: solid 2px gray;">
        
        <p>가격 문의는 비밀 댓글로 해주세요</p>
         <span class="price"> 가격 : ${vo.uProductPrice}원</span>
      </div>
      <div class="item-order">
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">제목</span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
             value = "${vo.uProductName }"
            readonly
          />
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">카테고리</span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            value = ${vo.uProductCategori }
            readonly
          />
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">작성자 주소</span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            value = "${vo.memberAddress }"
            readonly
          />
        </div>
        
        <div>
		<textarea rows="10" cols="58" readonly>${vo.uProductContent }</textarea>
		</div>
		
		  <br>
            
        <c:if test="${not empty sessionScope.memberId }">
        
        <input type="hidden" id="memberId" name="memberId" value="${sessionScope.memberId }">
        
        <c:if test="${uProductLikeId == 0 }">
        <button id="likeBtn" class="btn btn-primary btn-order">좋아요</button>
        </c:if>
        <c:if test="${uProductLikeId != 0 }" >
        <button id="likeBtn" class="btn btn-primary btn-order">좋아요취소</button>	
        </c:if>
        
        </c:if>
        
         <a href="list?page=${page }"><input type="button" value="상품 목록" style="float:right;"></a>
    
    <br>
	<br>
	<br>
	
	<input type="hidden" id="uProductId" value="${vo.uProductId }">
     <c:if test="${vo.memberNickname eq sessionScope.memberNickname}">
	<a href="update?uProductId=${vo.uProductId }&page=${page }"><input type="button" value="글 수정" style="float:right;"></a>
	
	
	<form action="delete" method="POST">
		<input type="hidden" name="uProductId" value="${vo.uProductId }">
		<input type="submit" value="상품 삭제" style="float:right;">
	</form>
     </c:if>   
        
        
      </div>
    </div>
    
    <br>
	<br>
	<br>
    
   
	
	<br>
	<br>
	<br>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<div>

					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
						
					</ul>
					
				</div>

			</div>
		</div>
	</nav>
	
	
	 <c:if test="${empty sessionScope.memberId }">
	<div>
		<h3>로그인이 필요한 서비스입니다.</h3>
	
	</div>
	
	</c:if>
	
	
	 <c:if test="${not empty sessionScope.memberId }">
	
	<div>
		<input type="text" id="memberNickname" value="${sessionScope.memberNickname }" readonly="readonly">
        <input type="text" id="uProductCommentContent">
        <button id="btnAdd">작성</button>
        <input type="checkbox" id ="uProductSecretComment" > 비밀 댓글
        
	</div>
	
	</c:if>
	
	<hr>
	
    
	<div id="uproductcomments"></div>
	
	<br>
	<br>
	<br>
	
	<div>
		<h3>이런 상품은 어떠세요?</h3>
	</div>
	
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<c:forEach var="vo" items="${list }">

					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top"
								src="display?fileName=${vo.uProductImagePath}" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">

										<a
											href="detail?uProductId=${vo.uProductId }&page=${pageMaker.criteria.page}">${vo.uProductName }</a>

									</h5>
									
									<hr>
									
									<span style="color:red;">
									<i class="fa-solid fa-heart fa-sm"></i>
									</span>
									
									<span>
									${vo.uProductLikes } &nbsp;
									</span>
									
									
									
									<span >
									<i class="fa-regular fa-comment"></i>
									</span>
									
									<span>
									${vo.uProductCommentCount }
									</span>
									
									<br>
									<br>
									
									<div>
									${vo.uProductPrice }원
									</div>						
								
									
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">${vo.uProductStatement }</a>
								</div>
							</div>
						</div>
					</div>


				</c:forEach>

			</div>
		</div>

	</section>
	
	<script type="text/javascript">
		$(document).ready(function(){
		   	window.addEventListener('hashchange', function() {
		    	getAllUProductComments();
		   	});
			getAllUProductComments();
			
			$('#btnAdd').click(function(){
				var uProductId = $('#uProductId').val();
				var memberNickname = $('#memberNickname').val();
				var uProductCommentContent = $('#uProductCommentContent').val();
				var uProductSecretComment = "n";
				if($("#uProductSecretComment").is(":checked")){
					uProductSecretComment ="y";
				}
				
				var obj = {
						'uProductId' : uProductId,
						'memberNickname' : memberNickname,
						'uProductCommentContent' : uProductCommentContent,
						'uProductSecretComment' : uProductSecretComment
						
						  }
				
				console.log(obj);
				
				$.ajax({
					type : 'POST',
					url : 'uproductcomments',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj),
					success : function(result){
						console.log(result);
						if(result == 1){
							alert('댓글 입력 성공');
	                        socket.send(
	                                $('#registerNick').val() + "," + "새 댓글" + "," + "중고상품" + "," +
	                                uProductCommentContent + "," +
	                                memberNickname + "," + $('#uProductTitle').val() + "," + uProductId
	                            );
							getAllUProductComments();
						}
					}
				});
			}); // end btnAdd
			
			function getAllUProductComments(){
				var uProductId = $('#uProductId').val();
				
				var url = 'uproductcomments/all/' + uProductId;
				$.getJSON(
						url,
						function(data){
							console.log(data);
							
							var memberNickname = $('#memberNickname').val();
							var list = '';
							
							$(data).each(function(){
								console.log(this);
								
								var uProductCommentCreatedDate = new Date(this.uProductCommentCreatedDate);
								var disabled = 'disabled';
								var readonly = 'readonly';
								
								if(memberNickname == this.memberNickname){
									disabled = '';
									readonly = '';
								}
								
								list += '<div class="uproduct_comment_item">'
									+'<pre>'
									+'<input type="hidden" class="uProductCommentId" value="' + this.uProductCommentId + '">'
									+'<input type="hidden" class="commentRegisterNickname" value="' + this.memberNickname + '">'
									+ this.memberNickname
									+ '&nbsp;&nbsp;'
									+'<input type="text" ' + readonly + ' class="uProductCommentContent" value="' + this.uProductCommentContent + '">'
									+ '&nbsp;&nbsp;'
									+ uProductCommentCreatedDate
									+ '&nbsp;&nbsp;'
									+'<button class="btn_update" ' + disabled + '> 수정</button>'
									+'<button class="btn_delete" ' + disabled + '> 삭제</button>'
									+'<button class="btnReply"> 답글</button>'
									+ '</pre>'
									+ '</div>';			
							});
							$('#uproductcomments').html(list);
							
							var fragment = window.location.hash;
			    	        if (fragment) {
			    	        	
			    	        	fragment = fragment.replace('#', '');
			    	        	var fragmentList = fragment.split(',');
			    	        	
			    	        	var targetCommentElement = $('.uProductCommentId').filter('[value="' + fragmentList[0] + '"]');
			    	        	console.log("targetElement" + targetCommentElement.length)
				    	           if (targetCommentElement.length > 0 && fragmentList[1] == 0) {
					    	           var target = targetCommentElement[0].parentElement;
					    	           target.style.backgroundColor = 'lightgray'; 
					    	           target.style.border = '2px solid';   
					    	           target.scrollIntoView({ behavior: 'smooth', block: 'center' });
						    	        
					    	           setTimeout(function() {
						    	           target.style.backgroundColor = '';
						    	           target.style.border = '';
					    	           }, 2000);  // 2초 후에 스타일 초기화
					        		   if (window.location.hash) {
					        			    history.replaceState('', document.title, window.location.pathname + window.location.search);
					        			}
				    	   		     } else if (fragmentList[1] > 0) {
				    	        	   getAllReplies(targetCommentElement);
				    	    	       }
			    	       	
			    	        } // if(fragment)	
						});
			} // end getAllproductComments
			
			$("#uproductcomments").on('click', '.uproduct_comment_item .btn_update', function(){
				console.log(this);
				
				var uProductCommentId = $(this).prevAll('.uProductCommentId').val();
				var uProductCommentContent = $(this).prevAll('.uProductCommentContent').val();
				console.log("선택된 댓글 번호 : " + uProductCommentId + ", 댓글 내용 : " + uProductCommentContent);
				
				$.ajax({
					type : 'PUT',
					url : 'uproductcomments/' + uProductCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : uProductCommentContent,
					success : function(result){
						console.log(result);
						if(result == '1'){
							alert('댓글 수정 성공')
							getAllUProductComments();
						}
					}
				}); // end ajax
			}); // end click
			
			$("#uproductcomments").on('click', '.uproduct_comment_item .btn_delete', function(){
				console.log(this);
				
				var uProductId = $('#uProductId').val();
				var uProductCommentId = $(this).prevAll('.uProductCommentId').val();
				console.log("선택된 댓글 번호 : " + uProductCommentId);
				console.log("uProductId:" + uProductId);
			
				$.ajax({
					type : 'DELETE',
					url : 'uproductcomments/' + uProductCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : uProductId,
					success : function(result){
						console.log(result);
						if(result == 1){
							alert('댓글 삭제 성공')
							getAllUProductComments();
						}
					}
				}); //end ajax
			}); // end click
			
			
		}); // end document
		
		$('#uproductcomments').on('click', '.uproduct_comment_item .btnReply', function(){
			if($('#memberNickname').val() == null) {
				alert('답글을 작성하려면 로그인 해 주세요')
				return;
			}
			console.log(this);
			var uProductCommentId = $(this).closest('.uproduct_comment_item').find('.uProductCommentId');
			console.log(uProductCommentId);
			getAllReplies(uProductCommentId);
		}); // end btn_Reply()
		
		function getAllReplies(uProductCommentId) {
			$('.replies').html('');
			console.log("getAllReplies() 호출");
			var url = 'replies/all/' + uProductCommentId.val();
			var uproduct_comment_item = uProductCommentId.closest('.uproduct_comment_item');
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
						
						var uProductReplyCreatedDate = new Date(this.uProductReplyCreatedDate);
						var disabled = 'disabled';
						var readonly = 'readonly';
						var string = 'ㄴ';
						
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
							+ '<input type="hidden" class="uProductReplyId" value="' + this.uProductReplyId + '">'
							+ string
							+ '&nbsp;&nbsp;' // 공백
							+ this.memberNickname
							+ '&nbsp;&nbsp;' // 공백
							+ '<input type="text" class="uProductReplyContent" value="' + this.uProductReplyContent + '" required>'	 
							+ '&nbsp;&nbsp;' // 공백
							+ formatDate(uProductReplyCreatedDate)
							+ '&nbsp;&nbsp;' // 공백
							+ '<button class="btnReplyUpdate" ' + disabled + '>수정</button>'
							+ '<button class="btnReplyDelete" ' + disabled + '>삭제</button>'
							+ '<br>'
							+ '</pre>'
							+ '</div>';
					}); // end each()
					
					list +=  '<div>'
						+ memberNickname
						+ '&nbsp;&nbsp;'
						+ '<input type="text" class="uProductReplyContent" required>'
						+ '&nbsp;&nbsp;'
						+ '<button class="btnReplyAdd">작성</button>' 
						+ '</div>'
					uproduct_comment_item.append('<div class="replies">' + list + '</div>');	
						
			              var fragment = window.location.hash;
			        	   if (fragment) {
			        	        	
			        	       fragment = fragment.replace('#', '');
			        	       var fragmentList = fragment.split(',');
			        	       
				        	   if (fragmentList[1] > 0) {
				        		   var targetReplyElement = $('.uProductReplyId[value="' + fragmentList[1] + '"]').closest('.reply_item')[0];
				        		   
				        		   targetReplyElement.style.backgroundColor = 'lightgray';
				        		   targetReplyElement.style.border = '2px solid';
			        		       targetReplyElement.scrollIntoView({ behavior: 'smooth', block: 'center' });
									
			        		       setTimeout(function() {
			        		    	   targetReplyElement.style.backgroundColor = '';
			        		    	   targetReplyElement.style.border = '';
				    	           }, 2000);  // 2초 후에 스타일 초기화
			        		       
			        		       if (window.location.hash) {
				        			    history.replaceState('', document.title, window.location.pathname + window.location.search);
				        			}
				        	   }
			        	   }
				}
			); // end getJSON()
		} // end getAllReplies()
		
		$(document).on('click', '.btnReplyAdd', function(){
			console.log(this);
			var commentItem = $(this).closest('.uproduct_comment_item');
			var uProductCommentId = $(this).closest('.uproduct_comment_item').find('.uProductCommentId');
			var uProductCommentIdVal = $(this).closest('.uproduct_comment_item').find('.uProductCommentId').val();
		    var memberNickname = $('#memberNickname').val();
		    var uProductReplyContent = $(this).prevAll('.uProductReplyContent').val();

            var commentRegisterNick = commentItem.find('.commentRegisterNickname').val();
            var commentContent = commentItem.find('.uProductCommentContent').val();
            var uProductId = $('#uProductId').val();
		    
			var obj = {
					'uProductCommentId' : uProductCommentIdVal, 
					'memberNickname' : memberNickname,
					'uProductReplyContent' : uProductReplyContent,
					'uProductId' : uProductId
					
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
                                commentRegisterNick + "," + "새 답글" + "," + "중고상품" + "," +
                                uProductReplyContent + "," +
                                memberNickname + "," + commentContent + "," + uProductId
                            );
						getAllReplies(uProductCommentId);
					}
				}
			}); // end ajax()
		}); // end btnReplyAdd.click()
		
		
		  $(document ).on('click', '.btnReplyUpdate', function(){
	        	 console.log(this);
	        	 var commentItem = $(this).closest('.uproduct_comment_item');
	        	 var uProductCommentId = $(this).closest('.uproduct_comment_item').find('.uProductCommentId');
	        	 console.log("아좀 제발" + uProductCommentId.val());
	        	 var uProductReplyId = $(this).prevAll('.uProductReplyId').val();
	        	 var uProductReplyContent = $(this).prevAll('.uProductReplyContent').val();
	        	 console.log("선택된 답글 번호 : " + uProductReplyId + ", 답글 내용 : " + uProductReplyContent);
	        	 
	        	 // ajax
	        	 $.ajax({
	        		 type : 'PUT',
	        		 url : 'replies/' + uProductReplyId,
	        		 headers : {
	        			 'Content-Type' : 'application/json'
	        		 },
	        		 data : uProductReplyContent,
	        		 success : function(result){
	        			 console.log(result);
	        			 if(result == 1){
	        				 alert('답글 수정 성공!');
	        				 getAllReplies(uProudctCommentId);
	        			 }
	        		 } // end success
	        	 }) // end ajax
	         }) // end 대댓글 수정
	         
	         // 대댓글 삭제
	         $(document).on('click', '.btnReplyDelete', function(){
	        	 console.log(this);
	        	 var commentItem = $(this).closest('.uproduct_comment_item');
				 var uProudctCommentId = $(this).closest('.uproduct_comment_item').find('.uProudctCommentId');
				 var uProductReplyId = $(this).prevAll('.uProductReplyId').val();
				 console.log("선택된 댓글 번호 : " + uProductReplyId);
				 
				 // ajax
				 $.ajax({
					 type : 'DELETE',
					 url : 'replies/' + uProductReplyId,
					 headers : {
						 'Content-Type' : 'application/json'
					 },
					 success : function(result) {
						 console.log(result);
						 if(result == 1){
							 alert('답글 삭제 성공!');
							 getAllReplies(uProudctCommentId);
						 }
					 } // end success
				 }) // end ajax
	         }); // end 대댓글 삭제
		
	
		$(document).ready(function() {
		    var likeBtn = $('#likeBtn');

		    likeBtn.click(function() {
		        var uProductId = $('#uProductId').val();
		        var memberId = $('#memberId').val();
		        console.log(uProductId);
		        console.log(memberId);

		        if ($(this).text() === '좋아요') { 
		            $.ajax({
		                type: 'POST',
		                url: 'likes',
		                headers: {
		                    'Content-Type': 'application/json'
		                },
		                data: JSON.stringify({ uProductId: uProductId, memberId: memberId }),
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
		                url: 'likes/' + uProductId + '/' + memberId,
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
		
	</script>	
	<%@ include file="footer.jsp"%>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/calla/resources/js/scripts.js"></script>
  </body>
</html>