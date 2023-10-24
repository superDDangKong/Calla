<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.qBoardTitle }</title>
</head>
<body>
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
	
</body>
</html>