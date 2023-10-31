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
        alert("주문이 완료되었습니다!")
      }
    </script>
    <title>${vo.uProductName }</title>
  </head>
<%@ include file="header.jsp"%>
  <body>
    <div class="wrap">
      <div class="product-img">
      <img src="display?fileName=${vo.uProductImagePath}" width="450px" height="300px">
      </div>
      <div class="product-desc">
        <h2>
          이쁜 핸드폰 케이스 팝니다
          <span class="price"> 가격:5,000</span>
        </h2>
        <p>${vo.uProductContent }</p>
        <p>가격 문의는 비밀 댓글로 해주세요</p>
      </div>
      <div class="item-order">
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">작성자</span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
          />
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <label class="input-group-text" for="inputGroupSelect01">수량</label>
          </div>
          <select class="custom-select" id="inputGroupSelect01">
            <option selected>-- 수량을 선택하세요 --</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
          </select>
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">주소</span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            value = ${vo.memberAddress }
            readonly
          />
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">전화번호</span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Default"
            aria-describedby="inputGroup-sizing-default"
            value = ${vo.memberAddress }
            readonly
          />
        </div>
        <button type="button" onclick="order()" class="btn btn-primary btn-order">
          주문하기
        </button>
      </div>
    </div>
    
    <br>
	<br>
	<br>
    
    <a href="list?page=${page }"><input type="button" value="상품 목록" style="float:right;"></a>
    
	<a href="update?uProductId=${vo.uProductId }&page=${page }"><input type="button" value="글 수정" style="float:right;"></a>
	
	
	<form action="delete" method="POST">
		<input type="hidden" id="uProductId" name="uProductId" value="${vo.uProductId }">
		<input type="submit" value="상품 삭제" style="float:right;">
	</form>
	
	<br>
	<br>
	<br>
	
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