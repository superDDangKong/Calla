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
<title>${vo.uProductName }</title>
</head>
<body>
	<h2>상품 보기</h2>
	<div>
		<p>작성자 : ${vo.memberNickname }</p>
	</div>
	
	<div>
		<p>상품 번호 : ${vo.uProductId }</p>
	</div>
	<div>
		<p>상품 이름 : ${vo.uProductName }</p>
	</div>
	<div>
		<p>상품 이미지 : </p>
		<img src="display?fileName=${vo.uProductImagePath}" width="100px" height="100px">
	</div>
	<div>
		<p>작성일 : ${vo.uProductCreatedDate }</p>
		<p>카테고리 : ${vo.uProductCategori }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.uProductContent }</textarea>
	</div>
	
	<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
	<a href="update?uProductId=${vo.uProductId }&page=${page }"><input type="button" value="글 수정"></a>
	
	
	<form action="delete" method="POST">
		<input type="hidden" id="uProductId" name="uProductId" value="${vo.uProductId }">
		<input type="submit" value="상품 삭제">
	</form>
	
	<hr>
	<div>
		<input type="text" id="memberNickname" value="${sessionScope.memberNickname }" readonly="readonly">
        <input type="text" id="uProductCommentContent">
        <button id="btnAdd">작성</button>
        <input type="checkbox" id ="uProductSecretComment" > 비밀 댓글
        
	</div>
	<hr>
	<div id="uproductcomments"></div>
	
	<script type="text/javascript">
		$(document).ready(function(){
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
									+'<input type="hidden" id="uProductCommentId" value="' + this.uProductCommentId + '">'
									+ this.memberNickname
									+ '&nbsp;&nbsp;'
									+'<input type="text" ' + readonly + ' id="uProductCommentContent" value="' + this.uProductCommentContent + '">'
									+ '&nbsp;&nbsp;'
									+ uProductCommentCreatedDate
									+ '&nbsp;&nbsp;'
									+'<button class="btn_update" ' + disabled + '> 수정</button>'
									+'<button class="btn_delete" ' + disabled + '> 삭제</button>'
									+ '</pre>'
									+ '</div>';			
							});
							$('#uproductcomments').html(list);
						});
			} // end getAllproductComments
			
			$("#uproductcomments").on('click', '.uproduct_comment_item .btn_update', function(){
				console.log(this);
				
				var uProductCommentId = $(this).prevAll('#uProductCommentId').val();
				var uProductCommentContent = $(this).prevAll('#uProductCommentContent').val();
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
				
				var uProductId = $('uProductId').val();
				var uProductCommentId = $(this).prevAll('#uProductCommentId').val();
				console.log("선택된 댓글 번호 : " + uProductCommentId);
			
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
	</script>	
	
	
	</body>


</html>