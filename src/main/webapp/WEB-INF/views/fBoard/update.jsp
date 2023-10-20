<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.fBoardTitle }</title>
</head>
<body>
	<h2>글 수정 페이지</h2>
	<form action="update" method="POST">
		<input type="hidden" name="page" value="${page }">
		<div>
			<p>글 번호 : ${vo.fBoardId }</p>
			<input type="hidden" name="fBoardId" value="${vo.fBoardId }">
		</div>
		<div>
			<p>제목 : </p>
			<input type="text" name="fBoardTitle" value="${vo.fBoardTitle }">  
		</div>
		<div>
			<p>작성자 : ${vo.memberNickname }</p>
			<p>작성일 : ${vo.fBoardCreatedDate }</p>
		</div>
		<div>
			<textarea rows="20" cols="120" name="fBoardContent">${vo.fBoardContent }</textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
</body>
</html>