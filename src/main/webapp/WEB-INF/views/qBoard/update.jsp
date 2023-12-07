<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.qBoardTitle }</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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
				<h2>글 수정 페이지</h2>
				<form action="update" method="post">
					<input type="hidden" name="page" value= "${page }">
					<div>
						<p>글 번호 : ${vo.qBoardId }</p>
						<input type="hidden" name="qBoardId" value="${vo.qBoardId }" readonly> 
					</div>
					<div>
						<p>제목 : </p>
						<input type="text" name="qBoardTitle" value="${vo.qBoardTitle }">
					</div>
					<div>
						<p>작성자 : ${vo.memberNickname }</p>
						<p>작성일 : ${vo.qBoardCreatedDate }</p>
					</div>
					<div>
						<textarea rows="20" cols="120" name="qBoardContent">${vo.qBoardContent }</textarea>
					</div>
					<div>
						<input type="submit" value="등록">
					</div>
				</form>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
</body>
</html>