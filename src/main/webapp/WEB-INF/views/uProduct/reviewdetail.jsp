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
				
					<div>
						<p>제목 :</p>
						<input type="text" name="uProductReviewTitle" value="${vo.uProductReviewTitle}"
							readonly="readonly">
					</div>

					<br>

					<div>
						<p>작성자 :</p>
						<input type="text" name="memberNickname" id="memberNickname"
							value="${vo.memberNickname }" readonly="readonly">
					</div>

					<br>

					<div>
   						 <p>내용 :</p>
  					  <textarea rows="15" cols="100" name="uProductReviewContent" readonly="readonly">${vo.uProductReviewContent}
  					  </textarea>
					</div>


			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>

	
</body>
</html>















