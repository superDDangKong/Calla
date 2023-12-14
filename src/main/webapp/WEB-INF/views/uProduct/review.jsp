<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
      integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"
    ></script>	
	
	
<%@ include file="../header.jspf"%>
<title>글 작성 페이지</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<form action="review" method="POST">
					<input type="hidden" name="sellerNickname" id="sellerNickname" value="${sellerNickname }">
					<input type="hidden" name="uProductId" id="uProductId" value="${uProductId }">
					<div>
						<p>제목 :</p>
						<input type="text" name="uProductReviewTitle" placeholder="제목 입력"
							required>
					</div>

					<br>

					<div>
						<p>작성자 :</p>
						<input type="text" name="memberNickname" id="memberNickname"
							value="${sessionScope.memberNickname }" readonly="readonly">
					</div>

					<br>

					<div>
						<p>내용 :</p>
						<textarea rows="15" cols="100" name="uProductReviewContent"
							placeholder="내용 입력"></textarea>
					</div>

					<div>
						<input type="submit" value="등록">
					</div>
				</form>

				<br>

				<button id="mannerupbtn" name="mannerupbtn" class="btn btn-primary btn-order"> 매너 칭찬하기 </button>
				<button id="mannerdownbtn" name="mannerupbtn" class="btn btn-primary btn-order"> 매너 비판하기 </button>
				
				<br>


			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>

	<script type="text/javascript">
	$(document).ready(function() {
	    var mannerBtn = $('#mannerupbtn');

	    mannerBtn.click(function() {
	        var sellerNickname = $('#sellerNickname').val();
	        var uProductId = $('#uProductId').val(); 
            var memberNickname = $('#memberNickname').val(); 
            var obj = {
                  'sellerNickname' : sellerNickname,
                  'uProductId' : uProductId,
                  'memberNickname' : memberNickname
            }
            console.log(obj);
	    	console.log(sellerNickname);

	            $.ajax({
	                type: 'POST',
	                url: 'manner/',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data : JSON.stringify(obj), // JSON으로 변환
	                success: function(result) {
	                    console.log(result);
	                    if(result == '1') {
	                        alert('매너칭찬을 하였습니다!')
	                     } else {
	                    	 alert('이미 매너칭찬을 하였습니다!')
	                     }
	                }
	            });
	    	});
	    });
	    
	</script>
	
	<script type="text/javascript">
	$(document).ready(function() {
	    var mannerBtn = $('#mannerdownbtn');

	    mannerBtn.click(function() {
	        var sellerNickname = $('#sellerNickname').val();
	        var uProductId = $('#uProductId').val(); 
            var memberNickname = $('#memberNickname').val(); 
            var obj = {
                  'sellerNickname' : sellerNickname,
                  'uProductId' : uProductId,
                  'memberNickname' : memberNickname
            }
            console.log(obj);
	    	console.log(sellerNickname);

	            $.ajax({
	                type: 'POST',
	                url: 'mannerdown/',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data : JSON.stringify(obj), // JSON으로 변환
	                success: function(result) {
	                    console.log(result);
	                    if(result == '1') {
	                        alert('매너온도를 내렸습니다')
	                     } else {
	                    	 alert('이미 매너온도를 내렸습니다')
	                     }
	                }
	            });
	    	});
	    });
	    
	</script>


</body>
</html>















