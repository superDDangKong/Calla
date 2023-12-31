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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container">
					<br> 
					<a href="list?page=${page }">
						<input type="button" class="btn btn-secondary float-right" value="글 목록">
					</a> 
					<br>
					<div class="post-detail">
						<br>
						<h2>${vo.fBoardTitle }</h2>
						<input type="hidden" id="fBoardTitle" value="${vo.fBoardTitle}">
						<input type="hidden" id="registerNick" value="${vo.memberNickname}">

						<p>${vo.memberNickname }</p>
						<fmt:formatDate value="${vo.fBoardCreatedDate }" pattern="yyyy.MM.dd. hh:mm" var="fBoardCreatedDate" />
						<p>${fBoardCreatedDate }</p>
						<hr>
						<div>
							<textarea style="width: 100%; height: 500px;" readonly>${vo.fBoardContent }</textarea>
						</div>

						<input type="hidden" id="fBoardId" name="fBoardId" value="${vo.fBoardId }">


						<c:set var="memberNickname" value="${memberNickname }" />
						<c:set var="voMemberNickname" value="${vo.memberNickname }" />

						<c:if test="${memberNickname == voMemberNickname}">
							<div class="d-flex">
								<div class="p-2">
									<a href="update?fBoardId=${vo.fBoardId}" class="btn btn-primary">글 수정</a>
								</div>
								<div class="p-2">
									<form action="delete" method="POST">
										<input type="hidden" id="fBoardId" name="fBoardId" value="${vo.fBoardId}"> 
										<input type="submit" value="글 삭제" class="btn btn-danger">
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
									<br> ${memberNickname}<br> 
									<input type="hidden" id="memberNickname" value=${memberNickname }>
									<div class="form-group">
										<textarea id="fBoardCommentContent" class="form-control" rows="1" placeholder="댓글 내용을 입력해 주세요" style="border: none;" required></textarea>
									</div>
									<div style="text-align: right;">
										<button id="btnCommentAdd" class="btn btn-dark">작성</button>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${memberNickname == null}">
							<br> 
							댓글 작성은 <a href="/calla/member/login?targetURL=/fBoard/detail?fBoardId=${vo.fBoardId }">로그인</a>이 필요합니다.
						</c:if>
						<hr>
						<br>
						
						<div>
							<div id="comments"></div>
						</div>

						<input type="hidden" id="pageMaker_hasPrev"	value="${pageMaker.hasPrev}"> 
						<input type="hidden" id="pageMaker_hasNext" value="${pageMaker.hasNext}"> 
						<input type="hidden" id="pageMaker_startPageNo" value="${pageMaker.startPageNo}">
						<input type="hidden" id="pageMaker_endPageNo" value="${pageMaker.endPageNo}">
						<input type="hidden" id="pageMaker_commentPage" value="1"> 
						<input type="hidden" id="pageMaker_commentNumsPerPage" value="${pageMaker.criteria.numsPerPage}"> 
						<br>
					</div>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>

<script type="text/javascript">
    $(document).ready(function () {
    	window.addEventListener('hashchange', function() {
	    	getAllComments();
    	});
        getAllComments();
		
       
        $('#btnCommentAdd').click(function () {
            var fBoardId = $('#fBoardId').val();
            var memberNickname = $('#memberNickname').val();
        	var fBoardCommentContent = $('#fBoardCommentContent').val();
            var obj = {
                'fBoardId': fBoardId,
                'memberNickname': memberNickname,
                'fBoardCommentContent': fBoardCommentContent
            };

            $.ajax({
                type: 'POST',
                url: 'comments',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(obj),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
                success: function (result) {
                    if (result == 1) {
                        alert('댓글 입력 성공');
                        socket.send(
                            $('#registerNick').val() + "," + "새 댓글" + "," + "자유게시판" + "," +
                            fBoardCommentContent + "," +
                            memberNickname + "," + $('#fBoardTitle').val() + "," + fBoardId
                        );
                        getAllComments();
                    }
                }
            });
        });
        
        
        function getAllComments() {
        	console.log("getAllComments 호출");
            var fBoardId = $('#fBoardId').val();
            var memberNickname = $('#memberNickname').val();
            var commentPage = $('#pageMaker_commentPage').val();
            var commentNumsPerPage = $('#pageMaker_commentNumsPerPage').val();
            var url = 'comments/all/' + fBoardId + '/' + commentPage + '/' + commentNumsPerPage;
			console.log(fBoardId + ',' + commentPage + ',' + commentNumsPerPage);
            $('#loadingContainer').remove();

            var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
            $('body').append(loadingContainer);
            $('#loadingContainer').css('display', 'block');
            
            $.getJSON(url, function (data) {
                var pageMaker_hasPrev = Boolean(data.pageMaker.hasPrev);
                var pageMaker_hasNext = Boolean(data.pageMaker.hasNext);
                $('#pageMaker_startPageNo').val(data.pageMaker.startPageNo);
                $('#pageMaker_endPageNo').val(data.pageMaker.endPageNo);
                var pageMaker_startPageNo = +$('#pageMaker_startPageNo').val();
                var pageMaker_endPageNo = +$('#pageMaker_endPageNo').val();

                var list = '';

                $(data.list).each(function () {
                	
                    var fBoardCommentCreatedDate = new Date(this.fBoardCommentCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' });

                    list += '<div class="comment_item">'
                        + '<input type="hidden" class="fBoardCommentId" value="' + this.fBoardCommentId + '">'
                        + '<input type="hidden" class="commentRegisterNickname" value="' + this.memberNickname + '">'
                        + this.memberNickname
                        + '<br>'
                        + '<textarea class="form-control bg-white fBoardCommentContent" rows="1" style="border:none;" readonly>'
                        + this.fBoardCommentContent
                        + '</textarea>'
                        + fBoardCommentCreatedDate
                        + '<br>'
                        if (memberNickname == this.memberNickname) {
	                       list += '<button class="btnCommentDelete">삭제</button>'
                        }
                        list += '<button class="btnReply">답글</button>'
	                        + '<br>'
	                        + '<hr>'
	                        + '</div>';
                }); // end each;
                list += '<div style="text-align: center;">'
                    + '<ul class="pagination justify-content-center" id="comment_page">';

                if (pageMaker_hasPrev) {
                    list += '<li class="text-secondary" style="margin-right: 5px"><button class="btn_comment_prev">◀</button></li>';
                }

                for (var num = pageMaker_startPageNo; num <= pageMaker_endPageNo; num++) {
                    list += '<li class="text-secondary" style="margin-right: 5px"><button class="btn_comment_page" value=' + num + '>'
                        + num
                        + '</button></li>';
                }
                if (pageMaker_hasNext) {
                    list += '<li class="text-secondary" style="margin-right: 5px"><button class="btn_comment_next">▶</button></li>';
                }

                list += '</ul>' + '</div>';
                $('#comments').html(list);
                
        		var fragment = window.location.hash;
    	        if (fragment) {
    	        	
    	        	fragment = fragment.replace('#', '');
    	        	var fragmentList = fragment.split(',');
    	        	
    	        	var targetCommentElement = $('.fBoardCommentId').filter('[value="' + fragmentList[0] + '"]');
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
            })//end getJSON
            .always(function() {
                $('#loadingContainer').css('display', 'none');
              })
        } // end getAllComents

        $(document).on('click', '.btn_comment_page', function () {
            $('#pageMaker_commentPage').val($(this).val());
            getAllComments();
        });

        $(document).on('click', '.btn_comment_prev', function () {
            $('#pageMaker_commentPage').val(+$('#pageMaker_startPageNo').val() - 1);
            getAllComments();
        });

        $(document).on('click', '.btn_comment_next', function () {
            $('#pageMaker_commentPage').val(+$('#pageMaker_endPageNo').val() + 1);
            getAllComments();
        });


        $(document).on('click', '.comment_item .btnCommentDelete', function () {
            var fBoardId = $('#fBoardId').val();
            var fBoardCommentId = $(this).prevAll('.fBoardCommentId').val();

            $.ajax({
                type: 'DELETE',
                url: 'comments/' + fBoardCommentId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: fBoardId,
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
                success: function (result) {
                    if (result == 1) {
                        alert('댓글 삭제 성공!');
                        getAllComments();
                    }
                }
            });
        });

        $('#comments').on('click', '.comment_item .btnReply', function () {
            var fBoardCommentId = $(this).closest('.comment_item').find('.fBoardCommentId');
            var replies = $(this).closest('.comment_item').find('.replies');
            if(replies.children().length === 0) {
            	getAllReplies(fBoardCommentId);
            } else {
            	replies.html("");
            }
            	
        });

							
        function getAllReplies(fBoardCommentId) {
            $('.replies').html('');
            var url = 'replies/all/' + fBoardCommentId.val();
            var comment_item = fBoardCommentId.closest('.comment_item');

            $('#loadingContainer').remove();

            var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
            $('body').append(loadingContainer);
            $('#loadingContainer').css('display', 'block');
            
            $.getJSON(url, function (data) {
                var memberNickname = $('#memberNickname').val();
                var list = '';

                $(data).each(function () {

                    var fBoardReplyCreatedDate = new Date(this.fBoardReplyCreatedDate).toLocaleDateString('ko-KR', { year: 'numeric',                     month: '2-digit', day: '2-digit' });

                    list += '<div class="reply_item bg-light border">'
                        + '<pre>'
                        + '<input type="hidden" class="fBoardReplyId" value="' + this.fBoardReplyId + '">'
                        + 'ㄴ  '
                        + this.memberNickname
                        + '<br>'
                        + '&nbsp&nbsp'
                        + '<textarea class="fBoardReplyContent form-control bg-light" rows="1" style="border:none;" readonly>'
                        + '&nbsp&nbsp'
                        + this.fBoardReplyContent
                        + '</textarea>'
                        + '<br>'
                        + '&nbsp&nbsp&nbsp&nbsp'
                        + fBoardReplyCreatedDate
                        + '<br>'
                        + '&nbsp&nbsp&nbsp&nbsp'
                        if (memberNickname == this.memberNickname) {
	                        list += '<button class="btnReplyDelete">삭제</button>'
                        }
                        list += '<br>'
                        + '</pre>'
                        + '</div>';
                }); // end each
				if(memberNickname != null) {
	                list += memberNickname
	                    + '<br>'
	                    + '<div class="form-group bg-transparent border">'
	                    + '<textarea class="fBoardReplyContentReg form-control" rows="1" placeholder="답글 내용을 입력해 주세요." style="border:none;" required>'
	                    + '</textarea> </div>'
	                    + '<div style="text-align:right;">'
	                    + '<button class="btnReplyAdd btn btn-dark">작성</button>'
	                    + '</div>' + '<hr>'
	                    + '<br>'
				} else {
					list += '답글 작성은 <a href="/calla/member/login?targetURL=/fBoard/detail?fBoardId=' + $("#fBoardId").val() + '">로그인</a>이 필요합니다.'
						+ '<hr><br>'
				}

                comment_item.append('<div class="replies bg-light">'
                    + list
                    + '</div>');
              var fragment = window.location.hash;
        	   if (fragment) {
        	        	
        	       fragment = fragment.replace('#', '');
        	       var fragmentList = fragment.split(',');
        	       
	        	   if (fragmentList[1] > 0) {
	        		   var targetReplyElement = $('.fBoardReplyId[value="' + fragmentList[1] + '"]').closest('.reply_item')[0];
	        		   
	        		   targetReplyElement.classList.remove('bg-light');
	        		   targetReplyElement.classList.remove('border');
	        		   targetReplyElement.style.backgroundColor = 'lightgray';
	        		   targetReplyElement.style.border = '2px solid';
        		       targetReplyElement.scrollIntoView({ behavior: 'smooth', block: 'center' });
						
        		       setTimeout(function() {
        		    	   targetReplyElement.style.backgroundColor = '';
        		    	   targetReplyElement.style.border = '';
		    	           targetReplyElement.classList.add('bg-light');
		        		   targetReplyElement.classList.add('border');
	    	           }, 2000);  // 2초 후에 스타일 초기화
        		       
        		       if (window.location.hash) {
	        			    history.replaceState('', document.title, window.location.pathname + window.location.search);
	        			}
	        	   }
        	   }
            })
            .always(function() {
                $('#loadingContainer').css('display', 'none');
              })// end getJson
        } // end getAllReplies

        $(document).on('click', '.btnReplyAdd', function () {
            var commentItem = $(this).closest('.comment_item');
            var fBoardCommentId = commentItem.find('.fBoardCommentId');
            var fBoardCommentIdVal = fBoardCommentId.val();
            var memberNickname = $('#memberNickname').val();
            var fBoardReplyContent = commentItem.find('.fBoardReplyContentReg').val();

            var commentRegisterNick = commentItem.find('.commentRegisterNickname').val();
            var commentContent = commentItem.find('.fBoardCommentContent').val();
            var fBoardId = $('#fBoardId').val();
            var obj = {
                'fBoardCommentId': fBoardCommentIdVal,
                'memberNickname': memberNickname,
                'fBoardReplyContent': fBoardReplyContent
            };

            $.ajax({
                type: 'POST',
                url: 'replies',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(obj),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
                success: function (result) {
                    if (result == 1) {
                        alert('답글 입력 성공');
                        socket.send(
                                commentRegisterNick + "," + "새 답글" + "," + "자유게시판" + "," +
                                fBoardReplyContent + "," +
                                memberNickname + "," + commentContent + "," + fBoardId
                            );
                        getAllReplies(fBoardCommentId);
                    }
                }
            });
        });

        $(document).on('click', '.btnReplyDelete', function () {
            var commentItem = $(this).closest('.comment_item');
            var fBoardCommentId = commentItem.find('.fBoardCommentId');
            var fBoardReplyId = $(this).prevAll('.fBoardReplyId').val();

            $.ajax({
                type: 'DELETE',
                url: 'replies/' + fBoardReplyId,
                headers: {
                    'Content-Type': 'application/json'
                },
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
                success: function (result) {
                    if (result == 1) {
                        alert('답글 삭제 성공!');
                        getAllReplies(fBoardCommentId);
                    }
                }
            });
        });
    });
</script>
	<%@ include file="../footer.jspf"%>
</body>
</html>

