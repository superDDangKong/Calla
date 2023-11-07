<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
<title>${vo.fBoardTitle }</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container">
					<form action="update" method="post">
						<input type="hidden" name="page" value="${page}"> <input
							type="hidden" name="fBoardId" value="${vo.fBoardId}">
						<div class="form-group">
							<label for="fBoardTitle">제목</label> <input type="text"
								class="form-control" id="fBoardTitle" name="fBoardTitle"
								value="${vo.fBoardTitle}">
						</div>
						<p>작성자: ${vo.memberNickname}</p>
						<p>작성일: ${vo.fBoardCreatedDate}</p>
						<div class="form-group">
							<label for="fBoardContent">내용</label>
							<textarea class="form-control" id="fBoardContent"
								name="fBoardContent" rows="10">${vo.fBoardContent}</textarea>
						</div>
						<button type="submit" class="btn btn-primary">수정</button>
					</form>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
</body>
</html>