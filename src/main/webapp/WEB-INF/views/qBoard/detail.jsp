<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

.content {
	border: 1px solid black; /* 테두리 스타일 설정 (1픽셀 두께의 검은색 실선) */
  	width: 900px; /* 너비 설정 */
  	height: 400px; /* 높이 설정 */
  	border-radius: 10px; /* 테두리 모서리를 10픽셀만큼 둥글게 설정 */
}
ul {
	list-style-type: none;
}

li {
	display: inline-block;
}

.comment_item {
    margin-bottom: 20px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.3);
}

.comment-header {
	display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.comment-date {
    margin-left: 10px;
    color: #777;
}

.comment-content {
    margin-bottom: 10px;
}

.comment-content textarea {
    width: 100%;
    resize: none; /* Disable textarea resizing */
}

/* 버튼 스타일링 */
.btn {
    margin-right: 5px;
}

/* 항목 간 간격 추가 */
hr {
    margin-top: 15px;
    margin-bottom: 15px;
}

.comment-buttons {
    text-align: right; 
}

.comment-buttons button {
    margin-left: 5px; 
}

.reply_item {
	margin-bottom: 20px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.3);
}

.reply-header {
	display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.reply-date {
	margin-left: 10px;
    color: #777;
}

.reply-content {
	margin-bottom: 10px;
}

.reply-content textarea {
	width: 100%;
    resize: none; /* Disable textarea resizing */
}

.reply-buttons {
    text-align: right; 
}

.reply-buttons button {
    margin-left: 5px; 
}

/* 공통 스타일 */
    input[type="submit"],
    input[type="button"] {
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
      border: none;
      border-radius: 5px;
      transition: background-color 0.3s, color 0.3s, border-color 0.3s;
    }

    /* 삭제 버튼 스타일 */
    #boardDelete {
      background-color: #dc3545; /* 삭제 버튼 배경색 */
      color: #fff; /* 삭제 버튼 텍스트 색상 */
    }

    /* 목록 버튼 스타일 */
    input[type="button"][href="list"] {
      background-color: #007bff; /* 목록 버튼 배경색 */
      color: #fff; /* 목록 버튼 텍스트 색상 */
      text-decoration: none;
    }

    /* 수정 버튼 스타일 */
    #boardUpdate {
      background-color: #28a745; /* 수정 버튼 배경색 */
      color: #fff; /* 수정 버튼 텍스트 색상 */
      text-decoration: none;
    }

    /* 호버 효과 */
    input[type="submit"]:hover,
    input[type="button"]:hover {
      filter: brightness(90%); /* 호버 시 색상을 조금 어둡게 */
    }
</style>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>${vo.qBoardTitle }</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link href="../resources/css/styles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf" %>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container">
				<br><br><br><br><br>
					<h2>글 보기</h2>
					<%-- <div>
						<p style="">글 번호 : ${vo.qBoardId }</p>
					</div> --%>
					<div>
						<p>제목 : ${vo.qBoardTitle }</p>
						<input type="hidden" id="qBoardTitle" value="${vo.qBoardTitle}">
						<input type="hidden" id="registerNick" value="${vo.memberNickname}">
						
					</div>
					<div>
						<p id="Writer" >${vo.memberNickname }</p>
						<c:set var="qBoardCreatedDate" value="${vo.qBoardCreatedDate}" />
						<fmt:formatDate value="${qBoardCreatedDate}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate" />
						<p>작성일 : ${formattedDate}</p>
					</div>
					<div class="content">
						<c:if test="${not empty vo.qBoardImagePath && vo.qBoardImagePath.indexOf('.') != -1}">
							<img src="display?fileName=${vo.qBoardImagePath }" id="img">
						</c:if>
						<div style="">
							<p id="textContent">${vo.qBoardContent }</p>
							
						</div>
						
					</div>
					<input type="hidden" id="qBoardId" name="qBoardId" value="${vo.qBoardId }"> 
					<a href="list?page=${page }"><input type="button" value="글 목록"></a>
					<c:set var="memberNickname" value="${memberNickname }" />
					<c:set var="voMemberNickname" value="${vo.memberNickname }" />
					<c:if test="${memberNickname == voMemberNickname}">
						<div class="d-flex">
							<div class="p-2">
								<a id="boardUpdate" href="update?qBoardId=${vo.qBoardId }&page=${page }"><input type="button" value="글 수정"></a>
							</div>
							<div class="p-2">
								<form action="delete" method="POST">
									<input type="hidden" id="qBoardId" name="qBoardId" value="${vo.qBoardId }"> 
									<input id="boardDelete" type="submit" value="글 삭제"> 
								</form>
							</div>
						</div>
					</c:if>
					<c:if test="${memberNickname != null}">
						<div>
						<br><br>
							<div class="border">
								${memberNickname}<br><br> 
								<input type="hidden" name="memberNickname" id="memberNickname" value=${memberNickname }>
								<div class="form-group">
									<textarea id="qBoardCommentContent" class="form-control" rows="4" placeholder="댓글 내용을 입력해 주세요" style="border: none;" required></textarea>
								</div>
								<div style="text-align: right;">
								<button id="btnCommentAdd" class="btn btn-dark">작성</button>
								</div>
							</div>
						</div>
					</c:if>
						
					
						<c:if test="${memberNickname == null}">
							<br> 
							댓글 작성은 <a href="/calla/member/login?targetURL=/qBoard/detail?qBoardId=${vo.qBoardId }">로그인</a>이 필요합니다.
						</c:if>
						<hr>
						<br>
						<div style="text-align: center;">
							<div id="comments"></div>
							
							<input type="hidden" id="pageMaker_hasPrev" value="${pageMaker.hasPrev}"> 
							<input type="hidden" id="pageMaker_hasNext" value="${pageMaker.hasNext}"> 
							<input type="hidden" id="pageMaker_startPageNo"	value="${pageMaker.startPageNo}"> 
							<input type="hidden" id="pageMaker_endPageNo" value="${pageMaker.endPageNo}">
							<input type="hidden" id="pageMaker_commentPage"	value="${pageMaker.criteria.page}"> 
							<input type="hidden" id="pageMaker_commentNumsPerPage" value="${pageMaker.criteria.numsPerPage}">
							<br>
						</div>
					</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<script type="text/javascript">
      $(document).ready(function() {
      	window.addEventListener('hashchange', function() {
	    	getAllComments();
    	});
    	  var img = $('#img').val();
    	  var date = $('#date').val();
    	  
          getAllComments();
    	  console.log(img);
          
         $('#btnCommentAdd').click(function(){ // 댓글작성 버튼 클릭 할 때 함수
            var qBoardId = $('#qBoardId').val(); // 게시판 번호 데이터
            var memberNickname = $('#memberNickname').val(); // 작성자 데이터
            var qBoardCommentContent = $('#qBoardCommentContent').val(); // 댓글 내용
            console.log("입력한댓글"+qBoardCommentContent);
            var obj = {
                  'qBoardId' : qBoardId, // 게시글 번호
                  'memberNickname' : memberNickname, // 회원닉네임
                  'qBoardCommentContent' : qBoardCommentContent // 게시글 댓글 내용
            } // obj	
            console.log(obj);
            if(qBoardCommentContent.trim() === '') {
            	alert("공백은 댓글입력이 안됩니다.")
            	return;
            }
	            $.ajax({
	               type : 'POST',
	               url : 'comments',
	               headers : {
	                  'Content-Type' : 'application/json'
	               },
	               data : JSON.stringify(obj), // JSON으로 변환
	               success : function(result){
	                  console.log(result);
	                  if(result == 1) {
	                     console.log('댓글 입력 성공');
	                     $('#qBoardCommentContent').val(' ');
	                     socket.send(
		                     $('#registerNick').val() + "," + "새 댓글" + "," + "문의게시판" + "," +
		                     qBoardCommentContent + "," +
		                     memberNickname + "," + $('#qBoardTitle').val() + "," + qBoardId
		                	 );
	                     getAllComments();
	                  }
	               }
	            }); // end ajax()   
         }); // end btnAdd.click()
         
         // 게시판 댓글 전체 가져오기
         function getAllComments(){
            var qBoardId = $('#qBoardId').val();
            var memberNickname = $('#memberNickname').val();
            var commentPage = $('#pageMaker_commentPage').val();
            var commentNumsPerPage = $('#pageMaker_commentNumsPerPage').val();
            var url = 'comments/all/' + qBoardId + '/' + commentPage + '/' + commentNumsPerPage;
            
            $.getJSON(url, function(data) {
            	var pageMaker_hasPrev = Boolean(data.pageMaker.hasPrev);
                var pageMaker_hasNext = Boolean(data.pageMaker.hasNext);
                $('#pageMaker_startPageNo').val(data.pageMaker.startPageNo);
                $('#pageMaker_endPageNo').val(data.pageMaker.endPageNo);
                var pageMaker_startPageNo = +$('#pageMaker_startPageNo').val();
                var pageMaker_endPageNo = +$('#pageMaker_endPageNo').val(); 
                // data : 서버에서 전송받은 list 데이터가 저장되어 있음.
                // getJSON()에서 json 데이터는
                // javascript object로 자동 parsing됨.
                var memberNickname = $('#memberNickname').val();
                var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
                  
                  // $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수. 사실 걍 for문 써도 됌
                  $(data.list).each(function(){
                     // this : 컬렉션의 각 인덱스 데이터를 의미
                     
                     var qBoardCommentCreatedDate = new Date(this.qBoardCommentCreatedDate);
                     var formattedDate = qBoardCommentCreatedDate.getFullYear() + '-' +
                     ('0' + (qBoardCommentCreatedDate.getMonth() + 1)).slice(-2) + '-' +
                     ('0' + qBoardCommentCreatedDate.getDate()).slice(-2) + ' ' +
                     ('0' + qBoardCommentCreatedDate.getHours()).slice(-2) + ':' +
                     ('0' + qBoardCommentCreatedDate.getMinutes()).slice(-2) + ':' +
                     ('0' + qBoardCommentCreatedDate.getSeconds()).slice(-2);
               
                     
                     list += '<div class="comment_item">'
                           + '<input type="hidden" class="qBoardCommentId" value="' + this.qBoardCommentId +'">'
                           + '<div class="comment-header">'
                           + '<strong>' + this.memberNickname + '</strong>'
                           + '<input type="hidden" class="commentRegisterNickname" value="' + this.memberNickname + '">'
                           + '<span class="comment-date">' + formattedDate + '</span>'
                           + '</div>'
                           + '<div class="comment-content">'
                           + '<textarea class="form-control qBoardCommentContent" rows="2" style="border:none;">'
						   + this.qBoardCommentContent
						   + '</textarea>'
						   + '</div>'
						   + '<div class="comment-buttons">'
                           
						// 현재 댓글 작성자와 로그인한 사용자의 닉네임이 일치하는 경우에만 수정 및 삭제 버튼을 표시
						   if (memberNickname === this.memberNickname) {
						       list += '<button class="btn btn-sm btn_update">수정</button>'
						           + '<button class="btn btn-sm btn_delete">삭제</button>';
						   }

						   list += '<button class="btn btn-sm btnReply">답글</button>'
						       + '</div>'
						       + '</div>';
						       
						   
                  }); // end each()
                list += '<div style="text-align: center;">'
                	+ '<ul id="comment_page">'
                	
			   if (pageMaker_hasPrev) {
	               list += '<li><button class="btn_comment_prev">이전</button></li>';
	           }

	            for (var num = pageMaker_startPageNo; num <= pageMaker_endPageNo; num++) {
	                list += '<li><button class="btn_comment_page" value=' + num + '>'
                    + num
                    + '</button></li>';
                }

			    if (pageMaker_hasNext) {
			    	list += '<li><button class="btn_comment_next">이후</button></li>';
			    }
			                
			    list += '</ul>' + '</div>';  
                  
                
                $('#comments').html(list);
                  
                var fragment = window.location.hash;
    	        if (fragment) {
 	        	   
    	        	fragment = fragment.replace('#', '');
    	        	var fragmentList = fragment.split(',');
    	        	
    	        	var targetCommentElement = $('.qBoardCommentId').filter('[value="' + fragmentList[0] + '"]');
    	        	console.log("targetElement" + targetCommentElement.length)
    	        	
	    	           if (targetCommentElement.length > 0 && fragmentList[1] == 0) {
		    	           var target = targetCommentElement[0].parentElement;
	    	        	   console.log("target = " + target)
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
               }); // end getJSON()
         } // end getAllComment()
         
         $(document).on('click', '.btn_comment_page', function () {
             $('#pageMaker_commentPage').val($(this).val());
             getAllComments();
         });

         $(document).on('click', '.btn_comment_prev', function () {
             $('#pageMaker_commentPage').val(+$('#pageMaker_startPageNo').val() - 1);
             console.log("이전버튼눌림");
             getAllComments();
         });

         $(document).on('click', '.btn_comment_next', function () {
             $('#pageMaker_commentPage').val(+$('#pageMaker_endPageNo').val() + 1);
             getAllComments();
         });         
         
         // 수정 버튼을 클릭하면 선택된 댓글 수정
         $("#comments").on('click','.comment_item .btn_update', function(){
            console.log(this);
            var qBoardCommentId = $(this).closest('.comment_item').find('.qBoardCommentId').val();
            var qBoardCommentContent = $(this).closest('.comment_item').find('.form-control.qBoardCommentContent').val();
			
            console.log('qBoardCommentId:', qBoardCommentId);
            console.log('qBoardCommentContent:', qBoardCommentContent);
          
            
           
            // ajax 요청
            $.ajax({
               type : 'PUT',
               url : 'comments/' + qBoardCommentId,
               headers : {
                   'Content-Type' : 'application/json'
                },
               data : qBoardCommentContent,
               success : function(result) {
                  console.log(result);
                  if(result == '1') {
                     alert('댓글 수정 성공')
                     getAllComments();
                  }
               }
               
               
            }); // end ajax()
             
         }); // end 수정
         
         
         // 삭제 버튼을 클릭하면 선택된 댓글 삭제
         $("#comments").on('click','.comment_item .btn_delete', function(){
            console.log(this);
            
            var qBoardId = $('#qBoardId').val();
            var qBoardCommentId = $('.qBoardCommentId').val();
            console.log("선택된 댓글 번호 : " + qBoardCommentId);
            
            // ajax 요청
            $.ajax({
               type : 'DELETE',
               url : 'comments/' + qBoardCommentId,
               headers : {
                   'Content-Type' : 'application/json'
                },
               data : qBoardId,
               success : function(result) {
                  console.log(result);
                  if(result == 1) {
                     alert('댓글 삭제 성공')
                     getAllComments();
                  }
               }
               
               
            }); // end ajax()
            
         }); // end comment.on()
         
         // btnReply 답글들 불러오기, btnReplyAdd 답글작성버튼
		$('#comments').on('click', '.comment_item .btnReply', function(){ // 대댓글 입력
			/* if($('#memberNickname').val() == null) { // 로그인 확인
				alert('답글을 작성하려면 로그인 해 주세요')
				return;
			} */
			console.log(this); // this 멤버닉네임?
			var qBoardCommentId = $(this).closest('.comment_item').find('.qBoardCommentId');
			console.log(qBoardCommentId);
			getAllReplies(qBoardCommentId);
         }) // end btnReply.click()
         
      // 게시판 대댓글 전체 가져오기
         function getAllReplies(qBoardCommentId){
  
            $('.replies').html('');
            
            console.log("getAllReplies() 호출");
            console.log("getAllReplies() 호출" + qBoardCommentId.val());
            
            var url = 'replies/all/' + qBoardCommentId.val();
            var comment_item = qBoardCommentId.closest('.comment_item');
            $.getJSON( // 머지이거
               url,
               function(data) {
                  // data : 서버에서 전송받은 list 데이터가 저장되어 있음.
                  // getJSON()에서 json 데이터는
                  // javascript object로 자동 parsing됨.
                  console.log(data);
               
                  var memberNickname = $('#memberNickname').val();
                  var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
                  
                  // $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수. 사실 걍 for문 써도 됌
                  $(data).each(function(){
                     // this : 컬렉션의 각 인덱스 데이터를 의미
                     console.log(this);
                     
                     var qBoardReplyCreatedDate = new Date(this.qBoardReplyCreatedDate);
                  // 날짜를 원하는 형식으로 형식화
                     var formattedDate = qBoardReplyCreatedDate.getFullYear() + '-' +
                                        ('0' + (qBoardReplyCreatedDate.getMonth() + 1)).slice(-2) + '-' +
                                        ('0' + qBoardReplyCreatedDate.getDate()).slice(-2) + ' ' +
                                        ('0' + qBoardReplyCreatedDate.getHours()).slice(-2) + ':' +
                                        ('0' + qBoardReplyCreatedDate.getMinutes()).slice(-2) + ':' +
                                        ('0' + qBoardReplyCreatedDate.getSeconds()).slice(-2);
                     var disabled = 'disabled';
                     var readonly = 'readonly';
                     
                     if(memberNickname == this.memberNickname) { // 로그인한 아이디 == 댓글작성한 아이디
                        disabled = '';
                        readonly = '';
                     }
                     var qBoardReplyId = $(this).closest('.reply_item').find('.qBoardReplyId').val();
                     console.log("After setting qBoardReplyId:", qBoardReplyId);
                     
                     list += '<div class="reply_item" style="border: 1px solid">'
                           + '<pre>'
                           + '<input type="hidden" class="qBoardReplyId" value="' + this.qBoardReplyId +'">'
                           + '<div class="reply-header">'
                           + '<strong>' + this.memberNickname + '</strong>'
                           + '<span class="reply-date">' + formattedDate + '</span>'
						   + '</div>'
						   + '<div class="reply-content">'
						   + '<textarea class="form-control qBoardReplyContent" rows="2" style="border:none;">'
						   + this.qBoardReplyContent
						   + '</textarea>'
						   + '</div>'
						   + '<div class="reply-buttons">'
						   /* + '<button class="btn btn-sm btnReplyUpdate">수정</button>'
                           + '<button class="btn btn-sm btnReplyDelete">삭제</button>' */
                           + '<br>'
                           + '</div>'
                           + '</pre>'
                           + '</div>';
						   /* if(memberNickname === this.memberNickname){
							   list += '<button class="btnReplyUpdate" ' + disabled + ' >수정</button>'
	                           + '<button class="btnReplyDelete" ' + disabled + ' >삭제</button>'
	                           + '<br>'
	                           + '</pre>'
	                           + '</div>'
	                           + '</div>';
						   } */
                           
                  }); // end each()
               	if(memberNickname != null) {
                  list += '<div style="text-align: center;">'
					  + memberNickname
					  + '&nbsp;&nbsp;'
					  + '<input type="text" class="qBoardReplyContentReg" required>'
					  + '&nbsp;&nbsp;'
					  + '<button class="btnReplyAdd">작성</button>' 
					  + '</div>'
               	} else {
               		list += '답글 작성은 <a href="/calla/member/login?targetURL=/qBoard/detail?qBoardId=' + $("#qBoardId").val() + '">로그인</a>이 필요합니다.'
					+ '<hr><br>'
               	}	
				  comment_item.append('<div class="replies">' + list + '</div>');	
                
					  
					  
               var fragment = window.location.hash;
           	   if (fragment) {
           	        	
           	       fragment = fragment.replace('#', '');
           	       var fragmentList = fragment.split(',');
           	       
   	        	   if (fragmentList[1] > 0) {
   	        		   var targetReplyElement = $('.qBoardReplyId[value="' + fragmentList[1] + '"]').closest('.reply_item')[0];
   	        		   
   	        		   targetReplyElement.style.backgroundColor = 'lightgray';
   	        		   targetReplyElement.style.border = '2px solid';
           		       targetReplyElement.scrollIntoView({ behavior: 'smooth', block: 'center' });
   						
           		       setTimeout(function() {
           		    	   targetReplyElement.style.backgroundColor = '';
           		    	   targetReplyElement.style.border = '1px solid';
   	    	           }, 2000);  // 2초 후에 스타일 초기화
           		       
           		       if (window.location.hash) {
   	        			    history.replaceState('', document.title, window.location.pathname + window.location.search);
   	        			}
   	        	   }
           	   }
               }); // end getJSON()
            }//end getAllReply()  
         
         // 대댓글 입력
         $(document).on('click', '.btnReplyAdd', function(){
        	console.log(this);
        	var commentItem = $(this).closest('.comment_item');
 			var qBoardCommentId = $(this).closest('.comment_item').find('.qBoardCommentId');
 			var qBoardCommentIdVal = $(this).closest('.comment_item').find('.qBoardCommentId').val();
 		    var memberNickname = $('#memberNickname').val();
 		    var qBoardReplyContent = $(this).closest('.comment_item').find('.qBoardReplyContentReg').val(); 
 		    console.log(memberNickname);
 		    console.log(qBoardCommentIdVal);
 		    console.log(qBoardReplyContent);
 		    
            var commentRegisterNick = commentItem.find('.commentRegisterNickname').val();
            var commentContent = commentItem.find('.qBoardCommentContent').val();
            var qBoardId = $('#qBoardId').val();
 		    var obj = {
 					'qBoardCommentId' : qBoardCommentIdVal, 
 					'memberNickname' : memberNickname,
 					'qBoardReplyContent' : qBoardReplyContent
 			};
 			console.log(obj);
 			

 			 $.ajax({
 				type : 'POST',
 				url : 'replies',
 				headers : {
 					'Content-Type' : 'application/json'
 				},
 				data : JSON.stringify(obj),
 				success : function(result){
 					console.log(result);
 					if(result == 1){
 						alert('답글 입력 성공');
                        socket.send(
                                commentRegisterNick + "," + "새 답글" + "," + "문의게시판" + "," +
                                qBoardReplyContent + "," +
                                memberNickname + "," + commentContent + "," + qBoardId
                            );
 						getAllReplies(qBoardCommentId);
 					} // end if
 				} // end success
 			}) // end ajax
         }) // end 대댓글 등록 
         
         // 대댓글 수정
         $(document ).on('click', '.btnReplyUpdate', function(){
        	 console.log(this);
        	 var commentItem = $(this).closest('.comment_item');
        	 var qBoardCommentId = $(this).closest('.comment_item').find('.qBoardCommentId');
        	 console.log("아좀 제발" + qBoardCommentId.val());
        	 var qBoardReplyId = $(this).prevAll('.qBoardReplyId').val();
        	 var qBoardReplyContent = $(this).prevAll('.qBoardReplyContent').val();
        	 console.log("선택된 답글 번호 : " + qBoardReplyId + ", 답글 내용 : " + qBoardReplyContent);
        	 
        	 // ajax
        	 $.ajax({
        		 type : 'PUT',
        		 url : 'replies/' + qBoardReplyId,
        		 headers : {
        			 'Content-Type' : 'application/json'
        		 },
        		 data : qBoardReplyContent,
        		 success : function(result){
        			 console.log(result);
        			 if(result == 1){
        				 alert('답글 수정 성공!');
        				 getAllReplies(qBoardCommentId);
        			 }
        		 } // end success
        	 }) // end ajax
         }) // end 대댓글 수정
         
         // 대댓글 삭제
         $(document).on('click', '.btnReplyDelete', function(){
        	 console.log(this);
        	 var commentItem = $(this).closest('.comment_item');
			 var qBoardCommentId = $(this).closest('.comment_item').find('.qBoardCommentId');
			 var qBoardReplyId = $(this).prevAll('.qBoardReplyId').val();
			 console.log("선택된 댓글 번호 : " + qBoardReplyId);
			 
			 // ajax
			 $.ajax({
				 type : 'DELETE',
				 url : 'replies/' + qBoardReplyId,
				 headers : {
					 'Content-Type' : qBoardReplyId,
				 },
				 success : function(result) {
					 console.log(result);
					 if(result == 1){
						 alert('답글 삭제 성공!');
						 getAllReplies(qBoardCommentId);
					 }
				 } // end success
			 }) // end ajax
         }); // end 대댓글 삭제
      }); // end document
   </script>
	
	<%@ include file="../footer.jspf"%>
</body>
</html>






















