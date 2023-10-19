<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
	
</script>
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
	
	<a href="update?productId=${vo.productId }&page=${page }"><input type="button" value="글 수정"></a>
	<form action="delete" method="POST">
		<input type="hidden" id="productId" name="productId" value="${vo.productId }">
		<input type="submit" value="상품 삭제">
	</form>
	
	<hr>
	<div>
		<input type="text" id="memberNickname" >
        <input type="text" id="productCommentContent">
        <button id="btnAdd">작성</button>
	</div>
	<hr>
	<div id="productComments"></div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			getAllProductComments();
			
			$('#btnAdd').click(function(){
				var productId = $('#productId').val();
				var memberNickname = $('#memberNickname').val();
				var productCommentContent = $('#productCommentContent').val();
				var obj = {
						'productId' : productId,
						'memberNickname' : memberNickname,
						'productCommentContent' : productCommentContent
				}
				console.log(obj);
				
				$.ajax({
					type : 'POST',
					url : 'productComments',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj),
					success : function(result){
						console.log(result);
						if(result == 1){
							alert('댓글 입력 성공');
							getAllProductComments();
						}
					}
				});
			}); // end btnAdd
			
			function getAllProductComments(){
				var productId = $('#productId').val();
				
				var url = 'productComments/all/' + productId;
				$.getJSON(
						url,
						function(data){
							console.log(data);
							
							var memberNickname = $('#memberNickname').val();
							var list = '';
							
							$(data).each(function(){
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
									+ '</pre>'
									+ '</div>';			
							});
							$('#productComments').html(list);
						});
			} // end getAllproductComments
			
			$("#productComments").on('click', '.product_comment_item .btn_update', function(){
				console.log(this);
				
				var productCommentId = $(this).prevAll('#productCommentId').val();
				var productCommentContent = $(this).prevAll('#productCommentContent').val();
				console.log("선택된 댓글 번호 : " + productCommentId + ", 댓글 내용 : " + productCommentContent);
				
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
				
				var productId = $('productId').val();
				var productCommentId = $(this).prevAll('#productCommentId').val();
				console.log("선택된 댓글 번호 : " + productCommentId);
				
				$.ajax({
					type : 'DELETE',
					url : 'productComments/' + productCommentId,
					headers : {
						'Content-Type' : 'application/json'
					},
					data : productCommentId,
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
	</script>
</body>


</html>